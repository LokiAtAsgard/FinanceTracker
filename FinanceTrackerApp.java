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
