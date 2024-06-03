package com.waage.ap.csa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class PanelDude implements ActionListener, MouseListener, FocusListener {

    private JFrame frame;
    private JPanel[] panel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    JTextField textField;

    private JButton[][] topLeftButtons = new JButton[3][3];
    private JButton[][] topRightButtons = new JButton[3][3];
    private JButton[][] bottomLeftButtons = new JButton[3][3];
    private JButton[][] bottomRightButtons = new JButton[3][3];

    private ArrayList<JButton[][]> buttonList;

    public PanelDude(){

        buttonList = new ArrayList<>();
        buttonList.add(topLeftButtons);
        buttonList.add(topRightButtons);
        buttonList.add(bottomLeftButtons);
        buttonList.add(bottomRightButtons);

        textField = new JTextField("");

        // Create the Frame
        frame = new JFrame("Mini Sudoku Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        panel = new JPanel[]{new JPanel(), new JPanel(),new JPanel(), new JPanel()};

        topPanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.setLayout(new GridLayout(1, 2)); // Two panels in a row
        bottomPanel.setLayout(new GridLayout(1, 2)); // Two panels in a row

        // Set insets to zero for both panels
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Init all panels
        for (int i = 0; i < panel.length; i++) {
            panelButtonInit(panel[i],buttonList.get(i));
        }

        // Create North And South Regions
        topPanel.add(panel[0]);
        topPanel.add(panel[1]);

        bottomPanel.add(panel[2]);
        bottomPanel.add(panel[3]);

        // Use BorderLayout for frame layout
        // frame.setLayout(new BorderLayout());
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));


        // Add top and bottom panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);


        panel[0].setBackground(Color.BLUE);
        panel[1].setBackground(Color.CYAN);
        panel[2].setBackground(Color.BLUE);
        panel[3].setBackground(Color.CYAN);

        // Add some space between panels
        frame.add(Box.createRigidArea(new Dimension(10, 0)));

        frame.getContentPane().setBackground(Color.WHITE);

        // Make the frame visible
        frame.setVisible(true);

    }

    private void panelButtonInit(JPanel panel, JButton[][] buttons){

        // Setup Layout For Panels
        panel.setLayout(new GridLayout(3, 3));;
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Add Buttons To Panels
        initButtons(panel,buttons);

    }

    private void initButtons (JPanel panel, JButton[][] buttons){
        // Init 2d array
        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[r].length; c++) {
                buttons[r][c] = new JButton("");

                // Font and Color
                buttons[r][c].setOpaque(true);
                buttons[r][c].setBackground(Color.LIGHT_GRAY);
                // buttons[r][c].setFont(new Font("Arial", Font.PLAIN, 40));

                // Add Event Listeners
                buttons[r][c].addActionListener(this);
                buttons[r][c].addMouseListener(this);

                // Create TextField to accept input number
                JTextField textField = new JTextField("");
                textField.setFont(new Font("Arial", Font.PLAIN, 40));
                textField.setHorizontalAlignment(0); // Center Align Texxt
                textField.addFocusListener(this);

                // Finally, add text to button and buttons to the panel
                buttons[r][c].add(textField);
                panel.add(buttons[r][c]);

            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("actionPerformed");

        // Get the text field that gained focus
        JTextField field = (JTextField) e.getSource();

        String inputText = field.getText();

        // If incorrect values warn and remove!
        if (!validateInput(inputText)){
            JOptionPane.showMessageDialog(topPanel,"Invalid Input!");
            field.setText("");
        }

//        JButton clickedButton = (JButton)e.getSource();
//        Container parent = clickedButton.getParent();
//
//        // Prompt the user to enter text
//        String userInput = JOptionPane.showInputDialog("Enter text for the button:");
//        if (userInput != null && !userInput.trim().isEmpty()) {
//            // Set the button text to the user input
//            clickedButton = (JButton) e.getSource();
//            clickedButton.setText(userInput);
//        }
//
//        // Traverse up the hierarchy to find the panel
//        while (!(parent instanceof JPanel) && parent != null) {
//            parent = parent.getParent();
//        }
//
//        if (parent != null) {
//            JPanel parentPanel = (JPanel) parent;
//            // JOptionPane.showMessageDialog(frame, "Button clicked in Panel With HashCode: " + parentPanel.hashCode());
//        }
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
        Component component = (Component) e.getSource();

        // Check if the source component is indeed a JTextField
        if (component instanceof JTextField) {
            JTextField textField = (JTextField) component;
            Container parent = textField.getParent();

            // Traverse up the hierarchy to find the JButton
            while (parent != null && !(parent instanceof JButton)) {
                parent = parent.getParent();
            }

            // If a JButton is found, change its background and foreground color
            if (parent instanceof JButton) {
                JButton button = (JButton) parent;
                button.setBackground(Color.RED);
                button.setForeground(Color.BLUE); // Change color on hover (foreground)
                // JOptionPane.showMessageDialog(frame, "Button clicked in Panel With HashCode: " + parentPanel.hashCode());
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        // System.out.println("Mouse Exited");

        // if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            // button.setBackground(Color.GREEN);

            // button.setForeground(Color.BLUE); // Change color on hover (foreground)

            button.setBackground(Color.LIGHT_GRAY); // Change color on hover (foreground)
            button.setForeground(Color.LIGHT_GRAY); // Change color on hover (foreground)


        // }
    }

    @Override
    public void focusGained(FocusEvent e) {

        System.out.println("focusGained");
        // Get the text field that gained focus
        JTextField field = (JTextField) e.getSource();

//        String inputText = field.getText();
//
//        // If incorrect values warn and remove!
//        if (!validateInput(inputText)){
//            JOptionPane.showMessageDialog(topPanel,"Invalid Input!");
//            field.setText("");
//        }


        // Change the text field background color (here to yellow)
        field.setBackground(Color.ORANGE);

    }

    @Override
    public void focusLost(FocusEvent e) {

        System.out.println("focusLost");
        JTextField field = (JTextField) e.getSource();
        field.setBackground(Color.WHITE);


        String inputText = field.getText();

        // If incorrect values warn and remove!
        if (!validateInput(inputText)){
            field.setBackground(Color.RED);
            JOptionPane.showMessageDialog(topPanel,"Invalid Input!\n Enter A Number Between 1 - 6 ");
            field.setBackground(Color.WHITE);
            field.setText("");
        }
    }

    private boolean validateInput(String s) {
        int value = 0;

        System.out.println("Validate Input");

        if (s.equals("")){
            return true;
        }

        try {
            // Try to convert the string to an integer
            value = Integer.parseInt(s);
            // Check if the value is between 1 and 6
        } catch (NumberFormatException e) {
            // If the conversion fails, the string is not an integer
            // Output Not Valid
            return false;
        }

        if (value < 1 || value > 6){
            return false;
        }
        return true;
    }



}
