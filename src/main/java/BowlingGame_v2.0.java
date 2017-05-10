import java.util.*;

public class BowlingGame {
    /**
     * 
     */
    private static final int NUMS_FRAMES = 10;
    private static String[] frames = new String[NUMS_FRAMES + 1];
    private static int[] scores = new int[NUMS_FRAMES + 1];
    private static int[] states = new int[NUMS_FRAMES + 1];
    private static int[] factors = new int[NUMS_FRAMES + 1];
    private static int total = 0;
    private static int factor = 1;
    
    private static int getBowlingScore(String str) {
        getFrame(str);
        for (int i = 0; i < NUMS_FRAMES; i++) {
            getScore(frames[i], i);
        }
        total += getLastScore(frames[NUMS_FRAMES]);
        return total;
    }
    private static int getLastScore(String frame) {
        if (frame == null) {
            return 0;
        }
        if (frame.length() == 1) {
            return getCharScore(frame.charAt(0));
        }
        if (frame.length() == 2) {
            if (frame.charAt(1) == '-') {
                return 10;
            }
            return getCharScore(frame.charAt(0)) + getCharScore(frame.charAt(1));
        }
        return -1;
    }
    
    private static void getScore(String frame, int num) {
        int score = 0;
        if (isStrike(frame)) {
            score = 10;
            if (num - 1 >= 0 && !isNorm(frames[num - 1])) score += getCharScore(frame.charAt(0));
            if (num - 2 >= 0 && isStrike(frames[num - 2])) score += getCharScore(frame.charAt(0));            
            states[num] = 2;
        } else if (isSpare(frame)) {
            score += getCharScore(frame.charAt(0));
            if (num - 1 >= 0 && !isNorm(frames[num - 1])) score += getCharScore(frame.charAt(0));
            if (num - 2 >= 0 && isStrike(frames[num - 2])) score += getCharScore(frame.charAt(0));  
            score += 10 - getCharScore(frame.charAt(0));
            if (num - 2 >= 0 && isStrike(frames[num - 2])) score += 10 - getCharScore(frame.charAt(0));  
            states[num] = 1;
        } else {
            score += getCharScore(frame.charAt(0));
            if (num - 1 >= 0 && !isNorm(frames[num - 1])) score += getCharScore(frame.charAt(0));
            if (num - 2 >= 0 && isStrike(frames[num - 2])) score += getCharScore(frame.charAt(0));  
            if (frame.length() > 1) {
                score += getCharScore(frame.charAt(1));
                if (num - 2 >=0 && isStrike(frames[num - 2])) score += getCharScore(frame.charAt(1)); 
            }
            states[num] = 0;
        }
        // if (num - 1 >=0 && states[num - 1] == 1) {
        //     factor--;
        // }
        // if (num - 2 >= 0 && states[num - 2] == 2) {
        //     factor--;
        // }
        scores[num] = score;
        factors[num] = factor;
        total += score;
    }

    private static int getCharScore(char c) {
        if (c == 'X') {
            return 10;
        }
        if (c - '0' >=0 && c - '9' <= 0) {
            return c - '0';
        }
        return -1;
    }
    
    // private static int getFrameScore(String frame) {
    //     if (frames == null) {
    //         return 0;
    //     }
    //     char[] chars = frame.toCharArray();
    //     int count = 0;
    //     for (char c: chars) {
    //         if (c - '0' >=0 && c - '9' <= 0) {
    //             count += c - '0';
    //         }
    //     }
    //     return count;
    // }
    
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
    
    private static boolean isNorm(String frame) {
        return !isStrike(frame) && !isSpare(frame);
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
        System.out.println("Here is the factors: ");
        System.out.println(Arrays.toString(factors));
    }
}