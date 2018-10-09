package com.yueny.cropui.controller.console;

import com.taobao.diamond.common.Constants;
import com.yueny.blog.service.admin.manager.ILoginManager;
import com.yueny.blog.service.IValidCodeService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.data.resp.pojo.response.BaseResponse;
import com.yueny.rapid.lang.exception.invalid.InvalidException;
import com.yueny.rapid.lang.mask.MaskOcclusionUtil;
import com.yueny.superclub.util.sla.api.SlaRateLimit;
import com.yueny.superclub.util.sla.enums.SlaRateLimitReturnType;
import com.yueny.superclub.util.sla.enums.SlaRateLimitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信、邮件验证码服务
 * 
 * Created by yangcs on 2017年9月4日下午2:35:25.
 *
 * Copyright © mizhuanglicai
 *
 * @since 1.2.0
 */
@RestController
@RequestMapping("/validate")
public class ValidateCodeController extends BaseController {
	@Autowired
	private IValidCodeService validCodeService;
	@Autowired
	private ILoginManager loginManager;

	/**
	 * 用户登录时，获取短信/邮件验证码
	 * 
	 * @param username 用户名
	 * @param password 输入的待校验密码
	 * @return 响应
	 */
	@RequestMapping(value = "/login/get", method = { RequestMethod.POST })
	@SlaRateLimit(type = SlaRateLimitType.SAL, threshold = 2, returnType = SlaRateLimitReturnType.JSON)
	public BaseResponse getLoginCode(@RequestParam(value = "username") final String username,
			@RequestParam(value = "password") final String password) {
		BaseResponse response = new BaseResponse();
		
		if (getSession().getAttribute(Constants.LOGIN_DIST_NAME) != null) {
			return response;
		}

		try {
			if (!loginManager.login(username, password)) {
				response.setCode("fail!");
				response.setMessage("验证码发送失败，用户信息错误");
				return response;
			}
		} catch (InvalidException e){
			response.setCode(e.getErrorCode());
			response.setMessage(e.getErrorMsg());
			return response;
		}

		boolean rs = validCodeService.sendCode("yueny09@163.com");
		if(rs){
			response.setMessage("验证码发已发送至 " + MaskOcclusionUtil.occlusionEmail("yueny09@163.com"));
		}else{
			response.setCode("fail!");
			response.setMessage("验证码发送失败，发送要素存在问题！");
		}
		
		return response;
	}

}
