package pa3.cs535.cs.iastate.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Connection {

	public static String get(String url){
		URL urlConnection;
        StringBuffer html = null;
        String inputLine;
		try {
			Thread.sleep(100);
			urlConnection = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlConnection.openConnection();
			conn.setReadTimeout(5000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Referer", "google.com");
		 
			System.out.println("Request URL ... " + url);
		 
			boolean redirect = false;
		 
			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
					|| status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
				redirect = true;
			}
		 
			System.out.println("Response Code ... " + status);
		 
			if (redirect) {
		 
				// get redirect url from "location" header field
				String newUrl = conn.getHeaderField("Location");
		 
				// get the cookie if need, for login
				String cookies = conn.getHeaderField("Set-Cookie");
		 
				// open the new connnection again
				conn = (HttpURLConnection) new URL(newUrl).openConnection();
				conn.setRequestProperty("Cookie", cookies);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				conn.addRequestProperty("User-Agent", "Mozilla");
				conn.addRequestProperty("Referer", "google.com");
		 
				System.out.println("Redirect to URL : " + newUrl);
		 
			}
		 
			BufferedReader in = new BufferedReader(
		                              new InputStreamReader(conn.getInputStream()));
			html = new StringBuffer();
		 
			while ((inputLine = in.readLine()) != null) {
				html.append(inputLine);
			}
			in.close();
		 
//			System.out.println("URL Content... \n" + html.toString());
			System.out.println("Done");
		} catch (IOException e) {	
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
		return html.toString();
	}
}
