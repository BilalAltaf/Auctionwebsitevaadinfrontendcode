package com;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.shared.ui.datefield.Resolution;

import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/**
 * Created by bilal on 10/18/2016.
 */
public class AuctionView extends FormLayout  implements View {
    private Service service;

    public AuctionView(Service service,int auction_Id) {
        Auction auction;
        Collection<Bid> bid;
        try {

            auction = service.singleauctionview(auction_Id);
            bid = service.singleauctionbidinfo(auction_Id);
            VaadinSession.getCurrent().setAttribute("auction", auction);
        this.service = service;
        Label pageHeadingLabel = new Label("Auction Detail");
        pageHeadingLabel.addStyleName("heading");
        addComponent(pageHeadingLabel);
        HorizontalLayout h1 = new HorizontalLayout();
        h1.setWidth("900px");
        TextField titleField = new TextField("Title");
        titleField.setValue(auction.getTitle());
        titleField.setReadOnly(true);
        h1.addComponent(titleField);
        TextField Category = new TextField("Category");
        Category.setValue(auction.getCategoryName());
        Category.setReadOnly(true);
        h1.addComponent(Category);
        TextField Status = new TextField("Status");
        Status.setValue(auction.getStatus());
        Status.setReadOnly(true);
        h1.addComponent(Status);
        TextField Deadline = new TextField("Deadline");
        Deadline.setValue(auction.getDeadline());
        Deadline.setReadOnly(true);
        h1.addComponent(Deadline);
        addComponent(h1);

        HorizontalLayout h2 = new HorizontalLayout();
        h2.setWidth("900px");
        TextField topBid = new TextField("Top Bid");
        topBid.setValue(String.valueOf(auction.gettopBid()));
        topBid.setReadOnly(true);
        h2.addComponent(topBid);
        TextField minPrice = new TextField("Min Price");
        minPrice.setValue(String.valueOf(auction.getminPrice()));
        minPrice.setReadOnly(true);
        h2.addComponent(minPrice);
        TextField auctionOwner = new TextField("Auction Owner");
        auctionOwner.setValue(auction.getOwner());
        auctionOwner.setReadOnly(true);
        h2.addComponent(auctionOwner);
        TextField auctionWinner = new TextField("Auction Winner");
        auctionWinner.setValue(auction.getWinner());
        auctionWinner.setReadOnly(true);
        h2.addComponent(auctionWinner);
        addComponent(h2);

        HorizontalLayout h3 = new HorizontalLayout();
        TextField creationDate = new TextField("Creation date");
        creationDate.setValue(auction.getcreatedDate());
        creationDate.setReadOnly(true);
        h3.addComponent(creationDate);
        addComponent(h3);

        HorizontalLayout h4 = new HorizontalLayout();
        TextArea description = new TextArea("Description");
        description.setValue(auction.getDescription());
        description.setWidth("900px");
        description.setReadOnly(true);
        h4.addComponent(description);
        addComponent(h4);

        HorizontalLayout h5 = new HorizontalLayout();
        h5.setWidth("400px");
        Button bidNow = new Button("Bid Now");
        bidNow.addStyleName("newstyle");
        h5.addComponent(bidNow);
        Button viewBid = new Button("view bidings");
        viewBid.addStyleName("newstyle");
        h5.addComponent(viewBid);
        addComponent(h5);
        //bid now module ----------start-------------
        VerticalLayout popupContent = new VerticalLayout();
        popupContent.setSpacing(true);
        popupContent.setMargin(true);
        TextField bidAmount = new TextField("Enter bid");
        bidAmount.setRequired(true);
        popupContent.addComponent(bidAmount);
        TextArea bidComment = new TextArea("Comment");
        bidComment.setInputPrompt("Comment Here");
        bidComment.setWidth("100%");
        popupContent.addComponent(bidComment);
        HorizontalLayout pop_button = new HorizontalLayout();
        pop_button.setWidth("400px");
        Button confirm_event = new Button("Confirm");
        confirm_event.addStyleName("new");
        confirm_event.addClickListener(clickEvent -> {
            String uiValue =bidAmount.getValue();
            try {
                    Float.parseFloat(uiValue);

            } catch (Exception ignoredException) {
                bidAmount.clear();
                bidAmount.setValidationVisible(true);
                bidAmount.setRequiredError("Please enter number for biding!");
                return;

            }
            User user=Utils.getCurrentUser();
            if(user==null)
            {
                Notification n = new Notification("please login before biding");
                n.setDelayMsec(3000);
                n.show(Page.getCurrent());
                getUI().getNavigator().navigateTo("login");
            }
            else if(user!=null && user.getIssuperuser()==true)
            {
                Notification n = new Notification("Admin cannot place biding");
                n.setDelayMsec(3000);
                n.show(Page.getCurrent());
                getUI().getNavigator().navigateTo("main");
            }
            else {
                try {
                    Auction auction1 = Utils.getCurrentAuction();
                    String message = service.bidauction(auction1.getId(), auction1.gettopBidNo(), Float.parseFloat(bidAmount.getValue()));
                    Notification n = new Notification("" + message);
                    n.setDelayMsec(3000);
                    n.show(Page.getCurrent());
                    getUI().getNavigator().navigateTo("main");


                } catch (Exception e) {

                }
            }


        });
        pop_button.addComponent(confirm_event);
        Button cancel_bid = new Button("Cancel");
        cancel_bid.addStyleName("new");
        cancel_bid.setIcon(new ThemeResource("../runo/icons/16/cancel.png"));
        pop_button.addComponent(cancel_bid);
        popupContent.addComponent(pop_button);
        final PopupView popup = new PopupView(null, popupContent);
        cancel_bid.addClickListener(clickEvent -> {
            popup.setPopupVisible(false);
        });
        bidNow.addClickListener(clickEvent -> {
            popup.setPopupVisible(true);
        });
        popup.setHideOnMouseOut(false);
        addComponent(popup);
        setComponentAlignment(popup, Alignment.MIDDLE_CENTER);
        //bid now module ----------End-------------

        //view bid  module ----------start-------------



             VerticalLayout popupContent_ViewBid = new VerticalLayout();
             popupContent_ViewBid.setSpacing(true);
             popupContent_ViewBid.setMargin(true);
             Table table = new Table("Placed Bidings");
             table.addContainerProperty("Name", String.class, null);
             table.addContainerProperty("Id", Integer.class, null);
             table.addContainerProperty("Bid", Float.class, null);
             table.addContainerProperty("Date", String.class, null);
                for (Bid b : bid) {
                    table.addItem(new Object[]{b.getUserName(), b.getUserid(), b.getAmount(), b.getBidDate()}, b);
                }
             table.setPageLength(table.size());
             popupContent_ViewBid.addComponent(table);
             final PopupView popup_ViewBid = new PopupView(null, popupContent_ViewBid);
             viewBid.addClickListener(clickEvent -> {
                 popup_ViewBid.setPopupVisible(true);
             });
             popup_ViewBid.setHideOnMouseOut(false);
             addComponent(popup_ViewBid);
             setComponentAlignment(popup_ViewBid, Alignment.MIDDLE_CENTER);


    }
        catch (Exception e) {

        };


        //view bid  module ----------start-------------




    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
       /* User user = Utils.getCurrentUser();

        // Is the user logged in?
        if (user == null) {
            // Get the users e-mail addres

            // User not logged in, throw them back to login page
            getUI().getNavigator().navigateTo("");
            Notification.show("Please log in to continue", Notification.Type.WARNING_MESSAGE);
        }*/
    }
}
