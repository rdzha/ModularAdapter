package rudzha.org.modularadapter;

/**
 * Abstraction layer which provides {@link BehaviorDelegate} and {@link rudzha.org.modularadapter.containers.ItemContainer} with methods to refresh adapter state.
 */
public interface Notifier {
    void notifyDataSetChanged();
}
