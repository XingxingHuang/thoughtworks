//package src;

import main.java.*;

public class test {
    public void test() {
        System.out.println("yes");
    }
    public static void main(String[] args) {
         String bowlingCode = "X|X|X|X|X|X|X|X|X|X||XX";  
         int res = new BowlingGame().getBowlingScore(bowlingCode);
         System.out.println(res);       
         System.out.println(bowlingCode);       
    }
}
