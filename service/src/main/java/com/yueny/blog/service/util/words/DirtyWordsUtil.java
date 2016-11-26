package com.yueny.blog.service.util.words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.yueny.blog.service.common.Configuration;
import com.yueny.rapid.lang.util.StringUtil;

/**
 * 脏词
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年11月26日 下午2:58:50
 *
 */
public abstract class DirtyWordsUtil {

	private static volatile List<String> dirtyWords = new CopyOnWriteArrayList<String>();

	static {
		flush();
	}

	public static void flush() {
		try {
			final List<String> temp = new ArrayList<>();
			final BufferedReader reader = new BufferedReader(
					new FileReader(Configuration.getClasspathFile("WEB-INF/words/dirty.words.txt")));
			String line;
			while ((line = reader.readLine()) != null) {
				if (!StringUtil.isEmpty(line.trim())) {
					temp.add(line.trim());
				}
			}
			reader.close();
			dirtyWords = temp;
		} catch (final FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isDirtyWords(final String words) {
		for (final String dirtyWord : dirtyWords) {
			if (words.contains(dirtyWord)) {
				return true;
			}
		}
		return false;
	}

}
