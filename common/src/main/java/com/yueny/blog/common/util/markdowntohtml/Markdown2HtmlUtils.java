package com.yueny.blog.common.util.markdowntohtml;

import java.io.FileReader;
import java.io.IOException;

import org.pegdown.PegDownProcessor;

/**
 * markdown转html格式
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月9日 下午2:03:37
 * @since
 */
@Deprecated
public class Markdown2HtmlUtils {
	public static void main(String[] args) throws IOException {
		System.out.println(markdownToHtml("## Headline with ID ## "));
	}

	public static String markdownToHtml(FileReader markdown) {
		try {
			// java.io.InputStream in =
			// this.getClass().getResourceAsStream("markdown.md");
			String md = null;
			final FileReader r = new FileReader("markdown.md");
			final char[] cbuf = new char[1024];
			while (r.read(cbuf) != -1) {
				md = new String(cbuf);
			}

			return markdownToHtml(md);
		} catch (final IOException e) {
			// TODO: handle exception
		}
		return "";
	}

	public static String markdownToHtml(String md) {
		final PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
		final String html = pdp.markdownToHtml(md);
		return html;
	}

}
