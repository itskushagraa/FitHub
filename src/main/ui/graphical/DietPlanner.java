package ui.graphical;

import model.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

public class DietPlanner extends JFrame {
    private static final int ROWS = 7;
    private static final int COLS = 3;

    private UserProfile mainUser;
    private DietPlan dietPlan;
    private JLayeredPane layeredPane;
    private JPanel gridContainer;
    private JPanel mealsPanel;
    private JPanel statsPanel;

    private JTextField mealName;
    private JLabel mealType;
    private JTextField mealQuantity;
    private JTextField mealCalories;
    private JTextField mealProtein;
    private JTextField mealFat;
    private JTextField mealCarb;

    public DietPlanner(UserProfile user) {
        this.mainUser = user;
        this.dietPlan = this.mainUser.getDietPlan();
        run();
    }

    private void run() {
        drawBackground();
        drawLogo("./assets/logo.png", 1150, 30, 100, 64);
        drawButtonHolderTab(0, 0, 100, this.getHeight());
        drawHeaderLabel("D I E T  P L A N N E R", 10, 30, 560, 90, new Font("SansSerif", Font.BOLD, 48));
        drawButtons();
        drawMealsPanel();
        drawStatsPanel();
        drawAllMealGridLabels();
        drawMealButtonGrid(200, 50, 940, 515);
    }

    /*
     * DRAW COMPONENTS
     */
    
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

    private void drawButtonHolderTab(int x, int y, int width, int height) {
        JPanel sideTab = new JPanel();
        sideTab.setBounds(x, y, width, height);
        sideTab.setBackground(new Color(34, 30, 65));
        sideTab.setOpaque(true);

        layeredPane.add(sideTab, Integer.valueOf(0));
    }

    private void drawButtons() {
        drawInteractiveButton("./assets/icons/mealsicon.png", 20, 145, 60, 60,
                "View Meals", 16, 115, new Font("SansSerif", Font.BOLD, 12), e -> mealsButtonAction());
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

    private void drawAllMealGridLabels() {
        drawSingleLabel("MONDAY", 0, 60, 190, 50, SwingConstants.RIGHT);
        drawSingleLabel("TUESDAY", 0, 125, 190, 50, SwingConstants.RIGHT);
        drawSingleLabel("WEDNESDAY", 0, 200, 190, 50, SwingConstants.RIGHT);
        drawSingleLabel("THURSDAY", 0, 275, 190, 50, SwingConstants.RIGHT);
        drawSingleLabel("FRIDAY", 0, 350, 190, 50, SwingConstants.RIGHT);
        drawSingleLabel("SATURDAY", 0, 425, 190, 50, SwingConstants.RIGHT);
        drawSingleLabel("SUNDAY", 0, 500, 190, 50, SwingConstants.RIGHT);

        drawSingleLabel("BREAKFAST", 270, 0, 180, 45, SwingConstants.CENTER);
        drawSingleLabel("LUNCH", 575, 0, 180, 45, SwingConstants.CENTER);
        drawSingleLabel("DINNER", 890, 0, 180, 45, SwingConstants.CENTER);
    }

    private void drawSingleLabel(String text, int x, int y, int width, int height, int alignment) {
        JLabel dayLabel = new JLabel(text);
        dayLabel.setBounds(x, y, width, height);
        dayLabel.setOpaque(false);
        dayLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        dayLabel.setForeground(new Color(196, 132, 241, 255));
        dayLabel.setHorizontalAlignment(alignment);

        mealsPanel.add(dayLabel);
        mealsPanel.revalidate();
        mealsPanel.repaint();
    }

    private void drawMealButtonGrid(int gridX, int gridY, int gridWidth, int gridHeight) {
        gridContainer = new JPanel();
        gridContainer.setBounds(gridX, gridY, gridWidth, gridHeight);
        gridContainer.setLayout(null);
        gridContainer.setOpaque(true);
        gridContainer.setBackground(new Color(16, 14, 40));
        mealsPanel.add(gridContainer);

        int buttonWidth = gridWidth / COLS;
        int buttonHeight = gridHeight / ROWS;

        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int buttonX = col * buttonWidth;
                int buttonY = row * buttonHeight;
                drawSingleMealButton(
                        dietPlan.getCompleteWeeklyPlan().get(daysOfWeek.get(row)).get(col),
                        buttonX,
                        buttonY, buttonWidth, buttonHeight);
            }
        }

        gridContainer.revalidate();
        gridContainer.repaint();
    }

    private void drawSingleMealButton(Meal meal, int x, int y, int width, int height) {
        JButton mealButton = new JButton(meal.getName().toUpperCase());
        mealButton.setBounds(x, y, width, height);
        mealButton.setBackground(new Color(16, 14, 40, 255));
        mealButton.setForeground(new Color(255, 255, 255, 200));
        mealButton.setBorderPainted(false);
        mealButton.setFocusPainted(false);
        mealButton.setOpaque(true);
        mealButton.setFont(new Font("SansSerif", Font.PLAIN, 14));

        mealButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mealButton.setBackground(new Color(34, 30, 65));
                mealButton.setFont(new Font("SansSerif", Font.BOLD, 16));
                mealButton.setForeground(new Color(196, 132, 241, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                mealButton.setBackground(new Color(16, 14, 40));
                mealButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
                mealButton.setForeground(new Color(255, 255, 255, 150));
            }
        });

        mealButton.addActionListener(e -> mealAction(meal, mealButton));

        gridContainer.add(mealButton);
    }

    private void drawOverlayPanel() {
        JPanel overlay = new JPanel() {
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

    private void drawMealDialog(Meal meal, JButton mealButton) {
        JDialog mealDialog = new JDialog(this, "Meal Details", true);
        setMealDialogVisibility(mealDialog, 600, 400);

        JPanel buttonPanel = drawDialogPanel(mealDialog);
        drawDialogButton("./assets/icons/backicon.png", buttonPanel, 5, 350, 40, 40, 10, 325, 200, 30, "Back",
                e -> closeDialogAction(mealDialog, meal, mealButton));
        drawDialogButton("./assets/icons/editicon.png", buttonPanel, 5, 290, 40, 40, 10, 265, 200, 30, "Edit",
                e -> editDialogAction(mealDialog, buttonPanel, meal, mealButton));
        drawMealInfo(mealDialog, meal);
        mealDialog.setVisible(true);
    }

    private void drawMealInfo(JDialog mealDialog, Meal meal) {
        drawMealName(mealDialog, meal, 60, 10, 520, 30);
        drawMealType(mealDialog, meal, 60, 50, 150, 30);
        drawMealCalories(mealDialog, meal, 40, 90, 100, 30);
        drawMealQuantity(mealDialog, meal, 40, 130, 100, 30);
        drawMealMacros(mealDialog, meal, 40, 170, 100, 30);
        drawMealIngredients(mealDialog, meal, 40, 290, 200, 30);
        setInputConstraints();
    }

    private void drawMealName(JDialog mealDialog, Meal meal, int x, int y, int width, int height) {
        mealName = new JTextField(meal.getName().toUpperCase());
        mealName.setFont(new Font("SansSerif", Font.BOLD, 28));
        mealName.setForeground(new Color(255, 255, 255));
        mealName.setEditable(false);
        mealName.setOpaque(false);
        mealName.setBorder(null);
        mealName.setBounds(x, y, width, height);
        mealDialog.add(mealName);
    }

    private void drawMealType(JDialog mealDialog, Meal meal, int x, int y, int width, int height) {
        mealType = new JLabel(meal.getType().toUpperCase());
        mealType.setFont(new Font("SansSerif", Font.PLAIN, 20));
        mealType.setForeground(new Color(255, 255, 255));
        mealType.setOpaque(false);
        mealType.setBorder(null);
        mealType.setBounds(x, y, width, height);
        mealDialog.add(mealType);
    }

    private void drawMealCalories(JDialog mealDialog, Meal meal, int x, int y, int width, int height) {
        JLabel calorieLabel = drawInfoLabel("Calories (in kcal): ", x, y, height);
        mealDialog.add(calorieLabel);
        mealCalories = new JTextField(meal.getCalories() + "");
        mealCalories.setFont(new Font("SansSerif", Font.PLAIN, 20));
        mealCalories.setForeground(new Color(255, 255, 255));
        mealCalories.setEditable(false);
        mealCalories.setOpaque(false);
        mealCalories.setBorder(null);
        mealCalories.setBounds(x + 200, y, width, height);
        mealDialog.add(mealCalories);
    }

    private void drawMealQuantity(JDialog mealDialog, Meal meal, int x, int y, int width, int height) {
        JLabel quantityLabel = drawInfoLabel("Quantity (in g): ", x, y, height);
        mealDialog.add(quantityLabel);
        mealQuantity = new JTextField(meal.getQuantity() + "");
        mealQuantity.setFont(new Font("SansSerif", Font.PLAIN, 20));
        mealQuantity.setForeground(new Color(255, 255, 255));
        mealQuantity.setEditable(false);
        mealQuantity.setOpaque(false);
        mealQuantity.setBorder(null);
        mealQuantity.setBounds(x + 200, y, width, height);
        mealDialog.add(mealQuantity);
    }

    private void drawMealMacros(JDialog mealDialog, Meal meal, int x, int y, int width, int height) {
        JLabel proteinLabel = drawInfoLabel("Protein (in g): ", x, y, height);
        JLabel fatLabel = drawInfoLabel("Fat (in g): ", x, y + 40, height);
        JLabel carbLabel = drawInfoLabel("Carbs (in g): ", x, y + 80, height);
        mealDialog.add(proteinLabel);
        mealDialog.add(fatLabel);
        mealDialog.add(carbLabel);
        drawMealProtein(mealDialog, meal, x, y, width, height);
        drawMealFat(mealDialog, meal, x, y + 40, width, height);
        drawMealCarb(mealDialog, meal, x, y + 80, width, height);
    }

    private void drawMealProtein(JDialog mealDialog, Meal meal, int x, int y, int width, int height) {
        mealProtein = new JTextField(meal.getProtein() + "");
        mealProtein.setFont(new Font("SansSerif", Font.PLAIN, 20));
        mealProtein.setForeground(new Color(255, 255, 255));
        mealProtein.setEditable(false);
        mealProtein.setOpaque(false);
        mealProtein.setBorder(null);
        mealProtein.setBounds(x + 200, y, width, height);
        mealDialog.add(mealProtein);
    }

    private void drawMealFat(JDialog mealDialog, Meal meal, int x, int y, int width, int height) {
        mealFat = new JTextField(meal.getFat() + "");
        mealFat.setFont(new Font("SansSerif", Font.PLAIN, 20));
        mealFat.setForeground(new Color(255, 255, 255));
        mealFat.setEditable(false);
        mealFat.setOpaque(false);
        mealFat.setBorder(null);
        mealFat.setBounds(x + 200, y, width, height);
        mealDialog.add(mealFat);
    }

    private void drawMealCarb(JDialog mealDialog, Meal meal, int x, int y, int width, int height) {
        mealCarb = new JTextField(meal.getCarb() + "");
        mealCarb.setFont(new Font("SansSerif", Font.PLAIN, 20));
        mealCarb.setForeground(new Color(255, 255, 255));
        mealCarb.setEditable(false);
        mealCarb.setOpaque(false);
        mealCarb.setBorder(null);
        mealCarb.setBounds(x + 200, y, width, height);
        mealDialog.add(mealCarb);
    }

    private void drawMealIngredients(JDialog mealDialog, Meal meal, int x, int y, int width, int height) {
        JLabel ingredientsLabel = drawInfoLabel("Ingredients: ", x, y, height);
        mealDialog.add(ingredientsLabel);
        JPanel ingredientPanel = new JPanel();
        ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.Y_AXIS));
        ingredientPanel.setOpaque(false);

        for (String ingredient : meal.getIngredients()) {
            ingredientPanel.add(drawIngredientsField(ingredient, ingredientPanel));
        }

        JButton addIngredientButton = new JButton("Add Ingredient");
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonRow.add(addIngredientButton);
        buttonRow.setOpaque(false);
        ingredientPanel.add(buttonRow);

        addIngredientButton.addActionListener(e -> {
            ingredientPanel.add(drawIngredientsField("", ingredientPanel), ingredientPanel.getComponentCount() - 1);
            ingredientPanel.revalidate();
            ingredientPanel.repaint();
        });

        JScrollPane scrollPane = new JScrollPane(ingredientPanel);
        setScrollPaneVisibility(scrollPane);
        scrollPane.setBounds(x + 200, y, width + 150, height + 70);
        mealDialog.add(scrollPane);
    }

    private JPanel drawIngredientsField(String ingredient, JPanel ingredientPanel) {
        JPanel ingredientRow = new JPanel();
        ingredientRow.setLayout(new BoxLayout(ingredientRow, BoxLayout.X_AXIS));
        ingredientRow.setMaximumSize(new Dimension(400, 40));
        ingredientRow.setOpaque(false);

        JTextField ingredientField = new JTextField(ingredient);
        ingredientField.setMaximumSize(new Dimension(300, 40));
        ingredientRow.add(ingredientField);
        ingredientField.setBackground(new Color(34, 30, 65));
        ingredientField.setForeground(Color.WHITE);
        ingredientField.setFont(new Font("SansSerif", Font.PLAIN, 18));

        JButton deleteButton = new JButton();
        ImageIcon icon = new ImageIcon("./assets/icons/deleteicon.png");
        deleteButton.setIcon(new ImageIcon(icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        deleteButton.setMaximumSize(new Dimension(30, 30));
        deleteButton.setBorder(BorderFactory.createEmptyBorder());
        deleteButton.setContentAreaFilled(false);
        deleteButton.addActionListener(e -> {
            ingredientPanel.remove(ingredientRow);
            ingredientPanel.revalidate();
            ingredientPanel.repaint();
        });
        ingredientRow.add(deleteButton);

        return ingredientRow;
    }

    private JLabel drawInfoLabel(String info, int x, int y, int height) {
        JLabel label = new JLabel(info);
        label.setFont(new Font("SansSerif", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setForeground(new Color(196, 132, 241));
        label.setOpaque(false);
        label.setBorder(null);
        label.setBounds(x, y, 200, height);
        return label;
    }

    private JPanel drawDialogPanel(JDialog mealDialog) {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setBounds(0, 0, 50, 400);
        dialogPanel.setBackground(new Color(34, 30, 65));
        dialogPanel.setLayout(null);
        dialogPanel.setOpaque(true);
        mealDialog.add(dialogPanel);
        return dialogPanel;
    }

    private void drawDialogButton(String imagePath, JPanel dialogPanel, int x, int y, int width, int height,
            int hoverX, int hoverY, int hoverWidth, int hoverHeight, String hoverMessage, ActionListener action) {
        JButton button = new JButton();
        button.putClientProperty("type", hoverMessage);
        setButtonVisibilty(button, x, y, width, height);

        JLabel hoverLabel = new JLabel(hoverMessage);
        drawLabelInDialog(hoverLabel, dialogPanel, hoverX, hoverY, hoverWidth, hoverHeight);

        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance((int) (width * 0.8), (int) (height * 0.8),
                Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
        Image hoverImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setIcon(new ImageIcon(hoverImage));
                hoverLabel.setVisible(true);
            }

            public void mouseExited(MouseEvent evt) {
                button.setIcon(new ImageIcon(scaledImage));
                hoverLabel.setVisible(false);
            }
        });

        button.addActionListener(action);
        dialogPanel.add(button);
    }

    private void drawLabelInDialog(JLabel backHoverLabel, JPanel dialogPanel, int x, int y, int width, int height) {
        backHoverLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        backHoverLabel.setForeground(Color.WHITE);
        backHoverLabel.setBounds(x, y, width, height);
        backHoverLabel.setVisible(false);
        dialogPanel.add(backHoverLabel);
    }

    private void drawMealsPanel() {
        mealsPanel = new JPanel();
        mealsPanel.setLayout(null);
        mealsPanel.setBounds(120, 140, 1140, 560);
        mealsPanel.setOpaque(false);
        mealsPanel.setVisible(true);
        layeredPane.add(mealsPanel, Integer.valueOf(1));
    }

    private void drawStatsPanel() {
        statsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int barWidth = 77;
                int gap = 40;
                int start = 30;
                int base = 510;
                Color[] colors = { new Color(196, 132, 241), Color.CYAN, Color.YELLOW };
                int[][] nutrientData = getMacroData();
                drawMacroBarGraph(g2d, nutrientData, start, barWidth, gap, base, colors);
            }
        };
        statsPanel.setLayout(null);
        statsPanel.setBounds(120, 140, 1140, 560);
        statsPanel.setBackground(new Color(16, 14, 40));
        statsPanel.setVisible(false);
        layeredPane.add(statsPanel, Integer.valueOf(1));
    }

    private void drawMacroBarGraph(Graphics2D g2d, int[][] nutrientData, int start, int barWidth, int gap, int base,
            Color[] colors) {
        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        for (int day = 0; day < nutrientData.length; day++) {
            int totalIntake = nutrientData[day][0] + nutrientData[day][1] + nutrientData[day][2];
            int xpos = start + (day * (barWidth + gap));
            int ypos = base;
            for (int i = 0; i < nutrientData[day].length; i++) {
                int segmentHeight = (int) ((nutrientData[day][i] / (double) totalIntake) * 485);
                g2d.setColor(colors[i]);
                g2d.fillRect(xpos, ypos - segmentHeight, barWidth, segmentHeight);
                ypos -= segmentHeight;
            }
            g2d.setColor(Color.WHITE);
            g2d.drawString(daysOfWeek.get(day), xpos, base + 20);
        }
        drawBarGraphLegend(g2d, 845, 160);
        drawCalorieInfo(g2d, 845, 50);
        drawQuantityBarGraph(g2d, 845, 330);
    }

    private void drawCalorieInfo(Graphics2D g2d, int x, int y) {
        int consumedCalories = dietPlan.calculateWeeklyCalories();
        int totalCalories = (int) mainUser.getTargetCalories() * 7;
        g2d.setFont(new Font("SansSerif", Font.BOLD, 18));
        g2d.setColor(Color.WHITE);
        g2d.drawString("WEEKLY CALORIES CONSUMED", x, y - 10);
        String calorieInfo = consumedCalories + " / " + totalCalories;
        g2d.drawString(calorieInfo, x, y + 25);
        int barWidth = 275;
        int barHeight = 35;
        int filledWidth = (int) ((consumedCalories / (double) totalCalories) * barWidth);
        g2d.setColor(new Color(16, 14, 40));
        g2d.fillRect(x, y + 50, barWidth, barHeight);
        g2d.setColor(new Color(117, 186, 117));
        g2d.fillRect(x, y + 50, filledWidth, barHeight);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(x, y + 50, barWidth, barHeight);
    }

    private void drawQuantityBarGraph(Graphics2D g2d, int x, int y) {
        int[] quantities = new int[7];
        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        for (int i = 0; i < 7; i++) {
            quantities[i] = (int) dietPlan.calculateDailyQuantity(daysOfWeek.get(i));
        }
        int maxQuantity = Arrays.stream(quantities).max().orElse(1);
        int barWidth = 25;
        int barHeightMax = 180;
        g2d.setFont(new Font("SansSerif", Font.BOLD, 18));
        g2d.drawString((int) dietPlan.calculateWeeklyQuantity() + " g", x, y - 20);
        g2d.drawString("WEEKLY QUANTITY CONSUMED", x, y - 55);
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 16));
        g2d.setColor(new Color(245, 148, 92));
        for (int i = 0; i < quantities.length; i++) {
            int barHeight = (int) ((quantities[i] / (double) maxQuantity) * barHeightMax);
            int xpos = x + i * (barWidth + 18);
            int ypos = y + barHeightMax - barHeight;
            g2d.fillRect(xpos, ypos, barWidth, barHeight);
            g2d.setColor(Color.WHITE);
            g2d.drawString(daysOfWeek.get(i).substring(0, 1), xpos + 5, y + barHeightMax + 20);
            g2d.setColor(new Color(245, 148, 92));
        }
    }

    private void drawBarGraphLegend(Graphics2D g2d, int x, int y) {
        Color[] colors = { Color.YELLOW, Color.CYAN, new Color(196, 132, 241) };
        String[] labels = { "Fat", "Carbs", "Protein" };

        int boxSize = 20;
        int spacing = 30;

        for (int i = 0; i < colors.length; i++) {
            g2d.setColor(colors[i]);
            g2d.fillRect(x, y + (i * spacing), boxSize, boxSize);
            g2d.setColor(Color.WHITE);
            g2d.drawString(labels[i], x + boxSize + 10, y + (i * spacing) + boxSize - 5);
        }
    }

    /*
     * BUTTON FUNCTIONALITY
     */

    private void backButtonAction() {
        this.dispose();
    }

    private void mealsButtonAction() {
        statsPanel.setVisible(false);
        mealsPanel.setVisible(true);
    }

    private void statsButtonAction() {
        mealsPanel.setVisible(false);
        statsPanel.setVisible(true);
    }

    private void mealAction(Meal meal, JButton mealButton) {
        drawOverlayPanel();
        drawMealDialog(meal, mealButton);
    }

    private void closeDialogAction(JDialog mealDialog, Meal meal, JButton mealButton) {
        if (getScrollPaneValidity(mealDialog)) {
            setMeal(mealDialog, meal);
            mealButton.setText(meal.getName());
            mealDialog.dispose();
            layeredPane.getComponentsInLayer(JLayeredPane.MODAL_LAYER)[0].setVisible(false);
            layeredPane.remove(layeredPane.getComponentsInLayer(JLayeredPane.MODAL_LAYER)[0]);
            layeredPane.revalidate();
            layeredPane.repaint();
        }

    }

    private void editDialogAction(JDialog mealDialog, JPanel buttonPanel, Meal meal, JButton mealButton) {
        setMealEditable(mealDialog, true);

        for (Component component : buttonPanel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                Object type = button.getClientProperty("type");
                if ("Edit".equals(type) || "Back".equals(type)) {
                    buttonPanel.remove(button);
                }
            } else if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if ("Edit".equals(label.getText()) || "Back".equals(label.getText())) {
                    buttonPanel.remove(label);
                }
            }
        }

        drawDialogButton("./assets/icons/doneicon.png", buttonPanel, 5, 350, 40, 40, 10, 325, 200, 30, "Done",
                e -> doneDialogAction(mealDialog, buttonPanel, meal, mealButton));
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void doneDialogAction(JDialog mealDialog, JPanel buttonPanel, Meal meal, JButton mealButton) {
        for (Component component : mealDialog.getContentPane().getComponents()) {
            if (component instanceof JTextField) {
                JTextField field = (JTextField) component;
                if (field.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(mealDialog, "One or more fields is empty.", "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        setMealEditable(mealDialog, false);
        if (getScrollPaneValidity(mealDialog)) {
            setMeal(mealDialog, meal);
        }

        setViewMode(buttonPanel);

        drawDialogButton("./assets/icons/editicon.png", buttonPanel, 5, 290, 40, 40, 10, 265, 200, 30, "Edit",
                e -> editDialogAction(mealDialog, buttonPanel, meal, mealButton));
        drawDialogButton("./assets/icons/backicon.png", buttonPanel, 5, 350, 40, 40, 10, 325, 200, 30, "Back",
                e -> closeDialogAction(mealDialog, meal, mealButton));
    }

    /*
     * GETTERS
     */

    private boolean getInputValidity(String text) {
        if (text.isEmpty()) {
            return true;
        }
        if (text.matches("\\d+\\.?\\d?")) {
            return true;
        }
        return false;
    }

    private boolean getScrollPaneValidity(JDialog mealDialog) {
        JScrollPane scrollPane = getScrollPane(mealDialog);
        if (scrollPane != null) {
            JPanel ingredientPanel = (JPanel) scrollPane.getViewport().getView();
            for (Component component : ingredientPanel.getComponents()) {
                if (component instanceof JPanel) {
                    for (Component subComponent : ((JPanel) component).getComponents()) {
                        if (subComponent instanceof JTextField) {
                            JTextField ingredientField = (JTextField) subComponent;
                            if (ingredientField.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(mealDialog,
                                        "One or more ingredient fields is empty.",
                                        "Validation Error",
                                        JOptionPane.ERROR_MESSAGE);
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private JScrollPane getScrollPane(JDialog mealDialog) {
        for (Component component : mealDialog.getContentPane().getComponents()) {
            if (component instanceof JScrollPane) {
                return (JScrollPane) component;
            }
        }
        return null;
    }

    private int[][] getMacroData() {
        Map<String, List<Meal>> weeklyPlan = dietPlan.getCompleteWeeklyPlan();
        int[][] nutrientData = new int[7][3];

        List<String> daysOfWeek = new ArrayList<>(
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));

        for (int i = 0; i < daysOfWeek.size(); i++) {
            List<Meal> meals = weeklyPlan.get(daysOfWeek.get(i));
            int totalProtein = 0;
            int totalCarbs = 0;
            int totalFat = 0;

            for (Meal meal : meals) {
                totalProtein += (int) meal.getProtein();
                totalCarbs += (int) meal.getCarb();
                totalFat += (int) meal.getFat();
            }

            nutrientData[i][0] = totalProtein;
            nutrientData[i][1] = totalCarbs;
            nutrientData[i][2] = totalFat;
        }

        return nutrientData;
    }

    /*
     * SETTERS
     */

    private void setInputConstraints() {
        setFieldConstraints(mealCalories);
        setFieldConstraints(mealQuantity);
        setFieldConstraints(mealProtein);
        setFieldConstraints(mealFat);
        setFieldConstraints(mealCarb);
        setFieldConstraints(mealCalories);
    }

    private void setFieldConstraints(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + string + currentText.substring(offset);

                if (getInputValidity(newText)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

                if (getInputValidity(newText)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
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

    private void setMealEditable(JDialog mealDialog, boolean canBeEdited) {
        if (canBeEdited) {
            for (Component component : mealDialog.getContentPane().getComponents()) {
                if (component instanceof JTextField) {
                    JTextField field = (JTextField) component;
                    field.setEditable(true);
                    field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(196, 132, 241)));
                }
            }
        } else {
            for (Component component : mealDialog.getContentPane().getComponents()) {
                if (component instanceof JTextField) {
                    JTextField field = (JTextField) component;
                    field.setEditable(false);
                    field.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(196, 132, 241)));
                }
            }
        }
    }

    private void setMealDialogVisibility(JDialog mealDialog, int width, int height) {
        mealDialog.setUndecorated(true);
        mealDialog.setSize(width, height);
        mealDialog.setLocationRelativeTo(this);
        mealDialog.getContentPane().setBackground(new Color(16, 14, 40));
        mealDialog.setLayout(null);
    }

    private void setViewMode(JPanel buttonPanel) {
        for (Component component : buttonPanel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                Object type = button.getClientProperty("type");
                if ("Done".equals(type)) {
                    buttonPanel.remove(button);
                }
            } else if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if ("Done".equals(label.getText())) {
                    buttonPanel.remove(label);
                }
            }
        }
    }

    private void setScrollPaneVisibility(JScrollPane scrollPane) {
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBackground(new Color(16, 14, 40, 0));
        scrollPane.getViewport().setBackground(new Color(16, 14, 40, 0));
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getHorizontalScrollBar().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
    }

    private void setMeal(JDialog mealDialog, Meal meal) {
        meal.setName(mealName.getText());
        meal.setCalories(Double.parseDouble(mealCalories.getText()));
        meal.setQuantity(Double.parseDouble(mealQuantity.getText()));
        meal.setProtein(Double.parseDouble(mealProtein.getText()));
        meal.setFat(Double.parseDouble(mealFat.getText()));
        meal.setCarb(Double.parseDouble(mealCarb.getText()));

        setIngredients(mealDialog, meal);

    }

    private void setIngredients(JDialog mealDialog, Meal meal) {
        JScrollPane scrollPane = getScrollPane(mealDialog);
        if (scrollPane != null) {
            JPanel ingredientPanel = (JPanel) scrollPane.getViewport().getView();
            List<String> updatedIngredients = new ArrayList<>();

            for (Component component : ingredientPanel.getComponents()) {
                if (component instanceof JPanel) {
                    for (Component subComponent : ((JPanel) component).getComponents()) {
                        if (subComponent instanceof JTextField) {
                            updatedIngredients.add(((JTextField) subComponent).getText());
                        }
                    }
                }
            }
            meal.setIngredients(updatedIngredients); // Set the new ingredients list
        }
    }

}