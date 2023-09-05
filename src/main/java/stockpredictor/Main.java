package stockpredictor;

public class Main {

	public static void main(String[] args) {
		YahooFinanceWebscraper yahoo = new YahooFinanceWebscraper("MSFT");
		StockHistory stock = yahoo.getHistory();
		System.out.println(stock);
		LinearRegression model = LinearRegression.calculate(stock.getPast3Months().getClosings());
		System.out.println(model.toStringFullInfo());
	}

}
