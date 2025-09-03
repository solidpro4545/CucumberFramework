package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepdefs"},
        plugin = {
                "pretty",                           // prints steps to console
                "html:target/cucumber-reports.html",// basic HTML report
                "json:target/cucumber.json"         // JSON (optional, for CI tools)
        },
        monochrome = true
)
public class TestRunner {}
