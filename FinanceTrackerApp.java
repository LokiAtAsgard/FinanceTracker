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

        JButton backToMenuButton = createHoverableButton("Back to Main Menu"); // Button to return to main menu
        backToMenuButton.addActionListener(event -> navigateToPanel("Main Menu")); // Navigates to the main menu

        calculatorPanel.add(display, BorderLayout.NORTH); // Adds the display to the top
        calculatorPanel.add(buttonPanel, BorderLayout.CENTER); // Adds the buttons to the center
        calculatorPanel.add(backToMenuButton, BorderLayout.SOUTH); // Adds the back button to the bottom

        return calculatorPanel; // Returns the calculator panel
    }
    private JButton createHoverableButton(String text) { // Creates a styled button with hover effects
        JButton button = new JButton(text); // Initializes the button with the provided text

        button.setBackground(new Color(40, 40, 40)); // Sets the default background color
        button.setForeground(Color.WHITE); // Sets the default text color
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Sets the font style and size
        button.setFocusPainted(false); // Disables focus painting for cleaner look
        button.setBorder(BorderFactory.createLineBorder(new Color(220, 20, 60), 2)); // Adds a border with red color

        button.addMouseListener(new MouseAdapter() { // Adds a listener for mouse hover events
            @Override
            public void mouseEntered(MouseEvent e) { // When the mouse hovers over the button
                button.setBackground(new Color(220, 20, 60)); // Changes the background color to red
                button.setForeground(Color.BLACK); // Changes the text color to black
            }

            @Override
            public void mouseExited(MouseEvent e) { // When the mouse leaves the button
                button.setBackground(new Color(40, 40, 40)); // Resets the background color
                button.setForeground(Color.WHITE); // Resets the text color
            }
        });

        return button; // Returns the styled button
    }

    private void navigateToPanel(String panelName) { // Switches the view to a specified panel
        panelSwitcher.show(contentPanel, panelName); // Uses CardLayout to switch to the target panel
    }

    private void confirmExit() { // Displays a confirmation dialog before exiting the application
        int choice = JOptionPane.showConfirmDialog( // Shows a Yes/No dialog
            mainFrame, 
            "Are you sure you want to exit?", 
            "Exit Confirmation", 
            JOptionPane.YES_NO_OPTION
        );
        if (choice == JOptionPane.YES_OPTION) { // If the user confirms exit
            System.exit(0); // Terminates the application
        }
    }

    public static void main(String[] args) { // Entry point of the application
        SwingUtilities.invokeLater(FinanceTrackerApp::new); // Launches the application on the Event Dispatch Thread
    }
}
