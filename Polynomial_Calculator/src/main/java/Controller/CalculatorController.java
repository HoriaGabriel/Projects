package Controller;

import Exceptions.UnproperFormatException;
import Model.Monomial;
import Model.Polynomial;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;


public class CalculatorController {

    /**
     * The function takes the string from the input and turns it into a polynomial
     * by creating monomials and adding them to a list
     * String is split according to the plus sign, then each char is compared with x
     * and the coeff and power is take accordingly
     * @param  poly1  original polynomial string to be turned into a polynomial
     * @return        the method returns a polynomial to have operations done on it
     * @see           Polynomial
     */

    public Polynomial createPolynomial(String poly1) throws UnproperFormatException {

        List<Monomial> monomialList = new ArrayList<Monomial>();
        validate(poly1);
        Pattern pattern = Pattern.compile("[x]");

        for (String sp : poly1.split("[+]")) {
            for (char c : sp.toCharArray()) {
                String aux=String.valueOf(c);
                Matcher matcher = pattern.matcher(aux);
                if (matcher.matches()) {
                    Monomial monomial = new Monomial();
                    monomial.setCoefficient(Integer.parseInt(sp.substring(0, sp.indexOf(c))));
                    monomial.setPower(Integer.parseInt(sp.substring(sp.indexOf(c) + 2)));
                    monomialList.add(monomial);
                }
            }
        }

        Polynomial res=new Polynomial();
        res.setMonomialList(monomialList);
        return res;
    }

    /**
     * compares the string to a pattern in order to confirm that it has a proper structure
     * also looks in the code to see if there are any other variables other than x
     * @param  poly  original polynomial string to be turned into a polynomial
     * @return      the method does not return anything it just throws an exception if the proper format is not followed
     * @see         UnproperFormatException
     */

    private void validate(String poly) throws UnproperFormatException {

        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Pattern pattern2 = Pattern.compile("[a-z&&[^x]]");

        for(String sp : poly.split("[+]")){
            Matcher matcher = pattern.matcher(sp);
            if(matcher.matches()){ }
            else{
                throw new UnproperFormatException();
            }
        }

        for(char c: poly.toCharArray()){
            String aux=String.valueOf(c);
            Matcher matcher2 = pattern2.matcher(aux);
            if(matcher2.matches()){
                throw new UnproperFormatException();
            }
        }
    }

    /**
     * Computes the derivation of the polynomial by creating a new one
     * with the decremented power and the coefficient times the power
     * @param  poly1  original polynomial to be used for making a new polynomial representing the derivation of the original
     * @return      the method returns the new polynomial to be displayed
     * @see         Polynomial
     */

    public Polynomial derivativeCalculator(Polynomial poly1) {

        Polynomial res = new Polynomial();
        List<Monomial> aux;
        aux = poly1.getMonomialList();
        List<Monomial> resultMonomialList = new ArrayList<Monomial>();

        for (Monomial str : aux) {
            int power = str.getPower();
            int coefficient = str.getCoefficient();
            int newPower = power - 1;
            int newCoefficient = coefficient * power;
            if (power > 0) {
                Monomial resMonomial = new Monomial();
                resMonomial.setCoefficient(newCoefficient);
                resMonomial.setPower(newPower);
                resultMonomialList.add(resMonomial);
            }
            else{
                Monomial resMonomial = new Monomial();
                resMonomial.setCoefficient(0);
                resMonomial.setPower(0);
                resultMonomialList.add(resMonomial);
            }
        }

        res.setMonomialList(resultMonomialList);
        return res;
    }

    /**
     * computes the integral of the polynomial by creating a new one
     * with the incremented power and the coefficient divided by the power
     * @param  poly1  original polynomial to be used for making a new polynomial representing the integration of the original
     * @return      the method returns the new polynomial to be displayed
     * @see         Polynomial
     */

    public Polynomial integralCalculator(Polynomial poly1) {

        Polynomial res = new Polynomial();
        List<Monomial> aux;
        aux = poly1.getMonomialList();
        List<Monomial> resultMonomialList = new ArrayList<Monomial>();

        for (Monomial str : aux) {
            int power = str.getPower();
            int coefficient = str.getCoefficient();
            int newPower = power + 1;
            int newCoefficient;
            float realnewCoefficient;
            Monomial resMonomial = new Monomial();
            if (coefficient % newPower == 0) {
                newCoefficient = coefficient / newPower;
                resMonomial.setCoefficient(newCoefficient);
            } else {
                realnewCoefficient = (float) coefficient / newPower;
                resMonomial.setRealCoefficient(realnewCoefficient);
            }
            resMonomial.setPower(newPower);
            resultMonomialList.add(resMonomial);
        }

        res.setMonomialList(resultMonomialList);
        return res;
    }

    /**
     * Computes the sum of two polynomials by creating a new one
     * which has the monomials of both original polynomials and eliminating the monomials with the same power
     * @param  poly1 one of the original polynomial to be used for making a new polynomial representing the sum of the originals
     * @param  poly2 one of the original polynomial to be used for making a new polynomial representing the sum of the originals
     * @return      the method returns the new polynomial to be displayed, representing the sum of the previous two
     * @see         Polynomial
     */

    public Polynomial sumCalculator(Polynomial poly1, Polynomial poly2){

        Polynomial res = new Polynomial();

        List<Monomial> resultMonomialList;

        resultMonomialList=poly1.getMonomialList();

        for(Monomial m: poly2.getMonomialList()){

            resultMonomialList.add(m);
        }

        res.setMonomialList(resultMonomialList);
        res.eliminateDuplicates(resultMonomialList);
        return res;

    }

    /**
     * Computes the difference of two polynomials by creating a new one
     * which has the monomials of both original polynomials, except the second one has its coefficient negated
     * and eliminating the monomials with the same power
     * @param  poly1 one of the original polynomial to be used for making a new polynomial representing the difference of the originals
     * @param  poly2 one of the original polynomial to be used for making a new polynomial representing the difference of the originals
     * @return      the method returns the new polynomial to be displayed, representing the difference of the previous two
     * @see         Polynomial
     */

    public Polynomial differenceCalculator(Polynomial poly1, Polynomial poly2) {

        Polynomial res = new Polynomial();

        List<Monomial> resultMonomialList;

        resultMonomialList=poly1.getMonomialList();

        for(Monomial m: poly2.getMonomialList()){

            Monomial m2 = new Monomial();
            m2.setCoefficient(-m.getCoefficient());
            m2.setPower(m.getPower());
            resultMonomialList.add(m2);
        }

        res.setMonomialList(resultMonomialList);
        res.eliminateDuplicates(resultMonomialList);
        return res;
    }

    /**
     * Computes the multiplication of two polynomials by creating a new one
     * which has the monomials with the power the sum of the two powers and the coefficient the
     * multiplication of the original two and eliminates the duplicates
     * @param  poly1 one of the original polynomial to be used for making a new polynomial representing the multiplication of the originals
     * @param  poly2 one of the original polynomial to be used for making a new polynomial representing the multiplication of the originals
     * @return      the method returns the new polynomial to be displayed, representing the multiplication of the previous two
     * @see         Polynomial
     */

    public Polynomial multiplicationCalculator(Polynomial poly1, Polynomial poly2){

        Polynomial res = new Polynomial();

        List<Monomial> aux1;
        aux1 = poly1.getMonomialList();

        List<Monomial> aux2;
        aux2 = poly2.getMonomialList();

        List<Monomial> resultMonomialList = new ArrayList<Monomial>();

        for (Monomial m1 : aux1) {
            for (Monomial m2 : aux2) {
                int newCoefficient = m1.getCoefficient() * m2.getCoefficient();
                int newPower = m1.getPower() + m2.getPower();
                Monomial resMonomial = new Monomial();
                resMonomial.setCoefficient(newCoefficient);
                resMonomial.setPower(newPower);
                resMonomial.setRealCoefficient(0);
                resultMonomialList.add(resMonomial);
            }
        }
        res.setMonomialList(resultMonomialList);
        res.eliminateDuplicates(resultMonomialList);
        return res;
    }

    /**
     * takes a polynomial and converts it to string to be displayed
     * there is attention payed to whether the coefficient is an integer or float
     * @param  result the polynomial resulted after one of the operations performed
     * @return       the method returns a String that represents the result to be displayed
     * @see         Polynomial
     */

    public String polynomToString(Polynomial result) {

        List<Monomial> aux;
        aux = result.getMonomialList();
        String s = null;
        for (Monomial m : aux) {

            if (m.getCoefficient()!= 0 || m.getPower() != 0 && m.getRealCoefficient()==0) {
                String aux1 = String.valueOf(m.getCoefficient());
                String aux2 = String.valueOf(m.getPower());
                s = s + aux1 + "x^" + aux2 + "+";
            } else {

                if(m.getRealCoefficient()!=0) {
                    String aux1 = String.valueOf(m.getRealCoefficient());
                    String aux2 = String.valueOf(m.getPower());
                    s = s + aux1 + "x^" + aux2 + "+";
                }
                else{
                    s=s+"0+";
                }
            }
        }

        s = s.substring(4, s.length() - 1);
        return s;
    }
}

