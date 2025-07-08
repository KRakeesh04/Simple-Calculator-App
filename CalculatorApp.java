import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class CalculatorApp {
    private double num1 = 0.0;
    private double num2 = 0.0;
    private String operator = "";
    private double result = 0.0;
    private JButton[] numButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[12];
    private JTextField displayField = new JTextField();
    private JPanel buttonPanel = new JPanel();
    private JPanel historyPanel = new JPanel();
    private JButton addButton, subButton, mulButton, divButton, eqButton, 
                    clrButton, delButton, historyButton, dotButton, 
                    percentageButton, sqrtButton, squareButton, modButton, clrHistoryButton;
    private String[] History = new String[10];
    private String displayText = "";

    public CalculatorApp() {

        Image icon = new ImageIcon(getClass().getResource("Calculator_icon.jpg")).getImage();
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Simple Calculator");
        mainFrame.setIconImage(icon); 
        mainFrame.setLayout(null); // Use null layout for custom positioning
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        // Number display field 
        displayField.setBounds(30, 40, 340, 50);
        displayField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setBackground(Color.WHITE);
        displayField.setFocusable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 24));
        displayField.setEditable(false);

        mainFrame.add(displayField);

        // clear History button 
        clrHistoryButton = new JButton("Clear History");
        clrHistoryButton.setBounds(200, 130, 150, 20);
        clrHistoryButton.setFont(new Font("Arial", Font.BOLD, 14));
        clrHistoryButton.setBackground(new Color(255, 102, 102));
        clrHistoryButton.setVisible(false); // Initially hidden
        clrHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the history array and update the history panel
                for (int i = 0; i < History.length; i++) {
                    History[i] = null;
                }

                historyPanel.removeAll(); 

                JLabel noHistoryLabel = new JLabel("No history available");
                noHistoryLabel.setFont(new Font("Arial", Font.ITALIC, 16));
                historyPanel.add(noHistoryLabel);

                historyPanel.revalidate(); // Refresh the panel to show updated history
                historyPanel.repaint(); // Repaint the panel to reflect changes
                clrHistoryButton.setVisible(false); // Hide clear history button
            }
        });

        mainFrame.add(clrHistoryButton);

        // History button
        historyButton = new JButton("Show History");
        historyButton.setBounds(30, 130, 150, 20);
        historyButton.setFont(new Font("Arial", Font.BOLD, 14));
        historyButton.setBackground(new Color(102, 178, 250));
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (historyPanel.isVisible()) {
                    historyButton.setText("Show History");
                    clrHistoryButton.setVisible(false);
                } else {
                    historyButton.setText("Hide History");
                    clrHistoryButton.setVisible(true);
                }
                historyPanel.setVisible(!historyPanel.isVisible());
                buttonPanel.setVisible(!historyPanel.isVisible());

                // Update the history panel with the latest history
                historyPanel.removeAll(); 

                boolean hasHistory = false;
                for (int i = History.length-1; i>=0; i--) {
                    String historyItem = History[i];
                    if (historyItem != null) {
                        hasHistory = true;
                        JLabel historyLabel = new JLabel(historyItem);
                        historyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                        historyPanel.add(historyLabel);
                    }
                }
                if (!hasHistory) {
                    JLabel noHistoryLabel = new JLabel("No history available");
                    noHistoryLabel.setFont(new Font("Arial", Font.ITALIC, 16));
                    historyPanel.add(noHistoryLabel);
                }

                historyPanel.revalidate(); 
                historyPanel.repaint();
                clrHistoryButton.setVisible(historyPanel.isVisible() && History[History.length-1] != null);
            }
        });

        mainFrame.add(historyButton);

        // History pannel
        historyPanel.setBounds(30, 160, 340, 370);
        historyPanel.setBorder(BorderFactory.createTitledBorder("History"));
        historyPanel.setLayout(new GridLayout(10, 1, 5, 5));    // need to adjust the size
        historyPanel.setVisible(false); // Initially hidden
        historyPanel.setBackground(Color.WHITE);

        mainFrame.add(historyPanel);
        

        // Add number buttons
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numButtons[i].setBackground(Color.LIGHT_GRAY);
            numButtons[i].setFocusable(false);
            numButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            numButtons[i].setFocusPainted(false);
            numButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton sourceButton = (JButton) e.getSource();
                    String buttonText = sourceButton.getText();
                    displayText += buttonText;
                    displayField.setText(displayText);
                }
            });
            // buttonPanel.add(numButtons[i]);
        }

        // Create function buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");
        clrButton = new JButton("AC");
        delButton = new JButton("Del");
        dotButton = new JButton(".");
        percentageButton = new JButton("%");
        sqrtButton = new JButton("√");
        squareButton = new JButton("x²");
        modButton = new JButton("mod");
        
        // Add function buttons
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = eqButton;
        functionButtons[5] = clrButton;
        functionButtons[6] = delButton;
        functionButtons[7] = dotButton;
        functionButtons[8] = percentageButton;
        functionButtons[9] = sqrtButton;
        functionButtons[10] = squareButton;
        functionButtons[11] = modButton;

        for (int i = 0; i < functionButtons.length ; i++) {
            // functionButtons[i].setPreferredSize(new Dimension(58, 64));
            functionButtons[i].setMargin(new Insets(5, 5, 5, 5)); // internal padding
            if (functionButtons[i] == clrButton || functionButtons[i] == delButton) {
                functionButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            } else {
                functionButtons[i].setFont(new Font("Arial", Font.BOLD, 24));
            }
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            functionButtons[i].setFocusPainted(false);
            functionButtons[i].setBackground(Color.LIGHT_GRAY);

            functionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton sourceButton = (JButton) e.getSource();
                    String buttonText = sourceButton.getText();
                    if (buttonText.equals("%")) {
                        operator = "%" ;
                    } else if (buttonText.equals("√")) {
                        operator = "√";
                    // } else if (buttonText.equals("%")) {
                        
                    }
                    if (buttonText.equals("=") || buttonText.equals("%") ) {
                        try {
                            if (operator != "x²") {
                                num2 = Double.parseDouble(displayText);
                            }
                            switch (operator) {
                                case "+":
                                    result = num1 + num2;
                                    break;

                                case "-":
                                    result = num1 - num2;
                                    break;

                                case "*":
                                    result = num1 * num2;
                                    break;

                                case "/":
                                    if (num2 != 0) {
                                        result = num1 / num2;
                                    } else {
                                        JOptionPane.showMessageDialog(mainFrame, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    break;

                                case "%":
                                    if (!displayText.isEmpty()) {
                                        result = Double.parseDouble(displayText) / 100; // Percentage calculation
                                    } else {
                                        JOptionPane.showMessageDialog(mainFrame, "Please enter a number first", "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    break;

                                case "√":
                                    result = Math.sqrt(num2);
                                    break;

                                case "x²":
                                    result = num1 * num1;
                                    break;

                                case "mod":
                                    if (isInteger(num1) && isInteger(num2)) {
                                        result = Math.floorMod((int)num1, (int)num2);
                                        break;
                                    } else {
                                        JOptionPane.showMessageDialog(mainFrame, "Modulus division is only defined for integers", "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    // try {  
                                    //     result = Math.floorMod((int)num1, (int)num2);
                                    // } catch (IllegalArgumentException ex) {
                                    //     JOptionPane.showMessageDialog(mainFrame, "Modulus division is only defined for integers", "Error", JOptionPane.ERROR_MESSAGE);
                                    //     return;
                                    // }
                                    break;

                                default:
                                    JOptionPane.showMessageDialog(mainFrame, "Invalid operator", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                            }
                            // TODO: need to approximate the numbers for the result
                            displayText = String.valueOf(result); // Update displayText with result
                            displayField.setText(displayText);

                            
                            if (History.length == 10) {
                                for (int i = 0; i < History.length - 1; i++) {
                                    History[i] = History[i + 1]; // Shift history
                                }
                                History[History.length - 1] = null;
                            }

                            String historyText ;
                            if (operator.equals("√")) {
                                historyText = operator + "(" + num1 + ") = " + displayText; 
                            } else if (operator.equals("x²")) {
                                historyText = num1 + "²" + " = " + displayText; 
                            } else if (operator.equals("%")) {
                                historyText = num2 + " = " + displayText + "%";
                            } else {
                                historyText = num1 + " " + operator + " " + num2 + " = " + displayText; 
                            }
                            // Skip adding to history if the last entry is the same
                            if (History[History.length - 1] != historyText && !historyText.isEmpty() && History[History.length - 1] == null) {
                                History[History.length - 1] = historyText; 
                            }


                            operator = ""; 
                            num1 = result; 
                            num2 = 0.0; 

                            
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(mainFrame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } else if ( buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") || 
                                buttonText.equals("/") || buttonText.equals("x²") || buttonText.equals("mod") ) {
                        if (!displayText.isEmpty()) {
                            num1 = Double.parseDouble(displayText);
                            operator = buttonText; // Set the operator
                            displayText = ""; // Clear display for next input
                            displayField.setText(displayText);
                        } else {
                            JOptionPane.showMessageDialog(mainFrame, "Please enter a number first", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (buttonText.equals(".")) {
                        if (!displayText.contains(".")) {
                            displayText += ".";
                            displayField.setText(displayText);
                        }
                    } else if (buttonText.equals("√") && displayText.isEmpty()) {
                        operator = "√";
                        displayText = operator;
                        displayField.setText(displayText);

                    } else if (buttonText.equals("AC")) {
                        displayText = "";
                        displayField.setText(displayText);
                        num1 = 0.0;
                        num2 = 0.0;
                        operator = "";
                        result = 0.0;
                    } else if (buttonText.equals("Del")) {
                        displayText = displayField.getText();
                        if (!displayText.isEmpty()) {
                            displayText = displayText.substring(0, displayText.length()-1);
                            displayField.setText(displayText);
                        } 
                    }
                }
            });

        }


        // /*** Create button panel with GridBagLayout ***/
        // buttonPanel.setBorder(BorderFactory.createLineBorder(null, 1));
        // buttonPanel.setBounds(30, 160, 340, 370);
        // buttonPanel.setBackground(Color.LIGHT_GRAY);
        // buttonPanel.setLayout(new GridBagLayout());

        // // Add number buttons and function buttons to buttonPanel
        // // Row 1
        // addButton(buttonPanel, clrButton, 0, 0, 2, 1);
        // addButton(buttonPanel, delButton, 2, 0, 2, 1);
        // addButton(buttonPanel, modButton, 4, 0, 1, 1);
        // // Row 2
        // addButton(buttonPanel, numButtons[7], 0, 1, 1, 1);
        // addButton(buttonPanel, numButtons[8], 1, 1, 1, 1);
        // addButton(buttonPanel, numButtons[9], 2, 1, 1, 1);
        // addButton(buttonPanel, divButton, 3, 1, 1, 1);
        // addButton(buttonPanel, sqrtButton, 4, 1, 1, 1);
        // // Row 3
        // addButton(buttonPanel, numButtons[4], 0, 2, 1, 1);
        // addButton(buttonPanel, numButtons[5], 1, 2, 1, 1);
        // addButton(buttonPanel, numButtons[6], 2, 2, 1, 1);
        // addButton(buttonPanel, mulButton, 3, 2, 1, 1);
        // addButton(buttonPanel, squareButton, 4, 2, 1, 1);
        // // Row 4
        // addButton(buttonPanel, numButtons[1], 0, 3, 1, 1);
        // addButton(buttonPanel, numButtons[2], 1, 3, 1, 1);
        // addButton(buttonPanel, numButtons[3], 2, 3, 1, 1);
        // addButton(buttonPanel, subButton, 3, 3, 1, 1);
        // addButton(buttonPanel, eqButton, 4, 3, 1, 2);
        // // Row 5
        // addButton(buttonPanel, numButtons[0], 0, 4, 1, 1);
        // addButton(buttonPanel, dotButton, 1, 4, 1, 1);
        // addButton(buttonPanel, percentageButton, 2, 4, 1, 1);
        // addButton(buttonPanel, addButton, 3, 4, 1, 1);

        // mainFrame.add(buttonPanel); 


        /*** Create button panel with GridLayout ***/
        buttonPanel = new JPanel(new GridLayout(5, 5, 10, 10));
        buttonPanel.setBounds(30, 160, 340, 370);  
        Border padding = BorderFactory.createEmptyBorder(8, 8, 8, 8);
        Border line = BorderFactory.createLineBorder(Color.black, 2);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(line, padding));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        buttonPanel.add(clrButton);        // Row 1
        buttonPanel.add(new JLabel());     // empty placeholder
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(delButton);
        
        buttonPanel.add(numButtons[7]);    // Row 2
        buttonPanel.add(numButtons[8]);
        buttonPanel.add(numButtons[9]);
        buttonPanel.add(divButton);
        modButton.setFont(new Font("Arial", Font.BOLD, 16)); // Adjust font size for better visibility
        buttonPanel.add(modButton);
        
        buttonPanel.add(numButtons[4]);    // Row 3
        buttonPanel.add(numButtons[5]);
        buttonPanel.add(numButtons[6]);
        buttonPanel.add(mulButton);
        buttonPanel.add(sqrtButton);
        
        buttonPanel.add(numButtons[1]);    // Row 4
        buttonPanel.add(numButtons[2]);
        buttonPanel.add(numButtons[3]);
        buttonPanel.add(subButton);
        buttonPanel.add(squareButton);
        
        buttonPanel.add(numButtons[0]);    // Row 5
        buttonPanel.add(dotButton);
        buttonPanel.add(percentageButton);
        buttonPanel.add(addButton);
        buttonPanel.add(eqButton);
        
        mainFrame.add(buttonPanel);



        mainFrame.setVisible(true);
    }

    // // Method to add buttons to the panel with GridBagLayout
    // private void addButton(JPanel panel, JButton button, int x, int y, int w, int h) {
    //     GridBagConstraints gbc = new GridBagConstraints();
    //     gbc.gridx = x;
    //     gbc.gridy = y;
    //     gbc.gridwidth = w;
    //     gbc.gridheight = h;
    //     gbc.insets = new Insets(5, 5, 5, 5);
    //     gbc.fill = GridBagConstraints.BOTH;
    //     gbc.weightx = 1.0;
    //     gbc.weighty = 1.0;
    //     button.setPreferredSize(new Dimension(58, 64));
    //     panel.add(button, gbc);
    // }

    private boolean isInteger(double num) {
        if (!String.valueOf(num).contains(".")) {
            return true;
        } else {
            String numVal = String.valueOf(num);
            String decimalVal = numVal.substring(numVal.indexOf('.')+1);
            if (Integer.parseInt(decimalVal) == 0) {
                return true;
            } else {
                return false;
            }
        }
    }


    public static void main(String[] args) {
        new CalculatorApp();
    }   
}
