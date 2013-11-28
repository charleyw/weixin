package example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WeXinServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String weXinPara = req.getParameter("echostr");
        resp.getWriter().print(weXinPara);
        resp.flushBuffer();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        XMLParser xmlParser = new XMLParser(req.getReader());
        String toUser = xmlParser.getTextByTagName("FromUserName");
        String fromUser = xmlParser.getTextByTagName("ToUserName");
        String requestContent = xmlParser.getTextByTagName("Content");
        String result = "没有内容";
        if(requestContent.startsWith("text")){
            String content = "你发送了： "+requestContent;
            result ="<xml>\n" +
                    "<ToUserName><![CDATA[" +
                    toUser +
                    "]]></ToUserName>\n" +
                    "<FromUserName><![CDATA[" +
                    fromUser +
                    "]]></FromUserName>\n" +
                    "<CreateTime>12345678</CreateTime>\n" +
                    "<MsgType><![CDATA[text]]></MsgType>\n" +
                    "<Content><![CDATA[" +
                    content +
                    "]]></Content>\n" +
                    "</xml>";

        }else{
            String title = "图文信息："+requestContent;
            String description = "这个是一个图文信息，不知道显示出来是什么格式的，尽可能长一点看看有没有后面的省略号，图片和文章链接都是用的163";
            String picurl = "http://img1.cache.netease.com/cnews/2013/11/27/20131127100457a618a.jpg";
            String url = "http://news.163.com/13/1127/10/9EM98U8E0001124J.html";
            result = "<xml>\n" +
                    "<ToUserName><![CDATA[" +
                    toUser +
                    "]]></ToUserName>\n" +
                    "<FromUserName><![CDATA[" +
                    fromUser +
                    "]]></FromUserName>\n" +
                    "<CreateTime>12345678</CreateTime>\n" +
                    "<MsgType><![CDATA[news]]></MsgType>\n" +
                    "<ArticleCount>1</ArticleCount>\n" +
                    "<Articles>\n" +
                    "<item>\n" +
                    "<Title><![CDATA[" +
                    title +
                    "]]></Title> \n" +
                    "<Description><![CDATA[" +
                    description +
                    "]]></Description>\n" +
                    "<PicUrl><![CDATA[" +
                    picurl +
                    "]]></PicUrl>\n" +
                    "<Url><![CDATA[" +
                    url +
                    "]]></Url>\n" +
                    "</item>\n" +
                    "</Articles>\n" +
                    "</xml>";
        }
        resp.setContentType("text/xml;charset=utf-8");
        resp.getWriter().print(result);
    }
}
