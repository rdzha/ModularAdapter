package rudzha.org.modularadapter.containers.sectioned;

import java.util.ArrayList;
import java.util.List;

/**
 * Section container.
 */
public class Section {
    final Object sectionInfo;
    final List<Object> sectionContent;
    private boolean isHidden;

    Section(Object sectionInfo) {
        this.sectionInfo = sectionInfo;
        this.sectionContent = new ArrayList<>();
        if(sectionInfo != null)
            sectionContent.add(sectionInfo);
        isHidden = false;
    }

    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public boolean isEmpty() {
        if(sectionInfo == null)
            return sectionContent.size() == 0;
        else
            return sectionContent.size() == 1;

    }

    public int getSize() {
        if(sectionInfo == null)
            return sectionContent.size();
        else if(isHidden)
            return 1;
        else
            return sectionContent.size() > 1 ? sectionContent.size() : 0;
    }

    public void clear() {
        sectionContent.clear();
        if(sectionInfo != null)
            sectionContent.add(sectionInfo);
    }
}
