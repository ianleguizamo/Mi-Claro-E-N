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

public class IngresoSuperApp implements Task {

    private final User user = TestDataProvider.getRealUser();
    private static final String paso = "Login exitoso a la Super App";

    @Override
    public <T extends Actor> void performAs(T actor) {

        // 1️⃣ Si ya está logueado
        if (isVisible(actor, LBL_ENCABEZADO_USUARIO)) {
            String encabezado = LBL_ENCABEZADO_USUARIO.resolveFor(actor).getText();
            if (encabezado.contains("Hola,")) {
                EvidenciaUtils.registrarCaptura("Usuario ya tiene sesión iniciada");
                return;
            }
            EvidenciaUtils.registrarCaptura("Usuario sin sesión, se inicia login");
        }

        // 2️⃣ Login solo vía email
        loginConEmail(actor);

    }

    private <T extends Actor> void loginConEmail(T actor) {
        // Click en iniciar sesión si está visible
        if (isVisible(actor, LBL_INICIAR_SESION)) {
            actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene("Iniciar sesión"));
        }

        // Ingreso de email y contraseña con espera explícita
        actor.attemptsTo(
                Click.on(PORTAFOLIO),
                WaitElement.isVisible(LOGIN_EN),
                Click.on(LOGIN_EN),
                WaitElement.isVisible(DESLIZAR),
                Click.on(DESLIZAR),
                Click.on(DESLIZAR2),
                WaitElement.isEnable(TXT_USERNAME_BOX),
                Enter.theValue(user.getEmail()).into(TXT_USERNAME),
                ClickElementByText.clickElementByText(CONTINUAR),
                WaitElement.isEnable(TXT_PASSWORD),
                Enter.theValue(user.getPassword()).into(TXT_PASSWORD),
                ClickElementByText.clickElementByText(CONTINUAR),
                WaitUntil.the(LOADING_ESPERA_UN_MOMENTO, isNotPresent()).forNoMoreThan(30).seconds()
        );
    }

    private <T extends Actor> boolean isVisible(T actor, Target element) {
        try {
            return !Presence.of(element).viewedBy(actor).resolveAll().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public static Performable ingresoSuperApp() {
        return instrumented(IngresoSuperApp.class);
    }
}
