package uid.contact.manager.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import uid.contact.manager.constant.Constants;
import uid.contact.manager.controller.AddContactController;
import uid.contact.manager.controller.EditContactController;
import uid.contact.manager.controller.SearchContactController;
import uid.contact.manager.dao.domain.ContactInfo;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.execution.AppInitialization;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.service.PersonService;
import uid.contact.manager.service.impl.PersonServiceImpl;
import uid.contact.manager.utility.Utils;

public class MainView extends Application{

	public static void main(String[] args) {
		AppInitialization appInit = null;
		try {
			appInit = new AppInitialization();
		} catch (InstantiationException e) {
			System.out.println("Technical Issue while instantiation");
		} catch (IllegalAccessException e) {
			System.out.println("Technical Issue while Illegal Access");
		}
		try {
			appInit.initApplication();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Application.launch("rgdsgsdgsd");
	}
	
	@Override
	public void start(Stage primaryStage) {
	primaryStage.setTitle("Contact Manager");
	Group root = new Group();
	Scene scene = new Scene(root, 1300, 940);
	final HBox hBoxParent = new HBox();
	hBoxParent.prefWidthProperty().bind(scene.widthProperty());
	hBoxParent.prefHeightProperty().bind(scene.heightProperty());
	final VBox vbox = new VBox();
	vbox.setAlignment(Pos.TOP_LEFT);
	vbox.prefWidthProperty().bind(hBoxParent.widthProperty().multiply(.50));
	//vbox.prefheightProperty().bind(hBoxParent.heightProperty());
    vbox.setSpacing(5);
    final VBox vbox2 = new VBox();
   vbox2.setAlignment(Pos.TOP_RIGHT);
    vbox2.prefWidthProperty().bind(hBoxParent.widthProperty().multiply(.50));
    vbox2.setSpacing(5);
    //vbox.setPadding(new Insets(0, 0, 0, 10));
    
    Map<String,Person> personMapForContactList = AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY);
    Map<String,Person> personMapForRecentContactList = AppData.mainDataMap.get(Constants.MAIN_DATA_RECENT_KEY);
    List<Person> personContactList = Utils.convertMapIntoArrayList(personMapForContactList);
    List<Person> personRecentList = Utils.convertMapIntoArrayList(personMapForRecentContactList);
    
    final ObservableList<Person> personContactObservable =
        FXCollections.observableArrayList(personContactList);
    final ObservableList<Person> personRecentContactObservable =
        FXCollections.observableArrayList(personRecentList);
    AddContactView addContactView = new AddContactView(scene,vbox2,personContactObservable,personRecentContactObservable);
    ContactDetailsView contactDetailsView = new ContactDetailsView(scene, vbox2, personRecentContactObservable.size()>0?personRecentContactObservable.get(0):null);
    EditContactView editContactView = new EditContactView(scene, vbox2, personContactObservable, personRecentContactObservable);
    RecentContactView recentContactView=null;
    ContactListView contactListView=null;
    try {
    	recentContactView = new RecentContactView(scene, vbox, personRecentContactObservable,contactDetailsView,editContactView);
    	contactListView =new  ContactListView(scene, vbox, personContactObservable,contactDetailsView,editContactView);
	} catch (InstantiationException e1) {
		e1.printStackTrace();
	} catch (IllegalAccessException e1) {
		e1.printStackTrace();
	}
 
	recentContactView.setAddContactView(addContactView);
	contactListView.setAddContactView(addContactView);
    contactListView.setRecentTable(recentContactView.getRecentTable());
    recentContactView.setContactTable(contactListView.getContactTable());
    addContactView.setEditPane(editContactView.getEditPane());
    editContactView.setAddPane(addContactView.getTitlePanel());
   
    
    
    AddContactController addContactController=null;
    SearchContactController searchContactController=null;
    EditContactController editContactController=null;
    try {
		addContactController = new AddContactController(addContactView);
		searchContactController = new SearchContactController(contactListView);
		editContactController = new EditContactController(editContactView,recentContactView.getRecentTable(),contactListView.getContactTable());
	} catch (InstantiationException e) {
		System.out.println("Technical Issue while instantiation");
	} catch (IllegalAccessException e) {
		System.out.println("Technical Issue while Illegal Access");
	}
	hBoxParent.getChildren().addAll(vbox,vbox2);
	root.getChildren().add(hBoxParent);
	primaryStage.setScene(scene);
	primaryStage.show();
	try {
		final PersonService personService = (PersonService)ApplicationSingeltonFactory.getInstance(PersonServiceImpl.class);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        public void handle(WindowEvent we) {
	        	try {
					personService.deleteRecordFromDataBase();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    });     
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	
	public ArrayList<Person> dummyPersonList()
	{
		ArrayList<Person> personList = new ArrayList<Person>();
		ContactInfo contactInfo1 = new ContactInfo();
		contactInfo1.setPhoneNo("94343420222");
		Person person1 = new Person();
		person1.setFirstName("Parveen");
		person1.setLastName("Chhikara");
		person1.setMiddleName("S");
		person1.setLastActionPerformed("Added");
		person1.setContactInfo(contactInfo1);
		
		ContactInfo contactInfo2 = new ContactInfo();
		contactInfo2.setPhoneNo("9434354523");
		Person person2 = new Person();
		person2.setFirstName("Sunil");
		person2.setLastName("Chhikara");
		person2.setMiddleName("S");
		person2.setLastActionPerformed("Added");
		person2.setContactInfo(contactInfo2);
		
		ContactInfo contactInfo3 = new ContactInfo();
		contactInfo3.setPhoneNo("978675443");
		Person person3 = new Person();
		person3.setFirstName("Gaurav");
		person3.setLastName("Kumar");
		person3.setMiddleName("M");
		person3.setLastActionPerformed("Deleted");
		person3.setContactInfo(contactInfo3);
		
		ContactInfo contactInfo4 = new ContactInfo();
		contactInfo4.setPhoneNo("99745634522");
		Person person4 = new Person();
		person4.setFirstName("vishal");
		person4.setLastName("shinde");
		person4.setMiddleName("M");
		person4.setLastActionPerformed("Deleted");
		person4.setContactInfo(contactInfo4);
		
		ContactInfo contactInfo5 = new ContactInfo();
		contactInfo5.setPhoneNo("9096774532");
		Person person5 = new Person();
		person5.setFirstName("Tyler");
		person5.setLastName("Adam");
		person5.setMiddleName("M");
		person5.setLastActionPerformed("Restored");
		person5.setContactInfo(contactInfo5);
		
		ContactInfo contactInfo6 = new ContactInfo();
		contactInfo6.setPhoneNo("444444444332");
		Person person6 = new Person();
		person6.setFirstName("Kunal");
		person6.setLastName("Arora");
		person6.setMiddleName("R");
		person6.setLastActionPerformed("Restored");
		person6.setContactInfo(contactInfo6);
		
		ContactInfo contactInfo7 = new ContactInfo();
		contactInfo7.setPhoneNo("90877675643");
		Person person7 = new Person();
		person7.setFirstName("Nitish");
		person7.setLastName("Salwan");
		person7.setMiddleName("R");
		person7.setLastActionPerformed("Edited");
		person7.setContactInfo(contactInfo7);
		
		ContactInfo contactInfo8 = new ContactInfo();
		contactInfo8.setPhoneNo("334324325464");
		Person person8 = new Person();
		person8.setFirstName("harsha");
		person8.setLastName("tomar");
		person8.setMiddleName("J");
		person8.setLastActionPerformed("Viewed");
		person8.setContactInfo(contactInfo8);
		
		personList.add(person1);
		personList.add(person2);
		personList.add(person3);
		personList.add(person4);
		personList.add(person5);
		personList.add(person6);
		personList.add(person7);
		personList.add(person8);
		
		return personList;
		
		
	}

}
