package pages.ru.yandex.mail;

import driver.WebDriverManager;
import helpers.CustomAssertions;
import helpers.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

import static driver.WebDriverManager.getDriver;

/**
 * Page Object страницы яндекс почты
 *
 * @author Сергей Хорошков
 */
public class YandexMailPage {
    /**
     * selectorButtonSignInMail - кнопка "Войти в почту"
     * selectorButtonMail - кнопка "Почта"
     * selectorFieldLogin - поле для ввода "Логина"
     * selectorFieldPassword - поле для ввода "Пароля"
     * selectorButtonSign - кнопка "Войти"
     * selectorIconLoading - иконка прогрузки, когда нажимаешь войти
     * selectorCheckBox - чекбоксы у писем
     * selectorNamesDirectories - названия папок "Входящие, Отправленные" и тд.
     * selectorButtonWrite - кнопка написать
     * selectorFieldToWhom - поле кому
     * selectorTheme - тема письма
     * selectorFieldOnMessage - поле ввода сообщения
     * selectorButtonSend - кнопка отправить
     */
    private String selectorButtonSignInMail = "//button[@type='button']";
    private String selectorButtonMail = "//span[text()='Почта']//..";
    private String selectorFieldLogin = "//input[@name='login']";
    private String selectorFieldPassword = "//input[@type='password']";
    private String selectorButtonSign = "//button[@id='passp:sign-in']";
    private String selectorIconLoading = "//div[@class='passp-page-overlay']";
    private String selectorCheckBox = "//label[@data-nb='checkbox']";
    private String selectorNamesDirectories = "//h2[text()='Папки']//..//div[contains(@class, 'LeftColumn')]//a";
    private String selectorButtonDelete = "//span[text()='Удалить']//..";
    private String selectorButtonWrite = "//span[text()='Написать']//..//..";
    private String selectorFieldToWhom = "//div[@title='Кому']";
    private String selectorTheme = "//span[text()='Тема']//..//..//input";
    private String selectorFieldOnMessage = "//div[@role='textbox']";
    private String selectorButtonSend = "//span[text()='Отправить']//..//..//..";

    /**
     * @param userLogin    логин пользователя
     * @param userPassword пароль пользователя
     */
    @Step("Пройти авторизацию и перейти на главную страницу Яндекс Почта")
    public void userAuthorization(String userLogin, String userPassword) {
        WebDriverManager.nextWindow();
        getDriver().findElement(By.xpath(selectorButtonSignInMail)).click();
        getDriver().findElement(By.xpath(selectorButtonMail)).click();
        getDriver().findElement(By.xpath(selectorFieldLogin)).sendKeys(userLogin);
        getDriver().findElement(By.xpath(selectorButtonSign)).click();
        WaitUtils.waitInvisibilityOf(selectorIconLoading);
        getDriver().findElement(By.xpath(selectorFieldPassword)).sendKeys(userPassword);
        getDriver().findElement(By.xpath(selectorButtonSign)).click();
    }

    /**
     * @param tabName       название папки в левом столбце
     * @param countCheckBox количество выделяемых писем
     */
    @Step("Включить чекбокс у {countCheckBox} случайно выбранных писем на вкладке {tabName}")
    public void clickOnCheckBox(String tabName, Integer countCheckBox) {
        WebElement directory = getDriver()
                .findElements(By.xpath(selectorNamesDirectories))
                .stream().filter(e -> e.getAttribute("aria-label").contains(tabName))
                .findFirst().orElse(null);
        CustomAssertions.assertTrue(Objects.nonNull(directory), "Папки с названием «" + tabName + "» не найдено");
        directory.click();
        List<WebElement> listCheckBox = getDriver().findElements(By.xpath(selectorCheckBox));
        CustomAssertions.assertTrue(listCheckBox.size() >= countCheckBox, "Количество писем на странице меньше " + countCheckBox);
        listCheckBox.stream().limit(countCheckBox).forEach(WebElement::click);
    }

    @Step("Нажать кнопку «Удалить»")
    public void deleteMessage() {
        WebElement delButton = getDriver().findElement(By.xpath(selectorButtonDelete));
        CustomAssertions.assertTrue(delButton.getAttribute("aria-disabled").equals("false"),
                "Кнопка «Удалить» недоступна, письмо не выбрано");
        delButton.click();
    }

    /**
     * @param email   почта, на которую будет отправлено письмо
     * @param theme   тема письма
     * @param message текст сообщения
     */
    @Step("Написать сообщение и отправить")
    public void sendMessage(String email, String theme, String message) {
        getDriver().findElement(By.xpath(selectorButtonWrite)).click();
        getDriver().findElement(By.xpath(selectorFieldToWhom)).sendKeys(email);
        getDriver().findElement(By.xpath(selectorTheme)).sendKeys(theme);
        getDriver().findElement(By.xpath(selectorFieldOnMessage)).sendKeys(message);
        getDriver().findElement(By.xpath(selectorButtonSend)).click();
    }
}
