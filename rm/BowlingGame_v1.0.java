import java.util.*;

public class BowlingGame {
    /**
     * 
     */
    private static final int NUMS_FRAMES = 10;
    private static String[] frames = new String[NUMS_FRAMES + 1];
    private static int[] scores = new int[NUMS_FRAMES + 1];
    private static int[] states = new int[NUMS_FRAMES + 1];
    private static int total = 0;
    
    private static int getBowlingScore(String str) {
        getFrame(str);
        for (int i = 0; i < NUMS_FRAMES; i++) {
            getScore(frames[i], i);
        }
        // first frames.
        int factor = 1;
        for (int i = 0; i < NUMS_FRAMES; i++) {
            total += scores[i] * factor;
            factor += states[i];
            if (i - 1 >= 0 && states[i - 1] == 1) {
                factor--;
            }
            if (i - 2 >= 0 && states[i - 2] == 2) {
                factor--;
            }
        }
        // last frame
        if (frames[NUMS_FRAMES].length() == 1) 
            total += getFrameScore(frames[NUMS_FRAMES]);
        if (frames[NUMS_FRAMES].length() == 2)
            total += getFrameScore(frames[NUMS_FRAMES].substring(0, 1));
            //total += getFrameScore(frames[NUMS_FRAMES].substring(1, 2));
        return total;
    }
    
    private static void getScore(String frame, int num) {
        if (isStrike(frame)) {
            scores[num] = 10;
            states[num] = 2;
        } else if (isSpare(frame)) {
            scores[num] = 10;
            states[num] = 1;
        } else {
            scores[num] = getFrameScore(frame);
            states[num] = 0;
        }
    }
    
    private static int getFrameScore(String frame) {
        if (frames == null) {
            return 0;
        }
        char[] chars = frame.toCharArray();
        int count = 0;
        for (char c: chars) {
            if (c - '0' >=0 && c - '9' <= 0) {
                count += c - '0';
            }
        }
        return count;
    }
    
    private static void getFrame(String str) {
        int start = 0;
        int count = 0;
        // add first 10 frame
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '|') {
                frames[count++] = str.substring(start, i);
                start = i + 1;
                if (count >= NUMS_FRAMES) break;
            }
        }
        // add last frame
        frames[count] = str.substring(++start, str.length());    
    }
    
    private static boolean isStrike(String frame) {
        return frame.charAt(0) == 'X';
    }
    
    private static boolean isSpare(String frame) {
        return frame.charAt(frame.length() - 1) == '/';
    }
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(getBowlingScore(str));
        System.out.println("Here is the input: ");
        System.out.println(Arrays.toString(frames));
        System.out.println("Here is the scores: ");
        System.out.println(Arrays.toString(scores));
        System.out.println("Here is the states: ");
        System.out.println(Arrays.toString(states));
    }
}