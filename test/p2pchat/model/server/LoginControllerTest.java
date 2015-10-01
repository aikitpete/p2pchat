/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

import org.junit.*;
import static org.junit.Assert.*;
import p2pchat.model.User;
import p2pchat.persistence.server.IPersistenceUser;
import p2pchat.persistence.server.PersistenceUserMock;

/**
 *
 * @author USER
 */
public class LoginControllerTest {

    IOnlineUserManagerServer onlineUserManager;
    LoginController instance;
    IPersistenceUser persistenceUser;
    final int sessionTest = 2000;
    final String addressTest = "localhost";
    final int portTest = 2000;
    //Existing user
    final String usernameBob = "bob";
    final String passwordBob = "bob";

    public LoginControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        onlineUserManager = new OnlineUserManager();
        instance = new LoginController(onlineUserManager);
        persistenceUser = new PersistenceUserMock();
        PersistentUser.setPersistence(persistenceUser);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class LoginController with existing user.
     */
    @Test
    public void testLoginExistingUser() {
        //Arrange
        System.out.println("Login Existing User");

        //Act
        User ret = instance.login(sessionTest, addressTest, portTest, usernameBob, passwordBob);

        //Assert
        assertNotNull(ret);
    }

    /**
     * Test of login method, of class LoginController with wrong username.
     */
    @Test
    public void testLoginWrongUsername() {
        //Arrange
        System.out.println("Login Wrong Username");

        //Act
        User ret = instance.login(sessionTest, addressTest, portTest, "foo", passwordBob);

        //Assert
        assertNull(ret);
    }

    /**
     * Test of login method, of class LoginController with wrong password.
     */
    @Test
    public void testLoginWrongPassword() {
        //Arrange
        System.out.println("Login Wrong Password");

        //Act
        User ret = instance.login(sessionTest, addressTest, portTest, usernameBob, "foo");

        //Assert
        assertNull(ret);
    }

    /**
     * Test of login method, of class LoginController with user already logged
     * in.
     */
    @Test
    public void testLoginLoggedUser() {
        //Arrange
        System.out.println("Login Logged User");
        onlineUserManager.addOnlineUser(new User(usernameBob, passwordBob, ""));

        //Act
        User ret = instance.login(sessionTest, addressTest, portTest, usernameBob, passwordBob);

        //Assert
        assertNull(ret);
    }

    /**
     * Test of login method, of class LoginController with username too short.
     */
    @Test
    public void testLoginShortUsername() {
        //Arrange
        System.out.println("Login Short Username");
        persistenceUser.addNewUser("", "foo", "");

        //Act
        User ret = instance.login(sessionTest, addressTest, portTest, "", "foo");

        //Assert
        assertNull(ret);
    }

    /**
     * Test of login method, of class LoginController with password too short.
     */
    @Test
    public void testLoginShortPassword() {
        //Arrange
        System.out.println("Login Short Password");
        persistenceUser.addNewUser("foo", "", "");

        //Act
        User ret = instance.login(sessionTest, addressTest, portTest, "foo", "");

        //Assert
        assertNull(ret);
    }

    /**
     * Test of login method, of class LoginController with null username.
     */
    @Test
    public void testLoginNullUsername() {
        //Arrange
        System.out.println("Login Null Username");
        persistenceUser.addNewUser(null, "foo", "");

        //Act
        User ret = instance.login(sessionTest, addressTest, portTest, null, "foo");

        //Assert
        assertNull(ret);
    }

    /**
     * Test of login method, of class LoginController with null password.
     */
    @Test
    public void testLoginNullPassword() {
        //Arrange
        System.out.println("Login Null Password");
        persistenceUser.addNewUser("foo", null, "");

        //Act
        User ret = instance.login(sessionTest, addressTest, portTest, "foo", null);

        //Assert
        assertNull(ret);
    }

    /**
     * Test of login method, of class LoginController for setting session
     * parameters.
     */
    @Test
    public void testLoginSessionParameters() {
        //Arrange
        System.out.println("Login Session Parameters");

        //Act
        User ret = instance.login(sessionTest, addressTest, portTest, usernameBob, passwordBob);

        //Assert
        assertEquals(ret.getSession(), sessionTest);
        assertEquals(ret.getAddress(), addressTest);
        assertEquals(ret.getPort(), sessionTest + 2010);
    }

    /**
     * Test of logout method, of class LoginController.
     */
    @Test
    public void testLogoutNormal() {
        //Arrange
        System.out.println("Logout Normal");
        onlineUserManager.addOnlineUser(new User(usernameBob, passwordBob, ""));

        //Act
        instance.logout(usernameBob);

        //Assert
        assertNull(onlineUserManager.findOnlineUser(usernameBob));
    }

    /**
     * Test of logout method, of class LoginController with non-existing username.
     */
    @Test
    public void testLogoutNonexisting() {
        //Arrange
        System.out.println("Logout Nonexisting");
        onlineUserManager.addOnlineUser(new User(usernameBob, passwordBob, ""));

        //Act
        instance.logout("foo");

        //Assert
        assertNotNull(onlineUserManager.findOnlineUser(usernameBob));

    }

    /**
     * Test of logout method, of class LoginController with username too short.
     */
    @Test
    public void testLogoutShort() {
        //Arrange
        System.out.println("Logout Short");
        onlineUserManager.addOnlineUser(new User(usernameBob, passwordBob, ""));

        //Act
        instance.logout("");

        //Assert
        assertNotNull(onlineUserManager.findOnlineUser(usernameBob));

    }

    /**
     * Test of logout method, of class LoginController with username not set.
     */
    @Test
    public void testLogoutNull() {
        //Arrange
        System.out.println("Logout Null");
        onlineUserManager.addOnlineUser(new User(usernameBob, passwordBob, ""));

        //Act
        instance.logout(null);

        //Assert
        assertNotNull(onlineUserManager.findOnlineUser(usernameBob));

    }
}
