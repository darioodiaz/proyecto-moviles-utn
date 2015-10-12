package moviles.flickr.data.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;

import moviles.flickr.data.DataBaseManager;
import moviles.flickr.data.entity.Comentario;

public class ComentarioDao {

    private static ComentarioDao self;

    private ComentarioDao() {}

    public static ComentarioDao getInstance() {
        if (self == null) {
            self = new ComentarioDao();
        }
        return self;
    }

    public void insert(Comentario temp) {
        ContentValues values = new ContentValues();
        values.put("fId", temp.getId());
        values.put("author", temp.getAutor());
        values.put("photoId", temp.getFotoId());
        values.put("body", temp.getBody());
        values.put("date", temp.getFecha().toGMTString());
        DataBaseManager.getInstance().insert(DataBaseManager.COMMENTS_TABLE, values);
    }

    public ArrayList<Comentario> getAll(String pId) {
        ArrayList<Comentario> comments = new ArrayList<>();
        Cursor cursor = DataBaseManager.getInstance().getAll(DataBaseManager.COMMENTS_TABLE, "photoId", pId);
        Comentario temp;
        while (cursor.moveToNext()) {
            temp = new Comentario();
            temp.setId( cursor.getString( cursor.getColumnIndex("fId") ) );
            temp.setBody(cursor.getString(cursor.getColumnIndex("body")));
            temp.setAutor(cursor.getString(cursor.getColumnIndex("author")));
            temp.setFecha( new Date( cursor.getString(cursor.getColumnIndex("author") ) ) );
            temp.setFotoId(pId);
            comments.add(temp);
        }
        cursor.close();
        return comments;
    }
}
