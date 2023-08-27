package com.example.aptify_login;


public class Score {
    private int id;
    private int score;
    private String date;
    private String time;
    private double accuracy;

    private String quizcatagory;

    public Score() {

    }

    public Score(int scoreVal, double accuracy, String date,String quizcatagory) {
        setId(id);
        setScore(scoreVal);
        setDate(date);
        setAccuracy(accuracy);
        setQuizcatagory(quizcatagory);
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public String getTime() { return time; }

    public double getAccuracy() { return accuracy; }

    public String getQuizcatagory(){return quizcatagory;}


    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) { this.time = time; }

    public void setAccuracy(double accuracy) { this.accuracy = accuracy; }

    public void setQuizcatagory(String quizcatagory){this.quizcatagory = quizcatagory;}

}
