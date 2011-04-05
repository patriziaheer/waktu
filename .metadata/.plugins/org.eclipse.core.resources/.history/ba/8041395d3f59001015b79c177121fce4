

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

public class Domain extends Observable {
	
	private static Domain theInstance;
	 

    /**
     * Statische Methode „getInstance()“ liefert die einzige Instanz der Klasse zurück.
     * 
     */
    public static Domain getInstance() {
        if (theInstance == null) {
            theInstance = new Domain();
        }
        return theInstance;
    }
    
    private List<User> users = new ArrayList<User>();
    private List<UserProperties> userProperties = new ArrayList<UserProperties>();
    

    public void addUser(User user) {
    	users.add(user);
    }
    
    public void addUsers(Collection<User> users) {
    	users.addAll(users);
    }
    
    public void removeUser(User user) {
    	users.remove(user);
    }
    
    public List<User> getUsers() {
    	return users;
    }
    
    
    
    public void addUserProperty(UserProperties userProperty) {
    	userProperties.add(userProperty);
    }
    
    public void addUserProperties(Collection<UserProperties> userProperties) {
    	this.userProperties.addAll(userProperties);
    }
    
    public void removeUserProperty(UserProperties userProperty) {
    	userProperties.remove(userProperty);
    }
    
    public List<UserProperties> getUserProperties() {
    	return userProperties;
    }
}
