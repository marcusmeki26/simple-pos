package Components;

import Components.Panels.CategoryPanel;
import Components.Panels.HeaderPanel;
import Components.Panels.ProductsPanel;

import javax.swing.*;
import java.awt.*;

public class POSFrame extends JFrame {
    public POSFrame(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Simple Point of Sales");
        this.setSize(screenSize.width, screenSize.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        this.setVisible(true);
    }

    public void initComponents(){
        this.add(new HeaderPanel(), BorderLayout.NORTH);
        ProductsPanel productsPanel = new ProductsPanel();
        this.add(productsPanel, BorderLayout.CENTER);
        this.add(new CategoryPanel(productsPanel), BorderLayout.WEST);
    }
}
