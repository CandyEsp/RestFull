# language: es
Característica: Eliminar una reserva online

  @DeleteBookingIncorrecto  @All
  Escenario: Eliminar ID de reserva no existente y con el token correcto
    Dado que se desea consultar la url "https://restful-booker.herokuapp.com/"
    Cuando se genera el token
    Y se requiere eliminar la reserva del id "1456674"
    Entonces validamos el status code obtenido de la consulta "405"
