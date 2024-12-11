

import javax.swing.*; // Provides classes for building a GUI, e.g., JFrame, JButton
import javax.swing.border.TitledBorder; // Enables titled borders around panels
import java.awt.*; // Provides layout managers and UI styling components
import java.awt.event.*; // Handles events such as button clicks
import java.util.ArrayList; // Implements a dynamic resizable array for storing data

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