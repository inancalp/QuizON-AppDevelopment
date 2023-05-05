package com.example.sqliterecyclerviewtemplate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME="QuizON.db";
    private static final int DATABASE_VERSION=1;

    private static final String QUIZ_TABLE_NAME="quizzes_table";
    private static final String QUIZ_ID="quiz_id";
    private static final String QUIZ_TITLE="quiz_title";


    private static final String QUESTIONS_TABLE_NAME="questions_table";
    private static final String QUESTION_ID="question_id";
    private static final String QUESTION="question";
    private static final String ANSWER_A="answer_a";
    private static final String ANSWER_B="answer_b";
    private static final String ANSWER_C="answer_c";
    private static final String ANSWER_D="answer_d";
    private static final String CORRECT_ANSWER="correct_answer";
    public static final String WELCOME_TEXT_TABLE="welcome_text_table";
    private static final String WELCOME_TEXT_ID="welcome_text_id";
    private static final String WELCOME_TEXT="welcome_text";


    DatabaseHelper(@Nullable Context context) {
        // factory is set to null explicitly.
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    // (!) After members and constructor is set we create the query.
    // "sqLiteDatabase" object name changed to "db", which is passed as onCreate argument.
    @Override
    public void onCreate(SQLiteDatabase db) {


        Log.d("db_oncreate", "END");
        String quiz_query =
                "CREATE TABLE " + QUIZ_TABLE_NAME +
                        " (" + QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " " + QUIZ_TITLE + " TEXT" +
                        ");";

        db.execSQL(quiz_query);


        String questions_query =
                "CREATE TABLE " + QUESTIONS_TABLE_NAME +
                        " (" + QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " " + QUIZ_ID + " INTEGER," +
                        " " + QUESTION + " TEXT," +
                        " " + ANSWER_A + " TEXT," +
                        " " + ANSWER_B + " TEXT," +
                        " " + ANSWER_C + " TEXT," +
                        " " + ANSWER_D + " TEXT," +
                        " " + CORRECT_ANSWER + " TEXT," +
                        " FOREIGN KEY (" + QUIZ_ID + ") REFERENCES " + QUIZ_TABLE_NAME + " (" + QUIZ_ID + ")" +
                        ");";
        db.execSQL(questions_query);


        String welcome_text_query =
                "CREATE TABLE " + WELCOME_TEXT_TABLE +
                        " (" + WELCOME_TEXT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " " + WELCOME_TEXT + " TEXT" +
                        ");";

        db.execSQL(welcome_text_query);
        insertDefaultWelcomeText(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String quiz_query =
                "DROP TABLE IF EXISTS " + QUIZ_TABLE_NAME;
        db.execSQL(quiz_query);

        String question_query =
                "DROP TABLE IF EXISTS " + QUESTIONS_TABLE_NAME;
        db.execSQL(question_query);

        String welcome_text_query =
                "DROP TABLE IF EXISTS " + WELCOME_TEXT_TABLE;
        db.execSQL(welcome_text_query);

    }

    long addQuiz(String title)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(QUIZ_TITLE, title);

        long quiz_id = db.insert(QUIZ_TABLE_NAME, null, cv);

        if(quiz_id == -1)
        {
            // context object comes from the constructor. (memorize.)
            Toast.makeText(context, "QUIZ_TABLE Insert Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "QUIZ_TABLE Insert Successful", Toast.LENGTH_SHORT).show();

        }

        return quiz_id;
    }



    void addQuestions(ArrayList<Question> questions, long quiz_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        for(Question question : questions)
        {
            ContentValues cv = new ContentValues();
            cv.put(QUIZ_ID, quiz_id);
            cv.put(QUESTION, question.getQuestion());
            cv.put(ANSWER_A, question.getAnswerA());
            cv.put(ANSWER_B, question.getAnswerB());
            cv.put(ANSWER_C, question.getAnswerC());
            cv.put(ANSWER_D, question.getAnswerD());
            cv.put(CORRECT_ANSWER, question.getCorrectAnswer());
            long result = db.insert(QUESTIONS_TABLE_NAME, null, cv);

            if(result == -1)
            {
                // context object comes from the constructor. (memorize.)
                Toast.makeText(context, "QUESTION_TABLE Insert Failed", Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {
                Toast.makeText(context, "QUESTION_TABLE Insert Successful", Toast.LENGTH_SHORT).show();
            }
        }

    }

    void insertDefaultWelcomeText(SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WELCOME_TEXT, "DEFAULT WELCOME TEXT.");
        db.insert(WELCOME_TEXT_TABLE, null, contentValues);
    }


    // new Method for to use in recycler_view
    Cursor readAllData_quizTable()
    {
        String query = "SELECT * FROM " + QUIZ_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }


    Cursor readData_questionsTable(long quiz_id)
    {
        String query = "SELECT * FROM " + QUESTIONS_TABLE_NAME
                + " WHERE " + QUIZ_ID + " == " + quiz_id;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
    Cursor readAllData_welcomeText()
    {

        // query
        String query = "SELECT * FROM " + WELCOME_TEXT_TABLE;

        // db object
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    // part-4
    void updateQuizData(String row_id, String title)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(QUIZ_TITLE, title);

        long result = db.update(QUIZ_TABLE_NAME, cv, "quiz_id=?", new String[]{row_id});

        if(result == -1)
        {
            Toast.makeText(context, "QUIZ_TABLE Update Failed.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "QUIZ_TABLE Update Successful.", Toast.LENGTH_SHORT).show();
            Log.d("QUIZ_TABLE_log", "title: " + title);
        }
    }


    void updateWelcomeText(String welcome_text)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(WELCOME_TEXT, welcome_text);

        Log.d("hello", welcome_text);

        long result = db.update(WELCOME_TEXT_TABLE, cv, "welcome_text_id=?", new String[] { "1" });

        if(result == -1)
        {
            Toast.makeText(context, "Update Failed.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Update Successful.", Toast.LENGTH_SHORT).show();
            Log.d("update_log", "welcome_text: " + welcome_text);
        }
    }


    public void deleteQuiz(String quiz_id) {

        Log.d("deleteQuiz", "You have reached to the deleteQuiz Method, quiz_id: " + quiz_id);
        SQLiteDatabase db = getWritableDatabase();
        db.delete(QUESTIONS_TABLE_NAME, QUIZ_ID + " = ?", new String[] { quiz_id });
        db.delete(QUIZ_TABLE_NAME, QUIZ_ID + " = ?", new String[] { quiz_id });
    }

    public long assignQuizId() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MAX(" + QUIZ_ID + ") FROM " + QUIZ_TABLE_NAME;
        int max_quiz_id = 0;
        Cursor cursor = db.rawQuery(query, null);

        //since max_quiz_id is already 0 as default, no need to put an else block within the statement below
        if (cursor.moveToFirst()) {
            max_quiz_id = cursor.getInt(0); //find quiz_id column index first.(refer quizTitleInUse() method)
        }
        cursor.close();
        db.close();
        return (max_quiz_id + 1);
    }

    public boolean quizTitleInUse(String quiz_title) {
        String existing_quiz_title;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + QUIZ_TITLE + " FROM " + QUIZ_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        //Good practice to make sure the index number for a specific column.
        Integer quiz_title_index = cursor.getColumnIndex(QUIZ_TITLE);

        while(cursor.moveToNext())
        {
            existing_quiz_title = cursor.getString(quiz_title_index);
            if(existing_quiz_title.equals(quiz_title))
            {
                return true;
            }
        }
        return false;
    }
}
