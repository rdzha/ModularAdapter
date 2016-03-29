package rudzha.org.modularadapter.delegates;

import java.util.HashSet;
import java.util.Set;

import rudzha.org.modularadapter.BehaviorDelegate;
import rudzha.org.modularadapter.viewholders.BindableViewHolder;
import rudzha.org.modularadapter.viewholders.Selectable;

/**
 * Delegate which enables multi-select support for a given type.
 */
public class MultiSelectDelegate<T> extends BehaviorDelegate implements Selectable.Listener {
    private final Class<T> selectableType;
    private Set<T> selection;

    public MultiSelectDelegate(Class<T> selectableType) {
        this.selectableType = selectableType;
        selection = new HashSet<>();
    }

    public Set<T> getSelection() {
        return selection;
    }

    public void setSelection(Set<T> selection) {
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
            ((Selectable) viewHolder).setSelected(selection.contains(selectableType.cast(item)));
        }
    }

    @Override
    public void onSelected(Object item) {
        if(selectableType.isInstance(item)) {
            T selectableItem = selectableType.cast(item);
            if(selection.contains(selectableItem))
                selection.remove(selectableItem);
            else
                selection.add(selectableItem);
            getNotifier().notifyDataSetChanged();
        }
    }
}
