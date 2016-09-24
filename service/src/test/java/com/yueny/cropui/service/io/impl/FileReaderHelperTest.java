/**
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月14日 下午3:09:10
 */
package com.yueny.cropui.service.io.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import com.yueny.cropui.service.io.core.FileReaderHelper;

/**
 * 文件读取测试用例
 *
 * @author <a href="mailto:yueny09@163.com"> 袁洋
 *
 * @date 2015年9月14日 下午3:09:10
 */
@Slf4j
public class FileReaderHelperTest {
	/**
	 * 文件批量读取
	 */
	@Test
	public void testReadBatch() {
		final String filePath = "E:/workspace/yueny/cropui/yueny/trunk/com/yueny/cropui/cropui-web/target/doc/bigFileByLineDemo.txt";

		final List<String> data = FileReaderHelper.readBatch(filePath);
		log.info("读取文件结果：{}。", data);
	}

	/**
	 * 文件读取，一次性得到行结果
	 */
	@Test
	public void testReadLump() {
		final String filePath = "E:/workspace/yueny/cropui/yueny/trunk/com/yueny/cropui/cropui-web/target/doc/bigFileByLineDemo.txt";

		// Constants.COMMA
		final List<String> data = FileReaderHelper.readLump(filePath);
		log.info("读取文件结果：{}。", data);
	}

}
