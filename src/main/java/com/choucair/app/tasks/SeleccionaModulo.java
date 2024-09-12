package com.choucair.app.tasks;

import static com.choucair.app.userinterface.UIPageHome.*;

import com.choucair.moviles.libreria.interactions.choucair.builders.Tap;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class SeleccionaModulo implements Task {

    private String modulo;

    public SeleccionaModulo(String modulo) {
        this.modulo = modulo;
    }

    public static SeleccionaModulo dePago(String modulo) {
        return Tasks.instrumented(SeleccionaModulo.class, modulo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Tap.siElElementoEsVisible(LOGIN_SUCCESSFULL_TXT)
        );

        String balanceText = LOGIN_SUCCESSFULL_TXT.resolveFor(actor).getText();
        double currentBalance = 0;
        if (balanceText.contains("Your balance")) {
            String balanceValue = balanceText.replace("Your", "").replace("balance", "").replace("is:", "").replace("$", "").trim();
            currentBalance = Double.parseDouble(balanceValue);
        }
        Serenity.setSessionVariable("saldoInicial").to(currentBalance);

        switch (modulo) {
            case "Make Payment":
                actor.attemptsTo(
                        WaitUntil.the(BTN_OPCIONES("makePaymentButton"), WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                        Click.on(BTN_OPCIONES("makePaymentButton"))
                );
                break;
            case "Mortgage Request":
                actor.attemptsTo(
                        WaitUntil.the(BTN_OPCIONES("mortageRequestButton"), WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                        Click.on(BTN_OPCIONES("mortageRequestButton"))
                );
                break;
            case "Expense Report":
                actor.attemptsTo(
                        WaitUntil.the(BTN_OPCIONES("expenseReportButton"), WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                        Click.on(BTN_OPCIONES("expenseReportButton"))
                );
                break;
            case "Logout":
                actor.attemptsTo(
                        WaitUntil.the(BTN_OPCIONES("logoutButton"), WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                        Click.on(BTN_OPCIONES("logoutButton"))
                );
                break;
            default:
                break;
        }
    }
}
