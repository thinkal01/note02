package com.note.common.lucene.itcast.utils;

import com.note.common.lucene.itcast.bean.PageIndex;

/**
 * 获取开始页与结束页
 */
public class PageUtils {
    /**
     * 获取开始,结束页
     *
     * @param currentPage   当前页
     * @param total，总记录数...
     * @return
     */
    public static PageIndex getPageCount(int currentPage, int total, int pageSize) {
        int viewpagecount = 10;
        int totalpage = total / pageSize;
        int startpage = currentPage - (viewpagecount % 2 == 0 ? viewpagecount / 2 - 1 : viewpagecount / 2);
        int endpage = currentPage + viewpagecount / 2;
        if (startpage < 1) {
            startpage = 1;
            if (totalpage >= viewpagecount) endpage = viewpagecount;
            else endpage = totalpage;
        }
        if (endpage > totalpage) {
            endpage = totalpage;
            if ((endpage - viewpagecount) > 0) startpage = endpage - viewpagecount + 1;
            else startpage = 1;
        }
        PageIndex pageIndex = new PageIndex();
        pageIndex.setStartPage(startpage);
        pageIndex.setEndPage(endpage);
        return pageIndex;
    }

    public static void main(String[] args) {
        getPageCount(6, 203, 10);
    }

}
