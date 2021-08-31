package com.example.projectuas.adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuas.R;
import com.example.projectuas.model.DataModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.security.auth.callback.Callback;

public class TeamsFavoriteAdapter extends RecyclerView.Adapter<TeamsFavoriteAdapter.TeamsFavoriteViewHolder> {

    private List<DataModel> dataModelList;
    private Callback callback;

    public interface Callback{
        void onClick(int position);
    }

    public TeamsFavoriteAdapter(List<DataModel> dataModelList, Callback callback) {
        this.dataModelList = dataModelList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TeamsFavoriteAdapter.TeamsFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_teams, parent, false);
        return new TeamsFavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsFavoriteAdapter.TeamsFavoriteViewHolder holder, int position) {

        holder.txt_name.setText(dataModelList.get(position).getName());
        holder.txt_years.setText(dataModelList.get(position).getYears());
        holder.txt_country.setText(dataModelList.get(position).getCountry());
        holder.txt_description.setText(dataModelList.get(position).getDescription());
        Picasso.get()
                .load(dataModelList.get(position).getImage())
                .placeholder(R.drawable.ic_logo_splash_screen)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.img_image);
    }

    @Override
    public int getItemCount() {

        return (dataModelList != null) ? dataModelList.size() : 0;
    }

    public class TeamsFavoriteViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_name;
        private TextView txt_years;
        private TextView txt_country;
        private TextView txt_description;
        private ImageView img_image;
        private CardView cardView;

        public TeamsFavoriteViewHolder(@NonNull View view) {
            super(view);

            txt_name = view.findViewById(R.id.txt_name);
            txt_years = view.findViewById(R.id.txt_years);
            txt_country = view.findViewById(R.id.txt_country);
            txt_description = view.findViewById(R.id.txt_description);
            img_image = view.findViewById(R.id.img_image);
            cardView = view.findViewById(R.id.id_cardview);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onClick(getLayoutPosition());
                }
            });
        }
    }
}
