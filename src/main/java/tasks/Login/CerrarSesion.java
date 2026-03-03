package tasks.Login;

import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.validations.ValidateInformationText;
import interactions.wait.WaitElement;
import interactions.wait.WaitFor;
import models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.questions.Presence;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import utils.EvidenciaUtils;
import utils.TestDataProvider;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotPresent;
import static org.hamcrest.core.IsEqual.equalTo;
import static userinterfaces.LoginPage.*;
import static utils.Constants.CONTINUAR;

public class CerrarSesion implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {




        // Ingreso de email y contraseña con espera explícita
        actor.attemptsTo(
                Click.on(MENU_DESPLEGABLE),
                Click.on(CERRAR_SESION),
                Click.on(ACEPTAR_CERRAR_SESION)
        );
    }


    public static Performable cerrarSesion() {
        return instrumented(CerrarSesion.class);
    }
}
