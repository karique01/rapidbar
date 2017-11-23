package pe.edu.upc.rapidbar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.models.Order;

/**
 * Created by Sofia on 22/11/2017.
 */

public class OrderWaiterAdapter extends RecyclerView.Adapter<OrderWaiterAdapter.OrderWaiterViewHolder> {


    private Context context;

    private List<Order> orders;


    public OrderWaiterAdapter(List<Order> orders) {
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


    public OrderWaiterAdapter() {
    }

    @Override
    public OrderWaiterAdapter.OrderWaiterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_order_waiter, parent, false);

        return new OrderWaiterAdapter.OrderWaiterViewHolder(itemView);}

    @Override
    public void onBindViewHolder(OrderWaiterAdapter.OrderWaiterViewHolder holder, int position) {

        final Order order = orders.get(position);
        holder.name.setText("Orden número: " + order.getId());

        holder.price.setText("Precio: S/." + order.getTotalAmount());

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

    public class OrderWaiterViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price,seemore;
        public ImageView thumbnail;
        public RelativeLayout viewBackground, viewForeground;

        public OrderWaiterViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.orderName);
            price = view.findViewById(R.id.price);
            //thumbnail = view.findViewById(R.id.thumbnail);
            seemore = view.findViewById(R.id.orderBarmanSeeMore);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }






}
