import com.codeborne.selenide.Condition;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeliveryCard {

    @Test
    public void positiveTest() {
        open("http://localhost:9999");
        $(By.cssSelector("[placeholder='Город']")).sendKeys("Москва");
        $(By.cssSelector("[name='name']")).sendKeys("Ермолина Валерия");
        $(By.cssSelector("[name='phone']")).sendKeys("+79666666666");
        $(By.cssSelector("[class='checkbox__text']")).click();
        $(By.cssSelector("button.button")).click();
        $("[class='notification__title']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        String text = $("[class='notification__title']").getText();
        assertEquals("Успешно!", text);
    }
}

