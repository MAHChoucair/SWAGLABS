package com.choucair.app.questions;

import static com.choucair.app.userinterface.UIPageHome.LOGIN_SUCCESSFULL_TXT;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class MakePayQuestions implements Question<Boolean> {

    public static MakePayQuestions verificarSaldo() {
        return new MakePayQuestions();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        double saldoInicial = Serenity.sessionVariableCalled("saldoInicial");
        double monto = Double.parseDouble(Serenity.sessionVariableCalled("randomMonto"));

        // Obtener el saldo final desde la interfaz de usuario
        String balanceText = LOGIN_SUCCESSFULL_TXT.resolveFor(actor).getText();
        double saldoFinal = 0;
        if (balanceText.contains("Your balance")) {
            String balanceValue = balanceText.replace("Your", "").replace("balance", "").replace("is:", "").replace("$", "").trim();
            saldoFinal = Double.parseDouble(balanceValue);
        }

        if (saldoFinal == saldoInicial - monto) {
            return true;
        } else {
            System.out.println("Error: El saldo final no coincide con el saldo inicial menos el monto aleatorio.");
            return false;
        }
    }
}
