package rudzha.org.modularadapter.demo.viewholder;

import android.view.View;
import android.widget.CheckBox;

import rudzha.org.modularadapter.demo.R;
import rudzha.org.modularadapter.demo.types.Vegetable;
import rudzha.org.modularadapter.viewholders.Selectable;

public class VegetableViewHolder extends SampleViewHolder<Vegetable> implements Selectable {
    private final CheckBox checkBox;

    public VegetableViewHolder(View itemView) {
        super(itemView, Vegetable.class);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
    }

    @Override
    public void setSelected(boolean isSelected) {
        checkBox.setChecked(isSelected);
    }

    @Override
    public void setSelectionListener(final Listener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelected(bindedValue);
            }
        });
    }
}
