package rudzha.org.modularadapter.viewholders;

/**
 * Buddy Tech, inc.
 * <p/>
 * Created by ruslandzhamaev on  01.02.16.
 */
public interface Selectable {
    void setSelected(boolean isSelected);
    void setSelectionListener(Listener listener);

    interface Listener{
        void onSelected(Object item);
    }
}
