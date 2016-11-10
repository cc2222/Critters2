/* Main.java
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * Casey Cotter
 * cbc2298
 * 16445
 * Max Fennis
 * maf3743
 * 16450
 * Slip days used: <0>
 * Fall 2016
 */
package assignment5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application { 
	
	static GridPane grid = new GridPane();
 
	
	/*
	 * Start: creates the display grid, controller pane, and stats pane. 
	 * Adds all buttons and fields into their respective Scenes.
	 * Handles actions of elements in scenes
	 */
	public void start(Stage primaryStage) {
		
		try {
			
			//setting grid
			grid.setGridLinesVisible(true);
			Scene scene = new Scene(grid, 550, 550);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//setting control pane
			Stage controls = new Stage();
			controls.setX(primaryStage.getX()+primaryStage.getWidth());
			controls.setY(primaryStage.getY());
			controls.setTitle("Controls");
			
			//setting stats pane       
	        Stage sttts = new Stage();
	        sttts.setX(primaryStage.getX()+primaryStage.getWidth());
	        sttts.setY(primaryStage.getY()+primaryStage.getHeight()/2);
	        sttts.setTitle("Stats");
			
			//all buttons
			ArrayList<Button> cntrl = new ArrayList<Button>();
			
			//Stats Button
	        Button stats = new Button();
	        cntrl.add(stats);
	        stats.setText("Stats");
	        stats.setPrefWidth(50);
	        stats.setLayoutX(10);
	        stats.setLayoutY(100);
	        stats.setDisable(true);
			
			//Make button   
			Button make = new Button();
			cntrl.add(make);
	        make.setText("Make");
	        make.setPrefWidth(50);
	        make.setLayoutX(10);
	        make.setLayoutY(10);
	        make.setDisable(true);
			
	        //Make Amount
			TextField makeAmount = new TextField();
	        makeAmount.setPromptText("Make Amount (Default=1)");
	        makeAmount.setLayoutX(170);
	        makeAmount.setLayoutY(10);
	        makeAmount.setPrefWidth(170);
	        
	        //get valid Critters for type selection
	        ArrayList<String> types = new ArrayList<String>();
	        String filePath = System.getProperty("user.dir");
	        String endPath = "\\src\\assignment5";
	        filePath += endPath;
	        File folder = new File(filePath);
	        File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
            	if (listOfFiles[i].isFile()) {
            		
            		String temp = listOfFiles[i].getName();
            		temp = temp.substring(0, temp.indexOf(".java"));
            		if(temp.equals("Critter"))
            		{
            			continue;
            		}
            		else
            		{
            			try{
            				//if able to make a critter instance of the class, add to list
            				Class<?> cls = Class.forName("assignment5."+temp);
            				Constructor<?> newConstructor = cls.getConstructor();
            				Object obj = newConstructor.newInstance();
            				Critter newCritter = (Critter)obj;
            				
            				types.add(temp);
            			}
            			catch(Exception e){
            			}
            		}
            	}
            }
	        //Make Type Selection
	        ComboBox<String> makeType = new ComboBox<String>();
	        makeType.setPromptText("Critter Type");
	        makeType.setLayoutX(60);
	        makeType.setLayoutY(10);
	        makeType.setPrefWidth(110);
	        
	        //add valid class names to the combobox for Make
	        for(String name : types)
	        {
	        	makeType.getItems().add(name);
	        }       
	        
	        //makeType action
	        makeType.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                make.setDisable(false);
	            }
	        });
	        
	        //make action
	        make.setOnAction(new EventHandler<ActionEvent>() {
	 
	        	boolean valid = false;
	        	
	        	//run make for the number of time of the value in the text field (if inputed)
	            @Override
	            public void handle(ActionEvent event) {
	            	if ((makeAmount.getText() != null && !makeAmount.getText().isEmpty())) {

        	    	   valid = true;
        	    	   try{
        	    		   int steps = Integer.parseInt(makeAmount.getText());
        	    		   for(int cnt = 0; cnt < steps; cnt++){
        	    			   Critter.makeCritter((String)makeType.getValue());
        	    		   }

	                   }
        	    	   catch(NumberFormatException f){
        	    		   valid = false; 
	                   }
	                   catch(Exception f){
	                	   valid = false; 
	                   } 
	        	     } 
	        	     if(valid == false){
        	    	   try{               			
        	    		   Critter.makeCritter((String)makeType.getValue());
   	                   	}
   	               		catch(NumberFormatException f){

   	                   	}
   	                   	catch(Exception f){

   	                   	}
	        	     }
	            }
	        });

	        //Step Amount      
	        TextField stepAmount = new TextField();
	        stepAmount.setPromptText("Step Amount (Default=1)");
	        stepAmount.setLayoutX(60);
	        stepAmount.setLayoutY(40);
	        stepAmount.setPrefWidth(170);
	        
	        //Step        
	        Button step = new Button();
	        cntrl.add(step);
	        step.setText("Step");
	        step.setPrefWidth(50);
	        step.setLayoutX(10);
	        step.setLayoutY(40);
	        
	        //Step the number of times of the value in the amount text field (if inputed)
	        step.setOnAction(new EventHandler<ActionEvent>() {

	        	@Override
        	    public void handle(ActionEvent e) {
	        		
	        		boolean valid = false; 
	        		
	        		if ((stepAmount.getText() != null && !stepAmount.getText().isEmpty())) {

        	    	   valid = true;
        	    	   try{
        	    		   int steps = Integer.parseInt(stepAmount.getText());
        	    		   for(int cnt = 0; cnt < steps; cnt++){
        	    			   Critter.worldTimeStep();
        	    		   }
        	    		   if(sttts.isShowing())
        	    		   {
	               				stats.fire();
        	    		   }

	                   }
        	    	   catch(NumberFormatException f){
        	    		   valid = false; 
	                   }
	                   catch(Exception f){
	                	   valid = false; 
	                   }
        	    	   
        	       } 
        	       if(valid == false)
        	       {
        	    	   try{               			
                  			Critter.worldTimeStep();
                  			if(sttts.isShowing())
	               			{
	               				stats.fire();
	               			}
   	                   	}
   	               		catch(NumberFormatException f){

   	                   	}
   	                   	catch(Exception f){

   	                   	}
        	       }
        	       Critter.displayWorld();
        	    } 
	        });
	        
	        //Seed Amount      
	        TextField seedAmount = new TextField();
	        seedAmount.setPromptText("Seed Amount (Default=1)");
	        seedAmount.setLayoutX(60);
	        seedAmount.setLayoutY(70);
	        seedAmount.setPrefWidth(170);
	        
	        //Seed        
	        Button seed = new Button();
	        cntrl.add(seed);
	        seed.setText("Seed");
	        seed.setPrefWidth(50);
	        seed.setLayoutX(10);
	        seed.setLayoutY(70);
	        
	        //Change Seed to the value in the text field (if inputed)
	        seed.setOnAction(new EventHandler<ActionEvent>() {

	        	@Override
        	    public void handle(ActionEvent e) {
	        		
	        		boolean valid = false; 
	        		
	        		if ((seedAmount.getText() != null && !seedAmount.getText().isEmpty())) {
        	    	   valid = true;
        	    	   try{
               			int numSeeds = Integer.parseInt(seedAmount.getText());
               			Critter.setSeed((long)numSeeds);
	                   	}
	               		catch(NumberFormatException f){
	               			valid = false; 
	                   	}
	                   	catch(Exception f){
	                   		valid = false; 
	                   	}
        	    	   
        	       } 
        	       if(valid == false)
        	       {
        	    	   try{
                  			int numSeeds = 1;
                  			Critter.setSeed((long)numSeeds);
   	                   	}
   	               		catch(NumberFormatException f){

   	                   	}
   	                   	catch(Exception f){

   	                   	}
        	       }
        	    } 
	        });
	        
	        //Stats Combo
	        ComboBox<String> statsType = new ComboBox<String>();
	        statsType.setPromptText("Critter Type");
	        statsType.setLayoutX(60);
	        statsType.setLayoutY(100);
	        statsType.setPrefWidth(110);
	        
	        for(String name : types)
	        {
	        	statsType.getItems().add(name);
	        }
	        
	        Pane root2 = new Pane();
	        Text t = new Text(10,10,"");
	        sttts.setScene(new Scene(root2, 350, 230));
	        
	        //Stats Type Action
	        statsType.setOnAction(new EventHandler<ActionEvent>() {
	       	 
	            @Override
	            public void handle(ActionEvent event) {
	                stats.setDisable(false);
	            }
	        });
	        
	        //Stats Action: redirecting sys output stream to catch stat info
	        stats.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                PrintStream ps = new PrintStream(baos);
	                
	                PrintStream old = System.out; // Save the old System.out
	                System.setOut(ps);

	    	        
	            	try{
            			//get all instances of the desired critter type
            			String critT = (String)(statsType.getValue());
            			List<Critter> tempStats = Critter.getInstances(critT);
                		Class<?> classType = Class.forName("assignment5."+critT);
                		
                		//get the correct runstats method for the desired critter type and call it
                		Method method = classType.getMethod("runStats",List.class);
                		method.invoke(classType,tempStats);
                		
                		t.setText(baos.toString());
                	}
                	catch(Exception e){
                		
                	}
	            	
	            	//after first stats is invoked
	            	if(root2.getChildren().contains(t))
	            	{
	            		root2.getChildren().remove(t);
	            	}
	            	root2.getChildren().add(t);
	            	
	            	sttts.show();
	            	
	            	//Put things back
	                System.out.flush();
	                System.setOut(old);
	            }
	        });
	        
	        //Animation Label
	        Label animation = new Label("Animation Speed: ");
	        animation.setLayoutX(10);
	        animation.setLayoutY(135);
	        
	        //Animation Text
	        TextField animationAmount = new TextField();
	        animationAmount.setPromptText("Animation Speed (Default=1)");
	        animationAmount.setPrefWidth(170);
	        animationAmount.setLayoutX(10);
	        animationAmount.setLayoutY(160);
	        
	        
	        //Animation Start Button
	        Button start = new Button();
	        cntrl.add(start);
	        start.setText("Start");
	        start.setPrefWidth(50);
	        start.setLayoutX(10);
	        start.setLayoutY(190);
	        
	        //Animation Stop Button
	        Button stop = new Button();
	        cntrl.add(stop);
	        stop.setText("Stop");
	        stop.setPrefWidth(50);
	        stop.setLayoutX(70);
	        stop.setLayoutY(190);
	        stop.setDisable(true);
	        
	        //Animation handler and timer: loop of worldTimeSteps at speed of inputed value
	        AnimationTimer animate = new AnimationTimer(){
	        	public void handle(long now) {

	                 boolean valid = false;
	            	
		        		if ((animationAmount.getText() != null && !animationAmount.getText().isEmpty())) {
	        	    	   valid = true;
	        	    	   try{
	               			int speed = Integer.parseInt(animationAmount.getText());
	               			
	               			for(int i = 0; i < speed; i++)
	               			{
	               				Critter.worldTimeStep();
	               			}
	               			Critter.displayWorld();
	               			
	               			if(sttts.isShowing())
	               			{
	               				stats.fire();
	               			}
	               			
		                   	}
		               		catch(NumberFormatException f){
		               			valid = false; 
		                   	}
		                   	catch(Exception f){
		                   		valid = false; 
		                   	}  
	        	       } 
	        	       if(valid == false)
	        	       {
	        	    	   try{
	        	    		   	Critter.worldTimeStep();
	        	    		   	Critter.displayWorld();
	        	    		   	if(sttts.isShowing())
		               			{
	        	    		   		stats.fire();
		               			}
	   	                   	}
	   	               		catch(NumberFormatException f){
	
	   	                   	}
	   	                   	catch(Exception f){
	
	   	                   	}
	        	       } 
	            }
	        };

	        //Start action handle: disable buttons and start animation
	        start.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	            	
            		for(Button x : cntrl)
	    	        {
	    	        	x.setDisable(true);
	    	        }
	            	makeType.setDisable(true);
	            	stats.setDisable(false);
	            	stop.setDisable(false);
	            	//start.setText("Stop");
	            	
	            	animate.start();
	        	       
	            }
	        });
	        
	      //Start action handle: re-enable buttons and stop animation
	        stop.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            	animate.stop();
	            	for(Button x : cntrl)
	    	        {
	    	        	x.setDisable(false);
	    	        }
	            	makeType.setDisable(false);
	            	stop.setDisable(true);
	            	
	            	
	            }
	        });
	        
	        //Quit Button  
	        Button quit = new Button();
	        cntrl.add(quit);
	        quit.setText("Quit");
	        quit.setPrefWidth(50);
	        quit.setLayoutX(290);
	        quit.setLayoutY(190);
	        
	        //Quit Handle: quit program
	        quit.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	               System.exit(0);
	            }
	        });       

	        Pane root = new Pane();
	        
	        //add all buttons and other fields
	        for(Button x : cntrl)
	        {
	        	root.getChildren().add(x);
	        }
	        root.getChildren().add(makeAmount);
	        root.getChildren().add(makeType);
	        root.getChildren().add(seedAmount);
	        root.getChildren().add(stepAmount);
	        root.getChildren().add(statsType);
	        root.getChildren().add(animation);
	        root.getChildren().add(animationAmount);
	        
	        controls.setScene(new Scene(root, 350, 220));
	        controls.show();
			
		} 
		catch(Exception e) { 
			e.printStackTrace(); 
		}
	} 
	
	public static void paint(){
		grid.getChildren().clear();
		double width = (500 + (Params.world_width/2))/Params.world_width;
		double height = (500 + (Params.world_height/2))/Params.world_height;
		Critter[][] critters = Critter.getCritters();
		for(int i = 0; i < Params.world_height; i++){
			for(int j = 0; j < Params.world_width; j++){
				if(critters[i][j] != null){
					Shape s = Critter.getIcon(critters[i][j].viewShape(), critters[i][j].viewOutlineColor(), critters[i][j].viewFillColor(), critters[i][j].viewColor());
					grid.add(s, j, i);
				}
				else{
					Shape s = new Rectangle(width, height);
					s.setFill(Color.WHITE);
					grid.add(s, j, i);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
