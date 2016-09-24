/**
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月15日 上午11:36:25
 */
package com.yueny.cropui.service.io.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yueny.cropui.service.io.core.FileNioOutHelper;
import com.yueny.superclub.api.constant.Constants;

/**
 * 文件输出测试用例
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月15日 上午11:36:25
 */
public class FileNioOutHelperTest {
	/**
	 * 按行写文件一次,并追加
	 */
	@Test
	public void testDemo() {
		final String filePath = "E:/workspace/yueny/cropui/yueny/trunk/com/yueny/cropui/cropui-web/target/doc/bigFileByLineDemo.txt";

		final int all = 1000;
		final List<String> messages = new ArrayList<>();
		for (int i = 0; i < all; i++) {
			final String index = String.valueOf(i);
			final String message = "我家大美妮".concat(index)
					.concat(Constants.COMMA).concat(index)
					.concat(Constants.COMMA).concat(index)
					.concat(Constants.COMMA).concat(index);

			messages.add(message);
		}
		FileNioOutHelper.writeAppendFileByLine(filePath, messages);
	}

	/**
	 * 按行写文件一次,并不换行追加
	 */
	@Test
	public void testWriteAppendFile() {
		final String filePath = "E:/workspace/yueny/cropui/yueny/trunk/com/yueny/cropui/cropui-web/src/test/resources/doc/WriteAppendFile-test.txt";

		final int all = 5;
		for (int i = 0; i < all; i++) {
			final String index = String.valueOf(i);
			final String message = "我家大美妮".concat(index)
					.concat(Constants.COMMA).concat(index)
					.concat(Constants.COMMA).concat(index)
					.concat(Constants.COMMA).concat(index);

			FileNioOutHelper.writeAppendFile(filePath, message);
		}
	}

	/**
	 * 按行写文件一次,并追加
	 */
	@Test
	public void testWriteAppendFileByLine() {
		final String filePath = "E:/workspace/yueny/cropui/yueny/trunk/com/yueny/cropui/cropui-web/src/test/resources/doc/WriteAppendFileByLine-test.txt";

		final int all = 10;
		for (int i = 0; i < all; i++) {
			final String index = String.valueOf(i);
			final String message = "我家大美妮".concat(index)
					.concat(Constants.COMMA).concat(index)
					.concat(Constants.COMMA).concat(index)
					.concat(Constants.COMMA).concat(index);

			FileNioOutHelper.writeAppendFileByLine(filePath, message,
					String.valueOf(Constants.ENTERSTR));
		}
	}

	/**
	 * 按行写文件,并换行，追加多次
	 */
	@Test
	public void testWriteAppendsFileByLine() {
		final String filePath = "E:/workspace/yueny/cropui/yueny/trunk/com/yueny/cropui/cropui-web/src/test/resources/doc/WriteAppendsFileByLine-test.txt";

		final List<String> messages = new ArrayList<>();
		for (int i = 0; i < 9999; i++) {
			final String index = String.valueOf(i);
			final String message = index.concat(Constants.COMMA).concat(index)
					.concat(Constants.COMMA).concat(index)
					.concat(Constants.COMMA).concat(index);
			messages.add(message);
		}
		FileNioOutHelper.writeAppendFileByLine(filePath, messages);
	}

	/**
	 * 文件写入，如果存在该文件和数据，则覆盖
	 */
	@Test
	public void testWriteFile() {
		final String fileName = "E:/workspace/yueny/cropui/yueny/trunk/com/yueny/cropui/cropui-web/src/test/resources/doc/WriteFileByLine-test.txt";

		final String messages = "你是我的大大领导啊!";
		FileNioOutHelper.writeFile(fileName, messages);
	}

}
