package org.techtown.guide.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.guide.R;

import java.util.ArrayList;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> implements OnListItemClickListener{

    TextView name;
    TextView learn;

    ArrayList<MainList> items = new ArrayList<MainList>();
    OnListItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_main_list, parent, false);

        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainList item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(MainList item){
        items.add(item);
    }

    public void setItems(ArrayList<MainList> items){
        this.items = items;
    }

    public MainList getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, MainList item){
        items.add(position, item);
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    public void clear(){
        setItems(new ArrayList<MainList>());
    }

    public void setOnItemClickListener(OnListItemClickListener listener){
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView, final OnListItemClickListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.textView);
            learn = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null){
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });

        }

        public void setItem(MainList item){
            name.setText(item.getName());
            learn.setText(item.getLearn());
        }


    }
}
