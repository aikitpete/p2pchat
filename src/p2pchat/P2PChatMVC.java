/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p2pchat;

import p2pchat.controller.P2PChatController;
import p2pchat.model.P2PChatModel;
import p2pchat.view.P2PChatView;

/**
 *
 * @author USER
 */
public class P2PChatMVC {
    //    Create model, view, and controller.  They are
    //    created once here and passed to the parts that
    //    need them so there is only one copy of each.
    public static void run() {
        
        P2PChatModel      model      = P2PChatModel.getInstance();
        P2PChatView       view       = P2PChatView.getInstance();
        P2PChatController controller = P2PChatController.getInstance();
        model.processLocal("KAWA");
    }
}
