package com;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

import java.util.Collection;

/**
 * Created by bilal on 10/17/2016.
 */
public class AllAuctionView  extends VerticalLayout implements View{

        private Service service;
        private User user = Utils.getCurrentUser();

    public AllAuctionView(Service service) {
        this.service = service;

        Collection<Auction> auction;
        try {
        auction = service.allauction();
        VerticalLayout layout = new VerticalLayout();
        layout.addStyleName("auctionview");
        Panel panel = new Panel(layout);
        panel.addStyleName("startview");
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
                        try {
                            String message = service.banauction(a.getId());

                            Notification n = new Notification("" + message);
                            n.setDelayMsec(3000);
                            n.show(Page.getCurrent());
                            getUI().getNavigator().navigateTo("main");
                        }
                        catch (Exception e) {

                        };
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
