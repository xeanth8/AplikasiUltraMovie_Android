package mobile.uas.kel_15.ultramovie.writer;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import mobile.uas.kel_15.ultramovie.model.Genre;
import mobile.uas.kel_15.ultramovie.model.Writer;

public class WriterAdapter extends ListAdapter<Writer, WriterViewHolder> {
    public WriterAdapter(@NonNull DiffUtil.ItemCallback<Writer> diffCallback) {
        super(diffCallback);
    }

    @Override
    public WriterViewHolder onCreateViewHolder(ViewGroup parent, int view) {
        return WriterViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull WriterViewHolder holder, int position) {
        Writer current = getItem(position);
        holder.bind(current.getName());
    }

    static class WriterDiff extends DiffUtil.ItemCallback<Writer> {

        @Override
        public boolean areItemsTheSame(@NonNull Writer oldItem, @NonNull Writer newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Writer oldItem, @NonNull Writer newItem) {
            return false;
        }
    }
}
