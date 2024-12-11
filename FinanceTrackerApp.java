// Example of combining features from both sections logically

// Method for creating hoverable buttons
private JButton createHoverableButton(String text) {
    JButton button = new JButton(text);
    button.setBackground(new Color(40, 40, 40));
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createLineBorder(new Color(220, 20, 60), 2));

    button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(new Color(220, 20, 60));
            button.setForeground(Color.BLACK);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(new Color(40, 40, 40));
            button.setForeground(Color.WHITE);
        }
    });

    return button;
}

// Navigate to specific panels
private void navigateToPanel(String panelName) {
    panelSwitcher.show(contentPanel, panelName);
}

// Confirm exit method
private void confirmExit() {
    int choice = JOptionPane.showConfirmDialog(
        mainFrame,
        "Are you sure you want to exit?",
        "Exit Confirmation",
        JOptionPane.YES_NO_OPTION
    );
    if (choice == JOptionPane.YES_OPTION) {
        System.exit(0);
    }
}

// Method for calculating totals
private void calculateTotals() {
    double total = 0;
    for (JPanel section : expenseSections) {
        Component[] components = section.getComponents();
        String amount = ((JTextField) components[5]).getText();
        try {
            total += Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid number format for money spent.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    JOptionPane.showMessageDialog(mainFrame, "Total Expenses: Php " + total, "Calculation", JOptionPane.INFORMATION_MESSAGE);
}

// Method for creating calculator panel
private JPanel createCalculatorPanel() {
    JPanel calculatorPanel = new JPanel(new BorderLayout());
    JTextField display = new JTextField();
    display.setEditable(false);
    display.setHorizontalAlignment(JTextField.RIGHT);
    display.setFont(new Font("Arial", Font.BOLD, 24));

    JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));
    String[] buttons = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", ".", "=", "+",
        "1/x", "C"
    };

    ActionListener calculatorListener = new ActionListener() {
        private String operator = "";
        private double operand1 = 0, operand2 = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.matches("\\d|\\.")) {
                display.setText(display.getText() + command);
            } else if (command.equals("C")) {
                display.setText("");
                operator = "";
                operand1 = operand2 = 0;
            } else if (command.equals("=")) {
                operand2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        display.setText(String.valueOf(operand1 + operand2));
                        break;
                    case "-":
                        display.setText(String.valueOf(operand1 - operand2));
                        break;
                    case "*":
                        display.setText(String.valueOf(operand1 * operand2));
                        break;
                    case "/":
                        display.setText(operand2 != 0 ? String.valueOf(operand1 / operand2) : "Error");
                        break;
                    default:
                        display.setText("Error");
                        break;
                }
            } else if (command.equals("1/x")) {
                double value = Double.parseDouble(display.getText());
                display.setText(value != 0 ? String.valueOf(1 / value) : "Error");
            } else {
                operand1 = Double.parseDouble(display.getText());
                operator = command;
                display.setText("");
            }
        }
    };

    for (String text : buttons) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.addActionListener(calculatorListener);
        buttonPanel.add(button);
    }

    calculatorPanel.add(display, BorderLayout.NORTH);
    calculatorPanel.add(buttonPanel, BorderLayout.CENTER);

    return calculatorPanel;
}
