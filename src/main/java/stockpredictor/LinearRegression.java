package stockpredictor;

import java.util.List;

public class LinearRegression {
	private double slope;
	private double yIntercept;
	private double mse;
	private double rSqrd;
	
	public LinearRegression(double slope, double yIntercept, double mse, double rSqrd) {
		this.slope = slope;
		this.yIntercept = yIntercept;
		this.mse = mse;
		this.rSqrd = rSqrd;
	}
	
	public double getSlope() {
		return slope;
	}
	public double getYIntercept() {
		return yIntercept;
	}
	public double getMse() {
		return mse;
	}
	public double getRSquared() {
		return rSqrd;
	}
	
	//Returns LinearRegression object of given y values, and x values incrementing by 1.
	public static LinearRegression calculate(List<Double> yValues) {
		//X values are 0 (inclusive) to yValues.size() (exclusive)
		int[] xValues = new int[yValues.size()];
		for(int i = 0; i < yValues.size(); i++) 
			xValues[i] = i;
		
		double yAvg = average(yValues);
		double xAvg = average(xValues);
		
		//Calculate slope
		double numerator = 0;
		double denominator = 0;
		for (int i = 0; i < yValues.size(); i++) {
			double xDelta = xValues[i] - xAvg;
			double yDelta = yValues.get(i) - yAvg;
			double xDeltaSqrd = Math.pow(xDelta, 2);
			numerator +=xDelta*yDelta;
			denominator+=xDeltaSqrd;
		}
		double slope = numerator/denominator; //Negative since values are reversed
		
		//Calculate y intercept
		double yIntercept = yAvg - slope*xAvg;
		
		double mse = meanSquaredError(slope, yIntercept, yValues);
		double rSquared = rSquared(slope, yIntercept, yValues);
		
		return new LinearRegression(slope, yIntercept, mse, rSquared);
	}
	
	//Returns the average of values
	public static double average(List<Double> values) {
		double sum = 0;
		for(double d : values) 
			sum+=d;
		return sum/values.size();
	}
	
	//Returns the average of values
	public static double average(int[] values) {
		int sum = 0;
		for(int i : values) 
			sum+=i;
		return ((double)sum)/values.length;
	}
	
	//Returns the rsquared of given model (y=mx+b) and actual values
	private static double rSquared(double m, double b, List<Double> yValues) {
		double yDelta = 0, yHatDelta = 0;
		for(int x = 0; x < yValues.size(); x++) {
			//calculate average
			double avg = average(yValues);
			//calculate LinearRegression prediction
			double yHat = m*x + b;
			//get actual value
			double y = yValues.get(x);
			
			yDelta += Math.pow(y-avg, 2);
			yHatDelta += Math.pow(yHat-avg, 2);
		}
		return yHatDelta/yDelta;
	}
	
	//Returns the Mean squared error of given model (y=mx+b) and actual values
	private static double meanSquaredError(double m, double b, List<Double> values) {
		double sum = 0;
		for(int i = 0; i < values.size(); i++) {
			//get predicted value
			double yHat = m*i + b;	
			//get acutal value
			double y = values.get(i);
			double delta = y - yHat;
			sum += delta*delta;
		}
		return sum / values.size();
	}

	//Returns linear regression equation only
	public String toString() {
		return ("f(x) = " + String.format("%.2f", slope) + "x + " + String.format("%.2f", yIntercept));
	}
	
	//Returns linear regression, r squared and Mean squared error
	public String toStringFullInfo() {
		return toString() + "\nMean Squared Error: " + String.format("%.4f", mse) + "\nR-Squared: " + String.format("%.4f", rSqrd);
	}
	
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof LinearRegression)) return false;
		LinearRegression b = (LinearRegression) o;
		return (this.slope == b.slope 
				&& this.yIntercept == b.yIntercept 
				&& this.mse == b.mse 
				&& this.rSqrd == b.rSqrd);	
	}
	
	//Returns a rating of 1-10 on how much it is recommended to buy this stock based off given day
	public int calculateBuyScore(StockDay day) {
		int score = 0;
		if(slope > 0) score+=4;
		if(day.getAdjClose() < yIntercept - mse) {
			if(rSqrd > .7) score +=6;
			if(rSqrd > .55) score +=4;
			if(rSqrd > .4) score +=2;
		}
		if(day.getAdjClose() < yIntercept) {
			if(rSqrd > .7) score +=5;
			if(rSqrd > .55) score +=3;
			if(rSqrd > .4) score +=1;
		}
		return score;
	}

}
