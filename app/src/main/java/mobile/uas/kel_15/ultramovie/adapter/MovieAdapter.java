package mobile.uas.kel_15.ultramovie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final ArrayList<Movie> moviesData;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);

        return new ViewHolder(view);
    }

    public MovieAdapter(ArrayList<Movie> data) {
        this.moviesData = data;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView tvTitle = holder.tvTitle;
        TextView tvGenre = holder.tvGenre;
        TextView tvWriter = holder.tvWriter;

        tvTitle.setText(moviesData.get(position).getTitle());

        String genres = String.join(", ", moviesData.get(position).getGenres());
        tvGenre.setText(genres);

        String writers = String.join(", ", moviesData.get(position).getWriters());
        tvWriter.setText(writers);
    }

    @Override
    public int getItemCount() {
        return moviesData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvGenre, tvWriter;

        ViewHolder(View v) {
            super(v);

            tvTitle = v.findViewById(R.id.card_movie_title);
            tvGenre = v.findViewById(R.id.card_movie_genre);
            tvWriter = v.findViewById(R.id.card_movie_writer);
        }
    }
}
