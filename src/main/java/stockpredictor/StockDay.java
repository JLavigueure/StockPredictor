package stockpredictor;

import java.time.LocalDate;

public class StockDay {
	private LocalDate date;
	private double open, high, low, close, adjClose;
	private int volume;
	
	//--- Constructors ---
	public StockDay(LocalDate date) {
		this.date = date;
		open = high = low = close = adjClose = 0;
		volume = 0;
	}
	
	//--- Accessors ---
	public LocalDate getDate() {
		return date;
	}
	public double getOpen() {
		return open;
	}
	public double getHigh() {
		return high;
	}
	public double getLow() {
		return low;
	}
	public double getClose() {
		return close;
	}
	public double getAdjClose() {
		return adjClose;
	}
	public int getVolume() {
		return volume;
	}
	
	//--- Modifiers ---
	public void setOpen(double open) {
		this.open = open;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
}
