package steps;

import com.frm.bot.backend.base.BaseClass;
import com.frm.bot.backend.variables.Variables;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.io.IOException;

public class StepDeleteBooking {

    BaseClass base = new BaseClass();
    static String token;
    @Cuando("se genera el token")
    public void seGeneraElToken() throws IOException {
        base.setURL(Variables.url+"auth");
        base.configureMethod("POST");
        String[] key={"Content-Type"};
        String[] value={"application/json"};
        base.setHeaders(key,value);
        base.addBody("src/test/resources/schemas/createToken.json");
        base.sendRequest();
        token = base.currentResponse.jsonPath().getString("token");
    }

    @Y("se requiere eliminar la reserva del id {string}")
    public void seRequiereEliminarLaReservaDelId(String id) throws IOException {
        base.setURL(Variables.url+"booking/"+id);
        base.configureMethod("DELETE");
        String[] key={"Cookie"};
        String[] value={"token="+token};
        base.setHeaders(key,value);
        base.addBody("");
        base.sendRequest();
    }

    @Entonces("validamos el status code obtenido de la consulta {string}")
    public void validamosElStatusCodeObtenidoDeLaConsulta(String status) {
        base.currentResponse.then().statusCode(Integer.parseInt(status));
    }
}
