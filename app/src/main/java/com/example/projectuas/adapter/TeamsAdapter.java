package com.example.projectuas.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuas.R;
import com.example.projectuas.model.ModelTeams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {

    public ArrayList<ModelTeams> listTeams;
    private Callback callback;
    private int posku;
    private View viewku;

    public interface Callback{
        void onClick(int position);
    }

    public TeamsAdapter(ArrayList<ModelTeams> listTeams, Callback callback) {
        this.listTeams = listTeams;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TeamsAdapter.TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_teams, parent, false);
        return new TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsAdapter.TeamsViewHolder holder, int position) {

        holder.txt_name.setText(listTeams.get(position).getName());
        holder.txt_years.setText(listTeams.get(position).getYears());
        holder.txt_country.setText(listTeams.get(position).getCountry());
        holder.txt_description.setText(listTeams.get(position).getDescription());
        Picasso.get()
                .load(listTeams.get(position).getImage())
                .placeholder(R.drawable.ic_logo_splash_screen)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.img_image);

    }

    @Override
    public int getItemCount() {
        return (listTeams != null) ? listTeams.size() : 0;
    }

    public class TeamsViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private TextView txt_name;
        private TextView txt_years;
        private TextView txt_country;
        private TextView txt_description;
        private ImageView img_image;
        private CardView cardView;

        public TeamsViewHolder(View view){
            super(view);

            viewku = view;

            txt_name = view.findViewById(R.id.txt_name);
            txt_years = view.findViewById(R.id.txt_years);
            txt_country = view.findViewById(R.id.txt_country);
            txt_description = view.findViewById(R.id.txt_description);
            img_image = view.findViewById(R.id.img_image);
            cardView = view.findViewById(R.id.id_cardview);

            cardView.setOnCreateContextMenuListener(this);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getLayoutPosition());
                }
            });

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Delete = menu.add(Menu.NONE, 1, 1, "Delete");
            posku = getLayoutPosition();
            Delete.setOnMenuItemClickListener(onlickcontextmenu);
        }
    }

    private final MenuItem.OnMenuItemClickListener onlickcontextmenu = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case 1:
                    AlertDialog.Builder builder = new AlertDialog.Builder(viewku.getContext());
                    builder.setMessage("Are you sure you want to delete this data?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    listTeams.remove(posku);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            .setTitle(listTeams.get(posku).getName());
                    AlertDialog alert = builder.create();
                    alert.show();

                    break;
            }
            return true;
        }
    };
}
