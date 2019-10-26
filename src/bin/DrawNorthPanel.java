package bin;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DrawNorthPanel extends JPanel {
    public DrawNorthPanel(){


        JLabel jLabel = new JLabel("ICDB");
        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("./font/yummy_bread.ttf")).deriveFont(128f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);

            jLabel.setFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }



        this.add(jLabel);
    }
}
