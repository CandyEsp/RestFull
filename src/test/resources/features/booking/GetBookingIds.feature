# language: es
Característica: Obtener lista de ID de reservas

  @GetBookingID @All
  Esquema del escenario: Obtener lista de reservas por filtro de nombre
    Dado que se desea consultar la url "https://restful-booker.herokuapp.com/"
    Cuando filtramos por el "<nombre>"
    Y verificamos que el response contenga el objeto "bookingid"
    Entonces validamos el status code "<status>"

    Ejemplos:
      | nombre | status |
      | John   | 200    |