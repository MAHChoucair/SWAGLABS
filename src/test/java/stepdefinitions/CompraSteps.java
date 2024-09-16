package stepdefinitions;

import static com.choucair.app.userinterface.UICheckoutOver.BACK_HOME_BTN;
import static com.choucair.app.userinterface.UIPageHome.MODO_VISTA;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import com.choucair.app.interactions.SwipeToElement;
import com.choucair.app.models.UserInformation;
import com.choucair.app.questions.CompraExitosaQuestions;
import com.choucair.app.tasks.*;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

import java.util.logging.Logger;

public class CompraSteps {

    @When("^realizo la compra de 3 productos aleatorios$")
    public void realizoLaCompraDeProductosAleatorios(DataTable dataTable) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(MODO_VISTA, isVisible()).forNoMoreThan(30).seconds(),
                Click.on(MODO_VISTA)
        );
        List<Map<String, String>> list = dataTable.asMaps();
        List<String> productosValidosList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String productosValidos = list.get(i).get("Producto");
            productosValidosList.add(productosValidos);
            Logger.getAnonymousLogger().info("Selecciono el producto: " + productosValidos);
            theActorInTheSpotlight().attemptsTo(
                    Compra.deProductos(productosValidos)

            );
        }
        Serenity.setSessionVariable("productosValidos").to(productosValidosList);
    }

    @And("^nos dirigimos al carrito de compras a validar nuestra compra$")
    public void nosDirigimosAlCarritoDeCompras() {
        theActorInTheSpotlight().attemptsTo(RevisaCarrito.deCompras());
    }

    @And("^ingreso los siguientes datos en checkout_information$")
    public void ingresoLosSiguientesDatosEnCheckout_information(DataTable data) {
        String usuario = Serenity.sessionVariableCalled("firstName").toString();

        UserInformation user = UserInformation.setData(data).get(0);
        user.setFirstname(usuario);
        theActorInTheSpotlight().attemptsTo(CheckoutInfo.deInformacion(user));
    }

    @And("^confirmo mi compra en checkout_overview$")
    public void confirmoMiCompraEnCheckout_overview() {
        theActorInTheSpotlight().attemptsTo(CheckoutOver.deCompra());
    }

    @Then("^debería mostrar el mensaje de (.*)$")
    public void deberíaMostrarElMensajeDe(String mensaje) {
        String mensajeUpper = mensaje.toUpperCase();
        theActorInTheSpotlight().should(seeThat(CompraExitosaQuestions.verified(mensajeUpper)));
        theActorInTheSpotlight().attemptsTo(
                SwipeToElement.with("BACK HOME", "test-CHECKOUT: COMPLETE!"),
                Click.on(BACK_HOME_BTN)
        );
    }
}
