package ru.korb.appmanager;

import org.openqa.selenium.By;
import ru.korb.model.UserData;

public class AdminHelper extends BaseHelper {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void adminLogin(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[value='Вход']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Вход']"));
    }

    public void changeUserPass(UserData user) {
        click(By.xpath("//li[7]/a/i"));
        click(By.xpath("//a[contains(text(),'Управление пользователями')]"));
        click(By.xpath("//a[contains(text(),'"+ user.getUsername() +"')]"));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }
}
