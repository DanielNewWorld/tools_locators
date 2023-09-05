import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class LocatorXPathPrinterTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void printLocatorsXPath() {
        // Открытие страницы, на которой нужно найти локаторы
        driver.get("https://google.com");

        // Поиск всех элементов на странице
        List<WebElement> elements = driver.findElements(By.xpath("//*"));

        int count = 0;
        // Печать локаторов в формате XPath
        for (WebElement element : elements) {
            String displayValue = element.getCssValue("display");

            if (element.isDisplayed() && !displayValue.equals("block")) {
                String xpath = getXPath(element);

                if (!xpath.isEmpty()) {
                    System.out.println(xpath);
                    count++;
                }
            }
        }

        System.out.println("count: " + count);
    }

    @AfterTest
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }

    private String getXPath(WebElement element) {
        // Получение атрибута "id" элемента
        String id = element.getAttribute("id");

        // Получение атрибута "name" элемента
        String name = element.getAttribute("name");

        // Получение атрибута "class" элемента
        String className = element.getAttribute("class");

        // Получение атрибута "tag" элемента
        String tagName = element.getTagName();

        // Формирование XPath на основе атрибутов элемента
        if (id != null && !id.isEmpty()) {
            return "//*[@id='" + id + "']";
        } else if (name != null && !name.isEmpty()) {
            return "//*[@name='" + name + "']";
        } else if (className != null && !className.isEmpty()) {
            return "//*[contains(@class, '" + className + "')]";
        } else {
            return "";
        }

        //cssSelector
        //linkText, partialLinkText
        //tagName
    }
}