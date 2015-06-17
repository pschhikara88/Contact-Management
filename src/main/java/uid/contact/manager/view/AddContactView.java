package uid.contact.manager.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import javax.validation.ConstraintViolation;

import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.validator.ApplicationValidatorFactory;

public class AddContactView {
	
	private TitledPane titlePanel;
	private Map<String,Label> labelMap;
	private Map<String,TextField> textFieldMap;
	private Map<String,Double> progressWeight;
	private Map<String,RadioButton> radioButtonMap;
	private VBox vbox;
	private Scene scene;
	private GridPane gridPane;
	private Map<String,Button> buttonMap;
	private ObservableList<Person> contactList;
	private ObservableList<Person> recentContactList;
	private TitledPane editPane;
	
	public static boolean expandFlagForForm=true;
	
	public AddContactView(Scene scene,VBox vbox,ObservableList<Person> contactList,ObservableList<Person> recentContactList)
	{
		labelMap = new HashMap<String,Label>(); 
		textFieldMap= new HashMap<String,TextField>();
		radioButtonMap = new HashMap<String,RadioButton>();
		progressWeight =  new HashMap<String,Double>(); 
		buttonMap = new  HashMap<String,Button>();
		this.contactList = contactList;
		this.recentContactList= recentContactList;
		this.vbox = vbox;
		this.scene = scene;
		Group formFieldGroup= new Group();
		formFieldGroup.setVisible(false);
		
		 titlePanel = new TitledPane();
		titlePanel.prefWidthProperty().bind(vbox.widthProperty());
		titlePanel.setText("ADD CONTACT (* means Mandatory Fields)");
		
		titlePanel.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               if(newValue)
               {
            	   editPane.setExpanded(false);
               }
               else
               {
            	   editPane.setExpanded(true);
               }
            }
        });
		
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setHgap(10);
		gridPane.setVgap(8);
		/*ColumnConstraints col1 = new ColumnConstraints();
		col1.setPrefWidth(150);
	    ColumnConstraints col2 = new ColumnConstraints();
	    col2.setPrefWidth(150);
	    ColumnConstraints col3 = new ColumnConstraints();
	    col3.setPrefWidth(20);
	    ColumnConstraints col4 = new ColumnConstraints();
	    col4.setPrefWidth(200);
	    
	    gridPane.getColumnConstraints().addAll(col1,col2,col3,col4);*/

		
		final Label phoneLabel = new Label("Phone*                  :");
		final TextField phoneText = new TextField();
		phoneText.setId("contactInfo.phoneNo");
		progressWeight.put("contactInfo.phoneNo", 0d);
		
		
		final Label firstNameLabel = new Label("First Name*           :");
		final TextField firstNameText = new TextField();	
		firstNameText.setId("firstName");
		progressWeight.put("firstName", 0d);
		final Label middleInitialLabel = new Label("Middle Initial         :");
		final TextField middleInitialText = new TextField();
		middleInitialText.setId("middleName");
		progressWeight.put("middleName", 0d);
		final Label lastNameLabel = new Label("Last Name*           :");
		final TextField lastNameText = new TextField();
		lastNameText.setId("lastName");
		progressWeight.put("lastName", 0d);
		final Label genderLabel = new Label("Gender*                :");
    	final ToggleGroup group = new ToggleGroup();
    	final RadioButton maleRadio = new RadioButton("Male");
    	maleRadio.setToggleGroup(group);
    	maleRadio.setSelected(true);
    	final RadioButton femaleRadio = new RadioButton("FEMALE");
    	femaleRadio.setToggleGroup(group);
    	final Label addressLabel = new Label("Address Line1*     :");
    	final TextField addressLine1Text = new TextField();
    	addressLine1Text.setId("contactInfo.addressLine1");
    	progressWeight.put("contactInfo.addressLine1", 0d);
    	final Label addressLabel2 = new Label("Address Line2       :");
    	final TextField addressLine2Text = new TextField();
    	addressLine2Text.setId("contactInfo.addressLine2");
    	progressWeight.put("contactInfo.addressLine2", 0d);
    	//addressLine2Text.setPrefColumnCount(5);
    	final Label cityLabel = new Label("City*                      :");
    	final TextField cityText = new TextField();
    	cityText.setId("contactInfo.city");
    	progressWeight.put("contactInfo.city", 0d);
    	final Label stateLabel = new Label("State*                    :");
    	final TextField stateText = new TextField();
    	stateText.setId("contactInfo.state");
    	progressWeight.put("contactInfo.state", 0d);
    	final Label zipCodeLabel = new Label("Zip Code*             :");
    	final TextField zipCodeText = new TextField();
    	zipCodeText.setId("contactInfo.zipCode");
    	progressWeight.put("contactInfo.zipCode", 0d);
    	final Label countryLabel = new Label("Country*               :");
    	final TextField countryText = new TextField();
    	countryText.setId("contactInfo.country");
    	progressWeight.put("contactInfo.country", 0d);
    	final Label emailLabel = new Label("Email*                   :");
    	final TextField emailText = new TextField();
    	emailText.setId("contactInfo.email");
    	progressWeight.put("contactInfo.email", 0d);
    	final Label addStatus = new Label("");
    	final Label progressLabel = new Label("Progress");
    	
    	
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
    	
	    	
	    	 
	    	 
    	final HBox buttonBox = new HBox();
    	buttonBox.setSpacing(5);
    	
    	final Button saveButton = new Button("Save");
    	/*final Button cancelButton = new Button("Cancel");*/
    	final Button resetButton = new Button("Reset");
    	buttonMap.put("saveButton", saveButton);
    	
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
   	 	vBar.getChildren().add(progBar);
   	 	vBar.setPrefWidth(420);
    	
    	/*cancelButton.setOnAction(new EventHandler<ActionEvent>(){
			 
            public void handle(ActionEvent t) {
            	editPane.setExpanded(true);
            	for(Map.Entry<String, TextField> entry : textFieldMap.entrySet()){
            		entry.getValue().setText("");
            		entry.getValue().setStyle("-fx-border-color: null");
        		}
            	
            	for(Map.Entry<String, Label> entry : labelMap.entrySet()){
            		entry.getValue().setText("");
        		}
            	if(gridPane.getChildren().size()>=3)
            	{	gridPane.getChildren().remove(2,39);
		    		expandFlagForForm= true;
            	}
            }
        });*/
    	resetButton.setOnAction(new EventHandler<ActionEvent>(){
			 
            public void handle(ActionEvent t) {
            	for(Map.Entry<String, TextField> entry : textFieldMap.entrySet()){
            		entry.getValue().setText("");
            		entry.getValue().setStyle("-fx-border-color: null");
        		}
            	
            	for(Map.Entry<String, Label> entry : labelMap.entrySet()){
            		entry.getValue().setText("");
        		}
            	progBar.setProgress(0d);
            	radioButtonMap.get("maleRadio").setSelected(true);
            	
            }
        });
    	buttonBox.getChildren().addAll(saveButton,resetButton);
    	
    	textFieldMap.put("firstNameText", firstNameText);
    	textFieldMap.put("phoneText", phoneText);
    	textFieldMap.put("middleInitialText", middleInitialText);
    	textFieldMap.put("lastNameText", lastNameText);
    	textFieldMap.put("addressLine1Text", addressLine1Text);
    	textFieldMap.put("addressLine2Text", addressLine2Text);
    	textFieldMap.put("cityText", cityText);
    	textFieldMap.put("stateText", stateText);
    	textFieldMap.put("zipCodeText", zipCodeText);
    	textFieldMap.put("countryText", countryText);
    	textFieldMap.put("emailText", emailText);
    	
    	radioButtonMap.put("maleRadio", maleRadio);
    	radioButtonMap.put("femaleRadio", femaleRadio);
    	
    	 labelMap.put("addStatus", addStatus);
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
 
    	 
    	 
    	
    	//formFieldGroup.getChildren().addAll(firstNameLabel,firstNameText,zipCodeLabel);
	
				    		//editPane.setExpanded(false);
				    		GridPane.setHalignment(firstNameText, HPos.LEFT);
				   		 	gridPane.add(firstNameText, 1, 1);
				    		GridPane.setHalignment(firstNameLabel, HPos.LEFT);
				    		gridPane.add(firstNameLabel, 0 ,1);
				        	GridPane.setHalignment(middleInitialLabel, HPos.LEFT);
				        	gridPane.add(middleInitialLabel, 0 ,2);
				        	GridPane.setHalignment(middleInitialText, HPos.LEFT);
				        	gridPane.add(middleInitialText, 1, 2);
				        	GridPane.setHalignment(lastNameLabel, HPos.LEFT);
				        	gridPane.add(lastNameLabel, 0 ,3);
				        	GridPane.setHalignment(lastNameText, HPos.LEFT);
				        	gridPane.add(lastNameText, 1, 3);
				        	GridPane.setHalignment(genderLabel, HPos.LEFT);
				        	gridPane.add(genderLabel, 0 ,4);
				        	GridPane.setHalignment(maleRadio, HPos.LEFT);
				        	gridPane.add(maleRadio, 1 ,4);
				        	GridPane.setHalignment(femaleRadio, HPos.RIGHT);
				        	gridPane.add(femaleRadio, 1 ,4);
				        	GridPane.setHalignment(phoneLabel, HPos.LEFT);
				    		gridPane.add(phoneLabel, 0, 5);
				    		GridPane.setHalignment(phoneText, HPos.LEFT);
				    		gridPane.add(phoneText, 1, 5);
				    		phoneText.setPrefWidth(30 * 7);
				        	GridPane.setHalignment(emailLabel, HPos.LEFT);
				        	gridPane.add(emailLabel, 0 ,6);
				        	GridPane.setHalignment(emailText, HPos.LEFT);
				        	gridPane.add(emailText, 1 ,6);
				        	GridPane.setHalignment(addressLabel, HPos.LEFT);
				        	gridPane.add(addressLabel, 0 ,7);
				        	GridPane.setHalignment(addressLine1Text, HPos.LEFT);
				        	gridPane.add(addressLine1Text, 1 ,7);
				        	GridPane.setHalignment(addressLabel2, HPos.LEFT);
				        	gridPane.add(addressLabel2, 0 ,8);
				        	GridPane.setHalignment(addressLine2Text, HPos.LEFT);
				        	gridPane.add(addressLine2Text, 1 ,8);
				    		GridPane.setHalignment(cityLabel, HPos.LEFT);
				        	gridPane.add(cityLabel, 0 ,9);
				    		GridPane.setHalignment(cityText, HPos.LEFT);
				        	gridPane.add(cityText, 1 ,9);
				    		GridPane.setHalignment(stateLabel, HPos.LEFT);
				        	gridPane.add(stateLabel, 0 ,10);
				    		GridPane.setHalignment(stateText, HPos.LEFT);
				        	gridPane.add(stateText, 1 ,10);
				        	
				    		GridPane.setHalignment(zipCodeLabel, HPos.LEFT);
				        	gridPane.add(zipCodeLabel, 0 ,11);
				    		GridPane.setHalignment(zipCodeText, HPos.LEFT);
				        	gridPane.add(zipCodeText, 1 ,11);
				        	GridPane.setHalignment(countryLabel, HPos.LEFT);
				        	gridPane.add(countryLabel, 0 ,12);
				    		GridPane.setHalignment(countryText, HPos.LEFT);
				        	gridPane.add(countryText, 1 ,12);
				        	
				        	 GridPane.setHalignment(addStatus, HPos.LEFT);
				        	 gridPane.setColumnSpan(addStatus, 2);
				        	 gridPane.add(addStatus, 1 ,13);
				        	
				        	GridPane.setHalignment(buttonBox, HPos.LEFT);
				        	gridPane.add(buttonBox, 1 ,14);
				        	
				        	 GridPane.setHalignment(vBar, HPos.LEFT);
				        	 vBar.setPadding(new Insets(0, 0, 0, 30));
				        	 GridPane.setValignment(vBar, VPos.TOP);
				        	 gridPane.setRowSpan(vBar, 9);
				        	 gridPane.add(vBar, 3 ,1);
				        	 
				        	 GridPane.setHalignment(progressLabel, HPos.LEFT);
				        	 gridPane.add(progressLabel, 3 ,0);
				        	
				        	
				        	 GridPane.setHalignment(firstNameValidationMessageLabel, HPos.LEFT);
				        	 gridPane.add(firstNameValidationMessageLabel, 2 ,1);
				        	 GridPane.setHalignment(middleNameValidationMessageLabel, HPos.LEFT);
				        	 gridPane.add(middleNameValidationMessageLabel, 2 ,2);
				        	 GridPane.setHalignment(lastNameValidationMessageLabel, HPos.LEFT);
				        	 gridPane.add(lastNameValidationMessageLabel,2 ,3);
				        	 GridPane.setHalignment(phoneValidationMessageLabel, HPos.LEFT);
				        	 gridPane.add(phoneValidationMessageLabel,2 ,5);
				        	 GridPane.setHalignment(emailValidationMessageLabel, HPos.LEFT);
				        	 gridPane.add(emailValidationMessageLabel, 2 ,6);
				        	 GridPane.setHalignment(addressLine1ValidationMessageLabel, HPos.LEFT);
				        	 gridPane.add(addressLine1ValidationMessageLabel, 2 ,7);
				        	 GridPane.setHalignment(addressLine2ValidationMessageLabel, HPos.LEFT);
				        	 gridPane.add(addressLine2ValidationMessageLabel,2,8);
				        	 GridPane.setHalignment(cityValidationMessageLabel, HPos.LEFT);
				        	 gridPane.add(cityValidationMessageLabel, 2 ,9);
				        	 GridPane.setHalignment(stateValidationMessageLabel, HPos.LEFT);
				        	 gridPane.add(stateValidationMessageLabel, 2 ,10);
				        	 GridPane.setHalignment(zipCodeValidationMessageLabel, HPos.LEFT);
				        	 gridPane.add(zipCodeValidationMessageLabel, 2 ,11);
				        	 GridPane.setHalignment(countryValidationMessageLabel, HPos.LEFT);
				        	 gridPane.add(countryValidationMessageLabel, 2 ,12);
				        
		
		 //addTextLimiter(firstNameText,20);
		 firstNameText.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	firstNameText.setStyle("-fx-border-color: null");
	                if (firstNameText.getText().length() > 20) {
	                    String s = firstNameText.getText().substring(0, 20);
	                    firstNameText.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,firstNameText.getId(),firstNameText.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	                		messageKey = firstNameText.getId()+".blank";
	                	
	                		firstNameText.setStyle("-fx-border-color: red");
	                	getLabelMap().get(firstNameText.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(firstNameText.getId()).setTextFill(Color.RED);
		    		 }
	                else
	                {
	                	getLabelMap().get(firstNameText.getId()).setText("");
	                	firstNameText.setStyle("-fx-border-color: null");
	                }
	               if(getLabelMap().get(firstNameText.getId()).getText()!=null && !getLabelMap().get(firstNameText.getId()).getText().equals(""))
		        	     progressWeight.put(firstNameText.getId(),0.0);
		        	 else
		        		  progressWeight.put(firstNameText.getId(),0.1);
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
    	 middleInitialText.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	middleInitialText.setStyle("-fx-border-color: null");
	                if (middleInitialText.getText().length() > 1) {
	                    String s = middleInitialText.getText().substring(0, 1);
	                    middleInitialText.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,middleInitialText.getId(),middleInitialText.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = middleInitialText.getId()+".blank";
	                	
	                		middleInitialText.setStyle("-fx-border-color: red");
	                	getLabelMap().get(middleInitialText.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(middleInitialText.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(middleInitialText.getId()).setText("");
	                	middleInitialText.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(middleInitialText.getId()).getText()!=null && !getLabelMap().get(middleInitialText.getId()).getText().equals(""))
	                {  
	                	if(progBar.getProgress()>0.8999)
	                		progressWeight.put(middleInitialText.getId(),-0.1);
	                	else
	                		progressWeight.put(middleInitialText.getId(),0.0);
	                }
		        	 else
		        		  progressWeight.put(middleInitialText.getId(),0.04);
	               /* if(progBar.getProgress()<0.10)
	                	 progressWeight.put(middleInitialText.getId(),0.0);*/
		        	 progBar.setProgress(updateProgressBar());
	            }
	        });
    	 //addTextLimiter(lastNameText,20);
    	 lastNameText.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	lastNameText.setStyle("-fx-border-color: null");
	                if (lastNameText.getText().length() > 20) {
	                    String s = lastNameText.getText().substring(0, 20);
	                    lastNameText.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,lastNameText.getId(),lastNameText.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = lastNameText.getId()+".blank";
	                	
	                		lastNameText.setStyle("-fx-border-color: red");
	                	getLabelMap().get(lastNameText.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(lastNameText.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(lastNameText.getId()).setText("");
	                	lastNameText.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(lastNameText.getId()).getText()!=null && !getLabelMap().get(lastNameText.getId()).getText().equals(""))
		        	     progressWeight.put(lastNameText.getId(),0.0);
		        	 else
		        		  progressWeight.put(lastNameText.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
    	 //addTextLimiter(phoneText,21);
    	 phoneText.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	phoneText.setStyle("-fx-border-color: null");
	                if (phoneText.getText().length() > 21) {
	                    String s = phoneText.getText().substring(0, 21);
	                    phoneText.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,phoneText.getId(),phoneText.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = phoneText.getId()+".blank";
	                	
	                		phoneText.setStyle("-fx-border-color: red");
	                	getLabelMap().get(phoneText.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(phoneText.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(phoneText.getId()).setText("");
	                	phoneText.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(phoneText.getId()).getText()!=null && !getLabelMap().get(phoneText.getId()).getText().equals(""))
		        	     progressWeight.put(phoneText.getId(),0.0);
		        	 else
		        		  progressWeight.put(phoneText.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
    	// addTextLimiter(addressLine1Text,35);
    	 addressLine1Text.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	addressLine1Text.setStyle("-fx-border-color: null");
	                if (addressLine1Text.getText().length() > 35) {
	                    String s = addressLine1Text.getText().substring(0, 35);
	                    addressLine1Text.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,addressLine1Text.getId(),addressLine1Text.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                	if(addressLine1Text.getText()!=null && addressLine1Text.getText().trim().equals(""))
                			messageKey = addressLine1Text.getId()+".blank";
                		else
                			messageKey = addressLine1Text.getId()+".invalid";  
	                	
	                	addressLine1Text.setStyle("-fx-border-color: red");
	                	getLabelMap().get(addressLine1Text.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(addressLine1Text.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(addressLine1Text.getId()).setText("");
	                	addressLine1Text.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(addressLine1Text.getId()).getText()!=null && !getLabelMap().get(addressLine1Text.getId()).getText().equals(""))
		        	     progressWeight.put(addressLine1Text.getId(),0.0);
		        	 else
		        		  progressWeight.put(addressLine1Text.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
    	// addTextLimiter(addressLine2Text,35);
    	 addressLine2Text.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	addressLine2Text.setStyle("-fx-border-color: null");
	                if (addressLine2Text.getText().length() > 35) {
	                    String s = addressLine2Text.getText().substring(0, 35);
	                    addressLine2Text.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,addressLine2Text.getId(),addressLine2Text.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = addressLine2Text.getId()+".invalid";
	                	
	                		addressLine2Text.setStyle("-fx-border-color: red");
	                	getLabelMap().get(addressLine2Text.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(addressLine2Text.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(addressLine2Text.getId()).setText("");
	                	addressLine2Text.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(addressLine2Text.getId()).getText()!=null && !getLabelMap().get(addressLine2Text.getId()).getText().equals(""))
	                {
	                	if(progBar.getProgress()>0.8999)
	                		progressWeight.put(addressLine2Text.getId(),-0.1);
	                	else
	                		progressWeight.put(addressLine2Text.getId(),0.0);
	                }
		        	 else
		        		  progressWeight.put(addressLine2Text.getId(),0.04);
	               /* if(progBar.getProgress()<0.10)
	                	 progressWeight.put(addressLine2Text.getId(),0.0);*/
	                progBar.setProgress(updateProgressBar());
	            }
	        });
    	// addTextLimiter(cityText,25);
    	 cityText.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	cityText.setStyle("-fx-border-color: null");
	                if (cityText.getText().length() > 25) {
	                    String s = cityText.getText().substring(0, 25);
	                    cityText.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,cityText.getId(),cityText.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = cityText.getId()+".blank";
	                	
	                		cityText.setStyle("-fx-border-color: red");
	                	getLabelMap().get(cityText.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(cityText.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(cityText.getId()).setText("");
	                	cityText.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(cityText.getId()).getText()!=null && !getLabelMap().get(cityText.getId()).getText().equals(""))
		        	     progressWeight.put(cityText.getId(),0.0);
		        	 else
		        		  progressWeight.put(cityText.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
    	// addTextLimiter(stateText,2);
    	 stateText.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	stateText.setStyle("-fx-border-color: null");
	                if (stateText.getText().length() > 2) {
	                    String s = stateText.getText().substring(0, 2);
	                    stateText.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,stateText.getId(),stateText.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = stateText.getId()+".blank";
	                	
	                		stateText.setStyle("-fx-border-color: red");
	                	getLabelMap().get(stateText.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(stateText.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(stateText.getId()).setText("");
	                	stateText.setStyle("-fx-border-color: null");
	                }
	            if(getLabelMap().get(stateText.getId()).getText()!=null && !getLabelMap().get(stateText.getId()).getText().equals(""))
	        	     progressWeight.put(stateText.getId(),0.0);
	        	 else
	        		  progressWeight.put(stateText.getId(),0.1);
	            progBar.setProgress(updateProgressBar());
	            }
	        });
    	 //addTextLimiter(zipCodeText,9);
    	 zipCodeText.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	zipCodeText.setStyle("-fx-border-color: null");
	                if (zipCodeText.getText().length() > 9) {
	                    String s = zipCodeText.getText().substring(0, 9);
	                    zipCodeText.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,zipCodeText.getId(),zipCodeText.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = zipCodeText.getId()+".blank";
	                	
	                		zipCodeText.setStyle("-fx-border-color: red");
	                	getLabelMap().get(zipCodeText.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(zipCodeText.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(zipCodeText.getId()).setText("");
	                	zipCodeText.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(zipCodeText.getId()).getText()!=null && !getLabelMap().get(zipCodeText.getId()).getText().equals(""))
		        	     progressWeight.put(zipCodeText.getId(),0.0);
		        	 else
		        		  progressWeight.put(zipCodeText.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
    	 
    	 emailText.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	emailText.setStyle("-fx-border-color: null");
	                if (emailText.getText().length() > 100) {
	                    String s = emailText.getText().substring(0, 100);
	                    emailText.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,emailText.getId(),emailText.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = emailText.getId()+".blank";
	                	
	                		emailText.setStyle("-fx-border-color: red");
	                	getLabelMap().get(emailText.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(emailText.getId()).setTextFill(Color.RED);;
		    		 }
	                else
	                {
	                	getLabelMap().get(emailText.getId()).setText("");
	                	emailText.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(emailText.getId()).getText()!=null && !getLabelMap().get(emailText.getId()).getText().equals(""))
		        	     progressWeight.put(emailText.getId(),0.0);
		        	 else
		        		  progressWeight.put(emailText.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
    	 countryText.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            	countryText.setStyle("-fx-border-color: null");
	                if (countryText.getText().length() > 100) {
	                    String s = countryText.getText().substring(0, 100);
	                    countryText.setText(s);
	                }
	                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,countryText.getId(),countryText.getText());
	                String messageKey = null;
	                if (!constraintViolations.isEmpty())
		    		 {
	         
	                		messageKey = countryText.getId()+".blank";
	                	
	                		countryText.setStyle("-fx-border-color: red");
	                	getLabelMap().get(countryText.getId()).setText(AppData.configPropertiesMap.get(messageKey));
	                	getLabelMap().get(countryText.getId()).setTextFill(Color.RED);
		    		 }
	                else
	                {
	                	getLabelMap().get(countryText.getId()).setText("");
	                	countryText.setStyle("-fx-border-color: null");
	                }
	                if(getLabelMap().get(countryText.getId()).getText()!=null && !getLabelMap().get(countryText.getId()).getText().equals(""))
		        	     progressWeight.put(countryText.getId(),0.0);
		        	 else
		        		  progressWeight.put(countryText.getId(),0.1);
	                progBar.setProgress(updateProgressBar());
	            }
	        });
    	 
		titlePanel.setContent(gridPane);
		this.vbox.getChildren().add(titlePanel);
	}

	public TitledPane getTitlePanel() {
		return titlePanel;
	}

	public void setTitlePanel(TitledPane titlePanel) {
		this.titlePanel = titlePanel;
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

	public VBox getVbox() {
		return vbox;
	}

	public void setVbox(VBox vbox) {
		this.vbox = vbox;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}

	public Map<String, Button> getButtonMap() {
		return buttonMap;
	}

	public void setButtonMap(Map<String, Button> buttonMap) {
		this.buttonMap = buttonMap;
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

	public Map<String, Label> getLabelMap() {
		return labelMap;
	}

	public void setLabelMap(Map<String, Label> labelMap) {
		this.labelMap = labelMap;
	}
	
	/*public void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
            	tf.setStyle("-fx-border-color: null");
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
                Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validateValue(Person.class,tf.getId(),tf.getText());
                String messageKey = null;
                if (!constraintViolations.isEmpty())
	    		 {
                	if(tf.getId().contains("address"))
                	{	if(tf.getText()!=null && tf.getText().trim().equals(""))
                			messageKey = tf.getId()+".blank";
                		else
                			messageKey = tf.getId()+".invalid";            		
                	}
                	else
                		messageKey = tf.getId()+".blank";
                	
                	tf.setStyle("-fx-border-color: red");
                	getLabelMap().get(tf.getId()).setText(AppData.configPropertiesMap.get(messageKey));
                	getLabelMap().get(tf.getId()).setTextFill(Color.RED);;
	    		 }
                else
                {
                	getLabelMap().get(tf.getId()).setText("");
                	tf.setStyle("-fx-border-color: null");
                }
            }
        });
    }*/
	
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
			getLabelMap().get("addStatus").setText("100% completed. Click save to add contact");
			getLabelMap().get("addStatus").setTextFill(Color.GREEN);
		}
		else
		{
			Double roundValue = totalProgress*100;
			getLabelMap().get("addStatus").setText(roundValue.intValue()+ "% completed");
			getLabelMap().get("addStatus").setTextFill(Color.GREEN);
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

	public TitledPane getEditPane() {
		return editPane;
	}

	public void setEditPane(TitledPane editPane) {
		this.editPane = editPane;
	}
	
	
	

}
