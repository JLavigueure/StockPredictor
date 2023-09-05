package stockpredictor;

public class YahooFinanceWebscraper extends Webscraper{

	public YahooFinanceWebscraper(String ticker) {
		super(generateUrl(ticker));
	}
	
	//--- Accessors ---
	public Stock getHistory(String ticker) {
		//TODO - complete method
		return new Stock();
	}
	
	//--- Utilities ---
	public static String generateUrl(String ticker) {
		return "https://finance.yahoo.com/quote/" + ticker +"/history";
	}
	
}
