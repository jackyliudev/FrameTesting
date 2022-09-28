import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events

import java.net.URL;
import java.util.ArrayList;
import java.io.IOException;

public class MainStart extends JPanel
                             implements ActionListener {
    private String newline = "\n";
    protected static final String textFieldString = "JTextField";
    protected static final String passwordFieldString = "JPasswordField";
    protected static final String comboString = "comboSelection";
    protected static final String buttonString = "JButton";
    
    protected JLabel actionLabel;
    protected ArrayList<JComboBox> comboBoxes = new ArrayList<JComboBox>();
    
    public MainStart() {
        setLayout(new BorderLayout());

        //Create a regular text field.
        JTextField textField = new JTextField(10);
        textField.setActionCommand(textFieldString);
        textField.addActionListener(this);

        //Create a password field.
        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setActionCommand(passwordFieldString);
        passwordField.addActionListener(this);
        
        //Create some labels for the fields.
        JLabel textFieldLabel = new JLabel(textFieldString + ": ");
        textFieldLabel.setLabelFor(textField);
        JLabel passwordFieldLabel = new JLabel(passwordFieldString + ": ");
        passwordFieldLabel.setLabelFor(passwordField);

        //Create a label to put messages during an action event.
        actionLabel = new JLabel("Type text in a field and press Enter.");
        actionLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        //Lay out the text controls and the labels.
        JPanel textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        textControlsPane.setLayout(gridbag);

        JLabel[] labels = {textFieldLabel, passwordFieldLabel};
        JTextField[] textFields = {textField, passwordField};
        addLabelTextRows(labels, textFields, gridbag, textControlsPane);

        c.gridwidth = GridBagConstraints.REMAINDER; //last
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        textControlsPane.add(actionLabel, c);
        textControlsPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Text Fields"),
                                BorderFactory.createEmptyBorder(5,5,5,5)));

        //Create a List Component and Label
        String[] comboList = {"Item1 Route Monitoring Long Strings what to do", "Item2", "Item3", "Item4", "Item5"};
        JComboBox itemList = new JComboBox(comboList);
        itemList.setSelectedIndex(2);
        itemList.setActionCommand(comboString);
        itemList.addActionListener(this);

        JLabel comboFieldLabel = new JLabel("Selected Feature: ");
        comboFieldLabel.setLabelFor(itemList);
        
        //Create a List Component and Label
        String[] comboList2 = {"Item1", "Item2", "Item3", "Item4", "Item5"};
        JComboBox itemList2 = new JComboBox(comboList2);
        itemList2.setSelectedIndex(4);
        itemList2.setActionCommand(comboString);
        itemList2.addActionListener(this);

        JLabel comboFieldLabel2 = new JLabel("Selected Feature: ");
        comboFieldLabel2.setLabelFor(itemList2);
        
        //Create a List Component and Label
        String[] comboList3 = {"Item1", "Item2", "Item3", "Item4", "Item5"};
        JComboBox itemList3 = new JComboBox(comboList3);
        itemList3.setSelectedIndex(4);
        itemList3.setActionCommand(comboString);
        itemList3.addActionListener(this);

        JLabel comboFieldLabel3 = new JLabel("Selected Feature: ");
        comboFieldLabel3.setLabelFor(itemList3);
        
        comboBoxes.add(itemList2);
        comboBoxes.add(itemList3);
        
        // Layout the Combo Selection and List Selection on a Pane
        JPanel listSelectionPane = new JPanel();
        //GridBagLayout gridbag = new GridBagLayout();
        //GridBagConstraints c = new GridBagConstraints();

        listSelectionPane.setLayout(gridbag);
        
        // Layout Combo label 1
        c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
        c.fill = GridBagConstraints.NONE;      //reset to default
        c.weightx = 0.0;                       //reset to default
        listSelectionPane.add(comboFieldLabel, c);

        c.gridwidth = GridBagConstraints.REMAINDER;     //end row
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        listSelectionPane.add(itemList, c);
        
        // Layout Combo label 2
        c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
        c.fill = GridBagConstraints.NONE;      //reset to default
        c.weightx = 0.0;                       //reset to default
        listSelectionPane.add(comboFieldLabel2, c);

        c.gridwidth = GridBagConstraints.REMAINDER;     //end row
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        listSelectionPane.add(itemList2, c);
        
        // Layout Combo label 3
        c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
        c.fill = GridBagConstraints.NONE;      //reset to default
        c.weightx = 0.0;                       //reset to default
        listSelectionPane.add(comboFieldLabel3, c);

        c.gridwidth = GridBagConstraints.REMAINDER;     //end row
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        listSelectionPane.add(itemList3, c);
        
        JButton button = new JButton();
        button.setText("Update");
        button.setCursor(Cursor.getDefaultCursor());
        button.setMargin(new Insets(0,0,0,0));
        button.setActionCommand(buttonString);
        button.addActionListener(this);
        c.gridwidth = GridBagConstraints.REMAINDER; //last
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        //c.weightx = 5.0;
        listSelectionPane.add(button,c);
        
        listSelectionPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Combo Fields"),
                                BorderFactory.createEmptyBorder(5,5,5,5))); 

        
        //Put everything together.
        JPanel leftPane = new JPanel(new BorderLayout());
        JPanel rightPane = new JPanel(new BorderLayout(1,0));
        
        rightPane.add(listSelectionPane,BorderLayout.PAGE_START);
        leftPane.add(textControlsPane, 
                     BorderLayout.PAGE_START);
        /*
        leftPane.add(areaScrollPane,
                     BorderLayout.CENTER);*/

        add(leftPane, BorderLayout.LINE_START);
        add(rightPane, BorderLayout.CENTER);
    }

    private void updateCombo(String[] strArray) {
    	for(JComboBox box : comboBoxes) {
    		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>( strArray );
    		box.setModel(model);
    	}
    }
    
    private void addLabelTextRows(JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;

        for (int i = 0; i < numLabels; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0;                       //reset to default
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String prefix = "You typed \"";
        if (textFieldString.equals(e.getActionCommand())) {
            JTextField source = (JTextField)e.getSource();
            actionLabel.setText(prefix + source.getText() + "\"");
        } else if (passwordFieldString.equals(e.getActionCommand())) {
            JPasswordField source = (JPasswordField)e.getSource();
            actionLabel.setText(prefix + new String(source.getPassword())
                                + "\"");
        } else if (comboString.equals(e.getActionCommand())) {
        	JComboBox cb = (JComboBox) e.getSource();
        	String selection = (String) cb.getSelectedItem();
        	actionLabel.setText(selection);
        } else if (buttonString.equals(e.getActionCommand())) {
        	String[] newArray= {"A","B","C"};
        	updateCombo(newArray);
        	System.out.println("Button pressed");
        }
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TextSamplerDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new MainStart());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    }
}
