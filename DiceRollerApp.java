import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DiceRollerApp extends JFrame implements ActionListener {

    private int dieAmount = Main.getList().size();
    private JFrame frame;
    private JPanel settingsPanel, outputPanel, buttonPanel, backgroundPanel;
    private JLabel[] dieLabels;
    private JTextArea rollOutput;
    private JScrollPane rollOutputScrollPane;
    private JTextField[] diceRollInput;
    private JButton rollButton;
    private Font diceLabelFont = new Font("Arial", Font.BOLD, 40);
    
    public DiceRollerApp() {
        frame = new JFrame("Dice Roller Application");
        diceRollInput = new JTextField[dieAmount];
        dieLabels = new JLabel[dieAmount];
        rollOutput = new JTextArea("This is where the output will go!");
        rollOutputScrollPane = new JScrollPane(rollOutput);
        backgroundPanel = new JPanel();
        outputPanel = new JPanel(new GridLayout());
        settingsPanel = new JPanel(new GridLayout(dieAmount,2));
        buttonPanel = new JPanel();
        rollButton = new JButton("Roll!");

        // frame.setLayout(new BorderLayout());
        frame.add(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        backgroundPanel.add(settingsPanel, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        backgroundPanel.add(outputPanel, BorderLayout.SOUTH);

        for (int i = 0; i < dieAmount; i++) {
            diceRollInput[i] = new JTextField(5);
        }

        for (int i = 0; i < dieAmount; i++) {
            dieLabels[i] = new JLabel();
            dieLabels[i].setFont(diceLabelFont);
            settingsPanel.add(dieLabels[i]);
            settingsPanel.add(diceRollInput[i]);
        }

        dieLabels[0].setText("D4");
        dieLabels[1].setText("D6");
        dieLabels[2].setText("D8");
        dieLabels[3].setText("D10");
        dieLabels[4].setText("D10 %");
        dieLabels[5].setText("D12");
        dieLabels[6].setText("D20");

        rollOutput.setEditable(false);
        rollButton.setPreferredSize(new Dimension(400, 98));

        rollOutputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rollOutputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rollOutputScrollPane.setPreferredSize(new Dimension(500, 280));

        outputPanel.add(rollOutputScrollPane);
        buttonPanel.add(rollButton);

        rollButton.addActionListener(this);

        frame.setSize(new Dimension(500, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Rolls the die and regales the outputs in the rollOutput JTextField
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollButton) {
            String output = "";
            int fieldText;
            for (int i = 0; i < 7; i++) {
                try {
                    fieldText = Integer.parseInt(diceRollInput[i].getText());
                    if (fieldText > 1000 || fieldText < 0) {
                        rollOutput.setText("Invalid inputs! Use better numbers.");
                        return;
                    }
                    output += Main.getList().get(i).roll(fieldText);
                } catch (Exception exception) {
                    rollOutput.setText("Invalid inputs! Use better numbers.");
                    return;
                }
            }
            rollOutput.setText(output);
        }
    }
}