package ru.korb.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.korb.model.GroupData;
import ru.korb.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition (){
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test5").withHeader("test21").withFooter("test32"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        Groups before = app.group().all();
        GroupData deleteGroup = before.iterator().next();
        app.group().delete(deleteGroup);
        assertEquals(app.group().count(), before.size() - 1);
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withOut(deleteGroup)));
    }
}
