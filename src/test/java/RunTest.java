import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.annotations.Screenshots;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber"},
        tags = "@DeleteBookingIncorrecto",
        features = {"src/test/resources/features/booking"},
        glue={"steps"}
)
@Test
@Screenshots(forEachAction=true)
public class RunTest {
}
