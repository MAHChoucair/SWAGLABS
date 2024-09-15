package stepdefinitions;

import com.choucair.app.tasks.AbreLaApp;
import com.choucair.app.models.UserLogin;
import com.choucair.app.questions.LoginQuestion;
import com.choucair.app.tasks.IniciaSesion;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.core.Serenity;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginSteps {

    @Given("^que (.*) desea abrir la aplicacion SwagLab$")
    public void queMelisaDeseaAbrirLaAplicacionSwagLab(String usuario) {
        Serenity.setSessionVariable("firstName").to(usuario);
        theActorCalled(usuario).wasAbleTo(AbreLaApp.swagLabs());
    }

    @When("^se ingresan credenciales correctamente$")
    public void seIngresanCredencialesCorrectamente(DataTable data) {
        theActorInTheSpotlight().attemptsTo(IniciaSesion.correctamente(UserLogin.setData(data).get(0)));
    }

    @Then("^podremos visualizar en la pantalla de home el título (.*)$")
    public void podremosVisualizarEnLaPantallaDeHomeA(String mensaje) {
        theActorInTheSpotlight().should(seeThat(LoginQuestion.verifiedLogin(mensaje)));
    }
}