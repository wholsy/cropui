package com.yueny.cropui.controller.upload;

import com.yueny.blog.service.util.FileUploadHelper;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.rapid.lang.json.JsonUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * MD富文本下的文件图片上传解析控制器
 *
 * @author 袁洋 2015年8月22日 下午3:22:06
 *
 */
@Controller
public class FileUploadForMDController extends BaseController {
	private UploadResponse getUploadError(final String message) {
		final UploadResponse res = new UploadResponse();
		res.setError(0);
		res.setMessage(message);
		return res;
	}

	/**
	 * 单文件和多文件上传<br>
	 * kindeditor中多文件上传以多个单文件的多次请求的方式进行 * @param uploadType 上传类型
	 *
	 * @return 上传后的地址url
	 */
	@RequestMapping(value = "/upload_md", method = RequestMethod.POST)
	@ResponseBody
	public UploadResponse uploadJson(@RequestParam(value = "uploadType", defaultValue = "image") final String uploadType,
									 @RequestParam("editormd-image-file") MultipartFile picPaths,
			final HttpServletResponse response) {
		if (!ServletFileUpload.isMultipartContent(getRequest())) {
			return getUploadError("请选择文件。");
		}

		// 从session中获取uid
		final String uid = "yuanyang";
		// 根目录路径
		// request.getSession().getServletContext().getRealPath("/");
		final String rootCtxPath = FileUploadHelper.getROOT();

		final MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) getRequest();
		final Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

		final UploadResponse res = new UploadResponse();
		res.setMessage("上传成功");
		try {
			final List<String> urls = FileUploadHelper.upload(uid, rootCtxPath, uploadType, fileMap.values());

			res.setUrl(urls.get(0));
		} catch (final DataVerifyAnomalyException e) {
			return getUploadError(e.getErrorMsg());
		} catch (final Exception e) {
			return getUploadError("busy");
		}
		return res;
	}

}
