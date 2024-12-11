private void calculateTotals() { // Calculates the total amount spent
    double total = 0; // Initializes total to 0

    for (JPanel section : expenseSections) { // Loops through each expense section
        Component[] components = section.getComponents(); // Gets components of the section
        String amount = ((JTextField) components[5]).getText(); // Retrieves money spent input

        try { // Attempts to parse the amount to a double
            total += Double.parseDouble(amount); // Adds the amount to the total
        } catch (NumberFormatException e) { // If parsing fails
            JOptionPane.showMessageDialog(mainFrame, "Invalid number format for money spent.", "Error", JOptionPane.ERROR_MESSAGE); // Displays error
        }
    }

    JOptionPane.showMessageDialog(mainFrame, "Total Expenses: Php " + total, "Calculation", JOptionPane.INFORMATION_MESSAGE); // Displays the total
}

private JPanel createCalculatorPanel() { // Creates the calculator panel
    JPanel calculatorPanel = new JPanel(new BorderLayout()); // Panel layout for the calculator

    JTextField display = new JTextField(); // Text field to show calculator input/output
    display.setEditable(false); // Prevents user from directly editing the display
    display.setHorizontalAlignment(JTextField.RIGHT); // Aligns text to the right
    display.setFont(new Font("Arial", Font.BOLD, 24)); // Sets font for the display

    JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10)); // Grid layout for calculator buttons
    String[] buttons = { // Array of calculator button labels
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+", 
            "1/x", "C"
    };

    ActionListener calculatorListener = new ActionListener() { // ActionListener for calculator buttons
        private String operator = ""; // Stores the selected operator
        private double operand1 = 0, operand2 = 0; // Stores operands

        @Override
        public void actionPerformed(ActionEvent e) { // Handles button clicks
            String command = e.getActionCommand(); // Gets the button text
            if (command.matches("\\d|\\.")) { // If it's a number or a dot
                display.setText(display.getText() + command); // Appends the digit to the display
            } else if (command.equals("C")) { // Clears the calculator
                display.setText(""); // Resets the display
                operator = ""; // Resets the operator
                operand1 = operand2 = 0; // Resets operands
            } else if (command.equals("=")) { // Executes the calculation
                operand2 = Double.parseDouble(display.getText()); // Parses the second operand
                switch (operator) { // Performs the operation based on the operator
                    case "+":
                        display.setText(String.valueOf(operand1 + operand2)); // Adds
                        break;
                    case "-":
                        display.setText(String.valueOf(operand1 - operand2)); // Subtracts
                        break;
                    case "*":
                        display.setText(String.valueOf(operand1 * operand2)); // Multiplies
                        break;
                    case "/":
                        display.setText(operand2 != 0 ? String.valueOf(operand1 / operand2) : "Error"); // Divides, checks for zero
                        break;
                    default:
                        display.setText("Error"); // Displays error for invalid operator
                        break;
                }
            } else if (command.equals("1/x")) { // Calculates reciprocal
                double value = Double.parseDouble(display.getText()); // Parses the display value
                display.setText(value != 0 ? String.valueOf(1 / value) : "Error"); // Calculates reciprocal or shows error
            } else { // Handles operator input
                operand1 = Double.parseDouble(display.getText()); // Parses the first operand
                operator = command; // Stores the operator
                display.setText(""); // Clears the display for the next input
            }
        }
    };

    for (String text : buttons) { // Creates and adds buttons to the panel
        JButton button = new JButton(text); // Creates a button with the label
        button.setFont(new Font("Arial", Font.BOLD, 20)); // Sets the font for the button
        button.addActionListener(calculatorListener); // Adds the ActionListener
        buttonPanel.add(button); // Adds the button to the panel
    }