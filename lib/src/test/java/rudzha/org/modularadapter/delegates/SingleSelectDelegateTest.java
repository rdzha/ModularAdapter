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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;


@RunWith(MockitoJUnitRunner.class)
public class SingleSelectDelegateTest {
    private SingleSelectDelegate<TestType> singleSelectDelegate;
    private Notifier notifier;

    @Before
    public void setUp() throws Exception {
        singleSelectDelegate = new SingleSelectDelegate<>(TestType.class);
        notifier = Mockito.mock(Notifier.class);
        singleSelectDelegate.setNotifier(notifier);
    }

    @After
    public void tearDown() throws Exception {
        singleSelectDelegate = null;
        notifier = null;
    }


    @Test
    public void testGetSelection_noneAvailable() throws Exception {
        assertThat(singleSelectDelegate.getSelection(), is(nullValue()));
    }

    @Test
    public void testGetSelection_selected() throws Exception {
        TestType testItem = new TestType();
        BindableViewHolder<TestType> viewHolder = Mockito.mock(BindableViewHolder.class, withSettings().extraInterfaces(Selectable.class));
        assertThat(singleSelectDelegate.getSelection(), is(nullValue()));

        singleSelectDelegate.onViewHolderCreated(viewHolder);
        verify((Selectable) viewHolder, times(1)).setSelectionListener(singleSelectDelegate);
        singleSelectDelegate.onSelected(testItem);
        singleSelectDelegate.onViewHolderBinded(viewHolder, testItem);
        assertThat(singleSelectDelegate.getSelection(), is(testItem));
        verify(notifier, times(1)).notifyDataSetChanged();
    }

    @Test
    public void testGetSelection_selected_twice() throws Exception {
        TestType testItem = new TestType();
        BindableViewHolder<TestType> viewHolder = Mockito.mock(BindableViewHolder.class, withSettings().extraInterfaces(Selectable.class));
        assertThat(singleSelectDelegate.getSelection(), is(nullValue()));

        singleSelectDelegate.onViewHolderCreated(viewHolder);
        verify((Selectable) viewHolder, times(1)).setSelectionListener(singleSelectDelegate);
        singleSelectDelegate.onSelected(testItem);
        singleSelectDelegate.onViewHolderBinded(viewHolder, testItem);
        assertThat(singleSelectDelegate.getSelection(), is(testItem));
        verify(notifier, times(1)).notifyDataSetChanged();

        singleSelectDelegate.onSelected(testItem);
        assertThat(singleSelectDelegate.getSelection(), is(testItem));
        verify(notifier, times(2)).notifyDataSetChanged();
    }


    @Test
    public void testGetSelection_selected_reselected() throws Exception {
        TestType testItem = new TestType();
        TestType testItem2 = new TestType();
        BindableViewHolder<TestType> viewHolder = Mockito.mock(BindableViewHolder.class, withSettings().extraInterfaces(Selectable.class));
        assertThat(singleSelectDelegate.getSelection(), is(nullValue()));

        singleSelectDelegate.onViewHolderCreated(viewHolder);
        verify((Selectable) viewHolder, times(1)).setSelectionListener(singleSelectDelegate);
        singleSelectDelegate.onSelected(testItem);
        singleSelectDelegate.onViewHolderBinded(viewHolder, testItem);
        assertThat(singleSelectDelegate.getSelection(), is(testItem));
        verify(notifier, times(1)).notifyDataSetChanged();

        singleSelectDelegate.onSelected(testItem2);
        assertThat(singleSelectDelegate.getSelection(), is(testItem2));
        verify(notifier, times(2)).notifyDataSetChanged();
    }
}