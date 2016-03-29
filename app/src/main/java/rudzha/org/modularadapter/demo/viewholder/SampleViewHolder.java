package rudzha.org.modularadapter.demo.viewholder;

import android.view.View;
import android.widget.TextView;

import rudzha.org.modularadapter.demo.R;
import rudzha.org.modularadapter.viewholders.BindableViewHolder;

public class SampleViewHolder<T> extends BindableViewHolder<T> {
    protected final TextView textView;
    protected T bindedValue;

    public SampleViewHolder(View itemView, Class<T> objectClass) {
        super(itemView, objectClass);
        textView = (TextView) itemView.findViewById(R.id.text);
    }

    @Override
    public void bind(T item) {
        this.bindedValue = item;
        textView.setText(item.toString());
    }
}
