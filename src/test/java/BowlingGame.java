//package thoughtworks.src.main.java;

//import Frame;

import java.util.*;

public class BowlingGame {
    /**
     * 
     */
    private ArrayList<Frame> frames = new ArrayList<Frame>();
    private int score = 0;
    
    public int getBowlingScore(String str) {
        int start = 0;
        int count = 0;
        // first ten frame
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '|') {
                frames.add(new Frame(str.substring(start, i)));
                start = i + 1;
                count++;
            } 
            if (count == 10) {
                break;
            }
        } 
        start++;
        // last frame
        if (start < str.length()) {
            String strend = str.substring(start, str.length());
            frames.add(new Frame(strend));
        }
        getBowlingScoreFirst();
        getBowlingScoreSecond();
        return score;
    }
        
    private void getBowlingScoreFirst() {
        for (int i = 0; i < 10; i++) {
            int[] frameScore = frames.get(i).score;
            for (int j = 0; j < 2; j++) {
                if (frameScore[j] > 0) 
                    score += frameScore[j];
            }
            // System.out.println(score);
        }
    }
    
    private void getBowlingScoreSecond() {
        for (int i = 0; i < frames.size(); i++) {
            int status = frames.get(i).status;
            if (status >= 1 && i + 1 < frames.size()) {
                Frame next = frames.get(i + 1);
                score += next.score[0];
            }
            if (status == 2 && i + 1 < frames.size()) {
                Frame next = frames.get(i + 1);
                if (next.score[1] != -1) {
                    score += next.score[1];
                } else if (i + 2 < frames.size()) {
                    score += frames.get(i + 2).score[0];
                }
            }
            // System.out.println(score);            
        }
    }    
       
    // public static void main(String args[]) {
    //     Scanner in = new Scanner(System.in);
    //     String str = in.nextLine();
    //     System.out.println(getBowlingScore(str));
        // for (int i = 0; i < frames.size(); i++) {
        //     System.out.println(i + "  " + frames.get(i).status);
        // }
    // }
}
