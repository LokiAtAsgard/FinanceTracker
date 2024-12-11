import javax.swing.*; // Provides classes for building a GUI, e.g., JFrame, JButton
import javax.swing.border.TitledBorder; // Enables titled borders around panels
import java.awt.*; // Provides layout managers and UI styling components
import java.awt.event.*; // Handles events such as button clicks
import java.util.ArrayList; // Implements a dynamic resizable array for storing data

public class FinanceTrackerApp { // Defines the main class of the Finance Tracker application
    private JFrame mainFrame; // Main application window
    private JPanel contentPanel; // Container to hold different panels for navigation
    private CardLayout panelSwitcher; // Manages panel switching within contentPanel

    private ArrayList<JPanel> expenseSections; // Stores individual expense sections dynamically
    private JPanel trackerDetailsPanel; // Panel to display all expense sections in a scrollable view

    public FinanceTrackerApp() { // Constructor to initialize the application's GUI components
        mainFrame = new JFrame("Finance Tracker"); // Creates the main application window
        Image icon = Toolkit.getDefaultToolkit().getImage( // Loads the application icon
        new java.io.File("C:\\ElexisEonClark\\College\\College gawain\\Second Year\\Object Oriented Programming\\Project\\resources\\LogoFinance.png").getAbsolutePath());
        mainFrame.setIconImage(icon); // Sets the application icon
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes the app on exit
        mainFrame.setSize(650, 400); // Sets the size of the main frame
        mainFrame.setLayout(new BorderLayout()); // Sets the layout manager for the main frame

        panelSwitcher = new CardLayout(); // Initializes the CardLayout for switching panels
        contentPanel = new JPanel(panelSwitcher); // Creates the content panel with CardLayout

        JPanel menuPanel = createMainMenuPanel(); // Creates the main menu panel
        JPanel financeTrackerPanel = createFinanceTrackerPanel(); // Creates the finance tracker panel
        JPanel calculatorPanel = createCalculatorPanel(); // Creates the calculator panel

        contentPanel.add(menuPanel, "Main Menu"); // Adds the main menu to the content panel
        contentPanel.add(financeTrackerPanel, "Finance Tracker"); // Adds the tracker panel
        contentPanel.add(calculatorPanel, "Calculator"); // Adds the calculator panel

        mainFrame.add(contentPanel, BorderLayout.CENTER); // Adds contentPanel to the frame
        mainFrame.setVisible(true); // Makes the main frame visible
    }

    private JPanel createMainMenuPanel() { // Creates the main menu panel
        JPanel mainMenuPanel = new JPanel(); // Initializes the main menu panel
        mainMenuPanel.setLayout(new GridLayout(3, 1, 10, 10)); // Sets a grid layout for buttons
        mainMenuPanel.setBackground(new Color(30, 30, 30)); // Sets the background color

        JButton financeTrackerButton = createHoverableButton("Track My Finances"); // Button for finance tracker
        JButton calculatorButton = createHoverableButton("Calculate on your Own"); // Button for calculator
        JButton exitButton = createHoverableButton("Exit"); // Button to exit the app

        financeTrackerButton.addActionListener(event -> navigateToPanel("Finance Tracker")); // Navigates to tracker
        calculatorButton.addActionListener(event -> navigateToPanel("Calculator")); // Navigates to calculator
        exitButton.addActionListener(event -> confirmExit()); // Exits the application

        mainMenuPanel.add(financeTrackerButton); // Adds the tracker button to the menu
        mainMenuPanel.add(calculatorButton); // Adds the calculator button
        mainMenuPanel.add(exitButton); // Adds the exit button

        return mainMenuPanel; // Returns the main menu panel
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