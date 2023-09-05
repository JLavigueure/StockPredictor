package stockpredictor;

import java.time.LocalDate;

public class StockDay {
	private LocalDate date;
	private double open, high, low, close, adjClose;
	private int volume;
	
	//--- Constructors ---
	public StockDay(LocalDate date) {
		this(date, 0, 0, 0, 0, 0, 0);
	}
	
	public StockDay(LocalDate date, double open, double high, double low, double close, double adjClose, int volume) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adjClose = adjClose;
		this.volume = volume;
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

	public String toString() {
		String space = "  ";
		return date.toString() 
				+ space + String.format("%06f", open) 
				+ space + String.format("%06f", high)
				+ space + String.format("%06f", low) 
				+ space + String.format("%06f", close) 
				+ space + String.format("%06f", adjClose) 
				+ space + String.format("%,d", volume) ;
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
