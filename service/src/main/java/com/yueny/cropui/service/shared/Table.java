package com.yueny.cropui.service.shared;

import java.util.ArrayList;
import java.util.List;

/**
 * 表格实体
 * 
 * @author <a href="mailto:yueny09@126.com"> 袁洋 2015年1月20日 上午11:24:54
 * 
 * @category tag
 */
public final class Table {
	private List<Row> rows;

	/**
	 * @param row
	 *            行实体
	 * @return 添加后的表格实体
	 */
	public Table addRow(final Row row) {
		if (null == rows) {
			rows = new ArrayList<Row>();
		}
		rows.add(row);
		return this;
	}

	@Override
	public String toString() {
		if (null == rows || rows.size() == 0) {
			return "";
		}
		StringBuffer resultBuffer = new StringBuffer();
		resultBuffer
				.append("<table  border='0' cellpadding='4' cellspacing='1' bgcolor='#464646' >");
		for (int i = 0, len = rows.size(); i < len; i++) {
			resultBuffer.append(rows.get(i));
		}
		resultBuffer.append("</table>");
		return resultBuffer.toString();
	}

}
