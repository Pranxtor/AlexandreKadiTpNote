package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.shape.Ellipses;
import sample.shape.Lines;
import sample.shape.Rectangles;

public class Controller {

	@FXML
	RadioButton radioSelectMove;
	
	@FXML
	RadioButton radioElipse;
	
	@FXML
	RadioButton radioRectangle;
	
	@FXML
	RadioButton radioLine;

	@FXML
	ColorPicker colorPicker;
	
	@FXML
	Button btnDelete;
	
	@FXML
	Button btnClone;
	
	@FXML
	Pane canvas;
	
	Model model ;
	
	double debutX,debutY;
	
	public Controller() {
		super();
		this.model = new Model();
	}
	
	@FXML
    public void initialize(){
		colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                model.setColor(colorPicker.getValue());
               if(model.shapePicked!=null) {
            	   model.changeColor(colorPicker.getValue());
            	   actualiser();
               }
                	
                	
            }
        });
		
		model.setColor(colorPicker.getValue());
		
		radioSelectMove.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
		        if (isNowSelected) { 
		            model.setRadio(radioSelectMove);
		            btnDelete.setDisable(false);
		            btnClone.setDisable(false);
		        } else {
		        	btnDelete.setDisable(true);
		        	btnClone.setDisable(true);
		        }
			}
		});
		
		radioElipse.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
		        if (isNowSelected) { 
		        	model.setRadio(radioElipse);
		        } else {
		            // ...
		        }
			}
		});
		
		radioLine.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
		        if (isNowSelected) { 
		        	model.setRadio(radioLine);
		        } else {
		            // ...
		        }
			}
		});
		
		radioRectangle.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
		        if (isNowSelected) { 
		        	model.setRadio(radioRectangle);
		        } else {
		            // ...
		        }
			}
		});
		
		canvas.setOnMousePressed(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
            	switch(model.radioSelected.getText()) {
            	case "Line":
            		debutX=event.getX();
	            	debutY=event.getY();
	            	model.addLine(debutX,debutY,event.getX(), event.getY());
	            	break;
            	case "Ellipse":
            		debutX=event.getX();
	            	debutY=event.getY();
	            	model.addEllipse(debutX,debutY,event.getX(), event.getY());
	            	break;
            	case "Rectangle":
            		debutX=event.getX();
	            	debutY=event.getY();
	            	model.addRectangle(debutX,debutY,event.getX(), event.getY());
	            	break;
    			
    			}
            	
            	
            	
            }
        });
		
		canvas.setOnMouseDragged(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
            	switch(model.radioSelected.getText()) {
            	case "Line":
                	model.changeLine(debutX,debutY,event.getX(), event.getY());
                	actualiser();
	            	break;
            	case "Ellipse":
	            	model.changeEllipse(debutX,debutY,event.getX(), event.getY());
	            	actualiser();
	            	break;
            	case "Rectangle":
	            	model.changeRectangle(debutX,debutY,event.getX()-debutX, event.getY()-debutY);
	            	actualiser();
	            	break;
    			}
            	
            	
            }
        });
		
		btnDelete.setOnMouseReleased(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
            	if(model.shapePicked!=null) {
             	   model.delete();
             	   actualiser();
                }
            }
        });
		
		btnClone.setOnMouseReleased(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
            	if(model.shapePicked!=null) {
             	   model.cloner();
             	   actualiser();
                }
            }
        });
	}
	
	public void actualiserHandler() {
		for(Node n : canvas.getChildren()) {
			n.setOnMouseDragged(new EventHandler <MouseEvent>()
	        {
	            public void handle(MouseEvent event)
	            {
	            	
	            	if(n instanceof Line) {
	            		Line l = (Line)n;
	            		debutX=event.getX()-l.getStartX();
		            	debutY=event.getY()-l.getStartY();;
		            	
	            		l.setStartX(event.getX());
	            		l.setStartY(event.getY());
	            		l.setEndX(l.getEndX()+ debutX);
	            		l.setEndY(l.getEndY()+ debutY);
	            		model.setShape(l);
	            		model.changeLine();
	            	}
	            	
	            	if(n instanceof Ellipse) {
	            		Ellipse l = (Ellipse)n;
	            		l.setCenterX(event.getX());
	            		l.setCenterY(event.getY());
	            		model.setShape(l);
	            		model.changeEllipse();
	            	}
	            	if(n instanceof Rectangle) {
	            		Rectangle l = (Rectangle)n;
	            		debutX=event.getX()-l.getX();
		            	debutY=event.getY()-l.getY();        	
	            		l.setX(event.getX());
	            		l.setY(event.getY());
	            		model.setShape(l);
	            		model.changeRectangle();
	            	}
	            	
	            }
	        });
			n.setOnMouseEntered(new EventHandler <MouseEvent>()
	        {
	            public void handle(MouseEvent event)
	            {
	            	if(n instanceof Line) {
	            		Line l = (Line)n;
	            		l.setStrokeWidth(l.getStrokeWidth()+5.0);
	            	}
	            	if(n instanceof Ellipse) {
	            		Ellipse l = (Ellipse)n;
	            		l.setStroke(Color.CRIMSON);
	            	}
	            	if(n instanceof Rectangle) {
	            		Rectangle l = (Rectangle)n;
	            		l.setStroke(Color.CRIMSON);
	            	}
	            	
	            	
	            }
	        });
			n.setOnMouseClicked(new EventHandler <MouseEvent>()
	        {
	            public void handle(MouseEvent event)
	            {
	            		if(n!=model.shapePicked) {
	            			if(model.shapePicked!=null)
	            				model.shapePicked.setStrokeWidth(model.shapePicked.getStrokeWidth()-5.0);
	            			Shape s =(Shape)n;
	            			
	            			if(n instanceof Line) {
	            				s.setStrokeWidth(s.getStrokeWidth()-5.0);
		            		    model.setShape(s);
		            		    s.setStrokeWidth(s.getStrokeWidth()+5.0);
	    	            		Line l = (Line)n;
	    	            		l.setStrokeWidth(l.getStrokeWidth()+5.0);
	    	            	}
	            			if(n instanceof Ellipse) {
	            				s.setStroke(Color.CRIMSON);
		            		    model.setShape(s);
	    	            		Ellipse l = (Ellipse)n;
	    	            		l.setStroke(Color.CRIMSON);
	    	            	}
	            			if(n instanceof Rectangle) {
	            				s.setStroke(Color.CRIMSON);
		            		    model.setShape(s);
	            				Rectangle l = (Rectangle)n;
	    	            		l.setStroke(Color.CRIMSON);
	    	            	}
	            		}
	            }
	        });
			n.setOnMouseExited(new EventHandler <MouseEvent>()
	        {
	            public void handle(MouseEvent event)
	            {
	            	if(n instanceof Line) {
	            		Line l = (Line)n;
	            		l.setStrokeWidth(l.getStrokeWidth()-5.0);
	            	}
	            	if(n instanceof Ellipse) {
	            		Ellipse l = (Ellipse)n;
	            		l.setStroke(model.colorPicked);
	            	}
	            	if(n instanceof Rectangle) {
	            		Rectangle l = (Rectangle)n;
	            		l.setStroke(model.colorPicked);
	            	}
	            	
	            }
	        });
		}
	}
	
	public void actualiser() {
		canvas.getChildren().clear();
		for(Lines l:model.lines) {
			Line line=new Line(l.getLine().getStartX(),
					l.getLine().getStartY(), l.getLine().getEndX(), l.getLine().getEndY());
			line.setStroke(l.getColor());
			canvas.getChildren().add(line);
		}
		for(Ellipses l:model.ellipses) {
			Ellipse ellipse=new Ellipse(l.getEllipse().getCenterX(),
					l.getEllipse().getCenterY(), l.getEllipse().getRadiusX(), l.getEllipse().getRadiusY());
			ellipse.setFill(l.getColor());
			canvas.getChildren().add(ellipse);
		}
		for(Rectangles l:model.rectangles) {
			Rectangle rectangle=new Rectangle(l.getRectangle().getX(),
					l.getRectangle().getY(), l.getRectangle().getWidth(), l.getRectangle().getHeight());
			rectangle.setFill(l.getColor());
			canvas.getChildren().add(rectangle);
		}
		actualiserHandler();
	}
}
