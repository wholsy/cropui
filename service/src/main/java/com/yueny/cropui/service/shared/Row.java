package com.yueny.cropui.service.shared;

import java.util.ArrayList;
import java.util.List;

/**
 * 行实体
 * 
 * @author <a href="mailto:yueny09@126.com"> 袁洋 2015年1月20日 上午11:22:36
 * 
 * @category tag
 */
public final class Row {
	private List<Column> columns;

	/**
	 * @param col
	 *            列(单元)实体
	 * @return 添加后的行对象
	 */
	public Row addColumn(final Column col) {
		if (null == columns) {
			columns = new ArrayList<Column>();
		}
		columns.add(col);
		return this;
	}

	/**
	 * @param colContent
	 *            列(单元)内容
	 * @return 添加后的行对象
	 */
	public Row addColumn(final String colContent) {
		if (null == columns) {
			columns = new ArrayList<Column>();
		}
		columns.add(new Column(colContent));
		return this;
	}

	/**
	 * 默认居右
	 * 
	 * @param colContent
	 *            列(单元)内容
	 * @return 添加后的行对象
	 */
	public Row addColumnAlignRight(final String colContent) {
		if (null == columns) {
			columns = new ArrayList<Column>();
		}
		columns.add(new Column(colContent, "right"));
		return this;
	}

	@Override
	public String toString() {
		if (null == columns || columns.size() == 0) {
			return "";
		}
		StringBuffer resultBuffer = new StringBuffer();
		resultBuffer.append("<tr  bgcolor='#FFFFFF'>");
		for (int i = 0, len = columns.size(); i < len; i++) {
			resultBuffer.append(columns.get(i));
		}
		resultBuffer.append("</tr>\n");
		return resultBuffer.toString();
	}

}
