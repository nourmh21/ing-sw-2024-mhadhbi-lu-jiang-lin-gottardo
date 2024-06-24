package it.polimi.ingsw.view.tui;

/**
 * Definition of colors with ANSI codes
 */
public enum AnsiColor {
    RESET("\u001B[0m"),
    BLACK("\u001B[38;5;0m"),
    RED("\u001B[38;5;1m"),
    GREEN("\u001B[38;5;2m"),
    BLUE("\u001B[38;5;4m"),
    MAGENTA("\u001B[38;5;5m"),
    WHITE("\u001B[38;5;7m"),
    BRIGHT_YELLOW("\u001B[38;5;11m"),
    RED_BACKGROUND("\u001B[41m"),
    GREEN_BACKGROUND("\u001B[42m"),
    YELLOW_BACKGROUND("\u001B[43m"),
    BLUE_BACKGROUND("\u001B[44m"),
    MAGENTA_BACKGROUND("\u001B[45m");

    private final String code;

    AnsiColor(String code) {
        this.code = code;
    }



    public String getCode() {
        return this.code;
    }
}
