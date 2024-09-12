@MortgageRequest
Feature: Solicitar una hipoteca en Eribank

  Background:
    Given que Juan Aguirre desea abrir la aplicacion eribank
    And se ingresan credenciales correctamente
      | usuario | contrasena |
      | company | company    |

  @RequestExitosa
  Scenario Outline: Enviar una solicitud de hipoteca exitosamente
    When el usuario accede a la opción de Mortgage Request
    And completa los datos personales requeridos en la solicitud
      | age   | address1   | address2   | pais   |
      | <age> | <address1> | <address2> | <pais> |
    And selecciona los siguientes datos para poder enviar la solicitud correctamente
      | typeofloan   | numberyear   | typeofocupation   | yearlyIncome   |
      | <typeofloan> | <numberyear> | <typeofocupation> | <yearlyIncome> |
    Then podremos visualizar en la pantalla de home y ver el mensaje <mensaje>

    Examples:
      | age | address1             | address2 | pais    | typeofloan | numberyear | typeofocupation | yearlyIncome | mensaje         |
      | 18  | calle miraflores 174 | chiclayo | Iceland | Car        | 10         | Private Job     | 20,00,000    | Your balance is |

  @RequestFallida
  Scenario Outline: Enviar una solicitud de hipoteca fallida
    When el usuario accede a la opción de Mortgage Request
    And completa los datos personales requeridos en la solicitud
      | age   | address1   | address2   | pais   |
      | <age> | <address1> | <address2> | <pais> |
    And selecciona los siguientes datos para poder enviar la solicitud correctamente
      | typeofloan   | numberyear   | typeofocupation   | yearlyIncome   |
      | <typeofloan> | <numberyear> | <typeofocupation> | <yearlyIncome> |
    Then podremos visualizar un mensaje de error <mensaje>

    Examples:
      | age | address1 | address2 | pais | typeofloan | numberyear | typeofocupation | yearlyIncome | mensaje                    |
      | 18  |          | chiclayo |      | Car        | 10         | Private Job     | 20,00,000    | Please complete all fields |
