package ru.korb.tests;

import org.testng.annotations.Test;
import ru.korb.model.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test5", "test21", "test32"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
