package com.bk.fuliboom.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bk.fuliboom.R;
import com.bk.fuliboom.Repository.Beans.Result;
import com.bk.fuliboom.Utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bk on 2016/9/18.
 */

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.VideoListHolder> {
    private List<Result> data;
    private onItemClickListener itemClickListener;
    private Context mContext;

    public VideoRecyclerAdapter(List<Result> data, Context context) {
        this.data = data;
        mContext = context;
    }

    @Override
    public VideoListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ganhuo,parent,false);
        return new VideoListHolder(view);
    }

    @Override
    public void onBindViewHolder(final VideoListHolder holder,  int position) {
        final Result result = data.get(position);
        holder.sourceIcon.setImageResource(AppUtils.getResourseIDByUrl(result.getUrl()));
        holder.desc.setText(result.getDesc());
        holder.people.setText(result.getWho());
        holder.tag.setText(result.getType());
        holder.time.setText(result.getCreatedAt());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClicked(holder.getAdapterPosition(), result.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class VideoListHolder extends RecyclerView.ViewHolder{
        View view;

        @BindView(R.id.iv_source)
        ImageView sourceIcon;

        @BindView(R.id.tv_desc)
        TextView desc;

        @BindView(R.id.iv_action)
        ImageView action;

        @BindView(R.id.iv_img)
        ImageView img;


        @BindView(R.id.tv_people)
        TextView people;

        @BindView(R.id.tv_tag)
        TextView tag;

        @BindView(R.id.tv_time)
        TextView time;

        public VideoListHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,itemView);
        }
    }

    public void setOnItemClickListener(onItemClickListener listener){
        itemClickListener = listener;
    }

    public interface onItemClickListener{
        void onItemClicked(int position, String url);
    }
}
