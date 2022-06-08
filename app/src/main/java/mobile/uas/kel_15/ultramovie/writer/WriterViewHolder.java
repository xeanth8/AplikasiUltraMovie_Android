package mobile.uas.kel_15.ultramovie.writer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import mobile.uas.kel_15.ultramovie.R;

public class WriterViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvName;

    public WriterViewHolder(View view) {
        super(view);
        tvName = view.findViewById(R.id.card_writer_name);
    }

    public void bind(String name) {
        tvName.setText(name);
    }

    static WriterViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_writer, parent, false);
        return new WriterViewHolder(view);
    }
}
