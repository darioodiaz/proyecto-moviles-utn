import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.people.User;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;

import java.util.ArrayList;

public class AlbumService {

    private static AlbumService self;
    private Flickr flickr;

    private AlbumService() {
        flickr = Config.getInstance().getFlickr();
    }

    public static AlbumService getInstance() {
        if (self == null) {
            self = new AlbumService();
        }
        return self;
    }

    public ArrayList<Photoset> getAlbums(String userId) {
        PhotosetsInterface fInterface = flickr.getPhotosetsInterface();
        ArrayList<Photoset> albums = new ArrayList<Photoset>();
        try {
            Photosets ps = fInterface.getList(userId);
            albums = new ArrayList<Photoset>(ps.getPhotosets());
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return albums;
    }

}
