import java.util.Set;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import uid.contact.manager.dao.domain.Person;


public class NewUIDEMO extends Application {
	
	 public static void main(String[] args) {
	        Application.launch(args);
	    }

	    @Override
	    public void start(Stage stage) {
	    	stage.setTitle("Hello World");
	    	//StackPane root = new StackPane();
	    	Group root = new Group();
	    	Scene scene = new Scene(root, 850, 950);
	    	 VBox vbox = new VBox();
	    	 vbox.setSpacing(3);
	    	 
	    	 
	    	
	    	
	    	final TitledPane viewPane = new TitledPane();
	    	 Tooltip tooltip = new Tooltip("Click view button in the contact lists to see the contact details of other person");
	    	 viewPane.setTooltip(tooltip);
	    	 viewPane.prefWidthProperty().bind(scene.widthProperty());
	    	 final GridPane gridpane = new GridPane();
	    	 gridpane.setPadding(new Insets(5));
	    	  gridpane.setHgap(20);
	    	 gridpane.setVgap(10);
	    	 Label firstNameLabel = new Label("First Name           :");
	    	 Label firstNameValue = new Label("Parveen");
	    	 Label middleNameLabel = new Label("Middle Initial       :");
	    	 Label middleNameValue = new Label("S");
	    	 Label lastNameLabel = new Label("Last Name           :");
	    	 Label lastNameValue = new Label("Chhikara");
	    	 GridPane.setHalignment(firstNameLabel, HPos.LEFT);
	         gridpane.add(firstNameLabel, 0 ,0);
	         GridPane.setHalignment(firstNameValue, HPos.LEFT);
	         gridpane.add(firstNameValue, 1,0);
	         GridPane.setHalignment(middleNameLabel, HPos.LEFT);
	         gridpane.add(middleNameLabel, 0 ,1);
	         GridPane.setHalignment(middleNameValue, HPos.LEFT);
	         gridpane.add(middleNameValue, 1 ,1);
	         GridPane.setHalignment(lastNameLabel, HPos.LEFT);
	         gridpane.add(lastNameLabel, 0 ,2);
	         GridPane.setHalignment(lastNameValue, HPos.LEFT);
	         gridpane.add(lastNameValue, 1 ,2);
	    	
	    	 Label phoneLabel = new Label("Phone                  :");
	    	 Label phoneValue = new Label("342343242342134");
	    	 GridPane.setHalignment(phoneLabel, HPos.LEFT);
	         gridpane.add(phoneLabel, 0 ,3);
	         GridPane.setHalignment(phoneValue, HPos.LEFT);
	         gridpane.add(phoneValue, 1 ,3);
	    	 Label genderLabel = new Label("Gender                 :");
	    	 Label genderValue = new Label("Male");
	    	 GridPane.setHalignment(genderLabel, HPos.LEFT);
	         gridpane.add(genderLabel, 0 ,4);
	         GridPane.setHalignment(genderValue, HPos.LEFT);
	         gridpane.add(genderValue, 1 ,4);
	    	 Label addressLabel = new Label("Address Line1      :");
	    	 Label addressValue= new Label("4-A Chandroop Nagar Babyal");
	    	 GridPane.setHalignment(addressLabel, HPos.LEFT);
	         gridpane.add(addressLabel, 0 ,5);
	         GridPane.setHalignment(addressValue, HPos.LEFT);
	         gridpane.add(addressValue, 1 ,5);
	         Label addressLabel2 = new Label("Address Line2      :");
	    	 Label addressValue2= new Label("gsdfdsfdsv Babyal");
	    	 GridPane.setHalignment(addressLabel2, HPos.LEFT);
	         gridpane.add(addressLabel2, 0 ,6);
	         GridPane.setHalignment(addressValue2, HPos.LEFT);
	         gridpane.add(addressValue2, 1 ,6);
	    	 Label cityLabel = new Label("City                      :");
	    	 Label cityValue = new Label("Ambala Cantt");
	    	 GridPane.setHalignment(cityLabel, HPos.LEFT);
	         gridpane.add(cityLabel, 0 ,7);
	         GridPane.setHalignment(cityValue, HPos.LEFT);
	         gridpane.add(cityValue, 1 ,7);
	    	
	    	 Label stateLabel = new Label("State                    :");
	    	 Label stateValue = new Label("HR");
	    	 GridPane.setHalignment(stateLabel, HPos.LEFT);
	         gridpane.add(stateLabel, 0 ,8);
	         GridPane.setHalignment(stateValue, HPos.LEFT);
	         gridpane.add(stateValue, 1 ,8);
	    	 Label zipLabel = new Label("Zip Code              :");
	    	 Label zipValue = new Label("133001");
	    	 GridPane.setHalignment(zipLabel, HPos.LEFT);
	         gridpane.add(zipLabel, 0 ,9);
	         GridPane.setHalignment(zipValue, HPos.LEFT);
	         gridpane.add(zipValue, 1 ,9);
	         
	         viewPane.setContent(gridpane);
	    	 
	    /*	 GridPane.setHalignment(firstNameLabel, HPos.LEFT);
	         gridpane.add(firstNameLabel, 0 ,0);
	         GridPane.setHalignment(firstNameLabel, HPos.LEFT);
	         gridpane.add(firstNameLabel, 1 ,0);
	         GridPane.setHalignment(firstNameLabel, HPos.LEFT);
	         gridpane.add(firstNameLabel, 2 ,0);*/
	    	 
	    	 
	         Button btn = new Button();
		    	btn.setText("View");
		    	btn.setOnAction(new EventHandler<ActionEvent>() {
		    	public void handle(ActionEvent event) {
		    		viewPane.setText("Parveen Chhikara : View Details");
		    	}
		    	});
		    	
		    	Button btn2 = new Button();
		    	btn2.setText("Edit");
		    	btn2.setOnAction(new EventHandler<ActionEvent>() {
		    	public void handle(ActionEvent event) {
		    		
		    		}
		    	});
		    	
	    	 TitledPane editPane = new TitledPane();
	    	 editPane.prefWidthProperty().bind(scene.widthProperty());
	    	// Tooltip tooltip2 = new Tooltip("Click edit button in the contact lists to edit the contact details of other person");
	    	// editPane.setTooltip(tooltip2);
	    	 editPane.prefWidthProperty().bind(scene.widthProperty());
	    	 final GridPane gridPaneEdit = new GridPane();
	    	 gridPaneEdit.setPadding(new Insets(5));
	    	 gridPaneEdit.setHgap(20);
	    	 gridPaneEdit.setVgap(10);
	    	 Label firstNameLabelEdit = new Label("First Name*           :");
	    	final TextField firstNameTextEdit = new TextField();
	    	 firstNameTextEdit.setPrefWidth(30 * 7);
	    	 Label middleNameLabelEdit = new Label("Middle Initial        :");
	    	 final TextField middleNameTextEdit = new TextField();
	    	 Label lastNameLabelEdit = new Label("Last Name*           :");
	    	 final TextField lastNameTextEdit = new TextField();
	    	 GridPane.setHalignment(firstNameLabelEdit, HPos.LEFT);
	    	 gridPaneEdit.add(firstNameLabelEdit, 0 ,0);
	         GridPane.setHalignment(firstNameTextEdit, HPos.LEFT);
	         gridPaneEdit.add(firstNameTextEdit, 1,0);
	         GridPane.setHalignment(middleNameLabelEdit, HPos.LEFT);
	         gridPaneEdit.add(middleNameLabelEdit, 0 ,1);
	         GridPane.setHalignment(middleNameTextEdit, HPos.LEFT);
	         gridPaneEdit.add(middleNameTextEdit, 1 ,1);
	         GridPane.setHalignment(lastNameLabelEdit, HPos.LEFT);
	         gridPaneEdit.add(lastNameLabelEdit, 0 ,2);
	         GridPane.setHalignment(lastNameTextEdit, HPos.LEFT);
	         gridPaneEdit.add(lastNameTextEdit, 1 ,2);
	    	
	    	 Label phoneLabelEdit = new Label("Phone*                  :");
	    	 final  TextField phoneTextEdit = new TextField();
	    	 GridPane.setHalignment(phoneLabelEdit, HPos.LEFT);
	    	 gridPaneEdit.add(phoneLabelEdit, 0 ,3);
	         GridPane.setHalignment(phoneTextEdit, HPos.LEFT);
	         gridPaneEdit.add(phoneTextEdit, 1 ,3);
	    	 Label genderLabelEdit = new Label("Gender*                 :");
	    	 final ToggleGroup groupEdit = new ToggleGroup();
	     	 final RadioButton maleRadioEdit = new RadioButton("Male");
	     	 maleRadioEdit.setToggleGroup(groupEdit);
	     	 maleRadioEdit.setSelected(true);
	     	 final RadioButton femaleRadioEdit = new RadioButton("FEMALE");
	     	 femaleRadioEdit.setToggleGroup(groupEdit);
	    	 GridPane.setHalignment(genderLabelEdit, HPos.LEFT);
	    	 gridPaneEdit.add(genderLabelEdit, 0 ,4);
	         GridPane.setHalignment(maleRadioEdit, HPos.LEFT);
	         gridPaneEdit.add(maleRadioEdit, 1 ,4);
	         GridPane.setHalignment(femaleRadioEdit, HPos.RIGHT);
	         gridPaneEdit.add(femaleRadioEdit, 1 ,4);
	    	 Label addressLine1LabelEdit = new Label("Address Line1*      :");
	    	 final TextField addressLine1TextEdit= new TextField();
	    	 GridPane.setHalignment(addressLine1LabelEdit, HPos.LEFT);
	    	 gridPaneEdit.add(addressLine1LabelEdit, 0 ,5);
	         GridPane.setHalignment(addressLine1TextEdit, HPos.LEFT);
	         gridPaneEdit.add(addressLine1TextEdit, 1 ,5);
	         Label addressLine2LabelEdit = new Label("Address Line2       :");
	         final TextField addressLine2TextEdit= new TextField();
	    	 GridPane.setHalignment(addressLine2LabelEdit, HPos.LEFT);
	    	 gridPaneEdit.add(addressLine2LabelEdit, 0 ,6);
	         GridPane.setHalignment(addressLine2TextEdit, HPos.LEFT);
	         gridPaneEdit.add(addressLine2TextEdit, 1 ,6);
	    	 Label cityLabelEdit = new Label("City*                      :");
	    	 final TextField cityTextEdit = new TextField();
	    	 GridPane.setHalignment(cityLabelEdit, HPos.LEFT);
	    	 gridPaneEdit.add(cityLabelEdit, 0 ,7);
	         GridPane.setHalignment(cityTextEdit, HPos.LEFT);
	         gridPaneEdit.add(cityTextEdit, 1 ,7);
	    	
	    	 Label stateLabelEdit = new Label("State*                    :");
	    	 final TextField stateTextEdit = new TextField();
	    	 GridPane.setHalignment(stateLabelEdit, HPos.LEFT);
	    	 gridPaneEdit.add(stateLabelEdit, 0 ,8);
	         GridPane.setHalignment(stateTextEdit, HPos.LEFT);
	         gridPaneEdit.add(stateTextEdit, 1 ,8);
	    	 Label zipLabelEdit = new Label("Zip Code*              :");
	    	 final TextField zipTextEdit = new TextField();
	    	 GridPane.setHalignment(zipLabelEdit, HPos.LEFT);
	    	 gridPaneEdit.add(zipLabelEdit, 0 ,9);
	         GridPane.setHalignment(zipTextEdit, HPos.LEFT);
	         gridPaneEdit.add(zipTextEdit, 1 ,9);
	         final Label editStatus = new Label("");
	    	 GridPane.setHalignment(editStatus, HPos.LEFT);
	    	 gridPaneEdit.add(editStatus, 1 ,10);
	    	 
	    	 final Label firstNameValidationMessageLabel = new Label("");
	    	 final Label lastNameValidationMessageLabel = new Label("");
	    	 final Label middleNameValidationMessageLabel = new Label("");
	    	 final Label phoneValidationMessageLabel = new Label("");
	    	 final Label addressLine1ValidationMessageLabel = new Label("");
	    	 final Label addressLine2ValidationMessageLabel = new Label("");
	    	 final Label cityValidationMessageLabel = new Label("");
	    	 final Label stateValidationMessageLabel = new Label("");
	    	 final Label zipCodeValidationMessageLabel = new Label("");
	    	 GridPane.setHalignment(firstNameValidationMessageLabel, HPos.LEFT);
	    	 gridPaneEdit.add(firstNameValidationMessageLabel, 2 ,0);
	    	 GridPane.setHalignment(middleNameValidationMessageLabel, HPos.LEFT);
	    	 gridPaneEdit.add(middleNameValidationMessageLabel, 2 ,1);
	    	 GridPane.setHalignment(lastNameValidationMessageLabel, HPos.LEFT);
	    	 gridPaneEdit.add(lastNameValidationMessageLabel, 2 ,2);
	    	 GridPane.setHalignment(phoneValidationMessageLabel, HPos.LEFT);
	    	 gridPaneEdit.add(phoneValidationMessageLabel, 2 ,3);
	    	 GridPane.setHalignment(addressLine1ValidationMessageLabel, HPos.LEFT);
	    	 gridPaneEdit.add(addressLine1ValidationMessageLabel, 2 ,5);
	    	 GridPane.setHalignment(addressLine2ValidationMessageLabel, HPos.LEFT);
	    	 gridPaneEdit.add(addressLine2ValidationMessageLabel, 2 ,6);
	    	 GridPane.setHalignment(cityValidationMessageLabel, HPos.LEFT);
	    	 gridPaneEdit.add(cityValidationMessageLabel, 2 ,7);
	    	 GridPane.setHalignment(stateValidationMessageLabel, HPos.LEFT);
	    	 gridPaneEdit.add(stateValidationMessageLabel, 2 ,8);
	    	 GridPane.setHalignment(zipCodeValidationMessageLabel, HPos.LEFT);
	    	 gridPaneEdit.add(zipCodeValidationMessageLabel, 2 ,9);
	    	 
	    	 final Button saveButton = new Button("Save");
	    	 GridPane.setHalignment(saveButton, HPos.LEFT);
	    	 gridPaneEdit.add(saveButton, 1 ,11);
	    	 saveButton.setOnAction(new EventHandler<ActionEvent>() {
			    	public void handle(ActionEvent event) {
			    		firstNameTextEdit.setStyle("-fx-border-color: null");
		    	    	middleNameTextEdit.setStyle("-fx-border-color: null");
		    	    	lastNameTextEdit.setStyle("-fx-border-color: null");
		    	    	phoneTextEdit.setStyle("-fx-border-color: null");
			    		addressLine1TextEdit.setStyle("-fx-border-color: null");
		    	    	addressLine2TextEdit.setStyle("-fx-border-color: null");
		    	    	cityTextEdit.setStyle("-fx-border-color: null");
		    	    	stateTextEdit.setStyle("-fx-border-color: null");
		    	    	zipTextEdit.setStyle("-fx-border-color: null");
		    	    	  
			    		Person p = new Person();
			    		p.setFirstName(firstNameTextEdit.getText());
			    		p.setLastName(lastNameTextEdit.getText());
			    		p.setMiddleName(middleNameTextEdit.getText());
			    		p.getContactInfo().setPhoneNo(phoneTextEdit.getText());
			    		p.getContactInfo().setAddressLine1(addressLine1TextEdit.getText());
			    		p.getContactInfo().setAddressLine2(addressLine2TextEdit.getText());
			    		p.getContactInfo().setCity(cityTextEdit.getText());
			    		p.getContactInfo().setState(stateTextEdit.getText());
			    		p.getContactInfo().setZipCode(zipTextEdit.getText());
			    		
			    		 firstNameValidationMessageLabel.setText("");
			    		 lastNameValidationMessageLabel.setText("");
			    		 middleNameValidationMessageLabel.setText("");
			    		 addressLine1ValidationMessageLabel.setText("");
			    		 addressLine2ValidationMessageLabel.setText("");
			    		 phoneValidationMessageLabel.setText("");
			    		 cityValidationMessageLabel.setText("");
			    		 stateValidationMessageLabel.setText("");
			    		 zipCodeValidationMessageLabel.setText("");
			    		 
			    		
			    			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
				    		Validator validator = factory.getValidator();
			    		
			    		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(p);
			    		 if (!constraintViolations.isEmpty())
			    		 {
			    			 editStatus.setText("Validation error");
			    			 editStatus.setTextFill(Color.RED);
			    			 for(ConstraintViolation<Person> constraintViolation :constraintViolations )
					    		{
			    				 String fieldPath = constraintViolation.getPropertyPath().toString();
					    		 if(fieldPath.equals("firstName"))
					    		 {
					    			 firstNameTextEdit.setStyle("-fx-border-color: red");
					    			 firstNameValidationMessageLabel.setText("1");
					    		 }
					    		 else if(fieldPath.equals("lastName"))
					    		 {
					    			 lastNameTextEdit.setStyle("-fx-border-color: red");
					    			 lastNameValidationMessageLabel.setText("2");
					    		 }
					    		 else if(fieldPath.equals("middleName"))
					    		 {
					    			 middleNameTextEdit.setStyle("-fx-border-color: red");
					    			 middleNameValidationMessageLabel.setText("1");
					    		 }
					    		 else if (fieldPath.equals("contactInfo.addressLine1"))
					    		 {
					    			 addressLine1TextEdit.setStyle("-fx-border-color: red");
					    			 addressLine1ValidationMessageLabel.setText("1");
					    		 }
					    		 else if(fieldPath.equals("contactInfo.addressLine2"))
					    		 {
					    			 addressLine2TextEdit.setStyle("-fx-border-color: red");
					    			 addressLine2ValidationMessageLabel.setText("1");
					    		 }
					    		 else if(fieldPath.equals("contactInfo.phoneNo"))
					    		 {
					    			 phoneTextEdit.setStyle("-fx-border-color: red");
					    			 phoneValidationMessageLabel.setText("1");
					    		 }
					    		 else if(fieldPath.equals("contactInfo.city"))
					    		 {
					    			 cityTextEdit.setStyle("-fx-border-color: red");
					    			 cityValidationMessageLabel.setText("1");
					    		 }
					    		 else if(fieldPath.equals("contactInfo.state"))
					    		 {
					    			 stateTextEdit.setStyle("-fx-border-color: red");
					    			 stateValidationMessageLabel.setText("1");
					    		 }
					    		 else if (fieldPath.equals("contactInfo.zipCode"))
					    		 {
					    			 zipTextEdit.setStyle("-fx-border-color: red");
					    			 zipCodeValidationMessageLabel.setText("1");
					    		 }
					    		}	 
			    		 }
			    	      else
			    	      { 
			    	    	  editStatus.setText("Successfully edited");
			    	    	  editStatus.setTextFill(Color.GREEN);
			    	      }
			    	           
			    		
			    		
			    	// ((Node)event.getTarget()).setStyle("-fx-border-color: red");
			    	// ((Node)event.getTarget()).setStyle("-fx-border-color: null");
			    	}
			    	});
	         
	    	 addTextLimiter(firstNameTextEdit,20);
	    	 addTextLimiter(middleNameTextEdit,1);
	    	 addTextLimiter(lastNameTextEdit,20);
	    	 addTextLimiter(phoneTextEdit,21);
	    	 addTextLimiter(addressLine1TextEdit,35);
	    	 addTextLimiter(addressLine2TextEdit,35);
	    	 addTextLimiter(cityTextEdit,25);
	    	 addTextLimiter(stateTextEdit,2);
	    	 addTextLimiter(zipTextEdit,9);
	    	 
	         editPane.setContent(gridPaneEdit);
	    	 
	    	 vbox.getChildren().addAll(btn,btn2,viewPane,editPane);
	    	 root.getChildren().add(vbox);
	    	stage.setScene(scene);
	    	stage.show();
	    	
	    }
	    
	    public static void addTextLimiter(final TextField tf, final int maxLength) {
	        tf.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	tf.setStyle("-fx-border-color: null");
	                if (tf.getText().length() > maxLength) {
	                    String s = tf.getText().substring(0, maxLength);
	                    tf.setText(s);
	                }
	            }
	        });
	    }

}
