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
import moviles.flickr.data.entity.Comentario;

/**
 * Created by PaulaPC on 12/10/2015.
 */
public class CommentAdapter extends BaseAdapter{
    private List<Comentario> data;
    private LayoutInflater inflater;

    public CommentAdapter(Fragment context) {
        data = new ArrayList<Comentario>(){};
        inflater = LayoutInflater.from(context.getActivity());
    }

    public CommentAdapter(List<Comentario> dataset, Fragment context) {
        data = dataset;
        inflater = LayoutInflater.from(context.getActivity());
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Comentario getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public void addItem(Comentario item){
        if (data != null){
            data.add(item);
        }
    }
    public void setData(List<Comentario> pData){
        this.data = pData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ComentarioHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_comentario, null);
            holder = new ComentarioHolder();
            holder.commentAutor = (TextView) convertView.findViewById(R.id.autorComment);
            holder.comment = (TextView) convertView.findViewById(R.id.comment);
            convertView.setTag(holder);
        } else {
            holder = (ComentarioHolder) convertView.getTag();
        }

        holder.commentAutor.setText(getItem(position).getAutor());
        holder.comment.setText(getItem(position).getBody());

        return convertView;
    }

    class ComentarioHolder{
        private TextView commentAutor;
        private TextView comment;
    }

}
