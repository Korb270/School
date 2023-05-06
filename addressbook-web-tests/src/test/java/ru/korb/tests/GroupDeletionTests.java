package ru.korb.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.korb.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition (){
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test5").withHeader("test21").withFooter("test32"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        List<GroupData> before = app.group().list();
        int index = 0;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        for (int i = 0; i < after.size(); i++){
            Assert.assertEquals(before.get(i), after.get(i));
        }
    }



}
