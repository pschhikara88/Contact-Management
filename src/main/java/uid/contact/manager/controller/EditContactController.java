package uid.contact.manager.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import javax.validation.ConstraintViolation;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.enums.Gender;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.service.DataService;
import uid.contact.manager.service.PersonService;
import uid.contact.manager.service.impl.DataServiceImpl;
import uid.contact.manager.service.impl.PersonServiceImpl;
import uid.contact.manager.utility.Utils;
import uid.contact.manager.validator.ApplicationValidatorFactory;
import uid.contact.manager.validator.CustomPersonValidator;
import uid.contact.manager.validator.CustomPersonValidatorImpl;
import uid.contact.manager.view.EditContactView;

public class EditContactController {
	
	private EditContactView editContactView;
	private PersonService personService;
	private CustomPersonValidator customPersonValidator;
	private DataService dataService;
	
	public EditContactController(final EditContactView editContactView,final TableView<Person> recentTableView,final TableView<Person> contactTableView) throws InstantiationException, IllegalAccessException
	{
		this.editContactView = editContactView;
		personService = (PersonService)ApplicationSingeltonFactory.getInstance(PersonServiceImpl.class);
		customPersonValidator = (CustomPersonValidator)ApplicationSingeltonFactory.getInstance(CustomPersonValidatorImpl.class);
		dataService =  (DataService)ApplicationSingeltonFactory.getInstance(DataServiceImpl.class);
		
		editContactView.getButtonMap().get("save").setOnAction(new EventHandler<ActionEvent>(){
			 
            public void handle(ActionEvent t) {
            	setStateBeforeValidation();
            	String personalIdentifier = editContactView.getLabelMap().get("personalIdentifier").getText();
            	if(personalIdentifier==null || personalIdentifier.equals(""))
            	{
            		editContactView.getLabelMap().get("editStatus").setText("Please click on edit button in the list before enter save.");
	    			editContactView.getLabelMap().get("editStatus").setTextFill(Color.RED);
	    			return;
            	}
            	Person person = dataService.getContactByPersonIdentifier( Long.valueOf(personalIdentifier));
            	person.setFirstName(editContactView.getTextFieldMap().get("firstName").getText());
            	person.setMiddleName(editContactView.getTextFieldMap().get("middleName").getText());
            	person.setLastName(editContactView.getTextFieldMap().get("lastName").getText());
            	if(editContactView.getRadioButtonMap().get("male").isSelected()) 
            	{
            		person.setGender(Gender.MALE);
            	}
            	else
            	{
            		person.setGender(Gender.FEMALE);
            	}
            	person.getContactInfo().setAddressLine1(editContactView.getTextFieldMap().get("addressLine1").getText());
            	person.getContactInfo().setAddressLine2(editContactView.getTextFieldMap().get("addressLine2").getText());
            	person.getContactInfo().setCity(editContactView.getTextFieldMap().get("city").getText());
            	person.getContactInfo().setPhoneNo(editContactView.getTextFieldMap().get("phoneNo").getText());
            	person.getContactInfo().setZipCode(editContactView.getTextFieldMap().get("zipCode").getText());
            	person.getContactInfo().setState(editContactView.getTextFieldMap().get("state").getText());
            	person.getContactInfo().setCountry(editContactView.getTextFieldMap().get("country").getText());
            	person.getContactInfo().setEmail(editContactView.getTextFieldMap().get("email").getText());
            	
            	
            	Set<ConstraintViolation<Person>> constraintViolations = ApplicationValidatorFactory.validator.validate(person);
	    		 if (!constraintViolations.isEmpty())
	    		 {
	    			 editContactView.getLabelMap().get("editStatus").setText("Validation error");
	    			 editContactView.getLabelMap().get("editStatus").setTextFill(Color.RED);
	    			 for(ConstraintViolation<Person> constraintViolation :constraintViolations )
			    		{
	    				 String fieldPath = constraintViolation.getPropertyPath().toString();
			    		 if(fieldPath.equals("firstName"))
			    		 {
			    			 editContactView.getTextFieldMap().get("firstName").setStyle("-fx-border-color: red");
			    			 editContactView.getLabelMap().get("firstName").setText("Please enter letters only");
			    			 editContactView.getLabelMap().get("firstName").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("lastName"))
			    		 {
			    			 editContactView.getTextFieldMap().get("lastName").setStyle("-fx-border-color: red");
			    			 editContactView.getLabelMap().get("lastName").setText("Please enter letters only");
			    			 editContactView.getLabelMap().get("lastName").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("middleName"))
			    		 {
			    			 editContactView.getTextFieldMap().get("middleName").setStyle("-fx-border-color: red");
			    			 editContactView.getLabelMap().get("middleName").setText("Please enter letter only");
			    			 editContactView.getLabelMap().get("middleName").setTextFill(Color.RED);
			    		 }
			    		 else if (fieldPath.equals("contactInfo.addressLine1"))
			    		 {
			    			 editContactView.getTextFieldMap().get("addressLine1").setStyle("-fx-border-color: red");
			    			 if(editContactView.getTextFieldMap().get("addressLine1").getText()==null || editContactView.getTextFieldMap().get("addressLine1").getText().equals(""))
			    				 editContactView.getLabelMap().get("contactInfo.addressLine1").setText("Please enter the address Line 1");
			    			 else
			    				 editContactView.getLabelMap().get("contactInfo.addressLine1").setText("! ' - _ @ # . / : , | are allowed");
			    			 editContactView.getLabelMap().get("contactInfo.addressLine1").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("contactInfo.addressLine2"))
			    		 {
			    			 editContactView.getTextFieldMap().get("addressLine2").setStyle("-fx-border-color: red");
			    			 editContactView.getLabelMap().get("contactInfo.addressLine2").setText("! ' - _ @ # . / : , | are allowed");
			    			 editContactView.getLabelMap().get("contactInfo.addressLine2").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("contactInfo.phoneNo"))
			    		 {
			    			 editContactView.getTextFieldMap().get("phoneNo").setStyle("-fx-border-color: red");
			    			 editContactView.getLabelMap().get("contactInfo.phoneNo").setText("Please enter Digits only");
			    			 editContactView.getLabelMap().get("contactInfo.phoneNo").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("contactInfo.city"))
			    		 {
			    			 editContactView.getTextFieldMap().get("city").setStyle("-fx-border-color: red");
			    			 editContactView.getLabelMap().get("contactInfo.city").setText("Please enter letters only");
			    			 editContactView.getLabelMap().get("contactInfo.city").setTextFill(Color.RED);
			    		 }
			    		 else if(fieldPath.equals("contactInfo.state"))
			    		 {
			    			 editContactView.getTextFieldMap().get("state").setStyle("-fx-border-color: red");
			    			 editContactView.getLabelMap().get("contactInfo.state").setText("Please enter 2 letters only");
			    			 editContactView.getLabelMap().get("contactInfo.state").setTextFill(Color.RED);
			    		 }
			    		 else if (fieldPath.equals("contactInfo.zipCode"))
			    		 {
			    			 editContactView.getTextFieldMap().get("zipCode").setStyle("-fx-border-color: red");
			    			 editContactView.getLabelMap().get("contactInfo.zipCode").setText("Please enter Digits only");
			    			 editContactView.getLabelMap().get("contactInfo.zipCode").setTextFill(Color.RED);
			    		 }
			    		 else if (fieldPath.equals("contactInfo.country"))
			    		 {
			    			 editContactView.getTextFieldMap().get("country").setStyle("-fx-border-color: red");
			    			 editContactView.getLabelMap().get("contactInfo.country").setText("Please enter Digits only");
			    			 editContactView.getLabelMap().get("contactInfo.country").setTextFill(Color.RED);
			    		 }
			    		 else if (fieldPath.equals("contactInfo.email"))
			    		 {
			    			 editContactView.getTextFieldMap().get("email").setStyle("-fx-border-color: red");
			    			 editContactView.getLabelMap().get("contactInfo.email").setText("Please enter Digits only");
			    			 editContactView.getLabelMap().get("contactInfo.email").setTextFill(Color.RED);
			    		 }
			    		}	 
	    		 }
	    	      else
	    	      { 
	    	    	 try {
	    	    		 if(customPersonValidator.validateNameAsKeyForEdit(person))
	    	    		 {
	    	    			 personService.update(person);
	    	    			 editContactView.getLabelMap().get("editStatus").setText("Successfully edited");
	    	    			 editContactView.getLabelMap().get("editStatus").setTextFill(Color.GREEN);
	    	    			 editContactView.getContactList().clear();
	    	    			 editContactView.getContactList().addAll(Utils.convertMapIntoArrayList(AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY)));
	    	    			 editContactView.getRecentContactList().clear();
	    	    			 editContactView.getRecentContactList().addAll(Utils.convertMapIntoArrayList(AppData.mainDataMap.get(Constants.MAIN_DATA_RECENT_KEY)));
	    	    			 recentTableView.getColumns().get(0).setVisible(false);
	    	    			 recentTableView.getColumns().get(0).setVisible(true);
	    	    			 contactTableView.getColumns().get(0).setVisible(false);
	    	    			 contactTableView.getColumns().get(0).setVisible(true);
	    	    		 }
	    	    		 else
	    	    		 {
	    	    			 editContactView.getLabelMap().get("editStatus").setText("User exists with same name. Change the name.");
	    	    			 editContactView.getLabelMap().get("editStatus").setTextFill(Color.RED);
		  					 //Toolkit.getDefaultToolkit().beep();
	    	    		 }
	  				} catch (FileNotFoundException e) {
	  					 editContactView.getLabelMap().get("editStatus").setText("Technical error. Contact not saved. Try Again.");
	  					 editContactView.getLabelMap().get("editStatus").setTextFill(Color.RED);
	  					e.printStackTrace();
	  				} catch (IOException e) {
	  					 editContactView.getLabelMap().get("editStatus").setText("Technical error. Contact not saved. Try Again.");
	  					 editContactView.getLabelMap().get("editStatus").setTextFill(Color.RED);
	  					e.printStackTrace();
	  				}
	    	    	  
	    	      }
            	
            	
            }
        });
	}
	
	private void setStateBeforeValidation()
	{
		editContactView.getTextFieldMap().get("firstName").setStyle("-fx-border-color: null");
		editContactView.getTextFieldMap().get("lastName").setStyle("-fx-border-color: null");
		editContactView.getTextFieldMap().get("middleName").setStyle("-fx-border-color: null");
		editContactView.getTextFieldMap().get("phoneNo").setStyle("-fx-border-color: null");
		editContactView.getTextFieldMap().get("addressLine1").setStyle("-fx-border-color: null");
		editContactView.getTextFieldMap().get("addressLine2").setStyle("-fx-border-color: null");
		editContactView.getTextFieldMap().get("city").setStyle("-fx-border-color: null");
		editContactView.getTextFieldMap().get("state").setStyle("-fx-border-color: null");
		editContactView.getTextFieldMap().get("zipCode").setStyle("-fx-border-color: null");
		editContactView.getTextFieldMap().get("email").setStyle("-fx-border-color: null");
		editContactView.getTextFieldMap().get("country").setStyle("-fx-border-color: null");
    	
		editContactView.getLabelMap().get("firstName").setText("");
		editContactView.getLabelMap().get("lastName").setText("");
		editContactView.getLabelMap().get("middleName").setText("");
		editContactView.getLabelMap().get("contactInfo.addressLine1").setText("");
		editContactView.getLabelMap().get("contactInfo.phoneNo").setText("");
		editContactView.getLabelMap().get("contactInfo.addressLine2").setText("");
		editContactView.getLabelMap().get("contactInfo.city").setText("");
		editContactView.getLabelMap().get("contactInfo.state").setText("");
		editContactView.getLabelMap().get("contactInfo.zipCode").setText("");
		editContactView.getLabelMap().get("contactInfo.country").setText("");
		editContactView.getLabelMap().get("contactInfo.email").setText("");
		 
	}

}
