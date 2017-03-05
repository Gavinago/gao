package com.wssys.test;

import java.util.Enumeration;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML.Tag;

public class SwingHtmlParser extends HTMLEditorKit.ParserCallback {
	 public void handleSimpleTag(Tag tag, MutableAttributeSet attrSet, int pos) {
         printTagWithAttributes(tag, attrSet);
     }
  
     private void printTagWithAttributes(Tag tag, MutableAttributeSet attrSet) {
         printTagWithAttributes(tag, attrSet);
     }
  
     public void handleStartTag(Tag tag, MutableAttributeSet attrSet, int pos) {
         System.out.println("Tag => " + tag.toString());
         Enumeration attributeNames = attrSet.getAttributeNames();
         while(attributeNames.hasMoreElements()){
             String attrName = attributeNames.nextElement().toString();
             System.out.println("Tag With Attributes => " +  attrName + " : " + attrSet.getAttribute(attrName));
         }
     }
  
     public void handleText(char[] data, int pos) {
         System.out.println("Tag Text => " + new String(data));
     }
      
     public void handleComment(char[] data, int pos) {
         System.out.println("Comment Text => " + new String(data));
     }
  
     public void handleEndOfLineString(String data) {
         // This is invoked after the stream has been parsed, but before flush. 
         // eol will be one of \n, \r or \r\n, which ever is 
         // encountered the most in parsing the stream.
         System.out.println("End of Line String => " + data);
     }
  
     public void handleEndTag(Tag tag, int pos) {
         System.out.println("End Of Tag for => " + tag.toString());
     }
  
     public void handleError(String err, int pos) {
         System.out.println("Error => " + err);
     }
}
