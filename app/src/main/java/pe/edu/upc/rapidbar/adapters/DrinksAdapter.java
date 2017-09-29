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
import pe.edu.upc.rapidbar.models.Drink;


public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinkViewHolder> {

    private List<Drink> drinks;

    public DrinksAdapter() {
    }

    public DrinksAdapter(List<Drink> drinks) {
        this.setDrinks(drinks);
    }

    @Override
    public DrinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DrinkViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_drink, parent,false));
    }

    @Override
    public void onBindViewHolder(DrinkViewHolder holder, int position) {
        final Drink drink = drinks.get(position);
        holder.drinkPictureANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.drinkPictureANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.drinkPictureANImageView.setImageUrl(drink.getPictureId());

        holder.drinkNameTextView.setText(drink.getProductName());
        holder.drinkDescriptionTextView.setText(drink.getDescription());
        holder.drinkPriceTextView.setText(drink.getPrice());
        int rate = (int)Double.parseDouble(drink.getRate());
        holder.drinkRateRatingBar.setRating(rate);

        holder.drinkGoToBarTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                /*Intent intent = new Intent(context, SourceActivity.class);
                intent.putExtras(source.toBundle());
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Drink> setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
        return drinks;
    }


    class DrinkViewHolder extends RecyclerView.ViewHolder{
        ANImageView drinkPictureANImageView;
        TextView drinkNameTextView;
        TextView drinkDescriptionTextView;
        TextView drinkPriceTextView;
        RatingBar drinkRateRatingBar;
        TextView drinkGoToBarTextView;

        public DrinkViewHolder(View itemView) {
            super(itemView);
            drinkPictureANImageView = (ANImageView) itemView.findViewById(R.id.drinkPictureANImageView);
            drinkNameTextView = (TextView) itemView.findViewById(R.id.drinkNameTextView);
            drinkDescriptionTextView = (TextView) itemView.findViewById(R.id.drinkDescriptionTextView);
            drinkPriceTextView = (TextView) itemView.findViewById(R.id.drinkPriceTextView);
            drinkRateRatingBar = (RatingBar) itemView.findViewById(R.id.drinkRateRatingBar);
            drinkGoToBarTextView = (TextView) itemView.findViewById(R.id.drinkGoToBarTextView);
        }
    }
}








































