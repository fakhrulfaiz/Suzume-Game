/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.data;

import java.io.Serializable;

/**
 *
 * @author User
 */

    public class LeaderboardEntry implements Serializable {
    private String name;
    private int score;
    private int win;
    private int draw;
    private int lose;
    private String timeSaved;
    private String difficulty;
    public LeaderboardEntry(String name, int score, int win, int draw, int lose, String timeSaved) {
        this.name = name;
        this.score = score;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.timeSaved = timeSaved;
    }
    public LeaderboardEntry(String name, int score, int win, int lose, String difficulty) {
        this.name = name;
        this.score = score;
        this.win = win;
        this.lose = lose;
        this.difficulty = difficulty;
    }

    public String getTimeSaved() {
        return timeSaved;
    }
    public String getDifficulty() {
        return difficulty;
    }
        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }
}

