package bin;

import javax.swing.*;
import java.awt.*;

public class DrawNorthPanel extends JPanel {
    public DrawNorthPanel(){
        JLabel jLabel = new JLabel("ICDB");
        jLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
        this.add(jLabel);
    }
}
