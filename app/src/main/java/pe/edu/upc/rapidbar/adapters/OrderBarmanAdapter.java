package pe.edu.upc.rapidbar.adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.activities.OrderDetailActivity;
import pe.edu.upc.rapidbar.models.Order;

import static android.support.constraint.R.id.parent;


public class OrderBarmanAdapter extends RecyclerView.Adapter<OrderBarmanAdapter.OrderBarmanViewHolder> {

    private Context context;

    private List<Order> orders;


    public OrderBarmanAdapter() {
    }

    public OrderBarmanAdapter(List<Order> orders) {
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
    public OrderBarmanAdapter.OrderBarmanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_order_barman, parent, false);

        return new OrderBarmanAdapter.OrderBarmanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderBarmanAdapter.OrderBarmanViewHolder holder, int position) {
        final Order order = orders.get(position);
        holder.name.setText("Orden número: " + order.getId());

        holder.price.setText("Precio: S/." + order.getTotalAmount());

        holder.seemore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OrderDetailActivity.class);
                intent.putExtras(order.toBundle());
                //1 for drinks
                intent.putExtra("productype",1);
                view.getContext().startActivity(intent);
            }
        });
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

    public class OrderBarmanViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price,seemore;
        public ImageView thumbnail;
        public RelativeLayout viewBackground, viewForeground;

        public OrderBarmanViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.orderName);
            price = view.findViewById(R.id.price);
            thumbnail = view.findViewById(R.id.thumbnail);
            seemore = view.findViewById(R.id.orderBarmanSeeMore);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }

}

