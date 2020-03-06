package javafx11;

/*
 * Brendan Szeto, 100702901
 * CSCI 2020U, Moosavitayebi
 * March 5, 2020
 * Assignment 1, Question 2
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.lang.Math;

public class Question2 extends Application {
	@Override
    public void start(Stage stage) {
    	//Create pane
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));
        
        //Create TextFields
        //Investment Amount
        final TextField investAmount = new TextField();
        GridPane.setConstraints(investAmount, 1, 0);
        investAmount.setAlignment(Pos.CENTER_RIGHT);
        
        //Years
        final TextField years = new TextField();
        GridPane.setConstraints(years, 1, 1);
        years.setAlignment(Pos.CENTER_RIGHT);
        
        //Interest Rate
        final TextField interestRate = new TextField();
        GridPane.setConstraints(interestRate, 1, 2);
        interestRate.setAlignment(Pos.CENTER_RIGHT);
        
        //Future Value
        final TextField futureVal = new TextField();
        futureVal.setEditable(false);											//Cannot edit the future value, must be calculated
        GridPane.setConstraints(futureVal,  1,  3);
        futureVal.setAlignment(Pos.CENTER_RIGHT);
        
        //Add Labels for TextFields
        pane.add(new Label("Investment Amount: "), 0, 0);
        pane.getChildren().add(investAmount);
        pane.add(new Label("Years: "), 0, 1);
        pane.getChildren().add(years);
        pane.add(new Label("Annual Interest Rate: "), 0 , 2);
        pane.getChildren().add(interestRate);
        pane.add(new Label("Future Value: "), 0, 3);
        pane.getChildren().add(futureVal);
        
        //Create button for calculating future value
        Button btnCal = new Button("Calculate");
        pane.add(btnCal, 1,4);
        GridPane.setHalignment(btnCal, HPos.RIGHT);
        
        //Set action for Calculate Button
        btnCal.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent actionEvent) {
        		//Get the double/int value of the TextFields
        		String investAmountStr = investAmount.getText();
        		double investAmountDoub = Double.parseDouble(investAmountStr);
        		String yearsStr = years.getText();
        		int yearsInt = Integer.parseInt(yearsStr);
        		String interestRateStr = interestRate.getText();
        		double interestRateDoub = Double.parseDouble(interestRateStr);
        		//Since annual interest rate is given, must convert to monthly interest rate for formula
        		double monthlyInterestRate = interestRateDoub / 1200;
        		
        		//Calculate future value
        		double futureValDoub = (investAmountDoub * Math.pow(1 + monthlyInterestRate, yearsInt * 12));
        		
        		//Display the future value in TextField
        		futureVal.setText(String.format("%.2f", futureValDoub));
        	}
        });
       
        //Create scene and display
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Question 2");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
