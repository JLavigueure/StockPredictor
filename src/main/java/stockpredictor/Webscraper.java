package stockpredictor;

import org.jsoup.*;

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
	
	public abstract Stock getHistory(String ticker);
	
	
}
