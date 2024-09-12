@MakePayment
Feature: Realizar un pago en Eribank

  Background:
    Given que Melisa desea abrir la aplicacion eribank
    And se ingresan credenciales correctamente
      | usuario | contrasena |
      | company | company    |

  @PagoExitoso
  Scenario Outline: Realizar un pago exitosamente
    When el usuario accede a la opción de Make Payment
    And completa los campos requeridos, incluido <pais> como pais
    Then debería ver un mensaje de confirmación para aceptar el pago
    And al confirmar, debería ver el saldo se actualizo correctamente

    Examples:
      | pais       |
      | Iceland    |
      | Bangladesh |
      | Ethiopia   |

  @PagoFallido
  Scenario Outline: Realizar un pago fallido
    When el usuario accede a la opción de Make Payment
    And completa los campos requeridos con datos incorrectos, incluido <pais> como pais
    Then podremos visualizar un mensaje de error <mensaje>

    Examples:
      | pais | mensaje                    |
      | USA  | Invalid data               |
      |      | Please complete all fields |
