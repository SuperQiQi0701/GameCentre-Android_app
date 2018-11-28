package Basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    /**
     * The instance of User for testing
     */
    private User myUser;

    @Before
    public void setUp() throws Exception {
        myUser = new User("test @ user");
        myUser.setPassword("123456");
    }

    @After
    public void tearDown() throws Exception {
        myUser = null;
    }

    @Test
    public void getPassword() {
        assertEquals("123456", myUser.getPassword());
    }

    @Test
    public void setPassword() {
        String newPassword = "111111";
        myUser.setPassword(newPassword);
        assertEquals(newPassword, myUser.getPassword());
    }

    @Test
    public void getUserName() {
        assertEquals("test @ user", myUser.getUserName());
    }
}