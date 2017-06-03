package com.yueny.blog.bo.utils;

public class PagerUT {

	/**
	 * 返回的是分页部分的html内容 <br>
	 * offset 可以设置的范围是>0即可 curr_page是从1开始的
	 *
	 * @param allCount
	 *            分页前的根据搜索条件得到的总数量
	 * @param curr_page
	 *            要请求第几页的内容
	 * @param pageurl
	 *            url的通用地址[要携带可能有的查询参数]
	 * @param pageno_offset
	 * @return
	 */
	public static String pages(long pagesize, long allCount, long curr_page, String pageurl, int pageno_offset) {

		// 总页数
		final long pagenums = allCount % pagesize == 0 ? allCount / pagesize : allCount / pagesize + 1;

		if (pagenums <= 1) {
			return "";
		}
		String multipage = new String();

		if (pageno_offset < 0) {
			pageno_offset = 0;
		}
		final int offset = pageno_offset;// 当前连接左右的个数 【】

		// +1是当前页面，+2是第一页和最后一页
		final long fullsize = 2 * offset + 1 + 2;// 最全状态下要显示的数量，两个省略号中的数量+2=11

		long from = curr_page - offset;
		long to = curr_page + offset;

		// 确定from和to的位置
		if (fullsize >= pagenums) {
			from = 2;
			to = pagenums - 1;
		} else {
			if (from <= 1) {
				to = fullsize - 1;
				from = 2;
			} else if (to >= pagenums) {
				from = pagenums - (fullsize - 2);
				to = pagenums - 1;
			}
		}

		// 上一页连接和第一页的连接
		if (curr_page > 0) {
			final long perpage = curr_page == 1 ? 1 : curr_page - 1;
			if (curr_page == 1) {
				multipage += " <li class='paginate_button previous disabled'><a  href="
						+ String.format(pageurl, perpage) + ">上一页</a></li>  ";
				multipage += "<li class='paginate_button active'><a>1</a></li>";
			} else {
				multipage += "<li><a  class='paginate_button previous' no='" + perpage + "' href="
						+ String.format(pageurl, perpage) + ">上一页</a></li>  ";
				multipage += " <li><a  class='paginate_button' no='1'   href=" + String.format(pageurl, "1")
						+ ">1</a></li>  ";
			}
		}

		// 两个省略号中间的部分
		for (long i = from; i <= to; i++) {
			if (i != curr_page) {
				multipage += "<li><a  class='paginate_button' no='" + i + "'  href=" + String.format(pageurl, i) + ">"
						+ i + "</a></li>";
			} else {
				multipage += " <li class='paginate_button active'><a>" + curr_page + "</a></li> ";
			}
		}

		// 最后的链接
		if (curr_page < pagenums) {
			multipage += "<li><a   class='paginate_button'  no='" + pagenums + "'  href="
					+ String.format(pageurl, pagenums) + ">" + pagenums + "</a></li> ";
			multipage += "<li><a  class='paginate_button' no='" + (curr_page + 1) + "' href="
					+ String.format(pageurl, curr_page + 1) + ">下一页</a></li> ";
		} else if (curr_page == pagenums) {
			multipage += "<li class='paginate_button active'><a  href=" + String.format(pageurl, pagenums) + ">"
					+ pagenums + "</a><li>";
			multipage += "<li class='paginate_button next disabled'><a href=" + String.format(pageurl, curr_page)
					+ ">下一页</a></li>";
		}
		return multipage;
	}

}
