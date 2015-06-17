package uid.contact.manager.controller;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

import javax.validation.ConstraintViolation;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.enums.Gender;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.service.PersonService;
import uid.contact.manager.service.impl.PersonServiceImpl;
import uid.contact.manager.utility.Utils;
import uid.contact.manager.validator.ApplicationValidatorFactory;
import uid.contact.manager.validator.CustomPersonValidator;
import uid.contact.manager.validator.CustomPersonValidatorImpl;
import uid.contact.manager.view.AddContactView;


public class AddContactController {
	
	private AddContactView addContactView;
	private PersonService personService;
	private CustomPersonValidator customPersonValidator;
	
	public AddContactController(final AddContactView addContactView) throws InstantiationException, IllegalAccessException
	{
		this.addContactView = addContactView;
		personService = (PersonService)ApplicationSingeltonFactory.getInstance(PersonServiceImpl.class);
		customPersonValidator = (CustomPersonValidator)ApplicationSingeltonFactory.getInstance(CustomPersonValidatorImpl.class);
		Map<String,Person> mainDataMap= AppData.mainDataMap.get(Constants.MAIN_DATA_SEEK_KEY);
		addContactView.getButtonMap().get("saveButton").setOnAction(new EventHandler<ActionEvent>(){
			 
            public void handle(ActionEvent t) {
            	setStateBeforeValidation();
            	Person person = new Person();
            	person.setFirstName(addContactView.getTextFieldMap().get("firstNameText").getText());
            	person.setMiddleName(addContactView.getTextFieldMap().get("middleInitialText").getText());
            	person.setLastName(addContactView.getTextFieldMap().get("lastNameText").getText());
            	if(addContactView.getRadioButtonMap().get("maleRadio").isSelected())
            	{
            		person.setGender(Gender.MALE);
            	}
            	else
            	{
            		person.setGender(Gender.FEMALE);
            	}
            	person.getContactInfo().setAddressLine1(addContactView.getTextFieldMap().get("addressLine1Text").getText());
            	person.getContactInfo().setAddressLine2(addContactView.getTextFieldMap().get("addressLine2Text").getText());
            	person.getContactInfo().setCity(addContactView.getTextFieldMap().get("cityText").getText());
            	person.getContactInfo().setPhoneNo(addContactView.getTextFieldMap().get("phoneText").getText());
            	person.getContactInfo().setZipCode(addContactView.getTextFieldMap().get("zipCodeText").getText());
            	person.getContactInfo().setState(addContactView.getTextFieldMap().get("stateText").getText());
            	person.getContactInfo().setCountry(addContactView.getTextFieldMap().get("countryText").getText());
            	person.getContactInfo().setEmail(addContactView.getTextFieldMap().get("emailText").getText());
            	
            	Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validate(person);
	    		 if (!constraintViolations.isEmpty())
	    		 {
	    			 addContactView.getLabelMap().get("addStatus").setText("Validation error");
	    			 addContactView.getLabelMap().get("addStatus").setTextFill(Color.RED);
	    			// Toolkit.getDefaultToolkit().beep();
	    			 for(ConstraintViolation<Person> constraintViolation :constraintViolations )
			    		{
	    				 String fieldPath = constraintViolation.getPropertyPath().toString();
			    		 if(fieldPath.equals("firstName"))
			    		 {
			    			 addContactView.getTextFieldMap().get("firstNameText").setStyle("-fx-border-color: red");
			    			 addContactView.getLabelMap().get("firstName").setText("Please enter letters only");
			    			 addContactView.getLabelMap().get("firstName").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("lastName"))
			    		 {
			    			 addContactView.getTextFieldMap().get("lastNameText").setStyle("-fx-border-color: red");
			    			 addContactView.getLabelMap().get("lastName").setText("Please enter letters only");
			    			 addContactView.getLabelMap().get("lastName").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("middleName"))
			    		 {
			    			 addContactView.getTextFieldMap().get("middleInitialText").setStyle("-fx-border-color: red");
			    			 addContactView.getLabelMap().get("middleName").setText("Please enter letter only");
			    			 addContactView.getLabelMap().get("middleName").setTextFill(Color.RED);
			    		 }
			    		 else if (fieldPath.equals("contactInfo.addressLine1"))
			    		 {
			    			 addContactView.getTextFieldMap().get("addressLine1Text").setStyle("-fx-border-color: red");
			    			 if(addContactView.getTextFieldMap().get("addressLine1Text").getText()==null || addContactView.getTextFieldMap().get("addressLine1Text").getText().equals(""))
			    				 addContactView.getLabelMap().get("contactInfo.addressLine1").setText("Please enter the address Line 1");
			    			 else
			    				 addContactView.getLabelMap().get("contactInfo.addressLine1").setText("! ' - _ @ # . / : , | are allowed");
			    			 addContactView.getLabelMap().get("contactInfo.addressLine1").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("contactInfo.addressLine2"))
			    		 {
			    			 addContactView.getTextFieldMap().get("addressLine2Text").setStyle("-fx-border-color: red");
			    			 addContactView.getLabelMap().get("contactInfo.addressLine2").setText("! ' - _ @ # . / : , | are allowed");
			    			 addContactView.getLabelMap().get("contactInfo.addressLine2").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("contactInfo.phoneNo"))
			    		 {
			    			 addContactView.getTextFieldMap().get("phoneText").setStyle("-fx-border-color: red");
			    			 addContactView.getLabelMap().get("contactInfo.phoneNo").setText("Please enter Digits only(min 4)");
			    			 addContactView.getLabelMap().get("contactInfo.phoneNo").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("contactInfo.city"))
			    		 {
			    			 addContactView.getTextFieldMap().get("cityText").setStyle("-fx-border-color: red");
			    			 addContactView.getLabelMap().get("contactInfo.city").setText("Please enter letters only");
			    			 addContactView.getLabelMap().get("contactInfo.city").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("contactInfo.state"))
			    		 {
			    			 addContactView.getTextFieldMap().get("stateText").setStyle("-fx-border-color: red");
			    			 addContactView.getLabelMap().get("contactInfo.state").setText("Please enter 2 letters");
			    			 addContactView.getLabelMap().get("contactInfo.state").setTextFill(Color.RED);
			    		 }
			    		 else if (fieldPath.equals("contactInfo.zipCode"))
			    		 {
			    			 addContactView.getTextFieldMap().get("zipCodeText").setStyle("-fx-border-color: red");
			    			 addContactView.getLabelMap().get("contactInfo.zipCode").setText("Please enter Digits only");
			    			 addContactView.getLabelMap().get("contactInfo.zipCode").setTextFill(Color.RED);
			    		 }
			    		 else if (fieldPath.equals("contactInfo.country"))
			    		 {
			    			 addContactView.getTextFieldMap().get("countryText").setStyle("-fx-border-color: red");
			    			 addContactView.getLabelMap().get("contactInfo.country").setText("Please enter Digits only");
			    			 addContactView.getLabelMap().get("contactInfo.country").setTextFill(Color.RED);
			    		 }
			    		 else if (fieldPath.equals("contactInfo.email"))
			    		 {
			    			 addContactView.getTextFieldMap().get("emailText").setStyle("-fx-border-color: red");
			    			 addContactView.getLabelMap().get("contactInfo.email").setText("Please enter Digits only");
			    			 addContactView.getLabelMap().get("contactInfo.email").setTextFill(Color.RED);
			    		 }
			    		}	 
	    		 }
	    	      else
	    	      { 
	    	    	 try {
	    	    		 if(customPersonValidator.validateNameAsKey(person))
	    	    		 {
	    	    			 personService.save(person);
	    	    			 addContactView.getLabelMap().get("addStatus").setText("Successfully Added");
	    	    			 addContactView.getLabelMap().get("addStatus").setTextFill(Color.GREEN);
	  				
	    	    			 addContactView.getContactList().clear();
	    	    			 addContactView.getContactList().addAll(Utils.convertMapIntoArrayList(AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY)));
	    	    			 addContactView.getRecentContactList().clear();
	    	    			 addContactView.getRecentContactList().addAll(Utils.convertMapIntoArrayList(AppData.mainDataMap.get(Constants.MAIN_DATA_RECENT_KEY)));
	    	    		 }
	    	    		 else
	    	    		 {
	    	    			 addContactView.getLabelMap().get("addStatus").setText("User exists with same name. Change the name.");
		  					 addContactView.getLabelMap().get("addStatus").setTextFill(Color.RED);
		  					 //Toolkit.getDefaultToolkit().beep();
	    	    		 }
	  				} catch (FileNotFoundException e) {
	  					 addContactView.getLabelMap().get("addStatus").setText("Technical error. Contact not saved. Try Again.");
	  					 addContactView.getLabelMap().get("addStatus").setTextFill(Color.RED);
	  					// Toolkit.getDefaultToolkit().beep();
	  					e.printStackTrace();
	  				} catch (IOException e) {
	  					 addContactView.getLabelMap().get("addStatus").setText("Technical error. Contact not saved. Try Again.");
	  					 addContactView.getLabelMap().get("addStatus").setTextFill(Color.RED);
	  					// Toolkit.getDefaultToolkit().beep();
	  					e.printStackTrace();
	  				}
	    	    	  
	    	      }
            	
            }
        });
		
	}
	
	private void setStateBeforeValidation()
	{
		addContactView.getTextFieldMap().get("firstNameText").setStyle("-fx-border-color: null");
		addContactView.getTextFieldMap().get("lastNameText").setStyle("-fx-border-color: null");
		addContactView.getTextFieldMap().get("middleInitialText").setStyle("-fx-border-color: null");
		addContactView.getTextFieldMap().get("phoneText").setStyle("-fx-border-color: null");
		addContactView.getTextFieldMap().get("addressLine1Text").setStyle("-fx-border-color: null");
		addContactView.getTextFieldMap().get("addressLine2Text").setStyle("-fx-border-color: null");
		addContactView.getTextFieldMap().get("cityText").setStyle("-fx-border-color: null");
		addContactView.getTextFieldMap().get("stateText").setStyle("-fx-border-color: null");
		addContactView.getTextFieldMap().get("zipCodeText").setStyle("-fx-border-color: null");
		addContactView.getTextFieldMap().get("emailText").setStyle("-fx-border-color: null");
		addContactView.getTextFieldMap().get("countryText").setStyle("-fx-border-color: null");
    	
		addContactView.getLabelMap().get("firstName").setText("");
		addContactView.getLabelMap().get("lastName").setText("");
		addContactView.getLabelMap().get("middleName").setText("");
		addContactView.getLabelMap().get("contactInfo.addressLine1").setText("");
		addContactView.getLabelMap().get("contactInfo.phoneNo").setText("");
		addContactView.getLabelMap().get("contactInfo.addressLine2").setText("");
		addContactView.getLabelMap().get("contactInfo.city").setText("");
		addContactView.getLabelMap().get("contactInfo.state").setText("");
		addContactView.getLabelMap().get("contactInfo.zipCode").setText("");
		addContactView.getLabelMap().get("contactInfo.country").setText("");
		addContactView.getLabelMap().get("contactInfo.email").setText("");
		 
	}
	

}
