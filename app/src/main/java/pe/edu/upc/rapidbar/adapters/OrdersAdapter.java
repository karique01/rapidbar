package pe.edu.upc.rapidbar.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.activities.OrderDetailActivity;
import pe.edu.upc.rapidbar.models.Order;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {
    List<Order> orders;

    public OrdersAdapter() {
    }

    public OrdersAdapter(List<Order> orders) {
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
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrdersAdapter.OrderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_order, parent,false));
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        final Order order = orders.get(position);
        holder.orderTotalAmountTextView.setText(holder.itemView.getResources()
                .getString(R.string.tittle_order_total_amount)+" "+
                order.getTotalAmount());
        holder.orderDateTextView.setText(holder.itemView.getResources()
                .getString(R.string.tittle_order_date)+" "+
                order.getDateFormated());
        holder.orderBarNameTextView.setText(holder.itemView.getResources()
                .getString(R.string.tittle_order_bar_name)+" "+
                order.getBarName());
        holder.orderSeeMoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OrderDetailActivity.class);
                intent.putExtras(order.toBundle());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView orderTotalAmountTextView;
        TextView orderDateTextView;
        TextView orderBarNameTextView;
        TextView orderSeeMoreTextView;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderTotalAmountTextView = (TextView) itemView.findViewById(R.id.orderTotalAmountTextView);
            orderDateTextView = (TextView) itemView.findViewById(R.id.orderDateTextView);
            orderBarNameTextView = (TextView) itemView.findViewById(R.id.orderBarNameTextView);
            orderSeeMoreTextView = (TextView) itemView.findViewById(R.id.orderSeeMoreTextView);
        }
    }
}
