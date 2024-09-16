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
      | Producto              |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Onesie     |
    And nos dirigimos al carrito de compras a validar nuestra compra
    And ingreso los siguientes datos en checkout_information
      | lastname | postalcode |
      | Aguirre  | 14001      |
    And confirmo mi compra en checkout_overview
    Then debería mostrar el mensaje de Thank you for you order

