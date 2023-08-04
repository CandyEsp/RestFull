# language: es
Característica: Realizar pruebas end to end

  @EndToEnd @All
  Escenario: Ping, Crear , obtener y eliminar una reserva
    Dado que se desea consultar la url "https://restful-booker.herokuapp.com/"
    Cuando se requiere realizar un ping que la url
    Y validamos que se encuentre activa
    Y creamos una reserva con los datos correctos
    Y obtenemos la reserva generada con el iD
    Entonces validamos el status code obtenido de la consulta realizada "200"
    Y eliminamos la reserva validando el status correcto