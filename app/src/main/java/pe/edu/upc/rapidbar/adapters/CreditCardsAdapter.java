package pe.edu.upc.rapidbar.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.rapidbar.R;
import pe.edu.upc.rapidbar.models.Bar;
import pe.edu.upc.rapidbar.models.CreditCard;


public class CreditCardsAdapter extends RecyclerView.Adapter<CreditCardsAdapter.CreditCardViewHolder> {
    List<CreditCard> creditCards;

    public CreditCardsAdapter() {
    }

    public CreditCardsAdapter(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public List<CreditCard> setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
        return creditCards;
    }

    @Override
    public CreditCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CreditCardsAdapter.CreditCardViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_credit_card, parent,false));
    }

    @Override
    public void onBindViewHolder(CreditCardViewHolder holder, int position) {
        final CreditCard creditCard = creditCards.get(position);
        holder.creditCardNumberTextView.setText(holder.itemView.getResources()
                .getString(R.string.tittle_number_credit_card)+" "+
                creditCard.getNumber());
        holder.expirationDateTextView.setText(holder.itemView.getResources()
                .getString(R.string.tittle_expiration_date_credit_card)+" "+
                creditCard.getExpirationDateFormatted());
    }

    @Override
    public int getItemCount() {
        return creditCards.size();
    }

    class CreditCardViewHolder extends RecyclerView.ViewHolder{
        TextView creditCardNumberTextView;
        TextView expirationDateTextView;

        public CreditCardViewHolder(View itemView) {
            super(itemView);
            creditCardNumberTextView = (TextView) itemView.findViewById(R.id.creditCardNumberTextView);
            expirationDateTextView = (TextView) itemView.findViewById(R.id.expirationDateTextView);
        }
    }
}
