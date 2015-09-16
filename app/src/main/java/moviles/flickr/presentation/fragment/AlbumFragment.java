package moviles.flickr.presentation.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import moviles.flickr.R;
import moviles.flickr.data.entity.Album;
import moviles.flickr.presentation.adapter.AlbumAdapter;

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
        adapter.addItem(new Album("Album 1", 152));
        adapter.addItem(new Album("Album 2", 25));
        adapter.addItem(new Album("Album 3", 65));
        adapter.addItem(new Album("Album 4", 19));
        adapter.addItem(new Album("Album 5", 96));
        adapter.addItem(new Album("Album 6", 251));
        adapter.addItem(new Album("Album 7", 45));
        adapter.addItem(new Album("Album 8", 25));
        adapter.addItem(new Album("Album 9", 14));
        adapter.addItem(new Album("Album 10", 452));
        adapter.addItem(new Album("Album 11", 758));
        adapter.addItem(new Album("Album 12", 654));
        adapter.addItem(new Album("Album 13", 152));
        adapter.addItem(new Album("Album 14", 25));
        adapter.addItem(new Album("Album 15", 65));
        adapter.addItem(new Album("Album 16", 19));
        adapter.addItem(new Album("Album 17", 96));
        adapter.addItem(new Album("Album 18", 251));
        adapter.addItem(new Album("Album 19", 45));
        adapter.addItem(new Album("Album 20", 25));
        adapter.addItem(new Album("Album 21", 14));
        adapter.addItem(new Album("Album 22", 452));
        adapter.addItem(new Album("Album 23", 758));
        adapter.addItem(new Album("Album 24", 654));
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
            mListener.onFragmentInteraction(adapter.getItem(position).getTitulo());
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String item);
    }

}
