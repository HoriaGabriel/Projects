package Model;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    private List<Monomial> monomialList=new ArrayList<Monomial>();

    /**
     * getter that takes returns the monomiallsit of the wanted polynomial
     * @return the method returns the monomial list of the polynomial
     * @see         Polynomial
     */

    public List<Monomial> getMonomialList() {
        return monomialList;
    }

    /**
     * setter that makes puts the monomial list as the list in the parameter
     * @param monomialList the parameter is sat as the current polynomials monomial list
     * @see         Polynomial
     */

    public void setMonomialList(List<Monomial> monomialList) {
        this.monomialList = monomialList;
    }

    /**
     * eliminates duplicates by marking the ones that have the same power, adding the coefficient of the second one
     * to the first and putting the other one in a new list and removing said monomial later.
     * @param  monomialList  a list of monomials to have their duplicate monomials elimiated
     * @return               the method does not return anything, it just changes the results monomial list before display
     * @see         Polynomial
     */

    public void eliminateDuplicates(List<Monomial> monomialList){

        List<Monomial> monomialList2 = new ArrayList<Monomial>();

        for(Monomial m1: monomialList){
            for(Monomial m2: monomialList){
                if(m1!=m2 && m1.getPower()==m2.getPower() && !m1.isDuplicate() && !m2.isDuplicate()){
                    m1.setCoefficient(m1.getCoefficient()+m2.getCoefficient());
                    monomialList2.add(m2);
                    m2.setDuplicate(true);
                    m1.setDuplicate(true);
                }
            }
        }

        for(Monomial m: monomialList2){
            int aux=0;
            while(aux!=monomialList.size()){
                if(m.getPower()==monomialList.get(aux).getPower() && m.getCoefficient()==monomialList.get(aux).getCoefficient()){
                    monomialList.remove(monomialList.get(aux));
                }
                else{
                    aux++;
                }
            }
        }
    }
}

