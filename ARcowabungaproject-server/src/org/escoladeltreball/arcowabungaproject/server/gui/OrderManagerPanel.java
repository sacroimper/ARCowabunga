/*
 *  OrderManagerPanel.java
 *  
 *  This file is part of ARcowabungaproject.
 *  
 *  Bernabe Gonzalez Garcia <bernagonzga@gmail.com>
 *  Joaquim Dalmau Torva <jdalmaut@gmail.com>
 *  Marc Sabate Piñol <masapim@hotmail.com>
 *  Victor Purcallas Marchesi <vpurcallas@gmail.com>
 *
 *   ARcowabungaproject is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   ARcowabungaproject is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with ARcowabungaproject.  If not, see <http://www.gnu.org/licenses/>. 
 */
package org.escoladeltreball.arcowabungaproject.server.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import org.escoladeltreball.arcowabungaproject.model.Order;
import org.escoladeltreball.arcowabungaproject.model.system.Pizzeria;

public class OrderManagerPanel extends JPanel {

    // ====================
    // CONSTANTS
    // ====================

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // ====================
    // ATTRIBUTES
    // ====================
    private JPanel jpOrders;
    private JPanel jpInfo;
    private JSplitPane split;
    private JScrollPane scroll;
    private JPanel jpWaitOrders;
    private JPanel jpMakingOrders;
    private JPanel jpSendedOrders;
    private static OrderManagerPanel instance;

    // ====================
    // CONSTRUCTORS
    // ====================
    private OrderManagerPanel() {
	this.initComponents();
    }

    public static OrderManagerPanel getInstance() {
	if (instance == null) {
	    instance = new OrderManagerPanel();
	}
	return instance;
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    public void setJpInfo(JPanel jpInfo) {
	this.split.remove(this.scroll);
	this.jpInfo = jpInfo;
	this.scroll = new JScrollPane(this.jpInfo);
	this.split.setRightComponent(this.scroll);
    }

    public void addWaitOrder(Order order) {
	this.jpWaitOrders.add(new OrderPanel(order));
    }

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================

    private void initComponents() {
	this.setLayout(new BorderLayout());
	this.jpOrders = new JPanel();
	this.jpOrders.setLayout(new BorderLayout());
	this.jpOrders.setBorder(BorderFactory.createEtchedBorder());
	this.jpInfo = new OrderInfoPanel();
	this.jpInfo.setBorder(BorderFactory.createEtchedBorder());
	this.scroll = new JScrollPane(this.jpInfo);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	this.jpWaitOrders = new JPanel();
	this.jpWaitOrders.setLayout(new BoxLayout(jpWaitOrders,
		BoxLayout.Y_AXIS));
	this.jpMakingOrders = new JPanel();
	this.jpMakingOrders.setLayout(new BoxLayout(jpMakingOrders,
		BoxLayout.Y_AXIS));
	this.jpSendedOrders = new JPanel();
	this.jpSendedOrders.setLayout(new BoxLayout(jpSendedOrders,
		BoxLayout.Y_AXIS));

	this.addWaitOrders();

	this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpOrders,
		scroll);
	this.split.setOneTouchExpandable(true);
	this.split.setResizeWeight(0.5);

	this.jpOrders.add(createTabs());
	this.add(split);
    }

    /**
     * Create the three tabs of this panel
     * 
     * @return the tabs
     */
    private JTabbedPane createTabs() {

	JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP,
		JTabbedPane.SCROLL_TAB_LAYOUT);
	jtp.addTab("Wait Orders", this.jpWaitOrders);
	jtp.addTab("Making Orders", this.jpMakingOrders);
	jtp.addTab("Sended Orders", this.jpSendedOrders);

	return jtp;
    }

    /**
     * Add an order from the client into the panel
     */
    private void addWaitOrders() {
	Pizzeria pizzeria = Pizzeria.getInstance();
	for (Order order : pizzeria.getOrdersSaved()) {
	    addWaitOrder(order);
	}

    }

    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================

    public JPanel getJpWaitOrders() {
	return this.jpWaitOrders;
    }

    public JPanel getJpMakingOrders() {
	return this.jpMakingOrders;
    }

    public JPanel getJpSendedOrders() {
	return this.jpSendedOrders;
    }
}
