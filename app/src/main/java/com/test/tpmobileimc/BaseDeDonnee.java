package com.test.tpmobileimc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BaseDeDonnee extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bddTpMobile.db";
    private static final int DATABASE_VERSION = 1;

    public BaseDeDonnee (Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql =  "create table information (" +
                "   id integer primary key autoincrement," +
                "   nom text not null," +
                "   prenom text not null," +
                "   IMC text not null" +
                "   );";

        db.execSQL(strSql);

        Log.i("DATABASE","Creation en cours");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSql =  "drop table if exists 'information'";
        db.execSQL(strSql);

        strSql =  "create table information (" +
                "   id integer primary key autoincrement," +
                "   nom text not null," +
                "   prenom text not null," +
                "   IMC text not null" +
                "   );";

        db.execSQL(strSql);
    }

    public void addTache(String nom, String prenom, String IMC){
        String strSql = "insert into information (nom, prenom, IMC) values ('" +
                nom + "', '" + prenom + "','" + IMC + "')";
        this.getWritableDatabase().execSQL(strSql);
    }

    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from information";

        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

}
