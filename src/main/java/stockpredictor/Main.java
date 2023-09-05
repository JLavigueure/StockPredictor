package stockpredictor;

public class Main {

	public static void main(String[] args) {
		YahooFinanceWebscraper tslaScraper = new YahooFinanceWebscraper("tsla");
		StockHistory tsla = tslaScraper.getHistory();
		for(StockDay day: tsla.getHistory()) {
			System.out.println(day.getClose());
		}
	}

}
