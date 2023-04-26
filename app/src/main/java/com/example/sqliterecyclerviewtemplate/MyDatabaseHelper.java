package com.example.sqliterecyclerviewtemplate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    // private static final => basically a constant.

    private static final String DATABASE_NAME="BookLibrary.db";
    private static final int DATABASE_VERSION=1;

    //table
    private static final String TABLE_NAME="my_library";

    //columns
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_TITLE="book_title";
    private static final String COLUMN_AUTHOR="book_author";
    private static final String COLUMN_PAGES="book_pages";


    //WELCOME TEXT:
    public static final String WELCOME_TEXT_TABLE="welcome_text_table";
    private static final String WELCOME_TEXT_ID="_id";
    private static final String WELCOME_TEXT="welcome_text";


    // context refers to the activity. (In this case: "AddActivity.this")
    MyDatabaseHelper(@Nullable Context context) {
        // factory is set to null explicitly.
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    // (!) After members and constructor is set we create the query.
    // "sqLiteDatabase" object name changed to "db", which is passed as onCreate argument.
    @Override
    public void onCreate(SQLiteDatabase db) {



        Log.d("addWelcomeMessage","onCreate Active!");
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " " + COLUMN_TITLE + " TEXT," +
                        " " + COLUMN_AUTHOR + " TEXT," +
                        " " + COLUMN_PAGES + " INTEGER" +
                        ");";
        db.execSQL(query);

        //WELCOME TEXT
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

        String query =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);

        String welcome_text_query =
                "DROP TABLE IF EXISTS " + WELCOME_TEXT_TABLE;
        db.execSQL(welcome_text_query);


    }

    void addBook(String title, String author, int pages)
    {
        // create database object "db"
        SQLiteDatabase db = this.getWritableDatabase();
        // create content object for the "db", "cv".
        ContentValues cv = new ContentValues();


        // add the column values into content
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);


        // insert the content into database object and store it into a long type object.
            // to check if everything was correct.
        long result = db.insert(TABLE_NAME, null, cv);


        if(result == -1)
        {
            // context object comes from the constructor. (memorize.)
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Insert Successful", Toast.LENGTH_SHORT).show();
        }
    }



    //insertDefaultWelcomeText

    void insertDefaultWelcomeText(SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WELCOME_TEXT, "DEFAULT WELCOME TEXT.");
        db.insert(WELCOME_TEXT_TABLE, null, contentValues);
    }



    // new Method for to use in recycler_view
    Cursor readAllData_bookTable()
    {
        // query
        String query = "SELECT * FROM " + TABLE_NAME;

        // db object
        SQLiteDatabase db = this.getReadableDatabase();


        // (?) cursor obj.
        Cursor cursor = null;

        // we store all the query inside the cursor object.
        // we should also make sure that it is not null.
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


        // (?) cursor obj.
        Cursor cursor = null;

        // we store all the query inside the cursor object.
        // we should also make sure that it is not null.
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    // part-4
    void updateData(String row_id, String title, String author, String pages)
    {
        // we retrieve out db in Write mode.
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});

        if(result == -1)
        {
            Toast.makeText(context, "Update Failed.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Update Successful.", Toast.LENGTH_SHORT).show();
            Log.d("update_log", "title: " + title);
            Log.d("update_log", "author: " + author);
            Log.d("update_log", "pages: " + pages);
        }
    }


    void updateWelcomeText(String welcome_text)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(WELCOME_TEXT, welcome_text);

        Log.d("hello", welcome_text);

        long result = db.update(WELCOME_TEXT_TABLE, cv, "_id=?", new String[] { "1" });

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

    // WELCOME TEXT UPDATE
//    void updateWelcomeText(String row_id, String welcome_text)
//    {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(WELCOME_TEXT, welcome_text);
//
//        long result = db.update(WELCOME_TEXT_TABLE, cv, "_id=?", new String[]{row_id});
//
//        if(result == -1)
//        {
//            Toast.makeText(context, "Welcome Text Update Failed.", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            Toast.makeText(context, "Welcome Text Update Successful.", Toast.LENGTH_SHORT).show();
//        }
//    }
}
