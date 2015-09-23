import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.people.User;

public class CommonService {

    private static CommonService self;
    private Flickr flickr;

    private CommonService() {
        flickr = Config.getInstance().getFlickr();
    }

    public static CommonService getInstance() {
        if (self == null) {
            self = new CommonService();
        }
        return self;
    }

    public User getUserIdByEmail(String email) {
        PeopleInterface fInterface = flickr.getPeopleInterface();
        User user = new User();
        try {
            user =  fInterface.findByEmail(email);
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserIdByUsername(String username) {
        PeopleInterface fInterface = flickr.getPeopleInterface();
        User user = new User();
        try {
            user =  fInterface.findByUsername(username);
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return user;
    }
}
