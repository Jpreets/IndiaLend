package net.indialend.attendence.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.indialend.attendence.bean.User;

/**
 * Created by jaspreetsingh on 5/10/16.
 */
public class DatabaseHandler  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "attendence";

    // Contacts table name
    private static final String TABLE_USER = "user";

    // Contacts Table Columns names
    private static final String KEY_STAFF_ID = "userId";
    private static final String KEY_ATTENDENCE_ID = "attendenceId";
    private static final String GCM_TOKEN = "gcm_token";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "( "
                + KEY_STAFF_ID + " TEXT, "
                + KEY_ATTENDENCE_ID + " TEXT, "
                + GCM_TOKEN + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);



    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Create tables again
        onCreate(db);
    }

    public void deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER," 1=1",null);
        db.close();
    }

    public void addUser(User user) {

        deleteUser();

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STAFF_ID, user.getStaffId()); // Contact Name
        values.put(KEY_ATTENDENCE_ID,user.getAttendenceId());
        values.put(GCM_TOKEN, user.getGcmToken());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }






    public User getUser() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  "+KEY_STAFF_ID+","
                +KEY_ATTENDENCE_ID+","
                + GCM_TOKEN
                +"  FROM "
                + TABLE_USER;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {

            User user = new User();
            user.setStaffId(cursor.getString(0));
            user.setAttendenceId(cursor.getString(1));
            user.setGcmToken(cursor.getString(4));

            db.close();
            return user;
        }
        db.close();
        // return user
        return null;
    }
}
