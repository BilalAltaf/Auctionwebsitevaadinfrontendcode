package com;

import com.vaadin.data.Container;
import com.vaadin.data.Validator;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by bilal on 10/18/2016.
 */
public class CreateAuctionView  extends FormLayout implements View {
    private Service service;

    public CreateAuctionView (Service service) {
        this.service = service;
        setSpacing(true);
        setMargin(true);
        setHeight("630px");
        Label pageHeadingLabel = new Label("Create Auction");
        pageHeadingLabel.addStyleName("heading");
        addComponent(pageHeadingLabel);
        TextField titleField = new TextField("Title");
        titleField.setInputPrompt("Add a Title");
        titleField.setRequired(true);
        titleField.addValidator(new NullValidator("Must be given", false));
        addComponent(titleField);

        TextField startprice = new TextField("Starting Price");
        startprice.setInputPrompt("Add a Starting Price");
        startprice.setRequired(true);
        startprice.addValidator(new NullValidator("Must be given", false));
        addComponent(startprice);

        TextArea descriptionArea = new TextArea("Description");
        descriptionArea.setInputPrompt("Add a description");
        descriptionArea.setRequired(true);
        descriptionArea.addValidator(new NullValidator("Must be given", false));
        addComponent(descriptionArea);

        DateField d1=new DateField("Deadline");
        d1.setLocale(new Locale("en", "US"));
        d1.setResolution(Resolution.SECOND);
        d1.addValidator(new Validator() {
            @Override
            public void validate(Object value) throws InvalidValueException {
                Date dateValue = (Date) value;
                Date now = new Date();
                if (dateValue.before(now))
                {
                    d1.clear();
                    Notification.show("select date from coming dates", Notification.Type.WARNING_MESSAGE);
                }
            }
        });
        d1.setRequired(true);
        d1.addValidator(new NullValidator("Must be given", false));
        addComponent(d1);


        ComboBox CategoryBox = new ComboBox("Enter Category ", getCategoryname());
        CategoryBox.setNullSelectionAllowed(true);
        CategoryBox.setValue(1);
        CategoryBox.setInputPrompt("Choose from list");
        CategoryBox.setImmediate(true);
        CategoryBox.setRequired(true);
        addComponent(CategoryBox);
        Button addButton = new Button("Save");
        addButton.addStyleName("newstyle");
        addButton.addClickListener(clickEvent -> {
            String uiValue =startprice.getValue();
            try {
                Float.parseFloat(uiValue);
                titleField.validate();
                descriptionArea.validate();
                d1.validate();
                CategoryBox.validate();

            } catch (Exception ignoredException) {
                startprice.clear();
                titleField.setValidationVisible(true);
                titleField.setRequiredError("Field Required!");
                descriptionArea.setValidationVisible(true);
                descriptionArea.setRequiredError("Field Required!");
                d1.setValidationVisible(true);
                d1.setRequiredError("Field Required!");
                CategoryBox.setValidationVisible(true);
                CategoryBox.setRequiredError("Field Required!");
                startprice.setValidationVisible(true);
                startprice.setRequiredError("Please enter number for biding!");
                return;

            }
            try {
                DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                DateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                String inputText = d1.getValue().toString();
                Date date = inputFormat.parse(inputText);
                String deadline = outputFormat.format(date);
                int CategoryId=0;
                for(int i=1;i<13;i++)
                {
                    Auction UploadCategory=new Auction(0,"","",0, 0,0,"","","",0);
                    UploadCategory.setCategoryName(i);
                    String value=UploadCategory.getCategoryName();
                    String value1=CategoryBox.getValue().toString();
                    if(value1.equals(value))
                    {
                        CategoryId=i;
                    }

                }
                if (service.createAuction(titleField.getValue(), deadline, startprice.getValue(), descriptionArea.getValue(),CategoryId)){
                    Notification n = new Notification("Auction successfully created!");
                    n.setDelayMsec(3000);
                    n.show(Page.getCurrent());
                    getUI().getNavigator().navigateTo("main");
                    /**
                     * TODO: This is a hack to move to the start view and update the meeting list after a meeting has
                     * been created. A better solution might be something like the message bus in the Vaadin Dashboard
                     * demo app.
                     */


                }
            } catch (Exception e) { // TODO
                Notification.show("There was an error when trying to contact the service", Notification.Type.ERROR_MESSAGE);

            }

        });
        addComponent(addButton);



    }
    private Container getCategoryname() {
        IndexedContainer container = new IndexedContainer();
        for(int i=1;i<13;i++)
        {
            Auction UploadCategory=new Auction(0,"","",0, 0,0,"","","",0);
            UploadCategory.setCategoryName(i);
            container.addItem(UploadCategory.getCategoryName());

        }
        return container;
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
