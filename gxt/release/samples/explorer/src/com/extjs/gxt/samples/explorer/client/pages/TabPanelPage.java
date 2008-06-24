/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007, 2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.extjs.gxt.samples.explorer.client.pages;

import com.extjs.gxt.samples.resources.client.TestData;
import com.extjs.gxt.ui.client.Events;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

public class TabPanelPage extends LayoutContainer implements EntryPoint {

  public void onModuleLoad() {
    setBounds(10, 10, 400, 300);
    RootPanel.get().add(this);
  }

  int count = 1;

  public TabPanelPage() {
    // next line is only used to pass layout to containing container
    // this will have NO effect outside of the explorer demo
    setData("layout", new FitLayout());
  }

  @Override
  protected void onRender(Element parent, int pos) {
    super.onRender(parent, pos);
    
    final Listener<TabPanelEvent> closeListener = new Listener<TabPanelEvent>() {
      public void handleEvent(TabPanelEvent ce) {
        TabItem item = ce.item;
        Info.display("Close", "Closing {0}", item.getText());
      }
    };

    final TabPanel tabFolder = new TabPanel();
    tabFolder.setTabScroll(true);
    tabFolder.addListener(Events.Select, new Listener<TabPanelEvent>() {
      public void handleEvent(TabPanelEvent tpe) {
        TabItem item = tpe.item;
        Info.display("Selection Changed", "The '{0}' item was selected", item.getText());
      }
    });
    TabItem item = new TabItem();
    item.setText("GWT");
    item.setIconStyle("icon-tabs");
    item.setUrl("http://code.google.com/webtoolkit");
    tabFolder.add(item);
    tabFolder.setSelection(item);

    item = new TabItem();
    item.setClosable(true);
    item.setText("Close");
    item.setIconStyle("icon-tabs");

    item.addListener(Events.Close, closeListener);
    item.setScrollMode(Scroll.AUTO);
    item.addText(TestData.DUMMY_TEXT_LONG);
    tabFolder.add(item);

    item = new TabItem();
    item.setClosable(true);
    item.setText("Tab 3");
    item.setIconStyle("icon-tabs");
    item.addListener(Events.Close, closeListener);

    tabFolder.add(item);

    ButtonBar buttonBar = new ButtonBar();
    buttonBar.add(new Button("Add TabItem", new SelectionListener<ComponentEvent>() {

      public void componentSelected(ComponentEvent ce) {
        TabItem item = new TabItem();
        item.setClosable(true);
        item.setText("New Item " + count++);
        item.addListener(Events.Close, closeListener);
        tabFolder.add(item);
      }

    }));

    RowLayout layout = new RowLayout(Orientation.VERTICAL);
    setLayout(layout);
    tabFolder.setStyleAttribute("margin", "15px");
    tabFolder.setData(new RowData(1, 1));
    add(tabFolder);
    add(buttonBar);

  }

}
