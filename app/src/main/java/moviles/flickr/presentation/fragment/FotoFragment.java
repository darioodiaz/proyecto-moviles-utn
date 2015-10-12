package moviles.flickr.presentation.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

import moviles.flickr.R;
import moviles.flickr.data.entity.Foto;
import moviles.flickr.presentation.adapter.FotoAdapter;
import moviles.flickr.services.PhotoService;

public class FotoFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String TAG = "FotoFragment";
    public static final String ALBUM_ID_KEY = "albumId";

    private FotoAdapter adapter;
    private OnFragmentInteractionListener mListener;

    public FotoFragment() {
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FotoAdapter(this);
        loadData();
    }

    private void loadData() {
        FindFotosTask fotosTask = new FindFotosTask();
        fotosTask.execute(getArguments().getString(ALBUM_ID_KEY));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_foto, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        GridView gridview = (GridView) getActivity().findViewById(R.id.gridViewFoto);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(this);
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
            mListener.onFotoFragmentInteraction(((Foto) adapter.getItem(position)).getId());
        }
    }

    class FindFotosTask extends AsyncTask<String, Void, List<Foto>> {

        private Exception exception;

        protected List<Foto> doInBackground(String... albumId) {
            try {
                PhotoService fotoService = PhotoService.getInstance();
                return fotoService.getPhotosByAlbum(albumId[0]);
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(List<Foto> fotos) {
            adapter.setData(fotos);
            adapter.notifyDataSetChanged();
        }
    }
}

