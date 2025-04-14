import javax.swing.*;
import java.awt.*;

public class MyGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyGUI().createAndShowGUI());
    }
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Benutzerformular");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField();
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);

        JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(18, 0, 120, 1));
        formPanel.add(new JLabel("Alter:"));
        formPanel.add(ageSpinner);

        formPanel.add(new JLabel("Geschlecht:"));
        JRadioButton male = new JRadioButton("Männlich");
        JRadioButton female = new JRadioButton("Weiblich");
        JRadioButton other = new JRadioButton("Andere");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);
        formPanel.add(male);
        formPanel.add(female);
        formPanel.add(other);

        formPanel.add(new JLabel("Lieblings-Genre:"));
        String[] genres = {"Fantasy", "Sci-Fi", "Krimi", "Romanze", "Sachbuch"};
        JComboBox<String> genreBox = new JComboBox<>(genres);
        formPanel.add(genreBox);

        formPanel.add(new JLabel("Hobbys:"));
        JCheckBox hobby1 = new JCheckBox("Lesen");
        JCheckBox hobby2 = new JCheckBox("Sport");
        JCheckBox hobby3 = new JCheckBox("Reisen");
        JCheckBox hobby4 = new JCheckBox("Spiele");
        formPanel.add(hobby1);
        formPanel.add(hobby2);
        formPanel.add(hobby3);
        formPanel.add(hobby4);

        formPanel.add(new JLabel("Kommentare:"));
        JTextArea commentArea = new JTextArea(4, 30);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        formPanel.add(new JScrollPane(commentArea));

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        formPanel.add(progressBar);

        JButton submitButton = new JButton("Abschicken");

        submitButton.addActionListener(e -> {
            progressBar.setValue(0);
            Timer timer = new Timer(20, null);
            timer.addActionListener(ev -> {
                int value = progressBar.getValue();
                if (value < 100) {
                    progressBar.setValue(value + 2);
                } else {
                    timer.stop();

                    String name = nameField.getText();
                    int alter = (int) ageSpinner.getValue();
                    String geschlecht = male.isSelected() ? "Männlich" :
                            female.isSelected() ? "Weiblich" :
                                    other.isSelected() ? "Andere" : "Nicht angegeben";
                    String genre = (String) genreBox.getSelectedItem();

                    StringBuilder hobbies = new StringBuilder();
                    if (hobby1.isSelected()) hobbies.append("Lesen, ");
                    if (hobby2.isSelected()) hobbies.append("Sport, ");
                    if (hobby3.isSelected()) hobbies.append("Reisen, ");
                    if (hobby4.isSelected()) hobbies.append("Spiele, ");
                    if (hobbies.length() > 0) hobbies.setLength(hobbies.length() - 2);

                    String kommentare = commentArea.getText().trim();

                    JTextArea resultArea = new JTextArea(10, 30);
                    resultArea.setEditable(false);
                    resultArea.setText("🎉 Ergebnis:\n\n");
                    resultArea.append("Name: " + name + "\n");
                    resultArea.append("Alter: " + alter + "\n");
                    resultArea.append("Geschlecht: " + geschlecht + "\n");
                    resultArea.append("Lieblings-Genre: " + genre + "\n");
                    resultArea.append("Hobbys: " + (hobbies.length() > 0 ? hobbies : "Keine ausgewählt") + "\n");
                    resultArea.append("Kommentare: " + (kommentare.isEmpty() ? "Keine" : kommentare));

                    JScrollPane scrollPane = new JScrollPane(resultArea);
                    JOptionPane.showMessageDialog(frame, scrollPane, "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            timer.start();
        });

        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(submitButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}