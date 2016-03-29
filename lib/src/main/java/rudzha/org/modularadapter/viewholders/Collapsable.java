package rudzha.org.modularadapter.viewholders;

/**
 * Buddy Tech, inc.
 * <p/>
 * Created by ruslandzhamaev on  03.02.16.
 */
public interface Collapsable {
    void setIsCollapsed(boolean isCollapsed);
    void setListener(Listener listener);

    interface Listener{
        void onExtendCollapse(Object section);
    }
}
