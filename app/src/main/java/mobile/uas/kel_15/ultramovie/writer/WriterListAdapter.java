package mobile.uas.kel_15.ultramovie.writer;

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
import mobile.uas.kel_15.ultramovie.model.Writer;

public class WriterListAdapter extends RecyclerView.Adapter<WriterListAdapter.ViewHolder> {
    private List<Writer> writerList = new ArrayList<>();
    
    public WriterListAdapter() { }

    @NonNull
    @Override
    public WriterListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_writer, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WriterListAdapter.ViewHolder holder, int position) {

        holder.itemView.setOnClickListener(v -> {

            NavDirections action = WriterListFragmentDirections.actionWriterListFragmentToWriterViewFragment(writerList.get(position).getId());
            Navigation.findNavController(holder.itemView).navigate(action);
        });

        holder.getName().setText(writerList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return writerList.size();
    }

    public void setWriterList(List<Writer> writers) {
        this.writerList = writers;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.card_writer_name);
        }

        public TextView getName() {
            return tvName;
        }
    }
}
