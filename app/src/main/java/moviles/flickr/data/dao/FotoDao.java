package moviles.flickr.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import moviles.flickr.data.DataBaseManager;
import moviles.flickr.data.entity.Foto;

public class FotoDao {

    private static FotoDao self;

    private FotoDao() {}

    public static FotoDao getInstance() {
        if (self == null) {
            self = new FotoDao();
        }
        return self;
    }

    public void insert(Foto temp) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        ContentValues values = new ContentValues();
        values.put("fId", temp.getId());
        values.put("albumId", temp.getAlbumId());
        values.put("name", temp.getName());
        temp.getThumbnail().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        values.put("thumbnail_b64", Base64.encodeToString(byteArray, Base64.DEFAULT));

        temp.getThumbnail().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        values.put("bigPhoto_b64", Base64.encodeToString(byteArray, Base64.DEFAULT));

        DataBaseManager.getInstance().insert(DataBaseManager.COMMENTS_TABLE, values);
    }

    public ArrayList<Foto> getAll(String pId) {
        ArrayList<Foto> photos = new ArrayList<>();
        Cursor cursor = DataBaseManager.getInstance().getAll(DataBaseManager.COMMENTS_TABLE, "photoId", pId);
        Foto temp;
        byte[] imageAsBytes;
        while (cursor.moveToNext()) {
            temp = new Foto();
            temp.setId( cursor.getString( cursor.getColumnIndex("fId") ) );
            temp.setAlbumId(cursor.getString(cursor.getColumnIndex("albumId")));

            imageAsBytes = Base64.decode(cursor.getString(cursor.getColumnIndex("thumbnail_b64")).getBytes(), Base64.DEFAULT);
            temp.setThumbnail(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

            imageAsBytes = Base64.decode(cursor.getString(cursor.getColumnIndex("photo_b64")).getBytes(), Base64.DEFAULT);
            temp.setPhoto(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
            photos.add(temp);
        }
        cursor.close();
        return photos;
    }
}
