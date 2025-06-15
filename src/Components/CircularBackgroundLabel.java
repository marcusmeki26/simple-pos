package Components;

import javax.swing.*;
import java.awt.*;

public class CircularBackgroundLabel extends JLabel {
    Color color;

    public CircularBackgroundLabel(String textInput){
        super(textInput, SwingConstants.CENTER);
        setForeground(Color.white);
        setOpaque(false);
    }

    public CircularBackgroundLabel(String textInput, int size, Color color){
        super(textInput, SwingConstants.CENTER);
        this.color = color;
        setForeground(Color.white);
        setFont(new Font("Arial", Font.PLAIN , size));
        setOpaque(false);
    }



    @Override
    public Dimension getPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        int diameter = Math.max(fm.stringWidth(getText()), fm.getHeight()) + 20; // Padding
        return new Dimension(diameter, diameter);
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(getWidth(), getHeight());
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        // Background circle
        g2.setColor(color);
        g2.fillOval(x, y, diameter, diameter);

        g2.dispose();

        // Draw text after circle
        super.paintComponent(g);
    }
}
