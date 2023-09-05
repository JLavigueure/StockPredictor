package stockpredictor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StockHistory {
	private List<StockDay> history;
	private String ticker;
	
	//--- Constructors ---
	public StockHistory(String ticker) {
		this(ticker, new ArrayList<StockDay>());
	}
	
	public StockHistory(String ticker, List<StockDay> days) {
		history = days;
		this.ticker = ticker;
	}
	
	//--- Accessors ---
	public String getTicker() {
		return ticker;
	}
	
	public List<StockDay> getHistory() {
		return history;
	}
	
	public List<Double> getOpens(){
		List<Double> out = new ArrayList<>();
		for(StockDay day : history) {
			out.add(day.getOpen());
		}
		return out;
	}
	
	public List<Double> getHighs(){
		List<Double> out = new ArrayList<>();
		for(StockDay day : history) {
			out.add(day.getHigh());
		}
		return out;
	}
	
	public List<Double> getLows(){
		List<Double> out = new ArrayList<>();
		for(StockDay day : history) {
			out.add(day.getLow());
		}
		return out;
	}
	
	public List<Double> getClosings(){
		List<Double> out = new ArrayList<>();
		for(StockDay day : history) {
			out.add(day.getClose());
		}
		return out;
	}
	
	public List<Double> getAdjClosings(){
		List<Double> out = new ArrayList<>();
		for(StockDay day : history) {
			out.add(day.getAdjClose());
		}
		return out;
	}
	
	public List<Integer> getVolumes(){
		List<Integer> out = new ArrayList<>();
		for(StockDay day : history) {
			out.add(day.getVolume());
		}
		return out;
	}
	
	//Returns a new StockHistory obj containing only the past x days
	public StockHistory getPastXDays(int x) {
		return new StockHistory(ticker, history.subList(0, x));
	}
	
	public StockHistory getPast3Days() {
		return getPastXDays(3);
	}
	
	public StockHistory getPast5Days() {
		return getPastXDays(5);
	}
	
	//Returns a new StockHistory obj containing data 
	public StockHistory getPastDaysUntil(LocalDate date) {
		if(history.isEmpty()) return null;
		ArrayList<StockDay> out = new ArrayList<>();
		int i = 0;
		StockDay day = history.get(i++);
		while(date.isBefore(day.getDate())) {
			out.add(day);
			day = history.get(i++);
		}
		return new StockHistory(ticker, out);
	}
	
	public StockHistory getPastWeek() {
		return getPastDaysUntil(LocalDate.now().minusWeeks(1));
	}
	
	public StockHistory getPastMonth() {
		return getPastDaysUntil(LocalDate.now().minusMonths(1));
	}
	
	public StockHistory getPast3Months() {
		return getPastDaysUntil(LocalDate.now().minusMonths(3));
	}

	public boolean isEmpty() {
		return history.isEmpty();
	}
	
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(toStringHeader());
		for(StockDay day : history) 
			out.append(day + "\n");
		return out.toString();
	}
	
	public String toStringHeader() {
		return "   Date        Open        High        Low        Close     Adj.Close    Volume\n";
	}
	
	//--- Modifiers ---
	public void setHistory(List<StockDay> history) {
		this.history = history;
	}
	
	public void addDay(StockDay day) {
		history.add(day);
	}
}
