import PresentationLayer.Controller;

import java.io.Serializable;

public class Application implements Serializable {

    public static void main(String[] args) {

        Controller v = new Controller();
        v.initialize();
    }
}
