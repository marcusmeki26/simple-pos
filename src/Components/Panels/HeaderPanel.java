package Components.Panels;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Components.LoginFrame;
import Components.BaseLabel;

import java.awt.*;

public class HeaderPanel extends JPanel {
    public HeaderPanel(){
        this.setBackground(new Color(0x4CBB17));
        this.setPreferredSize(new Dimension(50, 50));
        this.setLayout(new BorderLayout());

        BaseLabel userLbl = new BaseLabel("Welcome " + LoginFrame.loggedUser, 1, 16);
        BaseLabel titleLbl = new BaseLabel("Simple Point of Sales", 1, 16);

        userLbl.setBorder(new EmptyBorder(0, 10, 0, 0));
        titleLbl.setHorizontalAlignment(JLabel.CENTER);

        this.add(userLbl, BorderLayout.WEST);
        this.add(titleLbl, BorderLayout.CENTER);
    }
}
