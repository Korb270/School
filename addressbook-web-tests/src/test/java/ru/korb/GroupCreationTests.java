package ru.korb;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test5", "test21", "test32"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
