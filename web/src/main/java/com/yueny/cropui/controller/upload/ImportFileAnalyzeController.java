package com.yueny.cropui.controller.upload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yueny.blog.service.util.FileUploadHelper;
import com.yueny.cropui.controller.BaseController;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.rapid.lang.json.JsonUtil;

/**
 * 文件图片上传解析控制器
 *
 * @author 袁洋 2015年8月22日 下午3:22:06
 *
 */
@Controller
public class ImportFileAnalyzeController extends BaseController {
	class NameComparator implements Comparator<Hashtable<String, Object>> {
		@Override
		public int compare(final Hashtable<String, Object> a, final Hashtable<String, Object> b) {
			final Hashtable<String, Object> hashA = a;
			final Hashtable<String, Object> hashB = b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
			}
		}
	}

	class SizeComparator implements Comparator<Hashtable<String, Object>> {
		@Override
		public int compare(final Hashtable<String, Object> a, final Hashtable<String, Object> b) {
			final Hashtable<String, Object> hashA = a;
			final Hashtable<String, Object> hashB = b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	class TypeComparator implements Comparator<Hashtable<String, Object>> {
		@Override
		public int compare(final Hashtable<String, Object> a, final Hashtable<String, Object> b) {
			final Hashtable<String, Object> hashA = a;
			final Hashtable<String, Object> hashB = b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
			}
		}
	}

	/**
	 * 文件管理
	 *
	 * @param uploadType
	 *            上传类型
	 * @param pathNode
	 *            当前文件相对路径(当前文件夹),path节点
	 * @param sortType
	 *            排序形式，name or size or type
	 * @param response
	 * @return
	 *
	 * @throws ServletException
	 * @throws IOException
	 * @throws DataVerifyAnomalyException
	 */
	@RequestMapping(value = "/file_manager_json", method = RequestMethod.GET)
	@ResponseBody
	public UploadManagerJsonResponse fileManager(@RequestParam(value = "dir") final String uploadType,
			@RequestParam(value = "path", defaultValue = "") final String pathNode,
			@RequestParam(value = "order", defaultValue = "name") final String sortType,
			final HttpServletResponse response) {
		// 从session中获取uid
		final String uid = "yuanyang";
		final UploadManagerJsonResponse resp = new UploadManagerJsonResponse();

		// 根目录路径
		final String rootCtxPath = FileUploadHelper.getROOT();
		// 根目录URL
		final String rootUrl = FileUploadHelper.getUri();

		String userUploadRootRelativePath;
		try {
			userUploadRootRelativePath = FileUploadHelper.getUploadRootRelativePath(uid, rootCtxPath, uploadType);
		} catch (final DataVerifyAnomalyException e) {
			logger.error("用户上传根目录失败.", e);
			resp.setError(0);
			resp.setMessage(e.getErrorMsg());
			return resp;
		}

		final String updoadFilePath = rootCtxPath + FileUploadHelper.FILE_SEPARATOR + userUploadRootRelativePath;

		if (uploadType != null) {
			if (!FileUploadHelper.supportUploadType(uploadType)) {
				resp.setError(0);
				resp.setMessage("Invalid Directory name.");
				return resp;
			}

			final File saveDirFile = new File(updoadFilePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}

		// 根据path参数，设置当前文件所在的各路径和URL
		final String currentPath = updoadFilePath + FileUploadHelper.FILE_SEPARATOR + pathNode;
		// 当前图片的url地址
		final String currentImageUrl = rootUrl + FileUploadHelper.FILE_SEPARATOR + userUploadRootRelativePath
				+ FileUploadHelper.FILE_SEPARATOR + pathNode;
		// 当前文件夹名称
		final String currentDirPath = pathNode;
		String moveupDirPath = "";
		if (!"".equals(pathNode)) {
			final String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		// 排序形式，name or size or type
		final String order = sortType.toLowerCase();

		// 不允许使用..移动到上一级目录
		if (pathNode.indexOf("..") >= 0) {
			resp.setError(0);
			resp.setMessage("Access is not allowed.");
			return resp;
		}

		// 最后一个字符不是/
		if (!"".equals(pathNode) && !pathNode.endsWith("/")) {
			resp.setError(0);
			resp.setMessage("Parameter is not valid.");
			return resp;
		}

		// 目录不存在或不是目录
		final File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			resp.setError(0);
			resp.setMessage("Directory does not exist.");
			return resp;
		}

		// 遍历目录取的文件信息
		final List<Hashtable<String, Object>> fileList = new ArrayList<Hashtable<String, Object>>();
		if (currentPathFile.listFiles() != null) {
			for (final File file : currentPathFile.listFiles()) {
				final Hashtable<String, Object> hash = new Hashtable<String, Object>();
				final String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					final String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", FileUploadHelper.getFileImageTypes().contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}

		resp.setMoveup_dir_path(moveupDirPath);
		resp.setCurrent_dir_path(currentDirPath);
		resp.setCurrent_url(currentImageUrl);
		resp.setFile_list(fileList);

		resp.setName("names");
		System.out.println(JsonUtil.toJson(resp));

		return resp;
	}

	private UploadResponse getUploadError(final String message) {
		final UploadResponse res = new UploadResponse();
		res.setError(1);
		res.setMessage(message);
		return res;
	}

	/**
	 * 单文件和多文件上传<br>
	 * kindeditor中多文件上传以多个单文件的多次请求的方式进行 * @param uploadType 上传类型
	 *
	 * @param uploadType
	 * @param localUrl
	 * @param response
	 * @return 上传后的地址url
	 */
	@RequestMapping(value = "/upload_json", method = RequestMethod.POST)
	@ResponseBody
	public UploadResponse uploadJson(@RequestParam(value = "dir", defaultValue = "image") final String uploadType,
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
