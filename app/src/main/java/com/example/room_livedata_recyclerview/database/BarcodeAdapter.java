package com.example.room_livedata_recyclerview.database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_livedata_recyclerview.R;

import java.util.ArrayList;
import java.util.List;


public class BarcodeAdapter extends RecyclerView.Adapter<BarcodeAdapter.BarcodeHolder> {
    private List<Barcode> barcodes = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public BarcodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.barcode_item, parent, false);
        return new BarcodeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BarcodeHolder holder, int position) {
        Barcode currentBarcode = barcodes.get(position);

        holder.textViewName.setText(currentBarcode.getName());
        holder.textViewBarcodenum.setText(currentBarcode.getBarcodenum());

        holder.textViewPriority.setText(String.valueOf(currentBarcode.getId()));//Change getId by getPriority if it added to database
    }

    @Override
    public int getItemCount() {
        return barcodes.size();
    }

    public void setBarcodes(List<Barcode> barcodes) {
        this.barcodes = barcodes;
        notifyDataSetChanged();
    }

    public Barcode getBarcodeAt(int position) {
        return barcodes.get(position);
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
                        listener.OnItemClick(barcodes.get(position));
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
