package com.example.room_livedata_recyclerview.database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_livedata_recyclerview.R;

import java.util.ArrayList;
import java.util.List;


public class BarcodeAdapter extends ListAdapter<Barcode, BarcodeAdapter.BarcodeHolder> {
    //private List<Barcode> barcodes = new ArrayList<>();
    private OnItemClickListener listener;

    public BarcodeAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Barcode> DIFF_CALLBACK = new DiffUtil.ItemCallback<Barcode>() {
        @Override
        public boolean areItemsTheSame(@NonNull Barcode oldItem, @NonNull Barcode newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Barcode oldItem, @NonNull Barcode newItem) {
            return oldItem.getBarcodenum().equals(newItem.getBarcodenum()) &&
                    oldItem.getId() == newItem.getId() &&
                    oldItem.getName().equals(newItem.getName());
        }
    };

    @NonNull
    @Override
    public BarcodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.barcode_item, parent, false);
        return new BarcodeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BarcodeHolder holder, int position) {
        Barcode currentBarcode = getItem(position);

        holder.textViewName.setText(currentBarcode.getName());
        holder.textViewBarcodenum.setText(currentBarcode.getBarcodenum());

        holder.textViewPriority.setText(String.valueOf(currentBarcode.getId()));//Change getId by getPriority if it added to database

    }




    public Barcode getBarcodeAt(int position) {
        return getItem(position);
    }

    class BarcodeHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;
        private final TextView textViewBarcodenum;
        private final TextView textViewPriority;

        public BarcodeHolder(@NonNull View itemView) {
            super(itemView);
            textViewBarcodenum = itemView.findViewById(R.id.text_view_barcodenum);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            itemView.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(getItem(position));
                    }
                }
            }));
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(Barcode barcode);
    }

    public void SetOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
