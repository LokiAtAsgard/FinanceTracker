private JPanel createMainMenuPanel() {
    JPanel mainMenuPanel = new JPanel();
    mainMenuPanel.setLayout(new GridLayout(3, 1, 10, 10));
    mainMenuPanel.setBackground(new Color(30, 30, 30));

    JButton financeTrackerButton = createHoverableButton("Track My Finances");
    JButton calculatorButton = createHoverableButton("Calculate on your Own");
    JButton exitButton = createHoverableButton("Exit");

    financeTrackerButton.addActionListener(event -> navigateToPanel("Finance Tracker"));
    calculatorButton.addActionListener(event -> navigateToPanel("Calculator"));
    exitButton.addActionListener(event -> confirmExit());

    mainMenuPanel.add(financeTrackerButton);
    mainMenuPanel.add(calculatorButton);
    mainMenuPanel.add(exitButton);

    return mainMenuPanel;
}
