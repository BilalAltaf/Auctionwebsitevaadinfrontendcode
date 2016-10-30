package com;
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
import com.vaadin.navigator.View;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by bilal on 10/17/2016.
 */




public class mainpage extends VerticalLayout implements View {
    private Service service;
    // This panel shows the main functionality in this view
    private Panel panel;
    private MenuBar menu;

    // This needs updating after constructor
    public mainpage(Service service)
    {
        this.service = service;

        panel = new Panel();
        panel.addStyleName("startview");

        setSpacing(true);
        //setMargin(true);

        menu = new MenuBar();
        menu.addItem("Home", (selectedItem) -> {

            showStartView();
        });
        menu.addItem("create Auction", (selectedItem) -> {
            User user = Utils.getCurrentUser();
            if (user!=null && user.getIssuperuser() == true)
            {
                Notification n = new Notification("Administrator cannot create Auction!");
                n.setDelayMsec(3000);
                n.show(Page.getCurrent());
            }
            else {
                panel.setContent(new CreateAuctionView(service));
            }
        });
        menu.addItem("All Auction", (selectedItem) -> {
            showAllAuction();
        });

        menu.addItem("My Auctions", (selectedItem) -> {
            showMyAuctionView();
        });

        menu.addItem("Edit Profile", (selectedItem) -> {
            panel.setContent(new editprofileView(service));
        });menu.addItem("logout", (selectedItem) -> {
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
        });
        menu.addStyleName("auction_mainpage");
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
    public void showStartView()
    {
        panel.setHeight("533px");
        panel.setContent(new StartView(service));
    }
    public void showAllAuction()
    {
        panel.setHeight("533px");
        panel.setContent(new AllAuctionView(service));
    }
    public void showMyAuctionView()
    {
        panel.setHeight("533px");
        panel.setContent(new MyAuctionView(service));
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        User user = Utils.getCurrentUser();
        if (user == null) {
            getUI().getNavigator().navigateTo("home");
        }
        else {
            showStartView();
        }
    }
}

