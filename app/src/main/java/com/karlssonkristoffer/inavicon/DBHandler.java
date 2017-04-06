package com.karlssonkristoffer.inavicon;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageView;

/**
 * Created by Kristoffer on 2017-04-06.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "iconDatabase.db";
    private static final String TABLE_GEOFENCES = "geofenceTable";
    private static final String GEOFENCE_COLUMN_ID = "geofenceID";
    private static final String GEOFENCE_COLUMN_GEOFENCE = "geofenceColumn";
    private static final String GEOFENCE_COLUMN_ICON = "geofenceIcon";

    private static final String TABLE_ICONS = "iconTable";
    private static final String COLUMN_ICON = "iconColumn";
    private static final String COLUMN_ICON_ID = "iconID";



    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 ="CREATE TABLE" + TABLE_GEOFENCES + "(" +
                GEOFENCE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                GEOFENCE_COLUMN_GEOFENCE + " TEXT " +
                GEOFENCE_COLUMN_ICON + " INTEGER " + ");";
        db.execSQL(query1);

        String query2 = "CREATE TABLE " +TABLE_ICONS + "(" +
                COLUMN_ICON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_ICON + " BLOB );";
        db.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ICONS);
        onCreate(db);
    }

    public void addGeoFence(String name, int iconId) {
        ContentValues values = new ContentValues();
        //NOT FINISHED
    }


}