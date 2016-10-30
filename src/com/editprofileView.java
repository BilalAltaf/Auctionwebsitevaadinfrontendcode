package com;
import com.vaadin.data.Container;
import com.vaadin.data.Validator;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.*;


/**
 * Created by bilal on 10/20/2016.
 */
public class editprofileView extends FormLayout implements View {
    private Service service;
    private TextField FirstName;
    private TextField LastName;
    private TextField EmailAddress;
    private TextField TelNo;
    private TextField Username;
    private TextField Password;
    private TextField confirmPassword;

    public editprofileView(Service service) {
        this.service = service;
        User user = Utils.getCurrentUser();

        setSpacing(true);
        setMargin(true);
        setHeight("630px");
        Label pageHeadingLabel = new Label("Update Profile");
        pageHeadingLabel.addStyleName("heading");
        addComponent(pageHeadingLabel);

        FirstName = new TextField("First Name");
        FirstName.setRequired(true);
        FirstName.setValue(user.getFirstname());
        addComponent(FirstName);
        LastName = new TextField("Last Name");
        LastName.setRequired(true);
        LastName.setValue(user.getLastname());
        addComponent(LastName);
        TelNo = new TextField("Telephone No");
        TelNo.setValue(user.getTel());
        addComponent(TelNo);
        EmailAddress = new TextField("Email-Address");
        EmailAddress.setRequired(true);
        EmailAddress.addValidator(new EmailValidator("Please provide a valid e-mail address"));
        EmailAddress.setValue(user.getEmail());
        EmailAddress.setEnabled(true);
        addComponent(EmailAddress);
        Username = new TextField("Username");
        Username.setEnabled(false);
        Username.setValue(user.getUsername());
        addComponent(Username);
        Password = new TextField("Password");
        Password.setInputPrompt("password");
        Password.setEnabled(true);
        addComponent(Password);
        confirmPassword = new TextField("Confirm Password");
        confirmPassword.setEnabled(true);
        confirmPassword.setInputPrompt("password");
        addComponent(confirmPassword);
        Button updateButton = new Button("Save");
        updateButton.addStyleName("newstyle");
        updateButton.addClickListener(clickEvent -> {
            try {
                Username.validate();
                EmailAddress.validate();
                FirstName.validate();
                LastName.validate();

            } catch (Validator.InvalidValueException e) {
                EmailAddress.setValidationVisible(true);
                EmailAddress.setRequiredError("Field required");
                LastName.setValidationVisible(true);
                LastName.setRequiredError("Field required");
                FirstName.setValidationVisible(true);
                FirstName.setRequiredError("Field required");
                Username.setValidationVisible(true);
                Username.setRequiredError("Field required");
                Notification.show(e.getMessage(), Notification.Type.WARNING_MESSAGE);
                return;
            }

            if (Password.getValue().equals("") && confirmPassword.getValue().equals(""))
            {
                Password.setValue("********");
                confirmPassword.setValue("********");
            }
            try {
                String message=service.editUser(Username.getValue(), Password.getValue(),FirstName.getValue(),LastName.getValue(),EmailAddress.getValue(),confirmPassword.getValue());
                if(message.equals("User Updated successfully"))
                {
                    Notification n = new Notification(""+message);
                    n.setDelayMsec(3000);
                    n.show(Page.getCurrent());
                    getUI().getNavigator().navigateTo("main");
                }
                else
                {
                    Notification n = new Notification(""+message);
                    n.setDelayMsec(3000);
                    n.show(Page.getCurrent());
                }

            }
            catch (Exception e) {

            };
        });
        addComponent(updateButton);
    }

    private void updateFields(User user) {
        //nameField.setValue(user.getName());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        User user = Utils.getCurrentUser();
        if (user == null) {
            getUI().getNavigator().navigateTo("");
        }
    }
}
