package com.example.sqliterecyclerviewtemplate;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private String m_title;
    private ArrayList<Question> m_questions;

    public Quiz(String quizTitle) {
        this.m_title = quizTitle;
        this.m_questions = new ArrayList<>();
    }


    public String getTitle() {
        return m_title;
    }

    public void setTitle(String quizTitle) {
        this.m_title = quizTitle;
    }

    public ArrayList<Question> getQuestions() {
        return m_questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.m_questions = questions;
    }

    public void addQuestion(Question question) {
        m_questions.add(question);
    }
}
