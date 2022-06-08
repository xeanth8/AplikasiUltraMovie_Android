package mobile.uas.kel_15.ultramovie.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import mobile.uas.kel_15.ultramovie.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvTitle;
    private final TextView tvGenre;
    private final TextView tvWriter;

    private MovieViewHolder(View view) {
        super(view);
        tvTitle = view.findViewById(R.id.card_movie_title);
        tvGenre = view.findViewById(R.id.card_movie_genre);
        tvWriter = view.findViewById(R.id.card_movie_writer);
    }

    public void bind(String title, String[] genre, String[] writer) {
        tvTitle.setText(title);

        String genres = String.join(", ", genre);
        tvGenre.setText(genres);

        String writers = String.join(", ", writer);
        tvWriter.setText(writers);
    }

    static MovieViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_movie, parent, false);
        return new MovieViewHolder(view);
    }
}
