package rudzha.org.modularadapter.containers.sectioned;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import rudzha.org.modularadapter.Notifier;
import rudzha.org.modularadapter.TestType;
import rudzha.org.modularadapter.TestType2;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SectionedContainerTest {
    private SectionedContainer sectionedContainer;
    private Notifier notifier;

    @Before
    public void setUp() throws Exception {
        sectionedContainer = new SectionedContainer();
        notifier = Mockito.mock(Notifier.class);
        sectionedContainer.setNotifier(notifier);
    }

    @After
    public void tearDown() throws Exception {
        sectionedContainer = null;
    }


    @Test
    public void testGetSection() throws Exception {
        sectionedContainer.addSection(TestType.class, "Section 1");
        assertThat(sectionedContainer.getSection(TestType.class).sectionInfo, Matchers.<Object>is("Section 1"));
    }

    @Test
    public void testAddSectionedItems() throws Exception {
        TestType testItem1 = new TestType();
        TestType testItem2 = new TestType();
        TestType testItem3 = new TestType();
        TestType2 anotherTestItem1 = new TestType2();
        TestType2 anotherTestItem2 = new TestType2();
        TestType2 anotherTestItem3 = new TestType2();
        sectionedContainer.addSection(TestType.class, "Section 1");
        sectionedContainer.addSection(TestType2.class, "Section 2");

        sectionedContainer.addItem(anotherTestItem1);
        sectionedContainer.addItem(anotherTestItem2);
        sectionedContainer.addItem(testItem2);
        sectionedContainer.addItem(testItem1);
        sectionedContainer.addItem(anotherTestItem3);
        sectionedContainer.addItem(testItem3);


        assertThat(sectionedContainer.getItem(0), Matchers.<Object>is("Section 1"));
        assertThat(sectionedContainer.getItem(1), Matchers.<Object>is(testItem2));
        assertThat(sectionedContainer.getItem(2), Matchers.<Object>is(testItem1));
        assertThat(sectionedContainer.getItem(3), Matchers.<Object>is(testItem3));

        assertThat(sectionedContainer.getItem(4), Matchers.<Object>is("Section 2"));
        assertThat(sectionedContainer.getItem(5), Matchers.<Object>is(anotherTestItem1));
        assertThat(sectionedContainer.getItem(6), Matchers.<Object>is(anotherTestItem2));
        assertThat(sectionedContainer.getItem(7), Matchers.<Object>is(anotherTestItem3));
    }
 }