import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photosets.PhotosetsInterface;

import java.util.ArrayList;

public class PhotoService {

    private static PhotoService self;
    public static String BIG_SIZE_PHOTO = "PHOTO";
    public static String MEDIUM_SIZE_PHOTO = "MEDIUM";

    private Flickr flickr;

    private PhotoService() {
        flickr = Config.getInstance().getFlickr();
    }

    public static PhotoService getInstance() {
        if (self == null) {
            self = new PhotoService();
        }
        return self;
    }

    public ArrayList<Photo> getPhotosByAlbum(String albumId) {
        PhotosetsInterface fInterface = flickr.getPhotosetsInterface();
        ArrayList<Photo> photos = new ArrayList<Photo>();
        try {
            photos = fInterface.getPhotos(albumId, 50, 0);
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return photos;
    }

    public String getAlbumThumbnail() {
        return "";
    }

    public String getPhoto(String photoId, String size) {
        return "";
    }

}
