package pe.edu.upc.rapidbar.adapters;

/**
 * Created by Stefano on 17/11/2017.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.models.Order;

public class OrderChefAdapter extends RecyclerView.Adapter<OrderChefAdapter.OrderChefViewHolder>{

    private Context context;

    private List<Order> orders;

    public OrderChefAdapter() {
    }

    public OrderChefAdapter( List<Order> orders) {
        //this.context = context;
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> setOrders(List<Order> orders) {
        this.orders = orders;
        return orders;
    }

    @Override
    public OrderChefViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_order_chef, parent, false);

        return new OrderChefViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderChefViewHolder holder, final int position) {
        final Order order = orders.get(position);
        holder.name.setText("Orden número: " + order.getId());

        holder.price.setText("Precio: S/." + order.getTotalAmount());
/*
        Glide.with(context)
                .load(item.getThumbnail())
                .into(holder.thumbnail);*/
    }
    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void removeItem(int position) {
        orders.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Order order, int position) {
        orders.add(position, order);
        // notify item added by position
        notifyItemInserted(position);
    }

    public class OrderChefViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price,seemore;
        public ImageView thumbnail;
        public RelativeLayout viewBackground, viewForeground;

        public OrderChefViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.orderName);
            price = view.findViewById(R.id.price);
            thumbnail = view.findViewById(R.id.thumbnail);
            seemore = view.findViewById(R.id.orderChefSeeMore);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }




}
