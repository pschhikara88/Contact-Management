package uid.contact.manager.controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.service.PersonService;
import uid.contact.manager.service.impl.PersonServiceImpl;
import uid.contact.manager.view.ContactListView;
import uid.contact.manager.view.RecentContactView;

public class DeleteAndRestoreContactController {
	
	private RecentContactView recentContactView;
	private ContactListView contactListView;
	private PersonService personService;
	
	public DeleteAndRestoreContactController(RecentContactView recentContactView,ContactListView contactListView) throws InstantiationException, IllegalAccessException
	{
		this.contactListView = contactListView;
		this.recentContactView = recentContactView;
		personService = (PersonService)ApplicationSingeltonFactory.getInstance(PersonServiceImpl.class);
		//List<Button> restoreButtonList = recentContactView.getButtonMap().get("restore");
		//List<Button> deleteButtonListForRecent = recentContactView.getButtonMap().get("delete");
		//List<Button> deleteButtonListForContact = contactListView.getButtonMap().get("delete");
		
		/*for(Button restoreButton : restoreButtonList)
		{
			restoreButton.setOnAction(new EventHandler<ActionEvent>(){
				 
	            public void handle(ActionEvent t) {
	            	
	            }
			});
		}
		
		for(Button deleteButton : deleteButtonListForRecent)
		{
			deleteButton.setOnAction(new EventHandler<ActionEvent>(){
				 
	            public void handle(ActionEvent t) {
	            	
	            }
			});
		}*/
		
		/*for(Button deleteButton : deleteButtonListForContact)
		{
			deleteButton.setOnAction(new EventHandler<ActionEvent>(){
				 
	            public void handle(ActionEvent t) {
	            	
	            }
			});
		}*/
	}

	public RecentContactView getRecentContactView() {
		return recentContactView;
	}

	public void setRecentContactView(RecentContactView recentContactView) {
		this.recentContactView = recentContactView;
	}

	public ContactListView getContactListView() {
		return contactListView;
	}

	public void setContactListView(ContactListView contactListView) {
		this.contactListView = contactListView;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	

}
