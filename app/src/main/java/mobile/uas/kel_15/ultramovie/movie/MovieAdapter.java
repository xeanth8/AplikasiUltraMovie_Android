package mobile.uas.kel_15.ultramovie.movie;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import mobile.uas.kel_15.ultramovie.model.Movie;

public class MovieAdapter extends ListAdapter<Movie, MovieViewHolder> {
    public MovieAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback) {
        super(diffCallback);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int view) {
        return MovieViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie current = getItem(position);
        holder.bind(current.getTitle(), current.getGenres(), current.getWriters());
    }

    static class MovieDiff extends DiffUtil.ItemCallback<Movie> {

        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return false;
        }
    }
}
