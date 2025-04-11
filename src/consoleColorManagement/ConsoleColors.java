package consoleColorManagement;

public class ConsoleColors {
    // Core text
    public static final ConsoleColor PRIMARY = new ConsoleColor("\u001B[38;2;175;219;255m");  // Soft sky blue
    public static final ConsoleColor SECONDARY = new ConsoleColor("\u001B[38;2;163;190;212m"); // Muted steel
    public static final ConsoleColor NEUTRAL = new ConsoleColor("\u001B[38;2;220;220;220m");   // Off-white
    
    // Accents
    public static final ConsoleColor ACCENT_TEAL = new ConsoleColor("\u001B[38;2;85;205;192m");  // Jewel teal
    public static final ConsoleColor ACCENT_AMBER = new ConsoleColor("\u001B[38;2;255;179;71m"); // Warm amber
    public static final ConsoleColor ACCENT_MAUVE = new ConsoleColor("\u001B[38;2;183;147;211m"); // Soft purple
    
    // Status
    public static final ConsoleColor STATUS_SUCCESS = new ConsoleColor("\u001B[38;2;123;201;111m");  // Sage green
    public static final ConsoleColor STATUS_WARNING = new ConsoleColor("\u001B[38;2;255;213;97m");   // Golden yellow
    public static final ConsoleColor STATUS_ERROR = new ConsoleColor("\u001B[38;2;230;103;98m");     // Terracotta red
    
    // Special
    public static final ConsoleColor HIGHLIGHT = new ConsoleColor("\u001B[38;2;255;203;139m");    // Peach glow
    public static final ConsoleColor DIM = new ConsoleColor("\u001B[38;2;140;140;140m");          // Grayed text
    public static final ConsoleColor RESET = new ConsoleColor("\u001B[0m");

    private ConsoleColors() {}
}
