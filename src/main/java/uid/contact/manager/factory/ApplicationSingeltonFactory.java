package uid.contact.manager.factory;

import java.util.HashMap;
import java.util.Map;

public class ApplicationSingeltonFactory {
	private static final ApplicationSingeltonFactory  singletonFactory= new ApplicationSingeltonFactory();
	
	private Map<Class,Object> singletonMap = new HashMap<Class,Object>();
	
	private ApplicationSingeltonFactory(){};
	
	public static <T> T getInstance(Class<T> classType) throws InstantiationException, IllegalAccessException
	{
		if(!singletonFactory.singletonMap.containsKey(classType))
		{
			Object instance = classType.newInstance();
			singletonFactory.singletonMap.put(classType,instance);
		}
		return (T) singletonFactory.singletonMap.get(classType);	
		
	}

}
