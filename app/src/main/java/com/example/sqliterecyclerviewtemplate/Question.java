package com.example.sqliterecyclerviewtemplate;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private String question;
    private String answer_a;
    private String answer_b;
    private String answer_c;
    private String answer_d;
    private String correct_answer;

    private String selected_answer;

    // @constructor
    //
    public Question(String question, String answer_a, String answer_b, String answer_c, String answer_d, String correct_answer) {
        this.question = question;
        this.answer_a = answer_a;
        this.answer_b = answer_b;
        this.answer_c = answer_c;
        this.answer_d = answer_d;
        this.correct_answer = correct_answer;
    }

    protected Question(Parcel in) {
        question = in.readString();
        answer_a = in.readString();
        answer_b = in.readString();
        answer_c = in.readString();
        answer_d = in.readString();
        correct_answer = in.readString();
        selected_answer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer_a);
        dest.writeString(answer_b);
        dest.writeString(answer_c);
        dest.writeString(answer_d);
        dest.writeString(correct_answer);
        dest.writeString(selected_answer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

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

    public String getCorrectAnswer() {
        return correct_answer;
    }

    public void setCorrectAnswer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getSelectedAnswer() {
        return selected_answer;
    }

    public void setSelectedAnswer(String selected_answer) {
        this.selected_answer = selected_answer;
    }
}

