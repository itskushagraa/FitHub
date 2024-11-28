package ui.graphical;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

import model.UserProfile;

public class UserSettings extends JFrame {
    private UserProfile mainUser;
    private JLayeredPane layeredPane;
    private JLabel nameValueLabel;
    private JLabel heightValueLabel;
    private JLabel weightValueLabel;
    private JLabel bmiValueLabel;
    private JLabel ageValueLabel;
    private JLabel calorieValueLabel;
    private JLabel intensityValueLabel;
    private JLabel goalValueLabel;
    private JButton backButton;
    private JButton editButton;

    // EFFECTS: runs the user settings screen with the given user
    public UserSettings(UserProfile user) {
        this.mainUser = user;
        run();
    }

    /* 
     * MODIFIES: this
     * EFFECTS: creates the user profile screen and all elements in it
     */
    private void run() {
        ImageIcon splashIcon = new ImageIcon("./assets/viewprofile.png");
        JLabel splashLabel = new JLabel(splashIcon);
        this.setTitle("FitHub");
        this.setSize(splashIcon.getIconWidth(), splashIcon.getIconHeight());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);

        layeredPane = new JLayeredPane();
        layeredPane.setSize(this.getSize());
        splashLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        layeredPane.add(splashLabel, JLayeredPane.DEFAULT_LAYER);

        configureScreen();

        this.setContentPane(layeredPane);
        this.setVisible(true);
        mainUser.userProfileViewed();
    }

    // EFFECTS: initialies all elements on the user settings screen
    private void configureScreen() {
        initNameLabel();
        initHeightLabel();
        initWeightLabel();
        initBmiLabel();
        initAgeLabel();
        initCalorieLabel();
        initIntensityLabel();
        initGoalLabel();
        initBackButton();
        initEditButton();
    }

    // EFFECTS: creates a label to show user name
    private void initNameLabel() {
        nameValueLabel = new JLabel(mainUser.getName());
        nameValueLabel.setFont(new Font("Dialog", Font.PLAIN, 28));
        nameValueLabel.setForeground(Color.WHITE);
        nameValueLabel.setBounds(155, 126, 400, 30);
        layeredPane.add(nameValueLabel, JLayeredPane.PALETTE_LAYER);
    }

    // EFFECTS: creates a label to show user height
    private void initHeightLabel() {
        heightValueLabel = new JLabel(mainUser.getHeight() + " cm");
        heightValueLabel.setFont(new Font("Dialog", Font.PLAIN, 28));
        heightValueLabel.setForeground(Color.WHITE);
        heightValueLabel.setBounds(180, 181, 400, 30);
        layeredPane.add(heightValueLabel, JLayeredPane.PALETTE_LAYER);
    }
    
    // EFFECTS: creates a label to show user weight
    private void initWeightLabel() {
        weightValueLabel = new JLabel(mainUser.getWeight() + " kg");
        weightValueLabel.setFont(new Font("Dialog", Font.PLAIN, 28));
        weightValueLabel.setForeground(Color.WHITE);
        weightValueLabel.setBounds(190, 235, 400, 30);
        layeredPane.add(weightValueLabel, JLayeredPane.PALETTE_LAYER);
    }

    // EFFECTS: creates a label to show user name bmi
    private void initBmiLabel() {
        bmiValueLabel = new JLabel(mainUser.getBmi() + "");
        bmiValueLabel.setFont(new Font("Dialog", Font.PLAIN, 28));
        bmiValueLabel.setForeground(Color.WHITE);
        bmiValueLabel.setBounds(118, 289, 400, 30);
        layeredPane.add(bmiValueLabel, JLayeredPane.PALETTE_LAYER);
    }

    // EFFECTS: creates a label to show user age
    private void initAgeLabel() {
        ageValueLabel = new JLabel(mainUser.getAge() + " years");
        ageValueLabel.setFont(new Font("Dialog", Font.PLAIN, 28));
        ageValueLabel.setForeground(Color.WHITE);
        ageValueLabel.setBounds(125, 343, 400, 30);
        layeredPane.add(ageValueLabel, JLayeredPane.PALETTE_LAYER);
    }

    // EFFECTS: creates a label to show user's daily calorie intake
    private void initCalorieLabel() {
        calorieValueLabel = new JLabel(mainUser.getTargetCalories() + " kcals");
        calorieValueLabel.setFont(new Font("Dialog", Font.PLAIN, 28));
        calorieValueLabel.setForeground(Color.WHITE);
        calorieValueLabel.setBounds(322, 396, 400, 30);
        layeredPane.add(calorieValueLabel, JLayeredPane.PALETTE_LAYER);
    }

    // EFFECTS: creates a label to show user's weekly workout intensity
    private void initIntensityLabel() {
        intensityValueLabel = new JLabel(mainUser.getIntensity() + " days/week");
        intensityValueLabel.setFont(new Font("Dialog", Font.PLAIN, 28));
        intensityValueLabel.setForeground(Color.WHITE);
        intensityValueLabel.setBounds(402, 450, 400, 30);
        layeredPane.add(intensityValueLabel, JLayeredPane.PALETTE_LAYER);
    }

    // EFFECTS: creates a label to show user's fitness goal
    private void initGoalLabel() {
        goalValueLabel = new JLabel(mainUser.getGoal().substring(0, 1).toUpperCase() + mainUser.getGoal().substring(1));
        goalValueLabel.setFont(new Font("Dialog", Font.PLAIN, 28));
        goalValueLabel.setForeground(Color.WHITE);
        goalValueLabel.setBounds(289, 504, 400, 30);
        layeredPane.add(goalValueLabel, JLayeredPane.PALETTE_LAYER);
    }

    // EFFECTS: creates a back button
    private void initBackButton() {
        backButton = new JButton();
        backButton.setBounds(730, 425, 155, 155);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        layeredPane.add(backButton, JLayeredPane.PALETTE_LAYER);
        backButton.addActionListener(e -> this.dispose());
    }

    // EFFECTS: creates an edit button
    private void initEditButton() {
        editButton = new JButton();
        editButton.setBounds(730, 375, 155, 45);
        editButton.setContentAreaFilled(false);
        editButton.setBorderPainted(false);
        editButton.setFocusPainted(false);
        editButton.setOpaque(false);
        layeredPane.add(editButton, JLayeredPane.PALETTE_LAYER);
        editButton.addActionListener(e -> {
            new ConfigureUser(mainUser);
            this.dispose();
        });
    }
}
