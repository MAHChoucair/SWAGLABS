@Compra
Feature: Compra de productos en SwagLabs

  Background:
    Given que Melisa desea abrir la aplicacion SwagLab
    And se ingresan credenciales correctamente
      | usuario       | contrasena   |
      | standard_user | secret_sauce |

  @CompraExitosa
  Scenario: Se desea realizar una compra exitosa en SwagLab
    When realizo la compra de 3 productos aleatorios
        | Producto                |
      #  | Sauce Labs Backpack     |
      #  | Sauce Labs Bike Light   |
        | Sauce Labs Onesie |
    And nos dirigimos al carrito de compras
    Then Valido que los nombres de los productos agregados sean igual que en el carro
    And Valido que el precio de los productos agregados sean igual que en el carro
    And Valido que el numero de productos agregados debe ser igual que en el carrito

