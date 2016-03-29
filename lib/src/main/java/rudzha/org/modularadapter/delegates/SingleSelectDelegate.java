package rudzha.org.modularadapter.delegates;

import rudzha.org.modularadapter.BehaviorDelegate;
import rudzha.org.modularadapter.viewholders.BindableViewHolder;
import rudzha.org.modularadapter.viewholders.Selectable;

/**
 * Delegate which enables multi-select support for a given type.
 */
public class SingleSelectDelegate<T> extends BehaviorDelegate implements Selectable.Listener {
    private final Class<T> selectableType;
    private T selection;

    public SingleSelectDelegate(Class<T> selectableType) {
        this.selectableType = selectableType;
    }

    public T getSelection() {
        return selection;
    }

    public void setSelection(T selection) {
        this.selection = selection;
    }

    @Override
    public Class<?> getHandledClass() {
        return selectableType;
    }

    @Override
    public void onViewHolderCreated(BindableViewHolder viewHolder) {
        if(viewHolder instanceof Selectable) {
            ((Selectable) viewHolder).setSelectionListener(this);
        }
    }

    @Override
    public void onViewHolderBinded(BindableViewHolder viewHolder, Object item) {
        if((viewHolder instanceof Selectable) && (selectableType.isInstance(item))) {
            ((Selectable) viewHolder).setSelected(item.equals(selection));
        }
    }

    @Override
    public void onSelected(Object item) {
        if(selectableType.isInstance(item)) {
            selection = selectableType.cast(item);
            getNotifier().notifyDataSetChanged();
        }
    }
}
