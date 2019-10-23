package com.example.android_dialy_project;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DialyAdapter  extends RecyclerView.Adapter<DialyAdapter.DailyViewHolder> {

    public List<Dialy> Dialies;
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onClickItemDelete(int position);

    }

    @NonNull
    @Override
    public DailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialy, parent, false);
        return new DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolder holder, final int position) {
        holder.tvTitle.setText(Dialies.get(position).getTitle());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("1", "1  " + position);
                onClick.onClickItemDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {

        if(Dialies == null){
            return 0;

        }else {

            return Dialies.size();
        }

    }

    class DailyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        Button btnDelete;


        public DailyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            btnDelete = itemView.findViewById(R.id.deleteTask);
        }
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }
}
