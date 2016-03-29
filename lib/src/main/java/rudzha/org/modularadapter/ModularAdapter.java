package rudzha.org.modularadapter;

import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collection;

import rudzha.org.modularadapter.containers.ArrayListContainer;
import rudzha.org.modularadapter.containers.ItemContainer;
import rudzha.org.modularadapter.viewholders.BindableViewHolder;

/**
 * Main class which assembles the system from all components.
 * Current implementation requires all components to be provided in constructor.
 */
public class ModularAdapter extends RecyclerView.Adapter<BindableViewHolder> implements ItemContainer, Notifier {
    private final ItemContainer itemContainer;
    private final ViewHolderFactory viewHolderFactory;
    private final ArrayMap<Class<?>, BehaviorDelegate> delegateMap;

    public ModularAdapter(ViewHolderFactory viewHolderFactory, BehaviorDelegate... behaviorDelegates) {
        this(new ArrayListContainer(), viewHolderFactory, behaviorDelegates);
    }

    public ModularAdapter(ItemContainer itemContainer, ViewHolderFactory viewHolderFactory, BehaviorDelegate... behaviorDelegates) {
        this.itemContainer = itemContainer;
        this.viewHolderFactory = viewHolderFactory;
        this.delegateMap = new ArrayMap<>();
        itemContainer.setNotifier(this);
        for(BehaviorDelegate behaviorDelegate:behaviorDelegates) {
            behaviorDelegate.setNotifier(this);
            delegateMap.put(behaviorDelegate.getHandledClass(), behaviorDelegate);
        }
    }

    @Override
    public BindableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BindableViewHolder viewHolder = viewHolderFactory.getViewHolder(parent, viewType);
        BehaviorDelegate delegate = delegateMap.get(viewHolder.getType());
        if(delegate != null)
            delegate.onViewHolderCreated(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BindableViewHolder holder, int position) {
        Object item = itemContainer.getItem(position);
        holder.bindObj(item);
        if(item != null) {
            BehaviorDelegate delegate = delegateMap.get(item.getClass());
            if (delegate != null) {
                delegate.onViewHolderBinded(holder, item);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object object = itemContainer.getItem(position);
        return viewHolderFactory.getItemType(object);
    }

    @Override
    public void setNotifier(Notifier notifier) {}

    @Override
    public <T> T getItem(int index) {
        return itemContainer.getItem(index);
    }

    @Override
    public void addItem(Object item) {
        itemContainer.addItem(item);
    }

    @Override
    public void addItems(Object[] items) {
        itemContainer.addItems(items);
    }

    @Override
    public void addItems(Collection<?> items) {
        itemContainer.addItem(items);
    }

    @Override
    public int getItemCount() {
        return itemContainer.getItemCount();
    }

    @Override
    public void clear() {
        itemContainer.clear();
    }


}
