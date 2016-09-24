/**
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月15日 下午5:42:19
 */
package com.yueny.cropui.service.io.muti;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.yueny.superclub.api.constant.Constants;

/**
 * 文件解析线程
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月15日 下午5:42:19
 */
@Deprecated
public class FileProcessorCallableReader implements Callable<List<String>> {
	/**
	 * 每次读取的量,1KB
	 */
	private static final int CAPACITY = 1024;
	/**
	 * 每行读取的量, 10字节
	 */
	private static final int LINE_CAPACITY = 500;
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FileProcessorCallableReader.class);
	/**
	 * 每次读取的量
	 */
	private ByteBuffer byteBuffer;
	private FileChannel channel = null;
	/**
	 * 是否结束
	 */
	private boolean isDone = false;
	private RandomAccessFile randomAccessFile = null;

	/**
	 * 构造方法
	 *
	 * @param file
	 *            文件
	 * @param start
	 *            读的起始位置
	 * @param end
	 *            读的结束位置
	 */
	@Deprecated
	public FileProcessorCallableReader(final File file, final long start,
			final long end) {
		try {
			randomAccessFile = new RandomAccessFile(file, "r");
			channel = randomAccessFile.getChannel();

			// /*
			// * map(FileChannel.MapMode mode,long position, long size)
			// *
			// * mode - 根据是按只读、读取/写入或专用（写入时拷贝）来映射文件，分别为 FileChannel.MapMode
			// 类中所定义的
			// * READ_ONLY、READ_WRITE 或 PRIVATE 之一
			// *
			// * position - 文件中的位置，映射区域从此位置开始；必须为非负数
			// *
			// * size - 要映射的区域大小；必须为非负数且不大于 Integer.MAX_VALUE
			// *
			// * 所以若想读取文件后半部分内容，如例子所写；若想读取文本后1/8内容，需要这样写map(FileChannel.MapMode.
			// * READ_ONLY, f.length()*7/8,f.length()/8)
			// *
			// * 想读取文件所有内容，需要这样写map(FileChannel.MapMode.READ_ONLY, 0,f.length())
			// */
			byteBuffer = ByteBuffer.allocate(CAPACITY);
			// channel.map(FileChannel.MapMode.READ_ONLY, start, end);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> call() throws Exception {
		logger.info(Thread.currentThread().getName() + " 正在读取");

		List<String> data = null;
		try {
			// 编码处理
			// final Charset cs = Charset.forName("UTF-8");
			// final CharsetDecoder decoder = cs.newDecoder();
			// final CharsetEncoder encoder = cs.newEncoder();
			// final CharBuffer cb = decoder.decode(byteBuffer);
			// final ByteBuffer outputData = encoder.encode(cb);

			data = readFileByLine(channel, byteBuffer);

			byteBuffer.clear();
			byteBuffer = null;

			// 文件读取完成后，删除源文件
			// file.delete();
			// } catch (final IOException e) {
			// e.printStackTrace();
		} finally {
			try {
				channel.close();
				randomAccessFile.close();
			} catch (final IOException e) {
				logger.warn("【读文件】关闭流，出现异常:{}.",
						Throwables.getStackTraceAsString(e));
			}
			done();
		}
		logger.info(Thread.currentThread().getName() + " 处理结束，读取条数：{}。",
				data.size());
		return data;
	}

	/**
	 *
	 */
	public void done() {
		isDone = true;
	}

	/**
	 * @return 是否结束
	 */
	public boolean isDone() {
		return isDone;
	}

	private List<String> readFileByLine(final FileChannel channel,
			final ByteBuffer byteBuffer) {
		final List<String> data = new ArrayList<>();
		try {
			// 每行缓存的字节
			byte[] bs = new byte[LINE_CAPACITY];

			while (true) {
				final int readFlag = channel.read(byteBuffer);
				if (readFlag == -1) {
					break;
				}

				byteBuffer.flip();
				while (byteBuffer.hasRemaining()) {
					bs = null;
					bs = new byte[LINE_CAPACITY];

					if (byteBuffer.remaining() > bs.length) {
						byteBuffer.get(bs);
					} else {
						byteBuffer.get(bs, 0, byteBuffer.remaining());
					}

					// 得到这一次读取的全部数据，做进一步的读取和分析
					String readData = StringUtils.trim(new String(bs, Charset
							.forName("UTF-8")));
					System.out.println("当前读到的数据是 :" + readData + ". 剩余字节："
							+ byteBuffer.remaining());

					if (StringUtils.isBlank(readData)) {
						continue;
					}

					/**
					 * 我家大美妮38,38,38,38 我家大�
					 *
					 * 转换为
					 *
					 * 我家大��妮39,39,39,39 我家大美妮40,40,40,40
					 *
					 * 转换后的乱码问题
					 * */
					final int index = readData.lastIndexOf(String
							.valueOf(Constants.ENTERSTR));
					// 不存在换行，则该数据直接无法被分析，加入下一次遍历的缓存中
					if (index == -1) {
						// 当前对象 bs 不是完整的一行
						System.out.println("不完整数据行：" + readData);
						continue;
					}

					if (index != readData.length()) {
						// 不是期望的结尾，放入buffer中
						final String s = StringUtils.substringAfterLast(
								readData, String.valueOf(Constants.ENTERSTR));

						// final int newPosition = byteBuffer.position();
						byteBuffer.put(s.getBytes(Charset.forName("UTF-8")));
						System.out.println("before compact:" + byteBuffer);
						System.out.println(new String(byteBuffer.array()));
						byteBuffer.flip();
						System.out.println("after flip:" + byteBuffer);
						System.out.println(new String(byteBuffer.array()));
						// byteBuffer.position(newPosition);

						// 获得合法的部分
						readData = StringUtils.substringBeforeLast(readData,
								String.valueOf(Constants.ENTERSTR)).concat(
								String.valueOf(Constants.ENTERSTR));
					}

					StringBuffer line = new StringBuffer();
					for (final char c : readData.toCharArray()) {
						if (c != Constants.ENTERSTR) {
							line.append(c);
							continue;
						}
						data.add(line.toString());
						line = null;
						line = new StringBuffer();
					}
				}
				byteBuffer.clear();
			}
		} catch (final IOException e) {
			logger.warn("【读取文件】读取文件片段,出现异常:{}.",
					Throwables.getStackTraceAsString(e));
		}
		return data;
	}

}
