package com.vytrack.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json","html:target/default-html-reports",
                "rerun:target/rerun.txt"},
        features = "src/test/resources/features/",
        glue = "com/vytrack/step_definitions",
        dryRun = false,

        tags = "@wip"
        //tags = "@sales_manager or @store_manager" //{"@driver","VYT-123"} this is for old version this is for "and logic"
        //in this new version, just use "and" instead of "or"

        //"@login and not @wip" that means work tests having login tag excluding for tests having wip tag
            //{"@login","~@wip"} this is working for old version it s same meaning with the above one
)
public class CukesRunner {
}
