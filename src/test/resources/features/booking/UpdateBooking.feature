# language: es
Característica: Actualizar una reserva online


  @UpdateBookingCorrect  @All
  Escenario: Actualizar reserva con campos correctos en el Header, envío de token y valores obligatorios en el request body
    Dado que se desea consultar la url "https://restful-booker.herokuapp.com/"
    Cuando se genera el token de autenticacion
    Y se requiere actualizar la reserva del id "1"
    Y agregamos los headers obligatorios del api
    Y los datos a actualizar
    Entonces validamos el status code de la consulta "200"
    Y el nombre correcto en el response "James"


  @UpdateBookingSinToken  @All
  Escenario: Actualizar reserva sin enviar el token
    Dado que se desea consultar la url "https://restful-booker.herokuapp.com/"
    Cuando se genera el token de autenticacion
    Y se requiere actualizar la reserva del id "1"
    Y agregamos los headers sin enviar el token
    Y los datos a actualizar
    Entonces validamos el status code de la consulta "403"
