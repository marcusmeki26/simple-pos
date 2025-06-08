package Components.Panels;

import Components.BaseLabel;
import Interface.UpdatedProducts;
import Model.ProductModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProductsPanel extends JPanel implements UpdatedProducts {

    public ProductsPanel(){
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
    }

    @Override
    public void onUpdateProducts(ArrayList<ProductModel> newProduct){
        this.removeAll(); // removes the old components

        // loops through the available data
        for(ProductModel product : newProduct){
            ImageIcon icon = new ImageIcon(product.imgPath());
            Image resizedImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon newResizedImage = new ImageIcon(resizedImage);
            this.add(new BaseLabel(newResizedImage));

            // add the layout of the products here
        }

        this.revalidate(); // re-layouts the components
        this.repaint(); // redraw the panel
    }
}
