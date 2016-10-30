package com;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

@Theme("garantimeui")
@Title("AuctionWebsite")

/**
 * Created by bilal on 10/15/2016.
 */
public class MyVaadinAuctionApplication extends UI {
    private Service service;
    private Navigator navigator;

    @Override
    protected void init(VaadinRequest request) {
        service = new HTTPService();
        //service = new TestService();

        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        setContent(layout);

        Label appNameLabel = new Label("Auction website");
        layout.addComponent(appNameLabel);

        Panel viewPanel = new Panel();
        layout.addComponent(viewPanel);

        navigator = new Navigator(this, this);
        navigator.addView("", new Homepage(service));
        navigator.addView("home", new Homepage(service));
        navigator.addView("login", new LoginView(service));
        navigator.addView("start", new StartView(service));
        navigator.addView("create", new CreateAuctionView(service));
       // navigator.addView("editprofile", new editprofileView(service));
        //navigator.addView("allauction", new AuctionView(service));
        navigator.addView("register", new RegisterView(service));
        navigator.addView("main", new mainpage(service));

       /* navigator.addView("main", new MainView(service));*/
    }
}
