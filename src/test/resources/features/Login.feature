@Login
Feature: Se desea ingresar a la aplicacion SawLab
  As Nicolas
  I want login into SawLab
  So that I can used the app

  @LoginExitoso
  Scenario Outline: Se desea realizar login exitoso SawLab para poder verificar el balance
    Given que Melisa desea abrir la aplicacion SwagLab
    When se ingresan credenciales correctamente
      | usuario   | contrasena   |
      | <usuario> | <contrasena> |
    Then podremos visualizar en la pantalla de home el título PRODUCTS

    Examples:
      | usuario       | contrasena   |
      | standard_user | secret_sauce |

