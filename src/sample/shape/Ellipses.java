package sample.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Ellipses {
	Color color;
	
	Ellipse ellipse;
	
	public Ellipses(Color c, double xC,double yC,double xR,double yR) {
		ellipse = new Ellipse();
		color=c;
		ellipse.setCenterX(xC);
		ellipse.setCenterY(yC);
		ellipse.setRadiusX(xR);
		ellipse.setRadiusY(yR);
	}
	

	
	public Ellipse getEllipse() {
		return ellipse;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		this.color=c;
	}
}
