package com.sam.webtasks.basictools;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sam.webtasks.client.SequenceHandler;

public class InfoSheet {
    public static void Run(String infoText) {
        final VerticalPanel screenPanel = new VerticalPanel(); //used to align mainpanel
        final VerticalPanel mainPanel = new VerticalPanel();   //contains all screen elements
        final Label title = new Label();
        final HorizontalPanel emailSubmit = new HorizontalPanel();
        final HTML emailHTML = new HTML();
        final Label printText = new Label();
        final HorizontalPanel printPanel = new HorizontalPanel();
        final Button printButton = new Button("Print");
        final TextBox emailTextBox = new TextBox();
        final Button emailSubmitButton = new Button("Submit");
        final VerticalPanel emailPanel = new VerticalPanel();
        final Label projectTitleText = new Label();
        final HTML approvalHTML = new HTML();
        final Label contactTitle = new Label();
        final HTML contactHTML = new HTML();
        final HTML infoHTML = new HTML();
        final Button continueButton = new Button("Click here to continue");
        
        final ScrollPanel scrollPanel = new ScrollPanel();

        title.setText("Information page for participants in research studies");
        title.setStyleName("titleText");

        printPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        printText.setText("Click here to print this page:");
        printText.setStyleName("rightMarginSmall", true);
        printPanel.add(printText);
        printPanel.add(printButton);

        emailHTML.setHTML("If you would like to receive a copy of this information by email, please enter your email address below:");

        emailTextBox.setStyleName("rightMarginSmall", true);

        emailSubmit.add(emailTextBox);
        emailSubmit.add(emailSubmitButton);

        emailSubmitButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                String phpString = "send_info.php?to=" + emailTextBox.getText();
                PHP.Call(phpString, false);
                
                mainPanel.remove(emailPanel);
            }
        });

        emailPanel.add(emailHTML);
        emailPanel.add(emailSubmit);

        projectTitleText.setText("Title of project: Online response time studies of attention and memory");

        approvalHTML.setHTML("Please scroll to the bottom to view all content.<br><br>"
        		+ "This study has been approved by the UCL Research Ethics Committee "
                + "as Project ID Number: ICN-VW-28-22-2023");

        contactTitle.setText("Name, address and contact details of investigators: ");

        contactHTML.setHTML("Professor Sam Gilbert<br>"
                + "Institute of Cognitive Neuroscience<br>"
                + "17 Queen Square<br>"
                + "London WC1N 3AR<br><br>"
                + "<a href=\"mailto:sam.gilbert@ucl.ac.uk\">sam.gilbert@ucl.ac.uk</a><br>");

        infoHTML.setHTML(infoText);

        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        mainPanel.add(title);
        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        mainPanel.add(printPanel);
        //mainPanel.add(emailPanel);
        mainPanel.add(projectTitleText);
        mainPanel.add(approvalHTML);
        mainPanel.add(contactTitle);
        mainPanel.add(contactHTML);
        mainPanel.add(infoHTML);
        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        mainPanel.add(continueButton);
        mainPanel.setSpacing(20);
        mainPanel.setWidth("75%");

        screenPanel.setHeight(Window.getClientHeight() + "px");
        screenPanel.setWidth("100%");
        screenPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        screenPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

        screenPanel.add(mainPanel);

        printButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.print();
            }
        });

        continueButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get().remove(scrollPanel);
                SequenceHandler.Next();
            }
        });

        scrollPanel.add(screenPanel);
        scrollPanel.setHeight(Window.getClientHeight() + "px");
        RootPanel.get().add(scrollPanel);

        emailTextBox.setWidth((printText.getOffsetWidth() - 5) + "px");
    }

}
