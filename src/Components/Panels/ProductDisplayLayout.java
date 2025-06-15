package Components.Panels;



import Components.BaseLabel;

import javax.swing.*;
import java.awt.*;

public class ProductDisplayLayout extends JPanel {
    public ProductDisplayLayout(BaseLabel icon, BaseLabel prodName, BaseLabel price){
        this.setPreferredSize(new Dimension(150, 180));
        this.setLayout(new BorderLayout());

        initComponents(icon, prodName, price);
    }

    private void initComponents(BaseLabel icon, BaseLabel prodName, BaseLabel price){

        JPanel panelText = new JPanel(); // created a new panel for the labels
        this.add(icon, BorderLayout.NORTH);

        panelText.setLayout(new BoxLayout(panelText, BoxLayout.Y_AXIS)); // layout for the panel used for labels
        panelText.add(prodName);
        panelText.add(price);
        this.add(panelText, BorderLayout.CENTER);

        // styles
        prodName.setForeground(Color.black); // font color
        price.setForeground(Color.black); // font color
        panelText.setBackground(Color.white); // background color
        panelText.setOpaque(true);
        panelText.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}
