private JPanel createFinanceTrackerPanel() {
    JPanel trackerPanel = new JPanel(new BorderLayout());
    trackerPanel.setBackground(new Color(40, 40, 40));

    expenseSections = new ArrayList<>();
    trackerDetailsPanel = new JPanel();
    trackerDetailsPanel.setLayout(new BoxLayout(trackerDetailsPanel, BoxLayout.Y_AXIS));
    trackerDetailsPanel.setBackground(new Color(50, 50, 50));

    JScrollPane scrollPane = new JScrollPane(trackerDetailsPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    JButton addSectionButton = createHoverableButton("Add Month of Expenses");
    JButton deleteSectionButton = createHoverableButton("Delete Last Month");
    JButton submitButton = createHoverableButton("Submit");

    addSectionButton.addActionListener(event -> addExpenseSection());
    deleteSectionButton.addActionListener(event -> deleteLastExpenseSection());
    submitButton.addActionListener(event -> submitExpenseDetails());

    JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
    buttonPanel.add(addSectionButton);
    buttonPanel.add(deleteSectionButton);
    buttonPanel.add(submitButton);

    trackerPanel.add(scrollPane, BorderLayout.CENTER);
    trackerPanel.add(buttonPanel, BorderLayout.SOUTH);

    JButton backToMenuButton = createHoverableButton("Back to Main Menu");
    backToMenuButton.addActionListener(event -> navigateToPanel("Main Menu"));
    trackerPanel.add(backToMenuButton, BorderLayout.NORTH);

    return trackerPanel;
}
