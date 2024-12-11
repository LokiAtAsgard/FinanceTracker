import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FinanceTrackerApp {
    private JFrame mainFrame;
    private JPanel contentPanel;
    private CardLayout panelSwitcher;

    public FinanceTrackerApp() {
        mainFrame = new JFrame("Finance Tracker");
        Image icon = Toolkit.getDefaultToolkit().getImage("resources/LogoFinance.png");
        mainFrame.setIconImage(icon);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(650, 400);
        mainFrame.setLayout(new BorderLayout());

        panelSwitcher = new CardLayout();
        contentPanel = new JPanel(panelSwitcher);

        JPanel menuPanel = createMainMenuPanel();
        JPanel financeTrackerPanel = createFinanceTrackerPanel();
        JPanel calculatorPanel = createCalculatorPanel();

        contentPanel.add(menuPanel, "Main Menu");
        contentPanel.add(financeTrackerPanel, "Finance Tracker");
        contentPanel.add(calculatorPanel, "Calculator");

        mainFrame.add(contentPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }
}
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
