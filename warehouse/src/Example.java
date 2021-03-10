//You need to import java.awt.GridBagLayout for using the Layout
import java.awt.*;
import javax.swing.*; //"*" Is used for importing the whole class

public class Example{

    //First we need to declare the objects
    JPanel yourcontainer;
    GridBagLayout ourlayout;
    GridBagConstraints gbc;

    public Example(){

        //And then we initialize them
        yourcontainer = new JPanel();
        ourlayout = new GridBagLayout();
        //Setting the layout manager for our container (in this case the JPanel)
        yourcontainer.setLayout(ourlayout);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 0;
        gbc.gridheight= 0;

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.VERTICAL; //The object will extend only vertically.
        gbc.fill = GridBagConstraints.HORIZONTAL; //The object will extend only horizontally.
        gbc.fill = GridBagConstraints.BOTH; //The object will extend the same way in the 2 sides.


    }

    public static void main(String[] args) {
        return;
    }

};

