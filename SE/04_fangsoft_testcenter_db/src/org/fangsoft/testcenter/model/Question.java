package org.fangsoft.testcenter.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private  int id;
    //name String
    //参考正确答案 answer String
    //分数 score int
    //选择题的选项 choiceItem ChoiceItem[]
    private String name;
    private String answer;
    private int score;
//    private ChoiceItem choiceItem[];

    private List<ChoiceItem> choiceItem;

    public Question() {
        this.choiceItem = new ArrayList<ChoiceItem>();
    }

    public void addChoiceItem(ChoiceItem item) {
        this.choiceItem.add(item);
    }

    public ChoiceItem getChoiceItem(int index) {
        return this.choiceItem.get(index);
    }

    public List<ChoiceItem> getChoiceItem() {
        return choiceItem;
    }

    public void setChoiceItem(List<ChoiceItem> choiceItem) {
        this.choiceItem = choiceItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void assignLabel(String[] label) {
        int count = 0;
        StringBuffer rightAnswer = new StringBuffer();
        if (this.choiceItem != null) {
            for (ChoiceItem item : this.getChoiceItem()) {
                item.setLabel(label[count++]);
                if (item.isCorrect()) {
                    rightAnswer.append(label[count - 1]);
                }
            }
        }
        this.setAnswer(rightAnswer.toString());
    }
}
