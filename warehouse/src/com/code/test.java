package com.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files

public class test {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    //First we need to declare the objects
    JButton button;
    JLabel label;
    File map = new File("map.txt");

    JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();


    public void GenerateCell(int x, int y, int SID, Integer isButton){

        if (isButton == 1) {
            button = new JButton(Integer.toString(SID));
            button.setPreferredSize(new Dimension(30,30));
            button.setMargin(new Insets(0,0,0,0));
            button.setFont(new Font("Arial", Font.PLAIN,9));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Custom button text
                    Object[] options = {"Show goods", "Add goods"};
                    String longMessage = "stolicka\n"
                            +"stol\n"
                            +"hhhhh\n"
                            +"hhhhh\n"
                            +"hhhhh\n"
                            +"hhhhh\n"
                            +"hhhhh\n";

                    int choice = JOptionPane.showOptionDialog(null,
                            "What action do you want to commit",                            "Information",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            options,
                            options[0]);

                    if(choice == 0){
                        JTextArea textArea = new JTextArea(5,0);
                        textArea.setText(longMessage);
                        textArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        JOptionPane.showMessageDialog(null, scrollPane,"Goods in shelf", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (choice == 1){
                        JOptionPane.showMessageDialog(null, "to be done", "Add goods", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            });

        } else {
            label = new JLabel();
            label.setPreferredSize(new Dimension(30,30));
            label.setVisible(true);
        }
        c.gridx = x;
        c.gridy = y;
        if (isButton == 1) {
            pane.add(button, c);
        } else {
            pane.add(label, c);
        }
    }


    public void BuildMap() {
        int ShelfID = 0;
        int y = 0;
        try{
            Scanner myReader = new Scanner(map);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
               // System.out.println(data);
                String[] row = data.split(" ", 50);
                //System.out.println(Arrays.toString(row));
                Integer[] result = new Integer[row.length];
                for (int i = 0; i < row.length; i++) {
                    result[i] = Integer.parseInt(row[i]);
                }

                for (int x = 0; x < result.length; x++) {
                    if (result[x] == 1) {
                        ShelfID++;
                    }
                    GenerateCell(x, y, ShelfID, result[x]);
                }

                y++;

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public test(){

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        BuildMap();

    }

    public static void main(String[] args) {


        JFrame frame = new JFrame("Warehouse");
        frame.setSize(1200,800);

        JPanel win = new JPanel(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();


        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        //panel.setBounds(900,40,200,600);
        panel.setPreferredSize(new Dimension(200,620));

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.gray);
        //panel.setBounds(900,40,200,600);
        panel3.setPreferredSize(new Dimension(200,620));

        JPanel panel2 = new JPanel();
        //panel2.setBounds(0,0,100,100);
        panel2.add(new test().pane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c2.gridx = 0;
        c2.gridy = 0;
        win.add(panel3, c2);

        c2.gridx = 1;
        c2.gridy = 0;
        win.add(panel2, c2);

        c2.gridx = 2;
        c2.gridy = 0;
        win.add(panel, c2);

        JScrollPane scrl = new JScrollPane(win);
        frame.add(scrl);

        frame.setVisible(true);


    }
}
