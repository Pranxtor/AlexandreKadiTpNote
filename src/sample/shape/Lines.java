package sample.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Lines{

	Color color;
	
	Line line;
	
	public Lines(Color c, double xA,double yA,double xB,double yB) {
		line = new Line();
		color=c;
		line.setStartX(xA);
		line.setStartY(yA);
		line.setEndX(xB);
		line.setEndY(yB);
	}
	

	
	public Line getLine() {
		return line;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		this.color=c;
	}
	
}
