package steps;

import com.frm.bot.backend.base.BaseClass;
import com.frm.bot.backend.variables.Variables;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.cucumber.java.es.Cuando;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StepPartialUpdate {

    BaseClass base = new BaseClass();
    static String token;
    @Cuando("se obtiene el token de autenticacion")
    public void seObtieneElTokenDeAutenticacion() throws IOException {
        base.setURL(Variables.url+"auth");
        base.configureMethod("POST");
        String[] key={"Content-Type"};
        String[] value={"application/json"};
        base.setHeaders(key,value);
        base.addBody("src/test/resources/schemas/createToken.json");
        base.sendRequest();
        token = base.currentResponse.jsonPath().getString("token");
    }

    @Y("se requiere actualizar la reserva del siguiente id {string}")
    public void seRequiereActualizarLaReservaDelSiguienteId(String id) {
        base.setURL(Variables.url+"booking/"+id);
        base.configureMethod("PATCH");
    }

    @Y("agregamos los datos obligatorios del headers")
    public void agregamosLosDatosObligatoriosDelHeaders() {
        String[] key={"Content-Type","Accept","Cookie"};
        String[] value={"application/json","application/json","token="+token};
        base.setHeaders(key,value);
    }

    @Y("los datos especificos a actualizar")
    public void losDatosEspecificosAActualizar() throws IOException {
        base.addBody("src/test/resources/schemas/editPartialBooking.json");
        base.sendRequest();
    }

    @Entonces("validamos el status code  {string}")
    public void validamosElStatusCode(String status) {
        base.currentResponse.then().statusCode(Integer.parseInt(status));;
    }

    @Y("el apellido correcto en el response {string}")
    public void elApellidoCorrectoEnElResponse(String apellido) {
        String apellidoObtenido = base.currentResponse.jsonPath().getString("lastname");
        assertEquals(apellido,apellidoObtenido);
    }

    @Y("agregamos los headers sin enviar el token obtenido")
    public void agregamosLosHeadersSinEnviarElTokenObtenido() {
        String[] key={"Content-Type","Accept"};
        String[] value={"application/json","application/json"};
        base.setHeaders(key,value);
    }
}
