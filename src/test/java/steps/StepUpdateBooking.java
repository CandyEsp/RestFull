package steps;

import com.frm.bot.backend.base.BaseClass;
import com.frm.bot.backend.scripts.Scripts;
import com.frm.bot.backend.variables.Variables;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class StepUpdateBooking {

    BaseClass base = new BaseClass();
    static String token;
    @Cuando("se genera el token de autenticacion")
    public void seGeneraElTokenDeAutenticacion() throws IOException {
        base.setURL(Variables.url+"auth");
        base.configureMethod("POST");
        String[] key={"Content-Type"};
        String[] value={"application/json"};
        base.setHeaders(key,value);
        base.addBody("src/test/resources/schemas/createToken.json");
        base.sendRequest();
        token = base.currentResponse.jsonPath().getString("token");
    }


    @Y("se requiere actualizar la reserva del id {string}")
    public void seRequiereActualizarLaReservaDelId(String id) {
        base.setURL(Variables.url+"booking/"+id);
        base.configureMethod("PUT");
    }

    @Y("agregamos los headers obligatorios del api")
    public void agregamosLosHeadersObligatoriosDelApi() {
        String[] key={"Content-Type","Accept","Cookie"};
        String[] value={"application/json","application/json","token="+token};
        base.setHeaders(key,value);
    }

    @Y("los datos a actualizar")
    public void losDatosAActualizar() throws IOException {
        base.addBody("src/test/resources/schemas/bookingcorrecto2.json");
        base.sendRequest();
    }

    @Entonces("validamos el status code de la consulta {string}")
    public void validamosElStatusCodeDeLaConsulta(String status) {
        base.currentResponse.then().statusCode(Integer.parseInt(status));;
    }

    @Y("el nombre correcto en el response {string}")
    public void elNombreCorrectoEnElResponse(String nombre) {
        String nombreObtenido = base.currentResponse.jsonPath().getString("firstname");
        assertEquals(nombre,nombreObtenido);
    }

    @Y("agregamos los headers sin enviar el token")
    public void agregamosLosHeadersSinEnviarElToken() {
        String[] key={"Content-Type","Accept"};
        String[] value={"application/json","application/json"};
        base.setHeaders(key,value);
    }
}
