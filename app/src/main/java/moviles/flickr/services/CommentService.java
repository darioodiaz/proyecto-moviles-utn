package moviles.flickr.services;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.comments.Comment;
import com.flickr4java.flickr.photos.comments.CommentsInterface;

import java.util.ArrayList;

import moviles.flickr.data.DataBaseManager;
import moviles.flickr.data.dao.ComentarioDao;
import moviles.flickr.data.entity.Comentario;

public class CommentService {

    private static CommentService self;
    private Flickr flickr;

    private CommentService() {
        flickr = FlickrConfig.getInstance().getFlickr();
    }

    public static CommentService getInstance() {
        if (self == null) {
            self = new CommentService();
        }
        return self;
    }

    public ArrayList<Comentario> getCommentsByPhoto(String id) {
        CommentsInterface fInterface = flickr.getCommentsInterface();
        ArrayList<Comentario> mapped = new ArrayList<Comentario>();
        try {
            ArrayList<Comment> comments = (ArrayList<Comment>) fInterface.getList(id);
            Comentario map;
            boolean hasData = DataBaseManager.getInstance(null).hasData(DataBaseManager.COMMENTS_TABLE);
            for (Comment temp : comments) {
                map = new Comentario();
                map.setId(temp.getId());
                map.setBody(temp.getText());
                map.setFecha(temp.getDateCreate());
                map.setAutor(temp.getAuthorName());
                mapped.add(map);
                if (!hasData) {
                    ComentarioDao.getInstance().insert(map);
                }
            }
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return mapped;
    }

}
