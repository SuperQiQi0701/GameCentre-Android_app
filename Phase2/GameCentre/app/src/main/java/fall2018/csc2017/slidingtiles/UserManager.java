package fall2018.csc2017.slidingtiles;


import java.io.Serializable;
import java.util.ArrayList;

public class UserManager implements Serializable {

    /**
     * An ArrayList that store all the users.
     */
    private ArrayList<User> users = new ArrayList<>();

    /**
     * The user's name for current user.
     */
    private String currUser;

    /**
     * Return the user's name of the current user.
     *
     * @param username the username of the current user.
     * @param password the password of the current user.
     * @return the user who log in.
     */
    public User login(String username, String password) {
        for (User u : users) {
            if (u.userName.equals(username) && u.getPassword().equals(password)) {
                currUser = u.userName;
                return u;
            }
        }
        return null;
    }

    /**
     * Return if the user with username is in the users's Array List.
     *
     * @param username the username of the current user.
     * @return if the user with username is in the users's Array List.
     */
    boolean exist(String username) {
        for (User u : users) {
            if (u.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Let the user with username and password sign up.
     *
     * @param username the username of the current user.
     * @param password the password of the current user.
     */
    void signUp(String username, String password) {
        User tempuser = new User(username);
        tempuser.setPassword(password);
        users.add(tempuser);
    }

    /**
     * Return the user with username in the users's Array List.
     *
     * @param username the username of the current user.
     * @return the user with username in the users's Array List.
     */
    User getUser(String username) {
        for (User u : users) {
            if (u.getUserName().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Return the current user of the game
     *
     * @return the current user of the game
     */
    String getCurrentUser() {
        return currUser;
    }
}