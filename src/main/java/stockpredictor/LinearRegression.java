package stockpredictor;

import java.util.List;

import Jama.Matrix;

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
		//init matrices
		Matrix xMatrix = new Matrix(yValues.size(), 2);
		Matrix yMatrix = new Matrix(yValues.size(), 1);
		for(int i = 0; i < xMatrix.getRowDimension(); i++) {
			//set up x matrix, all 1s in left column, increment by 1 descending in right column
			xMatrix.set(i, 1, i);
			xMatrix.set(i, 0, 1);
			//set up y matrix with values top to bottom
			yMatrix.set(i, 0, yValues.get(i));
		}
		//(X^T * X)^-1 * X^T * Y
		Matrix xTransposeX = xMatrix.transpose().times(xMatrix);
		Matrix xTransposeY = xMatrix.transpose().times(yMatrix);
		Matrix result = xTransposeX.inverse().times(xTransposeY);
		double slope = result.get(1, 0);
		double yIntercept = result.get(0, 0);
		double mse = MeanSquaredError(slope, yIntercept, yValues);
		double rSqrd = rSquared(slope, yIntercept, yValues);
		return new LinearRegression(slope, yIntercept, mse, rSqrd);
	}
	
	//Returns the average of values
	public static double average(List<Double> values) {
		double sum = 0;
		for(double d : values) 
			sum+=d;
		return sum/values.size();
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
	private static double MeanSquaredError(double m, double b, List<Double> values) {
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
