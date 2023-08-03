# language: es
Característica: Obtener reserva por ID

  @GetBookingByID  @All
  Esquema del escenario: Filtrar reserva por ID existente
    Dado que se desea consultar la url "https://restful-booker.herokuapp.com/"
    Cuando buscamos la reserva con el id "<ID>"
    Y agregamos los headers
    Entonces validamos el status "<status>"
    Y el formato de fecha correcto de los objetos checkin y checkout

    Ejemplos:
      | ID | status |
      | 1  | 200    |