package ro.tuc.tp;
import Controller.CalculatorController;
import Exceptions.UnproperFormatException;
import Model.Monomial;
import Model.Polynomial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class OpTest {

    @Test
    public void Test() throws UnproperFormatException {

        Monomial m1 = new Monomial();
        m1.setCoefficient(3);
        m1.setPower(3);

        Monomial m2 = new Monomial();
        m2.setCoefficient(-4);
        m2.setPower(7);

        List<Monomial> aux = new ArrayList<>();
        aux.add(m1);
        aux.add(m2);

        Polynomial p = new Polynomial();
        p.setMonomialList(aux);

        CalculatorController c = new CalculatorController();

        Polynomial p2 = c.derivativeCalculator(p);
        String aux3=c.polynomToString(p2);

        assertTrue(aux3.equals("9x^2+-28x^6"),"ok");

        Polynomial p3 = c.integralCalculator(p);
        String aux4=c.polynomToString(p3);

        assertTrue(aux4.equals("0.75x^4+-0.5x^8"),"ok");

        Monomial m3=new Monomial();
        m3.setPower(3);
        m3.setCoefficient(-5);

        Monomial m4=new Monomial();
        m4.setPower(2);
        m4.setCoefficient(15);

        List<Monomial> aux2 = new ArrayList<>();
        aux2.add(m3);
        aux2.add(m4);

        Polynomial paux = new Polynomial();
        paux.setMonomialList(aux2);

        Polynomial paux0= c.createPolynomial("3x^3+-4x^7");

        Polynomial p4 = c.sumCalculator(paux0,paux);
        String aux5= c.polynomToString(p4);

        assertTrue(aux5.equals("-2x^3+-4x^7+15x^2"),"ok");

        Polynomial paux2= c.createPolynomial("3x^3+-4x^7");
        Polynomial paux3= c.createPolynomial("-5x^3+15x^2");

        Polynomial p5 = c.differenceCalculator(paux2,paux3);
        String aux6= c.polynomToString(p5);

        assertTrue(aux6.equals("8x^3+-4x^7+-15x^2"),"ok");


        Polynomial paux4= c.createPolynomial("3x^3+-4x^7");
        Polynomial paux5= c.createPolynomial("-5x^3+15x^2");

        Polynomial p6 = c.multiplicationCalculator(paux4,paux5);
        String aux7= c.polynomToString(p6);

        assertTrue(aux7.equals("-15x^6+45x^5+20x^10+-60x^9"),"ok");

    }
}

