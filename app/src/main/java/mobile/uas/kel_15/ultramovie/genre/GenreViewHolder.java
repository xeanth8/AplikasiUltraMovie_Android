package mobile.uas.kel_15.ultramovie.genre;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import mobile.uas.kel_15.ultramovie.R;

public class GenreViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvName;

    public GenreViewHolder(View view) {
        super(view);
        tvName = view.findViewById(R.id.card_genre_name);
    }

    public void bind(String name) {
        tvName.setText(name);
    }

    public static GenreViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_genre, parent, false);
        return new GenreViewHolder(view);
    }
}
