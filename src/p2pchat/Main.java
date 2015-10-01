/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat;

import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String type;

        System.out.println("Run as server or peer? s/p");

        Scanner in = new Scanner(System.in);

        type = in.nextLine();

        P2PChat.getInstance().run(P2PChatType.valueOf(type));
    }
}
