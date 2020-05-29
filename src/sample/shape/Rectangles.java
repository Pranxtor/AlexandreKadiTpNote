package sample.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rectangles {
	Color color;
	
	Rectangle rect;
	
	public Rectangles(Color c, double xC,double yC,double xR,double yR) {
		rect = new Rectangle();
		color=c;
		rect.setX(xC);
		rect.setY(yC);
		rect.setWidth(xR);
		rect.setHeight(yR);
	}
	

	
	public Rectangle getRectangle() {
		return rect;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		this.color=c;
	}
	
}
