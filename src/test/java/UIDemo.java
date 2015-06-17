import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class UIDemo extends Application {
	
	public static boolean expandFlagForForm = true;
	
	/**
	* @param args the command line arguments
	*/
	public static void main(String[] args) {
		Application.launch("rgdsgsdgsd");
	}
	@Override
	public void start(Stage primaryStage) {
	primaryStage.setTitle("Hello World");
	//StackPane root = new StackPane();
	Group root = new Group();
	Scene scene = new Scene(root, 850, 950);
	/*Button btn = new Button();
	btn.setLayoutX(100);
	btn.setLayoutY(80);
	btn.setText("Hello World");
	btn.setOnAction(new EventHandler<ActionEvent>() {
	public void handle(ActionEvent event) {
	System.out.println("Hello World");
	}
	});
	root.getChildren().add(btn);*/
	//BorderPane borderpane = new BorderPane();

	TitledPane tp = new TitledPane();
	//hbox.setPadding(new Insets(15, 12, 15, 12));
	//tp.minWidth(800);
	//tp.setLayoutX(100);
	//tp.sceneToLocal(200, 300);
	/*tp.setText("ADD CONTACT                                                                                                            " +
			"                                            " +
			"                    ");*/
	tp.prefWidthProperty().bind(scene.widthProperty());
	tp.setText("ADD CONTACT");
	final GridPane gridpane = new GridPane();
	gridpane.setPadding(new Insets(5));
	//gridpane.setHgap(20);
	gridpane.setVgap(5);
	Label phoneLabel = new Label("Phone No");
	TextField phoneText = new TextField();
	//phoneText.setPrefWidth(20);
	//Label lNameLbl = new Label("First Name");
	//TextField lNameFld = new TextField();
	final  Group formFieldGroup= new Group();
	//formFieldGroup.setVisible(false);
	
	phoneText.focusedProperty().addListener(new ChangeListener<Boolean>()
			{
			    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
			    {
			        if(expandFlagForForm)
			        {
			        	Label firstNameLabel = new Label("First Name");
			        	TextField firstNameText = new TextField();
			        	//firstNameText.setPrefWidth(20);
			        	GridPane.setHalignment(firstNameLabel, HPos.LEFT);
			        	gridpane.add(firstNameLabel, 0 ,2);
			        	GridPane.setHalignment(firstNameText, HPos.LEFT);
			        	gridpane.add(firstNameText, 0, 3);
			        	
			        	Label middleInitialLabel = new Label("Middle Initial");
			        	TextField middleInitialText = new TextField();
			        	middleInitialText.setPrefWidth(1);
			        	GridPane.setHalignment(middleInitialLabel, HPos.LEFT);
			        	gridpane.add(middleInitialLabel, 0 ,4);
			        	GridPane.setHalignment(middleInitialText, HPos.LEFT);
			        	gridpane.add(middleInitialText, 0, 5);
			        	Label lastNameLabel = new Label("Last Name");
			        	TextField lastNameText = new TextField();
			        	//middleInitialText.setPrefWidth(1);
			        	GridPane.setHalignment(lastNameLabel, HPos.LEFT);
			        	gridpane.add(lastNameLabel, 0 ,6);
			        	GridPane.setHalignment(lastNameText, HPos.LEFT);
			        	gridpane.add(lastNameText, 0, 7);
			        	
			        	Label genderLabel = new Label("Gender");
			        	GridPane.setHalignment(genderLabel, HPos.LEFT);
			        	gridpane.add(genderLabel, 0 ,8);
			        	final ToggleGroup group = new ToggleGroup();

			        	RadioButton rb1 = new RadioButton("Male");
			        	rb1.setToggleGroup(group);
			        	rb1.setSelected(true);
			        	GridPane.setHalignment(rb1, HPos.LEFT);
			        	gridpane.add(rb1, 0 ,9);

			        	RadioButton rb2 = new RadioButton("FEMALE");
			        	rb2.setToggleGroup(group);
			        	GridPane.setHalignment(rb2, HPos.RIGHT);
			        	gridpane.add(rb2, 0 ,9);
			        	
			        	Label addressLabel = new Label("Address Line 1");
			        	GridPane.setHalignment(addressLabel, HPos.LEFT);
			        	gridpane.add(addressLabel, 0 ,10);
			        	TextField addressLine1Text = new TextField();
			        	GridPane.setHalignment(addressLine1Text, HPos.LEFT);
			        	gridpane.add(addressLine1Text, 0 ,11);
			        	
			        	Label addressLabel2 = new Label("Address Line 2");
			        	GridPane.setHalignment(addressLabel2, HPos.LEFT);
			        	gridpane.add(addressLabel2, 0 ,12);
			        	TextField addressLine2Text = new TextField();
			        	addressLine2Text.setPrefColumnCount(5);
			        	//GridPane.setColumnSpan(addressLine2Text, 2);
			        	GridPane.setHalignment(addressLine2Text, HPos.LEFT);
			        	gridpane.add(addressLine2Text, 0,13);
			        	
			        	Label cityLabel = new Label("City");
			        	GridPane.setHalignment(cityLabel, HPos.LEFT);
			        	gridpane.add(cityLabel, 0 ,14);
			        	TextField cityText = new TextField();
			        	//addressLine2Text.setPrefColumnCount(5);
			        	//GridPane.setColumnSpan(zipCodeText, 2);
			        	GridPane.setHalignment(cityText, HPos.LEFT);
			        	gridpane.add(cityText, 0 ,15);
			        	
			        	Label stateLabel = new Label("State");
			        	GridPane.setHalignment(stateLabel, HPos.LEFT);
			        	gridpane.add(stateLabel, 0 ,16);
			        	TextField stateText = new TextField();
			        	//addressLine2Text.setPrefColumnCount(5);
			        	//GridPane.setColumnSpan(zipCodeText, 2);
			        	GridPane.setHalignment(stateText, HPos.LEFT);
			        	gridpane.add(stateText, 0 ,17);
			        	
			        	Label zipCodeLabel = new Label("Zip Code");
			        	GridPane.setHalignment(zipCodeLabel, HPos.LEFT);
			        	gridpane.add(zipCodeLabel, 0 ,18);
			        	TextField zipCodeText = new TextField();
			        	//addressLine2Text.setPrefColumnCount(5);
			        	//GridPane.setColumnSpan(zipCodeText, 2);
			        	GridPane.setHalignment(zipCodeText, HPos.LEFT);
			        	gridpane.add(zipCodeText, 0 ,19);
			        	Button saveButt = new Button("Save");
			        	gridpane.add(saveButt, 0 ,20);
			        	//formFieldGroup.setVisible(true);
			        	//formFieldGroup.getChildren().addAll(cityLabel,cityText,stateLabel,stateText,zipCodeLabel,zipCodeText,saveButt);
			        	//formFieldGroup.
			        	//gridpane.add(formFieldGroup, 0 ,10);
			        	
			        	
			    
			        	
			        	expandFlagForForm= false;
			        }
			        else
			    	{
			    		gridpane.getChildren().remove(2,22);
			    		expandFlagForForm= true;
			    	}
			    }
			});
	
	// First name label
	GridPane.setHalignment(phoneLabel, HPos.LEFT);
	gridpane.add(phoneLabel, 0, 0);
	// Last name label
//	GridPane.setHalignment(lNameLbl, HPos.RIGHT);
	//gridpane.add(lNameLbl, 0, 1);
	// First name field
	GridPane.setHalignment(phoneText, HPos.LEFT);
	gridpane.add(phoneText, 0, 1);
	// Last name field
	//GridPane.setHalignment(lNameFld, HPos.LEFT);
	//gridpane.add(lNameFld, 1, 1);
	// Save button
	//GridPane.setHalignment(saveButt, HPos.RIGHT);
	//gridpane.add(saveButt, 1, 2);
	//root.getChildren().add(gridpane);
	tp.setContent(gridpane);
	 final VBox vbox = new VBox();
     vbox.setSpacing(5);
     vbox.setPadding(new Insets(10, 0, 0, 10));
     vbox.getChildren().addAll(tp,addHBox());
	
	//tp.prefWidth(650);
	root.getChildren().add(vbox);
	//borderpane.setTop(tp);
	//root.getChildren().add(borderpane);
	//root.getChildren().add(addHBox());
	primaryStage.setScene(scene);
	primaryStage.show();
	}
	
	public HBox addHBox() {
	    HBox hbox = new HBox();
	    //hbox.setPadding(new Insets(5,5, 5, 650));
	    hbox.setAlignment(Pos.BOTTOM_LEFT); 
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    //Label zipCodeLabel = new Label("Zip Code");
    	
    	TextField searchText = new TextField();
    	searchText.setPromptText("Search with Name or Phone No.");
    	//addressLine2Text.setPrefColumnCount(5);
    	//GridPane.setColumnSpan(zipCodeText, 2);
	   /* Button buttonCurrent = new Button("Current");
	    buttonCurrent.setPrefSize(100, 20);

	    Button buttonProjected = new Button("Projected");
	    buttonProjected.setPrefSize(100, 20);*/
	   hbox.getChildren().addAll(searchText);

	    return hbox;
	}


}
