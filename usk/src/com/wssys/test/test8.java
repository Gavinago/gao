package com.wssys.test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.EditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class test8 {

	/**
     * 解析
     * 
     * @throws Exception
     */
    public static void parser() throws Exception {
        URL url = new URI("http://www.java2s.com/").toURL();
        URLConnection conn = url.openConnection();

        Reader rd = new InputStreamReader(conn.getInputStream());
        EditorKit kit = new HTMLEditorKit();
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
        kit.read(rd, doc, 0);

        HTMLDocument.Iterator it = doc.getIterator(HTML.Tag.A);
        while (it.isValid()) {
            SimpleAttributeSet s = (SimpleAttributeSet) it.getAttributes();

            String link = (String) s.getAttribute(HTML.Attribute.HREF);
            if (link != null) {
                System.out.println(link);
            }
            it.next();
        }
    }

    /**
     * 解析
     * 
     * @throws Exception
     */
    public static void parser2() throws Exception {
        StringBuffer output = new StringBuffer();

        FileReader file = new FileReader("a.htm");
        BufferedReader buff = new BufferedReader(file);
        boolean eof = false;
        while (!eof) {
            String line = buff.readLine();
            if (line == null){
                eof = true;
            } else{
                output.append(line + "\n");
            }
                
        }
        buff.close();
        String page = output.toString();
        Pattern pattern = Pattern.compile("<a.+href=\"(.+?)\"");
        Matcher matcher = pattern.matcher(page);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        parser();
        //parser2();
    }

}
