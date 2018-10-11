/**
 *
 */
package com.yueny.cropui.controller.admin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.yueny.blog.common.enums.CropuiToolkitsErrorType;
import com.yueny.blog.common.util.AjaxUtils;
import com.yueny.rapid.data.resp.pojo.response.BaseResponse;

/**
 * 统一处理验证失败异常 使用此切片后 @Valid 注解验证的参数后不用再加Errors或Bindingesult
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2018年1月17日 下午7:15:44
 *
 */
@ControllerAdvice
public class ValidateControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ValidateControllerAdvice.class);

	/**
	 * bean校验未通过异常
	 *
	 * @see javax.validation.Valid
	 * @see org.springframework.validation.Validator
	 * @see org.springframework.validation.DataBinder
	 */
	@ExceptionHandler(BindException.class)
	public String validExceptionHandler(final BindException e, final WebRequest request,
			final HttpServletResponse response) {

		final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		for (final FieldError error : fieldErrors) {
			logger.error(error.getField() + ":" + error.getDefaultMessage());
		}
		request.setAttribute("fieldErrors", fieldErrors, WebRequest.SCOPE_REQUEST);
		if (AjaxUtils.isAjaxRequest(request)) {
			final Map<String, Object> attrMap = new HashMap<String, Object>();
			final String[] atrrNames = request.getAttributeNames(WebRequest.SCOPE_REQUEST);
			for (final String attr : atrrNames) {
				final Object value = request.getAttribute(attr, WebRequest.SCOPE_REQUEST);
				if (value instanceof Serializable) {
					attrMap.put(attr, value);
				}

			}

			/**
			 * attrMap is
			 *
			 * {currentURL=http://localhost:8080/console/categories_tag/update/,
			 * hiddenHttpMethodFilter.FILTERED=true, fieldErrors=[Field error in object
			 * 'tagsForCategoriesModifyRequest' on field 'tagsForUpategoriesCode': rejected
			 * value []; codes
			 * [NotEmpty.tagsForCategoriesModifyRequest.tagsForUpategoriesCode,NotEmpty.tagsForUpategoriesCode,NotEmpty.java.lang.String,NotEmpty];
			 * arguments
			 * [org.springframework.context.support.DefaultMessageSourceResolvable: codes
			 * [tagsForCategoriesModifyRequest.tagsForUpategoriesCode,tagsForUpategoriesCode];
			 * arguments []; default message [tagsForUpategoriesCode]]; default message
			 * [不能为空]],
			 * org.springframework.web.servlet.HandlerMapping.bestMatchingPattern=/console/categories_tag/update/,
			 * ctx=, encodingFilter.FILTERED=true,
			 * org.springframework.web.servlet.DispatcherServlet.OUTPUT_FLASH_MAP=FlashMap
			 * [attributes={}, targetRequestPath=null, targetRequestParams={}],
			 * measurementStartTime=1516190775827,
			 * org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping=/console/categories_tag/update/,
			 * org.springframework.web.servlet.HandlerMapping.uriTemplateVariables={}}
			 *
			 */

			final StringBuilder message = new StringBuilder();
			for (final FieldError fieldError : fieldErrors) {
				if (StringUtils.equals(fieldError.getDefaultMessage(), "不能为空")) {
					message.append(fieldError.getField());
				}
				message.append(fieldError.getDefaultMessage());
			}

			final BaseResponse resp = new BaseResponse();
			resp.setCode(CropuiToolkitsErrorType.DATA_VERIFY_ERROR.getCode());
			resp.setMessage(message.toString());

			AjaxUtils.writeJson(resp, response);
			return null;
		}

		return "/validError";
	}

}
