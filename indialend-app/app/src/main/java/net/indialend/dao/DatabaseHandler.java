package net.indialend.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.indialend.bean.User;

/**
 * Created by jaspreetsingh on 5/10/16.
 */
public class DatabaseHandler  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "indialend";

    // Contacts table name
    private static final String TABLE_USER = "user";

    // Contacts Table Columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_GENDER = "gender";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "( "
                + KEY_NAME + " TEXT, "
                + KEY_PHONE + " TEXT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_GENDER + " TEXT " + ")";
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

    public void addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName()); // Contact Name
        values.put(KEY_PHONE, user.getPhone()); // Contact Phone Number
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_GENDER, user.getGender());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }


    public User getUser() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  "+KEY_NAME+","
                +KEY_EMAIL+","
                +KEY_PHONE+","
                + KEY_GENDER
                +"  FROM "
                + TABLE_USER;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {


            User user = new User();
            user.setName(cursor.getString(0));
            user.setEmail(cursor.getString(1));
            user.setPhone(cursor.getString(2));
            user.setGender(cursor.getString(3));

            return user;
        }

        // return user
        return null;
    }
}
