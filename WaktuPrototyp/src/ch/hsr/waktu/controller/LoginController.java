package ch.hsr.waktu.controller;

import org.apache.log4j.Logger;

import ch.hsr.waktu.controller.datacontroller.UserController;
import ch.hsr.waktu.domain.Usr;
import ch.hsr.waktu.services.Md5;
import ch.hsr.waktu.services.WaktuException;

public class LoginController {

	private LoginController() { }
	
    private static LoginController theInstance = null;
    private static Usr loggedInUser;

    private Logger logger = Logger.getLogger(LoginController.class);

    /**
     * 
     * @return LoginController
     */
    public static LoginController getInstance() {
        if (theInstance == null) {
            theInstance = new LoginController();
        }
        return theInstance;
    }

    /**
     * 
     * @return Usr
     */
    public final Usr getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * 
     * @param user
     */
    private void setLoggedInUser(final Usr user) {
        loggedInUser = user;
    }

    /**
     * 
     */
    public void logout() {
        setLoggedInUser(null);
    }

    /**
     * 
     * @param username
     * @param password
     * @return boolean
     * @throws WaktuException
     */
    public boolean login(final String username, final String password)
            throws WaktuException {
        if (canLogin(username)) {
            Usr user = UserController.getInstance().getUser(username);
            String passwordHash = Md5.hash(password);
            if (user.getPasswordHash().equals(passwordHash)) {
                setLoggedInUser(user);
                logger.info(user + " logged in");
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param username
     * @throws WaktuException
     */
    public final boolean canLogin(final String username) throws WaktuException {

        if (UserController.getInstance().getUser(username) != null) {
            return true;
        }

        return false;
    }

}
