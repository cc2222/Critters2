package assignment5;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Main extends Application { 
	static GridPane grid = new GridPane();
	@Override 
	public void start(Stage primaryStage) {
		try {
			grid.setGridLinesVisible(true);
			Scene scene = new Scene(grid, 550, 550);
			primaryStage.setScene(scene);
			primaryStage.show();
			// Paints the icons.
			
			Stage controls = new Stage();
			controls.setX(primaryStage.getX()+primaryStage.getWidth());
			controls.setY(primaryStage.getY());
			controls.setTitle("Controls");
			
			ArrayList<Button> cntrl = new ArrayList<Button>();
			
			
//Make & Type & Amount   
			Button make = new Button();
			cntrl.add(make);
	        make.setText("Make");
	        make.setPrefWidth(50);
	        make.setLayoutX(10);
	        make.setLayoutY(10);
	        make.setDisable(true);
			
			TextField makeAmount = new TextField();
	        makeAmount.setPromptText("Make Amount (Default=1)");
	        makeAmount.setLayoutX(170);
	        makeAmount.setLayoutY(10);
	        makeAmount.setPrefWidth(170);
	        
	        //get valid Critters for type selection
	        ArrayList<String> types = new ArrayList<String>();
 
	        //gets the filepath for the folder that our project is in
	        String filepath = System.getProperty("user.dir");
	        String endpath = "\\src\\assignment5";
	        filepath += endpath;
	        
	        File folder = new File(filepath);
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
	        
	        ComboBox makeType = new ComboBox();
	        makeType.setPromptText("Critter Type");
	        makeType.setLayoutX(60);
	        makeType.setLayoutY(10);
	        makeType.setPrefWidth(110);
	        
	        for(String name : types)
	        {
	        	makeType.getItems().add(name);
	        }       
	            
	        makeType.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                make.setDisable(false);
	            }
	        });
	        
	        make.setOnAction(new EventHandler<ActionEvent>() {
	 
	        	boolean valid = false;
	        	
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
	        cntrl.add(step);
	        step.setText("Step");
	        step.setPrefWidth(50);
	        step.setLayoutX(10);
	        step.setLayoutY(40);
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
	        cntrl.add(seed);
	        seed.setText("Seed");
	        seed.setPrefWidth(50);
	        seed.setLayoutX(10);
	        seed.setLayoutY(70);
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

//Stats        
	        Stage sttts = new Stage();
	        sttts.setX(primaryStage.getX()+primaryStage.getWidth());
	        sttts.setY(primaryStage.getY()+primaryStage.getHeight()/2);
	        sttts.setTitle("Stats");
			
	        Button stats = new Button();
	        cntrl.add(stats);
	        stats.setText("Stats");
	        stats.setPrefWidth(50);
	        stats.setLayoutX(10);
	        stats.setLayoutY(100);
	        stats.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	            /*	try{
            			//get all instances of the desired critter type
            			List<Critter> tempStats = Critter.getInstances(xxx);
                		Class<?> classType = Class.forName("assignment5."+xxx);
                		
                		//get the correct runstats method for the desired critter type and call it
                		Method method = classType.getMethod("runStats",List.class);
                		method.invoke(classType,tempStats);
                	}
                	catch(Exception e){
                		
                	} */
	            }
	        });
	        
//Animation Label & Amount
	        Label animation = new Label("Animation Speed: ");
	        animation.setLayoutX(10);
	        animation.setLayoutY(135);
	            
	        TextField animationAmount = new TextField();
	        animationAmount.setPromptText("Animation Speed (Default=1)");
	        animationAmount.setPrefWidth(170);
	        animationAmount.setLayoutX(10);
	        animationAmount.setLayoutY(160);
	        
	        
//Animation Start & Stop    
	        Button start = new Button();
	        cntrl.add(start);
	        start.setText("Start");
	        start.setPrefWidth(50);
	        start.setLayoutX(10);
	        start.setLayoutY(190);
	        
	        Button stop = new Button();
	        cntrl.add(stop);
	        stop.setText("Stop");
	        stop.setPrefWidth(50);
	        stop.setLayoutX(70);
	        stop.setLayoutY(190);
	        stop.setDisable(true);
	        
	        start.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            	for(Button x : cntrl)
	    	        {
	    	        	x.setDisable(true);
	    	        }
	            	makeType.setDisable(true);
	            	stop.setDisable(false);
	            	
	            }
	        });
	             
	        stop.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {

	            	for(Button x : cntrl)
	    	        {
	    	        	x.setDisable(false);
	    	        }
	            	makeType.setDisable(false);

	            	stop.setDisable(true);
	            }
	        });
	        
//Quit      
	        Button quit = new Button();
	        cntrl.add(quit);
	        quit.setText("Quit");
	        quit.setPrefWidth(50);
	        quit.setLayoutX(290);
	        quit.setLayoutY(190);
	        quit.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	               System.exit(0);
	            }
	        });       
	        
	        
    
	        Pane root = new Pane();
	      //add all buttons 
	        for(Button x : cntrl)
	        {
	        	root.getChildren().add(x);
	        }
	        
	        root.getChildren().add(makeAmount);
	        root.getChildren().add(makeType);
	        root.getChildren().add(seedAmount);
	        root.getChildren().add(stepAmount);
	        root.getChildren().add(animation);
	        root.getChildren().add(animationAmount);
	        
	        	        
	        controls.setScene(new Scene(root, 350, 220));
	        controls.show();
	        Pane root2 = new Pane();
	        sttts.setScene(new Scene(root2, 350, 230));
	        sttts.show();
			
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
					grid.add(s, i, j);
				}
				else{
					Shape s = new Rectangle(width, height);
					s.setFill(Color.WHITE);
					grid.add(s, i, j);
				}
			}
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
