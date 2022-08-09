package dresden.demo.foodie.history;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import dresden.demo.foodie.history.HistoryModel;

public class HistoryDBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "history";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "history";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    private static final String TIME_COL = "time";

    private static final String SOURCE_COL = "source";

    private static final String DESTINATION_COL = "destination";

    // creating a constructor for our database handler.
    public HistoryDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TIME_COL + " TEXT,"
                + SOURCE_COL + " TEXT,"
                + DESTINATION_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    public void addNewHistory(String time, String source, String dest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TIME_COL, time);
        values.put(SOURCE_COL, source);
        values.put(DESTINATION_COL, dest);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // we have created a new method for reading all the history.
    public ArrayList<HistoryModel> readHistory() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorHistory = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<HistoryModel> historyModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorHistory.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                historyModalArrayList.add(new HistoryModel(cursorHistory.getString(1),
                        cursorHistory.getString(3),
                        cursorHistory.getString(2)));
            } while (cursorHistory.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorHistory.close();
        return historyModalArrayList;
    }


}

