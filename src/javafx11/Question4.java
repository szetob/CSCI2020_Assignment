package javafx11;

/*
 * Brendan Szeto, 100702901
 * CSCI 2020U, Moosavitayebi
 * March 5, 2020
 * Assignment 1, Question 4
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Question4 extends Application {
		static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
		@Override
 		public void start(Stage stage) {
    	//Create pane
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        
        //Create BarChart and its labels
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Letters:");
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Times Appears:");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Number of Times A Letter Appears in a String");
        //Create Series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Number of Times A Letter Appears");
        
        //Create TextField
        final TextField text = new TextField();
        pane.getChildren().add(text);
        GridPane.setConstraints(text, 0, 5);
        
        //Create button for finding number times letters appear in TextField's text
        Button btnView = new Button("View");
        pane.getChildren().add(btnView);
        GridPane.setConstraints(btnView, 1, 5);
        
        //Set action for Calculate Button
        btnView.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent actionEvent) {
        		//Find the number of times each letter appears in TextField's text
        		int[] alphabetCount = new int[26];
        		alphabetCount = getNumberOfLetters(text.getText());
        		
        		//Add the data from the alphabetCount array to a Series
        		for (int i = 0;i < 26;i++) {
        			series.getData().add(new XYChart.Data<>(String.valueOf(alphabet[i]), alphabetCount[i]));
        		}
        		//Add the series to the BarChart
        		barChart.getData().addAll(series);
        		//Add the BarChart to the GridPane
        		pane.getChildren().add(barChart);
        		GridPane.setConstraints(barChart, 0, 0);
        	}
        });
        
        //Create scene and display
        Scene scene = new Scene(pane, 750, 750);
        stage.setScene(scene);
        stage.setTitle("Question 4");
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    //Function to find number of each letter of the string entered in the TextField
    static int[] getNumberOfLetters(String str) {
    	str = str.toLowerCase();								//Make the whole string lower case
    	
        //Create an array for the number of each letter
    	int[] alphabetCount = new int[26];
    	
    	//Iterate through the entire alphabet and the entire string. If the current letter matches a position in the string,
    	//increase the count stored in the letter-counting array
    	for (int i = 0;i < 26;i++) {
    		for (int j = 0;j < str.length();j++) {
    			if (alphabet[i] == str.charAt(j)) {
    				alphabetCount[i]++;
    			}
    		}
    	}
   
    	return alphabetCount;
    } 
}
