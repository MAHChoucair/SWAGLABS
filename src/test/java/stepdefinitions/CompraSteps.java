package stepdefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import com.choucair.app.tasks.Compra;

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
        List<Map<String, String>> list = dataTable.asMaps();
        for (int i = 0; i < list.size(); i++) {
            Logger.getAnonymousLogger().info("Selecciono el producto: " + list.get(i).get("Producto"));
            theActorInTheSpotlight().attemptsTo(
                    (Compra.deProductos(list.get(i).get("Producto")))
            );
        }
    }

    @And("nos dirigimos al carrito de compras")
    public void nosDirigimosAlCarritoDeCompras() {

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
