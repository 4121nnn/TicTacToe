package com.example.tictactoe;

public class Constants {
    public static final int LENGTH = 9;
    public static final int TOP = 20, BOTTOM = 280, START= 50, MIDDLE = 150, END = 250 ;
    public static  int[][] WIN_LINE ;
    static {
        WIN_LINE = new int[][] {
                {TOP, TOP, BOTTOM, BOTTOM},
                {BOTTOM, TOP, TOP, BOTTOM},
                {START, TOP, START, BOTTOM},
                {MIDDLE, TOP, MIDDLE, BOTTOM},
                {END, TOP, END, BOTTOM},
                {TOP, START, BOTTOM, START},
                {TOP, MIDDLE, BOTTOM, MIDDLE},
                {TOP, END, BOTTOM, END}
        };
    }
}
