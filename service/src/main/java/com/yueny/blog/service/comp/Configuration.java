// package com.yueny.blog.service.common;
//
// import java.util.Properties;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
//
// import com.yueny.rapid.lang.util.utility.ConfigurationClassLoader;
//
/// **
// * @author yueny09 <deep_blue_yang@163.com>
// *
// * @DATE 2016年11月26日 下午2:59:53
// *
// */
// public abstract class Configuration extends ConfigurationClassLoader {
// /**
// * 日志记录器
// */
// private static final Logger logger =
// LoggerFactory.getLogger(Configuration.class);
// private static final Properties properties = new Properties();
//
// // static {
// // final File classpath = getClasspathFile("");
// // final File[] propertyFiles = classpath.listFiles(new FileFilter() {
// // @Override
// // public boolean accept(final File file) {
// // return file.getName().endsWith(".properties") &&
// // !file.getName().startsWith("log4j");
// // }
// // });
// // for (final File propertyFile : propertyFiles) {
// // try {
// // properties.load(new FileInputStream(propertyFile));
// // if (logger.isInfoEnabled()) {
// // logger.info("load properties file success :" + propertyFile);
// // }
// // } catch (final IOException e) {
// // logger.warn("load properties file failed , skiped :" + propertyFile, e);
// // }
// // }
// // }
// //
// // public static String get(final String key) {
// // return properties.getProperty(key);
// // }
//
// }
