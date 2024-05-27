package com.waage.ap.csa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelDude implements ActionListener, MouseListener {

    private JFrame frame;
    private JPanel[] panel;
    private int number;


    private JButton[][] buttons0 = new JButton[3][3];
    private JButton[][] buttons1 = new JButton[3][3];

    public PanelDude(){

        int number = 1;

        // Create the Frame
        frame = new JFrame("Frame Dude");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

        panel = new JPanel[]{new JPanel(), new JPanel()};

        // Panel and Buttons
        // Create 3 x 3 grids
        panel[0].setLayout(new GridLayout(3, 3));
        panel[0].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel[1].setLayout(new GridLayout(3, 3));
        panel[1].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // Init 2d arrays
        initButtons(panel[0],buttons0);
        initButtons(panel[1],buttons1);

        // Add some space between panels
        frame.add(Box.createRigidArea(new Dimension(10, 0)));

        // Add panels to the frame
        frame.add(panel[0]);
        frame.add(panel[1]);

        // Make the frame visible
        frame.setVisible(true);


    }

    private void initButtons (JPanel panel, JButton[][] buttons){
        // Init 2d array
        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[r].length; c++) {
                buttons[r][c] = new JButton();
                buttons[r][c].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[r][c].addActionListener(this);
                panel.add(buttons[r][c]);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton sourceButton = (JButton)e.getSource();
        Container parent = sourceButton.getParent();

        sourceButton.setText(String.valueOf(number++));

        // Traverse up the hierarchy to find the panel
        while (!(parent instanceof JPanel) && parent != null) {
            parent = parent.getParent();
        }

        if (parent != null) {
            JPanel parentPanel = (JPanel) parent;
            JOptionPane.showMessageDialog(frame, "Button clicked in Panel With HashCode: " + parentPanel.hashCode());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
