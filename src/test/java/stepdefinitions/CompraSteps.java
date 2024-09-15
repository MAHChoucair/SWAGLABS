package stepdefinitions;

import static com.choucair.app.userinterface.UIPageHome.MODO_VISTA;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

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

    @And("nos dirigimos al carrito de compras")
    public void nosDirigimosAlCarritoDeCompras() {
        theActorInTheSpotlight().attemptsTo(RevisaCarrito.deCompras());
    }

    @Then("Valido que los nombres de los productos agregados sean igual que en el carro")
    public void validoQueLosNombresDeLosProductosAgregadosSeanIgualQueEnElCarro() {

    }

    @And("Valido que el precio de los productos agregados sean igual que en el carro")
    public void validoQueElPrecioDeLosProductosAgregadosSeanIgualQueEnElCarro() {

    }

    @And("Valido que el numero de productos agregados debe ser igual que en el carrito")
    public void validoQueElNumeroDeProductosAgregadosDebeSerIgualQueEnElCarrito() {
    }
}
