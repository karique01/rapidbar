package pe.edu.upc.rapidbar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.widget.ANImageView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.models.Drink;
import pe.edu.upc.rapidbar.models.Order;

public class BuyProductAdapter extends RecyclerView.Adapter<BuyProductAdapter.DrinkViewHolderBuy>{

    private List<Drink> drinks;

    public BuyProductAdapter() {
    }

    @Override
    public DrinkViewHolderBuy onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DrinkViewHolderBuy(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_drink_buy, parent,false));
    }

    @Override
    public void onBindViewHolder(DrinkViewHolderBuy holder, int position) {
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
                Order.addProductToCart(drink);

                Toast.makeText(view.getContext(),
                        "Se agregó un "+ drink.getProductName() +" al carrito"
                        ,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    public BuyProductAdapter(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    class DrinkViewHolderBuy extends RecyclerView.ViewHolder{
        ANImageView drinkPictureANImageView;
        TextView drinkNameTextView;
        TextView drinkDescriptionTextView;
        TextView drinkPriceTextView;
        RatingBar drinkRateRatingBar;
        TextView drinkGoToBarTextView;

        public DrinkViewHolderBuy(View itemView) {
            super(itemView);
            drinkPictureANImageView = (ANImageView) itemView.findViewById(R.id.drinkPictureANImageView);
            drinkNameTextView = (TextView) itemView.findViewById(R.id.drinkNameTextView);
            drinkDescriptionTextView = (TextView) itemView.findViewById(R.id.drinkDescriptionTextView);
            drinkPriceTextView = (TextView) itemView.findViewById(R.id.drinkPriceTextView);
            drinkRateRatingBar = (RatingBar) itemView.findViewById(R.id.drinkRateRatingBar);
            drinkGoToBarTextView = (TextView) itemView.findViewById(R.id.addToCartTextView);
        }
    }
}
