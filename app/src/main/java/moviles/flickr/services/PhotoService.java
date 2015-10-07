package moviles.flickr.services;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photosets.PhotosetsInterface;

import java.util.ArrayList;

import moviles.flickr.data.entity.Foto;

public class PhotoService {

    private static PhotoService self;
    public static String BIG_SIZE_PHOTO = "PHOTO";
    public static String MEDIUM_SIZE_PHOTO = "MEDIUM";

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

    public ArrayList<Foto> getPhotosByAlbum(String albumId) {
        PhotosetsInterface fInterface = flickr.getPhotosetsInterface();
        ArrayList<Photo> photos = new ArrayList<Photo>();
        ArrayList<Foto> mapped = new ArrayList<Foto>();
        try {
            photos = fInterface.getPhotos(albumId, 50, 0);
            Foto map;
            for (Photo temp : photos) {
                map = new Foto();
                //map.setId(temp.getId());
                mapped.add(map);
            }
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return mapped;
    }

    public String getAlbumThumbnail() {
        return "";
    }

    public String getPhoto(String photoId, String size) {
        return "";
    }

}
