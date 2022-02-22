package BusinessLayer;

import java.io.*;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileGetter {

    /**
     * Function reads the initial list of products and uses streams and lambda expressions in order
     * to create a list of menu items out of them.
     * @return returns the list of menu items
     */
    public ArrayList<MenuItem> processInputFile(){

        ArrayList<MenuItem> menu2 = new ArrayList<MenuItem>();

        try{

            File inputF = new File("C:\\PT2021_30421_Rusu_Horia_Assignment_4\\products.csv");
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            menu2 = (ArrayList<MenuItem>) br.lines()
                    .skip(1)
                    .map(mapToItem)
                    .collect(Collectors.toList());

            br.close();
        } catch(NotSerializableException e2){
            System.out.println("oops2");
        } catch (IOException e) {
            System.out.println("oops");
        }

        return menu2;
    }

    private Function<String,MenuItem> mapToItem = (row) -> {

        String[] p = row.split(",");

        MenuItem item = new BaseProduct();
        item.setName(p[0]);
        item.setCalories(Integer.parseInt(p[2]));
        item.setRating(Float.parseFloat(p[1]));
        item.setProtein(Integer.parseInt(p[3]));
        item.setFat(Integer.parseInt(p[4]));
        item.setSodium(Integer.parseInt(p[5]));
        item.setPrice(Integer.parseInt(p[6]));

        return item;
    };
}

