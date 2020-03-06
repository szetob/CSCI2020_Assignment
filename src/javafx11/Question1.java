package javafx11;

/*
 * Brendan Szeto, 100702901
 * CSCI 2020U, Moosavitayebi
 * March 5, 2020
 * Assignment 1, Question 1
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Random;

public class Question1 extends Application {

    @Override
    public void start(Stage stage) {
    	//Create pane
        Pane pane = new HBox(10);
        pane.setPadding(new Insets(20, 20, 20, 20));
       
        //Determine cards to display
        //ImageView 1
        Image card1 = new Image(getRandCard());				//Create Image with url from getRandCard()
        ImageView imageView1 = new ImageView(card1);		//Create the ImageView from previous Image
        pane.getChildren().add(imageView1);					//Add to the pane
       
        //ImageView 2
        Image card2 = new Image(getRandCard());
        ImageView imageView2 = new ImageView(card2);
        pane.getChildren().add(imageView2);
       
        //ImageView 3
        Image card3 = new Image(getRandCard());
        ImageView imageView3 = new ImageView(card3);
        pane.getChildren().add(imageView3);
       
       
        //Create scene and display
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Question 1");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    //Function to find a random from 1-54 and get that corresponding card
    public String getRandCard() {
    	Random random = new Random();
    	int num = 1 + random.nextInt(54);
    	return ("file:Cards/" + num + ".png");
    }

}