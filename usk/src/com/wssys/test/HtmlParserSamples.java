package com.wssys.test;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Enumeration;
 
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
 
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HtmlParserSamples {
	private final static String HTML = 
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
            "<head>\n" + 
            "  <title>This is a test page</title>\n" + 
            "</head>\n" +
            "<body>\n" + 
            "  <h1>This is a simple Html page to test:</h1>\n" + 
            "  <table>\n" + 
            "    <tr>\n" + 
            "      <td>Hello</td>\n" + 
            "      <td>World!</td>\n" + 
            "    </tr>\n" + 
            "  </table>\n" + 
            "</body>\n" + 
            "</html>";
     
    public static void main(String[] args) throws Exception {
        //parseWithSwing();
        parseWithJsoup();
        parseWithHtmlCleaner();
    }
    
    private static void parseWithSwing() throws IOException {
        System.out.println("*** SWING ***");
        Reader reader = new StringReader(HTML);
        HTMLEditorKit.Parser parser = new ParserDelegator();
        //parser.parse(reader, new SwingHtmlParser(), true);

        parser.parse(reader,new SwingHtmlParser(), true);
        reader.close();
        System.out.println();
    }
     
    private static void parseWithJsoup() {
        System.out.println("*** JSOUP ***");
        Document doc = Jsoup.parse(HTML);
        System.out.println("Title: " + doc.getElementsByTag("title").text());
        System.out.println("H1: " + doc.getElementsByTag("h1").text());
        Element table = doc.getElementsByTag("table").first();
        Elements trs = table.getElementsByTag("tr");
        for (Element tr : trs) {
            System.out.println("TR: " + tr.text());
            for (Element td : tr.getAllElements()) {
                System.out.println("TD: " + td.text());
            }
        }
        System.out.println();
    }
    
    private static void parseWithHtmlCleaner() throws XPatherException {
        System.out.println("*** HTMLCLEANER ***");
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode node = cleaner.clean(HTML);
        System.out.println("Title: " + ((TagNode)(node.evaluateXPath("//title")[0])).getText());
        System.out.println("H1: " + ((TagNode)(node.evaluateXPath("//h1")[0])).getText());
        for (Object o : node.evaluateXPath("//tr")) {
            System.out.println("TR: " + ((TagNode)(o)).getText());          
        }
        for (Object o : node.evaluateXPath("//td")) {
            System.out.println("TD: " + ((TagNode)(o)).getText());          
        }
        System.out.println();
    }
 
}
