package uid.contact.manager.view;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.enums.Gender;
import uid.contact.manager.utility.Utils;

public class ContactDetailsView {

	private TitledPane viewPane;
	private Scene scene;
	private VBox vbox;
	private Map<String,Label> fieldValueMap;

	public ContactDetailsView(Scene scene, VBox vbox,Person recentContactFirstRecord) {
		this.vbox = vbox;
		this.scene = scene;
		fieldValueMap=new HashMap<String,Label>();

		viewPane = new TitledPane();
		if(recentContactFirstRecord!=null)
			viewPane.setText("VIEW DETAILS : "+ recentContactFirstRecord.getFirstName()+ " "+recentContactFirstRecord.getMiddleName()+" "+ recentContactFirstRecord.getLastName());
		else
			viewPane.setText("VIEW DETAILS");
		/*Tooltip tooltip = new Tooltip(
				"Click view button in the contact lists to see the contact details of other person");
		viewPane.setTooltip(tooltip);*/
		viewPane.prefWidthProperty().bind(vbox.widthProperty());
		final GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(5));
		gridpane.setHgap(20);
		gridpane.setVgap(10);
		Label firstNameLabel = new Label("First Name             :");
		Label firstNameValue = new Label();
		Label middleNameLabel = new Label("Middle Initial         :");
		Label middleNameValue = new Label();
		Label lastNameLabel = new Label("Last Name             :");
		Label lastNameValue = new Label();
		GridPane.setHalignment(firstNameLabel, HPos.LEFT);
		gridpane.add(firstNameLabel, 0, 0);
		GridPane.setHalignment(firstNameValue, HPos.LEFT);
		gridpane.add(firstNameValue, 1, 0);
		GridPane.setHalignment(middleNameLabel, HPos.LEFT);
		gridpane.add(middleNameLabel, 0, 1);
		GridPane.setHalignment(middleNameValue, HPos.LEFT);
		gridpane.add(middleNameValue, 1, 1);
		GridPane.setHalignment(lastNameLabel, HPos.LEFT);
		gridpane.add(lastNameLabel, 0, 2);
		GridPane.setHalignment(lastNameValue, HPos.LEFT);
		gridpane.add(lastNameValue, 1, 2);

		Label phoneLabel = new Label("Phone                    :");
		Label phoneValue = new Label();
		GridPane.setHalignment(phoneLabel, HPos.LEFT);
		gridpane.add(phoneLabel, 0, 3);
		GridPane.setHalignment(phoneValue, HPos.LEFT);
		gridpane.add(phoneValue, 1, 3);
		Label genderLabel = new Label("Gender                   :");
		Label genderValue = new Label();
		GridPane.setHalignment(genderLabel, HPos.LEFT);
		gridpane.add(genderLabel, 0, 4);
		GridPane.setHalignment(genderValue, HPos.LEFT);
		gridpane.add(genderValue, 1, 4);
		Label emailLabel = new Label("Email                     :");
		Label emailValue = new Label();
		GridPane.setHalignment(emailLabel, HPos.LEFT);
		gridpane.add(emailLabel, 0, 5);
		GridPane.setHalignment(emailValue, HPos.LEFT);
		gridpane.add(emailValue, 1, 5);
		Label addressLabel = new Label("Address Line1        :");
		Label addressValue = new Label();
		GridPane.setHalignment(addressLabel, HPos.LEFT);
		gridpane.add(addressLabel, 0, 6);
		GridPane.setHalignment(addressValue, HPos.LEFT);
		gridpane.add(addressValue, 1, 6);
		Label addressLabel2 = new Label("Address Line2        :");
		Label addressValue2 = new Label();
		GridPane.setHalignment(addressLabel2, HPos.LEFT);
		gridpane.add(addressLabel2, 0, 7);
		GridPane.setHalignment(addressValue2, HPos.LEFT);
		gridpane.add(addressValue2, 1, 7);
		Label cityLabel = new Label("City                        :");
		Label cityValue = new Label();
		GridPane.setHalignment(cityLabel, HPos.LEFT);
		gridpane.add(cityLabel, 0, 8);
		GridPane.setHalignment(cityValue, HPos.LEFT);
		gridpane.add(cityValue, 1, 8);

		Label stateLabel = new Label("State                      :");
		Label stateValue = new Label();
		GridPane.setHalignment(stateLabel, HPos.LEFT);
		gridpane.add(stateLabel, 0, 9);
		GridPane.setHalignment(stateValue, HPos.LEFT);
		gridpane.add(stateValue, 1, 9);
		Label zipLabel = new Label("Zip Code                :");
		Label zipValue = new Label();
		GridPane.setHalignment(zipLabel, HPos.LEFT);
		gridpane.add(zipLabel, 0, 10);
		GridPane.setHalignment(zipValue, HPos.LEFT);
		gridpane.add(zipValue, 1, 10);
		Label countryLabel = new Label("Country                  :");
		Label countryValue = new Label();
		GridPane.setHalignment(countryLabel, HPos.LEFT);
		gridpane.add(countryLabel, 0, 11);
		GridPane.setHalignment(countryValue, HPos.LEFT);
		gridpane.add(countryValue, 1, 11);
		
		if(recentContactFirstRecord!=null)
		{
			firstNameValue.setText(Utils.makeFirstLetterCapital(recentContactFirstRecord.getFirstName()));
			middleNameValue.setText(recentContactFirstRecord.getMiddleName()!=null?recentContactFirstRecord.getMiddleName().toUpperCase():"");
			lastNameValue.setText(Utils.makeFirstLetterCapital(recentContactFirstRecord.getLastName()));
              if(recentContactFirstRecord.getGender()==Gender.MALE)
            	  genderValue.setText("Male");
              else
            	  genderValue.setText("Female");
             // editContactView.getTextFieldMap().get("gender").setText(person.getGender()==Gender.MALE?"MALE":"FEMALE");
              phoneValue.setText(recentContactFirstRecord.getContactInfo().getPhoneNo());
              addressValue.setText(recentContactFirstRecord.getContactInfo().getAddressLine1());
              addressValue2.setText(recentContactFirstRecord.getContactInfo().getAddressLine2()!=null?recentContactFirstRecord.getContactInfo().getAddressLine2():"");
              cityValue.setText(recentContactFirstRecord.getContactInfo().getCity().toUpperCase());
              stateValue.setText(recentContactFirstRecord.getContactInfo().getState().toUpperCase());
              zipValue.setText(recentContactFirstRecord.getContactInfo().getZipCode()); 
              countryValue.setText(recentContactFirstRecord.getContactInfo().getCountry()); 
              emailValue.setText(recentContactFirstRecord.getContactInfo().getEmail()); 
		}
		fieldValueMap.put("firstName",firstNameValue);
		fieldValueMap.put("middleName",middleNameValue);
		fieldValueMap.put("lastName",lastNameValue);
		fieldValueMap.put("gender",genderValue);
		fieldValueMap.put("phone",phoneValue);
		fieldValueMap.put("addressLine1",addressValue);
		fieldValueMap.put("addressLine2",addressValue2);
		fieldValueMap.put("city",cityValue);
		fieldValueMap.put("state",stateValue);
		fieldValueMap.put("zipCode",zipValue);

		viewPane.setContent(gridpane);
		vbox.getChildren().add(viewPane);
	}

	public TitledPane getViewPane() {
		return viewPane;
	}

	public void setViewPane(TitledPane viewPane) {
		this.viewPane = viewPane;
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

	public Map<String, Label> getFieldValueMap() {
		return fieldValueMap;
	}

	public void setFieldValueMap(Map<String, Label> fieldValueMap) {
		this.fieldValueMap = fieldValueMap;
	}
	
	

}
