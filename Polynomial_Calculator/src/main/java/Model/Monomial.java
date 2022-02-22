package Model;

public class Monomial {

    private int power;
    private int coefficient;

    private boolean duplicate;
    private float realcoefficient;



    /**
     * getter that takes returns the coefficient of the wanted monomial
     * @return the method returns the coefficient of the polynomial
     */

    public int getCoefficient() {
        return coefficient;
    }



    /**
     * setter that puts the parameter coefficient as the current monomials coefficient
     * @param coefficient the parameter is sat as the current monomials coefficient
     * @see         Monomial
     */

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }



    /**
     * getter that takes returns the power of the wanted monomial
     * @return the method returns the power of the polynomial
     */

    public int getPower() {
        return power;
    }



    /**
     * setter that puts the parameter power as the current monomials power
     * @param power the parameter is sat as the current monomials power
     * @see         Monomial
     */

    public void setPower(int power) {
        this.power = power;
    }



    /**
     * getter that takes sees if the monomial is a duplicate in a monomial list
     * @return the method returns true if the monomial is a duplicate in a monomial list, false otherwise
     */

    public boolean isDuplicate() {
        return duplicate;
    }



    /**
     * setter that puts the parameter duplicate if the monomial is a duplicate
     * @param duplicate the parameter is sat as true in regards to being a duplicate when it appears twice in a list
     * @see             Monomial
     */

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }



    /**
     * getter that takes returns the real coefficient of the wanted monomial
     * @return the method returns the real coefficient of the polynomial
     */

    public float getRealCoefficient() {
        return realcoefficient;
    }



    /**
     * setter that puts the parameter real coefficient as the current monomials real coefficient
     * @param realcoefficient the parameter is sat as the current monomials real coefficient
     * @see               Monomial
     */

    public void setRealCoefficient(float realcoefficient) {
        this.realcoefficient = realcoefficient;
    }
}

