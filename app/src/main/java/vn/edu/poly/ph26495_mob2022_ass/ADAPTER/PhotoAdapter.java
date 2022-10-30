package vn.edu.poly.ph26495_mob2022_ass.ADAPTER;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.poly.ph26495_mob2022_ass.DTO.Photos;
import vn.edu.poly.ph26495_mob2022_ass.R;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{
    ArrayList<Photos> listPhotos;

    public PhotoAdapter(ArrayList<Photos> listPhotos) {
        this.listPhotos = listPhotos;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_one_item_slide,parent,false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photos photo = listPhotos.get(position);
        holder.img_slide.setImageResource(photo.getRescourceId());
    }

    @Override
    public int getItemCount() {
        return listPhotos.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{
          ImageView img_slide;
        public PhotoViewHolder(@NonNull View view) {
            super(view);
            img_slide = view.findViewById(R.id.img_photo);


        }
    }
}
