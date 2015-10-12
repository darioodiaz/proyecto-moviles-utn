package moviles.flickr.presentation.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.flickr4java.flickr.people.User;

import moviles.flickr.R;
import moviles.flickr.data.entity.Album;
import moviles.flickr.presentation.adapter.AlbumAdapter;
import moviles.flickr.presentation.adapter.CommentAdapter;
import moviles.flickr.services.AlbumService;
import moviles.flickr.services.CommentService;
import moviles.flickr.services.CommonService;

/**
 * Created by PaulaPC on 12/10/2015.
 */
public class ComentarioFragment extends ListFragment {
    public static final String TAG = "CommentFragment";

    private CommentAdapter adapter;

    public static ComentarioFragment newInstance() {
        ComentarioFragment fragment = new ComentarioFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CommentAdapter(this);
        loadData();
        setListAdapter(adapter);
    }


    private void loadData(){
        CommonService commonService = CommonService.getInstance();
        User user = commonService.getUserIdByEmail("paula.acanepa@yahoo.com.ar");
        CommentService  commentService = CommentService.getInstance();
        adapter.setData(commentService.getCommentsByPhoto(user.getId()));
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_comentario, container, false);
        return v;
    }



}
