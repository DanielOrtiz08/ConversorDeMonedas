## Conversor de Divisas

**Descripción:**

Este proyecto es un conversor de divisas en tiempo real que utiliza la API de Exchangerate-API para obtener los tipos de cambio más actualizados. La aplicación emplea Java, Gradle para gestionar las dependencias, Lombok para reducir la verbosidad del código, Gson para el mapeo JSON, ForkJoin para la paralelización de tareas y JPanel para la interfaz gráfica de usuario.

**Características:**

* **Conversiones en tiempo real:** Utiliza la API de Exchangerate-API para obtener los tipos de cambio más recientes.
* **Paralelización:** Emplea el marco ForkJoin para mejorar el rendimiento en la búsqueda de divisas, siguiendo la filosofía de divide y vencerás.
* **Mapeo JSON:** Utiliza Gson para simplificar la conversión entre objetos Java y la representación JSON de los datos obtenidos de la API.
* **Interfaz gráfica de usuario:** Cuenta con una interfaz sencilla basada en JPanel para facilitar la interacción del usuario.
* **Reducción de código:** Emplea Lombok para eliminar código boilerplate y mejorar la legibilidad.

**Tecnologías utilizadas:**

* **Java:** Lenguaje de programación principal.
* **Gradle:** Herramienta de construcción para gestionar dependencias y tareas de compilación.
* **Lombok:** Librería para reducir la verbosidad del código, eliminando getters, setters, constructores, etc.
* **Gson:** Librería para convertir objetos Java a JSON y viceversa.
* **ForkJoin:** Marco para la paralelización de tareas en Java.
* **Swing (JPanel):** Framework para crear interfaces gráficas de usuario.
* **Exchangerate-API:** API externa para obtener tipos de cambio.

**Cómo ejecutar:**

1. **Clonar el repositorio:** `git clone https://tu-repositorio.git`
2. **Construir el proyecto:** Ejecutar `gradle build` en la terminal desde el directorio raíz del proyecto.
3. **Ejecutar la aplicación:** Ejecutar la clase principal (especificar el nombre de la clase).

**Dependencias:**

* **Exchangerate-API:** Declarar la dependencia en el archivo `build.gradle`.
* **Lombok:** Declarar la dependencia en el archivo `build.gradle` y configurar el plugin de Lombok en tu IDE.
* **Gson:** Declarar la dependencia en el archivo `build.gradle`.
* **Otras dependencias:** Listar cualquier otra dependencia adicional utilizada en el proyecto.

**Contribuciones:**

¡Las contribuciones son bienvenidas! Si encuentras algún error o deseas agregar nuevas funcionalidades, por favor, abre un issue o crea una pull request.

**Agradecimientos:**

Agradezco a AluraLatam por brindarme la oportunidad de enseñarme algunas de estás tecnologias y proponerpe este reto

```groovy
dependencies {
    implementation 'com.google.code.gson:gson:2.11.0'
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    compileOnly 'org.projectlombok:lombok:1.18.36'
    annotationProcessor 'org.projectlombok:lombok:1.18.36'

    testCompileOnly 'org.projectlombok:lombok:1.18.36'
    testAnnotationProcessor 'org.projectlombok:lombok:1.18.36'
}
```
