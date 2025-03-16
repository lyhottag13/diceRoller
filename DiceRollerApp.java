import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class DiceRollerApp extends JFrame implements ActionListener{
    private ArrayList<Dice> dieList;
    private HistoryList historyList;
    private int dieAmount;
    private boolean rolled;
    private JFrame frame;
    private JPanel settingsPanel, outputPanel, buttonPanel, backgroundPanel;
    private JLabel[] dieLabels;
    private JLabel column1, column2;
    private JTextArea rollOutputTextPane;
    private JScrollPane rollOutputScrollPane;
    private JTextField[] diceRollInput;
    private JButton rollButton, lastButton, nextButton;
    private Font diceLabelFont;

    public DiceRollerApp() {
        // This ArrayList is used to determine the rolls later.
        dieList = new ArrayList<>();
        dieList.add(new Dice(4, false));
        dieList.add(new Dice(6, false));
        dieList.add(new Dice(8, false));
        dieList.add(new Dice(10, false));
        dieList.add(new Dice(10, true));
        dieList.add(new Dice(12, false));
        dieList.add(new Dice(20, false));
        
        // I declare everything here, forgive its messiness.
        historyList = new HistoryList();
        dieAmount = dieList.size();
        rolled = false;
        frame = new JFrame("Dice Roller Application");
        diceRollInput = new JTextField[dieAmount];
        settingsPanel = new JPanel(new GridLayout(dieAmount + 1,2));
        outputPanel = new JPanel(new GridLayout());
        buttonPanel = new JPanel();
        backgroundPanel = new JPanel();
        dieLabels = new JLabel[dieAmount];
        column1 = new JLabel("Dice Type:", SwingConstants.CENTER);
        column2 = new JLabel("Amount of Dice to Roll:", SwingConstants.CENTER);
        rollOutputTextPane = new JTextArea("This is where the output will go!");
        rollOutputScrollPane = new JScrollPane(rollOutputTextPane);
        rollButton = new JButton("Roll!");
        lastButton = new JButton("\u21D0");
        nextButton = new JButton("\u21D2");
        diceLabelFont = new Font("Arial", Font.BOLD, 40);

        frame.add(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        backgroundPanel.add(settingsPanel, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        backgroundPanel.add(outputPanel, BorderLayout.SOUTH);

        for (int i = 0; i < dieAmount; i++) {
            diceRollInput[i] = new JTextField(5);
        }

        // Creates the settingsPanel section of the app.
        settingsPanel.add(column1);
        settingsPanel.add(column2);
        for (int i = 0; i < dieAmount; i++) {
            dieLabels[i] = new JLabel("", SwingConstants.CENTER);
            dieLabels[i].setFont(diceLabelFont);
            settingsPanel.add(dieLabels[i]);
            settingsPanel.add(diceRollInput[i]);
        } 

        dieLabels[0].setText("D4 ");
        dieLabels[1].setText("D6 ");
        dieLabels[2].setText("D8 ");
        dieLabels[3].setText("D10 ");
        dieLabels[4].setText("D% ");
        dieLabels[5].setText("D12 ");
        dieLabels[6].setText("D20 ");

        rollOutputTextPane.setEditable(false);
        
        // Creates the scroll bar for the ScrollPane.
        rollOutputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rollOutputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        outputPanel.add(rollOutputScrollPane);
        buttonPanel.add(rollButton);
        
        rollButton.addActionListener(this);
        nextButton.addActionListener(this);
        lastButton.addActionListener(this);

        // All this is to make the Enter button hit rollButton.
        InputMap inputMap = rollButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rollButton.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "rollDice");
        actionMap.put("rollDice", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollButton.doClick();
            }
        });

        Font arrowFont = new Font(nextButton.getFont().getName(), Font.PLAIN, 100);
        nextButton.setFont(arrowFont);
        lastButton.setFont(arrowFont);
        
        rollOutputScrollPane.setPreferredSize(new Dimension(500, 280));
        rollButton.setPreferredSize(new Dimension(400, 70));
        frame.setSize(new Dimension(500, 800));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Rolls the die and displays the outputs in the JTextPane rollOutput
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollButton) {
            // StringBuilder is apparently better for loops since it doesn't create new String objects. Wow!
            StringBuilder output = new StringBuilder();
            int fieldText;
            // This for loop fills the output with the results of each dice roll.
            for (int i = 0; i < dieAmount; i++) {
                try {
                    fieldText = (diceRollInput[i].getText().isEmpty()) ? 0 : Integer.parseInt(diceRollInput[i].getText());
                    if (fieldText > 1000 || fieldText < 0) {
                        throw new Exception();
                    }
                    output.append(Dice.roll(dieList.get(i).getFaces(), fieldText, dieList.get(i).isPercent()));
                } catch (Exception exception) {
                    rollOutputTextPane.setText("Invalid inputs!\nUse better numbers.");
                    return;
                }
            }
            if (output.isEmpty()) {
                output.append("You rolled nothing!");
            }
            historyList.addNewHistory(output.toString());
            if (!rolled) {
                outputPanel.add(lastButton);
                outputPanel.add(nextButton);
                rolled = true;
                frame.repaint();
                frame.revalidate();
            }
        }
        if (e.getSource() == lastButton) {
            historyList.selectLast();
        }
        if (e.getSource() == nextButton) {
            historyList.selectNext();
        }
        rollOutputTextPane.setText(historyList.getSelector());
        for (JTextField field : diceRollInput) {
            field.setText("");
        }
    }
}