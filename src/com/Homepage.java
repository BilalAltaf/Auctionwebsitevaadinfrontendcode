package com;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.*;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Component;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Created by bilal on 10/15/2016.
 */
public class Homepage extends VerticalLayout implements View {
    private Service service;
    // This panel shows the main functionality in this view
    private Panel panel;
    private MenuBar menu;

    private TextField nameField;
    // This needs updating after constructor
    private MenuBar.MenuItem meetingItem;
    public Homepage(Service service)
    {
        this.service = service;

        panel = new Panel();

        setSpacing(true);
        //setMargin(true);

        menu = new MenuBar();
        menu.addItem("Home", (selectedItem) -> {
            showStartView();
        });

        menu.addItem("All Auction", (selectedItem) -> {
            showAllAuction();
        });

        menu.addItem("register", (selectedItem) -> {

            User user = Utils.getCurrentUser();
            if (user == null) {
                getUI().getNavigator().navigateTo("register");
            }
            else
            {
                getUI().getNavigator().navigateTo("main");
            }
        });
        menu.addItem("login", (selectedItem) -> {
            User user = Utils.getCurrentUser();
            if (user == null) {

                getUI().getNavigator().navigateTo("login");
            }
            else
            {
                getUI().getNavigator().navigateTo("main");
            }
        });
        menu.addStyleName("auction_homepage");
        addComponent(menu);
        addComponent(panel);
        VerticalLayout footer = new VerticalLayout();
        footer.setSpacing(true);
        footer.addStyleName("foo");
        Label commitment=new Label("Our Commitment");
        commitment.addStyleName("foo1");
        //footer.addComponent(commitment);
        Label tagline=new Label("Connecting People, Independently  without their Calendars.");
        tagline.addStyleName("foo2");
        // footer.addComponent(tagline);
        addComponent(footer);
        HorizontalLayout social_button=new HorizontalLayout();
        social_button.setWidth("15%");
        Button facebook=new Button();
        facebook.addClickListener(clickEvent -> {
            Page.getCurrent().open("https://www.facebook.com", "_self");
        });
        facebook.setIcon(new ThemeResource("../runo/icons/16/facebook.png"));
        facebook.addStyleName("Social_website");
        social_button.addComponent(facebook);
        Button twitter=new Button();
        twitter.addClickListener(clickEvent -> {
            Page.getCurrent().open("https://www.twitter.com", "_self");
        });
        twitter.setIcon(new ThemeResource("../runo/icons/16/twitter.png"));
        twitter.addStyleName("Social_website");
        social_button.addComponent(twitter);
        Button google=new Button();
        google.addClickListener(clickEvent -> {
            Page.getCurrent().open("https://plus.google.com/", "_self");
        });
        google.setIcon(new ThemeResource("../runo/icons/16/google.png"));
        google.addStyleName("Social_website");
        social_button.addComponent(google);
        footer.addComponent(social_button);
        Label copyright=new Label("©  2016 Auction Website. All rights reserved.");
        copyright.addStyleName("-footer-copyright");
        footer.addComponent(copyright);

}
    public void showStartView() {
        panel.setHeight("533px");
        panel.setContent(new StartView(service));
    }
    public void showAllAuction()
    {
        panel.setHeight("533px");
        panel.setContent(new AllAuctionView(service));
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        User user = Utils.getCurrentUser();

        // Is the user logged in?
        if (user != null) {
            getUI().getNavigator().navigateTo("main");


        } else {
            showStartView();
            }
            // User not logged in, throw them back to login page

            //Notification.show("Please log in to continue", Notification.Type.WARNING_MESSAGE);

    }
 }

