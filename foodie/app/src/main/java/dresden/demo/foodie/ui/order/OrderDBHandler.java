package dresden.demo.foodie.ui.order;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class OrderDBHandler extends SQLiteOpenHelper {

    // below variable is for our database name.
    private static final String DB_NAME = "orders";

    // below int is our database version
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "orders1";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    private static final String TIME_COL = "time";

    private static final String ITEM_COL = "item";

    private static final String MONEY_COL = "money";

    private static final String PLACE_COL = "place";

    public OrderDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + TIME_COL + " TEXT,"
                + ITEM_COL + " TEXT,"
                + MONEY_COL + " TEXT,"
                + PLACE_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNewOrder(String time, String item, String money, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TIME_COL, time);
        values.put(ITEM_COL, item);
        values.put(MONEY_COL, money);
        values.put(PLACE_COL, place);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<OrderModel> readOrder() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorHistory = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + ID_COL + " DESC", null);

        // on below line we are creating a new array list.
        ArrayList<OrderModel> orderModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorHistory.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                orderModalArrayList.add(new OrderModel(cursorHistory.getInt(0),
                        cursorHistory.getString(1),
                        cursorHistory.getString(2),
                        cursorHistory.getString(3),
                        cursorHistory.getString(4)
                        ));
            } while (cursorHistory.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorHistory.close();
        return orderModalArrayList;
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM " + TABLE_NAME;
        db.execSQL(clearDBQuery);
    }
}
