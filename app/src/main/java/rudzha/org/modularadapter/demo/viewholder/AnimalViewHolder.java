package rudzha.org.modularadapter.demo.viewholder;

import android.view.View;
import android.widget.RadioButton;

import rudzha.org.modularadapter.demo.R;
import rudzha.org.modularadapter.demo.types.Animal;
import rudzha.org.modularadapter.viewholders.Selectable;

public class AnimalViewHolder extends SampleViewHolder<Animal> implements Selectable {
    private final RadioButton radioButton;

    public AnimalViewHolder(View itemView) {
        super(itemView, Animal.class);
        radioButton = (RadioButton) itemView.findViewById(R.id.radio);
    }

    @Override
    public void setSelected(boolean isSelected) {
        radioButton.setChecked(isSelected);
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
