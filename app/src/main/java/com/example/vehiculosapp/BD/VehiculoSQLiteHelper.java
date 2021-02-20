package com.example.vehiculosapp.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VehiculoSQLiteHelper extends SQLiteOpenHelper {

    final String SQLCREATE ="CREATE TABLE vehiculos ( matricula TEXT,marca TEXT,modelo TEXT)";

    public VehiculoSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLCREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS vehiculos");
        this.onCreate(db);
    }
}
