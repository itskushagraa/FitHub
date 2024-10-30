package ui.graphical;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import model.UserProfile;

public class ConfigureUser extends JFrame {
    private JLayeredPane layeredPane;
    private JTextField nameTextField;
    private JTextField heightTextField;
    private JTextField weightTextField;
    private JTextField ageTextField;
    private JSlider intensitySlider;
    private JRadioButton bulkButton;
    private JRadioButton cutButton;
    private JRadioButton maintainButton;
    private JButton continueButton;

    public ConfigureUser() {
        run();
    }

    private void run() {
        ImageIcon splashIcon = new ImageIcon("./assets/configureuser.png");
        JLabel splashLabel = new JLabel(splashIcon);

        this.setTitle("FitHub");
        this.setSize(splashIcon.getIconWidth(), splashIcon.getIconHeight());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);

        layeredPane = new JLayeredPane();
        layeredPane.setSize(this.getSize());
        splashLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        layeredPane.add(splashLabel, JLayeredPane.DEFAULT_LAYER);

        configureComponents();

        this.setContentPane(layeredPane);
        this.setVisible(true);
    }

    private void configureComponents() {
        initNameField();
        initHeightField();
        initWeightField();
        initAgeField();
        initSlider();
        initRadioButtonGroup();
        initContinueButton();
    }

    private void initNameField() {
        nameTextField = new JTextField();
        constrainTextFieldToString(nameTextField);
        nameTextField.setBounds(270, 119, 354, 45);

        nameTextField.setOpaque(true);
        nameTextField.setForeground(new Color(255, 255, 255));
        nameTextField.setBackground(new Color(16, 14, 35));
        nameTextField.setFont(new Font("Dialog", Font.PLAIN, 28));
        nameTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 160, 250)));

        layeredPane.add(nameTextField, JLayeredPane.PALETTE_LAYER);
    }

    private void initHeightField() {
        heightTextField = new JTextField();
        constrainTextFieldToDouble(heightTextField);
        heightTextField.setBounds(300, 172, 85, 45);

        heightTextField.setOpaque(true);
        heightTextField.setForeground(new Color(255, 255, 255));
        heightTextField.setBackground(new Color(16, 14, 35));
        heightTextField.setFont(new Font("Dialog", Font.PLAIN, 28));
        heightTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 160, 250)));

        layeredPane.add(heightTextField, JLayeredPane.PALETTE_LAYER);
    }

    private void initWeightField() {
        weightTextField = new JTextField();
        constrainTextFieldToDouble(weightTextField);
        weightTextField.setBounds(310, 225, 85, 45);

        weightTextField.setOpaque(true);
        weightTextField.setForeground(new Color(255, 255, 255));
        weightTextField.setBackground(new Color(16, 14, 35));
        weightTextField.setFont(new Font("Dialog", Font.PLAIN, 28));
        weightTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 160, 250)));

        layeredPane.add(weightTextField, JLayeredPane.PALETTE_LAYER);
    }

    private void initAgeField() {
        ageTextField = new JTextField();
        constrainTextFieldToInt(ageTextField);
        ageTextField.setBounds(245, 278, 40, 45);

        ageTextField.setOpaque(true);
        ageTextField.setForeground(new Color(255, 255, 255));
        ageTextField.setBackground(new Color(16, 14, 35));
        ageTextField.setFont(new Font("Dialog", Font.PLAIN, 28));
        ageTextField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 160, 250)));

        layeredPane.add(ageTextField, JLayeredPane.PALETTE_LAYER);
    }

    private void initSlider() {
        intensitySlider = new JSlider(JSlider.HORIZONTAL, 1, 7, 1);
        intensitySlider.setMajorTickSpacing(1);
        intensitySlider.setPaintTicks(true);
        intensitySlider.setSnapToTicks(true);
        intensitySlider.setPaintLabels(true);

        Font labelFont = new Font("Dialog", Font.BOLD, 14);
        Color labelColor = new Color(255, 255, 255);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        for (int i = 1; i <= 7; i++) {
            JLabel label = new JLabel(String.valueOf(i));
            label.setFont(labelFont);
            label.setForeground(labelColor);
            labelTable.put(i, label);
        }

        intensitySlider.setForeground(new Color(200, 160, 250));
        intensitySlider.setLabelTable(labelTable);
        intensitySlider.setBounds(50, 380, 400, 50);
        layeredPane.add(intensitySlider, JLayeredPane.PALETTE_LAYER);
    }

    private void initRadioButtonGroup() {
        bulkButton = new JRadioButton();
        cutButton = new JRadioButton();
        maintainButton = new JRadioButton();
        ButtonGroup goalGroup = new ButtonGroup();
        goalGroup.add(bulkButton);
        goalGroup.add(cutButton);
        goalGroup.add(maintainButton);
        bulkButton.setBounds(43, 480, 150, 60);
        cutButton.setBounds(200, 480, 150, 60);
        maintainButton.setBounds(357, 480, 250, 60);
        layeredPane.add(bulkButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(cutButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(maintainButton, JLayeredPane.PALETTE_LAYER);
    }

    private void initContinueButton() {
        continueButton = new JButton();
        continueButton.setBounds(730, 440, 160, 150);
        continueButton.setContentAreaFilled(false);
        continueButton.setBorderPainted(false);
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(e -> processInputs());
        layeredPane.add(continueButton, JLayeredPane.PALETTE_LAYER);
    }

    private void processInputs() {
        if (!checkInputs()) {
            JOptionPane.showMessageDialog(this, "One or more fields is empty", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            createUser();
        }
    }

    private void createUser() {
        String name = nameTextField.getText().trim();
        double height = Double.parseDouble(heightTextField.getText().trim());
        double weight = Double.parseDouble(weightTextField.getText().trim());
        int age = Integer.parseInt(ageTextField.getText().trim());
        int intensity = intensitySlider.getValue();
        String goal;
        if (bulkButton.isSelected()) {
            goal = "bulk";
        } else if (cutButton.isSelected()) {
            goal = "cut";
        } else {
            goal = "maintain";
        }

        try {
            UserProfile mainUser = new UserProfile(name, height, weight, age, intensity, goal);
            this.dispose();
            new MainMenu(mainUser);
        } catch (IOException e) {
            System.out.println("Unexpected IOException");
        }
    }

    private boolean checkInputs() {
        if (nameTextField.getText().trim().isEmpty()
                || heightTextField.getText().trim().isEmpty()
                || weightTextField.getText().trim().isEmpty()
                || ageTextField.getText().trim().isEmpty()) {
            return false;
        }

        if (!bulkButton.isSelected() && !cutButton.isSelected() && !maintainButton.isSelected()) {
            return false;
        }

        return true;
    }

    private void constrainTextFieldToInt(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + string + currentText.substring(offset);

                if (string.matches("\\d+") && checkAge(newText)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

                if (text.matches("\\d*") && checkAge(newText)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    private void constrainTextFieldToDouble(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + string + currentText.substring(offset);

                if (isValidDecimalInRange(newText, 1.0, 249.9)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

                if (isValidDecimalInRange(newText, 1.0, 249.9)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    private void constrainTextFieldToString(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + string + currentText.substring(offset);

                if (isAlphabetic(newText)) { // Check if input is alphabetic
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

                if (isAlphabetic(newText)) { // Check if input is alphabetic
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    // HELPER
    private boolean isAlphabetic(String text) {
        return text.matches("[a-zA-Z\\s]*") && text.length() <= 20;
    }

    // HELPER
    private boolean checkAge(String text) {
        try {
            int age = Integer.parseInt(text);
            return age >= 1 && age <= 99;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // HELPER
    private boolean isValidDecimalInRange(String text, double min, double max) {
        if (text.matches("\\d{1,3}(\\.\\d?)?")) {
            try {
                double value = Double.parseDouble(text);
                return value >= min && value <= max;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
