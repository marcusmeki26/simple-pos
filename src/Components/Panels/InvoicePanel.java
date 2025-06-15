package Components.Panels;

import Components.BaseLabel;
import Components.CircularBackgroundLabel;
import Interface.SelectedProduct;
import Model.ProductModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InvoicePanel extends JPanel implements SelectedProduct {
    JPanel selectedProductsPanel = new JPanel();
    BaseLabel totalValue = new BaseLabel("0", 20)
            .changeColor(Color.black);

    public InvoicePanel(){
        this.setPreferredSize(new Dimension(450, 150));
        this.setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents(){
        JPanel labelContainer = new JPanel(); // panel for label
        JPanel totalContainer = new JPanel(); // panel for the total

        selectedProductsPanel.setPreferredSize(new Dimension(150, 150));
        selectedProductsPanel.setLayout(new BoxLayout(selectedProductsPanel, BoxLayout.Y_AXIS));

        labelContainer.setLayout(new BoxLayout(labelContainer, BoxLayout.X_AXIS));
        labelContainer.setBackground(new Color(125, 125 ,125));

        // adding components inside labelContainer
        labelContainer.add(createItemLabel());
        labelContainer.add(Box.createGlue()); // for proper spacing
        labelContainer.add(new BaseLabel("QTY", 20)
                .changeColor(Color.black));
        labelContainer.add(Box.createGlue()); // for proper spacing
        labelContainer.add(createPriceLabel());
        labelContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // totalContainer Part
        JButton checkoutBtn = new JButton("Checkout");
        totalContainer.setLayout(new FlowLayout(FlowLayout.LEADING));
        totalContainer.setBackground(new Color(125, 125 ,125));

        // adding components inside totalContainer
        totalContainer.add(new BaseLabel("TOTAL: ", 20)
                .changeColor(Color.black));
        totalContainer.add(totalValue);
        totalContainer.add(checkoutBtn);

        this.add(labelContainer, BorderLayout.NORTH);
        this.add(selectedProductsPanel, BorderLayout.CENTER);
        this.add(totalContainer, BorderLayout.SOUTH);

        // checkout button listener
        checkoutBtnListener(checkoutBtn);
    }

    @Override
    public void onSelectedProduct(ProductModel selectedProduct){

        JPanel panel = new JPanel(); // created a new panel for the container of label to have different layout

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); // layout for the panel
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0 ,0, 5)); // added a padding
        panel.setOpaque(false);

        CircularBackgroundLabel circularLabel = new CircularBackgroundLabel("x", 20, Color.red); // for the x label
        CircularBackgroundLabel minusLabel = new CircularBackgroundLabel("-", 20, Color.black); // for the - label
        CircularBackgroundLabel addLabel = new CircularBackgroundLabel("+", 20, Color.black); // for the + label

        BaseLabel price = new BaseLabel(String.valueOf(selectedProduct.price()), 20)
                .changeColor(Color.black);

        BaseLabel quantity = new BaseLabel("1", 20)
                .changeColor(Color.black);

        circularLabel.putClientProperty("wrapper", panel); // creates a reference to the panel
        panel.add(circularLabel); // adding circular label
        panel.add(createProductNameLabel(selectedProduct.productName())); // adding product name label
        panel.add(Box.createVerticalStrut(10));
        panel.add(minusLabel); // adding minus label
        panel.add(quantity); // adding quantity label
        panel.add(addLabel); // adding add label
        panel.add(Box.createVerticalStrut(10));
        minusLabel.putClientProperty("price", selectedProduct.price());
        addLabel.putClientProperty("price", selectedProduct.price());
        panel.add(price); // adding price label

        // adding listener to the circular label
        closeListener(circularLabel);

        // adding listener for minus
        minusListener(minusLabel, quantity, price);

        // adding listener for add
        addListener(addLabel, quantity, price);

        int currentTotalValue = Integer.parseInt(totalValue.getText());
        totalValue.setText(String.valueOf(currentTotalValue + selectedProduct.price())); // sets the total value when a product is clicked

        addProductPanel(panel); // refreshes the UI
    }

    // HELPER METHODS
    private void removeProductPanel(JPanel clickedLabel){
        selectedProductsPanel.remove(clickedLabel);
        updateUIPanel();
    }

    private void addProductPanel(JPanel panel){
        selectedProductsPanel.add(panel);
        updateUIPanel();
    }

    private void updateUIPanel(){
        selectedProductsPanel.revalidate();
        selectedProductsPanel.repaint();
    }

    private BaseLabel createItemLabel(){
        return new BaseLabel("ITEM", 20)
                .withPadding(BorderFactory.createEmptyBorder(0 ,45,0 , 0))
                .changeColor(Color.black);
    }

    private BaseLabel createPriceLabel(){
        return new BaseLabel("PRICE", 20)
                .withPadding(BorderFactory.createEmptyBorder(0, 0, 0, 5))
                .changeColor(Color.black);
    }

    private BaseLabel createProductNameLabel(String productName){
        return new BaseLabel(productName, 20) // for the product name
                .changeColor(Color.black);
    }

    private void closeListener(CircularBackgroundLabel circularLabel){
        circularLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel clickedLabel = (JPanel) ((JComponent) e.getSource()).getClientProperty("wrapper"); // getting the source where the clicked happened
                BaseLabel priceLabel = (BaseLabel) clickedLabel.getComponent(7);
                totalValue.setText(String.valueOf(Integer.parseInt(totalValue.getText()) - Integer.parseInt(priceLabel.getText())));

                removeProductPanel(clickedLabel);
            }
        });
    }

    private void minusListener(CircularBackgroundLabel minusLabel, BaseLabel quantity, BaseLabel price){
        minusLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int currentQuantity = Integer.parseInt(quantity.getText());
                int currentPrice = Integer.parseInt(price.getText()); // gets the current price
                int originalPrice = (int) minusLabel.getClientProperty("price"); // gets the original price
                if(currentQuantity != 1) {
                    quantity.setText(String.valueOf(currentQuantity-1));
                    price.setText(String.valueOf(currentPrice-originalPrice));
                    int currentTotalValue = Integer.parseInt(totalValue.getText());
                    totalValue.setText(String.valueOf(currentTotalValue-originalPrice));
                    updateUIPanel();
                }
            }
        });
    }

    private void addListener(CircularBackgroundLabel addLabel, BaseLabel quantity, BaseLabel price){
        addLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int currentQuantity = Integer.parseInt(quantity.getText()); // gets the current quantity
                int currentPrice = Integer.parseInt(price.getText()); // gets the current price
                int originalPrice = (int) addLabel.getClientProperty("price"); // gets the original price
                int totalCurrentPrice = Integer.parseInt(totalValue.getText());

                quantity.setText(String.valueOf(currentQuantity+1)); // sets the new quantity
                price.setText(String.valueOf(currentPrice+originalPrice)); // sets the new price
                totalValue.setText(String.valueOf(totalCurrentPrice+originalPrice)); // sets the new total price

                updateUIPanel();
            }
        });
    }
    // fixing layout only created the logic
    private void checkoutBtnListener(JButton checkoutBtn){
        checkoutBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame popup = new JFrame();

                popup.setTitle("Confirm Transaction");
                popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                popup.setResizable(false);
                popup.setLocationRelativeTo(null);

                // Panel
                JPanel popupContainer = new JPanel();
                popupContainer.setLayout(new BoxLayout(popupContainer, BoxLayout.Y_AXIS));
                popupContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                // Components
                BaseLabel total = new BaseLabel("The total amount is: " + totalValue.getText(), 16)
                        .changeColor(Color.black);
                BaseLabel inputLabel = new BaseLabel("Amound paid: ", 16)
                        .changeColor(Color.black);
                JTextField inputAmount = new JTextField();
                JButton cancelBtn = new JButton("Cancel");
                JButton continueBtn = new JButton("Continue");

                popupContainer.add(total);
                popupContainer.add(Box.createVerticalStrut(5));
                popupContainer.add(inputLabel);
                popupContainer.add(Box.createVerticalStrut(5));
                popupContainer.add(inputAmount);
                popupContainer.add(Box.createVerticalStrut(5));
                popupContainer.add(cancelBtn);
                popupContainer.add(Box.createVerticalStrut(5));
                popupContainer.add(continueBtn);

                popup.add(popupContainer, BorderLayout.CENTER);
                popup.pack();
                popup.setVisible(true);

                cancelBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        popup.dispose();
                    }
                });

                continueBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println(Integer.parseInt(inputAmount.getText() )- Integer.parseInt(totalValue.getText()));
                        System.out.println("Successful");
                        popup.dispose();
                    }
                });
            }
        });
    }
}
