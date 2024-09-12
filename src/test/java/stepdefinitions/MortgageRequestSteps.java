package stepdefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import com.choucair.app.models.DatosSolicitudMortgage;
import com.choucair.app.models.UserMortgage;
import com.choucair.app.tasks.IngresaDatosPersonales;
import com.choucair.app.tasks.IngresaDatosRequeridos;

import net.serenitybdd.core.Serenity;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MortgageRequestSteps {
    @And("^completa los datos personales requeridos en la solicitud$")
    public void completaLosDatosPersonalesRequeridosDeLaSolicitud(DataTable data) {
        String usuario = Serenity.sessionVariableCalled("nombre");
        String[] parts = usuario.split(" ");
        String name = parts[0];
        String lastname = parts[1];


        UserMortgage userMortgage = UserMortgage.setData(data).get(0);
        userMortgage.setName(name);
        userMortgage.setLastname(lastname);

        theActorInTheSpotlight().attemptsTo(IngresaDatosPersonales.deSolicitudHipoteca(userMortgage));
    }

    @And("^selecciona los siguientes datos para poder enviar la solicitud correctamente$")
    public void seleccionaLosSiguientesDatosParaPoderEnviarLaSolicitudCorrectamente(DataTable data) {
        theActorInTheSpotlight().attemptsTo(IngresaDatosRequeridos.deSolicitudHipoteca(DatosSolicitudMortgage.setData(data).get(0)));
    }

    @Then("^podremos visualizar un mensaje de error (.*)$")
    public void podremosVisualizarUnMensajeDeErrorMensaje(String mensaje) {
        //theActorInTheSpotlight().should(seeThat(VerificaMensajeError.enPantalla(), equalTo(mensaje)));
    }
}
