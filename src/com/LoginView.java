package com;

/**
 * Created by bilal on 10/15/2016.
 */

import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.*;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.ValoTheme;

import java.awt.*;
import java.awt.GridLayout;
import java.io.File;

public class LoginView extends VerticalLayout implements View {
    private Service service;

    public LoginView(Service service) {
        this.service = service;
        setSpacing(true);
        setMargin(true);
        /*ExternalResource resource = new ExternalResource("GuarantimeUI/image/11.jpg");
        ThemeResource resource = new ThemeResource("image/11.jpg");*/


        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        ThemeResource resource = new ThemeResource("../garantimeui/image/logo.jpg");
        Image image = new Image("",
                resource);
        image.setWidth("90px");
        image.setHeight("90px");
        layout.addComponent(image);
        layout.setComponentAlignment(image, Alignment.TOP_CENTER);
        Label pageHeadingLabel = new Label("Log into your Auction Website account.");
        pageHeadingLabel.addStyleName(ValoTheme.LABEL_BOLD);
        //use success style for guarantime
        layout.addComponent(pageHeadingLabel);
        Label pageHeadingLabel1 = new Label(".");
        pageHeadingLabel1.setVisible(false);
        layout.addComponent(pageHeadingLabel1);
        layout.setComponentAlignment(pageHeadingLabel, Alignment.TOP_CENTER);
        layout.setComponentAlignment(pageHeadingLabel1, Alignment.TOP_CENTER);
        Panel panel = new Panel(layout);
        //panel.addStyleName(ValoTheme.PANEL_WELL);
        addComponent(panel);
        panel.setSizeUndefined();
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        TextField emailField = new TextField("Enter your Username to get started.");
        emailField.setWidth("374px");
        emailField.setRequired(true);
        emailField.focus();
        emailField.setInputPrompt("Username");
        emailField.setIcon(FontAwesome.USER);
        layout.addComponent(emailField);
        PasswordField passwordField = new PasswordField("Enter your password.");
        passwordField.setInputPrompt("password");
        passwordField.setWidth("374px");
        passwordField.setRequired(true);
        passwordField.setIcon(FontAwesome.LOCK);
        layout.addComponent(passwordField);
        HorizontalLayout horizontal = new HorizontalLayout();
        horizontal.setSpacing(true);
        Button loginButton = new Button("Login");
        loginButton.setClickShortcut(KeyCode.ENTER);
        loginButton.addStyleName("new");
        layout.addComponent(loginButton);
        loginButton.addClickListener(clickEvent -> {
            emailField.setVisible(true);
            passwordField.setVisible(true);
            try {
                String message =service.login(emailField.getValue(), passwordField.getValue());
                if (message.equals("")) {

                    getUI().getNavigator().navigateTo("main");

                }
                else {
                    emailField.clear();
                    passwordField.clear();
                    Notification n = new Notification(""+message);
                    n.setDelayMsec(3000);
                    n.show(Page.getCurrent());
                }

            } catch (Exception e) {


            }
        });
        HorizontalLayout register_line=new HorizontalLayout();
        register_line.setSpacing(true);
        Link register= new Link("Back to Homepage", new ExternalResource("#!home"));
        Link registerLink = new Link("Sign up.", new ExternalResource("#!register"));
        register_line.addComponent(register);
        register_line.addComponent(registerLink);
        layout.addComponent(register_line);



//        forgetpassword.setVisible(false);

    }

    @Override
    public void enter(ViewChangeEvent event) {
        User user = Utils.getCurrentUser();
        if (user != null) {

            getUI().getNavigator().navigateTo("main");
        }
    }
}

