package com;

/**
 * Created by bilal on 10/15/2016.
 */

import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;

import java.util.*;

public class Utils {
    public static void printIfDebug(String message) {
        if (!VaadinService.getCurrent().getDeploymentConfiguration().isProductionMode()) {
            System.out.println(message);
        }
    }

    public static void notImplemented() {
        Notification.show("Functionality not yet implemented", Notification.Type.WARNING_MESSAGE);
    }

    public static User getCurrentUser() {

        return (User) VaadinSession.getCurrent().getAttribute("user");
    }
    public static Auction getCurrentAuction() {

        return (Auction) VaadinSession.getCurrent().getAttribute("auction");
    }
}