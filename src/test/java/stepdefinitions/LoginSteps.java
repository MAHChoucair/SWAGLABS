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


    @Given("^que (.*) desea abrir la aplicacion eribank$")
    public void queDeseaabrirlaaplicacioneribank(String actor) {
        Serenity.setSessionVariable("nombre").to(actor);
        theActorCalled(actor).wasAbleTo(AbreLaApp.eribank());
    }

    @When("^se ingresan credenciales correctamente$")
    public void seIngresanCredencialesCorrectamente(DataTable data) {
        theActorInTheSpotlight().attemptsTo(IniciaSesion.correctamente(UserLogin.setData(data).get(0)));
    }

    @Then("^podremos visualizar en la pantalla de home y ver el mensaje (.*)$")
    public void podremosvisualizarenlapantalladehomeyverelmensaje$(String mensaje) {
        theActorInTheSpotlight().should(seeThat(LoginQuestion.verifiedLogin(mensaje)));
    }

    @When("^se ingresan credenciales incorrectas$" )
    public void seIngresanCredencialesIncorrectas() {

    }

    @Then("^podremos visualizar una alerta de error (.*)$" )
    public void podremosVisualizarUnaAlertaDeErrorMensaje(String mensaje) {
    }
}