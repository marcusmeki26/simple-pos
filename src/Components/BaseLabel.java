package Components;

import javax.swing.*;
import java.awt.*;

public class BaseLabel extends JLabel {

    private static JLabel activeCategory; // made static so that it is shared across different instances

    // parameter only for string
    public BaseLabel(String textInput){
        super(textInput);
        this.setForeground(Color.white);
        this.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    // parameter for string and font size
    public BaseLabel(String textInput, int size){
        super(textInput);
        this.setForeground(Color.white);
        this.setFont(new Font("Arial", Font.PLAIN , size));
    }

    // parameter for string, font weight and font size
    public BaseLabel(String textInput, int fontWeight, int size){
        super(textInput);
        this.setForeground(Color.white);
        this.setFont(new Font("Arial", fontWeight, size));
    }

    // parameter for adding Icon
    public BaseLabel(Icon icon){
        super(icon);
    }

    // method for the highlight or active label
    public static void categoryPanelActive(BaseLabel clickedLabel){
        // removes the styling on the previous label
        if(activeCategory != null){
            activeCategory.setBackground(null);
            activeCategory.setOpaque(false);
            activeCategory.setForeground(Color.black);
        }
        // adding a style on the clicked label
        activeCategory = clickedLabel;
        clickedLabel.setOpaque(true);
        clickedLabel.setBackground(Color.black);
        clickedLabel.setForeground(Color.white);

    }
}
