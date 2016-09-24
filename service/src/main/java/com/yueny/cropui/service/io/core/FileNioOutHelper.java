/**
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月15日 上午11:32:32
 */
package com.yueny.cropui.service.io.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.yueny.superclub.api.constant.Constants;

/**
 * 文件输出
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月15日 上午11:32:32
 */
public class FileNioOutHelper {
	private static final int CAPACITY = 1024;
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FileNioOutHelper.class);

	/**
	 * 按行写文件,并不换行追加
	 *
	 * @param filePath
	 *            文件路径名
	 * @param message
	 *            写入的一次数据
	 */
	public static void writeAppendFile(final String filePath,
			final String message) {
		writeAppendFileByLine(filePath, message, "");
	}

	/**
	 * 按行写文件,并换行，追加多次
	 *
	 * @param filePath
	 *            文件路径名
	 * @param messages
	 *            写入的数据列表，分为多次追加
	 */
	public static void writeAppendFileByLine(final String filePath,
			final List<String> messages) {
		final Long currentTime = System.currentTimeMillis();
		logger.info("【按行追加文件】开始写入文件:{}，信息量：{}行,当前时间：{}.", filePath,
				messages.size(), currentTime);

		RandomAccessFile randomAccessFile = null;
		FileChannel channel = null;

		// 文件输出的目录不存在，则创建
		final String path = StringUtils.substringBeforeLast(filePath, "/");
		if (Files.notExists(Paths.get(path))) {
			try {
				Files.createDirectories(Paths.get(path));
			} catch (final IOException e) {
				logger.warn("【按行追加文件】输出文件路径：{}不存在，尝试创建失败:{}.", path,
						Throwables.getStackTraceAsString(e));
				return;
			}
		}

		try {
			// 随机读取,对文件末尾追加内容
			randomAccessFile = new RandomAccessFile(filePath, "rw");
			channel = randomAccessFile.getChannel();

			for (final String message : messages) {
				appendFile(channel, message, String.valueOf(Constants.ENTERSTR));
			}
		} catch (final FileNotFoundException e) {
			logger.warn("【按行追加文件】按行写文件,并追加出现异常:{}.",
					Throwables.getStackTraceAsString(e));
			// Throwables.propagate(e);
		} finally {
			try {
				channel.close();
				randomAccessFile.close();
			} catch (final IOException e) {
				logger.warn("【按行追加文件】关闭流，出现异常:{}.",
						Throwables.getStackTraceAsString(e));
			}
			logger.info("【按行追加文件】写入文件结束，总计历时(毫秒)：{}.",
					System.currentTimeMillis() - currentTime);
		}
	}

	/**
	 * 按行写文件,并追加
	 *
	 * @param filePath
	 *            文件路径名
	 * @param message
	 *            写入的一次数据
	 * @param endFlag
	 *            行结束标识，如换行符
	 */
	public static void writeAppendFileByLine(final String filePath,
			final String message, final String endFlag) {
		final Long currentTime = System.currentTimeMillis();
		logger.debug("【按行追加文件】开始写入文件:{}，写入信息：{}，当前时间：{}.", filePath, message,
				currentTime);

		RandomAccessFile randomAccessFile = null;
		FileChannel channel = null;

		try {
			// 随机读取,对文件末尾追加内容
			randomAccessFile = new RandomAccessFile(filePath, "rw");
			channel = randomAccessFile.getChannel();

			appendFile(channel, message, endFlag);
		} catch (final FileNotFoundException e) {
			logger.warn("【按行追加文件】按行写文件,并追加出现异常:{}.",
					Throwables.getStackTraceAsString(e));
			// Throwables.propagate(e);
		} finally {
			try {
				channel.close();
				randomAccessFile.close();
			} catch (final IOException e) {
				logger.warn("【按行追加文件】关闭流，出现异常:{}.",
						Throwables.getStackTraceAsString(e));
			}
			logger.info("【按行追加文件】写入文件结束，总计历时(毫秒)：{}.",
					System.currentTimeMillis() - currentTime);
		}
	}

	/**
	 * 文件写入，如果存在该文件和数据，则覆盖
	 *
	 * @param filePath
	 *            文件路径名
	 * @param message
	 *            写入数据
	 */
	public static void writeFile(final String filePath, final String message) {
		final Long currentTime = System.currentTimeMillis();
		logger.info("【按行写文件】开始写入文件:{}，写入信息：{}，当前时间：{}.", filePath, message,
				currentTime);

		FileOutputStream fos = null;
		FileChannel channel = null;
		try {
			fos = new FileOutputStream(filePath);
			// 第一步 获取一个通道
			channel = fos.getChannel();
			// buffer=ByteBuffer.allocate(1024);
			// 第二步 定义缓冲区
			ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
			// 将内容写到缓冲区
			fos.flush();
			channel.write(buffer);
			// 将缓冲区清理完毕，移动文件内部指针
			buffer.clear();
			buffer = null;
		} catch (final FileNotFoundException e) {
			logger.warn("【文件输出】按行写文件,并追加出现异常:{}.",
					Throwables.getStackTraceAsString(e));
			// Throwables.propagate(e);
		} catch (final IOException e) {
			logger.warn("【文件输出】按行写文件,并追加出现异常:{}.",
					Throwables.getStackTraceAsString(e));
		} finally {
			try {
				channel.close();
				fos.close();
			} catch (final IOException e) {
				logger.warn("【文件输出】关闭流，出现异常:{}.",
						Throwables.getStackTraceAsString(e));
			}
			logger.info("【按行写文件】写入文件结束，总计历时(毫秒)：{}.",
					System.currentTimeMillis() - currentTime);
		}
	}

	/**
	 * 写文件
	 *
	 * @param channel
	 *            FileChannel
	 * @param message
	 *            写入信息
	 * @param endFlag
	 *            文件结尾标识
	 */
	private static void appendFile(final FileChannel channel,
			final String message, final String endFlag) {
		try {
			// 调整文件指针的位置到文件的末尾
			channel.position(channel.size());

			/* 第二步 设定缓冲区 . 生成一个偏移量为0,容量和最大容量都为 <code>CAPACITY</code> 的ByteBuffer */
			ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);
			/* 第三步 数据源 */
			buffer.put(message.getBytes());
			if (StringUtils.isNotEmpty(endFlag)) {
				buffer.put(endFlag.getBytes());
			}
			// 用于将写模式转换成读模式(准备写入，防止其他读取，锁住文件)
			buffer.flip();
			// 写入文件
			channel.write(buffer);
			// 用于清空缓冲区，准备再次被写入，limit设置为capacity，position设置为0
			buffer.clear();
			buffer = null;
		} catch (final IOException e) {
			logger.warn("【追加文件】按行写文件,并追加出现异常:{}.",
					Throwables.getStackTraceAsString(e));
		}
	}

}
