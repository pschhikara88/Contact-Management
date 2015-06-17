package uid.contact.manager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uid.contact.manager.constant.Constants;
import uid.contact.manager.dao.domain.Person;
import uid.contact.manager.execution.AppData;
import uid.contact.manager.factory.ApplicationSingeltonFactory;
import uid.contact.manager.service.DataService;
import uid.contact.manager.service.SearchService;

public class SearchServiceImpl implements SearchService{

	private DataService dataService;
	
	public SearchServiceImpl() throws InstantiationException, IllegalAccessException
	{
		dataService = (DataService)ApplicationSingeltonFactory.getInstance(DataServiceImpl.class);
	}
	public List<Person> searchContactsByName(String searchKey) {
		if(searchKey.length()==1)
		{
			return searchContactsByNameWithStartingChar(searchKey);
		}
		List<Person> searchFirstPriorityList = new ArrayList<Person>();
		List<Person> searchSecondPriorityList = new ArrayList<Person>();
		Map<String,Person>  contactNameMap = AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY);
		String[] searchParts=null;
		if(contactNameMap!=null && contactNameMap.size()>0)
		{
			for(Map.Entry<String, Person> entry : contactNameMap.entrySet()){
				searchParts =entry.getKey().split("%%");
				if(searchParts[0].toLowerCase().startsWith(searchKey.toLowerCase()))
					searchFirstPriorityList.add(entry.getValue());
				else if(searchParts[0].toLowerCase().contains(searchKey.toLowerCase()) || searchParts[2].toLowerCase().contains(searchKey.toLowerCase()))
					searchSecondPriorityList.add(entry.getValue());
			}
		}
		searchFirstPriorityList.addAll(searchSecondPriorityList);
		return searchFirstPriorityList;
	}

	public List<Person> searchContactsByPhone(String searchKey) {
		List<Person> searchFirstPriorityList = new ArrayList<Person>();
		List<Person> searchSecondPriorityList = new ArrayList<Person>();
		Map<String,Person>  contactNameMap = AppData.mainDataMap.get(Constants.MAIN_DATA_PHONE_KEY);
		if(contactNameMap!=null && contactNameMap.size()>0)
		{
			for(Map.Entry<String, Person> entry : contactNameMap.entrySet()){
				if(entry.getKey().toLowerCase().startsWith(searchKey .toLowerCase()))
					searchFirstPriorityList.add(entry.getValue());
				else if(entry.getKey().toLowerCase().contains(searchKey.toLowerCase()))
					searchSecondPriorityList.add(entry.getValue());		
			}
		}
		searchFirstPriorityList.addAll(searchSecondPriorityList);
		return searchFirstPriorityList;
	}
	public List<Person> searchContactsByNameWithStartingChar(String searchKey) {
		List<Person> searchList = new ArrayList<Person>();
		String[] searchParts=null;
		Map<String,Person>  contactNameMap = AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY);
		if(contactNameMap!=null && contactNameMap.size()>0)
		{
			for(Map.Entry<String, Person> entry : contactNameMap.entrySet()){
				searchParts =entry.getKey().split("%%");		
				if(searchParts[0].toLowerCase().startsWith(searchKey.toLowerCase()))
					searchList.add(entry.getValue());	
			}
		}
		return searchList;
	}
	public List<Person> searchContactsByPhoneWithStartingChar(String searchKey) {
		List<Person> searchList = new ArrayList<Person>();
		Map<String,Person>  contactNameMap = AppData.mainDataMap.get(Constants.MAIN_DATA_PHONE_KEY);
		if(contactNameMap!=null && contactNameMap.size()>0)
		{
			for(Map.Entry<String, Person> entry : contactNameMap.entrySet()){
				if(entry.getKey().toLowerCase().startsWith(searchKey.toLowerCase()))
					searchList.add(entry.getValue());			
			}
		}
		return searchList;
	}
	
	public List<Person> searchContactsByBothName(String firstName, String lastName) {
		List<Person> searchFirstPriorityList = new ArrayList<Person>();
		List<Person> searchSecondPriorityList = new ArrayList<Person>();
		List<Person> searchThirdPriorityList = new ArrayList<Person>();
		Map<String,Person>  contactNameMap = AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY);
		String[] searchParts=null;
		if(contactNameMap!=null && contactNameMap.size()>0)
		{
			for(Map.Entry<String, Person> entry : contactNameMap.entrySet()){
				searchParts =entry.getKey().split("%%");
				if(searchParts[0].toLowerCase().startsWith(firstName.toLowerCase()) && searchParts[2].toLowerCase().startsWith(lastName.toLowerCase()))
					searchFirstPriorityList.add(entry.getValue());
				else if(searchParts[0].toLowerCase().startsWith(firstName.toLowerCase()) && searchParts[2].toLowerCase().contains(lastName.toLowerCase()))
					searchSecondPriorityList.add(entry.getValue());
				else if(searchParts[0].toLowerCase().contains(firstName.toLowerCase()) && searchParts[2].toLowerCase().contains(lastName.toLowerCase()))
					searchThirdPriorityList.add(entry.getValue());
			}
		}
		searchFirstPriorityList.addAll(searchSecondPriorityList);
		searchFirstPriorityList.addAll(searchThirdPriorityList);
		return searchFirstPriorityList;
	}
	
	public List<Person> searchContactsByAllName(String firstName, String lastName,String middleName) {
		List<Person> searchFirstPriorityList = new ArrayList<Person>();
		List<Person> searchSecondPriorityList = new ArrayList<Person>();
		List<Person> searchThirdPriorityList = new ArrayList<Person>();
		Map<String,Person>  contactNameMap = AppData.mainDataMap.get(Constants.MAIN_DATA_NAME_KEY);
		String[] searchParts=null;
		if(contactNameMap!=null && contactNameMap.size()>0)
		{
			for(Map.Entry<String, Person> entry : contactNameMap.entrySet()){
				searchParts =entry.getKey().split("%%");
				if(searchParts[0].toLowerCase().startsWith(firstName.toLowerCase()) && searchParts[1].toLowerCase().equals(middleName.toLowerCase())&& searchParts[2].toLowerCase().startsWith(lastName.toLowerCase()))
					searchFirstPriorityList.add(entry.getValue());
				else if(searchParts[0].toLowerCase().startsWith(firstName.toLowerCase())&& searchParts[1].toLowerCase().equals(middleName.toLowerCase()) && searchParts[2].toLowerCase().contains(lastName.toLowerCase()))
					searchSecondPriorityList.add(entry.getValue());
				else if(searchParts[0].toLowerCase().contains(firstName.toLowerCase())&& searchParts[1].toLowerCase().equals(middleName.toLowerCase()) && searchParts[2].toLowerCase().contains(lastName.toLowerCase()))
					searchThirdPriorityList.add(entry.getValue());
			}
		}
		searchFirstPriorityList.addAll(searchSecondPriorityList);
		searchFirstPriorityList.addAll(searchThirdPriorityList);
		return searchFirstPriorityList;
	}
	
	
	

}
