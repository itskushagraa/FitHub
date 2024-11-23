package ui.graphical;

/*
 * Represents the App State during startup before Main Menu is reached
 * - Splash Screen: 2 second screen shown while the main app loads
 * - Welcome Screen: Create/Load Profile or Exit
 * - Default: Default state for when users move past the welcome screen.
 */
public enum AppState {
    SPLASH_SCREEN,
    WELCOME_SCREEN,
    DEFAULT
}
