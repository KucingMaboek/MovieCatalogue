package com.example.favoritehub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.favoritehub.R;
import com.example.favoritehub.model.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    private ArrayList<Item> listItem;
    private OnItemClickCallBack onItemClickCallBack;
    private Context context;

    public void setData(ArrayList<Item> items) {
        if (listItem != null) {
            listItem.clear();
            listItem.addAll(items);
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public ItemAdapter(ArrayList<Item> list) {
        this.listItem = list;
    }

    public ItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int position) {
        Item item = listItem.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getPhoto())
                .into(holder.imgPhoto);

        holder.tvTitle.setText(item.getTitle());
        holder.tvSynopsis.setText(item.getSysnopsis());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallBack.onItemClicked(listItem.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listItem != null) {
            return listItem.size();
        }
        else {
            return 0;
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvTitle, tvSynopsis;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvTitle = itemView.findViewById(R.id.txt_title);
            tvSynopsis = itemView.findViewById(R.id.txt_synopsis);
        }
    }

    public interface OnItemClickCallBack {
        void onItemClicked(Item data);
    }
}
