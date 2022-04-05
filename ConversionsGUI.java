/**
 * CMSC 350 Project 1
 * Generates GUI to use FixConversions, which is to switch expressions between prefix and postfix expressions.
 * Inputs user String of a prefix or postfix expression.
 * Outputs String of a prefix or postfix expression
 * 
 * @author Christopher Stoner
 */
package project1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConversionsGUI extends JFrame {
    private JTextField input = new JTextField(20);
    private JTextField output = new JTextField(20);
    private final JButton preToPost = new JButton("Prefix to Postfix");
    private final JButton postToPre = new JButton("Postfix to Prefix"); // Creating textfields for input output and
                                                                        // buttons for prefix to postfix and postfix to
                                                                        // prefix

    private final ActionListener preToPostListener = event -> { // Listener for prefix to postfix
        try {
            String postFix = FixConversions.convertPrefixPostfix(input.getText()); // Sends String input to
                                                                                   // convertPrefixPostfix method
            output.setText("" + postFix); // outputs the postfix returned from convertPrefixPostfix method
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An Exception has been thrown! -> " + e);
        } // If something went wrong, throw exception in message dialog
    };

    private final ActionListener postToPreListener = event -> { // Listener for postfix to prefix
        try {
            String preFix = FixConversions.convertPostfixPrefix(input.getText()); // Sends String input to
                                                                                  // convertPostfixPrefix method
            output.setText("" + preFix); // outputs the prefix returned from converPostfixPrefix
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An Exception has been thrown! -> " + e);
        } // If something went wrong, throw exception in message dialog
    };

    ConversionsGUI() {
        super("Prefix/Postfix Expression Converter"); // Creates text at top of window
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1)); // Create layout of 3 rows, 1 column
        JComponent[] inputComponents = { new JLabel("Enter Expression"), input };
        JComponent[] buttonComponents = { preToPost, postToPre };
        JComponent[] outputComponents = { new JLabel("Result"), output }; // Take each component by trait and place them
                                                                          // in a JComponent array to be displayed in
                                                                          // each row

        makeFlowPanel(inputComponents); // add components to a flow layout panel
        makeFlowPanel(buttonComponents);
        makeFlowPanel(outputComponents);

        output.setEditable(false);
        preToPost.addActionListener(preToPostListener);
        postToPre.addActionListener(postToPreListener); // add ActionListeners to buttons
    }

    /**
     * Makes a flow panel for each row of the inner GUI window by creating a panel
     * with a flow layout for each component in JComponent array
     * 
     * @param components array of JComponents
     */
    private void makeFlowPanel(JComponent[] components) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        for (Component component : components) {
            panel.add(component);
        }
        add(panel);
    }

    public static void main(String[] args) {
        ConversionsGUI frame = new ConversionsGUI(); // create new frame object
        frame.setVisible(true); // set that frame to be visible
    }
}
