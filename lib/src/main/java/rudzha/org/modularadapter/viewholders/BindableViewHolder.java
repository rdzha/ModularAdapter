package rudzha.org.modularadapter.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Buddy Tech, inc.
 * <p/>
 * Created by ruslandzhamaev on  01.02.16.
 */
public abstract class BindableViewHolder<T> extends RecyclerView.ViewHolder {
    private final Class<T> tClass;

    public BindableViewHolder(View itemView, Class<T> tClass) {
        super(itemView);
        this.tClass = tClass;
    }

    public Class<T> getType() {
        return tClass;
    }

    public abstract void bind(T item);

    public void bindObj(Object object) {
        if(tClass.isInstance(object))
            bind(tClass.cast(object));
    }
}
