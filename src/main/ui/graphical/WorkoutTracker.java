package ui.graphical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import model.Exercise;
import model.ExerciseSet;
import model.UserProfile;
import model.Workout;

public class WorkoutTracker extends JFrame {
    private static final Color PINK = new Color(196, 132, 241);
    private static final Color BG = new Color(16, 14, 40);
    private static final Color FADED_BG = new Color(34, 30, 65);

    private JLayeredPane layeredPane;
    private JPanel workoutsPanel;
    private JPanel overlay;
    private JPanel statsPanel;

    private JLabel absLabel;
    private JLabel bicepsLabel;
    private JLabel chestLabel;
    private JLabel forearmsLabel;
    private JLabel frontDeltsLabel;
    private JLabel quadsLabel;
    private JLabel sideDeltsLabel;
    private JLabel calvesLabelFront;

    private JLabel forearmLabel;
    private JLabel glutesLabel;
    private JLabel hamstringsLabel;
    private JLabel latsLabel;
    private JLabel lowerBackLabel;
    private JLabel upperBackLabel;
    private JLabel rearDeltLabel;
    private JLabel tricepLabel;
    private JLabel calvesLabelBack;

    private UserProfile mainUser;
    private List<Workout> workoutSplit;

    public WorkoutTracker(UserProfile user) {
        this.mainUser = user;
        this.workoutSplit = mainUser.getWorkoutList();
        run();
    }

    private void run() {
        drawBackground();
        drawLogo("./assets/logo.png", 1150, 30, 100, 64);
        drawButtonHolderTab(0, 0, 100, this.getHeight());
        drawHeaderLabel("W O R K O U T  T R A C K E R", 10, 30, 740, 90, new Font("SansSerif", Font.BOLD, 48));
        drawButtons();
        drawWorkoutsPanel();
        drawStatsPanel();
        setLabelClientProperty();
    }

    private void drawBackground() {
        this.setTitle("FitHub");
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);

        layeredPane = new JLayeredPane();
        layeredPane.setBackground(new Color(16, 14, 40));
        layeredPane.setOpaque(true);

        this.setContentPane(layeredPane);
        this.setVisible(true);
    }

    private void drawLogo(String imagePath, int x, int y, int width, int height) {
        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon(imagePath);
        Image originalImage = icon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(scaledImage));
        logo.setBounds(x, y, width, height);
        logo.setVisible(true);
        layeredPane.add(logo, Integer.valueOf(1));
    }

    private void drawButtonHolderTab(int x, int y, int width, int height) {
        JPanel sideTab = new JPanel();
        sideTab.setBounds(x, y, width, height);
        sideTab.setBackground(new Color(34, 30, 65));
        sideTab.setOpaque(true);

        layeredPane.add(sideTab, Integer.valueOf(0));
    }

    private void drawHeaderLabel(String text, int x, int y, int width, int height, Font font) {
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(34, 30, 65));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

                g2d.setFont(font);
                g2d.setColor(Color.WHITE);

                FontMetrics metrics = g2d.getFontMetrics(font);
                int textX = (getWidth() - metrics.stringWidth(text)) / 2;
                int textY = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2d.drawString(text, textX, textY);
            }
        };

        headerPanel.setBounds(x, y, width, height);
        headerPanel.setOpaque(false);
        layeredPane.add(headerPanel, Integer.valueOf(1));
    }

    private void drawButtons() {
        drawInteractiveButton("./assets/icons/workouticon.png", 20, 145, 60, 60,
                "View Workouts", 5, 115, new Font("SansSerif", Font.BOLD, 12), e -> workoutsButtonAction());
        drawInteractiveButton("./assets/icons/dpstatsicon.png", 20, 235, 60, 60,
                "View Stats", 16, 295, new Font("SansSerif", Font.BOLD, 12), e -> statsButtonAction());
        drawInteractiveButton("./assets/icons/backicon.png", 32, 647, 40, 40,
                "Back", 35, 620, new Font("SansSerif", Font.BOLD, 12), e -> backButtonAction());
    }

    private void drawInteractiveButton(String imagePath, int x, int y, int width, int height, String hoverMessage,
            int messageX, int messageY, Font messageFont, ActionListener action) {
        JButton button = new JButton();
        setButtonVisibilty(button, x, y, width, height);

        JLabel hoverLabel = new JLabel(hoverMessage);
        setHoverVisibility(hoverLabel, messageX, messageY, messageFont);

        ImageIcon icon = new ImageIcon(imagePath);
        Image originalImage = icon.getImage();
        Image scaledImage = originalImage.getScaledInstance((int) (width * 0.8), (int) (height * 0.8),
                Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));

        Image hoverImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon hoverIcon = new ImageIcon(hoverImage);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setIcon(hoverIcon);
                hoverLabel.setVisible(true);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setIcon(new ImageIcon(scaledImage));
                hoverLabel.setVisible(false);
            }
        });

        button.addActionListener(action);
        layeredPane.add(button, Integer.valueOf(1));
    }

    private void drawWorkoutsPanel() {
        workoutsPanel = new JPanel();
        workoutsPanel.setLayout(null);
        workoutsPanel.setBounds(120, 140, 1140, 560);
        workoutsPanel.setOpaque(false);
        workoutsPanel.setVisible(true);

        drawWorkoutButton(workoutSplit.get(0), 0);
        drawWorkoutButton(workoutSplit.get(1), 380);
        drawWorkoutButton(workoutSplit.get(2), 760);

        drawScrollPane(workoutSplit.get(0), 0, 50, 380, 510);
        drawScrollPane(workoutSplit.get(1), 380, 50, 380, 510);
        drawScrollPane(workoutSplit.get(2), 760, 50, 380, 510);

        layeredPane.add(workoutsPanel, Integer.valueOf(1));
    }

    private void drawScrollPane(Workout workout, int x, int y, int width, int height) {
        JPanel exercisesContainer = new JPanel();
        exercisesContainer.setLayout(new BoxLayout(exercisesContainer, BoxLayout.Y_AXIS));
        exercisesContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        exercisesContainer.setBackground(BG);

        for (Exercise exercise : workout.getExercises()) {
            JPanel exercisePanel = new JPanel();
            exercisePanel.setLayout(null);
            exercisePanel.setPreferredSize(new Dimension(width, 110));
            exercisePanel.setMaximumSize(new Dimension(width, 110));
            exercisePanel.setMinimumSize(new Dimension(width, 110));
            drawExercise(exercisePanel, exercise, exercisesContainer, workout);
            exercisesContainer.add(exercisePanel);
        }

        drawAddExerciseButton(width, 50, exercisesContainer, workout);
        exercisesContainer.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(exercisesContainer);
        setScrollbarColor(scrollPane);
        scrollPane.setBounds(x, y, width, height);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        workoutsPanel.add(scrollPane);
    }

    private void drawAddExerciseButton(int width, int height, JPanel exercisesContainer, Workout workout) {
        JButton addExerciseButton = new JButton("+ Add Exercise");
        addExerciseButton.setPreferredSize(new Dimension(width, height));
        addExerciseButton.setMaximumSize(new Dimension(width, height));
        addExerciseButton.setMinimumSize(new Dimension(width, height));
        setAddExerciseButtonProperties(addExerciseButton);

        addExerciseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addExerciseButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
                addExerciseButton.setBackground(FADED_BG);
                addExerciseButton.setForeground(PINK); // Assuming PINK is defined somewhere in your code
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addExerciseButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
                addExerciseButton.setBackground(BG);
                addExerciseButton.setForeground(Color.WHITE);
            }
        });

        addExerciseButton.addActionListener(e -> addExerciseAction(workout));

        exercisesContainer.add(addExerciseButton);
    }

    private void setAddExerciseButtonProperties(JButton addExerciseButton) {
        addExerciseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addExerciseButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        addExerciseButton.setBackground(BG);
        addExerciseButton.setForeground(Color.WHITE);
        addExerciseButton.setOpaque(true);
        addExerciseButton.setContentAreaFilled(true);
        addExerciseButton.setBorderPainted(false);
        addExerciseButton.setFocusPainted(false);
    }

    private void drawAddExerciseDialog(Workout workout, Exercise exercise, String purpose) {
        JDialog addExerciseDialog = new JDialog();
        setAddExerciseDialogVisibility(addExerciseDialog, 1140, 720);
        addExerciseDialog.setVisible(true);
        JLayeredPane frontBodyPane = drawImage("./assets/musclemap/front/frontempty.png", 550, 50, 0.25);
        JLayeredPane backBodyPane = drawImage("./assets/musclemap/back/backempty.png", 830, 50, 0.25);
        addExerciseDialog.add(frontBodyPane);
        addExerciseDialog.add(backBodyPane);
        drawHighlightedMuscleLabel(frontBodyPane, backBodyPane);
        if (purpose.equals("ADD")) {
            drawInputFields(addExerciseDialog, workout, null, "ADD");
        } else if (purpose.equals("VIEW")) {
            drawInputFields(addExerciseDialog, workout, exercise, "VIEW");
        }
    }

    private void drawInputFields(JDialog addExerciseDialog, Workout workout, Exercise exercise, String purpose) {
        JLabel exerciseNameLabel = new JLabel("EXERCISE NAME: ");
        exerciseNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
        exerciseNameLabel.setBounds(30, 15, 500, 50);
        exerciseNameLabel.setForeground(PINK);
        exerciseNameLabel.setOpaque(false);

        JTextField exerciseNameField = new JTextField(20);
        if (purpose.equals("VIEW")) {
            exerciseNameField.setText(exercise.getName());
        }
        setExerciseNameFieldProperties(exerciseNameField);

        JLabel musclesworkedLabel = new JLabel("MUSCLES WORKED (select): ");
        musclesworkedLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
        musclesworkedLabel.setBounds(30, 125, 500, 50);
        musclesworkedLabel.setForeground(PINK);
        musclesworkedLabel.setOpaque(false);

        addExerciseDialog.add(exerciseNameLabel);
        addExerciseDialog.add(exerciseNameField);
        addExerciseDialog.add(musclesworkedLabel);
        JPanel buttonsPanel = drawSelectableButtons(addExerciseDialog, purpose, exercise);
        JScrollPane setsPane = drawSetsPane(addExerciseDialog, exercise);
        drawDoneButton(addExerciseDialog, workout, exercise, exerciseNameField, buttonsPanel, setsPane);
        setCloseOperation(addExerciseDialog, workout, exerciseNameField, buttonsPanel, setsPane);
    }

    private void setExerciseNameFieldProperties(JTextField exerciseNameField) {
        exerciseNameField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        exerciseNameField.setForeground(Color.WHITE);
        exerciseNameField.setBackground(new Color(0, 0, 0, 0));
        exerciseNameField.setBounds(30, 70, 500, 50);
        exerciseNameField.setOpaque(false);
        exerciseNameField.setBorder(BorderFactory.createLineBorder(PINK, 2));
    }

    private JScrollPane drawSetsPane(JDialog addExerciseDialog, Exercise exercise) {
        // Container for the sets
        JPanel setContainer = new JPanel();
        setContainer.setLayout(new BoxLayout(setContainer, BoxLayout.Y_AXIS));
        setContainer.setOpaque(false);

        JScrollPane setPane = new JScrollPane(setContainer);
        setPane.setBounds(30, 450, 500, 240);
        setPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setPane.getViewport().setBackground(BG);
        setPane.setBorder(null);

        addExerciseDialog.add(setPane);

        JButton addSetButton = new JButton("+ ADD SET");
        addSetButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
        addSetButton.setBackground(BG);
        addSetButton.setForeground(Color.WHITE);
        addSetButton.setAlignmentX(CENTER_ALIGNMENT);
        addSetButton.setBorder(BorderFactory.createLineBorder(PINK, 0));
        addSetButton.setFocusPainted(false);

        addSetButton.addActionListener(e -> {
            drawSetPanel(setContainer, addSetButton, exercise);
        });

        setContainer.add(addSetButton);
        drawExerciseSetData(exercise, addSetButton, setContainer);
        setScrollbarColor(setPane);
        return setPane;
    }

    private void drawExerciseSetData(Exercise exercise, JButton addSetButton, JPanel setContainer) {
        if (exercise != null) {
            for (ExerciseSet set : exercise.getSets()) {
                addSetButton.doClick();
                for (Component comp : setContainer.getComponents()) {
                    if (comp instanceof JPanel) {
                        JPanel setPanel = (JPanel) comp;
                        for (Component innerComp : setPanel.getComponents()) {
                            if (innerComp instanceof JTextField) {
                                JTextField field = (JTextField) innerComp;
                                String type = (String) field.getClientProperty("type");
                                if ("WEIGHT".equals(type) && field.getForeground().equals(Color.GRAY)) {
                                    field.setText(set.getWeight() + "");
                                    field.setForeground(Color.WHITE);
                                } else if ("REPS".equals(type) && field.getForeground().equals(Color.GRAY)) {
                                    field.setText(set.getReps() + "");
                                    field.setForeground(Color.WHITE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void drawSetPanel(JPanel setContainer, JButton addSetButton, Exercise exercise) {
        JPanel setPanel = new JPanel();
        setSetPanelProperties(setPanel);

        JTextField weightField = new JTextField("Weight");
        setWeightFieldProperties(weightField);
        setDefaultText(weightField, "WEIGHT");
        setFieldConstraints(weightField);

        JTextField repsField = new JTextField("Reps");
        setRepsFieldProperties(repsField);

        setDefaultText(repsField, "REPS");
        setFieldConstraints(repsField);

        JButton deleteSetButton = new JButton("Delete");
        setDeleteSetButtonProperties(deleteSetButton, setContainer, setPanel);

        setPanel.add(weightField);
        setPanel.add(Box.createHorizontalStrut(10));
        setPanel.add(repsField);
        setPanel.add(Box.createHorizontalStrut(10));
        setPanel.add(deleteSetButton);
        setContainer.add(setPanel, setContainer.getComponentCount() - 1);

        setContainer.revalidate();
        setContainer.repaint();
    }

    private void setSetPanelProperties(JPanel setPanel) {
        setPanel.setLayout(new BoxLayout(setPanel, BoxLayout.X_AXIS));
        setPanel.setMaximumSize(new Dimension(460, 40));
        setPanel.setOpaque(false);
        setPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        setPanel.setBorder(BorderFactory.createEmptyBorder(7, 5, 7, 5));
    }

    private void setRepsFieldProperties(JTextField repsField) {
        repsField.putClientProperty("type", "REPS");
        repsField.setPreferredSize(new Dimension(100, 20));
        repsField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        repsField.setForeground(Color.WHITE);
        repsField.setBackground(new Color(0, 0, 0, 0));
        repsField.setOpaque(false);
        repsField.setBorder(BorderFactory.createLineBorder(PINK, 1));
    }

    private void setDeleteSetButtonProperties(JButton deleteSetButton, JPanel setContainer, JPanel setPanel) {
        deleteSetButton.setPreferredSize(new Dimension(80, 40));
        deleteSetButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
        deleteSetButton.setBackground(BG);
        deleteSetButton.setForeground(Color.WHITE);
        deleteSetButton.setAlignmentX(LEFT_ALIGNMENT);
        deleteSetButton.setBorder(BorderFactory.createLineBorder(PINK, 0));
        deleteSetButton.setFocusPainted(false);
        deleteSetButton.addActionListener(e -> {
            setContainer.remove(setPanel);
            setContainer.revalidate();
            setContainer.repaint();
        });
    }

    private void setWeightFieldProperties(JTextField weightField) {
        weightField.putClientProperty("type", "WEIGHT");
        weightField.setPreferredSize(new Dimension(100, 20));
        weightField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        weightField.setForeground(Color.WHITE);
        weightField.setBackground(new Color(0, 0, 0, 0));
        weightField.setOpaque(false);
        weightField.setBorder(BorderFactory.createLineBorder(PINK, 1));
    }

    private void drawDoneButton(JDialog addExerciseDialog, Workout workout, Exercise exercise,
            JTextField exerciseNameField,
            JPanel buttonsPanel, JScrollPane setsPane) {
        JButton doneButton = new JButton("DONE");
        doneButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        doneButton.setBackground(BG);
        doneButton.setForeground(Color.WHITE);
        doneButton.setBorderPainted(false);
        doneButton.setFocusPainted(false);
        doneButton.setOpaque(true);
        doneButton.setBounds(730, 550, 200, 70);
        doneButton.setVisible(true);
        doneButton.setFocusPainted(false);

        doneButton.addActionListener(
                e -> doneButtonAction(workout, exercise, addExerciseDialog, exerciseNameField, buttonsPanel, setsPane));

        addExerciseDialog.add(doneButton);
    }

    private void doneButtonAction(Workout workout, Exercise exercise, JDialog addExerciseDialog,
            JTextField exerciseNameField, JPanel buttonsPanel, JScrollPane setsPane) {
        if (getExerciseValidity(exerciseNameField, buttonsPanel, setsPane)) {
            String exerciseName = exerciseNameField.getText().trim();

            List<String> musclesWorked = new ArrayList<>();
            for (Component comp : buttonsPanel.getComponents()) {
                if (comp instanceof JToggleButton) {
                    JToggleButton button = (JToggleButton) comp;
                    if (button.isSelected()) {
                        musclesWorked.add(button.getText());
                    }
                }
            }

            List<ExerciseSet> sets = new ArrayList<>();
            getExerciseSetsFromPane(setsPane, sets);
            doneButtonCloseAction(exercise, addExerciseDialog, exerciseName, musclesWorked, sets, workout);

        } else {
            JOptionPane.showMessageDialog(addExerciseDialog, "One or more fields is empty",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void doneButtonCloseAction(Exercise exercise, JDialog addExerciseDialog, String exerciseName,
            List<String> musclesWorked, List<ExerciseSet> sets, Workout workout) {
        if (exercise == null) {
            Exercise newExercise = new Exercise(exerciseName, musclesWorked, sets);
            workout.addExercise(newExercise);
        } else {
            exercise.setName(exerciseName);
            exercise.setMusclesWorked(musclesWorked);
            exercise.setSets(sets);
        }

        JOptionPane.showMessageDialog(addExerciseDialog,
                (exercise == null) ? "Exercise added!" : "Changes Saved!", "Success",
                JOptionPane.INFORMATION_MESSAGE);

        layeredPane.remove(overlay);
        layeredPane.revalidate();
        layeredPane.repaint();
        restartFrameAction();
        addExerciseDialog.dispose();
    }

    private void getExerciseSetsFromPane(JScrollPane setsPane, List<ExerciseSet> sets) {
        JPanel setContainer = (JPanel) setsPane.getViewport().getView();
        for (Component comp : setContainer.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel setPanel = (JPanel) comp;
                JTextField weightField = null;
                JTextField repsField = null;

                for (Component innerComp : setPanel.getComponents()) {
                    if (innerComp instanceof JTextField) {
                        JTextField field = (JTextField) innerComp;
                        String type = (String) field.getClientProperty("type");
                        if ("WEIGHT".equals(type)) {
                            weightField = field;
                        } else if ("REPS".equals(type)) {
                            repsField = field;
                        }
                    }
                }

                getSetPaneFields(weightField, repsField, sets);
            }
        }
    }

    private void getSetPaneFields(JTextField weightField, JTextField repsField, List<ExerciseSet> sets) {
        if (weightField != null && repsField != null) {
            int weight = Integer.parseInt(weightField.getText().trim());
            int reps = Integer.parseInt(repsField.getText().trim());
            sets.add(new ExerciseSet(reps, weight));
        }
    }

    private JPanel drawSelectableButtons(JDialog addExerciseDialog, String purpose, Exercise exercise) {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 3, 5, 5));
        buttonsPanel.setOpaque(false);

        String[] muscleGroups = { "Quads", "Hamstrings", "Glutes", "Chest", "Upper Back", "Front Delts", "Biceps",
                "Triceps", "Forearms", "Abs", "Side Delts", "Calves", "Rear Delts", "Lats", "Lower Back" };

        setMuscleButtonFuntionality(muscleGroups, buttonsPanel, purpose, exercise);

        buttonsPanel.setBounds(30, 180, 500, 250);
        addExerciseDialog.add(buttonsPanel);
        addExerciseDialog.revalidate();
        addExerciseDialog.repaint();
        return buttonsPanel;
    }

    private void setMuscleButtonFuntionality(String[] muscleGroups, JPanel buttonsPanel, String purpose,
            Exercise exercise) {
        for (String muscle : muscleGroups) {
            JToggleButton muscleButton = new JToggleButton(muscle);
            setMuscleButtonProperties(muscleButton);
            muscleButton.addItemListener(e -> {
                for (JLabel label : getAllLabels()) {
                    if (muscle.equals(label.getClientProperty("muscle"))) {
                        if (muscleButton.isSelected()) {
                            label.setVisible(true);
                            muscleButton.setBackground(new Color(76, 74, 100));
                        } else {
                            label.setVisible(false);
                            muscleButton.setBackground(BG);
                        }
                    }
                }
            });

            buttonsPanel.add(muscleButton);
            if (purpose.equals("VIEW") && exercise.getMusclesWorked().contains(muscle)) {
                muscleButton.setSelected(true);
            }
        }
    }

    private void setMuscleButtonProperties(JToggleButton muscleButton) {
        muscleButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        muscleButton.setForeground(Color.WHITE);
        muscleButton.setBackground(BG);
        muscleButton.setBorder(BorderFactory.createLineBorder(PINK, 1));
        muscleButton.setOpaque(true);
        muscleButton.setFocusPainted(false);
    }

    private List<JLabel> getAllLabels() {
        return Arrays.asList(absLabel, bicepsLabel, chestLabel, forearmsLabel, frontDeltsLabel, quadsLabel,
                sideDeltsLabel, calvesLabelFront, forearmLabel, glutesLabel, hamstringsLabel, latsLabel, lowerBackLabel,
                upperBackLabel, rearDeltLabel, tricepLabel, calvesLabelBack);
    }

    private void drawHighlightedMuscleLabel(JLayeredPane frontBodyPane, JLayeredPane backBodyPane) {
        frontBodyPane.add(absLabel, Integer.valueOf(1));
        frontBodyPane.add(bicepsLabel, Integer.valueOf(1));
        frontBodyPane.add(chestLabel, Integer.valueOf(1));
        frontBodyPane.add(forearmsLabel, Integer.valueOf(1));
        frontBodyPane.add(frontDeltsLabel, Integer.valueOf(1));
        frontBodyPane.add(quadsLabel, Integer.valueOf(1));
        frontBodyPane.add(sideDeltsLabel, Integer.valueOf(1));
        frontBodyPane.add(calvesLabelFront, Integer.valueOf(1));

        backBodyPane.add(forearmLabel, Integer.valueOf(1));
        backBodyPane.add(glutesLabel, Integer.valueOf(1));
        backBodyPane.add(hamstringsLabel, Integer.valueOf(1));
        backBodyPane.add(latsLabel, Integer.valueOf(1));
        backBodyPane.add(lowerBackLabel, Integer.valueOf(1));
        backBodyPane.add(upperBackLabel, Integer.valueOf(1));
        backBodyPane.add(rearDeltLabel, Integer.valueOf(1));
        backBodyPane.add(tricepLabel, Integer.valueOf(1));
        backBodyPane.add(calvesLabelBack, Integer.valueOf(1));
        setHighlightLabelsInvisible();
    }

    private void drawWorkoutButton(Workout workout, int x) {
        JButton button = new JButton(workout.getName().toUpperCase());
        button.setFont(new Font("SansSerif", Font.BOLD, 30));
        button.setForeground(PINK);
        button.setBackground(BG);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setFont(new Font("SansSerif", Font.BOLD, 32));
                button.setBackground(FADED_BG);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setFont(new Font("SansSerif", Font.BOLD, 30));
                button.setBackground(BG);
            }
        });
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBounds(x, 0, 380, 50);
        workoutsPanel.add(button);
    }

    private void drawExercise(JPanel exercisePanel, Exercise exercise, JPanel exercisesContainer, Workout workout) {
        exercisePanel.setBackground(BG);
        exercisePanel.setLayout(null);
        exercisePanel.putClientProperty("exercise", exercise);

        JLabel exerciseNameLabel = new JLabel(exercise.getName());
        exerciseNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        exerciseNameLabel.setForeground(new Color(255, 255, 255, 200));
        exerciseNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        exerciseNameLabel.setBounds(0, 10, 380, 30);
        exerciseNameLabel.setOpaque(false);

        JButton deleteButton = drawExerciseFunctionsButton(exercisePanel, "./assets/icons/deleteicon.png", 135,
                60, 35, 35, "DELETE", 131, 35, e -> deleteExerciseAction(exercisePanel, exercise, exercisesContainer));
        JButton viewButton = drawExerciseFunctionsButton(exercisePanel, "./assets/icons/viewicon.png", 210, 60,
                35, 35, "VIEW", 213, 35, e -> viewExerciseAction(workout, exercise));

        setExercisePanelFunctionality(exercisePanel, exerciseNameLabel, deleteButton, viewButton);

        exercisePanel.add(exerciseNameLabel);
    }

    private void setExercisePanelFunctionality(JPanel exercisePanel, JLabel exerciseNameLabel, JButton deleteButton,
            JButton viewButton) {
        exercisePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (exercisePanel.contains(evt.getPoint())) {
                    exercisePanel.setBackground(FADED_BG);
                    exerciseNameLabel.setForeground(PINK);
                    deleteButton.setVisible(true);
                    viewButton.setVisible(true);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!exercisePanel.contains(evt.getPoint())) {
                    exercisePanel.setBackground(BG);
                    exerciseNameLabel.setForeground(new Color(255, 255, 255, 200));
                    for (int i = 0; i < 2; i++) {
                        deleteButton.setVisible(false);
                        viewButton.setVisible(false);
                    }
                }
            }
        });
    }

    private JButton drawExerciseFunctionsButton(JPanel exercisePanel, String imagePath, int x, int y, int width,
            int height, String hoverMessage, int messageX, int messageY, ActionListener action) {
        JButton button = new JButton();
        setButtonVisibilty(button, x, y, width, height);
        button.putClientProperty("function", hoverMessage);
        button.setVisible(false);

        JLabel hoverLabel = new JLabel(hoverMessage);
        Font messageFont = new Font("SansSerif", Font.PLAIN, 12);
        setHoverVisibility(hoverLabel, messageX, messageY, messageFont);

        ImageIcon icon = new ImageIcon(imagePath);
        Image originalImage = icon.getImage();
        Image scaledImage = originalImage.getScaledInstance((int) (width * 0.8), (int) (height * 0.8),
                Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));

        Image hoverImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon hoverIcon = new ImageIcon(hoverImage);

        setExerciseFunctionButtonHoverability(button, hoverIcon, hoverLabel, scaledImage);

        button.addActionListener(action);
        exercisePanel.add(button);
        exercisePanel.add(hoverLabel);
        return button;
    }

    private void setExerciseFunctionButtonHoverability(JButton button, ImageIcon hoverIcon, JLabel hoverLabel,
            Image scaledImage) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setIcon(hoverIcon);
                hoverLabel.setVisible(true);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setIcon(new ImageIcon(scaledImage));
                hoverLabel.setVisible(false);
            }
        });
    }

    private void drawStatsPanel() {
        statsPanel = new JPanel();
        statsPanel.setLayout(null);
        statsPanel.setBounds(120, 140, 1140, 560);
        statsPanel.setBackground(new Color(16, 14, 40));
        statsPanel.setVisible(false);
        JLayeredPane frontBodyPane = drawImage("./assets/musclemap/front/frontempty.png", 550, 50, 0.25);
        JLayeredPane backBodyPane = drawImage("./assets/musclemap/back/backempty.png", 830, 50, 0.25);
        statsPanel.add(frontBodyPane, Integer.valueOf(1));
        statsPanel.add(backBodyPane, Integer.valueOf(1));
        List<String> musclesWorked = getMusclesWorked();
        drawFrontBodyMap(frontBodyPane, musclesWorked);
        drawBackBodyMap(backBodyPane, musclesWorked);
        drawWorkoutBarGraph(0, 0, 530, 460);
        drawLegend(30, 470);

        layeredPane.add(statsPanel, Integer.valueOf(1));
    }

    private void drawFrontBodyMap(JLayeredPane pane, List<String> musclesWorked) {
        absLabel = drawMuscleLabel("./assets/musclemap/front/abs.png", 0.25, 95, 116);
        bicepsLabel = drawMuscleLabel("./assets/musclemap/front/biceps.png", 0.25, 72, 112);
        chestLabel = drawMuscleLabel("./assets/musclemap/front/chest.png", 0.25, 94, 75);
        forearmsLabel = drawMuscleLabel("./assets/musclemap/front/forearms.png", 0.25, 31, 152);
        frontDeltsLabel = drawMuscleLabel("./assets/musclemap/front/frontdelts.png", 0.25, 84, 84);
        quadsLabel = drawMuscleLabel("./assets/musclemap/front/quads.png", 0.25, 99, 201);
        sideDeltsLabel = drawMuscleLabel("./assets/musclemap/front/sidedelts.png", 0.25, 84, 84);
        calvesLabelFront = drawMuscleLabel("./assets/musclemap/front/calves.png", 0.25, 105, 316);

        pane.add(absLabel, Integer.valueOf(1));
        pane.add(bicepsLabel, Integer.valueOf(1));
        pane.add(chestLabel, Integer.valueOf(1));
        pane.add(forearmsLabel, Integer.valueOf(1));
        pane.add(frontDeltsLabel, Integer.valueOf(1));
        pane.add(quadsLabel, Integer.valueOf(1));
        pane.add(sideDeltsLabel, Integer.valueOf(1));
        pane.add(calvesLabelFront, Integer.valueOf(1));

        setFrontMusclesWorkedVisible(musclesWorked);
    }

    private List<String> getMusclesWorked() {
        Set<String> ret = new HashSet<>();
        for (Workout workout : workoutSplit) {
            ret.addAll(workout.calculateMusclesWorked());
        }
        return new ArrayList<>(ret);
    }

    private void drawBackBodyMap(JLayeredPane pane, List<String> musclesWorked) {
        forearmLabel = drawMuscleLabel("./assets/musclemap/back/forearms.png", 0.25, 33, 152);
        glutesLabel = drawMuscleLabel("./assets/musclemap/back/glutes.png", 0.19, 103, 190);
        hamstringsLabel = drawMuscleLabel("./assets/musclemap/back/hamstrings.png", 0.19, 98, 230);
        latsLabel = drawMuscleLabel("./assets/musclemap/back/lats.png", 0.19, 94, 112);
        lowerBackLabel = drawMuscleLabel("./assets/musclemap/back/lowerback.png", 0.19, 118, 154);
        upperBackLabel = drawMuscleLabel("./assets/musclemap/back/upperback.png", 0.19, 97, 59);
        rearDeltLabel = drawMuscleLabel("./assets/musclemap/back/reardelts.png", 0.25, 82, 88);
        tricepLabel = drawMuscleLabel("./assets/musclemap/back/triceps.png", 0.25, 71, 112);
        calvesLabelBack = drawMuscleLabel("./assets/musclemap/back/calves.png", 0.19, 104, 309);

        pane.add(forearmLabel, Integer.valueOf(1));
        pane.add(glutesLabel, Integer.valueOf(1));
        pane.add(hamstringsLabel, Integer.valueOf(1));
        pane.add(latsLabel, Integer.valueOf(1));
        pane.add(lowerBackLabel, Integer.valueOf(1));
        pane.add(upperBackLabel, Integer.valueOf(1));
        pane.add(rearDeltLabel, Integer.valueOf(1));
        pane.add(tricepLabel, Integer.valueOf(1));
        pane.add(calvesLabelBack, Integer.valueOf(1));

        setBackMusclesWorkedVisible(musclesWorked);
    }

    private JLabel drawMuscleLabel(String imagePath, double scale, int x, int y) {
        ImageIcon muscleIcon = new ImageIcon(imagePath);

        int originalWidth = muscleIcon.getIconWidth();
        int originalHeight = muscleIcon.getIconHeight();

        int scaledWidth = (int) (originalWidth * scale);
        int scaledHeight = (int) (originalHeight * scale);

        Image scaledImage = muscleIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledScaledIcon = new ImageIcon(scaledImage);

        JLabel muscleLabel = new JLabel(scaledScaledIcon);
        muscleLabel.setBounds(x, y, scaledWidth, scaledHeight);
        muscleLabel.setVisible(false);

        layeredPane.add(muscleLabel, Integer.valueOf(1));

        return muscleLabel;
    }

    private JLayeredPane drawImage(String imagePath, int x, int y, double scale) {
        JLayeredPane muscleMapPane = new JLayeredPane();
        muscleMapPane.setLayout(null); // Allow manual positioning
        ImageIcon muscleMapIcon = new ImageIcon(imagePath);

        int originalWidth = muscleMapIcon.getIconWidth();
        int originalHeight = muscleMapIcon.getIconHeight();

        int scaledWidth = (int) (originalWidth * scale);
        int scaledHeight = (int) (originalHeight * scale);

        Image scaledImage = muscleMapIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(scaledIcon);
        backgroundLabel.setBounds(0, 0, scaledWidth, scaledHeight);

        // Add background label to the base layer of JLayeredPane
        muscleMapPane.add(backgroundLabel, Integer.valueOf(0));
        muscleMapPane.setBounds(x, y, scaledWidth, scaledHeight);
        return muscleMapPane;
    }

    private void drawWorkoutBarGraph(int x, int y, int width, int height) {
        JPanel graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int barWidth = width / 13;
                int spacing = barWidth / 2;
                String[] workoutNames = getWorkoutNames();
                int[] totalSets = new int[3];
                int[] totalReps = new int[3];
                int[] totalVolume = new int[3];

                setTotalStatArrays(totalSets, totalReps, totalVolume);
                int maxVolume = getMaxVolume(totalSets, totalReps, totalVolume);

                double scale = (maxVolume != 0) ? (double) (height - 60) / maxVolume : 1;
                Color[] barColors = { new Color(88, 128, 200), new Color(175, 96, 117), new Color(113, 88, 143) };
                int currentX = spacing;

                drawIndividualBars(g2d, totalSets, totalReps, totalVolume, workoutNames, currentX, height, scale,
                        barColors, barWidth, spacing);
            }
        };
        drawGraph(graphPanel, x, y, width, height);
    }

    private void drawGraph(JPanel graphPanel, int x, int y, int width, int height) {
        graphPanel.setBounds(x, y, width, height);
        graphPanel.setOpaque(false);
        statsPanel.add(graphPanel);
        statsPanel.repaint();
    }

    private int getMaxVolume(int[] totalSets, int[] totalReps, int[] totalVolume) {
        int maxVolume = Math.max(Math.max(totalSets[0], totalReps[0]), totalVolume[0]);
        maxVolume = Math.max(maxVolume, Math.max(Math.max(totalSets[1], totalReps[1]), totalVolume[1]));
        maxVolume = Math.max(maxVolume, Math.max(Math.max(totalSets[2], totalReps[2]), totalVolume[2]));
        return maxVolume;
    }

    private String[] getWorkoutNames() {
        String[] workoutNames = { workoutSplit.get(0).getName(), workoutSplit.get(1).getName(),
                workoutSplit.get(2).getName() };
        return workoutNames;
    }

    private void setTotalStatArrays(int[] totalSets, int[] totalReps, int[] totalVolume) {
        for (int i = 0; i < 3; i++) {
            totalSets[i] = workoutSplit.get(i).calculateTotalSets();
            totalReps[i] = workoutSplit.get(i).calculateTotalReps();
            totalVolume[i] = workoutSplit.get(i).calculateTotalVolume();
        }
    }

    private void drawIndividualBars(Graphics2D g2d, int[] totalSets, int[] totalReps, int[] totalVolume,
            String[] workoutNames, int currentX, int height, double scale, Color[] barColors, int barWidth,
            int spacing) {
        for (int i = 0; i < 3; i++) {
            int[] values = { totalSets[i], totalReps[i], totalVolume[i] };
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 16));
            g2d.setColor(Color.WHITE);
            g2d.drawString(workoutNames[i], currentX, height - 5);
            for (int j = 0; j < 3; j++) {
                int barHeight = (int) (values[j] * scale);
                int barBaseY = height - barHeight - 25;

                g2d.setColor(barColors[j]);
                g2d.fillRect(currentX, barBaseY, barWidth, barHeight);

                g2d.setColor(Color.WHITE);
                g2d.drawString(String.valueOf(values[j]), currentX + barWidth / 4 - 10, barBaseY - 5);
                currentX += barWidth + spacing / 2;
            }
            currentX += spacing;
        }
    }

    private void drawLegend(int x, int y) {
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(null);
        legendPanel.setBounds(x, y, 500, 150); // Adjust size as needed
        legendPanel.setOpaque(false);

        Font legendFont = new Font("SansSerif", Font.PLAIN, 15);
        Color[] barColors = {
                new Color(88, 128, 200), // Sets Completed
                new Color(175, 96, 117), // Reps Completed
                new Color(113, 88, 143) // Weight Lifted
        };

        String[] labels = {
                "Sets Completed",
                "Reps Completed",
                "Weight Lifted (kgs)"
        };

        drawLegendElements(legendPanel, labels, barColors, legendFont);

        statsPanel.add(legendPanel);
        statsPanel.repaint();
    }

    private void drawLegendElements(JPanel legendPanel, String[] labels, Color[] barColors, Font legendFont) {
        for (int i = 0; i < labels.length; i++) {
            JLabel colorBox = new JLabel();
            colorBox.setBounds(i * 160, 26, 20, 20);
            colorBox.setOpaque(true);
            colorBox.setBackground(barColors[i]);
            legendPanel.add(colorBox);

            JLabel textLabel = new JLabel(labels[i]);
            textLabel.setBounds(i * 160 + 24, 26, 250, 20);
            textLabel.setFont(legendFont);
            textLabel.setForeground(Color.WHITE);
            legendPanel.add(textLabel);
        }
    }

    private JButton drawInvisibleButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        return button;
    }

    private void drawOverlayPanel() {
        overlay = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(0, 0, 0, 200));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        overlay.setOpaque(false);
        overlay.setBounds(0, 0, getWidth(), getHeight());

        overlay.addMouseListener(new MouseAdapter() {
        });

        layeredPane.add(overlay, JLayeredPane.MODAL_LAYER);
    }

    private void restartFrameAction() {
        this.dispose();

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new WorkoutTracker(mainUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void workoutsButtonAction() {
        statsPanel.setVisible(false);
        workoutsPanel.setVisible(true);
    }

    private void statsButtonAction() {
        statsPanel.setVisible(true);
        workoutsPanel.setVisible(false);
    }

    private void backButtonAction() {
        this.dispose();
    }

    private void deleteExerciseAction(JPanel exercisePanel, Exercise exercise, JPanel exercisesContainer) {
        exercisesContainer.remove(exercisePanel);
        for (Workout workout : workoutSplit) {
            workout.getExercises().remove(exercise);
        }
        exercisesContainer.revalidate();
        exercisesContainer.repaint();
        setFrontMusclesWorkedVisible(getMusclesWorked());
        setBackMusclesWorkedVisible(getMusclesWorked());
    }

    private void viewExerciseAction(Workout workout, Exercise exercise) {
        drawOverlayPanel();
        drawAddExerciseDialog(workout, exercise, "VIEW");
    }

    private void addExerciseAction(Workout workout) {
        drawOverlayPanel();
        drawAddExerciseDialog(workout, null, "ADD");
    }

    private void setHighlightLabelsInvisible() {
        absLabel.setVisible(false);
        bicepsLabel.setVisible(false);
        chestLabel.setVisible(false);
        forearmsLabel.setVisible(false);
        frontDeltsLabel.setVisible(false);
        quadsLabel.setVisible(false);
        sideDeltsLabel.setVisible(false);
        calvesLabelFront.setVisible(false);

        forearmLabel.setVisible(false);
        glutesLabel.setVisible(false);
        hamstringsLabel.setVisible(false);
        latsLabel.setVisible(false);
        lowerBackLabel.setVisible(false);
        upperBackLabel.setVisible(false);
        rearDeltLabel.setVisible(false);
        tricepLabel.setVisible(false);
        calvesLabelBack.setVisible(false);
    }

    private void setAddExerciseDialogVisibility(JDialog dialog, int width, int height) {
        dialog.setBounds(120, 0, width, height);
        dialog.getContentPane().setBackground(BG);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(null);
    }

    private void setButtonVisibilty(JButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
    }

    private void setHoverVisibility(JLabel hoverLabel, int messageX, int messageY, Font messageFont) {
        hoverLabel.setFont(messageFont);
        hoverLabel.setForeground(Color.WHITE);
        hoverLabel.setBounds(messageX, messageY, 200, 30);
        hoverLabel.setVisible(false);
        layeredPane.add(hoverLabel, Integer.valueOf(2));
    }

    private void setScrollbarColor(JScrollPane scrollPane) {
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = PINK; // Set scrollbar thumb color
                this.trackColor = new Color(16, 14, 40); // Fully transparent track
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                g.setColor(PINK); // Paint the scrollbar thumb in PINK
                g.fillRect(thumbBounds.x, thumbBounds.y, 10, thumbBounds.height);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return drawInvisibleButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return drawInvisibleButton();
            }
        });
    }

    private void setBackMusclesWorkedVisible(List<String> musclesWorked) {
        forearmLabel.setVisible(musclesWorked.contains("Forearms"));
        glutesLabel.setVisible(musclesWorked.contains("Glutes"));
        hamstringsLabel.setVisible(musclesWorked.contains("Hamstrings"));
        latsLabel.setVisible(musclesWorked.contains("Lats"));
        lowerBackLabel.setVisible(musclesWorked.contains("Lower Back"));
        upperBackLabel.setVisible(musclesWorked.contains("Upper Back"));
        rearDeltLabel.setVisible(musclesWorked.contains("Rear Delts"));
        tricepLabel.setVisible(musclesWorked.contains("Triceps"));
        calvesLabelBack.setVisible(musclesWorked.contains("Calves"));
        statsPanel.revalidate();
        statsPanel.repaint();
    }

    private void setFrontMusclesWorkedVisible(List<String> musclesWorked) {
        absLabel.setVisible(musclesWorked.contains("Abs"));
        bicepsLabel.setVisible(musclesWorked.contains("Biceps"));
        chestLabel.setVisible(musclesWorked.contains("Chest"));
        forearmsLabel.setVisible(musclesWorked.contains("Forearms"));
        frontDeltsLabel.setVisible(musclesWorked.contains("Front Delts"));
        quadsLabel.setVisible(musclesWorked.contains("Quads"));
        sideDeltsLabel.setVisible(musclesWorked.contains("Side Delts"));
        calvesLabelFront.setVisible(musclesWorked.contains("Calves"));
        statsPanel.revalidate();
        statsPanel.repaint();
    }

    private void setFieldConstraints(JTextField field) {
        field.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (field.getForeground() == Color.GRAY || !Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    private void setDefaultText(JTextField field, String defaultText) {
        field.setText(defaultText);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(defaultText)) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(defaultText);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void setCloseOperation(JDialog addExerciseDialog, Workout workout, JTextField exerciseNameField,
            JPanel buttonsPanel, JScrollPane setsPane) {
        addExerciseDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addExerciseDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                addExerciseDialog.dispose();
                restartFrameAction();
            }
        });
    }

    private void setLabelClientProperty() {
        absLabel.putClientProperty("muscle", "Abs");
        bicepsLabel.putClientProperty("muscle", "Biceps");
        chestLabel.putClientProperty("muscle", "Chest");
        forearmsLabel.putClientProperty("muscle", "Forearms");
        frontDeltsLabel.putClientProperty("muscle", "Front Delts");
        quadsLabel.putClientProperty("muscle", "Quads");
        sideDeltsLabel.putClientProperty("muscle", "Side Delts");
        calvesLabelFront.putClientProperty("muscle", "Calves");
        forearmLabel.putClientProperty("muscle", "Forearms");
        glutesLabel.putClientProperty("muscle", "Glutes");
        hamstringsLabel.putClientProperty("muscle", "Hamstrings");
        latsLabel.putClientProperty("muscle", "Lats");
        lowerBackLabel.putClientProperty("muscle", "Lower Back");
        upperBackLabel.putClientProperty("muscle", "Upper Back");
        rearDeltLabel.putClientProperty("muscle", "Rear Delts");
        tricepLabel.putClientProperty("muscle", "Triceps");
        calvesLabelBack.putClientProperty("muscle", "Calves");
    }

    private boolean getExerciseValidity(JTextField exerciseNameField, JPanel buttonsPanel, JScrollPane setsPane) {
        boolean isExerciseNameFilled = !exerciseNameField.getText().trim().isEmpty();

        boolean isMuscleSelected = false;
        for (Component comp : buttonsPanel.getComponents()) {
            if (comp instanceof JToggleButton) {
                JToggleButton button = (JToggleButton) comp;
                if (button.isSelected()) {
                    isMuscleSelected = true;
                    break;
                }
            }
        }

        boolean areSetFieldsValid = getSetPaneValidity(setsPane);

        return isExerciseNameFilled && isMuscleSelected && areSetFieldsValid;
    }

    private boolean getSetPaneValidity(JScrollPane setsPane) {
        boolean areSetFieldsValid = true;
        JPanel setContainer = (JPanel) setsPane.getViewport().getView();
        for (Component comp : setContainer.getComponents()) {
            if (comp instanceof JPanel) { // Each setPanel
                JPanel setPanel = (JPanel) comp;
                for (Component innerComp : setPanel.getComponents()) {
                    if (innerComp instanceof JTextField) {
                        JTextField setField = (JTextField) innerComp;
                        if (setField.getText().trim().isEmpty() || setField.getForeground() == Color.GRAY) {
                            areSetFieldsValid = false;
                            break;
                        }
                    }
                }
            }
        }
        return areSetFieldsValid;
    }
}