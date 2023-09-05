package stockpredictor;

import java.util.List;

import Jama.Matrix;

public class LinearRegression {
	private double slope;
	private double yIntercept;
	
	public LinearRegression(double slope, double yIntercept) {
		this.slope = slope;
		this.yIntercept = yIntercept;
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
		return new LinearRegression(slope, yIntercept);
	}
	
	public String toString() {
		return ("y = " + slope + "x + " + yIntercept);
	}
}
