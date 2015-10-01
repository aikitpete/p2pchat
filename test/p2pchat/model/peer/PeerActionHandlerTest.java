/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.model.peer;

import java.security.Permission;
import org.junit.*;
import static org.junit.Assert.*;
import p2pchat.connection.peer.IConnectionManagerPeer;
import p2pchat.connection.server.TransmissionRequest;
import p2pchat.mock.OnlineUserManagerPeerMock;
import p2pchat.mock.ConnectionManagerPeerMock;
import p2pchat.mock.P2PChatMock;
import p2pchat.mock.ViewManagerMock;
import p2pchat.model.*;
import p2pchat.view.IViewManager;
import p2pchat.view.ViewConstants;

/**
 *
 * @author USER
 */
public class PeerActionHandlerTest {

    ConnectionManagerPeerMock connectionPeerMock;
    OnlineUserManagerPeerMock accountPeerMock;
    PeerActionHandler instance;
    ViewManagerMock viewMock;
    P2PChatMock p2pChatMock;

    public PeerActionHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        connectionPeerMock = new ConnectionManagerPeerMock();
        accountPeerMock = new OnlineUserManagerPeerMock();
        viewMock = new ViewManagerMock();
        p2pChatMock = new P2PChatMock();
        instance = new PeerActionHandler(p2pChatMock, connectionPeerMock, accountPeerMock, viewMock);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of handleStatusAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleStatusActionOnlineFromOnline() {
        System.out.println("Handle Status Action Online from Online");
        //Arrange
        accountPeerMock.online = true;
        accountPeerMock.status = StatusType.ONLINE;
        String args[] = new String[1];
        args[0] = StatusType.ONLINE.toString();

        //Act
        instance.handleStatusAction(args);

        //Assert
        assertEquals(accountPeerMock.getStatus(), StatusType.ONLINE);//leave status online
    }

    /**
     * Test of handleStatusAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleStatusActionOnlineFromOffline() {
        System.out.println("Handle Status Action Online from Offline");
        //Arrange
        viewMock.display = false;
        accountPeerMock.online = false;
        accountPeerMock.status = StatusType.OFFLINE;
        String args[] = new String[1];
        args[0] = StatusType.ONLINE.toString();

        //Act
        instance.handleStatusAction(args);

        //Assert
        assertEquals(viewMock.display, true);//display login form
    }

    /**
     * Test of handleStatusAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleStatusActionOfflineFromOnline() {
        System.out.println("Handle Status Action Offline from Online");
        //Arrange
        connectionPeerMock.closePeerConnections = false;
        accountPeerMock.online = true;
        accountPeerMock.status = StatusType.ONLINE;
        accountPeerMock.getCurrentUser = "username";
        String args[] = new String[1];
        args[0] = StatusType.OFFLINE.toString();

        //Act
        instance.handleStatusAction(args);

        //Assert
        assertEquals(connectionPeerMock.sendToServer[3], "username");//send disconnect message to server
        assertEquals(connectionPeerMock.closePeerConnections, true);//close connection
        assertEquals(accountPeerMock.setStatus, StatusType.OFFLINE);//change status to offline
        assertEquals(viewMock.setStatus, StatusType.OFFLINE);//update view to offline
        assertEquals(viewMock.setStatusCaptionStatus, StatusType.OFFLINE);//update view caption to offline
        assertNull(viewMock.setStatusCaptionUsername);//update view caption to offline
        assertNull(viewMock.setStatusCaptionUsers);//update view caption to offline
        assertEquals(viewMock.addMessage, ViewConstants.LOGOUT_MESSAGE);//display logout message
    }

    /**
     * Test of handleStatusAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleStatusActionOfflineFromOffline() {
        System.out.println("Handle Status Action Offline from Offline");
        //Arrange
        connectionPeerMock.closePeerConnections = false;
        accountPeerMock.online = false;
        accountPeerMock.status = StatusType.OFFLINE;
        accountPeerMock.getCurrentUser = "username";
        String args[] = new String[1];
        args[0] = StatusType.OFFLINE.toString();

        //Act
        instance.handleStatusAction(args);

        //Assert
        assertEquals(accountPeerMock.getStatus(), StatusType.OFFLINE);// go offline
    }

    /**
     * Test of handleSendAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleSendActionNormal() {
        System.out.println("Handle Send Action Normal");
        //Arrange
        User users[] = new User[]{new User("a", "a", "a"), new User("b", "b", "b"), new User("c", "c", "c")};
        accountPeerMock.getCurrentUser = "username";
        accountPeerMock.online = true;
        accountPeerMock.getOnlineUsersNumber = users.length;
        accountPeerMock.getUsers = users;
        viewMock.getUserInput = "input";
        connectionPeerMock.sendToPeer = new TransmissionRequest[users.length];


        //Act
        instance.handleSendAction();

        //Assert
        assertEquals(viewMock.setUserInput, PeerActionHandler.DEFAULT_INPUT);//reset input
        assertEquals(connectionPeerMock.sendToPeerCount, users.length);//send to online peers
        for (int i = 0; i < connectionPeerMock.sendToPeerCount; i++) {
            assertEquals(connectionPeerMock.sendToPeer[i].getData()[0], MessageType.PEER_TO_PEER_TYPE.toString());
            assertEquals(connectionPeerMock.sendToPeer[i].getData()[1], MessageSubtype.CHAT_SUBTYPE.toString());
            assertEquals(connectionPeerMock.sendToPeer[i].getData()[2], MessageResult.NONE_RESULT.toString());
            assertEquals(connectionPeerMock.sendToPeer[i].getData()[3], accountPeerMock.getCurrentUser);
            assertEquals(connectionPeerMock.sendToPeer[i].getData()[4], " " + users[0].getUsername() + " " + users[1].getUsername() + " " + users[2].getUsername());
        }
    }

    /**
     * Test of handleSendAction method, of class PeerActionHandler with empty
     * online user list.
     */
    @Test
    public void testHandleSendActionEmpty() {
        System.out.println("Handle Send Action Empty");
        //Arrange
        User users[] = new User[]{};
        accountPeerMock.getCurrentUser = "username";
        accountPeerMock.online = true;
        accountPeerMock.getOnlineUsersNumber = users.length;
        accountPeerMock.getUsers = users;
        viewMock.getUserInput = "input";
        connectionPeerMock.sendToPeer = new TransmissionRequest[users.length];


        //Act
        instance.handleSendAction();

        //Assert
        assertEquals(viewMock.displayError, ErrorType.CHAT_EMPTY);//display empty chat error
    }

    /**
     * Test of handleSendAction method, of class PeerActionHandler with empty
     * online user list.
     */
    @Test
    public void testHandleSendActionNotOnline() {
        System.out.println("Handle Send Action Not Online");
        //Arrange
        User users[] = new User[]{new User("a", "a", "a"), new User("b", "b", "b"), new User("c", "c", "c")};
        accountPeerMock.getCurrentUser = "username";
        accountPeerMock.online = false;
        accountPeerMock.getOnlineUsersNumber = users.length;
        accountPeerMock.getUsers = users;
        viewMock.getUserInput = "input";
        connectionPeerMock.sendToPeer = new TransmissionRequest[users.length];


        //Act
        instance.handleSendAction();

        //Assert
        assertEquals(viewMock.displayError, ErrorType.NOT_ONLINE);//display not online error
    }

    /**
     * Test of handleSendAction method, of class PeerActionHandler with short
     * input.
     */
    @Test
    public void testHandleSendActionShortInput() {
        System.out.println("Handle Send Action Short Input");
        //Arrange
        User users[] = new User[]{new User("a", "a", "a"), new User("b", "b", "b"), new User("c", "c", "c")};
        accountPeerMock.getCurrentUser = "username";
        accountPeerMock.online = true;
        accountPeerMock.getOnlineUsersNumber = users.length;
        accountPeerMock.getUsers = users;
        viewMock.getUserInput = "";
        connectionPeerMock.sendToPeer = new TransmissionRequest[users.length];


        //Act
        instance.handleSendAction();

        //Assert
        assertEquals(viewMock.displayError, ErrorType.MESSAGE_LENGTH_SHORT);//display short input error
    }

    /**
     * Test of handleSendAction method, of class PeerActionHandler with long
     * input.
     */
    @Test
    public void testHandleSendActionLongInput() {
        System.out.println("Handle Send Action Long Input");
        //Arrange
        User users[] = new User[]{new User("a", "a", "a"), new User("b", "b", "b"), new User("c", "c", "c")};
        accountPeerMock.getCurrentUser = "username";
        accountPeerMock.online = true;
        accountPeerMock.getOnlineUsersNumber = users.length;
        accountPeerMock.getUsers = users;
        viewMock.getUserInput = "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbd";//long input
        connectionPeerMock.sendToPeer = new TransmissionRequest[users.length];


        //Act
        instance.handleSendAction();

        //Assert
        assertEquals(viewMock.displayError, ErrorType.MESSAGE_LENGTH_LONG);//display long input error
    }

    /**
     * Test of handleQuitAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleQuitAction() {
        System.out.println("Handle Quit Action");
        //Act
        instance.handleQuitAction();

        //Assert
        assertEquals(p2pChatMock.exited, true);//check if exit was called
    }

    /**
     * Test of handleSettingsAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleSettingsAction() {
        System.out.println("Handle Settings Action");
        //Arrange
        String address = "localhost";
        String port = "2000";

        //Act
        instance.handleSettingsAction(address, port);

        //Assert
        assertEquals(connectionPeerMock.setServerAddress, "localhost");//check server address
        assertEquals(connectionPeerMock.setServerPort, 2000);//check server port
    }

    /**
     * Test of handleSettingsAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleSettingsActionPortLow() {
        System.out.println("Handle Settings Action Port Low");
        //Arrange
        String address = "localhost";
        String port = "-1";

        //Act
        instance.handleSettingsAction(address, port);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.INVALID_CONTENT);//display invalid content error
        assertNull(connectionPeerMock.setServerAddress);//check server address
    }

    /**
     * Test of handleSettingsAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleSettingsActionPortHigh() {
        System.out.println("Handle Settings Action Port High");
        //Arrange
        String address = "localhost";
        String port = "65536";

        //Act
        instance.handleSettingsAction(address, port);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.INVALID_CONTENT);//display invalid content error
        assertNull(connectionPeerMock.setServerAddress);//check server address
    }

    /**
     * Test of handleSettingsAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleSettingsActionPortInvalidFormat() {
        System.out.println("Handle Settings Action Port Invalid Format");
        //Arrange
        String address = "localhost";
        String port = "foo";

        //Act
        instance.handleSettingsAction(address, port);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.INVALID_CONTENT);//display invalid content error
        assertNull(connectionPeerMock.setServerAddress);//check server address
    }

    /**
     * Test of handleSettingsAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleSettingsActionAddressLengthShort() {
        System.out.println("Handle Settings Action Address Length Short");
        //Arrange
        String address = "";
        String port = "2000";

        //Act
        instance.handleSettingsAction(address, port);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.INVALID_CONTENT);//display invalid content error
        assertNull(connectionPeerMock.setServerAddress);//check server address
    }

    /**
     * Test of handleSettingsAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleSettingsPortLengthShort() {
        System.out.println("Handle Settings Port Length Short");
        //Arrange
        String address = "localhost";
        String port = "";

        //Act
        instance.handleSettingsAction(address, port);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.INVALID_CONTENT);//display invalid content error
        assertNull(connectionPeerMock.setServerAddress);//check server address
    }

    /**
     * Test of handleSettingsAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleSettingsAddressNull() {
        System.out.println("Handle Settings Address Null");
        //Arrange
        String address = null;
        String port = "2000";

        //Act
        instance.handleSettingsAction(address, port);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.INVALID_CONTENT);//display invalid content error
        assertNull(connectionPeerMock.setServerAddress);//check server address
    }

    /**
     * Test of handleSettingsAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleSettingsPortNull() {
        System.out.println("Handle Settings Port Null");
        //Arrange
        String address = "localhost";
        String port = null;

        //Act
        instance.handleSettingsAction(address, port);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.INVALID_CONTENT);//display invalid content error
        assertNull(connectionPeerMock.setServerAddress);//check server address
    }

    /**
     * Test of handleRegisterAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRegisterActionUsernameNull() {
        System.out.println("Handle Register Action Username Null");
        //Arrange
        String username = null;
        String password = "bob";
        String rePassword = "bob";

        //Act
        instance.handleRegisterAction(username, password, rePassword);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleRegisterAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRegisterActionPasswordNull() {
        System.out.println("Handle Register Action Password Null");
        //Arrange
        String username = "bob";
        String password = null;
        String rePassword = "bob";

        //Act
        instance.handleRegisterAction(username, password, rePassword);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleRegisterAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRegisterActionRePasswordNull() {
        System.out.println("Handle Register Action rePassword Null");
        //Arrange
        String username = "bob";
        String password = "bob";
        String rePassword = null;

        //Act
        instance.handleRegisterAction(username, password, rePassword);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleRegisterAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRegisterUsernameShort() {
        System.out.println("Handle Register Action Username Short");
        //Arrange
        String username = "";
        String password = "bob";
        String rePassword = "bob";

        //Act
        instance.handleRegisterAction(username, password, rePassword);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleRegisterAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRegisterActionPasswordShort() {
        System.out.println("Handle Register Action Password Short");
        //Arrange
        String username = "bob";
        String password = "";
        String rePassword = "bob";

        //Act
        instance.handleRegisterAction(username, password, rePassword);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleRegisterAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRegisterRePasswordShort() {
        System.out.println("Handle Register Action rePassword Short");
        //Arrange
        String username = "bob";
        String password = "bob";
        String rePassword = "";

        //Act
        instance.handleRegisterAction(username, password, rePassword);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleRegisterAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRegisterActionDontMatch() {
        System.out.println("Handle Register Action");
        //Arrange
        String username = "bob";
        String password = "bob";
        String rePassword = "foo";

        //Act
        instance.handleRegisterAction(username, password, rePassword);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.PASSWORDS_DONT_MATCH);//display blank field error
    }

    /**
     * Test of handleAddFriendAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleAddFriendAction() {
        System.out.println("Handle Add Friend Action");
        //Arrange
        accountPeerMock.getCurrentUser = "onlineuser";
        accountPeerMock.findOnlineUserUser = null;
        String friendname = "bob";

        //Act
        instance.handleAddFriendAction(friendname);

        //Assert
        assertEquals(connectionPeerMock.sendToServer[0], MessageType.PEER_TO_SERVER_TYPE.toString());
        assertEquals(connectionPeerMock.sendToServer[1], MessageSubtype.ADD_FRIEND_REQUEST_SUBTYPE.toString());
        assertEquals(connectionPeerMock.sendToServer[2], MessageResult.NONE_RESULT.toString());
        assertEquals(connectionPeerMock.sendToServer[3], accountPeerMock.getCurrentUser);
        assertEquals(connectionPeerMock.sendToServer[4], friendname);
    }

    /**
     * Test of handleAddFriendAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleAddFriendActionShortFriendname() {
        System.out.println("Handle Add Friend Action Short Friendname");
        //Arrange
        accountPeerMock.getCurrentUser = "onlineuser";
        accountPeerMock.findOnlineUserUser = null;
        String friendname = "";

        //Act
        instance.handleAddFriendAction(friendname);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleAddFriendAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleAddFriendActionAddOwnself() {
        System.out.println("Handle Add Friend Action Add Ownself");
        //Arrange
        accountPeerMock.getCurrentUser = "bob";
        accountPeerMock.findOnlineUserUser = null;
        String friendname = "bob";

        //Act
        instance.handleAddFriendAction(friendname);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.FRIEND_INPUT_OWNSELF);//display add ownself error
    }

    /**
     * Test of handleAddFriendAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleAddFriendActionAddExisting() {
        System.out.println("Handle Add Friend Action Add Existing");
        //Arrange
        accountPeerMock.getCurrentUser = "onlineuser";
        String friendname = "bob";
        accountPeerMock.findOnlineUserUser = new User(friendname, "", "");

        //Act
        instance.handleAddFriendAction(friendname);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.FRIEND_ALREADY_CHATTING);//display add friend already chatting error
    }

    /**
     * Test of handleRemoveFriendAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRemoveFriendAction() {
        System.out.println("Handle Remove Friend Action");
        //Arrange
        accountPeerMock.getCurrentUser = "onlineuser";
        String friendname = "bob";
        accountPeerMock.findOnlineUserUser = new User(friendname, "", "");

        //Act
        instance.handleRemoveFriendAction(friendname);

        //Assert
        assertEquals(connectionPeerMock.sendToServer[0], MessageType.PEER_TO_SERVER_TYPE.toString());
        assertEquals(connectionPeerMock.sendToServer[1], MessageSubtype.REMOVE_FRIEND_REQUEST_SUBTYPE.toString());
        assertEquals(connectionPeerMock.sendToServer[2], MessageResult.NONE_RESULT.toString());
        assertEquals(connectionPeerMock.sendToServer[3], accountPeerMock.getCurrentUser);
        assertEquals(connectionPeerMock.sendToServer[4], friendname);
    }

    /**
     * Test of handleRemoveFriendAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRemoveFriendActionNullFriendname() {
        System.out.println("Handle Remove Friend Action Null Friendname");
        //Arrange
        accountPeerMock.getCurrentUser = "onlineuser";
        String friendname = null;
        accountPeerMock.findOnlineUserUser = new User(friendname, "", "");

        //Act
        instance.handleRemoveFriendAction(friendname);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleRemoveFriendAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRemoveFriendActionShortFriendname() {
        System.out.println("Handle Remove Friend Action Null Friendname");
        //Arrange
        accountPeerMock.getCurrentUser = "onlineuser";
        String friendname = "";
        accountPeerMock.findOnlineUserUser = new User(friendname, "", "");

        //Act
        instance.handleRemoveFriendAction(friendname);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleRemoveFriendAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRemoveFriendActionRemoveOwnself() {
        System.out.println("Handle Remove Friend Action Remove Ownself");
        //Arrange
        accountPeerMock.getCurrentUser = "bob";
        String friendname = "bob";
        accountPeerMock.findOnlineUserUser = new User(friendname, "", "");

        //Act
        instance.handleRemoveFriendAction(friendname);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.FRIEND_INPUT_OWNSELF);//display input ownself error
    }

    /**
     * Test of handleRemoveFriendAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleRemoveFriendActionNotChatting() {
        System.out.println("Handle Remove Friend Action Null Friendname");
        //Arrange
        accountPeerMock.getCurrentUser = "onlineuser";
        String friendname = "bob";
        accountPeerMock.findOnlineUserUser = null;

        //Act
        instance.handleRemoveFriendAction(friendname);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.FRIEND_NOT_CHATTING);//display friend not chatting error
    }

    /**
     * Test of handleLoginAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleLoginAction() {
        System.out.println("Handle Login Action");
        //Arrange
        String username = "bob";
        String password = "bob";

        //Act
        instance.handleLoginAction(username, password);

        //Assert
        assertEquals(connectionPeerMock.sendToServer[0], MessageType.PEER_TO_SERVER_TYPE.toString());
        assertEquals(connectionPeerMock.sendToServer[1], MessageSubtype.LOGIN_REQUEST_SUBTYPE.toString());
        assertEquals(connectionPeerMock.sendToServer[2], MessageResult.NONE_RESULT.toString());
        assertEquals(connectionPeerMock.sendToServer[3], username);
        assertEquals(connectionPeerMock.sendToServer[4], password);
    }

    /**
     * Test of handleLoginAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleLoginActionUsernameNull() {
        System.out.println("Handle Login Action Username Null");
        //Arrange
        String username = null;
        String password = "bob";

        //Act
        instance.handleLoginAction(username, password);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleLoginAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleLoginActionUsernameShort() {
        System.out.println("Handle Login Action Username Short");
        //Arrange
        String username = "";
        String password = "bob";

        //Act
        instance.handleLoginAction(username, password);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleLoginAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleLoginActionPasswordNull() {
        System.out.println("Handle Login Action Password Null");
        //Arrange
        String username = "bob";
        String password = null;

        //Act
        instance.handleLoginAction(username, password);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }

    /**
     * Test of handleLoginAction method, of class PeerActionHandler.
     */
    @Test
    public void testHandleLoginActionPasswordShort() {
        System.out.println("Handle Login ActionPasswordShort");
        //Arrange
        String username = "bob";
        String password = "";

        //Act
        instance.handleLoginAction(username, password);

        //Assert
        assertEquals(viewMock.displayError, ErrorType.BLANK_FIELD);//display blank field error
    }
}
