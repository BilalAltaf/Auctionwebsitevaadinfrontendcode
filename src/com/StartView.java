package com;

/**
 * Created by bilal on 10/15/2016.
 */

import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.MouseEvents;
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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.ValoTheme;

import java.awt.*;
import java.awt.GridLayout;
import java.io.File;

public class StartView extends VerticalLayout implements View {
    private Service service;

    public StartView(Service service) {
        this.service = service;
        VerticalLayout layout = new VerticalLayout();
        layout.addStyleName("startview");
        Panel panel = new Panel(layout);
        panel.addStyleName("startview");
        addComponent(panel);
        layout.setMargin(true);
       //---------------------------------- Start-------------------------------
       //-----------------------------------layout#1----------------------------

        ThemeResource resource1 = new ThemeResource("../garantimeui/image/iphone_360.jpg");
        Image image1 = new Image("",
                resource1);
        image1.setWidth("90px");
        image1.setHeight("90px");
        image1.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,1));
            }
        });
        ThemeResource resource2 = new ThemeResource("../garantimeui/image/HomeFurniture.jpg");
        Image image2 = new Image("",
                resource2);
        image2.setWidth("120px");
        image2.setHeight("90px");
        image2.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,4));
            }
        });
        ThemeResource resource3 = new ThemeResource("../garantimeui/image/fashion-and-beauty.jpg");
        Image image3 = new Image("",
                resource3);
        image3.setWidth("120px");
        image3.setHeight("90px");
        image3.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,7));
            }
        });
        ThemeResource resource4 = new ThemeResource("../garantimeui/image/Jobs-Graphic.jpg");
        Image image4 = new Image("",
                resource4);
        image4.setWidth("90px");
        image4.setHeight("90px");
        image4.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,10));
            }
        });
        HorizontalLayout layout1 = new HorizontalLayout();
        layout1.setSpacing(true);
        layout1.setWidth("1000px");
        layout1.addComponent(image1);
        layout1.addComponent(image2);
        layout1.addComponent(image3);
        layout1.addComponent(image4);
        layout.addComponent(layout1);
        //---------------------------------- End-------------------------------
        //-----------------------------------layout#1----------------------------

        //---------------------------------- Start-------------------------------
        //-----------------------------------layout#2----------------------------
        HorizontalLayout layout2 = new HorizontalLayout();
        layout2.setSpacing(true);
        layout2.setWidth("1000px");
        Button MobilesTablets = new Button("Mobiles & Tablets");
        MobilesTablets.addStyleName("startview");
        MobilesTablets.addClickListener(clickEvent -> {
                panel.setContent(new categoryAuction(service,1));
        });
        layout2.addComponent(MobilesTablets);
        Button HomeFurniture = new Button("Home & Furniture");
        HomeFurniture.addStyleName("startview");
        HomeFurniture.addClickListener(clickEvent -> {
            panel.setContent(new categoryAuction(service,4));
        });
        layout2.addComponent(HomeFurniture);
        Button fashionbeauty = new Button("fashion & beauty");
        fashionbeauty.addStyleName("startview");
        fashionbeauty.addClickListener(clickEvent -> {
            panel.setContent(new categoryAuction(service,7));
        });
        layout2.addComponent(fashionbeauty);
        Button jobs = new Button("Jobs");
        jobs.addStyleName("startview");
        jobs.addClickListener(clickEvent -> {
            panel.setContent(new categoryAuction(service,10));
        });
        layout2.addComponent(jobs);
        layout.addComponent(layout2);
        //---------------------------------- End-------------------------------
        //-----------------------------------layout#2----------------------------

        //---------------------------------- Start-------------------------------
        //-----------------------------------layout#3----------------------------
        ThemeResource resource5 = new ThemeResource("../garantimeui/image/Laptop.jpg");
        Image image5 = new Image("",
                resource5);
        image5.setWidth("120px");
        image5.setHeight("90px");
        image5.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,2));
            }
        });
        ThemeResource resource6 = new ThemeResource("../garantimeui/image/3127765.large.jpg");
        Image image6 = new Image("",
                resource6);
        image6.setWidth("90px");
        image6.setHeight("90px");
        image6.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,5));
            }
        });
        ThemeResource resource7 = new ThemeResource("../garantimeui/image/kids.jpg");
        Image image7 = new Image("",
                resource7);
        image7.setWidth("90px");
        image7.setHeight("90px");
        image7.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,8));
            }
        });
        ThemeResource resource8 = new ThemeResource("../garantimeui/image/Realestate.jpg");
        Image image8 = new Image("",
                resource8);
        image8.setWidth("90px");
        image8.setHeight("90px");
        image8.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,11));
            }
        });
        HorizontalLayout layout3 = new HorizontalLayout();
        layout3.setSpacing(true);
        layout3.setWidth("1000px");
        layout3.addComponent(image5);
        layout3.addComponent(image6);
        layout3.addComponent(image7);
        layout3.addComponent(image8);
        layout.addComponent(layout3);
        //---------------------------------- End-------------------------------
        //-----------------------------------layout#3----------------------------

        //---------------------------------- Start-------------------------------
        //-----------------------------------layout#4----------------------------
        HorizontalLayout layout4 = new HorizontalLayout();
        layout4.setSpacing(true);
        layout4.setWidth("1000px");
        Button ElectronicsComputers = new Button("Electronics & Computers");
        ElectronicsComputers.addStyleName("startview");
        ElectronicsComputers.addClickListener(clickEvent -> {
            panel.setContent(new categoryAuction(service,2));
        });
        layout4.addComponent(ElectronicsComputers);
        Button Pets = new Button("Pets");
        Pets.addStyleName("startview");
        Pets.addClickListener(clickEvent -> {
            panel.setContent(new categoryAuction(service,5));
        });
        layout4.addComponent(Pets);
        Button KidsBabyProducts = new Button("Kids & Baby Products");
        KidsBabyProducts.addStyleName("startview");
        KidsBabyProducts.addClickListener(clickEvent -> {
            panel.setContent(new categoryAuction(service,8));
        });
        layout4.addComponent(KidsBabyProducts);
        Button RealEstate = new Button("Real Estate");
        RealEstate.addStyleName("startview");
        RealEstate.addClickListener(clickEvent -> {
            panel.setContent(new categoryAuction(service,11));
        });
        layout4.addComponent(RealEstate);
        layout.addComponent(layout4);
        //---------------------------------- End-------------------------------
        //-----------------------------------layout#4----------------------------

        //---------------------------------- Start-------------------------------
        //-----------------------------------layout#5----------------------------
        ThemeResource resource9 = new ThemeResource("../garantimeui/image/Car.jpg");
        Image image9 = new Image("",
                resource9);
        image9.setWidth("90px");
        image9.setHeight("90px");
        image9.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,3));
            }
        });
        ThemeResource resource10 = new ThemeResource("../garantimeui/image/balanced-books.jpg");
        Image image10 = new Image("",
                resource10);
        image10.setWidth("90px");
        image10.setHeight("90px");
        image10.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,6));
            }
        });
        ThemeResource resource11 = new ThemeResource("../garantimeui/image/Services.jpg");
        Image image11 = new Image("",
                resource11);
        image11.setWidth("90px");
        image11.setHeight("90px");
        image11.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,9));
            }
        });
        ThemeResource resource12 = new ThemeResource("../garantimeui/image/others.jpg");
        Image image12 = new Image("",
                resource12);
        image12.setWidth("90px");
        image12.setHeight("90px");
        image12.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                panel.setContent(new categoryAuction(service,12));
            }
        });
        HorizontalLayout layout5 = new HorizontalLayout();
        layout5.setSpacing(true);
        layout5.setWidth("1000px");
        layout5.addComponent(image9);
        layout5.addComponent(image10);
        layout5.addComponent(image11);
        layout5.addComponent(image12);
        layout.addComponent(layout5);
        //---------------------------------- End-------------------------------
        //-----------------------------------layout#5----------------------------

        //---------------------------------- Start-------------------------------
        //-----------------------------------layout#6----------------------------
        HorizontalLayout layout6 = new HorizontalLayout();
        layout6.setSpacing(true);
        layout6.setWidth("1000px");
        Button Vehicles = new Button("Vehicles");
        Vehicles.addStyleName("startview");
        Vehicles.addClickListener(clickEvent -> {
            panel.setContent(new categoryAuction(service,3));
        });
        layout6.addComponent(Vehicles);
        Button BooksSport= new Button( "Books & Sport");
        BooksSport.addStyleName("startview");
        BooksSport.addClickListener(clickEvent -> {
            panel.setContent(new categoryAuction(service,6));
        });
        layout6.addComponent(BooksSport);
        Button Services = new Button("Services");
        Services.addClickListener(clickEvent -> {
            panel.setContent(new categoryAuction(service,9));
        });
        Services.addStyleName("startview");
        layout6.addComponent(Services);
        Button Others = new Button("Others");
        Others.addStyleName("startview");
        Others.addClickListener(clickEvent -> {
            panel.setContent(new categoryAuction(service,12));
        });
        layout6.addComponent(Others);
        layout.addComponent(layout6);
        //---------------------------------- End-------------------------------
        //-----------------------------------layout#6----------------------------

    }

    public void showcurrentauction(int category){

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }
}


