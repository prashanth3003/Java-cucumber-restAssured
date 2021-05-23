package com.jpm.Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(glue={"com.jpm.StepDefinitions"}, 
				 features = "src/test/resources/features", 
				 plugin = { "pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json" })

public class CucumberRunner {

}
