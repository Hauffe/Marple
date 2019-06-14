package com.pucpr.alexandre.marple.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pucpr.alexandre.marple.R;
import com.pucpr.alexandre.marple.bc.RestrictionBC;
import com.pucpr.alexandre.marple.entity.Restriction;
import com.pucpr.alexandre.marple.utils.Constants;

import java.util.List;

public class RestrictionsAdapter extends RecyclerView.Adapter<RestrictionsAdapter.RestrictionHolder> {

    private List<Restriction> restrictions = RestrictionBC.sharedInstance().getRestrictions();
    private Context context;

    public RestrictionsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RestrictionsAdapter.RestrictionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_restrictions, viewGroup, false);


        return new RestrictionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestrictionsAdapter.RestrictionHolder restrictionHolder, int position) {
        Restriction restriction = restrictions.get(position);

        restrictionHolder.restriction_name.setText(restriction.getName());
        restrictionHolder.restriction_description.setText(restriction.getIngredients().toString());
    }

    @Override
    public int getItemCount() {
        return restrictions.size();
    }

    public class RestrictionHolder extends RecyclerView.ViewHolder{

        TextView restriction_name;
        TextView restriction_description;

        public RestrictionHolder(@NonNull View itemView) {
            super(itemView);

            restriction_name = itemView.findViewById(R.id.restriction_name);
            restriction_description = itemView.findViewById(R.id.restriction_description);

        }
    }
}
