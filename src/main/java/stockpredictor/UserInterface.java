package stockpredictor;

import java.util.Scanner;

public class UserInterface {
	private Scanner scan;
	
	public UserInterface(Scanner s) {
		scan = s;
	}
	
	public void start() {
		System.out.println("Enter exit to terminate program\n");
		System.out.print("Enter ticker: ");
		String ticker = scan.nextLine().toUpperCase();
		if(ticker.equals("EXIT")) {
			System.out.println("Ending program...");
			return;
		}
		YahooFinanceWebscraper yahoo = new YahooFinanceWebscraper(ticker);
		StockHistory stock = yahoo.getHistory();
		if(stock.isEmpty()) {
			System.out.println("Ticker " + ticker +" not found.");
			start();
			return;
		}
		LinearRegression model = LinearRegression.calculate(stock.getPast3Months().getAdjClosings());
		System.out.println("                           5 Day History         \n                    -------------------------------");
		System.out.println(stock.getPast5Days());
		
		System.out.println("                           Predictive Model\n                               (3 Month)\n                   -------------------------------");
		System.out.println(model.toStringFullInfo());
		System.out.println("Buy score: " + model.calculateBuyScore(stock.getHistory().get(0)) +"/10");
		System.out.println("\nEnter 1 to print full price history. Enter 2 to analyze another stock.");
		String in = scan.nextLine();
		while(!in.matches("[1-2]")) {
			System.out.println("Command not recognized.");
			System.out.println("\nEnter 1 to print full price history. Enter 2 to analyze another stock.");
			in = scan.nextLine();
		}
		if(in.equals("2")) {
			start();
			return;
		}
		System.out.println(stock);
		start();
	}
		
}
