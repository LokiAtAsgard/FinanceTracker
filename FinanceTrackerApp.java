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

    JButton backToMenuButton = createHoverableButton("Back to Main Menu");
    backToMenuButton.addActionListener(event -> navigateToPanel("Main Menu"));

    calculatorPanel.add(display, BorderLayout.NORTH);
    calculatorPanel.add(buttonPanel, BorderLayout.CENTER);
    calculatorPanel.add(backToMenuButton, BorderLayout.SOUTH);

    return calculatorPanel;
}
