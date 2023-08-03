# language: es
Característica: Actualizar datos especificos de la reserva


  @PartialUpdateCorrect  @All
  Escenario: Actualizar campos de reserva enviando el Header correcto, token e indicando los valores a actualizar
    Dado que se desea consultar la url "https://restful-booker.herokuapp.com/"
    Cuando se obtiene el token de autenticacion
    Y se requiere actualizar la reserva del siguiente id "1"
    Y agregamos los datos obligatorios del headers
    Y los datos especificos a actualizar
    Entonces validamos el status code  "200"
    Y el apellido correcto en el response "Cyrus"


  @PartialUpdateInCorrect  @All
  Escenario: Actualizar campos de reserva enviando el Header correcto, sin enviar el token e indicando los valores a actualizar
    Dado que se desea consultar la url "https://restful-booker.herokuapp.com/"
    Cuando se obtiene el token de autenticacion
    Y se requiere actualizar la reserva del siguiente id "1"
    Y agregamos los headers sin enviar el token obtenido
    Y los datos especificos a actualizar
    Entonces validamos el status code  "403"