package com.pucpr.alexandre.marple.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pucpr.alexandre.marple.R;

public class RestrictionsAdapter extends RecyclerView.Adapter<RestrictionsAdapter.RestrictionHolder> {

    @NonNull
    @Override
    public RestrictionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_restrictions, viewGroup, false);

        return new RestrictionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestrictionHolder restrictionHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RestrictionHolder extends RecyclerView.ViewHolder{

        public RestrictionHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
