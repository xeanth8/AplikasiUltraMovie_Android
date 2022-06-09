package mobile.uas.kel_15.ultramovie.genre;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import mobile.uas.kel_15.ultramovie.genre.GenreViewHolder;
import mobile.uas.kel_15.ultramovie.model.Genre;

public class GenreAdapter extends ListAdapter<Genre, GenreViewHolder> {
    public GenreAdapter(@NonNull DiffUtil.ItemCallback<Genre> diffCallback) {
        super(diffCallback);
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int view) {
        return GenreViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        Genre current = getItem(position);
        holder.bind(current.getName());
    }

    public static class GenreDiff extends DiffUtil.ItemCallback<Genre> {

        @Override
        public boolean areItemsTheSame(@NonNull Genre oldItem, @NonNull Genre newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Genre oldItem, @NonNull Genre newItem) {
            return false;
        }
    }
}
