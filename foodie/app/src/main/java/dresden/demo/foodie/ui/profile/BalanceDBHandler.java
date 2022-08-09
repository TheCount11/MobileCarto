package dresden.demo.foodie.ui.profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BalanceDBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "balance";

    // below int is our database version
    private static final int DB_VERSION = 2;

    // below variable is for our table name.
    private static final String TABLE_NAME = "balance";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    private static final String BALANCE_COL = "balance";

    // creating a constructor for our database handler.
    public BalanceDBHandler(Context context) {
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
                + ID_COL + " REAL PRIMARY KEY, "
                + BALANCE_COL + " REAL)";

        // insert initial balance when creating the table
        String insert = "INSERT INTO balance (id, balance) VALUES ('42', '15');";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
        db.execSQL(insert);
    }

    public void addBalance(double credit) {
        double origin = readBalance();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BALANCE_COL, origin+credit);

        db.update(TABLE_NAME, values, "id=?", new String[]{"42"});
        db.close();
    }

    public void reduceBalance(double credit) {
        double origin = readBalance();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BALANCE_COL, origin-credit);

        db.update(TABLE_NAME, values, "id=?", new String[]{"42"});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // we have created a new method for reading all the courses.
    public double readBalance() {
        // on below line we are creating a
        // database for reading our database.
        double balance = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorHistory = db.rawQuery("SELECT balance FROM balance WHERE id=42", null);

        if (cursorHistory.moveToFirst()) {
            balance = Double.parseDouble(cursorHistory.getString(0));
        }
        // at last closing our cursor
        cursorHistory.close();
        return balance;
    }

}
