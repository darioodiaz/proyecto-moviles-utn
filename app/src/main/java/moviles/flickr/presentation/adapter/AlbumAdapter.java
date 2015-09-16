package moviles.flickr.presentation.adapter;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import moviles.flickr.R;
import moviles.flickr.data.entity.Album;

public class AlbumAdapter extends BaseAdapter{
    private List<Album> data;
    private LayoutInflater inflater;

    public AlbumAdapter(List<Album> dataset, Fragment context) {
        data = dataset;
        inflater = LayoutInflater.from(context.getActivity());
    }

    public AlbumAdapter(Fragment context) {
        data = new ArrayList<Album>(){};
        inflater = LayoutInflater.from(context.getActivity());
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Album getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    public void addItem(Album item){
        if (data != null){
            data.add(item);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        AlbumHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_album, null);
            holder = new AlbumHolder();
            holder.albumTitle = (TextView) convertView.findViewById(R.id.albumTitle);
            holder.albumDescription = (TextView) convertView.findViewById(R.id.albumDescription);
            convertView.setTag(holder);
        } else {
            holder = (AlbumHolder) convertView.getTag();
        }

        holder.albumTitle.setText(getItem(position).getTitulo());
        holder.albumDescription.setText(getItem(position).getDescripcion());

        return convertView;
    }

    class AlbumHolder{
        private TextView albumTitle;
        private TextView albumDescription;
    }

}
