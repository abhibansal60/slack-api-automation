package com.slack.api.runner;
import com.slack.api.ServiceConfig;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;

@ContextConfiguration(classes = {ServiceConfig.class})
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue ="com.slack.api.stepdefinitions",
        tags = "@Archive",
        plugin = {"json:target/cucumber-report.json",
                "html:target/cucumber-report",
                "pretty"
                },
        strict = true, monochrome = false
)
public class TestRunner {
}
