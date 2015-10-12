package moviles.flickr.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photosets.PhotosetsInterface;

import java.util.ArrayList;

import moviles.flickr.data.DataBaseManager;
import moviles.flickr.data.dao.FotoDao;
import moviles.flickr.data.entity.Foto;

public class PhotoService {

    private static PhotoService self;

    private Flickr flickr;

    private PhotoService() {
        flickr = FlickrConfig.getInstance().getFlickr();
    }

    public static PhotoService getInstance() {
        if (self == null) {
            self = new PhotoService();
        }
        return self;
    }

    public Bitmap getBigPhotoAsStream(String id) {
        Bitmap bm = null;
        try {
            PhotosInterface fInterface = flickr.getPhotosInterface();
            bm = BitmapFactory.decodeStream(fInterface.getPhoto(id).getLargeAsStream());
        } catch(Exception e) {

        }
        return bm;
    }

    public ArrayList<Foto> getPhotosByAlbum(String albumId) {
        PhotosetsInterface fInterface = flickr.getPhotosetsInterface();
        ArrayList<Photo> photos = new ArrayList<Photo>();
        ArrayList<Foto> mapped = new ArrayList<Foto>();
        try {
            photos = fInterface.getPhotos(albumId, 50, 0);
            Foto map;
            boolean hasData = DataBaseManager.getInstance(null).hasData(DataBaseManager.PHOTOS_TABLE);
            for (Photo temp : photos) {
                map = new Foto();
                map.setId(temp.getId());
                map.setComentarios(CommentService.getInstance().getCommentsByPhoto(temp.getId()));
                map.setName(temp.getDescription());
                map.setAlbumId(albumId);
                try {
                    Bitmap bm;
                    bm = BitmapFactory.decodeStream( temp.getSmallAsInputStream() );
                    map.setThumbnail(bm);
                } catch(Exception e) {}
                if (!hasData) {
                    FotoDao.getInstance().insert(map);
                }
                mapped.add(map);
            }
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return mapped;
    }

}
