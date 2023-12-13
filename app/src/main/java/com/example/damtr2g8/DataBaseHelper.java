package com.example.damtr2g8;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mi_base_de_datos";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "usuario";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOM = "nombre";
    public static final String COLUMN_PASS = "pass";
    public static final String COLUMN_CORREU = "correu";
    public static final String COLUMN_COGNOM = "cognom";

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NOM + " TEXT, " +
                    COLUMN_COGNOM + " TEXT, " +
                    COLUMN_PASS + " TEXT, " +
                    COLUMN_CORREU + " TEXT);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

