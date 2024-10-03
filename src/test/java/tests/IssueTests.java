package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

@Feature("Проверка Issues")
@DisplayName("Проверка Issues")
public class IssueTests extends BaseTests {

    public static final String dataSearch = "qa_guru_14_10";
    public static final String issueName = "Issue for Autotest";

    @Test
    @Story("Проверка на 'чистом Selenide'")
    @Owner("Petyukov Alexander")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Test page", url = "https://github.com/qa-guru/qa_guru_14_10/issues")
    @DisplayName("Проверка на 'чистом Selenide', что в Issues содержится Issue с именем '" + issueName + "'.")
    void onlySelenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("/");
        $(".search-input").click();
        $("#query-builder-test").setValue(dataSearch).pressEnter();
        $("[data-testid='results-list'] a[href='/qa-guru/qa_guru_14_10']").click();
        $("a#issues-tab").click();
        $("div.js-navigation-container").shouldHave(text(issueName));
    }

    @Test
    @Story("Проверка с использованием lambda-выражений")
    @Owner("Petyukov Alexander")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Test page", url = "https://github.com/qa-guru/qa_guru_14_10/issues")
    @DisplayName("Проверка с использованием lambda-выражений, что в Issues содержится Issue с именем '" + issueName + "'.")
    void lambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу Github.", () -> {
            open("/");
        });
        step("В поле поиска пишем '" + dataSearch + "' и нажимаем [enter].", () -> {
            $(".search-input").click();
            $("#query-builder-test").setValue(dataSearch).pressEnter();
        });
        step("Ищем в списке нужную ссылку и кликаем на неё.", () -> {
            $("[data-testid='results-list'] a[href='/qa-guru/qa_guru_14_10']").click();
        });
        step("Переходим на вкладку 'Issues'.", () -> {
            $("a#issues-tab").click();
        });
        step("Проверяем, что есть Issue с именем '" + issueName + "'.", () -> {
            $("div.js-navigation-container").shouldHave(text("Issue for Autotest"));
            attachment("Source", webdriver().driver().source());
        });
    }

    @Test
    @Story("Проверка с использованием аннотации @Step")
    @Owner("Petyukov Alexander")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Test page", url = "https://github.com/qa-guru/qa_guru_14_10/issues")
    @DisplayName("Проверка с использованием аннотации @Step, что в Issues содержится Issue с именем '" + issueName + "'.")
    void stepAnnotationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps
                .openMainPage()
                .searchRepositories(dataSearch)
                .selectRepository()
                .selectIssuesTab()
                .checkIssueName(issueName)
                .takeScreenshot();
    }
}