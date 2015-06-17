package uid.contact.manager.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import uid.contact.manager.constant.Constants;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.service.SearchService;
import uid.contact.manager.service.impl.SearchServiceImpl;
import uid.contact.manager.utility.Utils;
import uid.contact.manager.view.ContactListView;

public class SearchContactController {
	
	private ContactListView contactListView;
	private SearchService searchService;
	private ObservableList<Person> masterData;
	//private ObservableList<Person> filteredData;
	
	public SearchContactController(final ContactListView contactListView) throws InstantiationException, IllegalAccessException
	{
		this.contactListView=contactListView;
		searchService= (SearchService)ApplicationSingeltonFactory.getInstance(SearchServiceImpl.class);
		final List<Person> filteredData = new ArrayList<Person>();
		
		contactListView.getTextFieldMap().get("searchText").textProperty().addListener(new ChangeListener<String>() {
           
			public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
					filteredData.clear();
				String[] searchKeyParts =	newValue.split(" ");
				if(newValue.length()<1)
				{
					contactListView.getContactTable().getItems().clear();
					contactListView.getContactTable().getItems().addAll(Utils.convertMapIntoArrayList(AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY)));
					return;
				}
				if(newValue.matches("-?\\d+(\\.\\d+)?"))
				{	
					filteredData.addAll(searchService.searchContactsByPhone(newValue));
				}
				else if(searchKeyParts.length==2)
				{
					filteredData.addAll(searchService.searchContactsByBothName(searchKeyParts[0], searchKeyParts[1]));
				}
				else if(searchKeyParts.length==3)
				{
					filteredData.addAll(searchService.searchContactsByAllName(searchKeyParts[0], searchKeyParts[2],searchKeyParts[1]));
				}
				else
				{
					filteredData.addAll(searchService.searchContactsByName(newValue));
				}
				contactListView.getContactTable().getItems().clear();
				contactListView.getContactTable().getItems().addAll(filteredData);
            }
        });
		
	}

}
