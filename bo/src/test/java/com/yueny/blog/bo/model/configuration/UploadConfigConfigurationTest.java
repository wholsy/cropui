package com.yueny.blog.bo.model.configuration;

import org.junit.Test;

public class UploadConfigConfigurationTest {
	/**
	 *
	 */
	@Test
	public void test() {
		final UploadConfigModel mm = UploadConfigConfiguration.getUploadConfig();
		System.out.println(mm);
	}
}
