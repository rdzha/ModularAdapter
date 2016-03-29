package rudzha.org.modularadapter;

import android.view.ViewGroup;

import rudzha.org.modularadapter.viewholders.BindableViewHolder;

/**
 * Abstract component which is responsible for mapping view types and creating {@link BindableViewHolder} instances.
 */
public interface ViewHolderFactory {
    int getItemType(Object item);
    BindableViewHolder getViewHolder(ViewGroup parent, int type);
}
