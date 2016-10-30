package com;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class RegisterView extends VerticalLayout implements View {
    private Service service;

    public RegisterView(Service service) {
        this.service = service;
        setSpacing(true);
        setMargin(true);
        Label pageHeadingLabel = new Label("Sign up for Auction website");
        pageHeadingLabel.addStyleName(ValoTheme.LABEL_BOLD);
        pageHeadingLabel.addStyleName(ValoTheme.DATEFIELD_ALIGN_CENTER);
        //setComponentAlignment(pageHeadingLabel, Alignment.MIDDLE_CENTER);
        //use success style for guarantime
        addComponent(pageHeadingLabel);
        VerticalLayout layout = new VerticalLayout();
        Panel panel = new Panel(layout);
        addComponent(panel);
        panel.setSizeUndefined();
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        layout.setSpacing(true);
        layout.setMargin(true);

        TextField firstNameField = new TextField("First Name");
        firstNameField.setRequired(true);
        firstNameField.setWidth("280px");
        layout.addComponent(firstNameField);

        TextField lastNameField = new TextField("Last Name");
        lastNameField.setRequired(true);
        lastNameField.setWidth("280px");
        layout.addComponent(lastNameField);



        TextField usernameField = new TextField("Username");
        usernameField.setRequired(true);
        usernameField.setWidth("280px");
        layout.addComponent(usernameField);

        TextField emailField = new TextField("Email Address");
        emailField.setRequired(true);
        emailField.addValidator(new EmailValidator("Please provide a valid e-mail address"));
        emailField.setIcon(FontAwesome.ENVELOPE);
        emailField.setWidth("280px");
        layout.addComponent(emailField);

        PasswordField passwordField = new PasswordField("Password");
        passwordField.setRequired(true);
        passwordField.setWidth("280px");
        passwordField.setIcon(FontAwesome.LOCK);
        layout.addComponent(passwordField);

        PasswordField confirmPasswordField = new PasswordField("Confirm password");
        confirmPasswordField.setRequired(true);
        confirmPasswordField.setWidth("280px");
        confirmPasswordField.setIcon(FontAwesome.LOCK);
        // Check that both password fields match
        confirmPasswordField.addValidator(value -> {
            if (!(value instanceof String ) || !((String) value).equals(passwordField.getValue())) {
                throw new Validator.InvalidValueException("Please make sure to type the same password in both fields");
            }
        });
        layout.addComponent(confirmPasswordField);

        CheckBox tocCheckbox = new CheckBox("I agree to the terms and conditions");
        tocCheckbox.setRequired(true);
        layout.addComponent(tocCheckbox);

        Button registerButton = new Button("Register");
        registerButton.addStyleName("new");
        registerButton.setClickShortcut(KeyCode.ENTER);
        registerButton.addClickListener(clickEvent -> {
            // Do the fields have valid data?
            try {
                usernameField.validate();
                passwordField.validate();
                confirmPasswordField.validate();
                tocCheckbox.validate();
                emailField.validate();
                firstNameField.validate();
                lastNameField.validate();

            } catch (Validator.InvalidValueException e) {
                emailField.setValidationVisible(true);
                emailField.setRequiredError("Field required");
                lastNameField.setValidationVisible(true);
                lastNameField.setRequiredError("Field required");
                firstNameField.setValidationVisible(true);
                firstNameField.setRequiredError("Field required");
                usernameField.setValidationVisible(true);
                usernameField.setRequiredError("Field required");
                passwordField.setValidationVisible(true);
                passwordField.setRequiredError("Field required");
                confirmPasswordField.setValidationVisible(true);
                confirmPasswordField.setRequiredError("Field required");
                tocCheckbox.setValidationVisible(true);
                tocCheckbox.setRequiredError("please mark the checkbox to proceed");
                Notification.show(e.getMessage(), Notification.Type.WARNING_MESSAGE);
                return;
            }
            try {
                String message=service.register(usernameField.getValue(), passwordField.getValue(),firstNameField.getValue(),lastNameField.getValue(),emailField.getValue(),confirmPasswordField.getValue());
                Notification n = new Notification(""+message);
                n.setDelayMsec(3000);
                n.show(Page.getCurrent());
                getUI().getNavigator().navigateTo("login");
            }
            catch (Exception e) {

            };

        });
        layout.addComponent(registerButton);
        HorizontalLayout register_line=new HorizontalLayout();
        register_line.setSpacing(true);
        Link register= new Link("Back to Homepage", new ExternalResource("#!home"));
        Link loginLink = new Link("login", new ExternalResource("#!login"));
        register_line.addComponent(register);
        register_line.addComponent(loginLink);
        layout.addComponent(register_line);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        User user = Utils.getCurrentUser();
        if (user != null) {
            getUI().getNavigator().navigateTo("main");
        }
    }
}
