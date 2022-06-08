package mobile.uas.kel_15.ultramovie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Writer;

public class WriterAdapter extends RecyclerView.Adapter<WriterAdapter.ViewHolder> {
    private final ArrayList<Writer> writerData;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_writer, parent, false);

        return new ViewHolder(view);
    }

    public WriterAdapter(ArrayList<Writer> data) {
        this.writerData = data;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView tvName = holder.tvName;

        tvName.setText(writerData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return writerData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        ViewHolder(View v) {
            super(v);

            tvName = v.findViewById(R.id.card_writer_name);
        }
    }
}
