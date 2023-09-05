package stockpredictor;

import java.time.LocalDate;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class YahooFinanceWebscraper extends Webscraper{
	private String ticker;
	
	public YahooFinanceWebscraper(String ticker) {
		super(getUrl(ticker));
		this.ticker = ticker;
	}
	
	//--- Accessors ---
	public StockHistory getHistory() {
		StockHistory stock = new StockHistory();
		Document doc = super.getDocument();		
		//select table from html
		Elements table = doc.select("table[data-test=historical-prices]");
		//go through each row
		for(Element row : table.select("tr")) {
			Elements data = row.select("td").select("span");
			//date, open, high, low, close, adjclose, volume
			if(data.text().equals("") || !isDate(data.get(0).text())) continue;
			LocalDate date = parseDateField(data.get(0).text());
			double open = Double.valueOf(data.get(1).text());
			double high = Double.valueOf(data.get(2).text());
			double low = Double.valueOf(data.get(3).text());
			double close = Double.valueOf(data.get(4).text());
			double adjClose = Double.valueOf(data.get(5).text());
			int volume = Integer.valueOf(data.get(6).text().replace(",", ""));
			stock.addDay(new StockDay(date, open, high, low, close, adjClose, volume));
		}
		return stock;
	}
	
	//--- Utilities ---
	public String getUrl() {
		return super.getUrl();
	}
	public static String getUrl(String ticker) {
		return "https://finance.yahoo.com/quote/" + ticker +"/history";
	}
	
	private boolean isDate(String string) {
		String[] parts = string.split(" ");
		return (parts[0].matches("Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec"));
	}
	
	private LocalDate parseDateField(String date) {
		//incoming string format: Sep 01, 2023
		String[] parts = date.split(" ");
		String monthString = parts[0];
		String day = parts[1].replace(",", "");
		String year = parts[2];
		int month = 1;
		switch (monthString) {
		case "Jan":
			month = 1;
			break;
		case "Feb":
			month = 2;
			break;
		case "Mar":
			month = 3;
			break;
		case "Apr":
			month = 4;
			break;
		case "May":
			month = 5;
			break;
		case "Jun":
			month = 6;
			break;
		case "Jul":
			month = 7;
			break;
		case "Aug":
			month = 8;
			break;
		case "Sep":
			month = 9;
			break;
		case "Oct":
			month = 10;
			break;
		case "Nov":
			month = 11;
			break;
		case "Dec":
			month = 12;
		}
		return LocalDate.of(Integer.valueOf(year), month, Integer.valueOf(day));
	}
	
}
