package moviles.flickr.data.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import moviles.flickr.data.DataBaseManager;
import moviles.flickr.data.entity.Album;

public class AlbumDao {

    private static AlbumDao self;

    private AlbumDao() {}

    public static AlbumDao getInstance() {
        if (self == null) {
            self = new AlbumDao();
        }
        return self;
    }

    public void insert(Album temp) {
        ContentValues values = new ContentValues();
        values.put("fId", temp.getId());
        values.put("name", temp.getTitulo());
        values.put("howManyPhotos", temp.getCantidadFotos());
        DataBaseManager.getInstance().insert(DataBaseManager.ALBUMS_TABLE, values);
    }

    public ArrayList<Album> getAll() {
        ArrayList<Album> albums = new ArrayList<>();
        Cursor cursor = DataBaseManager.getInstance().getAll(DataBaseManager.ALBUMS_TABLE);
        Album temp;
        while (cursor.moveToNext()) {
            temp = new Album();
            temp.setId( cursor.getString( cursor.getColumnIndex("fId") ) );
            temp.setTitulo( cursor.getString( cursor.getColumnIndex("name") ) );
            temp.setCantidadFotos( cursor.getInt( cursor.getColumnIndex("howManyPhotos") ) );
            albums.add(temp);
        }
        cursor.close();
        return albums;
    }
}
