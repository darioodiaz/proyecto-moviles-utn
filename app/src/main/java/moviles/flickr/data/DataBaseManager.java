package moviles.flickr.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBaseManager extends SQLiteOpenHelper {

    private static DataBaseManager self;
    private static String DB_NAME = "utn_moviles_2015_flickr";
    private static String NEW_TABLE_QUERY_TEMPLATE = "CREATE TABLE ? ( id INTEGER PRIMARY KEY, fId TEXT, @)";

    public static String ALBUMS_TABLE = "albums";
    public static String COMMENTS_TABLE = "comments";
    public static String PHOTOS_TABLE = "photos";

    private static Pattern tablePattern = Pattern.compile("[?]");
    private static Pattern colsPattern = Pattern.compile("@");
    private static Pattern fixPattern = Pattern.compile("(, \\))");

    private DataBaseManager(Context ctx) {
        super(ctx, DB_NAME, null, 1);
    }

    public static DataBaseManager getInstance(Context ctx) {
        if (self == null) {
            self = new DataBaseManager(ctx);
        }
        return self;
    }

    public void insert(String table, ContentValues params) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(table, null, params);
        db.close();
    }

    public Cursor getAll(String table) {
        return getWritableDatabase().rawQuery("SELECT * FROM ".concat(table), null);
    }

    public boolean hasData(String table) {
        SQLiteDatabase db = getReadableDatabase();
        boolean flag = db.rawQuery("SELECT * FROM ".concat(table), null).getCount() != 0;
        db.close();
        return flag;
    }

    public Cursor getAll(String table, String where, String whereParam) {
        return getWritableDatabase()
                .rawQuery("SELECT * FROM "
                        .concat(table)
                        .concat(" WHERE ")
                        .concat(where)
                        .concat("=")
                        .concat(whereParam), null);
    }

    public void update(String table, ContentValues params, String fId) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(table, params, "fId=?", new String[]{fId});
        db.close();
    }

    private String getNewTableQuery(String table, HashMap<String, String> cols) {
        StringBuffer sb = new StringBuffer();
        StringBuffer csb = new StringBuffer();
        Matcher tm = tablePattern.matcher(NEW_TABLE_QUERY_TEMPLATE);
        tm.find();
        Matcher cm = colsPattern.matcher(tm.replaceFirst(table));
        for(Map.Entry<String, String> temp : cols.entrySet()) {
            csb.append( temp.getKey().concat( " ".concat( temp.getValue().toUpperCase() ).concat(", ") ) );
        }
        cm.find();
        Matcher fm = fixPattern.matcher( cm.replaceFirst( csb.toString() ) );
        fm.find();
        sb.append( fm.replaceFirst(")") );
        return sb.toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        HashMap<String, String> map = new HashMap<>();

        map.put("name", "text");
        map.put("howManyPhotos", "integer");
        db.execSQL( getNewTableQuery("albums", map) );
        map.clear();

        map.put("name", "text");
        map.put("albumId", "text");
        map.put("thumbnail_b64", "text");
        map.put("bigPhoto_b64", "text");
        db.execSQL( getNewTableQuery("photos", map) );
        map.clear();

        map.put("body", "text");
        map.put("date", "text");
        map.put("author", "text");
        map.put("photoId", "text");
        db.execSQL( getNewTableQuery("comments", map) );

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}