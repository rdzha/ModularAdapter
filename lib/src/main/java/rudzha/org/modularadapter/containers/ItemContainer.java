package rudzha.org.modularadapter.containers;

import java.util.Collection;

import rudzha.org.modularadapter.Notifier;

/**
 * Abstraction that is designed to accomodate various data structures to needs of ModularAdapter.
 * Two-way communication is provided with Notifier component.
 */
public interface ItemContainer {
    void setNotifier(Notifier notifier);
    <T> T getItem(int index);
    void addItem(Object item);
    void addItems(Object[] items);
    void addItems(Collection<?> items);
    int getItemCount();
    void clear();
}
