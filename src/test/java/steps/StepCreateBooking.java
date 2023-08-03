package steps;

import com.frm.bot.backend.base.BaseClass;
import com.frm.bot.backend.scripts.Scripts;
import com.frm.bot.backend.variables.Variables;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class StepCreateBooking {

    BaseClass base = new BaseClass();
    Scripts script = new Scripts();
    @Cuando("se requiere consultar el endpoint de reservas")
    public void seRequiereConsultarElEndpointDeReservas() {
        base.setURL(Variables.url+"booking/");
        base.configureMethod("POST");
    }

    @Y("agregamos los headers obligatorios")
    public void agregamosLosHeadersObligatorios() {
        String[] key={"Accept","Content-Type"};
        String[] value={"application/json","application/json"};
        base.setHeaders(key,value);
    }

    @Y("los datos correctos en el body")
    public void losDatosCorrectosEnElBody() throws IOException {
        base.addBody("src/test/resources/schemas/bookingcorrecto.json");
        base.sendRequest();
    }

    @Entonces("validamos el status code obtenido {string}")
    public void validamosElStatusCodeObtenido(String status) {
        base.currentResponse.then().statusCode(Integer.parseInt(status));;
    }

    @Y("el formato de fecha correcto de los objetos checkin y checkout del response")
    public void elFormatoDeFechaCorrectoDeLosObjetosCheckinYCheckoutDelResponse() {
        String checkinDate = base.currentResponse.jsonPath().getString("booking.bookingdates.checkin");
        String checkoutDate = base.currentResponse.jsonPath().getString("booking.bookingdates.checkout");
        assertTrue(script.validarFormatoFecha(checkinDate));
        assertTrue(script.validarFormatoFecha(checkoutDate));
    }

    @Y("los datos incorrectos en el body")
    public void losDatosIncorrectosEnElBody() throws IOException {
        base.addBody("src/test/resources/schemas/bookingIncorrecto.json");
        base.sendRequest();
    }
}
