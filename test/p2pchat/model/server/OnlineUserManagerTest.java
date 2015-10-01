/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.server;

import org.junit.*;
import static org.junit.Assert.*;
import p2pchat.model.ErrorType;
import p2pchat.model.StatusType;
import p2pchat.model.User;
import p2pchat.model.peer.PeerManager;

/**
 *
 * @author USER
 */
public class OnlineUserManagerTest {

    OnlineUserManager instance;

    public OnlineUserManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new OnlineUserManager();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOnlineUser method, of class OnlineUserManager.
     */
    @Test
    public void testAddOnlineUser() {
        System.out.println("Add Online User");
        //Arrange
        User user = new User("bob", "");
        boolean result;

        //Act
        result = instance.addOnlineUser(user);

        //Assert
        assertEquals(result, true);
        assertEquals(instance.getOnlineUsersNumber(), 1);
    }

    /**
     * Test of addOnlineUser method, of class OnlineUserManager.
     */
    @Test
    public void testAddOnlineUserAlreadyChatting() {
        System.out.println("Add Online User");
        //Arrange
        User user = new User("bob", "");
        boolean result;
        instance.addOnlineUser(user);

        //Act
        result = instance.addOnlineUser(user);

        //Assert
        assertEquals(result, false);
        assertEquals(instance.getOnlineUsersNumber(), 1);
    }

    /**
     * Test of addOnlineUser method, of class OnlineUserManager.
     */
    @Test
    public void testAddOnlineUserFull() {
        System.out.println("Add Online User");
        //Arrange
        User user = new User("bob", "");
        boolean result;
        for (int i = 0; i < OnlineUserManager.MAXIMUM_ONLINE_USERS; i++) {
            instance.addOnlineUser(new User("user" + i, ""));
        }

        //Act
        result = instance.addOnlineUser(user);

        //Assert
        assertEquals(result, false);
        assertEquals(instance.getOnlineUsersNumber(), OnlineUserManager.MAXIMUM_ONLINE_USERS);
    }

    /**
     * Test of isOnline method, of class OnlineUserManager.
     */
    @Test
    public void testIsOnline() {
        System.out.println("Is Online");
        //Arrange
        User user = new User("bob", "");
        instance.addOnlineUser(user);
        boolean result;

        //Act
        result = instance.isOnline("bob");

        //Assert
        assertTrue(result);
    }

    /**
     * Test of isOnline method, of class OnlineUserManager.
     */
    @Test
    public void testIsOnlineNull() {
        System.out.println("Is Online Null");
        //Arrange
        User user = new User("bob", "");
        instance.addOnlineUser(user);
        boolean result;

        //Act
        result = instance.isOnline(null);

        //Assert
        assertFalse(result);
    }

    /**
     * Test of isOnline method, of class OnlineUserManager.
     */
    @Test
    public void testIsOnlineNotMatching() {
        System.out.println("Is Online Not Matching");
        //Arrange
        User user = new User("foo", "");
        instance.addOnlineUser(user);
        boolean result;

        //Act
        result = instance.isOnline("bob");

        //Assert
        assertFalse(result);
    }

    /**
     * Test of logout method, of class OnlineUserManager.
     */
    @Test
    public void testLogout() {
        System.out.println("Logout");
        //Arrange
        User user = new User("bob", "");
        instance.addOnlineUser(user);
        boolean result;

        //Act
        instance.logout("bob");

        //Assert
        assertEquals(instance.isOnline("bob"), false);
    }

    /**
     * Test of logout method, of class OnlineUserManager.
     */
    @Test
    public void testLogoutNull() {
        System.out.println("Logout Null");
        //Arrange
        User user = new User("bob", "");
        instance.addOnlineUser(user);
        boolean result;

        //Act
        instance.logout(null);

        //Assert
        assertEquals(instance.isOnline("bob"), true);
    }

    /**
     * Test of logout method, of class OnlineUserManager.
     */
    @Test
    public void testLogoutNotMatching() {
        System.out.println("Logout Not Matching");
        //Arrange
        User user = new User("bob", "");
        instance.addOnlineUser(user);
        boolean result;

        //Act
        instance.logout("foo");

        //Assert
        assertEquals(instance.isOnline("bob"), true);
    }

    /**
     * Test of endSession method, of class OnlineUserManager.
     */
    @Test
    public void testEndSession() {
        System.out.println("End Session");
        //Arrange
        User user = new User("bob", "");
        user.setSession(2);
        instance.addOnlineUser(user);
        User result;

        //Act
        result = instance.endSession(2);

        //Assert
        assertEquals(instance.isOnline("bob"), false);
        assertEquals(result.getUsername(), "bob");
    }
    
        /**
     * Test of endSession method, of class OnlineUserManager.
     */
    @Test
    public void testEndSessionNotMatching() {
        System.out.println("End Session Not Matching");
        //Arrange
        User user = new User("bob", "");
        user.setSession(2);
        instance.addOnlineUser(user);
        User result;

        //Act
        result = instance.endSession(6);

        //Assert
        assertEquals(instance.isOnline("bob"), true);
        assertNull(result);
    }
    
    /**
     * Test of findOnlineUser method, of class OnlineUserManager.
     */
    @Test
    public void testFindOnlineUser() {
        System.out.println("Find Online User");
        //Arrange
        User user = new User("bob", "");
        instance.addOnlineUser(user);
        User result;

        //Act
        result = instance.findOnlineUser("bob");

        //Assert
        assertEquals(result.getUsername(), "bob");
    }

        /**
     * Test of findOnlineUser method, of class OnlineUserManager.
     */
    @Test
    public void testFindOnlineUserNotMatching() {
        System.out.println("Find Online User Not Matchinf");
        //Arrange
        User user = new User("bob", "");
        instance.addOnlineUser(user);
        User result;

        //Act
        result = instance.findOnlineUser("foo");

        //Assert
        assertNull(result);
    }
    
    /**
     * Test of getOnlineUsers method, of class OnlineUserManager.
     */
    @Test
    public void testGetOnlineUsers() {
        System.out.println("Get Online Users");
        //Arrange
        User result[];

        //Act
        result = instance.getOnlineUsers();

        //Assert
        assertNotNull(result);
    }
    
        /**
     * Test of getOnlineUsersNumber method, of class OnlineUserManager.
     */
    @Test
    public void testGetOnlineUsersNumber() {
        System.out.println("Get Online Users Number");
        //Arrange
        String address = "localhost";
        int port = 2010;
        instance.addOnlineUser(new User("user1", ""));//add users
        instance.addOnlineUser(new User("user2", ""));
        instance.addOnlineUser(new User("user3", ""));
        int onlineUsersNumber;

        //Act
        onlineUsersNumber = instance.getOnlineUsersNumber();

        //Assert
        assertEquals(onlineUsersNumber, 3);
    }

    /**
     * Test of getOnlineUsersNumber method, of class OnlineUserManager.
     */
    @Test
    public void testGetOnlineUsersNumberRandom() {
        System.out.println("Get Online Users Number Random");
        //Arrange
        String address = "localhost";
        int port = 2010;
        instance.addOnlineUser(new User("user1", ""));//randomly add/remove users
        instance.addOnlineUser(new User("user2", ""));
        instance.logout("user1");
        instance.addOnlineUser(new User("user3", ""));
        instance.addOnlineUser(new User("user4", ""));
        instance.logout("user2");
        instance.addOnlineUser(new User("user2", ""));
        int onlineUsersNumber;

        //Act
        onlineUsersNumber = instance.getOnlineUsersNumber();

        //Assert
        assertEquals(onlineUsersNumber, 3);
    }
}
