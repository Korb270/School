package ru.korb.tests;

import org.testng.annotations.Test;
import ru.korb.model.GroupData;

public class GroupModificationTests extends TestBase{
    @Test
    public void testGroupModofocation(){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test5", "test21", "test32"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
