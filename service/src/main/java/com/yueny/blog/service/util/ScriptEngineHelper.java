package com.yueny.blog.service.util;

import java.io.FileReader;
import java.io.IOException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * ScriptEngine脚本引擎
 *
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2017年6月9日 下午2:17:26
 * @since
 */
@Deprecated
public class ScriptEngineHelper {
	public static String eval(String jsUri, String functionMethod, Object... args) {
		final ScriptEngineManager manager = new ScriptEngineManager();
		final ScriptEngine engine = manager.getEngineByName("javascript");
		// Bindings bind = engine.createBindings();
		// bind.put("factor", 2); //这里绑定一个factor的值为2.
		// engine.setBindings(bind,ScriptContext.ENGINE_SCOPE);

		String rs = "";
		try {
			// 读取js文件
			final String jsFileName = jsUri;
			final FileReader reader = new FileReader(jsUri); // 执行指定脚本
			engine.eval(reader);
			// engine.eval("load('http://static.yueny.website/plugins/markdown/to-markdown.js')");

			if (engine instanceof Invocable) {
				final Invocable invoke = (Invocable) engine;

				rs = (String) invoke.invokeFunction(functionMethod, args);
			}
		} catch (ScriptException | IOException | NoSuchMethodException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

}
