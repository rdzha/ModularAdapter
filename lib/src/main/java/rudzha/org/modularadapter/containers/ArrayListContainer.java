package rudzha.org.modularadapter.containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import rudzha.org.modularadapter.Notifier;

/**
 * Basic container implementation that relies on {@link ArrayList}.
 */
public class ArrayListContainer implements ItemContainer {
    private final List<Object> itemList;

    public ArrayListContainer() {
        itemList = new ArrayList<>();
    }

    @Override
    public void setNotifier(Notifier notifier) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getItem(int index) {
        Object object = itemList.get(index);
        try {
            return (T) object;
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override
    public void addItem(Object item) {
        itemList.add(item);
    }

    @Override
    public void addItems(Object[] items) {
        Collections.addAll(itemList, items);
    }

    @Override
    public void addItems(Collection<?> items) {
        itemList.addAll(items);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void clear() {
        itemList.clear();
    }
}
