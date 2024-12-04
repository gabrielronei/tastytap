package br.com.fiap.tastytap.bdd;

import br.com.fiap.tastytap.domain.user.CPF;
import io.cucumber.java.en.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

public class CreateUserCucumberTest {

    private String cpf;
    private CPF cpfObject;


    @Given("A new cpf = {string}")
    public void a_new_cpf(String cpf) {
        this.cpf = cpf;
    }

    @When("create the object")
    public void createTheObject() {
        this.cpfObject = new CPF(cpf);
    }

    @Then("should create the cpf correctly")
    public void shouldCreateTheCPFCorrectly() {
        Assertions.assertNotNull(this.cpfObject);
    }
}

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", features = "src/test/java/resources/features")
class CoreCucumberRunnerTest {
}
