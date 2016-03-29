package rudzha.org.modularadapter.containers.sectioned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rudzha.org.modularadapter.Notifier;
import rudzha.org.modularadapter.containers.ItemContainer;

/**
 * Item container which is designed to accomodate sections.
 * In addition to basic item list, it also sports a map which maps sectioned items types to sections.
 * Sections are represented by arbitrary objects for which you must provide corresponding {@link rudzha.org.modularadapter.viewholders.BindableViewHolder} in your ViewHolderFactory implementation.
 */
public class SectionedContainer implements ItemContainer {
    private final Map<Class<?>, Section> sectionsMap;
    private final List<Section> itemList;
    private final Section miscSection;
    private boolean isMiscSectionAdded;

    public SectionedContainer() {
        sectionsMap = new HashMap<>();
        itemList = new ArrayList<>();
        miscSection = new Section(null);
        isMiscSectionAdded = false;
    }

    public void addSection(Class<?> sectionItemType, Object sectionInfo) {
        Section section = new Section(sectionInfo);
        sectionsMap.put(sectionItemType, section);
        itemList.add(section);
    }

    public Section getSection(Class<?> sectionItemType) {
        return sectionsMap.get(sectionItemType);
    }

    public Section getSectionByItem(Object item) {
        for(Section section:itemList)
            if(section.sectionInfo.equals(item))
                return section;
        return null;
    }

    @Override
    public void setNotifier(Notifier notifier) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getItem(int index) {
        int skippedCount = 0;
        for(Section section:itemList) {
            int sectionIndex = index - skippedCount;
            if(section.getSize() > sectionIndex) {
                Object object = section.sectionContent.get(sectionIndex);
                try {
                    return (T) object;
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            else
                skippedCount += section.getSize();
        }
        return null;
    }

    @Override
    public void addItem(Object item) {
        //Check if item should belong to section
        Class<?> itemType = item.getClass();
        if(sectionsMap.containsKey(itemType)) {
            Section section = sectionsMap.get(itemType);
            section.sectionContent.add(item);
        }
        else {
            //Just add the item into first level List if no suitable section for given type
            miscSection.sectionContent.add(item);
            if(!isMiscSectionAdded) {
                isMiscSectionAdded = true;
                itemList.add(miscSection);
            }
        }
    }

    @Override
    public void addItems(Object[] items) {
        for(Object item:items)
            addItem(item);
    }

    @Override
    public void addItems(Collection<?> items) {
        for(Object item:items)
            addItem(item);
    }

    @Override
    public int getItemCount() {
        int size = 0;
        for(Section section:itemList) {
            if(!section.isEmpty())
                size += section.getSize();
        }
        return size;
    }

    @Override
    public void clear() {
        for(Section section:itemList) {
            section.clear();
        }
    }
}
