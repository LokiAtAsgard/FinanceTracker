private void addExpenseSection() { // Adds a new section for expense details
    JPanel section = new JPanel(new GridLayout(3, 2, 10, 10)); // Section layout for details
    section.setBackground(new Color(60, 60, 60)); // Sets the background color

    TitledBorder titledBorder = BorderFactory.createTitledBorder("Expense Details"); // Adds titled border
    titledBorder.setTitleColor(Color.WHITE); // Sets the title color to white
    section.setBorder(titledBorder); // Applies the border to the section

    JLabel monthLabel = new JLabel("Month of Expense:"); // Label for the month input
    monthLabel.setForeground(Color.WHITE); // Sets the label text color to white
    JTextField monthField = new JTextField(); // Text field for month input

    JLabel expenseLabel = new JLabel("<html>Expense Description:<br>(include everything per month)</html>"); // Label for expense description
    expenseLabel.setForeground(Color.WHITE); // Sets the label text color to white
    JTextField expenseField = new JTextField(); // Text field for expense description

    JLabel moneyLabel = new JLabel("Money Spent:"); // Label for money input
    moneyLabel.setForeground(Color.WHITE); // Sets the label text color to white
    JTextField moneyField = new JTextField(); // Text field for money input

    section.add(monthLabel); // Adds the month label to the section
    section.add(monthField); // Adds the month text field
    section.add(expenseLabel); // Adds the expense label
    section.add(expenseField); // Adds the expense text field
    section.add(moneyLabel); // Adds the money label
    section.add(moneyField); // Adds the money text field

    expenseSections.add(section); // Adds the section to the list of expense sections
    trackerDetailsPanel.add(section); // Displays the section on the tracker panel
    trackerDetailsPanel.revalidate(); // Refreshes the tracker panel to show updates
    trackerDetailsPanel.repaint(); // Repaints the panel to reflect changes
}

private void deleteLastExpenseSection() { // Deletes the most recently added expense section
    if (!expenseSections.isEmpty()) { // Checks if there are sections to delete
        JPanel lastSection = expenseSections.remove(expenseSections.size() - 1); // Removes the last section
        trackerDetailsPanel.remove(lastSection); // Removes the section from the tracker panel
        trackerDetailsPanel.revalidate(); // Refreshes the tracker panel
        trackerDetailsPanel.repaint(); // Repaints the panel
    } else { // If no sections to delete
        JOptionPane.showMessageDialog(mainFrame, "<html>No expense sections to delete!<br>Add more to process again</html>"); // Displays an alert
    }
}

private void submitExpenseDetails() { // Submits and displays all entered expense details
    StringBuilder summary = new StringBuilder(); // Creates a string builder for the summary
    if (expenseSections.isEmpty()) { // Checks if no sections have been added
        JOptionPane.showMessageDialog(mainFrame, "You haven't added anything, silly!", "No Data", JOptionPane.WARNING_MESSAGE); // Displays warning
        return; // Stops further processing
    }
    for (JPanel section : expenseSections) { // Loops through each expense section
        Component[] components = section.getComponents(); // Gets components of the section
        String month = ((JTextField) components[1]).getText(); // Retrieves the month input
        String expense = ((JTextField) components[3]).getText(); // Retrieves the expense description
        String amount = ((JTextField) components[5]).getText(); // Retrieves the money spent

        summary.append("Month of Expense: ").append(month).append("\n"); // Adds month to summary
        summary.append("Expenses:           Money Spent:\n"); // Adds header for expenses
        summary.append(expense).append("              ").append(amount).append("\n\n"); // Adds details
    }

    JOptionPane.showMessageDialog(mainFrame, summary.toString(), "Expense Summary", JOptionPane.INFORMATION_MESSAGE); // Displays the summary

    int choice = JOptionPane.showConfirmDialog(mainFrame, "Do you want to calculate totals or add more data?", "Next Step", JOptionPane.YES_NO_CANCEL_OPTION); // Asks for next action
    if (choice == JOptionPane.YES_OPTION) { // If user chooses to calculate totals
        calculateTotals(); // Calls the method to calculate totals
    }
}

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