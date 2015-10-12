package moviles.flickr.presentation.adapter;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import moviles.flickr.R;
import moviles.flickr.data.entity.Foto;

public class FotoAdapter extends BaseAdapter{
    private List<Foto> data;
    private LayoutInflater inflater;

    public FotoAdapter(List<Foto> dataset, Fragment context){
        data = dataset;
        inflater = LayoutInflater.from(context.getActivity());
    }

    public FotoAdapter(Fragment context) {
        data = new ArrayList<Foto>(){};
        inflater = LayoutInflater.from(context.getActivity());
    }

    public void setData(List<Foto> fotos){
        this.data = fotos;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        FotoHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_foto, null);
            holder = new FotoHolder();
            holder.thumbnail = (ImageView) convertView.findViewById(R.id.fotoThumbnail);
            convertView.setTag(holder);
        } else {
            holder = (FotoHolder) convertView.getTag();
        }
        holder.thumbnail.setImageBitmap(data.get(position).getThumbnail());
        return convertView;
    }

    class FotoHolder{
        private ImageView thumbnail;
    }
}
