package com;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.Panel;

import java.awt.*;
import com.vaadin.ui.Button;

import java.util.Collection;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * Created by bilal on 10/17/2016.
 */
public class MyAuctionView   extends VerticalLayout implements View {

    private Service service;
    public MyAuctionView (Service service) {
        this.service = service;
        Collection<Auction> auction;
        try {
            auction = service.myauction();
            VerticalLayout layout = new VerticalLayout();
            layout.addStyleName("auctionview");
            Panel panel = new Panel(layout);
            addComponent(panel);
            Table table = new Table("My Auction");
            table.addContainerProperty("Title", String.class, null);
            table.addContainerProperty("winner", String.class, null);
            table.addContainerProperty("Min Price", Float.class, null);
            table.addContainerProperty("Top bid", Float.class, null);
            table.addContainerProperty("Category", String.class, null);
            table.addContainerProperty("Status", String.class, null);
            table.addContainerProperty("Deadline", String.class, null);
            table.addContainerProperty("Action", Button.class, null);
            int i=1;

            for (Auction a : auction) {

                Button detailsField = new Button("view");
                detailsField.addStyleName("newstyle");
                detailsField.addClickListener(new Button.ClickListener() {
                    public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
                        panel.setContent(new MyAuctionSingleView(service, a.getId()));
                    }
                });


                table.addItem(new Object[]{a.getTitle(), a.getWinner(), a.getminPrice(), a.gettopBid(), a.getCategoryName(), a.getStatus(), a.getDeadline(), detailsField}, a);

                // Create a button and handle its click. A Button does not
                // know the item it is contained in, so we have to store the
                // item ID as user-defined data.
            }
            table.setPageLength(table.size());
            layout.addComponent(table);
        } catch (Exception e) {

        }
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
