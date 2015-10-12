package moviles.flickr.presentation.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.flickr4java.flickr.people.User;

import java.net.URL;
import java.util.List;

import moviles.flickr.R;
import moviles.flickr.data.entity.Album;
import moviles.flickr.presentation.adapter.AlbumAdapter;
import moviles.flickr.services.AlbumService;
import moviles.flickr.services.CommonService;

public class AlbumFragment extends ListFragment implements AdapterView.OnItemClickListener {
    public static final String TAG = "AlbumFragment";

    private AlbumAdapter adapter;
    private OnFragmentInteractionListener mListener;

    public static AlbumFragment newInstance() {
        AlbumFragment fragment = new AlbumFragment();
        return fragment;
    }

    public AlbumFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new AlbumAdapter(this);
        loadData();
        setListAdapter(adapter);
    }

    private void loadData(){
        FindAlbumsTask albumsTask = new FindAlbumsTask();
        albumsTask.execute();
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_album, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        if (null != mListener) {
            mListener.onAlbumFragmentInteraction(adapter.getItem(position).getId());
        }
    }

    class FindAlbumsTask extends AsyncTask<String, Void, List<Album>> {

        private Exception exception;

        protected List<Album> doInBackground(String... userMail) {
            try {
                //CommonService commonService = CommonService.getInstance();
                //User user = commonService.getUserIdByEmail("paula.acanepa@yahoo.com.ar");
                AlbumService albumService = AlbumService.getInstance();
                return albumService.getAlbums("136129465@N07");
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(List<Album> albums) {
            adapter.setData(albums);
            adapter.notifyDataSetChanged();
        }
    }
}
