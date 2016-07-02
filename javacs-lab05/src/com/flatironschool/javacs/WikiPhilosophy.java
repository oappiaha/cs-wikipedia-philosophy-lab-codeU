package com.flatironschool.javacs;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.TextNode;
import java.util.List;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

import org.jsoup.nodes.Node;

public class WikiPhilosophy {
	private String web;
	private List<String> visited;
	final static WikiFetcher wf = new WikiFetcher();
	final static String end = "https://en.wikipedia.org/wiki/Philosophy";
	
	
	
	/**
	 * Tests a conjecture about Wikipedia and Philosophy.
	 * 
	 * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
	 * 
	 * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
	 * 
	 * @param args
	 * @throws IOException
	 */
	
	public static void main(String[] args) throws IOException {
		
        // some example code to get you started

//		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
//		Elements paragraphs = wf.fetchWikipedia(url);
//
//		Element firstPara = paragraphs.get(0);
//		
//		Iterable<Node> iter = new WikiNodeIterable(firstPara);
//		for (Node node: iter) {
//			if (node instanceof TextNode) {
//				System.out.print(node);
//			}
//        }

        // the following throws an exception so the test fails
        // until you update the code
//        String msg = "Complete this lab by adding your code and removing this statement.";
//        throw new UnsupportedOperationException(msg);
        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		WikiPhilosophy bop = new WikiPhilosophy(url); 
		bop.access(); 
	}
	public WikiPhilosophy(String website){
		visited = new ArrayList<String>();
		this.web = website;
		
	}
	private void access()throws IOException {
		do {
		 visited.add(web);
         System.out.println ("visiting: " + web);
         Elements paragraphs = wf.fetchWikipedia(web);
 		 Element firstPara = paragraphs.get(0);
 		 
		 Iterable<Node> iter = new WikiNodeIterable(firstPara);
         web = firstValidLink(iter);
		} while (visited.contains(web) && !end.equals(web) && web.length() > 0);
		
//		{
//		    visited.add(web);
//            System.out.println ("visiting" + web);
//            Elements paragraphs = wf.fetchWikipedia(web);
//    		Element firstPara = paragraphs.get(0);
//    		 
//   		 	Iterable<Node> iter = WikiNodeIterable(firstPara);
//			
//            web = firstValidLink(iter);
//            
//		}
		if (end.equals(web)){
			System.out.println ("visiting: " + end);
			System.out.println ("success");
		} else {
			System.out.println ("failure");
		}
	}
	private String firstValidLink (Iterable<Node> iter) {
		String nothing = "";
	    for (Node bop: iter) {
	         if (bop.hasAttr("href")) {
	              for (Node temp : bop.childNodes()) {
		              if (temp instanceof TextNode) {
		                   String title = ((TextNode) temp).text();
		                   if (isValid((TextNode) temp)) {
			                   return bop.absUrl("href");
		                    }
		               }
	               }
	          }
	     }
	     return nothing;
	} 
	private boolean isValid (TextNode bop) {
		String name= bop.text();
		return Character.isLowerCase(name.charAt(0));
	}
	
}
