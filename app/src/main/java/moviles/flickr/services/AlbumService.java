package moviles.flickr.services;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;

import java.util.ArrayList;

import moviles.flickr.data.entity.Album;

public class AlbumService {

    private static AlbumService self;
    private Flickr flickr;

    private AlbumService() {
        flickr = FlickrConfig.getInstance().getFlickr();
    }

    public static AlbumService getInstance() {
        if (self == null) {
            self = new AlbumService();
        }
        return self;
    }

    public ArrayList<Album> getAlbums(String userId) {
        PhotosetsInterface fInterface = flickr.getPhotosetsInterface();
        ArrayList<Photoset> albums = new ArrayList<Photoset>();
        ArrayList<Album> mapped = new ArrayList<Album>();
        try {
            Photosets ps = fInterface.getList(userId);
            albums = new ArrayList<Photoset>(ps.getPhotosets());
            Album map;
            for (Photoset temp : albums) {
                map = new Album();
                mapped.add(map);
            }
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return mapped;
    }

}
