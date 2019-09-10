import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ScrabbleGUI implements ActionListener {
    private final Dictionnary dico;
    private JTextField letterTextField = new JTextField(20);
    private JButton searchButton = new JButton("Search");
    private JTextArea wordListTextArea = new JTextArea(1, 1);

    public ScrabbleGUI(final Dictionnary dico) {
        this.dico = dico;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame();
        frame.setTitle("Scrabble GUI");
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocation(screenSize.width/2 - frame.getSize().width/2,
                          screenSize.height/2 - frame.getSize().height/2);
        JPanel p = new JPanel();
        p.add(this.letterTextField);
        p.add(this.searchButton);
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(p, BorderLayout.NORTH);
        this.wordListTextArea.setEditable(false);
        frame.add(new JScrollPane(this.wordListTextArea), BorderLayout.CENTER);
        this.searchButton.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            new ScrabbleGUI(new Dictionnary("fr_FR_utf8.dico"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.wordListTextArea.setText(null);
        if(e.getSource() == this.searchButton) {
            char[] letters = letterTextField.getText().toCharArray();
            List<String> matchingList = this.dico.getWordsThatCanBeComposed(letters);
            matchingList.sort(new ScrabbleComparator(letters));
            this.wordListTextArea.append(matchingList.size() + " word(s) found :\n");
            for(String s : matchingList) {
                char[] composition = Dictionnary.getComposition(s, letters);
                if (composition != null) {
                    this.wordListTextArea
                            .append("- " + s +
                                    "(" + Arrays.toString(composition) + " " +
                                    ScrabbleComparator.lettersValue(composition) + ")" + "\n");
                }

            }
        }
    }
}
