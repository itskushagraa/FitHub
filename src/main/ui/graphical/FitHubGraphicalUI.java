package ui.graphical;

public class FitHubGraphicalUI {
    private AppState currentState;

    public FitHubGraphicalUI() {
        currentState = AppState.SPLASH_SCREEN;
        run();
    }

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

    private void showSplashScreen() {
        SplashScreen splashScreen = new SplashScreen();
        while (splashScreen.isDisplayable()) {
            // LASTS FOR THE DURATION OF THE SPLASH SCREEN
        }
        currentState = AppState.WELCOME_SCREEN;
    }
}
