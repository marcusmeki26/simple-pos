package Components.Panels;

import Components.BaseLabel;
import Interface.SelectedProduct;
import Interface.UpdatedProducts;
import Model.ProductModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ProductsPanel extends JPanel implements UpdatedProducts {
    SelectedProduct listener;

    public ProductsPanel(SelectedProduct listener){
        this.listener = listener;
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
    }

    @Override
    public void onUpdateProducts(ArrayList<ProductModel> newProduct){
        this.removeAll(); // removes the old components

        // loops through the available data
        for(ProductModel product : newProduct){
            // Image styling/resizing
            ImageIcon icon = new ImageIcon(product.imgPath());
            Image resizedImage = icon.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
            ImageIcon newResizedImage = new ImageIcon(resizedImage);

            // called a new class for the layout of the products. Passed the values as BaseLabel Objects
            ProductDisplayLayout panel = new ProductDisplayLayout(new BaseLabel(newResizedImage), new BaseLabel(product.productName(), 20), new BaseLabel(String.valueOf(product.price()), 20));
            this.add(panel);

            // adding mouse listener per panel
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    listener.onSelectedProduct(product);
                }
            });
        }

        this.revalidate(); // re-layouts the components
        this.repaint(); // redraw the panel
    }
}
