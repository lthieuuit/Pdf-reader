package com.pnhphamhieu.ebookreader;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pnhphamhieu.ebookreader.Files;
import com.pnhphamhieu.ebookreader.R;

import java.io.File;
import java.util.List;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.ViewHolder> {
    private Context context;
    private List<Files> filesList;

    public FilesAdapter(Context context, List<Files> list) {
        this.context = context;
        this.filesList = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.file_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Files files = filesList.get(position);

        holder.tvName.setText(files.getName());

        if(files.getGroupId() == Files.GROUP_NEW){
            holder.ivFile.setImageResource(R.drawable.ic_drawing_box);
        }
        if(files.getGroupId() == Files.GROUP_FAVORITE){
            holder.ivFile.setImageResource(R.drawable.ic_menu_camera);
        }
    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        // Image name
        TextView tvName;
        ImageView ivFile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.file_name);
            ivFile = itemView.findViewById(R.id.file_img);

             itemView.findViewById(R.id.layoutItem).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     showIem(getAdapterPosition());
                 }
            });

        }
    }
    private void showIem(int position){
        Files file = filesList.get(position);
        Toast.makeText(context, file.getName(), Toast.LENGTH_SHORT).show();
    }
}
