package steps;

import com.frm.bot.backend.base.BaseClass;
import com.frm.bot.backend.variables.Variables;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import io.cucumber.java.es.Cuando;

import java.io.IOException;

public class StepEndtoEnd {
    BaseClass base = new BaseClass();
    static String iDGenerado="";
    static String token;

    @Cuando("se requiere realizar un ping que la url")
    public void seRequiereRealizarUnPingQueLaUrl() {
        base.setURL(Variables.url+"ping");
        base.configureMethod("GET");
        base.sendRequest();
    }

    @Y("validamos que se encuentre activa")
    public void validamosQueSeEncuentreActiva() {
        base.currentResponse.then().statusCode(Integer.parseInt("201"));;
    }

    @Y("creamos una reserva con los datos correctos")
    public void creamosUnaReservaConLosDatosCorrectos() throws IOException {
        base.setURL(Variables.url+"booking/");
        base.configureMethod("POST");
        String[] key={"Accept","Content-Type"};
        String[] value={"application/json","application/json"};
        base.setHeaders(key,value);
        base.addBody("src/test/resources/schemas/bookingcorrecto.json");
        base.sendRequest();
        iDGenerado = base.currentResponse.jsonPath().getString("bookingid");
    }

    @Y("obtenemos la reserva generada con el iD")
    public void obtenemosLaReservaGeneradaConElID() {
        base.setURL(Variables.url+"booking/"+iDGenerado);
        base.configureMethod("GET");
        String[] key={"Accept"};
        String[] value={"application/json"};
        base.setHeaders(key,value);
        base.sendRequest();
    }

    @Entonces("validamos el status code obtenido de la consulta realizada {string}")
    public void validamosElStatusCodeObtenidoDeLaConsultaRealizada(String status) {
        base.currentResponse.then().statusCode(Integer.parseInt(status));
    }

    @Y("eliminamos la reserva validando el status correcto")
    public void eliminamosLaReservaValidandoElStatusCorrecto() throws IOException {
        base.setURL(Variables.url+"auth");
        base.configureMethod("POST");
        String[] key={"Content-Type"};
        String[] value={"application/json"};
        base.setHeaders(key,value);
        base.addBody("src/test/resources/schemas/createToken.json");
        base.sendRequest();
        token = base.currentResponse.jsonPath().getString("token");
        base.setURL(Variables.url+"booking/"+iDGenerado);
        base.configureMethod("DELETE");
        String[] key2={"Cookie"};
        String[] value2={"token="+token};
        base.setHeaders(key2,value2);
        base.addBody("");
        base.sendRequest();
        base.currentResponse.then().statusCode(Integer.parseInt("201"));
    }
}
