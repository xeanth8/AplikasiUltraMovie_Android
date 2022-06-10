package mobile.uas.kel_15.ultramovie.movie;

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
import mobile.uas.kel_15.ultramovie.model.Movie;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<Movie> movieList = new ArrayList<>();
    public MovieListAdapter() { }

    // Inflate (munculkan) masing-masing card untuk setiap movie.
    @NonNull
    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);

        return new ViewHolder(view);
    }

    // Setting teks di setiap card movie yang diinflate.
    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.ViewHolder holder, int position) {

        // Setiap card movie yang diinflate diberikan onClickListener dengan argument movieList.get(Position).getId()
        holder.itemView.setOnClickListener(v -> {
            NavDirections action = MovieListFragmentDirections.actionMovieListFragmentToMovieViewFragment(movieList.get(position).getId());
            Navigation.findNavController(holder.itemView).navigate(action);
        });

        holder.getTitle().setText(movieList.get(position).getTitle());

        String genres = String.join(", ", movieList.get(position).getGenres());
        holder.getGenre().setText(genres);
        String writer = String.join(", ", movieList.get(position).getWriters());
        holder.getWriter().setText(writer);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // Set movieList yang akan disetting ke card dengan movies dari Fragment
    public void setMovieList(List<Movie> movies) {
        this.movieList = movies;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvGenre;
        private final TextView tvWriter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.card_movie_title);
            tvGenre = itemView.findViewById(R.id.card_movie_genre);
            tvWriter = itemView.findViewById(R.id.card_movie_writer);
        }

        public TextView getTitle() {
            return tvTitle;
        }

        public TextView getGenre() {
            return tvGenre;
        }

        public TextView getWriter() {
            return tvWriter;
        }
    }
}
