package colorUtils;

public class ColorUtil {
    // Reset code
    public static final String RESET = "\033[0m";
    
    // Text styles
    public static final String BOLD = "\033[1m";
    public static final String DIM = "\033[2m";
    public static final String ITALIC = "\033[3m";
    public static final String UNDERLINE = "\033[4m";

    // Base colors
    public static final String BLACK = "\033[0;30m";
    public static final String OFF_WHITE = "\033[38;2;224;224;224m";
    private static final String BRIGHT_CYAN = "\u001B[1;36m";
    private static final String BRIGHT_WHITE = "\u001B[1;97m";
    
    public static final String MINT_GREEN = "\033[38;2;165;214;167m";
    public static final String EMERALD_GREEN = "\033[38;2;102;187;106m";
    public static final String CORAL_RED = "\033[38;2;239;83;80m";
    public static final String SKY_BLUE = "\033[38;2;79;195;247m";
    public static final String AMBER_YELLOW = "\033[38;2;255;202;40m";
    public static final String SLATE_BLUE = "\033[38;2;92;107;192m";
    public static final String LAVENDER_BLUE = "\033[38;2;121;134;203m";
    public static final String LIGHT_GRAY = "\033[38;2;176;190;197m";
    public static final String DARK_GRAY = "\033[38;2;120;144;156m";
    public static final String MUTED_TEAL = "\033[38;2;77;182;172m";
    public static final String MAGENTA = "\033[0;35m";
    public static final String BOLD_BRIGHT_WHITE = "\033[1;97m";

    // Custom combinations
    private static final String HEADER_COLOR = BOLD + BRIGHT_CYAN;
    private static final String SUBHEADER_COLOR = BOLD + MUTED_TEAL;
    private static final String BANNER_COLOR = BOLD + LAVENDER_BLUE;
    private static final String BORDER_COLOR = BRIGHT_CYAN;
    private static final String OPTION_COLOR = BRIGHT_WHITE;
    private static final String NOTE_COLOR = MINT_GREEN;
    private static final String HIGHLIGHT_COLOR = BOLD + AMBER_YELLOW;
    private static final String DEBUG_COLOR = MAGENTA;
    private static final String DIM_TEXT = DIM + DARK_GRAY;

    // Apply color method
    public static String applyColor(String color, String text) {
        return color + text + RESET;
    }

    public static String applyBorder (String text)
    {
        return BORDER_COLOR + text + RESET;
    }

    // Status messages
    public static String applySuccess(String text) {
        return applyColor(EMERALD_GREEN, text);
    }

    public static String applyBoldBrightWhite(String text) {
        return BOLD_BRIGHT_WHITE + text + RESET;
    }

    public static String applyError(String text) {
        return applyColor(CORAL_RED, text);
    }

    public static String applyWarning(String text) {
        return applyColor(AMBER_YELLOW, text);
    }

    // Structural elements
    public static String applyHeader(String text) {
        return applyColor(HEADER_COLOR, text);
    }

    public static String applySubHeader(String text) {
        return applyColor(SUBHEADER_COLOR, text);
    }

    public static String applyBanner(String text) {
        return applyColor(BANNER_COLOR, text);
    }

    // Interactive elements
    public static String applyOption(String text) {
        return applyColor(OPTION_COLOR, text);
    }

    public static String applyHighlight(String text) {
        return applyColor(HIGHLIGHT_COLOR, text);
    }

    // Informational elements
    public static String applyInformation(String text) {
        return applyColor(MAGENTA, text);
    }

    public static String applyNote(String text) {
        return applyColor(NOTE_COLOR, text);
    }

    // Secondary text
    public static String applyDim(String text) {
        return applyColor(DIM_TEXT, text);
    }

    public static String applyDebug(String text) {
        return applyColor(DEBUG_COLOR, text);
    }

    // Special formats
    public static String applyUnderline(String text) {
        return UNDERLINE + text + RESET;
    }

    public static String applyBold(String text) {
        return BOLD + text + RESET;
    }

    // Additional statuses
    public static String applyCaution(String text) {
        return applyColor(DIM + AMBER_YELLOW, text);
    }

    public static String applyProgress(String text) {
        return applyColor(LAVENDER_BLUE, text);
    }

    // Text formatting combinations
    public static String applyImportantNote(String text) {
        return applyColor(BOLD + BRIGHT_CYAN + UNDERLINE, text);
    }
}
