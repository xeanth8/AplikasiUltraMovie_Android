package mobile.uas.kel_15.ultramovie.genre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Genre;
import mobile.uas.kel_15.ultramovie.model.Movie;

public class GenreListAdapter extends RecyclerView.Adapter<GenreListAdapter.ViewHolder> {
    private List<Genre> genreList = new ArrayList<>();
    public GenreListAdapter() { }

    @NonNull
    @Override
    public GenreListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_genre, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreListAdapter.ViewHolder holder, int position) {

//        holder.itemView.setOnClickListener(v -> {
////            NavDirections action = MovieListFragmentDirections.actionMovieListFragmentToMovieViewFragment(genreList.get(position).getId());
////            Navigation.findNavController(holder.itemView).navigate(action);
//        });

        holder.getName().setText(genreList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public void setGenreList(List<Genre> genres) {
        this.genreList = genres;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.card_genre_name);
        }

        public TextView getName() {
            return tvName;
        }
    }
}
