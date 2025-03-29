package feature.sleep;

public class DefaultProgressBarRenderer implements iProgressBarRenderer {
    @Override
    public String renderProgressBar(double avg, double target, int length) {
        int filled = (int) Math.round((avg / target) * length);
        if (filled > length) {
            filled = length;
        }
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < filled; i++) {
            bar.append("#");
        }
        for (int i = filled; i < length; i++) {
            bar.append("-");
        }
        bar.append("]");
        return bar.toString();
    }
}