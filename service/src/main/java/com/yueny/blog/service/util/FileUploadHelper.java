package com.yueny.blog.service.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.yueny.blog.bo.enums.BlogResultCodeType;
import com.yueny.blog.bo.model.configuration.UploadConfigConfiguration;
import com.yueny.blog.bo.model.configuration.UploadConfigModel;
import com.yueny.rapid.lang.exception.DataVerifyAnomalyException;
import com.yueny.rapid.lang.util.StringUtil;

import lombok.Getter;

/**
 * 上传帮助类
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月22日 下午9:09:35
 *
 */
public final class FileUploadHelper {
	// 定义允许上传的文件扩展名
	private static final Map<String, String> extMap = new HashMap<>();
	/**
	 * 文件分隔符
	 */
	public static final String FILE_SEPARATOR = "/";
	// 图片扩展名
	private static List<String> fileImageTypes;
	/** 日志记录器 */
	private static final Logger logger = LoggerFactory.getLogger(FileUploadHelper.class);
	// 最大文件大小
	private static final long maxSize = 1000000;
	@Getter
	private static String uploadBasePath;
	private static final UploadConfigModel uploadConfig = UploadConfigConfiguration.getUploadConfig();
	static {
		assemblyByTypes("image");
		uploadBasePath = getConfig().getUploadBase();
	}

	private static List<String> assemblyByTypes(final String type) {
		return getConfig().getUploadByType(type).getExtTypes();
	}

	private static void assemblyExtMap() {
		extMap.put("image", Joiner.on(",").join(getFileImageTypes()));
		extMap.put("flash", Joiner.on(",").join(assemblyByTypes("flash")));
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	}

	/**
	 * @return 上传配置模型
	 */
	public static final UploadConfigModel getConfig() {
		return uploadConfig;
	}

	private static Map<String, String> getExtMap() {
		if (MapUtils.isEmpty(extMap)) {
			assemblyExtMap();
		}
		return extMap;
	}

	/**
	 * @return 图片扩展名
	 */
	public static final List<String> getFileImageTypes() {
		if (fileImageTypes == null) {
			fileImageTypes = assemblyByTypes("image");
		}
		return Collections.unmodifiableList(fileImageTypes);
	}

	/**
	 * 上传根路径，可以指定绝对路径，比如 http://www.yoursite.com/attached/
	 */
	public static final String getROOT() {
		return getConfig().getRoot();
	}

	/**
	 * 用户上传根目录的uploadType目录<br>
	 * 服务器根目录 + 'uploadBasePath' + uid + uploadType
	 *
	 * @param uid
	 *            用户ID
	 * @param rootPath
	 *            服务器根目录,eg> /var/www/attached/
	 * @param uploadType
	 *            上传类型, eg> image
	 * @return eg> /var/www/attached/upload/zhangsan/image
	 * @throws DataVerifyAnomalyException
	 */
	public static final String getUploadRootRelativePath(final String uid, final String rootPath,
			final String uploadType) throws DataVerifyAnomalyException {
		/* 创建文件根目录 */
		final File rootDir = new File(rootPath);
		if (!rootDir.isDirectory()) {
			logger.error("上传根目录不存在:{}!", rootPath);
			throw new DataVerifyAnomalyException(BlogResultCodeType.FILE_UPLOAD_DIRECTORY_NOT_EXIST);
		}
		// 检查根目录写权限
		if (!rootDir.canWrite()) {
			logger.error("上传根目录没有写权限:{}!", rootPath);
			throw new DataVerifyAnomalyException(BlogResultCodeType.FILE_UPLOAD_DIRECTORY_WRITE_PERMISSION);
		}

		/* 创建上传目录'upload' */
		// 文件保存的相对路径
		final StringBuilder sb = new StringBuilder();
		sb.append(uploadBasePath);
		sb.append(FILE_SEPARATOR);
		if (StringUtil.isNotEmpty(uid)) {
			sb.append(uid);
			sb.append(FILE_SEPARATOR);
		}
		if (StringUtil.isNotEmpty(uploadType)) {
			sb.append(uploadType);
		}

		final String userUploadRootRelativePath = sb.toString();
		final File judgePhotoSaveDirFile = new File(rootPath + FILE_SEPARATOR + userUploadRootRelativePath);
		if (!judgePhotoSaveDirFile.exists()) {
			judgePhotoSaveDirFile.mkdirs();// 文件夹不存在则创建
		}

		return userUploadRootRelativePath;
	}

	/**
	 * 文件服务器uri,根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
	 */
	public static final String getUri() {
		return getConfig().getUri();
	}

	/**
	 * @return 是否支持的上传类型
	 */
	public static final boolean supportUploadType(final String uploadType) {
		return getExtMap().keySet().contains(uploadType);
	}

	/**
	 * @param uid
	 *            用户uid
	 * @param rootPath
	 *            根目录路径，可以指定绝对路径，比如 /var/www/attached/
	 * @param uploadType
	 *            上传类型
	 * @param multFiles
	 *            上传文件
	 *
	 * @return 上传地址
	 * @throws DataVerifyAnomalyException
	 */
	// @SneakyThrows(value = DataVerifyAnomalyException.class)
	public static final List<String> upload(final String uid, final String rootPath, final String uploadType,
			final Collection<MultipartFile> multFiles) throws DataVerifyAnomalyException {
		if (StringUtil.isEmpty(rootPath)) {
			logger.error("根路径配置不存在:{}!", rootPath);
			throw new DataVerifyAnomalyException(BlogResultCodeType.FILE_UPLOAD_DIRECTORY_NOT_EXIST);
		}

		if (!getExtMap().containsKey(uploadType)) {
			logger.error("不支持的上传类型:{}!", uploadType);
			throw new DataVerifyAnomalyException(BlogResultCodeType.FILE_UPLOAD_TYPE_UNSUPPORT);
		}

		// eg> /var/www/attached/upload/zhangsan/image
		final String userUploadRootRelativePath = getUploadRootRelativePath(uid, rootPath, uploadType);

		/* 创建归档文件夹 */
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		final String ymd = sdf.format(new Date());
		final String relative = userUploadRootRelativePath + FILE_SEPARATOR + ymd;

		final String updoadFilePath = rootPath + FILE_SEPARATOR + relative;
		// 文件保存目录URL
		final String saveUrl = getUri() + FILE_SEPARATOR + relative;

		final File dirFile = new File(updoadFilePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		/* 处理文件上传项 */
		final List<String> urls = Lists.newArrayList();
		for (final MultipartFile multFile : multFiles) {
			// 获取文件名
			final String fileName = multFile.getOriginalFilename();

			// 检查文件大小
			final long fileSize = multFile.getSize();
			if (fileSize > maxSize) {
				logger.error("上传文件大小超过限制:{}!", fileSize);
				throw new DataVerifyAnomalyException(BlogResultCodeType.FILE_UPLOAD_TOO_LARGE);
			}

			// 检查扩展名
			final String fileExtension = FilenameUtils.getExtension(fileName);
			if (!Arrays.asList(getExtMap().get(uploadType).split(",")).contains(fileExtension)) {
				logger.error("上传文件扩展名是不允许的扩展名。\n只允许{} 格式。", getExtMap().get(uploadType));
				throw new DataVerifyAnomalyException(BlogResultCodeType.FILE_UPLOAD_EXT_UNSUPPORT);
			}

			final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			final String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "_" + fileName + "." + fileExtension;
			try {
				final File uploadedFile = new File(updoadFilePath, newFileName);
				// 将文件写入服务器上的目录，文件名问newFileName
				FileCopyUtils.copy(multFile.getBytes(), uploadedFile);
			} catch (final Exception e) {
				e.printStackTrace();
				logger.error("上传文件失败!", e);
				throw new DataVerifyAnomalyException(BlogResultCodeType.FILE_UPLOAD_FAIL);
			}

			// eg> "http://localhost:8090/blog/upload/image/20921/26_22236.jpg";
			final String url = saveUrl + FILE_SEPARATOR + newFileName;
			urls.add(url);
		}

		return urls;
	}

}
