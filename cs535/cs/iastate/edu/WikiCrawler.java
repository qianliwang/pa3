package pa3.cs535.cs.iastate.edu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class WikiCrawler {
	
	private String seedUrl;
	private ArrayList<String> keywords;
	private int maxSites;
	private String outputFileName;
	
	private Queue<String> linkQueue;
	private ArrayList<String> edges;
	
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
		edges = new ArrayList<String>();
	}
	
	public void crawl(){
		String currentPage;
		String pageContent;
		
		while(!this.linkQueue.isEmpty()&&this.edges.size()<=this.maxSites){
			currentPage = linkQueue.poll();
			if(isContainKeywords(currentPage)){
				extractLinks(currentPage,this.linkQueue,this.edges);
			}
			
			
		}
	}
	
	private boolean isContainKeywords(String url){
		
	}
	
	private void extractLinks(String url,Queue queue,ArrayList<String> edges){
		
	}
}
