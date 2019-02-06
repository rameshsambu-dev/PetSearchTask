package com.petsearchtask.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petsearchtask.R;
import com.petsearchtask.activities.MoviesDetailActivity;
import com.petsearchtask.helper.Utilities;
import com.petsearchtask.model.GetMoviesResponse;
import com.petsearchtask.rest.AppConstants;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ItemViewHolder> {
    List<GetMoviesResponse.Result> resultList;
    private Context context;

    public HomeListAdapter(List<GetMoviesResponse.Result> results, Context context) {
        this.resultList = results;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_home_list, parent, false);

        return new ItemViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        GetMoviesResponse.Result jobModel = resultList.get(position);
        holder.tv_title.setText(jobModel.getTitle());
        holder.tv_desc.setText(jobModel.getOverview());
        holder.tv_rating.setText(jobModel.getVoteAverage());
        holder.tv_date.setText(Utilities.getTimeFromDate(jobModel.getReleaseDate()));
        holder.tv_language.setText(jobModel.getOriginalLanguage());

        Glide.with(context).load(AppConstants.BASE_IMAGE_URL + jobModel.getPosterPath()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_desc, tv_rating, tv_date, tv_language;
        ConstraintLayout cardView;
        ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_rating = itemView.findViewById(R.id.tv_rating);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_language = itemView.findViewById(R.id.tv_language);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MoviesDetailActivity.class);
                    intent.putExtra("moviedata",  resultList.get(getLayoutPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
