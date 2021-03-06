package assignment7;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/assignment7")
public class MyEavesDropApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
		
	public MyEavesDropApplication() {
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		classes.add(MyEavesDropResource.class);
		return classes;
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
}
