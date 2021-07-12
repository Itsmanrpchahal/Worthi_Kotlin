package io.worthi.submitQualifier.model;

public class qAndA {

    String question_id;
    String answer;

    public qAndA(String question_id, String answer) {
        this.question_id = question_id;
        this.answer = answer;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
