package rudzha.org.modularadapter.delegates;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import rudzha.org.modularadapter.Notifier;
import rudzha.org.modularadapter.TestType;
import rudzha.org.modularadapter.viewholders.BindableViewHolder;
import rudzha.org.modularadapter.viewholders.Selectable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;

@RunWith(MockitoJUnitRunner.class)
public class MultiSelectDelegateTest {
    private MultiSelectDelegate<TestType> multiSelectDelegate;
    private Notifier notifier;


    @Before
    public void setUp() throws Exception {
        multiSelectDelegate = new MultiSelectDelegate<>(TestType.class);
        notifier = Mockito.mock(Notifier.class);
        multiSelectDelegate.setNotifier(notifier);
    }

    @After
    public void tearDown() throws Exception {
        multiSelectDelegate = null;
        notifier = null;
    }

    @Test
    public void testGetSelection_noneAvailable() throws Exception {
        assertThat(multiSelectDelegate.getSelection(), is(empty()));
    }


    @Test
    public void testGetSelection_selected() throws Exception {
        TestType testItem = new TestType();
        BindableViewHolder<TestType> viewHolder = Mockito.mock(BindableViewHolder.class, withSettings().extraInterfaces(Selectable.class));

        assertThat(multiSelectDelegate.getSelection(), is(empty()));
        multiSelectDelegate.onViewHolderCreated(viewHolder);
        verify((Selectable) viewHolder, times(1)).setSelectionListener(multiSelectDelegate);

        multiSelectDelegate.onViewHolderBinded(viewHolder, testItem);
        verify((Selectable) viewHolder, times(1)).setSelected(false);

        multiSelectDelegate.onSelected(testItem);
        verify(notifier, times(1)).notifyDataSetChanged();
        assertThat(multiSelectDelegate.getSelection(), contains(testItem));
    }
    @Test
    public void testGetSelection_preselected_unselected() throws Exception {
        TestType testItem = new TestType();
        BindableViewHolder<TestType> viewHolder = Mockito.mock(BindableViewHolder.class, withSettings().extraInterfaces(Selectable.class));

        multiSelectDelegate.onViewHolderCreated(viewHolder);
        verify((Selectable) viewHolder, times(1)).setSelectionListener(multiSelectDelegate);
        multiSelectDelegate.onSelected(testItem);
        multiSelectDelegate.onViewHolderBinded(viewHolder, testItem);
        assertThat(multiSelectDelegate.getSelection(), contains(testItem));

        multiSelectDelegate.onSelected(testItem);
        assertThat(multiSelectDelegate.getSelection(), not(contains(testItem)));

    }
}