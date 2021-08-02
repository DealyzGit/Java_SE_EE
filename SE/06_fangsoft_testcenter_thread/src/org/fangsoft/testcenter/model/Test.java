package org.fangsoft.testcenter.model;

import java.util.ArrayList;

public class Test {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private  int id;
    private  String name;
    private String description ;
    private int score;

    public void setQuestion(ArrayList<Question> question) {
        this.question = question;
    }

    private  ArrayList<Question> question;
    private int timeLimitMin;
    private int numQuestion;
    
    public Test(int numQuestion){
    this.numQuestion=numQuestion;
    question=new ArrayList<>(numQuestion);
    }
    public Test(){
    this(3);
    }
    private int count=0;
    public void addQuestion(Question q){
        question.add(q);
     }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Question> getQuestion() {
        return question;
    }

    public int getTimeLimitMin() {
        return timeLimitMin;
    }

    public void setTimeLimitMin(int timeLimitMin) {
        this.timeLimitMin = timeLimitMin;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
