/**
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月17日 下午12:44:10
 */
package com.yueny.cropui.service.io.quick;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;

/**
 * 小文件的快速读取
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月17日 下午12:44:10
 */
public class FileNioQuickReader {
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FileNioQuickReader.class);

	/**
	 * 按行读文件
	 *
	 * @param filePath
	 *            文件路径名
	 * @return 读取文件的行列表
	 */
	public static List<String> quickReader(final String filePath) {
		/* 获取数据 */
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(filePath),
					Charset.forName("UTF-8"));
		} catch (final IOException e) {
			logger.warn("【文件读取】文件读取出现异常:{}.",
					Throwables.getStackTraceAsString(e));
		}
		return lines;
	}

}
