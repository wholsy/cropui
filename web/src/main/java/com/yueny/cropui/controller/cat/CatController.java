package com.yueny.cropui.controller.cat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.data.resp.pojo.response.NormalResponse;

/**
 * 愉快聊天控制器
 *
 * @author 袁洋 2015年8月6日 下午2:05:46
 *
 */
@RequestMapping(value = "/cat")
@Controller
public class CatController extends BaseController {

	/**
	 * 发布聊天信息
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param res
	 *            HttpServletResponse
	 * @return 发布聊天信息
	 */
	@RequestMapping(value = "/pushCat/", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public NormalResponse<String> pushCat(final HttpServletRequest request, final HttpServletResponse res) {
		final NormalResponse<String> response = new NormalResponse<>();

		return response;
	}

}
