package rudzha.org.modularadapter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import rudzha.org.modularadapter.ModularAdapter;
import rudzha.org.modularadapter.ViewHolderFactory;
import rudzha.org.modularadapter.containers.sectioned.SectionedContainer;
import rudzha.org.modularadapter.delegates.MultiSelectDelegate;
import rudzha.org.modularadapter.delegates.SingleSelectDelegate;
import rudzha.org.modularadapter.delegates.CollapsibleSectionDelegate;
import rudzha.org.modularadapter.demo.types.Animal;
import rudzha.org.modularadapter.demo.types.ArtStyle;
import rudzha.org.modularadapter.demo.types.Section;
import rudzha.org.modularadapter.demo.types.Vegetable;

public class SampleActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private static final Animal[] ANIMALS = new Animal[] {new Animal("Cat"), new Animal("Dog"), new Animal("Zebra"), new Animal("Elephant"), new Animal("Owl"), new Animal("Bear")};
    private static final Vegetable[] VEGETABLES = new Vegetable[] {new Vegetable("Carrot"), new Vegetable("Onion"), new Vegetable("Cabbage"), new Vegetable("Tomato")};
    private static final ArtStyle[] ART_STYLES = new ArtStyle[] {new ArtStyle("Realism"), new ArtStyle("Romantism"), new ArtStyle("Art Noveau"), new ArtStyle("Surrealism")};

    private SingleSelectDelegate<Animal> animalSingleSelectDelegate;
    private MultiSelectDelegate<Vegetable> vegetableMultiSelectDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sample");
        toolbar.inflateMenu(R.menu.menu_sample);
        toolbar.setOnMenuItemClickListener(this);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewHolderFactory viewHolderFactory = SampleViewHolderFactory.getInstance();
        SectionedContainer itemContainer = new SectionedContainer();
        itemContainer.addSection(Animal.class, new Section("Animals:"));
        itemContainer.addSection(Vegetable.class, new Section("Vegetables:"));
        animalSingleSelectDelegate = new SingleSelectDelegate<>(Animal.class);
        vegetableMultiSelectDelegate = new MultiSelectDelegate<>(Vegetable.class);
        CollapsibleSectionDelegate<Section> collapsibleSectionDelegate = new CollapsibleSectionDelegate<>(Section.class, itemContainer);

        ModularAdapter adapter = new ModularAdapter(itemContainer, viewHolderFactory, animalSingleSelectDelegate, vegetableMultiSelectDelegate, collapsibleSectionDelegate);
        adapter.addItems(ART_STYLES);
        adapter.addItems(ANIMALS);
        adapter.addItems(VEGETABLES);
        adapter.addItem(new Animal("Panda"));

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sel_animals:
                Animal selection = animalSingleSelectDelegate.getSelection();
                if(selection != null)
                    Toast.makeText(this, selection.toString(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_sel_vegetables:
                Toast.makeText(this, TextUtils.join(", ", vegetableMultiSelectDelegate.getSelection()), Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
