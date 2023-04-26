package com.example.sqliterecyclerviewtemplate;

public class Question {
    private String m_question;
    private String m_answerA;
    private String m_answerB;
    private String m_answerC;
    private String m_answerD;
    private String m_correctAnswer;

    public Question(String questionText, String answerA, String answerB, String answerC, String answerD, String correctAnswer) {
        this.m_question = questionText;
        this.m_answerA = answerA;
        this.m_answerB = answerB;
        this.m_answerC = answerC;
        this.m_answerD = answerD;
        this.m_correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return m_question;
    }

    public void setQuestionText(String questionText) {
        this.m_question = questionText;
    }

    public String getAnswerA() {
        return m_answerA;
    }

    public void setAnswerA(String answerA) {
        this.m_answerA = answerA;
    }

    public String getAnswerB() {
        return m_answerB;
    }

    public void setAnswerB(String answerB) {
        this.m_answerB = answerB;
    }

    public String getAnswerC() {
        return m_answerC;
    }

    public void setAnswerC(String answerC) {
        this.m_answerC = answerC;
    }

    public String getAnswerD() {
        return m_answerD;
    }

    public void setAnswerD(String answerD) {
        this.m_answerD = answerD;
    }

    public String getCorrectAnswer() {
        return m_correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.m_correctAnswer = correctAnswer;
    }
}