package ra.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile<T>{
    public static final String CATALOG_FILE = "catalogs.txt";
    public static final String PRODUCT_FILE = "product.txt";
    public static final String CART_FILE = "cart.txt";
    public void writeToFile(List<T> list, String fileName){
        File file =null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            file = new File(fileName);
            if (!file.exists()){
                file.createNewFile();
            }
            fos= new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            // ghi file
            oos.writeObject(list);
            oos.flush();
        }catch (IOException e){
            throw  new RuntimeException(e);
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public List<T> readFromFile(String fileName){
        File file = null;
        FileInputStream fis =null;
        ObjectInputStream ois = null;
        List<T> list = new ArrayList<>();
        try{
            file = new File(fileName);
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();

        }catch (ClassNotFoundException c){
            c.printStackTrace();
        }catch (IOException e){
//            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return list;
    }
}
