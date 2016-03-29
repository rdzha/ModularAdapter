package rudzha.org.modularadapter.demo.viewholder;

import android.view.View;
import android.widget.Button;

import rudzha.org.modularadapter.demo.R;
import rudzha.org.modularadapter.demo.types.Section;
import rudzha.org.modularadapter.viewholders.Collapsable;


public class SectionViewHolder extends SampleViewHolder<Section> implements Collapsable {
    private final Button collapseButton;

    public SectionViewHolder(View itemView) {
        super(itemView, Section.class);
        collapseButton = (Button) itemView.findViewById(R.id.collapse_button);
    }

    @Override
    public void setIsCollapsed(boolean isCollapsed) {
        if(isCollapsed)
            collapseButton.setText(R.string.expand);
        else
            collapseButton.setText(R.string.collapse);
    }

    @Override
    public void setListener(final Listener listener) {
        collapseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onExtendCollapse(bindedValue);
            }
        });
    }
}
