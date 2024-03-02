import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeliveryCard {

    @Test
    public void positiveTest() {
        open("http://localhost:9999");
        $(By.cssSelector("[placeholder='Город']")).sendKeys("Москва");
        $(By.cssSelector("[name='name']")).sendKeys("Ермолина Валерия");
        String planningDate = generateDate(4);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $(By.cssSelector("[name='phone']")).sendKeys("+79666666666");
        $(By.cssSelector("[class='checkbox__text']")).click();
        $(By.cssSelector("button.button")).click();
        $("[class='notification__title']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        String text = $("[class='notification__title']").getText();
        assertEquals("Успешно!", text);
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}

