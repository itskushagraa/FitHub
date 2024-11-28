package ui.graphical;

import java.io.IOException;

import javax.swing.*;

import model.EventLog;
import model.Event;
import model.UserProfile;

public class MainMenu extends JFrame {
    private JLayeredPane layeredPane;
    private JButton workoutSplit;
    private JButton dietPlan;
    private JButton userProfile;
    private JButton save;
    private JButton load;
    private JButton exit;
    private UserProfile mainUser;

    // EFFECTS: runs the main menu and sets the main UserProfile as the one that was
    // created during startup
    public MainMenu(UserProfile user) {
        this.mainUser = user;
        run();
    }

    /*
     * MOFIFIES: this
     * EFFECTS: runs the main menu, and creates all elements of the main menu
     */
    private void run() {
        ImageIcon splashIcon = new ImageIcon("./assets/mainmenu.png");
        JLabel splashLabel = new JLabel(splashIcon);
        registerShutdownHook();
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

        configureButtons();

        this.setContentPane(layeredPane);
        this.setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS: ensures that EventLog is printed when the application is quit
     */
    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            printLog(EventLog.getInstance());
        }));
    }

    // EFFECTS: prints the EventLog
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next + "\n");
        }
    }

    // EFFECTS: initializes the menu buttons
    private void configureButtons() {
        initWorkoutSplitButton();
        initDietPlanButton();
        initUserProfileButton();
        initSaveButton();
        initLoadButton();
        initExitButton();
    }

    // EFFECTS: creates the button responsible for running the FitHub Workout
    // Tracker
    private void initWorkoutSplitButton() {
        workoutSplit = new JButton();
        workoutSplit.setBounds(25, 105, 700, 75);
        layeredPane.add(workoutSplit, JLayeredPane.PALETTE_LAYER);
        makeButtonInvisible(workoutSplit);
        workoutSplit.addActionListener(e -> {
            new WorkoutTracker(mainUser);
        });
    }

    // EFFECTS: creates the button responsible for running the FitHub Diet Planner
    private void initDietPlanButton() {
        dietPlan = new JButton();
        dietPlan.setBounds(25, 180, 700, 75);
        layeredPane.add(dietPlan, JLayeredPane.PALETTE_LAYER);
        makeButtonInvisible(dietPlan);
        dietPlan.addActionListener(e -> {
            new DietPlanner(mainUser);
        });
    }

    // EFFECTS: creates the button responsible for running User Config in edit mode
    private void initUserProfileButton() {
        userProfile = new JButton();
        userProfile.setBounds(25, 255, 700, 75);
        layeredPane.add(userProfile, JLayeredPane.PALETTE_LAYER);
        makeButtonInvisible(userProfile);
        userProfile.addActionListener(e -> {
            new UserSettings(mainUser);
        });
    }

    // EFFECTS: creates the button responsible for saving changes to file
    private void initSaveButton() {
        save = new JButton();
        save.setBounds(25, 330, 700, 75);
        layeredPane.add(save, JLayeredPane.PALETTE_LAYER);
        makeButtonInvisible(save);
        save.addActionListener(e -> {
            try {
                mainUser.save("./data/User Data/userProfile.json",
                        "./data/User Data/workoutSplit.json",
                        "./data/User Data/dietPlan.json");
                JOptionPane.showMessageDialog(this, "Save Successful!", "Save Data", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                System.out.println("Unexpected IOException");
            }
        });
    }

    // EFFECTS: creates the button responsible for loading from file
    private void initLoadButton() {
        load = new JButton();
        load.setBounds(25, 406, 700, 75);
        layeredPane.add(load, JLayeredPane.PALETTE_LAYER);
        makeButtonInvisible(load);
        load.addActionListener(e -> {
            try {
                mainUser.load("./data/User Data/userProfile.json",
                        "./data/User Data/workoutSplit.json",
                        "./data/User Data/dietPlan.json");
                JOptionPane.showMessageDialog(this, "Load Successful!", "Load Data", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                System.out.println("Unexpected IOException");
            }
        });
    }

    // EFFECTS: creates the button responsible for exiting the app
    private void initExitButton() {
        exit = new JButton();
        exit.setBounds(25, 480, 700, 75);
        layeredPane.add(exit, JLayeredPane.PALETTE_LAYER);
        makeButtonInvisible(exit);
        exit.addActionListener(e -> exitAppAction());
    }

    // EFFECTS: prints the EventLog and quits the app
    private void exitAppAction() {
        System.exit(0);
    }

    /*
     * MODIFIES: button
     * EFFECTS: makes button invisible
     */
    private void makeButtonInvisible(JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }
}