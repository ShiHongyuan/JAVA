package designPattern.facade.pagemaker;

import java.io.IOException;
import java.io.Writer;

/**
 * 简单窗口模式：内部处理集合类中的生成html类
 */
class HtmlWriter {
    private Writer writer;
    public HtmlWriter(Writer writer) {
        this.writer = writer;
    }
    public void title(String title) throws IOException {
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<title>" + title + "</title>");
        writer.write("</head>");
        writer.write("<body>\n");
        writer.write("<h1>" + title + "</h1>");
    }
    public void paragraph(String msg) throws IOException {
        writer.write("<p>" + msg + "</p>");
    }
    public void link(String href, String caption) throws IOException {
        paragraph("<a href=\"" + href + "\">" + caption + "</a>");
    }
    public void mailto(String mailaddr, String username) throws IOException {
        link("mailto:" + mailaddr, username);
    }
    public void close () throws IOException {
        writer.write("</body>");
        writer.write("</html>\n");
        writer.close();
    }
}
