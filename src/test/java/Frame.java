//package thoughtworks.src.main.java;

public class Frame{
    public int[] score = new int[] {-1, -1};
    public int status = 0;
    
    public Frame(String str) {
        setState(str);
        setScore(str);
    }
        
    // public Frame(int[] score, int status) {
    //     this.score = score;
    //     this.status = status;
    // }
    
    private void setState(String str) {
        if (isStrike(str)) {
            status = 2;
        } else if (isSpare(str)) {
            status = 1;
        } else {
            status = 0;
        }
    }
    
    private void setScore(String str) {
        if (str == null || str.length() == 0) {
            return;
        } else if (str.length() == 1) {
            score[0] = getOneCharScore(str.charAt(0));
        } else if (str.length() == 2) {
            getTwoCharScore(str.toCharArray());
        } else {
            System.out.println("Wrong!");
        }
    }
    
    private int getOneCharScore(char c) {
        if (c == 'X') {
            return 10;
        }
        if (c - '0' >=0 && c - '9' <= 0) {
            return c - '0';
        }
        return 0;
    }
    
    private int[] getTwoCharScore(char[] chars) {
        score[0] = getOneCharScore(chars[0]);
        if (status == 1) {
            score[1] = 10 - getOneCharScore(chars[0]);
        } else{
            score[1] = getOneCharScore(chars[1]);
        } 
        return score;
    }
    
    private boolean isStrike(String frame) {
        return frame.charAt(0) == 'X';
    }
    
    private boolean isSpare(String frame) {
        return frame.charAt(frame.length() - 1) == '/';
    }
    
    private boolean isNorm(String frame) {
        return !isStrike(frame) && !isSpare(frame);
    }
}
