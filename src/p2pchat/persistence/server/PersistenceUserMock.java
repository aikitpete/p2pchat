/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat.persistence.server;

import p2pchat.model.ErrorType;
import p2pchat.model.User;

/**
 * Class for managing connaction with database
 *
 * @author USER
 */
public class PersistenceUserMock implements IPersistenceUser {

    //Constants
    private final static int MAXIMUM_USERS = 100;
    private final static int USER_LENGTH = 3;
    //Variables
    private String users[][];

    public PersistenceUserMock() {

        users = new String[MAXIMUM_USERS][3];

        users[0] = new String[]{"bob", "bob", "bob.jpg"};
        users[1] = new String[]{"alice", "alice", "alice.jpg"};
        
    }

    /**
     * Get user based on username
     *
     * @param name username
     * @return matching user instance
     */
    @Override
    public String[] findUserByName(String name) {

        String[] ret = null;

        for (int i = 0; i < MAXIMUM_USERS; i++) {

            if (users[i][0] != null && users[i][0].equals(name)) {

                ret = users[i];
                break;
            }
        }
        return ret;
    }

    /**
     * Add new user
     *
     * @param args user arguments
     */
    @Override
    public boolean addNewUser(String[] args) {
        return addNewUser(args[0], args[1], args[2]);
    }

    @Override
    public boolean addNewUser(String name, String password, String photoPath) {

        boolean ret = false;

        for (int i = 0; i < MAXIMUM_USERS; i++) {

            if (users[i][0] == null) {

                users[i][0] = name;
                users[i][1] = password;
                users[i][2] = photoPath;

                ret = true;
                System.out.println("Persistence: User " + name + " added");

                break;
            }
        }
        return ret;
    }

    @Override
    public boolean updateUser(String[] args) {
        boolean ret = false;

        for (int i = 0; i < MAXIMUM_USERS; i++) {

            if (users[i][0].equals(args[0])) {

                for (int j = 0; j < USER_LENGTH; j++) {
                    users[i][j] = args[j];
                }
                ret = true;
                System.out.println("Persistence: User " + args[0] + " updated");
                break;
            }
        }
        return ret;
    }

    @Override
    public boolean deleteUser(String username) {
        boolean ret = false;

        for (int i = 0; i < MAXIMUM_USERS; i++) {

            if (users[i][0].equals(username)) {

                users[i][0] = null;
                users[i][1] = null;
                users[i][2] = null;
                
                ret = true;

                System.out.println("Persistence: User " + username + " deleted");

                break;
            }
        }
        return ret;
    }
}
