package Components.Panels;

import javax.swing.*;
import Components.BaseLabel;
import Interface.UpdatedProducts;
import Model.ProductModel;
import Services.ProductService;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

public class CategoryPanel extends JPanel{
    UpdatedProducts listener;

    public CategoryPanel(UpdatedProducts listener){
        this.listener = listener; // sets the ProductsPanel Class as the listener

        // Panel Customization
        this.setPreferredSize(new Dimension(150, 150));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Object Creation
        BaseLabel allCategory = new BaseLabel("All Category", 20);
        BaseLabel burger = new BaseLabel("Burger", 20);
        BaseLabel chicken = new BaseLabel("Chicken", 20);
        BaseLabel drink = new BaseLabel("Drink", 20);

        // Adding of components to the panel
        this.add(Box.createVerticalStrut(10));
        this.add(allCategory);
        this.add(Box.createVerticalStrut(10));
        this.add(burger);
        this.add(Box.createVerticalStrut(10));
        this.add(chicken);
        this.add(Box.createVerticalStrut(10));
        this.add(drink);

        // Looping through the components for styling and mouse listener
        for(Component component : this.getComponents()){
            if(component instanceof BaseLabel label){
                label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // creates a padding 5px
                label.setForeground(Color.black); // sets the font color to black
                label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height)); // sets the maximum width it can take and the maximum height
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        BaseLabel clickedLabel = (BaseLabel) e.getSource(); // getting which label is clicked
                        String queryProduct = clickedLabel.getText(); // products passed to the parameter for fetching
                        BaseLabel.categoryPanelActive(clickedLabel); // passing the BaseLabel Object to change the flag reference

                        listener.onUpdateProducts(ProductService.getProducts(queryProduct)); // sends the new product as the parameter
                    }
                });
            }
        }

        // used as initialization
        BaseLabel.categoryPanelActive(allCategory);
        listener.onUpdateProducts(ProductService.getProducts("All Category"));
    }
}
