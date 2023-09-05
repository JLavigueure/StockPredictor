package stockpredictor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class Webscraper {
	private final String URL;
	
	//--- Constructors ---
	public Webscraper(String URL) {
		this.URL = URL;
	}
	
	//--- Accessors ---
	public String getUrl() {
		return this.URL;
	}
		
	public Document getDocument(){
		try {
			return Jsoup.connect(URL).get();
		}catch(Exception e) {
			System.out.println("Unable to access url: " + URL);
			System.exit(1);
			return null;
		}
	}
	
	public abstract StockHistory getHistory();
	
}
