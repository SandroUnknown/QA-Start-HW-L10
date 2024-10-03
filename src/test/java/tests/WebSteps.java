package tests;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Открываем главную страницу Github.")
    public WebSteps openMainPage() {
        open("/");
        return this;
    }

    @Step("В поле поиска пишем '{dataSearch}' и нажимаем [enter].")
    public WebSteps searchRepositories(String dataSearch) {
        $(".search-input").click();
        $("#query-builder-test").setValue(dataSearch).pressEnter();
        return this;
    }

    @Step("Ищем в списке нужную ссылку и кликаем на неё.")
    public WebSteps selectRepository() {
        $("[data-testid='results-list'] a[href='/qa-guru/qa_guru_14_10']").click();
        return this;
    }

    @Step("Переходим на вкладку 'Issues'.")
    public WebSteps selectIssuesTab() {
        $("a#issues-tab").click();
        return this;
    }

    @Step("Проверяем, что есть Issue с именем '{issueName}'")
    public WebSteps checkIssueName(String issueName) {
        $("div.js-navigation-container").shouldHave(text(issueName));
        return this;
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}