package feature.sleep;

import colorUtils.ColorUtil;

public class DefaultProgressBarRenderer implements iProgressBarRenderer {
    private static final String COMPLETED_SEGMENT = "█";  
    private static final String CURRENT_SEGMENT = "▌";   
    private static final String REMAINING_SEGMENT = "░"; 
    
    @Override
    public String renderProgressBar(double avg, double target, int length) {
        double percentage = Math.min(avg / target, 1.0);
        double exactFilled = percentage * length;
        int fullBlocks = (int) exactFilled;
        double partialBlock = exactFilled - fullBlocks;
        
        StringBuilder bar = new StringBuilder();
        
        bar.append(ColorUtil.applyBorder("["));
        
        bar.append(ColorUtil.applySuccess(COMPLETED_SEGMENT.repeat(fullBlocks)));
        
        if (partialBlock > 0.3) {  
            bar.append(ColorUtil.applyHighlight(CURRENT_SEGMENT));
            fullBlocks++;  
        }
        
        int remaining = length - fullBlocks;
        if (remaining > 0) {
            bar.append(ColorUtil.applyDim(REMAINING_SEGMENT.repeat(remaining)));
        }
        
        bar.append(ColorUtil.applyBorder("]"));
        
        bar.append(String.format(" %s%.0f%%%s", 
            ColorUtil.BOLD, 
            percentage * 100, 
            ColorUtil.RESET));
        
        return bar.toString();
    }
}
