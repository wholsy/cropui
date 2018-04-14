package com.yueny.cropui.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yueny.blog.bo.model.document.ArchiveData;
import com.yueny.blog.service.manager.IArticleQueryManageService;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.superclub.api.page.core.PageCond;
import com.yueny.superclub.util.web.security.contanst.WebAttributes;

/**
 * 存档控制器
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月10日 上午11:57:31
 *
 */
@Controller
public class ArchiveController extends BaseController {
	@Autowired
	private IArticleQueryManageService articleQueryService;

	/**
	 * 获得存档信息
	 */
	// TODO 该接口需要分页
	@RequestMapping(value = "/archive.html", method = { RequestMethod.GET })
	public String getArchive(Integer currentPage, Integer pageSize, final HttpServletResponse response) {
		setModelAttribute(WebAttributes.ACTION, "archive");
		setModelAttribute("title", "Archive");

		try {
			if (currentPage == null) {
				currentPage = 1;
			}
			if (pageSize == null) {
				pageSize = 10;
			}
			final PageCond pageable = new PageCond(currentPage, pageSize);

			// 从session中获取uid
			// final String uid = "yuanyang";
			final List<ArchiveData> archiveList = articleQueryService.getArchive(pageable);

			setModelAttribute("currentPage", currentPage);
			setModelAttribute("pageSize", pageSize);
			setModelAttribute("archiveList", archiveList);
		} catch (final DataVerifyAnomalyException e) {
			logger.error("【获得存档信息】出现错误!", e);
			// 文章出错,回首页
			return redirectAction("/");
		}

		return "document/archive";
	}

}
