package uid.contact.manager.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.validator.ApplicationValidatorFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class EditContactView {
	private TitledPane editPane;
	private Scene scene;
	private VBox vbox;
	private Map<String,Label> labelMap;
	private Map<String,Button> buttonMap;
	private Map<String,Double> progressWeight;


	private Map<String,TextField> textFieldMap;
	private Map<String,RadioButton> radioButtonMap;
	private ObservableList<Person> contactList;
	private ObservableList<Person> recentContactList;
	private TitledPane addPane;
	
	public EditContactView(Scene scene,VBox vbox,ObservableList<Person> contactList,ObservableList<Person> recentContactList)
	{
		textFieldMap= new HashMap<String,TextField>();
		radioButtonMap = new HashMap<String,RadioButton>();
		buttonMap = new  HashMap<String,Button>();
		progressWeight =  new HashMap<String,Double>(); 
		this.contactList = contactList;
		this.recentContactList= recentContactList;
		labelMap=new HashMap<String,Label>();
		this.vbox =vbox;
		
		 editPane = new TitledPane();
		 editPane.setExpanded(false);
		 editPane.expandedProperty().addListener(new ChangeListener<Boolean>() {
	            @Override
	            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	               if(newValue)
	               {
	            	   addPane.setExpanded(false);
	               }
	               else
	               {
	            	   addPane.setExpanded(true);
	               }
	            }
	        });
		 editPane.setText("EDIT CONTACT");
    	 editPane.prefWidthProperty().bind(vbox.widthProperty());
    	// Tooltip tooltip2 = new Tooltip("Click edit button in the contact lists to edit the contact details of other person");
    	// editPane.setTooltip(tooltip2);
    	// editPane.prefWidthProperty().bind(scene.widthProperty());
    	 final GridPane gridPaneEdit = new GridPane();
    	 gridPaneEdit.setPadding(new Insets(5));
    	 gridPaneEdit.setHgap(20);
    	 gridPaneEdit.setVgap(8);
    	 Label firstNameLabelEdit = new Label("First Name*           :");
    	 final TextField firstNameTextEdit = new TextField();
    	 firstNameTextEdit.setPrefWidth(30 * 7);
    	 firstNameTextEdit.setDisable(true);
    	 firstNameTextEdit.setId("firstName");
 		 progressWeight.put("firstName", 0d);
    	 Label middleNameLabelEdit = new Label("Middle Initial        :");
    	 final TextField middleNameTextEdit = new TextField();
    	 middleNameTextEdit.setId("middleName");
    	 middleNameTextEdit.setDisable(true);
 		 progressWeight.put("middleName", 0d);
    	 Label lastNameLabelEdit = new Label("Last Name*           :");
    	 final TextField lastNameTextEdit = new TextField();
    	 lastNameTextEdit.setId("lastName");
    	 lastNameTextEdit.setDisable(true);
 		 progressWeight.put("lastName", 0d);
    	 GridPane.setHalignment(firstNameLabelEdit, HPos.LEFT);
    	 gridPaneEdit.add(firstNameLabelEdit, 0 ,1);
         GridPane.setHalignment(firstNameTextEdit, HPos.LEFT);
         gridPaneEdit.add(firstNameTextEdit, 1,1);
         GridPane.setHalignment(middleNameLabelEdit, HPos.LEFT);
         gridPaneEdit.add(middleNameLabelEdit, 0 ,2);
         GridPane.setHalignment(middleNameTextEdit, HPos.LEFT);
         gridPaneEdit.add(middleNameTextEdit, 1 ,2);
         GridPane.setHalignment(lastNameLabelEdit, HPos.LEFT);
         gridPaneEdit.add(lastNameLabelEdit, 0 ,3);
         GridPane.setHalignment(lastNameTextEdit, HPos.LEFT);
         gridPaneEdit.add(lastNameTextEdit, 1 ,3);
         
         Label genderLabelEdit = new Label("Gender*                 :");
    	 final ToggleGroup groupEdit = new ToggleGroup();
     	 final RadioButton maleRadioEdit = new RadioButton("Male");
     	 maleRadioEdit.setToggleGroup(groupEdit);
     	 maleRadioEdit.setSelected(true);
     	 maleRadioEdit.setDisable(true);
     	 final RadioButton femaleRadioEdit = new RadioButton("FEMALE");
     	 femaleRadioEdit.setDisable(true);
     	 femaleRadioEdit.setToggleGroup(groupEdit);
     	 radioButtonMap.put("male", maleRadioEdit);
     	 radioButtonMap.put("female",femaleRadioEdit);
    	 GridPane.setHalignment(genderLabelEdit, HPos.LEFT);
    	 gridPaneEdit.add(genderLabelEdit, 0 ,4);
         GridPane.setHalignment(maleRadioEdit, HPos.LEFT);
         gridPaneEdit.add(maleRadioEdit, 1 ,4);
         GridPane.setHalignment(femaleRadioEdit, HPos.RIGHT);
         gridPaneEdit.add(femaleRadioEdit, 1 ,4);
    	
    	 Label phoneLabelEdit = new Label("Phone*                  :");
    	 final  TextField phoneTextEdit = new TextField();
    	 phoneTextEdit.setDisable(true);
    	 phoneTextEdit.setId("contactInfo.phoneNo");
 		 progressWeight.put("contactInfo.phoneNo", 0d);
    	 GridPane.setHalignment(phoneLabelEdit, HPos.LEFT);
    	 gridPaneEdit.add(phoneLabelEdit, 0 ,5);
         GridPane.setHalignment(phoneTextEdit, HPos.LEFT);
         gridPaneEdit.add(phoneTextEdit, 1 ,5);
         
         Label emailLabelEdit = new Label("Email*                  :");
    	 final  TextField emailTextEdit = new TextField();
    	 emailTextEdit.setDisable(true);
    	 emailTextEdit.setId("contactInfo.email");
 		 progressWeight.put("contactInfo.email", 0d);
    	 GridPane.setHalignment(emailLabelEdit, HPos.LEFT);
    	 gridPaneEdit.add(emailLabelEdit, 0 ,6);
         GridPane.setHalignment(emailTextEdit, HPos.LEFT);
         gridPaneEdit.add(emailTextEdit, 1 ,6);
    	
    	 Label addressLine1LabelEdit = new Label("Address Line1*      :");
    	 final TextField addressLine1TextEdit= new TextField();
    	 addressLine1TextEdit.setDisable(true);
    	 addressLine1TextEdit.setId("contactInfo.addressLine1");
 		 progressWeight.put("contactInfo.addressLine1", 0d);
    	 GridPane.setHalignment(addressLine1LabelEdit, HPos.LEFT);
    	 gridPaneEdit.add(addressLine1LabelEdit, 0 ,7);
         GridPane.setHalignment(addressLine1TextEdit, HPos.LEFT);
         gridPaneEdit.add(addressLine1TextEdit, 1 ,7);
         Label addressLine2LabelEdit = new Label("Address Line2       :");
         final TextField addressLine2TextEdit= new TextField();
         addressLine2TextEdit.setDisable(true);
         addressLine2TextEdit.setId("contactInfo.addressLine2");
 		 progressWeight.put("contactInfo.addressLine2", 0d);
    	 GridPane.setHalignment(addressLine2LabelEdit, HPos.LEFT);
    	 gridPaneEdit.add(addressLine2LabelEdit, 0 ,8);
         GridPane.setHalignment(addressLine2TextEdit, HPos.LEFT);
         gridPaneEdit.add(addressLine2TextEdit, 1 ,8);
    	 Label cityLabelEdit = new Label("City*                      :");
    	 final TextField cityTextEdit = new TextField();
    	 cityTextEdit.setDisable(true);
    	 cityTextEdit.setId("contactInfo.city");
 		 progressWeight.put("contactInfo.city", 0d);
    	 GridPane.setHalignment(cityLabelEdit, HPos.LEFT);
    	 gridPaneEdit.add(cityLabelEdit, 0 ,9);
         GridPane.setHalignment(cityTextEdit, HPos.LEFT);
         gridPaneEdit.add(cityTextEdit, 1 ,9);
    	
    	 Label stateLabelEdit = new Label("State*                    :");
    	 final TextField stateTextEdit = new TextField();
    	 stateTextEdit.setDisable(true);
    	 stateTextEdit.setId("contactInfo.state");
 		 progressWeight.put("contactInfo.state", 0d);
    	 GridPane.setHalignment(stateLabelEdit, HPos.LEFT);
    	 gridPaneEdit.add(stateLabelEdit, 0 ,10);
         GridPane.setHalignment(stateTextEdit, HPos.LEFT);
         gridPaneEdit.add(stateTextEdit, 1 ,10);
    	 Label zipLabelEdit = new Label("Zip Code*              :");
    	 final TextField zipTextEdit = new TextField();
    	 zipTextEdit.setDisable(true);
    	 zipTextEdit.setId("contactInfo.zipCode");
 		 progressWeight.put("contactInfo.zipCode", 0d);
    	 GridPane.setHalignment(zipLabelEdit, HPos.LEFT);
    	 gridPaneEdit.add(zipLabelEdit, 0 ,11);
         GridPane.setHalignment(zipTextEdit, HPos.LEFT);
         gridPaneEdit.add(zipTextEdit, 1 ,11);
         Label countryLabelEdit = new Label("Country*              :");
         final TextField countryTextEdit = new TextField();
         countryTextEdit.setDisable(true);
         countryTextEdit.setId("contactInfo.country");
 		 progressWeight.put("contactInfo.country", 0d);
    	 GridPane.setHalignment(countryLabelEdit, HPos.LEFT);
    	 gridPaneEdit.add(countryLabelEdit, 0 ,12);
         GridPane.setHalignment(countryTextEdit, HPos.LEFT);
         gridPaneEdit.add(countryTextEdit, 1 ,12);
         
         
         final Label editStatus = new Label("");
    	 GridPane.setHalignment(editStatus, HPos.LEFT);
    	 gridPaneEdit.setColumnSpan(editStatus, 2);
    	 gridPaneEdit.add(editStatus, 1 ,13);
    	 
    	 
    	 final Label firstNameValidationMessageLabel = new Label("");
    	 final Label lastNameValidationMessageLabel = new Label("");
    	 final Label middleNameValidationMessageLabel = new Label("");
    	 final Label phoneValidationMessageLabel = new Label("");
    	 final Label addressLine1ValidationMessageLabel = new Label("");
    	 final Label addressLine2ValidationMessageLabel = new Label("");
    	 final Label cityValidationMessageLabel = new Label("");
    	 final Label stateValidationMessageLabel = new Label("");
    	 final Label zipCodeValidationMessageLabel = new Label("");
    	 final Label countryValidationMessageLabel = new Label("");
    	 final Label emailValidationMessageLabel = new Label("");
    	 GridPane.setHalignment(firstNameValidationMessageLabel, HPos.LEFT);
    	 gridPaneEdit.add(firstNameValidationMessageLabel, 2 ,1);
    	 GridPane.setHalignment(middleNameValidationMessageLabel, HPos.LEFT);
    	 gridPaneEdit.add(middleNameValidationMessageLabel, 2 ,2);
    	 GridPane.setHalignment(lastNameValidationMessageLabel, HPos.LEFT);
    	 gridPaneEdit.add(lastNameValidationMessageLabel, 2 ,3);
    	
    	
    	 GridPane.setHalignment(phoneValidationMessageLabel, HPos.LEFT);
    	 gridPaneEdit.add(phoneValidationMessageLabel, 2 ,5);
    	 GridPane.setHalignment(emailValidationMessageLabel, HPos.LEFT); 
    	 gridPaneEdit.add(emailValidationMessageLabel, 2 ,6);
    	 GridPane.setHalignment(addressLine1ValidationMessageLabel, HPos.LEFT);
    	 gridPaneEdit.add(addressLine1ValidationMessageLabel, 2 ,7);
    	 GridPane.setHalignment(addressLine2ValidationMessageLabel, HPos.LEFT);
    	 gridPaneEdit.add(addressLine2ValidationMessageLabel, 2 ,8);
    	 GridPane.setHalignment(cityValidationMessageLabel, HPos.LEFT);
    	 gridPaneEdit.add(cityValidationMessageLabel, 2 ,9);
    	 GridPane.setHalignment(stateValidationMessageLabel, HPos.LEFT);
    	 gridPaneEdit.add(stateValidationMessageLabel, 2 ,10);
    	 GridPane.setHalignment(zipCodeValidationMessageLabel, HPos.LEFT);
    	 gridPaneEdit.add(zipCodeValidationMessageLabel, 2 ,11);
    	 gridPaneEdit.add(countryValidationMessageLabel, 2 ,12);
    	 GridPane.setHalignment(countryValidationMessageLabel, HPos.LEFT);
    	 
    	 final Label progressLabel = new Label("Progress");
    	 GridPane.setHalignment(progressLabel, HPos.LEFT);
    	 gridPaneEdit.add(progressLabel, 3 ,0);
    	 
    	 /* progress bar*/
    	 	final VBox vBar = new VBox();
    	 	final ProgressBar progBar = new ProgressBar(0);
    	 	progBar.setPrefHeight(0);
    
    	 	//System.out.println("Height" + progBar.getPrefHeight());
    	 	progBar.setStyle(" .progress-bar .track {-fx-padding:10px; -fx-background-insets: 0;-fx-accent: green;  }");
    	 	progBar.setPrefWidth(420);
    	 	//progBar.prefHeightProperty().bind(progBar.widthProperty().subtract(355)); 
    	 	progBar.getTransforms().setAll(
 	            new Translate(0,0),
 	            new Rotate(90, 0, 0)
 	    );
    	 	//progBar.setProgress(0.10);
    	 	vBar.setPadding(new Insets(0, 0, 0, 30));
    	 	vBar.getChildren().add(progBar);
    	 	vBar.setPrefWidth(420);
    	 	 GridPane.setHalignment(vBar, HPos.LEFT);
        	 GridPane.setValignment(vBar, VPos.TOP);
        	 gridPaneEdit.setRowSpan(vBar, 9);
        	 gridPaneEdit.add(vBar, 3 ,1);
        	 
        	
    	 
    	 final Button saveButton = new Button("Save");
    	 saveButton.setDisable(true);
    	 GridPane.setHalignment(saveButton, HPos.LEFT);
    	 gridPaneEdit.add(saveButton, 1 ,14);
    	 
    	 buttonMap.put("save", saveButton);
    	 
    	 Label personalIdentifier = new Label();
    	 
    	 textFieldMap.put("firstName", firstNameTextEdit);
    	 textFieldMap.put("middleName", middleNameTextEdit);
    	 textFieldMap.put("lastName", lastNameTextEdit);
    	 textFieldMap.put("phoneNo", phoneTextEdit);
    	 textFieldMap.put("addressLine1", addressLine1TextEdit);
    	 textFieldMap.put("addressLine2", addressLine2TextEdit);
    	 textFieldMap.put("city", cityTextEdit);
    	 textFieldMap.put("state", stateTextEdit);
    	 textFieldMap.put("zipCode", zipTextEdit);
    	 textFieldMap.put("country", countryTextEdit);
     	 textFieldMap.put("email", emailTextEdit);
    	 
    	 labelMap.put("personalIdentifier", personalIdentifier);
    	 labelMap.put("editStatus", editStatus);
    	 labelMap.put("firstName", firstNameValidationMessageLabel);
    	 labelMap.put("middleName", middleNameValidationMessageLabel);
    	 labelMap.put("lastName", lastNameValidationMessageLabel);
    	 labelMap.put("contactInfo.phoneNo", phoneValidationMessageLabel);
    	 labelMap.put("contactInfo.addressLine1", addressLine1ValidationMessageLabel);
    	 labelMap.put("contactInfo.addressLine2", addressLine2ValidationMessageLabel);
    	 labelMap.put("contactInfo.city", cityValidationMessageLabel);
    	 labelMap.put("contactInfo.state", stateValidationMessageLabel);
    	 labelMap.put("contactInfo.zipCode", zipCodeValidationMessageLabel);
    	 labelMap.put("contactInfo.country", countryValidationMessageLabel);
    	 labelMap.put("contactInfo.email", emailValidationMessageLabel);
    	 
    	/* saveButton.setOnAction(new EventHandler<ActionEvent>() {
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
		    	});*/
         
    	/* addTextLimiter(firstNameTextEdit,20);
    	 addTextLimiter(middleNameTextEdit,1);
    	 addTextLimiter(lastNameTextEdit,20);
    	 addTextLimiter(phoneTextEdit,21);
    	 addTextLimiter(addressLine1TextEdit,35);
    	 addTextLimiter(addressLine2TextEdit,35);
    	 addTextLimiter(cityTextEdit,25);
    	 addTextLimiter(stateTextEdit,2);
    	 addTextLimiter(zipTextEdit,9);*/
    	 
    	 firstNameTextEdit.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	firstNameTextEdit.setStyle("-fx-border-color: null");
	                if (firstNameTextEdit.getText().length() > 20) {
	                    String s = firstNameTextEdit.getText().substring(0, 20);
	                    firstNameTextEdit.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,firstNameTextEdit.getId(),firstNameTextEdit.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	                		messageKey = firstNameTextEdit.getId()+".blank";
	                	
	                		firstNameTextEdit.setStyle("-fx-border-color: red");
	                	getLabelMap().get(firstNameTextEdit.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(firstNameTextEdit.getId()).setTextFill(Color.RED);
		    		 }
	                else
	                {
	                	getLabelMap().get(firstNameTextEdit.getId()).setText("");
	                	firstNameTextEdit.setStyle("-fx-border-color: null");
	                }
	               if(getLabelMap().get(firstNameTextEdit.getId()).getText()!=null && !getLabelMap().get(firstNameTextEdit.getId()).getText().equals(""))
		        	     progressWeight.put(firstNameTextEdit.getId(),0.0);
		        	 else
		        		  progressWeight.put(firstNameTextEdit.getId(),0.1);
		        	 progBar.setProgress(updateProgressBar());
	            }
	        });
		/*firstNameText.focusedProperty().addListener(new ChangeListener<Boolean>()
				 {
				     @Override
				     public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
				     {
				    	 double progress = progBar.getProgress();
			        	 if(getLabelMap().get(firstNameText.getId()).getText()!=null && !getLabelMap().get(firstNameText.getId()).getText().equals(""))
			        	     progressWeight.put(firstNameText.getId(),0.0);
			        	 else
			        		  progressWeight.put(firstNameText.getId(),0.1);
			        	 progBar.setProgress(updateProgressBar());
				         if (newPropertyValue)
				         { 
				     
				         }
				         else
				         {
				        	// double progress = progBar.getProgress();
				        	 if(getLabelMap().get(firstNameText.getId()).getText()!=null && !getLabelMap().get(firstNameText.getId()).getText().equals(""))
				        	     progressWeight.put(firstNameText.getId(),0d);
				        	 else
				        		  progressWeight.put(firstNameText.getId(),0.1);
				        	 progBar.setProgress(updateProgressBar());
				         }
				     }
				 });*/
 	 //addTextLimiter(middleInitialText,1);
 	 middleNameTextEdit.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
						middleNameTextEdit.setStyle("-fx-border-color: null");
	                if (middleNameTextEdit.getText().length() > 1) {
	                    String s = middleNameTextEdit.getText().substring(0, 1);
	                    middleNameTextEdit.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,middleNameTextEdit.getId(),middleNameTextEdit.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = middleNameTextEdit.getId()+".blank";
	                	
	                		middleNameTextEdit.setStyle("-fx-border-color: red");
	                	getLabelMap().get(middleNameTextEdit.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(middleNameTextEdit.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(middleNameTextEdit.getId()).setText("");
	                	middleNameTextEdit.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(middleNameTextEdit.getId()).getText()!=null && !getLabelMap().get(middleNameTextEdit.getId()).getText().equals(""))
	                {  
	                	if(progBar.getProgress()>0.8999)
	                		progressWeight.put(middleNameTextEdit.getId(),-0.1);
	                	else
	                		progressWeight.put(middleNameTextEdit.getId(),0.0);
	                }
		        	 else
		        		  progressWeight.put(middleNameTextEdit.getId(),0.04);
	               /* if(progBar.getProgress()<0.10)
	                	 progressWeight.put(middleNameTextEdit.getId(),0.0);*/
		        	 progBar.setProgress(updateProgressBar());
	            }
	        });
 	 //addTextLimiter(lastNameText,20);
 	 lastNameTextEdit.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	lastNameTextEdit.setStyle("-fx-border-color: null");
	                if (lastNameTextEdit.getText().length() > 20) {
	                    String s = lastNameTextEdit.getText().substring(0, 20);
	                    lastNameTextEdit.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,lastNameTextEdit.getId(),lastNameTextEdit.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = lastNameTextEdit.getId()+".blank";
	                	
	                		lastNameTextEdit.setStyle("-fx-border-color: red");
	                	getLabelMap().get(lastNameTextEdit.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(lastNameTextEdit.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(lastNameTextEdit.getId()).setText("");
	                	lastNameTextEdit.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(lastNameTextEdit.getId()).getText()!=null && !getLabelMap().get(lastNameTextEdit.getId()).getText().equals(""))
		        	     progressWeight.put(lastNameTextEdit.getId(),0.0);
		        	 else
		        		  progressWeight.put(lastNameTextEdit.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
 	 //addTextLimiter(phoneText,21);
 	 phoneTextEdit.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	phoneTextEdit.setStyle("-fx-border-color: null");
	                if (phoneTextEdit.getText().length() > 21) {
	                    String s = phoneTextEdit.getText().substring(0, 21);
	                    phoneTextEdit.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,phoneTextEdit.getId(),phoneTextEdit.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = phoneTextEdit.getId()+".blank";
	                	
	                		phoneTextEdit.setStyle("-fx-border-color: red");
	                	getLabelMap().get(phoneTextEdit.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(phoneTextEdit.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(phoneTextEdit.getId()).setText("");
	                	phoneTextEdit.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(phoneTextEdit.getId()).getText()!=null && !getLabelMap().get(phoneTextEdit.getId()).getText().equals(""))
		        	     progressWeight.put(phoneTextEdit.getId(),0.0);
		        	 else
		        		  progressWeight.put(phoneTextEdit.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
 	// addTextLimiter(addressLine1Text,35);
 	 addressLine1TextEdit.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	addressLine1TextEdit.setStyle("-fx-border-color: null");
	                if (addressLine1TextEdit.getText().length() > 35) {
	                    String s = addressLine1TextEdit.getText().substring(0, 35);
	                    addressLine1TextEdit.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,addressLine1TextEdit.getId(),addressLine1TextEdit.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                	if(addressLine1TextEdit.getText()!=null && addressLine1TextEdit.getText().trim().equals(""))
             			messageKey = addressLine1TextEdit.getId()+".blank";
             		else
             			messageKey = addressLine1TextEdit.getId()+".invalid";  
	                	
	                	addressLine1TextEdit.setStyle("-fx-border-color: red");
	                	getLabelMap().get(addressLine1TextEdit.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(addressLine1TextEdit.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(addressLine1TextEdit.getId()).setText("");
	                	addressLine1TextEdit.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(addressLine1TextEdit.getId()).getText()!=null && !getLabelMap().get(addressLine1TextEdit.getId()).getText().equals(""))
		        	     progressWeight.put(addressLine1TextEdit.getId(),0.0);
		        	 else
		        		  progressWeight.put(addressLine1TextEdit.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
 	// addTextLimiter(addressLine2Text,35);
 	 addressLine2TextEdit.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	addressLine2TextEdit.setStyle("-fx-border-color: null");
	                if (addressLine2TextEdit.getText().length() > 35) {
	                    String s = addressLine2TextEdit.getText().substring(0, 35);
	                    addressLine2TextEdit.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,addressLine2TextEdit.getId(),addressLine2TextEdit.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = addressLine2TextEdit.getId()+".invalid";
	                	
	                		addressLine2TextEdit.setStyle("-fx-border-color: red");
	                	getLabelMap().get(addressLine2TextEdit.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(addressLine2TextEdit.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(addressLine2TextEdit.getId()).setText("");
	                	addressLine2TextEdit.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(addressLine2TextEdit.getId()).getText()!=null && !getLabelMap().get(addressLine2TextEdit.getId()).getText().equals(""))
	                {
	                	if(progBar.getProgress()>0.8999)
	                		progressWeight.put(addressLine2TextEdit.getId(),-0.1);
	                	else
	                		progressWeight.put(addressLine2TextEdit.getId(),0.0);
	                }
		        	 else
		        		  progressWeight.put(addressLine2TextEdit.getId(),0.04);
	                /*if(progBar.getProgress()<0.10)
	                	 progressWeight.put(addressLine2TextEdit.getId(),0.0);*/
	                progBar.setProgress(updateProgressBar());
	            }
	        });
 	// addTextLimiter(cityText,25);
 	 cityTextEdit.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	cityTextEdit.setStyle("-fx-border-color: null");
	                if (cityTextEdit.getText().length() > 25) {
	                    String s = cityTextEdit.getText().substring(0, 25);
	                    cityTextEdit.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,cityTextEdit.getId(),cityTextEdit.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = cityTextEdit.getId()+".blank";
	                	
	                		cityTextEdit.setStyle("-fx-border-color: red");
	                	getLabelMap().get(cityTextEdit.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(cityTextEdit.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(cityTextEdit.getId()).setText("");
	                	cityTextEdit.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(cityTextEdit.getId()).getText()!=null && !getLabelMap().get(cityTextEdit.getId()).getText().equals(""))
		        	     progressWeight.put(cityTextEdit.getId(),0.0);
		        	 else
		        		  progressWeight.put(cityTextEdit.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
 	// addTextLimiter(stateText,2);
 	 stateTextEdit.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	stateTextEdit.setStyle("-fx-border-color: null");
	                if (stateTextEdit.getText().length() > 2) {
	                    String s = stateTextEdit.getText().substring(0, 2);
	                    stateTextEdit.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,stateTextEdit.getId(),stateTextEdit.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = stateTextEdit.getId()+".blank";
	                	
	                		stateTextEdit.setStyle("-fx-border-color: red");
	                	getLabelMap().get(stateTextEdit.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(stateTextEdit.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(stateTextEdit.getId()).setText("");
	                	stateTextEdit.setStyle("-fx-border-color: null");
	                }
	            if(getLabelMap().get(stateTextEdit.getId()).getText()!=null && !getLabelMap().get(stateTextEdit.getId()).getText().equals(""))
	        	     progressWeight.put(stateTextEdit.getId(),0.0);
	        	 else
	        		  progressWeight.put(stateTextEdit.getId(),0.1);
	            progBar.setProgress(updateProgressBar());
	            }
	        });
 	 //addTextLimiter(zipCodeText,9);
 	 zipTextEdit.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	zipTextEdit.setStyle("-fx-border-color: null");
	                if (zipTextEdit.getText().length() > 9) {
	                    String s = zipTextEdit.getText().substring(0, 9);
	                    zipTextEdit.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,zipTextEdit.getId(),zipTextEdit.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = zipTextEdit.getId()+".blank";
	                	
	                		zipTextEdit.setStyle("-fx-border-color: red");
	                	getLabelMap().get(zipTextEdit.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(zipTextEdit.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(zipTextEdit.getId()).setText("");
	                	zipTextEdit.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(zipTextEdit.getId()).getText()!=null && !getLabelMap().get(zipTextEdit.getId()).getText().equals(""))
		        	     progressWeight.put(zipTextEdit.getId(),0.0);
		        	 else
		        		  progressWeight.put(zipTextEdit.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
 	 
 	 emailTextEdit.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	emailTextEdit.setStyle("-fx-border-color: null");
	                if (emailTextEdit.getText().length() > 100) {
	                    String s = emailTextEdit.getText().substring(0, 100);
	                    emailTextEdit.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,emailTextEdit.getId(),emailTextEdit.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = emailTextEdit.getId()+".blank";
	                	
	                		emailTextEdit.setStyle("-fx-border-color: red");
	                	getLabelMap().get(emailTextEdit.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(emailTextEdit.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(emailTextEdit.getId()).setText("");
	                	emailTextEdit.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(emailTextEdit.getId()).getText()!=null && !getLabelMap().get(emailTextEdit.getId()).getText().equals(""))
		        	     progressWeight.put(emailTextEdit.getId(),0.0);
		        	 else
		        		  progressWeight.put(emailTextEdit.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
 	 countryTextEdit.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	countryTextEdit.setStyle("-fx-border-color: null");
	                if (countryTextEdit.getText().length() > 100) {
	                    String s = countryTextEdit.getText().substring(0, 100);
	                    countryTextEdit.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,countryTextEdit.getId(),countryTextEdit.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = countryTextEdit.getId()+".blank";
	                	
	                		countryTextEdit.setStyle("-fx-border-color: red");
	                	getLabelMap().get(countryTextEdit.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(countryTextEdit.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(countryTextEdit.getId()).setText("");
	                	countryTextEdit.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(countryTextEdit.getId()).getText()!=null && !getLabelMap().get(countryTextEdit.getId()).getText().equals(""))
		        	     progressWeight.put(countryTextEdit.getId(),0.0);
		        	 else
		        		  progressWeight.put(countryTextEdit.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
    	 
         editPane.setContent(gridPaneEdit);
         this.vbox.getChildren().add(editPane);
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
	
	
	public Double updateProgressBar()
	{
		Double totalProgress = 0.0;
		for(Map.Entry<String, Double> entry : progressWeight.entrySet())
		{
			totalProgress =totalProgress+entry.getValue();
		}
		if(totalProgress>0.8999)
			totalProgress=1d;
		if(totalProgress==1d)
		{
			getLabelMap().get("editStatus").setText("Click save to edit contact");
			getLabelMap().get("editStatus").setTextFill(Color.GREEN);
		}
		else
		{
			Double roundValue = totalProgress*100;
			getLabelMap().get("editStatus").setText(roundValue.intValue()+ "% completed");
			getLabelMap().get("editStatus").setTextFill(Color.GREEN);
		}
		if(totalProgress<0.1)
		{	
			if(getTextFieldMap().get("middleInitialText").getText()!=null && getTextFieldMap().get("middleInitialText").getText().equals("")
					&& getTextFieldMap().get("addressLine2Text").getText()!=null && getTextFieldMap().get("addressLine2Text").getText().equals(""))
			{	
				totalProgress=0.0;
				getLabelMap().get("addStatus").setText("0% completed");
				getLabelMap().get("addStatus").setTextFill(Color.RED);
			}
			else
			{
				Double roundValue = totalProgress*100;
				getLabelMap().get("addStatus").setText(roundValue.intValue()+ "% completed");
				getLabelMap().get("addStatus").setTextFill(Color.GREEN);
			}
		
		}
		if(totalProgress<0.0)
			totalProgress=0.0;
		return totalProgress;
	}

	

	public TitledPane getAddPane() {
		return addPane;
	}

	public void setAddPane(TitledPane addPane) {
		this.addPane = addPane;
	}

	public TitledPane getEditPane() {
		return editPane;
	}

	public void setEditPane(TitledPane editPane) {
		this.editPane = editPane;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public VBox getVbox() {
		return vbox;
	}

	public void setVbox(VBox vbox) {
		this.vbox = vbox;
	}

	public Map<String, Label> getLabelMap() {
		return labelMap;
	}

	public void setLabelMap(Map<String, Label> labelMap) {
		this.labelMap = labelMap;
	}

	public Map<String, Button> getButtonMap() {
		return buttonMap;
	}

	public void setButtonMap(Map<String, Button> buttonMap) {
		this.buttonMap = buttonMap;
	}

	public Map<String, TextField> getTextFieldMap() {
		return textFieldMap;
	}

	public void setTextFieldMap(Map<String, TextField> textFieldMap) {
		this.textFieldMap = textFieldMap;
	}

	public Map<String, RadioButton> getRadioButtonMap() {
		return radioButtonMap;
	}

	public void setRadioButtonMap(Map<String, RadioButton> radioButtonMap) {
		this.radioButtonMap = radioButtonMap;
	}

	public ObservableList<Person> getContactList() {
		return contactList;
	}

	public void setContactList(ObservableList<Person> contactList) {
		this.contactList = contactList;
	}

	public ObservableList<Person> getRecentContactList() {
		return recentContactList;
	}

	public void setRecentContactList(ObservableList<Person> recentContactList) {
		this.recentContactList = recentContactList;
	}
	public Map<String, Double> getProgressWeight() {
		return progressWeight;
	}

	public void setProgressWeight(Map<String, Double> progressWeight) {
		this.progressWeight = progressWeight;
	}
	
	

}
