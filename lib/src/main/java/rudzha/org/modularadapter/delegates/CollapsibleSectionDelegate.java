package rudzha.org.modularadapter.delegates;

import android.util.Log;

import rudzha.org.modularadapter.BehaviorDelegate;
import rudzha.org.modularadapter.containers.sectioned.Section;
import rudzha.org.modularadapter.containers.sectioned.SectionedContainer;
import rudzha.org.modularadapter.viewholders.BindableViewHolder;
import rudzha.org.modularadapter.viewholders.Collapsable;


/**
 * Enables support for collapsible sections.
 */
public class CollapsibleSectionDelegate<T> extends BehaviorDelegate implements Collapsable.Listener {
    private static final String TAG = "CollapsibleSection";
    private final Class<T> sectionedType;
    private final SectionedContainer sectionedContainer;

    public CollapsibleSectionDelegate(Class<T> sectionedType, SectionedContainer sectionedContainer) {
        this.sectionedType = sectionedType;
        this.sectionedContainer = sectionedContainer;
    }

    @Override
    public Class<?> getHandledClass() {
        return sectionedType;
    }

    @Override
    public void onViewHolderCreated(BindableViewHolder viewHolder) {
        if(viewHolder instanceof Collapsable) {
            ((Collapsable) viewHolder).setListener(this);
        }
    }

    @Override
    public void onViewHolderBinded(BindableViewHolder viewHolder, Object item) {
        if(viewHolder instanceof Collapsable) {
            Section section = sectionedContainer.getSectionByItem(item);
            ((Collapsable) viewHolder).setIsCollapsed(section.isHidden());
        }
    }

    @Override
    public void onExtendCollapse(Object sectionItem) {
        Section section = sectionedContainer.getSectionByItem(sectionItem);
        if(section != null) {
            section.setHidden(!section.isHidden());
            getNotifier().notifyDataSetChanged();
        }
        else
            Log.w(TAG, "Item " + sectionItem.toString() + " wasn't found as section");
    }
}
