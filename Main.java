package assignment5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application { 
	static GridPane grid = new GridPane();
	@Override 
	public void start(Stage primaryStage) {
		
		try {
			grid.setGridLinesVisible(true);
			Scene scene = new Scene(grid, 500, 500);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Paints the icons.
			
			Stage controls = new Stage();
			controls.setX(primaryStage.getX()+primaryStage.getWidth());
			controls.setY(primaryStage.getY());
			controls.setTitle("Controls");
	//Make		
			Button make = new Button();
	        make.setText("Make");
	        make.setPrefWidth(60);
	        make.setLayoutX(10);
	        make.setLayoutY(10);
	        make.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Hello World!");
	            }
	        });

//Step Amount      
	        TextField stepAmount = new TextField();
	        stepAmount.setPromptText("Enter Step Amount (Default is 1)");
	        stepAmount.setLayoutX(70);
	        stepAmount.setLayoutY(40);
	        stepAmount.setPrefWidth(210);
/*	        
	        stepAmount.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Hello World!");
	            }
	        });
*/
	        
//Step        
	        Button step = new Button();
	        step.setText("Step");
	        step.setPrefWidth(60);
	        step.setLayoutX(10);
	        step.setLayoutY(40);
	        step.setOnAction(new EventHandler<ActionEvent>() {

	        	@Override
        	    public void handle(ActionEvent e) {
        	       if ((stepAmount.getText() != null && !stepAmount.getText().isEmpty())) {
        	            // step step.getText() times
        	       } 
        	       else {
        	            //step 1
        	       }
        	    } 
	        });
	        
//Seed Amount      
	        TextField seedAmount = new TextField();
	        seedAmount.setPromptText("Enter Seed Amount (Default is 1)");
	        seedAmount.setLayoutX(70);
	        seedAmount.setLayoutY(70);
	        seedAmount.setPrefWidth(210);
/*	        
	        stepAmount.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Hello World!");
	            }
	        });
*/
	        
//Seed        
	        Button seed = new Button();
	        seed.setText("Seed");
	        seed.setPrefWidth(60);
	        seed.setLayoutX(10);
	        seed.setLayoutY(70);
	        seed.setOnAction(new EventHandler<ActionEvent>() {

	        	@Override
        	    public void handle(ActionEvent e) {
        	       if ((seedAmount.getText() != null && !seedAmount.getText().isEmpty())) {
        	            // step step.getText() times
        	       } 
        	       else {
        	            //step 1
        	       }
        	    } 
	        });

//Stats        
	        Button stats = new Button();
	        stats.setText("Stats");
	        stats.setPrefWidth(60);
	        stats.setLayoutX(10);
	        stats.setLayoutY(100);
	        stats.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Hello World!");
	            }
	        });
	        
//Animation Label & Amount
	        Label animation = new Label("Animation Speed: ");
	        animation.setLayoutX(10);
	        animation.setLayoutY(135);
	            
	        TextField animationAmount = new TextField();
	        animationAmount.setPromptText("Enter Animation Speed (Default is 1)");
	        animationAmount.setPrefWidth(210);
	        animationAmount.setLayoutX(10);
	        animationAmount.setLayoutY(160);
	        
	        
//Animation Start & Stop    
	        Button start = new Button();
	        start.setText("Start");
	        start.setPrefWidth(60);
	        start.setLayoutX(10);
	        start.setLayoutY(190);
	        
	        Button stop = new Button();
	        stop.setText("Stop");
	        stop.setPrefWidth(60);
	        stop.setLayoutX(70);
	        stop.setLayoutY(190);
	        stop.setDisable(true);
	        
	        start.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	            	stop.setDisable(false);
	            	start.setDisable(true);
	            	System.out.println("Hello World!");
	            }
	        });
	             
	        stop.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	            	start.setDisable(false);
	            	stop.setDisable(true);
	                System.out.println("Hello World!");
	            }
	        });
	        
//Quit      
	        Button quit = new Button();
	        quit.setText("Quit");
	        quit.setPrefWidth(60);
	        quit.setLayoutX(10);
	        quit.setLayoutY(220);
	        quit.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println("Hello World!");
	            }
	        });       
	        
	        
//add all buttons     
	        Pane root = new Pane();
	        
	        root.getChildren().add(make);
	        root.getChildren().add(stats);
	        root.getChildren().add(step);
	        root.getChildren().add(stepAmount);
	        root.getChildren().add(seed);
	        root.getChildren().add(seedAmount);
	        root.getChildren().add(animation);
	        root.getChildren().add(animationAmount);
	        root.getChildren().add(start);
	        root.getChildren().add(stop);
	        root.getChildren().add(quit);
	        	        
	        controls.setScene(new Scene(root, 300, 250));
	        controls.show();
			
		} 
		catch(Exception e) { 
			e.printStackTrace(); 
		}
	} 
	public static void main(String[] args) {
		launch(args);
	}
}
