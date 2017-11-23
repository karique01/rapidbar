package pe.edu.upc.rapidbar.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.activities.BarActivity;
import pe.edu.upc.rapidbar.models.Bar;
import pe.edu.upc.rapidbar.models.Drink;

public class BarsAdapter extends RecyclerView.Adapter<BarsAdapter.BarViewHolder>{
    List<Bar> bars;

    public BarsAdapter() {
    }

    public BarsAdapter(List<Bar> bars) {
        this.bars = bars;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public List<Bar> setBars(List<Bar> bars) {
        this.bars = bars;
        return bars;
    }

    @Override
    public BarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BarsAdapter.BarViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_bar, parent,false));
    }

    @Override
    public void onBindViewHolder(BarViewHolder holder, int position) {
        final Bar bar = bars.get(position);
        holder.barPictureANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.barPictureANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.barPictureANImageView.setImageUrl(bar.getPictureId());

        holder.barNameTextView.setText(bar.getName());
        holder.barDescriptionTextView.setText(bar.getDescription());
        holder.typeBarNameTextView.setText(bar.getTypeBarName());
        int rate = (int)Double.parseDouble(bar.getRate());
        holder.barRateRatingBar.setRating(rate);
        holder.barAddressTextView.setText(bar.getAddress());

        holder.barSeeMoreTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, BarActivity.class);
                intent.putExtras(bar.toBundle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bars.size();
    }

    class BarViewHolder extends RecyclerView.ViewHolder{
        ANImageView barPictureANImageView;
        TextView barNameTextView;
        TextView barDescriptionTextView;
        TextView typeBarNameTextView;
        RatingBar barRateRatingBar;
        TextView barAddressTextView;
        TextView barSeeMoreTextView;

        public BarViewHolder(View itemView) {
            super(itemView);
            barPictureANImageView = (ANImageView) itemView.findViewById(R.id.barPictureANImageView);
            barNameTextView = (TextView) itemView.findViewById(R.id.barNameTextView);
            barDescriptionTextView = (TextView) itemView.findViewById(R.id.barDescriptionTextView);
            typeBarNameTextView = (TextView) itemView.findViewById(R.id.typeBarNameTextView);
            barRateRatingBar = (RatingBar) itemView.findViewById(R.id.barRateRatingBar);
            barAddressTextView = (TextView) itemView.findViewById(R.id.barAddressTextView);
            barSeeMoreTextView = (TextView) itemView.findViewById(R.id.barSeeMoreTextView);
        }
    }
}




































