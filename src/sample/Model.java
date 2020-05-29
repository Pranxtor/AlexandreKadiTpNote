package sample;

import java.util.ArrayList;

import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.shape.*;

public class Model {

	ArrayList<Lines> lines;
	ArrayList<Rectangles> rectangles;
	ArrayList<Ellipses> ellipses;
	
	Color colorPicked;
	
	RadioButton radioSelected;
	
	Shape shapePicked;
	int index;
	
	
	public Model() {
		lines=new ArrayList<Lines>();
		rectangles=new ArrayList<Rectangles>();
		ellipses=new ArrayList<Ellipses>();
		
		colorPicked=new Color(0,0,0,0);
		
		radioSelected=new RadioButton();
	}
	
	public void addLine(double xA,double yA,double xB,double yB){
		lines.add(new Lines(colorPicked, xA, yA,xB,yB));
	}
	
	public void addEllipse(double xA,double yA,double xB,double yB){
		ellipses.add(new Ellipses(colorPicked, xA, yA,xB,yB));
	}
	
	public void addRectangle(double xA,double yA,double xB,double yB){
		rectangles.add(new Rectangles(colorPicked, xA, yA,xB,yB));
	}
	
	public void changeLine(double xA,double yA,double xB,double yB){
		Lines l = new Lines(colorPicked, xA, yA,xB,yB);
		lines.set(lines.size()-1,l);
	}
	
	public void changeLine(){
		if(shapePicked instanceof Line) {
			Line line = (Line)shapePicked;
			Lines l=new Lines(colorPicked, line.getStartX(), line.getStartY(),line.getEndX(),line.getEndY());
			lines.set(index,l);
		}
	}
	
	public void changeEllipse(double xA,double yA,double xB,double yB){
		Ellipses l = new Ellipses(colorPicked, xA, yA,xB,yB);
		ellipses.set(ellipses.size()-1,l);
	}
	
	public void changeEllipse() {
		if(shapePicked instanceof Ellipse) {
			Ellipse elli = (Ellipse)shapePicked;
			Ellipses e=new Ellipses(colorPicked, elli.getCenterX(), elli.getCenterY(),elli.getRadiusX(),elli.getRadiusY());
			ellipses.set(index,e);
		}
	}
	
	public void changeRectangle(double xA,double yA,double xB,double yB){
		Rectangles l = new Rectangles(colorPicked, xA, yA,xB,yB);
		rectangles.set(rectangles.size()-1,l);
	}
	
	public void changeRectangle() {
		if(shapePicked instanceof Rectangle) {
			Rectangle elli = (Rectangle)shapePicked;
			Rectangles e=new Rectangles(colorPicked, elli.getX(), elli.getY(),elli.getWidth(),elli.getHeight());
			rectangles.set(index,e);
		}
	}
	
	public void setColor(Color c) {
		colorPicked=c;
		System.out.println(c);
	}
	
	public void changeColor(Color c) {
		
		if(shapePicked instanceof Line) {
			for(int i=0;i<lines.size();i++) {
				if(i==index){
					lines.get(i).setColor(c);
					break;
				}
			}
		}
		if(shapePicked instanceof Ellipse) {
			for(int i=0;i<ellipses.size();i++) {
				if(i==index){
					ellipses.get(i).setColor(c);
					break;
				}
			}
		}
		if(shapePicked instanceof Rectangle) {
			for(int i=0;i<rectangles.size();i++) {
				if(i==index){
					rectangles.get(i).setColor(c);
					break;
				}
			}
		}

	}
	
	public void cloner() {
		if(shapePicked instanceof Line) {
			for(int i=0;i<lines.size();i++) {
				if(i==index){
					lines.add(new Lines(colorPicked, lines.get(i).getLine().getStartX()+10,lines.get(i).getLine().getStartY()+10,lines.get(i).getLine().getEndX()+10,lines.get(i).getLine().getEndY()+10));
					break;
				}
			}
		}
		
		if(shapePicked instanceof Ellipse) {
			for(int i=0;i<lines.size();i++) {
				if(i==index){
					ellipses.add(new Ellipses(colorPicked, ellipses.get(i).getEllipse().getCenterX()+10,ellipses.get(i).getEllipse().getCenterY()+10,ellipses.get(i).getEllipse().getRadiusX(),ellipses.get(i).getEllipse().getRadiusY()));
					break;
				}
			}
		}
		
		if(shapePicked instanceof Rectangle) {
			for(int i=0;i<rectangles.size();i++) {
				if(i==index){
					rectangles.add(new Rectangles(colorPicked, rectangles.get(i).getRectangle().getX()+10,
							rectangles.get(i).getRectangle().getY()+10,
							rectangles.get(i).getRectangle().getWidth(),
							rectangles.get(i).getRectangle().getHeight()));
					break;
				}
			}
		}
		
	}
	
	
	public void delete() {
		if(shapePicked instanceof Line) {
			ArrayList<Lines> linesTemp= new ArrayList<Lines>();
			for(int i = 0 ; i<lines.size();i++) {
				
				if(i!=index){
					linesTemp.add(lines.get(i));
				}
				
			}
			lines=linesTemp;
			
			
		}
		if(shapePicked instanceof Ellipse) {
			ArrayList<Ellipses> ellipsesTemp= new ArrayList<Ellipses>();
			for(int i = 0 ; i<ellipses.size();i++) {
				
				if(i!=index){
					ellipsesTemp.add(ellipses.get(i));
				}
				
			}
			ellipses=ellipsesTemp;
		}
		if(shapePicked instanceof Rectangle) {
			ArrayList<Rectangles> ellipsesTemp= new ArrayList<Rectangles>();
			for(int i = 0 ; i<rectangles.size();i++) {
				
				if(i!=index){
					ellipsesTemp.add(rectangles.get(i));
				}
				
			}
			rectangles=ellipsesTemp;
		}
	}
	
	public void setRadio(RadioButton rB) {
		radioSelected=rB;
		System.out.println(radioSelected.getText());
	}
	
	public void setShape(Shape s) {
		if(s instanceof Line) {
			Line line = (Line)s;
			for(int i = 0 ; i<lines.size();i++) {
				if(lines.get(i).getLine().getStartX()==line.getStartX()&&
						lines.get(i).getLine().getStartY()==line.getStartY()&&
						lines.get(i).getLine().getEndX()==line.getEndX()&&
						lines.get(i).getLine().getEndY()==line.getEndY()){
					index = i;
					System.out.println("boucle"+index);
				}
				
			}
		}
		if(s instanceof Ellipse) {
			Ellipse line = (Ellipse)s;
			System.out.println(line);
			
			for(int i = 0 ; i<ellipses.size();i++) {
				System.out.println(ellipses.get(i).getEllipse());
				if(ellipses.get(i).getEllipse().getCenterX()==line.getCenterX()&&
						ellipses.get(i).getEllipse().getCenterY()==line.getCenterY()&&
						ellipses.get(i).getEllipse().getRadiusX()==line.getRadiusX()&&
						ellipses.get(i).getEllipse().getRadiusY()==line.getRadiusY()){
					index = i;
					System.out.println("boucle"+index);
				}
				
			}
		}
		if(s instanceof Rectangle) {
			Rectangle line = (Rectangle)s;
			for(int i = 0 ; i<rectangles.size();i++) {
				if(rectangles.get(i).getRectangle().getX()==line.getX()&&
						rectangles.get(i).getRectangle().getY()==line.getY()&&
						rectangles.get(i).getRectangle().getWidth()==line.getWidth()&&
						rectangles.get(i).getRectangle().getHeight()==line.getHeight()){
					index = i;
					System.out.println("boucle"+index);
				}
				
			}
		}
		System.out.println("fin"+index);
		shapePicked=s;		
	}
}
