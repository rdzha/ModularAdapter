package rudzha.org.modularadapter;

import rudzha.org.modularadapter.viewholders.BindableViewHolder;

/**
 * Buddy Tech, inc.
 * <p/>
 * Created by ruslandzhamaev on  01.02.16.
 */
public abstract class BehaviorDelegate {
    private Notifier adapter;

    public void setNotifier(Notifier adapter) {
        this.adapter = adapter;
    }

    protected Notifier getNotifier() {
        return adapter;
    }

    public abstract Class<?> getHandledClass();
    public abstract void onViewHolderCreated(BindableViewHolder viewHolder);
    public abstract void onViewHolderBinded(BindableViewHolder viewHolder, Object item);
}
