package javafx11;

import java.util.Random;

/*
 * Brendan Szeto, 100702901
 * CSCI 2020U, Moosavitayebi
 * March 5, 2020
 * Assignment 1, Question 3
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Question3 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	//Create pane
        Pane pane = new Pane();
        pane.setPadding(new Insets(20, 20, 20, 20));
        
        //Create a Circle
        Circle circle = new Circle(200, 200, 100);
        pane.getChildren().add(circle);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        Circle[] points = new Circle[3];				//Create 3 Circles to hold points on circle
        Line[] lines = new Line[3];						//Create 3 Lines to hold lines on circle
        Text[] angles = new Text[3];					//Create 3 Texts to hold angles on circle
        
        //Loop for each point
        for (int i = 0; i < 3; i++) {
        	angles[i] = new Text();
        	points[i] = new Circle(0, 0, 5);
        	getRandomPoint(points[i], circle);
        	
        	
        	int i2 = i;									//Extra counter to be used in drag action							
        	//Set action for when mouse drags a point
        	points[i].setOnMouseDragged(e -> {
        		//Update points' x and y position
        		double radVal = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());
        		double xPos = circle.getCenterX() + circle.getRadius() * Math.cos(radVal);
        		double yPos = circle.getCenterY() + circle.getRadius() * Math.sin(radVal);
        		points[i2].setCenterX(xPos);
        		points[i2].setCenterY(yPos);
        		
        		//Update the Lines' location
        		updateLineLocation(lines, points, angles);
        	});
        }
        
        for (int j = 0;j < 3;j++) {
        	int j2 = j + 1;
    		if (j + 1 >= 3) { 
    			j2 = 0;
    		}
    		lines[j] = new Line(points[j].getCenterX(), points[j].getCenterY(),
    							points[j2].getCenterX(), points[j2].getCenterY());
        }
        
        //Add lines, angles, and points to pane then create and display Scene
        updateLineLocation(lines, points, angles);
        pane.getChildren().addAll(lines);
        pane.getChildren().addAll(angles);
        pane.getChildren().addAll(points);
        stage.setScene(new Scene(pane, 500, 500));
        stage.setTitle("Question 3");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
    
    //Function to find initial random points on main Circle
    public void getRandomPoint(Circle point, Circle circle) {
    	//Get random degree
    	Random random = new Random();
    	int angleDeg = 1 + random.nextInt(360);
    	
    	//Turn degree into radians and use to find x and y position for point
    	double xPos = circle.getCenterX() + circle.getRadius() * Math.cos(Math.toRadians(angleDeg));
    	double yPos = circle.getCenterY() + circle.getRadius() * Math.sin(Math.toRadians(angleDeg));
    	point.setCenterX(xPos);
    	point.setCenterY(yPos);
    }
    
    //Function to update Lines' x and y position whenever points are changed
    public void updateLineLocation(Line[] line, Circle[] circle, Text[] angle) {
    	for (int i = 0;i < 3;i++) {
    		int j = i + 1;
    		if (i + 1 >= 3) { 
    			j = 0;
    		}
    		
    		line[i].setStartX(circle[i].getCenterX());
    		line[i].setStartY(circle[i].getCenterY());
    		line[i].setEndX(circle[j].getCenterX());
    		line[i].setEndY(circle[j].getCenterY());
    		angle[i].setX(circle[i].getCenterX() + 5);
    		angle[i].setY(circle[i].getCenterY() - 5);
    	}
    	
    	//Calculate the distance between the new points
    	double a = getDistance(line[0]);
    	double b = getDistance(line[1]);
    	double c = getDistance(line[2]);
    	
    	//Using given formulas, compute angles
    	double A = Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
    	angle[2].setText(String.format("%.0f", A));
    	double B = Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
    	angle[0].setText(String.format("%.0f", B));
    	double C = Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b)));
    	angle[1].setText(String.format("%.0f", C));
    }

    //Function to get distance between two points from x1, y1 and x2, y2
	private double getDistance(Line line) {
		double x1 = line.getStartX();
		double y1 = line.getStartY();
		double x2 = line.getEndX();
		double y2 = line.getEndY();
		
		return (Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
	}
}