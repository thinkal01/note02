package com.note.common.lucene.itcast.servlet;


import com.note.common.lucene.itcast.bean.Article;
import com.note.common.lucene.itcast.bean.PageIndex;
import com.note.common.lucene.itcast.bean.ResultBean;
import com.note.common.lucene.itcast.service.LuceneService;
import com.note.common.lucene.itcast.service.LuceneServiceImpl;
import com.note.common.lucene.itcast.utils.Constants;
import com.note.common.lucene.itcast.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 搜索servlet...
 */
public class SearchServlet extends HttpServlet {
    private LuceneService luceneService = new LuceneServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("currentPage");
        int current = Integer.parseInt(page);
        int firstResult = (current - 1) * Constants.PAGESIZE;//从第几条开始显示...
        int maxResult = current * Constants.PAGESIZE;//到第几条结束....
        String keywords = req.getParameter("keywords");
        ResultBean<Article> resultBean = luceneService.getResultBean(keywords, firstResult, maxResult);

        int totalPage = resultBean.getTotal() / Constants.PAGESIZE;
        /**
         *<strong>1</strong><a href="">2</a><a href="s?q=%E4%B8%AD%E5%9B%BD&pn=3&j=0&src=srp_paging">3</a>
         *<a id="snext"  href="s?q=%E4%B8%AD%E5%9B%BD&pn=2&j=0&src=srp_paging">下一页></a>
         */
        PageIndex pageIndex = PageUtils.getPageCount(current, resultBean.getTotal(), 10);
        StringBuffer buffer = new StringBuffer();
        for (int i = pageIndex.getStartPage(); i <= pageIndex.getEndPage(); i++) {
            if (current == i) {
                buffer.append("<strong>").append(i).append("</strong>");
            } else {
                buffer.append("<a style='width:65px;' href='query?currentPage=").append(i).append("&keywords=").append(keywords).append("'>")
                        .append(i).append("</a>");
            }
        }
        System.out.println("date==" + buffer.toString());
        req.setAttribute("pagetotal", buffer.toString());
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("resultbean", resultBean);
        /**
         * ${resultBean.total}
         * <c:foreach var item="it"
         * document.add(field)
         * Stringfield  不分词  雾霾的
         * TextField 分词
         *
         * String keywords="雾霾的"
         * Query query=new TermQuery(new Term("title",keywords));
         * QueryParser quereyparse=new MultiQueryParser(version,String fields [] ,anlyzer);
         * Query query=quereyparse.parser(keywords); //standardAnalyzer();雾  霾   的
         *
         */
        req.getRequestDispatcher("querylist.jsp").forward(req, resp);
    }

}
