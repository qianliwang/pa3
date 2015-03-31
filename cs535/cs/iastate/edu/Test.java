package pa3.cs535.cs.iastate.edu;

import java.util.ArrayList;

public class Test {

	public static void main(String args[]){
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("tennis");
		keywords.add("grand slam");
		WikiCrawler crawler = new WikiCrawler("/wiki/Tennis",keywords,2,"test.txt");
		crawler.crawl();
	}
}
