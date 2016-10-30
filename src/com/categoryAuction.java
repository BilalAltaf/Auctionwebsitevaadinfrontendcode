package com;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.Collection;

/**
 * Created by bilal on 10/24/2016.
 */
public class categoryAuction extends VerticalLayout implements View {
    private Service service;
    private User user = Utils.getCurrentUser();

    public categoryAuction(Service service,int category) {
        this.service = service;

        Collection<Auction> auction;
        try {
            auction = service.categoryauction(category);
            VerticalLayout layout = new VerticalLayout();
            layout.addStyleName("auctionview");
            Panel panel = new Panel(layout);
            addComponent(panel);
            Table table = new Table("All Auction");
            table.addContainerProperty("Title", String.class, null);
            table.addContainerProperty("winner", String.class, null);
            table.addContainerProperty("Min Price", Float.class, null);
            table.addContainerProperty("Top bid", Float.class, null);
            table.addContainerProperty("Category", String.class, null);
            table.addContainerProperty("Status", String.class, null);
            table.addContainerProperty("Deadline", String.class, null);
            table.addContainerProperty("Action", Button.class, null);
            if (user!=null && user.getIssuperuser() == true) {

                table.addContainerProperty("Ban", Button.class, null);
            }

            for (Auction a : auction) {
                Button detailsField = new Button("view");
                detailsField.addStyleName("newstyle");
                detailsField.addClickListener(new Button.ClickListener() {
                    public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
                        if (user!=null && user.getId() == a.getOwner_id()) {
                            panel.setContent(new MyAuctionSingleView(service, a.getId()));
                        }
                        else
                        {
                            panel.setContent(new AuctionView(service,a.getId()));

                        }

                    }

                });
                if (user!=null && user.getIssuperuser() == true)
                {
                    Button banAuctionField = new Button("Ban");
                    banAuctionField.addStyleName("newstyle");
                    banAuctionField.addClickListener(new Button.ClickListener() {
                        public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {

                        }

                    });
                    table.addItem(new Object[]{a.getTitle(), a.getWinner(), a.getminPrice(), a.gettopBid(), a.getCategoryName(), a.getStatus(), a.getDeadline(), detailsField,banAuctionField}, a);
                }
                else
                {

                    table.addItem(new Object[]{a.getTitle(), a.getWinner(), a.getminPrice(), a.gettopBid(), a.getCategoryName(), a.getStatus(), a.getDeadline(), detailsField}, a);

                }
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
