package com.example.sqliterecyclerviewtemplate;

public class Question {
    private String question;
    private String answer_a;
    private String answer_b;
    private String answer_c;
    private String answer_d;
//    private String correct_answer;

    // @constructor
    //  String correct_answer
    public Question(String question, String answer_a, String answer_b, String answer_c, String answer_d) {
        this.question = question;
        this.answer_a = answer_a;
        this.answer_b = answer_b;
        this.answer_c = answer_c;
        this.answer_d = answer_d;
//        this.correct_answer = correct_answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answer_a;
    }

    public void setAnswerA(String answer_a) {
        this.answer_a = answer_a;
    }

    public String getAnswerB() {
        return answer_b;
    }

    public void setAnswerB(String answer_b) {
        this.answer_b = answer_b;
    }

    public String getAnswerC() {
        return answer_c;
    }

    public void setAnswerC(String answer_c) {
        this.answer_c = answer_c;
    }

    public String getAnswerD() {
        return answer_d;
    }

    public void setAnswerD(String answer_d) {
        this.answer_d = answer_d;
    }

//    public String getCorrectAnswer() {
//        return correct_answer;
//    }
//
//    public void setCorrectAnswer(String correct_answer) {
//        this.correct_answer = correct_answer;
//    }
}

