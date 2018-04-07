package com.yueny.blog.provider.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yueny.rapid.lang.agent.UserAgentResource;
import com.yueny.rapid.lang.agent.handler.UserAgentUtils;
import com.yueny.superclub.api.biz.Action;

/**
 * @author 袁洋 2015年8月5日 下午5:00:18
 *
 */
public abstract class BaseController implements Action {
	/**
	 * 存放当前线程的HttpServletRequest对象
	 */
	private final static ThreadLocal<HttpServletRequest> httpServletRequestThreadLocal = new ThreadLocal<>();
	/**
	 * 存放当前线程的Model对象
	 */
	private final static ThreadLocal<Model> modelThreadLocal = new ThreadLocal<>();
	/**
	 * 日志记录器
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 获取当前线程的UserAgent
	 */
	protected UserAgentResource getCurrentUserAgent() {
		return UserAgentUtils.getCurrentUserAgent(getRequest());
	}

	/**
	 * 获取当前线程的Model对象
	 *
	 * @return 当前线程的Model对象
	 */
	protected Model getModel() {
		return modelThreadLocal.get();
	}

	/**
	 * 获取当前线程的HttpServletRequest对象
	 *
	 * @return 当前线程的HttpServletRequest对象
	 */
	protected HttpServletRequest getRequest() {
		return httpServletRequestThreadLocal.get();
	}

	/**
	 * 从 HttpServletRequest 中获取属性值
	 *
	 * @param name
	 *            属性名
	 * @return 属性值
	 */
	protected Object getRequestAttribute(final String name) {
		final HttpServletRequest request = this.getRequest();
		final Object value = request.getAttribute(name);
		return value;
	}

	/**
	 * 获取当前线程的HttpSession对象
	 *
	 * @return 当前线程的HttpSession对象
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 从 HttpSession 中获取属性值
	 *
	 * @param name
	 *            属性名
	 * @return 属性值
	 */
	protected Object getSessionAttribute(final String name) {
		final HttpSession session = this.getSession();
		final Object value = session.getAttribute(name);
		return value;
	}

	/**
	 * 从session中获取用户编号
	 *
	 * @param request
	 * @return
	 */
	@Deprecated
	protected String getUserCode(final HttpServletRequest request) {
		final HttpSession session = request.getSession(true);
		// VisitContextHolder.getUserCode()
		return session.getAttribute("userCode").toString();
	}

	/**
	 * 请求重定向
	 *
	 * @param url
	 *            重定向请求
	 * @return 重定向请求
	 */
	protected String redirectAction(final String url) {
		return String.format("redirect:%s", url);
	}

	/**
	 * 向 Model 设置属性值
	 *
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	protected void setModelAttribute(final String name, final Object value) {
		getModel().addAttribute(name, value);
	}

	/**
	 * 向 HttpServletRequest 设置属性值
	 *
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	protected void setRequestAttribute(final String name, final Object value) {
		final HttpServletRequest request = this.getRequest();
		request.setAttribute(name, value);
	}

	/**
	 * 向 HttpSession 设置属性值
	 *
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void setSessionAttribute(final String name, final Object value) {
		final HttpSession session = this.getSession();
		session.setAttribute(name, value);
	}

	/**
	 * 使用@ModelAttribute注解标识的方法会在每个控制器中的方法访问之前先调用
	 *
	 * @param request
	 * @param model
	 */
	@ModelAttribute
	protected void setThreadLocal(final HttpServletRequest request, final Model model) {
		httpServletRequestThreadLocal.set(request);
		modelThreadLocal.set(model);
	}

}
