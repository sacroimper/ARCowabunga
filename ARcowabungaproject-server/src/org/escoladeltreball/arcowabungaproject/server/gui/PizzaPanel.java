/*
 *  PizzaPanel.java
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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

import org.escoladeltreball.arcowabungaproject.model.Ingredient;
import org.escoladeltreball.arcowabungaproject.model.Pizza;

public class PizzaPanel extends JPanel {

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
    private Pizza pizza;
    private Border border;
    private JTable jtIngredients;
    private JLabel jlPizzaName;
    private JLabel jlPizzaSize;
    private JLabel jlPizzaMassType;
    private JLabel jlPizzaType;
    private JLabel jlPizzaPrice;

    // ====================
    // CONSTRUCTORS
    // ====================
    public PizzaPanel(Pizza pizza) {
	this.pizza = pizza;
	initComponents();
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    // ====================
    // PROTECTED METHODS
    // ====================

    // ====================
    // PRIVATE METHODS
    // ====================
    private void initComponents() {

	this.setLayout(new GridBagLayout());
	this.border = BorderFactory.createEtchedBorder();
	this.setBorder(BorderFactory.createTitledBorder(border, "PIZZA"));
	// Put information
	this.jlPizzaName = new JLabel(this.pizza.getName());
	this.jlPizzaName.setFont(jlPizzaName.getFont().deriveFont(30f));
	this.jlPizzaSize = new JLabel("Size: " + this.pizza.getSize());
	this.jlPizzaMassType = new JLabel("Mass Type: "
		+ this.pizza.getMassType());
	this.jlPizzaType = new JLabel("Type: " + this.pizza.getType());
	this.jlPizzaPrice = new JLabel("Price: " + this.pizza.getPrice() + "€");

	String[] columnNames = { "Ingredient", "Quantity" };
	String[][] ingredientsData = new String[this.pizza.getIngredients()
		.size()][2];

	int i = 0;
	for (Map.Entry<Ingredient, Integer> entry : this.pizza.getIngredients()
		.entrySet()) {
	    ingredientsData[i][0] = entry.getKey().getName();
	    ingredientsData[i][1] = entry.getValue().toString();
	    i++;
	}
	this.jtIngredients = new JTable(ingredientsData, columnNames);
	// Center text in table
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
	this.jtIngredients.getColumn("Ingredient").setCellRenderer(
		centerRenderer);
	this.jtIngredients.getColumn("Quantity")
		.setCellRenderer(centerRenderer);

	JScrollPane sp = new JScrollPane(this.jtIngredients);
	sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	this.jtIngredients
		.setPreferredScrollableViewportSize(this.jtIngredients
			.getPreferredSize());
	this.jtIngredients.setFillsViewportHeight(true);
	GridBagConstraints constraints = new GridBagConstraints();
	constraints.gridx = 0;
	constraints.gridy = 0;
	constraints.fill = GridBagConstraints.HORIZONTAL;
	this.add(this.jlPizzaName, constraints);
	constraints.gridy = 1;
	this.add(this.jlPizzaSize, constraints);
	constraints.gridy = 2;
	this.add(this.jlPizzaMassType, constraints);
	constraints.gridy = 3;
	this.add(this.jlPizzaType, constraints);
	constraints.gridy = 4;
	this.add(sp, constraints);
	constraints.gridy = 5;
	this.add(this.jlPizzaPrice, constraints);
    }
    // ====================
    // OVERRIDE METHODS
    // ====================

    // ====================
    // GETTERS & SETTERS
    // ====================
}
