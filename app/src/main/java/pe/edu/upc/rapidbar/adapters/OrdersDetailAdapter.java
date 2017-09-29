package pe.edu.upc.rapidbar.adapters;

import android.content.Context;
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
import pe.edu.upc.rapidbar.models.Order;
import pe.edu.upc.rapidbar.models.OrderDetail;

public class OrdersDetailAdapter extends RecyclerView.Adapter<OrdersDetailAdapter.OrderDetailViewHolder> {

    List<OrderDetail> orderDetails;

    public OrdersDetailAdapter() {
    }

    public OrdersDetailAdapter(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public List<OrderDetail> setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
        return orderDetails;
    }

    @Override
    public OrderDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrdersDetailAdapter.OrderDetailViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_order_detail, parent,false));
    }

    @Override
    public void onBindViewHolder(OrderDetailViewHolder holder, int position) {
        final OrderDetail order = orderDetails.get(position);
        holder.drinkPictureANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.drinkPictureANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.drinkPictureANImageView.setImageUrl(order.getPictureId());

        holder.drinkNameTextView.setText(order.getProductName());
        holder.drinkDescriptionTextView.setText(order.getDescription());
        holder.drinkPriceTextView.setText(holder.itemView.getResources().getString(R.string.tittle_drink_price)+": "+order.getPrice()+
                " - "+ holder.itemView.getResources().getString(R.string.tittle_detail_quantity) +": "+ order.getQuantity());
        int rate = (int)Double.parseDouble(order.getRate());
        holder.drinkRateRatingBar.setRating(rate);

        double total = Double.parseDouble(order.getQuantity())*Double.parseDouble(order.getPrice());
        holder.orderDetailTotalAmountTextView.setText(String.valueOf(total));
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    class OrderDetailViewHolder extends RecyclerView.ViewHolder{
        ANImageView drinkPictureANImageView;
        TextView drinkNameTextView;
        TextView drinkDescriptionTextView;
        TextView drinkPriceTextView;
        RatingBar drinkRateRatingBar;
        TextView orderDetailTotalAmountTextView;

        public OrderDetailViewHolder(View itemView) {
            super(itemView);
            drinkPictureANImageView = (ANImageView) itemView.findViewById(R.id.drinkPictureANImageView);
            drinkNameTextView = (TextView) itemView.findViewById(R.id.drinkNameTextView);
            drinkDescriptionTextView = (TextView) itemView.findViewById(R.id.drinkDescriptionTextView);
            drinkPriceTextView = (TextView) itemView.findViewById(R.id.drinkPriceTextView);
            drinkRateRatingBar = (RatingBar) itemView.findViewById(R.id.drinkRateRatingBar);
            orderDetailTotalAmountTextView = (TextView) itemView.findViewById(R.id.orderDetailTotalAmountTextView);
        }
    }
}
