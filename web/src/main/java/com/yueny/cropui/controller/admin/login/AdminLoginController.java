package com.yueny.cropui.controller.admin.login;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueny.blog.service.cas.admin.IAdminPassportService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.data.resp.pojo.response.JsonNormalResponse;
import com.yueny.rapid.lang.enums.BaseErrorType;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 后台admin控制器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月12日 下午8:17:40
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminLoginController extends BaseController {
	@Autowired
	private IAdminPassportService adminPassportService;

	/**
	 * 登录行为
	 */
	@RequestMapping(value = "/dologin", method = { RequestMethod.POST })
	@ResponseBody
	public JsonNormalResponse<Boolean> dologin(@RequestParam(value = "username") final String username,
			@RequestParam(value = "password") final String password, final HttpServletResponse response) {
		final JsonNormalResponse<Boolean> res = new JsonNormalResponse<>();

		try {
			final String originalPassword = password;
			final boolean getMatch = adminPassportService.getMatch(username, originalPassword);
			res.setData(getMatch);
		} catch (final Exception e) {
			res.setCode(BaseErrorType.SYSTEM_BUSY.getCode());
			res.setMessage(BaseErrorType.SYSTEM_BUSY.getMessage());
			res.setData(false);
		}

		return res;
	}

	/**
	 * 退出登录行为
	 */
	@RequestMapping(value = "/dologout", method = { RequestMethod.POST })
	@ResponseBody
	public JsonNormalResponse<Boolean> dologout(@RequestParam(value = "username") final String username,
			@RequestParam(value = "password") final String password, final HttpServletResponse response) {
		// CurrentUserUtils.getInstance().setUser(null);

		final JsonNormalResponse<Boolean> res = new JsonNormalResponse<>();
		res.setData(true);

		return res;
	}

	/**
	 * admin 登录页
	 */
	@RequestMapping(value = "/login")
	public String login(final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "LOGIN");

		return "admin/login";
	}

}
