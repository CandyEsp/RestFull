package steps;
import com.frm.bot.backend.base.BaseClass;
import com.frm.bot.backend.variables.Variables;
import io.cucumber.java.es.Y;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Dado;

import static org.hamcrest.Matchers.containsString;


public class StepGetBookingId {

    BaseClass base = new BaseClass();

    @Dado("que se desea consultar la url {string}")
    public void queSeDeseaConsultarLaUrl(String urlConsulta) {
        Variables.url=urlConsulta;
    }
    @Cuando("filtramos por el {string}")
    public void filtramosPorEl(String nombre) {
        base.setURL(Variables.url+"booking?firstname="+nombre);
        base.configureMethod("GET");
        base.sendRequest();
    }

    @Y("verificamos que el response contenga el objeto {string}")
    public void verificamosQueElResponseContenga(String bodycontain) {
        base.currentResponse.then().assertThat().body(containsString(bodycontain));
    }

    @Entonces("validamos el status code {string}")
    public void validamosElStatusCode(String status) {
        base.currentResponse.then().statusCode(Integer.parseInt(status));;
    }


}
