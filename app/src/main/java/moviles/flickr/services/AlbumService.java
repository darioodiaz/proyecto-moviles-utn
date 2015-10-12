package moviles.flickr.services;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;

import java.util.ArrayList;

import moviles.flickr.data.DataBaseManager;
import moviles.flickr.data.dao.AlbumDao;
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
            boolean hasData = DataBaseManager.getInstance(null).hasData(DataBaseManager.ALBUMS_TABLE);
            for (Photoset temp : albums) {
                map = new Album();
                map.setId(temp.getId());
                map.setCantidadFotos(temp.getPhotoCount());
                map.setTitulo(temp.getTitle());
                mapped.add(map);
                if (!hasData) {
                    AlbumDao.getInstance().insert(map);
                }
            }
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return mapped;
    }

}
