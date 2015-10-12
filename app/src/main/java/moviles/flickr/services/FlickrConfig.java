package moviles.flickr.services;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;

public class FlickrConfig {

    private static String API_KEY = "841f079bbd7d1a5732cdf3979553f67d";
    private static String SECRET_KEY = "d294e8d2f913541d";
    private static FlickrConfig self;

    private Flickr flickr;

    private FlickrConfig() {
        flickr = new Flickr(API_KEY, SECRET_KEY, new REST());
    }

    public static FlickrConfig getInstance() {
        if (self == null) {
            self = new FlickrConfig();
        }
        return self;
    }

    public Flickr getFlickr() {
        return flickr;
    }
}
