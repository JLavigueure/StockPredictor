package stockpredictor;

import java.util.ArrayList;
import java.util.List;

public class Stock {
	private List<StockDay> history;
	
	//--- Constructors ---
	public Stock() {
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
}
