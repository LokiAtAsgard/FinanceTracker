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

    private JPanel createFinanceTrackerPanel() { // Creates the finance tracker panel
        JPanel trackerPanel = new JPanel(new BorderLayout()); // Tracker panel with BorderLayout
        trackerPanel.setBackground(new Color(40, 40, 40)); // Sets the background color

        expenseSections = new ArrayList<>(); // Initializes the list of expense sections
        trackerDetailsPanel = new JPanel(); // Panel for showing expense sections
        trackerDetailsPanel.setLayout(new BoxLayout(trackerDetailsPanel, BoxLayout.Y_AXIS)); // Vertical alignment
        trackerDetailsPanel.setBackground(new Color(50, 50, 50)); // Sets the background color

        JScrollPane scrollPane = new JScrollPane(trackerDetailsPanel); // Scrolls for large data sets
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Enables horizontal scroll
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Enables vertical scroll

        JButton addSectionButton = createHoverableButton("Add Month of Expenses"); // Adds expense section
        JButton deleteSectionButton = createHoverableButton("Delete Last Month"); // Deletes last section
        JButton submitButton = createHoverableButton("Submit"); // Submits all entered details

        addSectionButton.addActionListener(event -> addExpenseSection()); // Adds an expense section
        deleteSectionButton.addActionListener(event -> deleteLastExpenseSection()); // Deletes the last section
        submitButton.addActionListener(event -> submitExpenseDetails()); // Submits the entered expenses

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10)); // Holds buttons in grid layout
        buttonPanel.add(addSectionButton); // Adds the add section button
        buttonPanel.add(deleteSectionButton); // Adds the delete section button
        buttonPanel.add(submitButton); // Adds the submit button

        trackerPanel.add(scrollPane, BorderLayout.CENTER); // Adds scrollPane to the tracker panel
        trackerPanel.add(buttonPanel, BorderLayout.SOUTH); // Adds the button panel at the bottom

        JButton backToMenuButton = createHoverableButton("Back to Main Menu"); // Back to menu button
        backToMenuButton.addActionListener(event -> navigateToPanel("Main Menu")); // Navigates to menu
        trackerPanel.add(backToMenuButton, BorderLayout.NORTH); // Adds the back button at the top

        return trackerPanel; // Returns the tracker panel
    }
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