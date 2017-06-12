package com.yueny.blog.service.util;

import org.junit.Test;

@Deprecated
public class ScriptEngineHelperTest {
	@Test
	public void TestHtmlToMarkdown() {
		final String jsUri = "/adm/assets/js/markdown/to-markdown.js";
		final String rs = ScriptEngineHelper.eval(jsUri, "toMarkdown", "<h3>Some HTML</h3>");
		System.out.println(rs);
	}
}
