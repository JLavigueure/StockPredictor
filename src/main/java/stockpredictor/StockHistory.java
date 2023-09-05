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
	
	//--- Modifiers ---
	public void setHistory(List<StockDay> history) {
		this.history = history;
	}
	
	public void addDay(StockDay day) {
		history.add(day);
	}
}
