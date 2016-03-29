package rudzha.org.modularadapter.demo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import rudzha.org.modularadapter.ViewHolderFactory;
import rudzha.org.modularadapter.demo.types.Animal;
import rudzha.org.modularadapter.demo.types.ArtStyle;
import rudzha.org.modularadapter.demo.types.Section;
import rudzha.org.modularadapter.demo.types.Vegetable;
import rudzha.org.modularadapter.demo.viewholder.AnimalViewHolder;
import rudzha.org.modularadapter.demo.viewholder.SampleViewHolder;
import rudzha.org.modularadapter.demo.viewholder.SectionViewHolder;
import rudzha.org.modularadapter.demo.viewholder.VegetableViewHolder;
import rudzha.org.modularadapter.viewholders.BindableViewHolder;

public class SampleViewHolderFactory implements ViewHolderFactory {
    private static final int TYPE_SECTION = 1;
    private static final int TYPE_ANIMAL = 2;
    private static final int TYPE_VEGETABLE = 3;
    private static final int TYPE_ART_STYLE = 4;

    private static ViewHolderFactory viewHolderFactory;

    @Override
    public int getItemType(Object item) {
        if(item instanceof Section)
            return TYPE_SECTION;
        else if(item instanceof Animal)
            return TYPE_ANIMAL;
        else if(item instanceof Vegetable)
            return TYPE_VEGETABLE;
        else if(item instanceof ArtStyle)
            return TYPE_ART_STYLE;
        else
            throw new UnsupportedOperationException("Unsupported object type!");
    }

    @Override
    public BindableViewHolder getViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (type) {
            case TYPE_SECTION:
                return new SectionViewHolder(inflater.inflate(R.layout.item_section, parent, false));
            case TYPE_ANIMAL:
                return new AnimalViewHolder(inflater.inflate(R.layout.item_radio, parent, false));
            case TYPE_VEGETABLE:
                return new VegetableViewHolder(inflater.inflate(R.layout.item_checkbox, parent, false));
            case TYPE_ART_STYLE:
                return new SampleViewHolder<>(inflater.inflate(R.layout.item, parent, false), ArtStyle.class);
            default:
                throw new UnsupportedOperationException("Unsupported object type!");
        }
    }

    public static ViewHolderFactory getInstance() {
        if(viewHolderFactory == null)
            viewHolderFactory = new SampleViewHolderFactory();
        return viewHolderFactory;
    }
}
