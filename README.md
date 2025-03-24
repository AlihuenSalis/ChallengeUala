## Herramientas y Librerias

  1- **Android Studio**
  
  2- **Kotlin**
  
  3- **JetPackCompose**
  
  4- **Patrón MVVM**
  
  5- **Clean architecture**
  
  6- **Inyección dependencias:** Dagger Hilt

  7- **API Request:** Retrofit2
  
  8- **Test:** Junit-mockk

  9- **Google Maps**

En este caso las librerias que se utilizaron fueron librerias que recomienda google para android y que considero que son estables
para desarrollar un proyecto, además del uso frecuente de las mismas.
Se implementó el patrón MVVM con arquitectura clean, esto hace que el proyecto quede estructurado para poder escalar correctamente.
Además quisiera destacar que muchas veces el uso exagerado de la arquitectura en proyectos pequeños es contraproducente y puede que lo
dificulte mas (no es bueno realizar sobreingeniería).

Notas:
  - **No olvidar colocar la KEY DE GOOGLE para el correcto funcionamiento del mapa en /res/values/google_maps_key. La misma puedo enviarselas por mail para facilitarselas en caso de que necesiten generar una.** 
  - Algunas de las funcionalidades, como por ejemplo el listado de ciudades, se guardan en base de datos para poner a prueba la paginación. Entiendo que el challenge está hecho con un propósito, pero me gusataría destacar que
    el uso de la paginación es a modo de poder mostrarla e implementarla (en este caso de forma manual, también existen librerias como Paging3). En un proyecto real, no es performante trabajarla desde el lado de la aplicación,
    siempre debe haber un soporte desde backEnd para la misma.
  - En el proyecto quedaron comentarios sobre la desición de distintas acciones.
