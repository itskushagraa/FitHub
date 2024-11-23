package ui.graphical;

public class FitHubGraphicalUI {
    private AppState currentState;

    // EFFECTS: runs splash screen and sets currentState to SPLASH_SCREEN
    public FitHubGraphicalUI() {
        currentState = AppState.SPLASH_SCREEN;
        run();
    }

    /* EFFECTS: 
     * - runs the app by switching app states
     * - case DEFAULT is never reached
     */
    public void run() {
        while (true) {
            switch (currentState) {
                case SPLASH_SCREEN:
                    showSplashScreen();
                case WELCOME_SCREEN:
                    new WelcomeScreen();
                    currentState = AppState.DEFAULT;
                    break;
                case DEFAULT:
                    return;
            }
        }
    }

    // EFFECTS: runs a while loop to se
    private void showSplashScreen() {
        SplashScreen splashScreen = new SplashScreen();
        while (splashScreen.isDisplayable()) {
            // LASTS FOR THE DURATION OF THE SPLASH SCREEN
        }
        currentState = AppState.WELCOME_SCREEN;
    }
}
