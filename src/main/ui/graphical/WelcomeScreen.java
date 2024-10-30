package ui.graphical;

import model.UserProfile;

import java.io.IOException;

import javax.swing.*;

/*
 * Represents State Machine Component: AppState.WELCOME_SCREEN
 * Displays a welcome message with new profile / load profile options
 */

public class WelcomeScreen extends JFrame {
    JLayeredPane layeredPane = new JLayeredPane();
    JButton create;
    JButton load;
    JButton exit;

    public WelcomeScreen() {
        run();
    }

    public void run() {
        ImageIcon splashIcon = new ImageIcon("./assets/welcomescreen.png");
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

        addButtons();

        this.setContentPane(layeredPane);
        this.setVisible(true);
    }

    private void addButtons() {
        create = new JButton();
        load = new JButton();
        exit = new JButton();

        create.setBounds(240, 234, 240, 42);
        load.setBounds(240, 274, 240, 42);
        exit.setBounds(240, 314, 240, 42);

        // Add buttons to the layered pane at a higher layer than the splash image
        layeredPane.add(create, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(load, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(exit, JLayeredPane.PALETTE_LAYER);

        makeButtonInvisible(create);
        makeButtonInvisible(load);
        makeButtonInvisible(exit);

        configureButtons();
    }

    private void configureButtons() {
        create.addActionListener(e -> new ConfigureUser());
        load.addActionListener(e -> loadAction());
        exit.addActionListener(e -> System.exit(0));
    }

    private void loadAction() {
        try {
            UserProfile mainUser = new UserProfile("", 0.0, 0.0, 0, 0, "");
            mainUser.load("./data/User Data/userProfile.json",
                    "./data/User Data/workoutSplit.json",
                    "./data/User Data/dietPlan.json");

            this.dispose();
            new MainMenu(mainUser);
        } catch (IOException e) {
            System.out.println("Unexpected IOException");
        }
    }

    private void makeButtonInvisible(JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }
}
