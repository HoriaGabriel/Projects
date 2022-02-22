package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;


public interface IDeliveryServiceProcessing extends Serializable {

    /**
     * @pre item1 != null
     * @pre item2 != null
     * @post map.size@post == map.size@pre+1
     */
    void createMenuMap(Order o, ArrayList<MenuItem> compositeOrder);

    /**
     * @pre item != null
     * @invariant isWellFormed()
     */
    void createMenuItem(MenuItem item);

    /**
     * @pre item != null
     * @post list.size@post == list.size@pre -1
     */
    void deleteMenuItem(MenuItem aux);

    /**
     * @pre item1 != null
     * @pre item2 != null
     * @post item1==item2
     */
    void editMenuItem(MenuItem aux, BaseProduct bp2);

    /**
     * @pre item1 != null
     * @post list.size@post == list.size@pre + 1
     */
    void createOrder(Order o);

}

