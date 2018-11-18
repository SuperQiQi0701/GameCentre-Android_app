package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

class User implements Serializable {

    /**
     * The user's name for current user.
     */
    String userName;
    /**
     * The user's password for current user.
     */
    private String password;

    /**
     * The constructor of the class User.
     */
    public User(String username) {
        this.userName = username;
        this.password = null;
    }

    /**
     * Return the password of the current user.
     *
     * @return the password of the current user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the current user.
     *
     * @param password the password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return the user's name of the current user.
     *
     * @return the userName of the current user.
     */
    String getUserName() {
        return this.userName;
    }
}
