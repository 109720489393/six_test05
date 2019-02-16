package com.example.windows_zxy.six_test05.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows_zxy.six_test05.MainActivity;
import com.example.windows_zxy.six_test05.R;
import com.example.windows_zxy.six_test05.bean.ShopBean;
import com.facebook.drawee.view.SimpleDraweeView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private ShopBean list;

    public MyAdapter(Context context, ShopBean list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recy_layout,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.image.setImageURI(list.getResult().get(i).getMasterPic());
        viewHolder.name.setText(list.getResult().get(i).getCommodityName());
        viewHolder.price.setText(list.getResult().get(i).getPrice()+".00");
    }

    @Override
    public int getItemCount() {
        return list.getResult().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView image;
        private TextView name;
        private TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_one);
            name = itemView.findViewById(R.id.name_one);
            price = itemView.findViewById(R.id.name_price);
        }
    }
}
