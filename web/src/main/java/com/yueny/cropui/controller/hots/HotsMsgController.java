package com.yueny.cropui.controller.hots;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueny.blog.bo.hots.HotsMsgBo;
import com.yueny.cropui.controller.BaseController;

/**
 * 热点信息控制器
 *
 * @author 袁洋 2015年8月6日 下午2:05:09
 *
 */
@RequestMapping(value = "/hots")
@Controller
public class HotsMsgController extends BaseController {
	/**
	 * 查询同城最近热点信息
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return 同城最近热点信息
	 */
	@RequestMapping(value = "/hotsCityMsg/")
	@Deprecated
	public List<String> queryRelateCityHotsMessage(final HttpServletRequest request,
			final HttpServletResponse response) {
		final List<String> list = new ArrayList<String>();

		return list;
	}

	/**
	 * 查询最近热点信息
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return 最近热点信息
	 */
	@RequestMapping(value = "/hotsMsg/", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<HotsMsgBo> queryRelateHotsMessage(final HttpServletRequest request,
			final HttpServletResponse response) {
		final List<HotsMsgBo> list = new ArrayList<HotsMsgBo>();
		final HotsMsgBo hotsMsgBo = new HotsMsgBo();
		hotsMsgBo.setTitle("title");
		hotsMsgBo.setContext("context");
		hotsMsgBo.setIp("123.123.123.1");
		// hotsMsgBo.setUpdateTime(DateFormatUtil.getCurrentTimestamp());
		list.add(hotsMsgBo);

		return list;
	}

}
