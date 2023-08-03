package steps;

import com.frm.bot.backend.base.BaseClass;
import com.frm.bot.backend.scripts.Scripts;
import com.frm.bot.backend.variables.Variables;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.cucumber.java.es.Cuando;

import static org.junit.Assert.assertTrue;

public class StepGetBookinByID {

    BaseClass base = new BaseClass();
    Scripts script = new Scripts();

    @Cuando("buscamos la reserva con el id {string}")
    public void buscamosLaReservaConElID(String id) {
        base.setURL(Variables.url+"booking/"+id);
        base.configureMethod("GET");
    }

    @Y("agregamos los headers")
    public void agregamosLosHeaders() {
        String[] key={"Accept"};
        String[] value={"application/json"};
        base.setHeaders(key,value);
        base.sendRequest();
    }

    @Entonces("validamos el status {string}")
    public void validamosElStatus(String status) {
        base.currentResponse.then().statusCode(Integer.parseInt(status));;
    }

    @Y("el formato de fecha correcto de los objetos checkin y checkout")
    public void elFormatoDeFechaCorrectoDeLosObjetosCheckinYCheckout() {
        String checkinDate = base.currentResponse.jsonPath().getString("bookingdates.checkin");
        String checkoutDate = base.currentResponse.jsonPath().getString("bookingdates.checkout");
        assertTrue(script.validarFormatoFecha(checkinDate));
        assertTrue(script.validarFormatoFecha(checkoutDate));
    }
}
