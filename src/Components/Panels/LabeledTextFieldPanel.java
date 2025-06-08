package Components.Panels;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import Components.BaseLabel;

public class LabeledTextFieldPanel extends JPanel {
    private JTextField myTextField;

    public LabeledTextFieldPanel(String textLabel, int textFieldWidth){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        if(textLabel.replace(":", "").equalsIgnoreCase("password")){
            BaseLabel baseLabel = new BaseLabel(textLabel);
            myTextField = new JPasswordField(textFieldWidth);

            baseLabel.setAlignmentX(LEFT_ALIGNMENT); // MAKES THE LABEL & TEXT FIELD LEFT ALIGNED
            myTextField.setAlignmentX(LEFT_ALIGNMENT);
            ((JPasswordField)myTextField).setEchoChar('*'); // need to cast since the java checks the method based on the declared type

            this.setOpaque(false); // to still display the background color of the frame
            this.add(baseLabel);
            this.add(myTextField);
        }else{
            BaseLabel baseLabel = new BaseLabel(textLabel);
            myTextField = new JTextField(textFieldWidth);

            baseLabel.setAlignmentX(LEFT_ALIGNMENT); // MAKES THE LABEL & TEXT FIELD LEFT ALIGNED
            myTextField.setAlignmentX(LEFT_ALIGNMENT);

            this.setOpaque(false); // to still display the background color of the frame
            this.add(baseLabel);
            this.add(myTextField);
        }
    }

    public String getTextFieldValue(){
        return myTextField.getText();
    }
}
