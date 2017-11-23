package pe.edu.upc.rapidbar.helpers;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import pe.edu.upc.rapidbar.adapters.OrderWaiterAdapter;

/**
 * Created by Sofia on 22/11/2017.
 */

public class WaiterRecyclerTouchHelper extends  ItemTouchHelper.SimpleCallback {
    private WaiterRecyclerTouchHelperListener listener;

    public WaiterRecyclerTouchHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }


    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((OrderWaiterAdapter.OrderWaiterViewHolder) viewHolder).viewForeground;

            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((OrderWaiterAdapter.OrderWaiterViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }


    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((OrderWaiterAdapter.OrderWaiterViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().clearView(foregroundView);
    }
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((OrderWaiterAdapter.OrderWaiterViewHolder) viewHolder).viewForeground;

        getDefaultUIUtil().onDraw(c, recyclerView,foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());

    }
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface WaiterRecyclerTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }





}
