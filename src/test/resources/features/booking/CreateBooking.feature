# language: es
Característica: Crear una reserva online

  @CreateBookingCorrect  @All
    Escenario: Crear reserva con parámetros correctos en el Header, requestBody y objeto no obligatorio
    Dado que se desea consultar la url "https://restful-booker.herokuapp.com/"
    Cuando se requiere consultar el endpoint de reservas
    Y agregamos los headers obligatorios
    Y los datos correctos en el body
    Entonces validamos el status code obtenido "200"
    Y el formato de fecha correcto de los objetos checkin y checkout del response


  @CreateBookingInCorrect  @All
  Escenario: Crear reserva con parámetros correctos en el Header, requestBody y objeto no obligatorio
    Dado que se desea consultar la url "https://restful-booker.herokuapp.com/"
    Cuando se requiere consultar el endpoint de reservas
    Y agregamos los headers obligatorios
    Y los datos incorrectos en el body
    Entonces validamos el status code obtenido "500"


