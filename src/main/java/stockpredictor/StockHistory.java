package stockpredictor;

import java.util.ArrayList;
import java.util.List;

public class StockHistory {
	private List<StockDay> history;
	
	//--- Constructors ---
	public StockHistory() {
		history = new ArrayList<>();
	}
	
	//--- Accessors ---
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
	
	//--- Modifiers ---
	public void setHistory(List<StockDay> history) {
		this.history = history;
	}
	
	public void addDay(StockDay day) {
		history.add(day);
	}
}
