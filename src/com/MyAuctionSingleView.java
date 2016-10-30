package com;

import com.google.gwt.user.client.ui.TextBox;
import com.vaadin.data.Container;
import com.vaadin.data.Validator;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bilal on 10/20/2016.
 */
public class MyAuctionSingleView extends FormLayout implements View {
    private Service service;

    public MyAuctionSingleView  (Service service,int auction_Id) {
        Auction auction;
        setSpacing(true);
        setMargin(true);
        setHeight("630px");

        try {
            auction = service.singleauctionview(auction_Id);
            VaadinSession.getCurrent().setAttribute("auction", auction);
            this.service = service;
            Label pageHeadingLabel = new Label("Edit Auction");
            pageHeadingLabel.addStyleName("heading");
            addComponent(pageHeadingLabel);
            TextField titleField = new TextField("Title");
            titleField.setInputPrompt("Add a Title");
            titleField.setValue(auction.getTitle());
            titleField.setReadOnly(true);
            titleField.addValidator(new NullValidator("Must be given", false));
            addComponent(titleField);

            TextField minPrice = new TextField("Min Price");
            minPrice.setValue(String.valueOf(auction.getminPrice()));
            minPrice.setReadOnly(true);
            addComponent(minPrice);

            TextArea descriptionArea = new TextArea("Description");
            descriptionArea.setInputPrompt("Add a description");
            descriptionArea.setRequired(true);
            descriptionArea.setValue(auction.getDescription());
            descriptionArea.addValidator(new NullValidator("Must be given", false));
            addComponent(descriptionArea);


            TextField d1 = new TextField("Deadline");
            d1.setValue(auction.getDeadline());
            d1.setReadOnly(true);
            addComponent(d1);

            ComboBox CategoryBox = new ComboBox("Enter Category ", getCategoryname());
            CategoryBox.setNullSelectionAllowed(true);
            CategoryBox.setInputPrompt("Choose from list");
            CategoryBox.setImmediate(true);
            CategoryBox.setRequired(true);
            addComponent(CategoryBox);
            Button addButton = new Button("update");
            addButton.addStyleName("newstyle");
            addButton.addClickListener(clickEvent -> {
                int CategoryId=0;
                try {
                    descriptionArea.validate();
                    CategoryBox.validate();
                    try {
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
                    Auction auction1 = Utils.getCurrentAuction();
                    String message=service.editauction(descriptionArea.getValue(),CategoryId,auction1.getId());
                    Notification n = new Notification(""+message);
                    n.setDelayMsec(3000);
                    n.show(Page.getCurrent());
                    getUI().getNavigator().navigateTo("main");



                    } catch (Exception e) { // TODO
                        Notification.show("There was an error when trying to contact the service", Notification.Type.ERROR_MESSAGE);

                    }

                }
                catch (Validator.InvalidValueException e) {
                        CategoryBox.setRequired(true);
                        CategoryBox.setRequiredError("Select Category!");
                        descriptionArea.setRequired(true);
                        descriptionArea.setRequiredError("Description Area is required!");
                }



            });
            addComponent(addButton);
            Button cancelButton = new Button("Cancel");
            cancelButton.addStyleName("newstyle");
            cancelButton.addClickListener(clickEvent -> {
                getUI().getNavigator().navigateTo("main");
            });
            addComponent(cancelButton);
        }
        catch (Exception e) { // TODO

        }



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
