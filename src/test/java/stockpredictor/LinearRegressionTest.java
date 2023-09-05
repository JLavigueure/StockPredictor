package stockpredictor;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class LinearRegressionTest {
	
	@Test
	public void testLinearRegressionYIntercept1() {
		ArrayList<Double> values = buildDblList1();
		LinearRegression a = LinearRegression.calculate(values);
		assertEquals(14.2, a.getYIntercept(), 0.01);
	}
	
	@Test
	public void testLinearRegressionYIntercept2() {
		ArrayList<Double> values = buildDblList2();
		LinearRegression a = LinearRegression.calculate(values);
		assertEquals(310.04, a.getYIntercept(), 0.01);
	}
	
	@Test
	public void testLinearRegressionSlope1() {
		ArrayList<Double> values = buildDblList1();
		LinearRegression a = LinearRegression.calculate(values);
		assertEquals(18.3, a.getSlope(), 0.01);
	}
	
	@Test
	public void testLinearRegressionSlope2() {
		ArrayList<Double> values = buildDblList2();
		LinearRegression a = LinearRegression.calculate(values);
		assertEquals(14.76, a.getSlope(), 0.01);
	}
	
	@Test
	public void testRSquared1() {
		ArrayList<Double> values = buildDblList1();
		LinearRegression a = LinearRegression.calculate(values);
		assertEquals(0.9682,a.getRSquared(),.0001);
	}
	
	@Test
	public void testRSquared2() {
		ArrayList<Double> values = buildDblList2();
		LinearRegression a = LinearRegression.calculate(values);
		assertEquals(0.626,a.getRSquared(),.001);
	}
	
	@Test
	public void testMse1() {
		ArrayList<Double> values = buildDblList1();
		LinearRegression a = LinearRegression.calculate(values);
		System.out.println(a.getMse());
		assertEquals(21.98, a.getMse(), .01);
	}
	
	@Test
	public void testMse2() {
		ArrayList<Double> values = buildDblList2();
		LinearRegression a = LinearRegression.calculate(values);
		System.out.println(a.getMse());
		assertEquals(260.34, a.getMse(), .01);
	}
	
	private ArrayList<Double> buildDblList1(){
		ArrayList<Double> values = new ArrayList<>();
		values.add(10.0);
		values.add(33.0);
		values.add(56.0);
		values.add(74.0);
		values.add(81.0);
		return values;
	}
	
	private ArrayList<Double> buildDblList2(){
		ArrayList<Double> values = new ArrayList<>();
		values.add(326.5);
		values.add(302.2);
		values.add(330.4);
		values.add(374.6);
		values.add(364.1);
		return values;
	}
		
	
}
