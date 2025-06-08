package Components;

import java.awt.*;
import javax.swing.*;

import Components.Panels.LabeledTextFieldPanel;
import Services.LoginService;

public class LoginFrame extends JFrame {
    public static String loggedUser;

    public LoginFrame(){
        this.setTitle("Simple Point of Sales");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // makes the frame center to the monitor
        this.getContentPane().setBackground(new Color(51, 49, 61)); // sets the background color of the frame
        this.setLayout(new GridLayout(4,1, 0, 10));

        initComponents();

        this.setVisible(true);
        this.pack(); // keeps the size of the frame pack to the components
    }

    void initComponents(){
        // Labels
        BaseLabel loginLabel = new BaseLabel("Login");

        // Custom Labeled Text Field Panels
        LabeledTextFieldPanel username = new LabeledTextFieldPanel("Username:", 30);
        LabeledTextFieldPanel password = new LabeledTextFieldPanel("Password:", 30);

        // Button
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        JButton loginBtn = new JButton("Log in");
        this.getRootPane().setDefaultButton(loginBtn); // this makes the button be trigger using enter key
        panel.add(loginBtn); // added panel since the frame is in GridLayout
        loginBtn.addActionListener(e -> {
            String user = username.getTextFieldValue();
            String pass = password.getTextFieldValue();

            if(LoginService.isValidUser(user, pass)){
                System.out.println("Welcome user");
                loggedUser = user;
                this.dispose();
                new POSFrame();
            }else{
                System.out.println("Invalid Credentials");
            }
        });

        // creating padding for each component
        loginLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        username.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        password.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.add(loginLabel);
        this.add(username);
        this.add(password);
        this.add(panel);
    }
}
