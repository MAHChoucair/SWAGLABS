# Automatización Móvil con Appium, Screenplay y SerenityBDD
Descripción del Proceso
-
El framework utilizado para la automatización de las pruebas es SerenityBDD, utilizando el patrón de diseño Screenplay. 
<br>Este enfoque permite escribir pruebas más legibles y mantenibles, separando claramente las responsabilidades de los actores, tareas y preguntas.
<br>Link de referencia: https://serenity-bdd.github.io/theserenitybook/latest/index.html

Requisito para ejecutar:

+ **Java 11**
+ **gradle 7 o superior**


Herramientas Utilizadas
-
* **Appium:** Herramienta de automatización para aplicaciones móviles.
* **SerenityBDD:** Framework para la automatización de pruebas que facilita la generación de informes detallados.
* **Screenplay:** Patrón de diseño que organiza las pruebas en términos de actores que realizan tareas y hacen preguntas sobre el estado de la aplicación.


Estructura del Proyecto
-
* **Models:** Clases que representan los datos utilizados en las pruebas.
* **Tasks:** Clases que definen las acciones que los actores pueden realizar.
* **Questions:** Clases que verifican el estado actual de la aplicación. <br>Las respuestas obtenidas de estas preguntas se utilizan para determinar si una prueba ha pasado o fallado.
* **StepsDefinitions:** Clases que mapean los pasos de los escenarios de Cucumber a las tareas y preguntas de Screenplay.
* **Features:** Archivos .feature que contienen los escenarios de prueba escritos en Gherkin.
* **Interactions:** Clases que encapsulan interacciones complejas con la interfaz de usuario que no se consideran tareas completas.
* **UserInterface:** Clases que representan los elementos de la interfaz de usuario con los que los actores pueden interactuar.


Funcionalidades y Escenarios Cubiertos
-
**Funcionalidad: Login**

**Descripción:** Verifica la capacidad del usuario para iniciar sesión en la aplicación.

Escenarios:
1. [ ] Inicio de sesión exitoso con credenciales válidas

**Funcionalidad: Comprar**

**Descripción:** Verifica la capacidad del usuario para comprar uno o varios productos en la aplicación.

Escenarios:
1. [ ] Flujo E2E de compra de uno o varios productos exitosamente.

**Escenarios adicionales a probar**

Caso Unhappy: Compra con datos de usuario inválidos

* Simular un error en el proceso de pago, por ejemplo, introduciendo datos de usuario inválidos.
* Verificar que se muestra un mensaje de error apropiado y que la compra no se completa.

Caso Unhappy: Compra con carrito vacío

* Intentar acceder al proceso de compra con el carrito vacío.
* Verificar que se muestra un mensaje indicando que el carrito está vacío o que se redirige al usuario a la página principal.

Caso Happy: Cancelar la compra

* Iniciar el proceso de compra y luego cancelarlo en diferentes etapas (antes de ingresar datos de pago, después de ingresar datos de pago, etc.).
* Verificar que la compra se cancela correctamente y que el usuario regresa a la etapa anterior o a la página principal.


Ejecución de pruebas
---  

Para ejecutar el proyecto completo utilizar el comando:

`gradle test` 

Para ejecutar un runner específico:

```  gradle clean test -Dtest.single=MiRunner  ```   
```  gradle clean test -Prunner=MiRunner  ```

Para ejecutar diferentes runners separándolos por comas:

```  gradle clean test -Prunner=PrimerRunner,SegundoRunner  ```

Para ejecutar pruebas con un tag específico:

```  gradle clean test -Dcucumber.filter.tags="@MiTag"  ```

Para Generar la evidencia de pruebas:

```  gradle aggregate  ```

Aunque el proyecto por si solo genera la evidencia automaticamente una vez finalice la ejecución.


Ejemplo comandos de ejecución:
-
```   gradle clean test -Dtest.single=RunnerCucumber aggregate  ```  
```   gradle clean test -Dcucumber.filter.tags="@CompraExitosa" aggregate  ```