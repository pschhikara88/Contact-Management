package uid.contact.manager.view;

import java.awt.Event;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import uid.contact.manager.constant.Constants;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.enums.Gender;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.service.PersonService;
import uid.contact.manager.service.impl.PersonServiceImpl;
import uid.contact.manager.utility.Utils;

public class ContactListView {

	private VBox vbox;
	private Scene scene;
	private Map<String,List<Button>> buttonMap;
	private Map<String,TextField> textFieldMap;
	private TableView<Person> contactTable;
	private TableView<Person> recentTable;
	private ContactDetailsView contactDetailsView;
	private EditContactView editContactView;
	private PersonService personService;
	private AddContactView addContactView;
	
	@SuppressWarnings("unchecked")
	public ContactListView(Scene scene,VBox vbox,ObservableList<Person> personList,ContactDetailsView contactDetailsView,EditContactView editContactView) throws InstantiationException, IllegalAccessException
	{
		this.vbox=vbox;
		this.scene= scene;
		this.contactDetailsView = contactDetailsView;
		this.editContactView = editContactView;
		personService = (PersonService)ApplicationSingeltonFactory.getInstance(PersonServiceImpl.class);
		buttonMap = new HashMap<String,List<Button>>();
		buttonMap.put("delete", new ArrayList<Button>() );
		textFieldMap =new HashMap<String,TextField>() ;
		contactTable = new TableView<Person>();
		
		
		 HBox hbox = new HBox();
		 hbox.setAlignment(Pos.CENTER_LEFT); 
		 //hbox.setSpacing(10);
		 hbox.setPrefHeight(30);
		 hbox.setStyle("-fx-background-color: #336699;");
		 TextField searchText = new TextField();
	     searchText.setFocusTraversable(false);
	     searchText.setPromptText("Search");
	     searchText.setStyle("-fx-focus-color: transparent;");
	     textFieldMap.put("searchText", searchText);
	     hbox.getChildren().addAll(searchText);
	     
			//TableColumn lastNameCol = new TableColumn("Last Name");2
			 TableColumn nameCol = new TableColumn("Name");
			 TableColumn phoneCol = new TableColumn("Phone");
			 TableColumn actionCol = new TableColumn("Action");
			// nameCol.prefWidthProperty().bind(contactTable.widthProperty().multiply(.40));
			phoneCol.prefWidthProperty().bind(scene.widthProperty().multiply(.18));
			 actionCol.prefWidthProperty().bind(scene.widthProperty().multiply(.14));
		  // nameCol.prefWidthProperty().bind(vbox.widthProperty().multiply(.20));
		   //  middleInitialCol.prefWidthProperty().bind(scene.widthProperty().multiply(.12));
		   //  lastNameCol.prefWidthProperty().bind(scene.widthProperty().multiply(.20));
		  //   phoneCol.prefWidthProperty().bind(vbox.widthProperty().multiply(.20));
		     
		   //  firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		//	 middleInitialCol.setCellValueFactory(new PropertyValueFactory<Person, String>("middleName")); 
		//	lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
			phoneCol.setCellValueFactory(
		                new Callback<TableColumn.CellDataFeatures<Person, String>, 
		                ObservableValue<String>>() {
		            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
		            	if(p.getValue()!=null && p.getValue().getContactInfo().getPhoneNo()!=null)
		            		return new SimpleStringProperty(String.valueOf(p.getValue().getContactInfo().getPhoneNo()));
		            	else
		            		return new SimpleStringProperty(null);
		            }
		        });
			
			nameCol.setCellValueFactory(
	                new Callback<TableColumn.CellDataFeatures<Person, String>, 
	                ObservableValue<String>>() {
	            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
	            	if(p.getValue()!=null && p.getValue().getContactInfo().getPhoneNo()!=null)
	            	{
	            		String middleName = null;
	            		if(p.getValue().getMiddleName()!=null && p.getValue().getMiddleName().length()>0)
	            			middleName = Utils.makeFirstLetterCapital(p.getValue().getMiddleName());
	            		else
	            			middleName="";
	            		return new SimpleStringProperty(Utils.makeFirstLetterCapital(p.getValue().getFirstName())+" "+ middleName + 
	            				" "+ Utils.makeFirstLetterCapital(p.getValue().getLastName()));
	            	}
	            	else
	            		return new SimpleStringProperty(null);
	            }
	        });
			
			actionCol.setSortable(false);
		     actionCol.setCellValueFactory(
		                new Callback<TableColumn.CellDataFeatures<Person, Boolean>, 
		                ObservableValue<Boolean>>() {
		 
		            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Person, Boolean> p) {
		                return new SimpleBooleanProperty(p.getValue() != null);
		            }
		        });
		     actionCol.setCellFactory(
		                new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {

		            public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {
		                return new ActionCell();
		            }
		         
		        });
		     
		     TitledPane titleRecentPane = new TitledPane();
				
				//titleRecentPane.prefWidthProperty().bind(scene.widthProperty());
				titleRecentPane.setCollapsible(false);
				titleRecentPane.setText("Contacts List");
				contactTable.setItems(personList);
				contactTable.setId("recentTable");
				contactTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
				String css = this.getClass().getResource("/css/recent-contact-table-style.css").toExternalForm();
				contactTable.getStylesheets().add(css);
				//contactTable.prefWidthProperty().bind(scene.widthProperty());
				contactTable.prefHeightProperty().bind(scene.heightProperty().multiply(0.52));
				contactTable.getColumns().addAll(nameCol, phoneCol,actionCol);  
				 final VBox innerVbox = new VBox();
				 //innerVbox.setSpacing(5);
				 //innerVbox.setPadding(new Insets(10, 0, 0, 10));
				 innerVbox.getChildren().addAll( hbox , contactTable);
			        
				titleRecentPane.setContent(innerVbox);
				//titleRecentPane.setAlignment(Pos.CENTER_LEFT);
				this.vbox.getChildren().add(titleRecentPane);
	     
	    
	}
	
	 private class ActionCell extends TableCell<Person,Boolean>
	    {
	    	private HBox hBox;
	    	private Button viewButton;
	    	private Button deleteButton;
	    	private Button editButton;
	    	
	    	public ActionCell()
	    	{
	    	
	    	}
	    	
	    	 @Override
	         protected void updateItem(Boolean t, boolean empty) {
	             super.updateItem(t, empty);
	             if(empty){
	                 //setGraphic(null);
	             }
	             else
	             {
	            		hBox= new HBox();
	    	    		hBox.setSpacing(2);
	    	    		hBox.setAlignment(Pos.CENTER_LEFT);
	    	    		
	    	    		Image editImage = new Image(getClass().getResourceAsStream("/images/edit1.png"));
	                    Image deleteImage = new Image(getClass().getResourceAsStream("/images/delete1.png"));
	                    Image viewImage = new Image(getClass().getResourceAsStream("/images/view1.png"));
	                    viewButton = new Button("",new ImageView(viewImage));
	                    viewButton.setTooltip(new Tooltip("View"));
	    	    		deleteButton = new Button("",new ImageView(deleteImage));
	    	    		deleteButton.setTooltip(new Tooltip("Delete"));
	                    
	    	    		viewButton.setOnAction(new EventHandler<ActionEvent>(){
	    	    			 
	     	                public void handle(ActionEvent t) {
	     	                    int selectdIndex = getTableRow().getIndex();
	     	                   Person person = contactTable.getItems().get(selectdIndex);
	     	                  /*fieldValueMap.put("firstName",firstNameValue);
	     	         		fieldValueMap.put("middleName",middleNameValue);
	     	         		fieldValueMap.put("lastName",lastNameValue);
	     	         		fieldValueMap.put("gender",genderValue);
	     	         		fieldValueMap.put("phone",phoneValue);
	     	         		fieldValueMap.put("addressLine1",addressValue);
	     	         		fieldValueMap.put("addressLine2",addressValue2);
	     	         		fieldValueMap.put("city",cityValue);
	     	         		fieldValueMap.put("state",stateValue);
	     	         		fieldValueMap.put("zipCode",zipValue);
	     	         		*/
	     	                  contactDetailsView.getFieldValueMap().get("firstName").setText(Utils.makeFirstLetterCapital(person.getFirstName()));
	     	                  contactDetailsView.getFieldValueMap().get("middleName").setText(person.getMiddleName()!=null?person.getMiddleName().toUpperCase():"");
	     	                  contactDetailsView.getFieldValueMap().get("lastName").setText(Utils.makeFirstLetterCapital(person.getLastName()));
	     	                  contactDetailsView.getFieldValueMap().get("gender").setText(person.getGender()==Gender.MALE?"MALE":"FEMALE");
	     	                  contactDetailsView.getFieldValueMap().get("phone").setText(person.getContactInfo().getPhoneNo());
	     	                  contactDetailsView.getFieldValueMap().get("addressLine1").setText(person.getContactInfo().getAddressLine1());
	     	                  contactDetailsView.getFieldValueMap().get("addressLine2").setText(person.getContactInfo().getAddressLine2()!=null?person.getContactInfo().getAddressLine2():"");
	     	                  contactDetailsView.getFieldValueMap().get("city").setText(Utils.makeFirstLetterCapital(person.getContactInfo().getCity()));
	     	                 contactDetailsView.getFieldValueMap().get("state").setText(person.getContactInfo().getState().toUpperCase());
	     	                  contactDetailsView.getFieldValueMap().get("zipCode").setText(person.getContactInfo().getZipCode());
	     	                  
	     	                 contactDetailsView.getViewPane().setText("View Contact : " + Utils.makeFirstLetterCapital(person.getFirstName())+ " "+ (person.getMiddleName()!=null?person.getMiddleName().toUpperCase():"") + " " + Utils.makeFirstLetterCapital(person.getLastName()) );
	     	                // contactDetailsView.getViewPane().fireEvent(new Event(contactDetailsView.getViewPane(),Event.GOT_FOCUS,null));
	     	                  // Person person = null;
	     	                   // filteredData.add(selectdIndex+1, person);

	     	                    //TableRow<Person> row = getTableView().getRowFactory().call(getTableView());
	     	                  
	     	                    
	     	                    //delete the selected item in data
	     	                  /*  Person person2 = data.get(selectdIndex);
	     	                    data.remove(selectdIndex);
	     	                    
	     	                    filteredData.remove(person2);*/
	     	                }
	     	            });
	     	    		
	     	    		deleteButton.setOnAction(new EventHandler<ActionEvent>(){
	     	    			 
	     	                public void handle(ActionEvent t) {
	     	                	int selectdIndex = getTableRow().getIndex();
	    	 	                Person person = contactTable.getItems().get(selectdIndex);
	    	 	                try {
	    							personService.delete(person);
	    							contactTable.getItems().clear();
	    							contactTable.getItems().addAll(Utils.convertMapIntoArrayList(AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY)));
	    							recentTable.getItems().clear();
	    							recentTable.getItems().addAll(Utils.convertMapIntoArrayList(AppData.mainDataMap.get(Constants.MAIN_DATA_RECENT_KEY)));
	    							recentTable.getColumns().get(0).setVisible(false);
	    							recentTable.getColumns().get(0).setVisible(true);
	    						} catch (FileNotFoundException e) {
	    							e.printStackTrace();
	    						} catch (IOException e) {
	    							e.printStackTrace();
	    						}
	     	                }
	     	    		});
	     	    		
	     	    		ImageView editView = new ImageView(editImage);
	     	    		editButton = new Button("",new ImageView(editImage));
	     	    		editButton.setTooltip(new Tooltip("Edit"));
	     	    		
	     	    		editButton.setOnAction(new EventHandler<ActionEvent>(){
	    	    			 
	     	                public void handle(ActionEvent t) {
	     	                    int selectdIndex = getTableRow().getIndex();
	     	                   Person person = contactTable.getItems().get(selectdIndex);
	     	                  editContactView.getLabelMap().get("editStatus").setText("");
	     	                for(Map.Entry<String, TextField> entry : addContactView.getTextFieldMap().entrySet()){
	     	            		entry.getValue().setText("");
	     	            		entry.getValue().setStyle("-fx-border-color: null");
	     	        		}
	     	            	
	     	            	for(Map.Entry<String, Label> entry : addContactView.getLabelMap().entrySet()){
	     	            		entry.getValue().setText("");
	     	        		}
	     	            	
	     	                  editContactView.getEditPane().setExpanded(true);
	     	                  addContactView.getTitlePanel().setExpanded(false);
	     	                /* if(addContactView.getGridPane().getChildren().size()>=3)
	     	                 {	addContactView.getGridPane().getChildren().remove(2,39);
	     	                 	addContactView.expandFlagForForm= true;
	     	                 }*/
	     	                  
	     	                  editContactView.getTextFieldMap().get("firstName").setText(Utils.makeFirstLetterCapital(person.getFirstName()));
		     	             // editContactView.getTextFieldMap().get("middleName").setText("-");
	     	                 editContactView.getProgressWeight().put("middleName",0.4);
	     	                  editContactView.getTextFieldMap().get("middleName").setText(person.getMiddleName()!=null?person.getMiddleName().toUpperCase():"");
	     	                 
	     	                  editContactView.getTextFieldMap().get("lastName").setText(Utils.makeFirstLetterCapital(person.getLastName()));
	     	                  if(person.getGender()==Gender.MALE)
	     	                	 editContactView.getRadioButtonMap().get("male").setSelected(true);
	     	                  else
	     	                	 editContactView.getRadioButtonMap().get("female").setSelected(true);
	     	                 // editContactView.getTextFieldMap().get("gender").setText(person.getGender()==Gender.MALE?"MALE":"FEMALE");
	     	                  editContactView.getTextFieldMap().get("phoneNo").setText(person.getContactInfo().getPhoneNo());
	     	                  editContactView.getTextFieldMap().get("addressLine1").setText(person.getContactInfo().getAddressLine1());
	     	                 //editContactView.getTextFieldMap().get("middleName").setText("-");
	     	                 editContactView.getProgressWeight().put("addressLine2",0.4);
	     	                  editContactView.getTextFieldMap().get("addressLine2").setText(person.getContactInfo().getAddressLine2()!=null?person.getContactInfo().getAddressLine2():"");
	     	                  editContactView.getTextFieldMap().get("city").setText(person.getContactInfo().getCity().toUpperCase());
	     	                  editContactView.getTextFieldMap().get("state").setText(person.getContactInfo().getState().toUpperCase());
	     	                  editContactView.getTextFieldMap().get("zipCode").setText(person.getContactInfo().getZipCode());
	     	                 editContactView.getTextFieldMap().get("email").setText(person.getContactInfo().getEmail());
	     	                 editContactView.getTextFieldMap().get("country").setText(person.getContactInfo().getCountry());
	     	                 editContactView.getLabelMap().get("personalIdentifier").setText(String.valueOf(person.getPersonIdentifier()));
	     	                 editContactView.getEditPane().setText("EDIT CONTACT : " + Utils.makeFirstLetterCapital(person.getFirstName())+ " "+ (person.getMiddleName()!=null?person.getMiddleName().toUpperCase():"") + " " + Utils.makeFirstLetterCapital(person.getLastName()) );
	     	                editContactView.getTextFieldMap().get("country").setDisable(false);
	     	                 editContactView.getTextFieldMap().get("email").setDisable(false);
		 	                  editContactView.getTextFieldMap().get("zipCode").setDisable(false);
		 	                  editContactView.getTextFieldMap().get("state").setDisable(false);
		 	                  editContactView.getTextFieldMap().get("city").setDisable(false);
		 	                  editContactView.getTextFieldMap().get("addressLine2").setDisable(false);
		 	                  editContactView.getTextFieldMap().get("addressLine1").setDisable(false);
		 	                  editContactView.getTextFieldMap().get("phoneNo").setDisable(false);
		 	                  editContactView.getRadioButtonMap().get("male").setDisable(false);
		 	                  editContactView.getRadioButtonMap().get("female").setDisable(false);
		 	                  editContactView.getTextFieldMap().get("lastName").setDisable(false);
		 	                  editContactView.getTextFieldMap().get("middleName").setDisable(false);
		 	                  editContactView.getTextFieldMap().get("firstName").setDisable(false);
		 	                  editContactView.getButtonMap().get("save").setDisable(false);
	     	                 //editContactView.getButtonMap().get("save").requestFocus();
	     	                    /*Person person = data.get(selectdIndex);
	     	                    //delete the selected item in data
	     	                    data.remove(selectdIndex);
	     	                    filteredData.remove(person);*/
	     	                }
	     	            });
	    	    		
	    	    	//	editView.setFitHeight();
	    	    		
	    	    		//editButton.setPrefWidth(20);
	    	    		//editButton.setPrefHeight(20);
	    	    		buttonMap.get("delete").add(deleteButton);
	    	    		hBox.getChildren().addAll(viewButton,editButton,deleteButton);
	    	    		setGraphic(hBox);
	             }
	         }
	    	
	    	
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


	public Map<String, List<Button>> getButtonMap() {
		return buttonMap;
	}

	public void setButtonMap(Map<String, List<Button>> buttonMap) {
		this.buttonMap = buttonMap;
	}

	public Map<String, TextField> getTextFieldMap() {
		return textFieldMap;
	}

	public void setTextFieldMap(Map<String, TextField> textFieldMap) {
		this.textFieldMap = textFieldMap;
	}

	public TableView<Person> getContactTable() {
		return contactTable;
	}

	public void setContactTable(TableView<Person> contactTable) {
		this.contactTable = contactTable;
	}

	public TableView<Person> getRecentTable() {
		return recentTable;
	}

	public void setRecentTable(TableView<Person> recentTable) {
		this.recentTable = recentTable;
	}

	public ContactDetailsView getContactDetailsView() {
		return contactDetailsView;
	}

	public void setContactDetailsView(ContactDetailsView contactDetailsView) {
		this.contactDetailsView = contactDetailsView;
	}

	public EditContactView getEditContactView() {
		return editContactView;
	}

	public void setEditContactView(EditContactView editContactView) {
		this.editContactView = editContactView;
	}

	public AddContactView getAddContactView() {
		return addContactView;
	}

	public void setAddContactView(AddContactView addContactView) {
		this.addContactView = addContactView;
	}
	 
	
	 
}
