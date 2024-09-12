package stepdefinitions;

import static com.choucair.app.userinterface.UIMakePayment.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import com.choucair.app.questions.MakePayQuestions;
import com.choucair.app.tasks.*;

import net.serenitybdd.screenplay.actions.Click;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MakePaymentSteps {
    @When("^el usuario accede a la opción de (.*)$")
    public void elUsuarioAccedeALaOpciónDe(String opcion) {
        theActorInTheSpotlight().attemptsTo(SeleccionaModulo.dePago(opcion));
    }

    @And("^completa los campos requeridos, incluido (.*) como pais$")
    public void completaLosCamposRequeridosParaConfirmarElPago(String pais) {
        theActorInTheSpotlight().attemptsTo(RealizoPago.conElPais(pais));
    }

    @Then("^debería ver un mensaje de confirmación para aceptar el pago$")
    public void deberíaVerUnMensajeDeConfirmaciónParaAceptarElPago() {
        theActorInTheSpotlight().attemptsTo(
                Click.on(ACEPT_BUTTON)
        );
    }

    @And("^al confirmar, debería ver el saldo se actualizo correctamente$")
    public void alConfirmarDeberíaVerElSaldoSeActualizoCorrectamente() {
        theActorInTheSpotlight().should(seeThat(MakePayQuestions.verificarSaldo()));
        //theActorInTheSpotlight().attemptsTo(SeleccionaModulo.dePago("Logout"));
    }

    @And("^completa los campos requeridos con datos incorrectos, incluido (.*) como pais$")
    public void completaLosCamposRequeridosConDatosIncorrectosIncluidoPaisComoPais(String pais) {
    }
}
