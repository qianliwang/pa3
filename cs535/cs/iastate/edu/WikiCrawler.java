package pa3.cs535.cs.iastate.edu;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikiCrawler {
	
	private String seedUrl;
	private ArrayList<String> keywords;
	private int maxSites;
	private String outputFileName;
	
	private Queue<String> linkQueue;
	private ArrayList<String> edges;
	
	private TreeSet<String> visitedLinks;
	private TreeSet<String> noKeywordLinks;
	
	private String urlPrefix;
	
	public WikiCrawler(){
		
	}
	
	public WikiCrawler(String url,ArrayList<String> keywords,int max, String fileName){
		this.seedUrl = url;
		this.maxSites = max;
		this.outputFileName = fileName;
		this.keywords = new ArrayList<String>();
		for(String keyword:keywords){
			this.keywords.add(keyword);
		}
		
		linkQueue = new LinkedList();
		linkQueue.add(this.seedUrl);
		
		visitedLinks = new TreeSet<String>();
		visitedLinks.add(this.seedUrl);
		
		noKeywordLinks = new TreeSet<String>();
		edges = new ArrayList<String>();
		urlPrefix = "http://en.wikipedia.org";
	}
	
	public void crawl(){
		
		String currentPageLink;
		
		while(!this.linkQueue.isEmpty()&&this.visitedLinks.size()<=this.maxSites){
			currentPageLink = linkQueue.poll();
			System.out.println(currentPageLink);
			if(isContainKeywords(currentPageLink)){
				extractLinks(currentPageLink);
			}
		}
		
		writeToFile(this.edges);
	}
	
	private boolean isContainKeywords(String url){
		
		boolean isContainKeywords = true;
		
		if(!this.noKeywordLinks.contains(url)){
			String rawTextUrl = "http://en.wikipedia.org/w/index.php?title=$$$$&action=raw";
			String tokens[] = url.split("/");
			String title = tokens[tokens.length-1];
			String newString = rawTextUrl.replace("$$$$", title);
			System.out.println(newString);
			
			String pageContent = Connection.get(newString);
			
			
			for(String keyword:this.keywords){
				if(!pageContent.contains(keyword)){
					isContainKeywords = false;
					this.noKeywordLinks.add(url);
					break;
				}
			}
		}
		
		
		return isContainKeywords;
	}
	
	private void extractLinks(String url){
		String pageContent = Connection.get(this.urlPrefix+url);
		ArrayList<String> paraList = new ArrayList<String>();
		extractTags("<p>(.+?)</p>",pageContent,paraList);
		ArrayList<String> linkList = new ArrayList<String>();
		String temp = "";
		for(String para:paraList){
			extractTags("href=\"(.+?)\"",para,linkList);
			for(String link:linkList){
				
//		Be careful when testing!!!!!
				
				if(!link.contains("#")&&!link.contains(":")&&!this.visitedLinks.contains(link)&&isContainKeywords(link)){
					
					this.linkQueue.add(link);
					this.visitedLinks.add(link);
					temp = url + "\t" + link;
					this.edges.add(temp);
				}
			}
		}
		paraList.clear();
		linkList.clear();
/*		
		Pattern p = Pattern.compile("<p>(.+?)</p>");
		Matcher m = p.matcher(pageContent);
		String cu
		while(m.find()){
			
		}
*/
	}
	
	private void extractTags(String pattern,String target,ArrayList<String> result){
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(target);
		
		while(m.find()){
			result.add(m.group(1));
		}
	}
	
	private void writeToFile(ArrayList<String> lines){
		PrintWriter writer;
		try {
			writer = new PrintWriter(this.outputFileName, "UTF-8");
			for (String line : lines) {
				writer.println(line);
			}
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
