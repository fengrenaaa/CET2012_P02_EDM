import java.util.ArrayList;
import static Utilities.InputValidation.*;

public class Receiver {

    private ArrayList<String> dataStore = new ArrayList<>();

    public void add(String params) {
        dataStore.add(params);
        System.out.println("add");
    }
    public void removeAdded() {
        dataStore.removeLast();
        System.out.println("Undo");
    }

    public void list() {
//        int size = dataStore.size();
        System.out.println("List");
        printData();
    }

    public String delete(String index) throws  CustomException {
        int listIndex = Integer.parseInt(index) - 1;
        int listSize = dataStore.size() - 1;
        if(invalidIndexRange(listIndex, listSize)){throw new CustomException("Invalid index " +
                "range");}
        System.out.println("Delete # " + index);
        String deletedData = dataStore.get(listIndex);
        dataStore.remove(listIndex);
        return deletedData;
    }

    public void recover(String index, String params) {
        int listIndex = Integer.parseInt(index) - 1;
        dataStore.add(listIndex, params);
        System.out.println("Undo");
    }

    public void update(int listIndex, String params, String input) throws CustomException{
        int listSize = dataStore.size() - 1;
        if(invalidIndexRange(listIndex, listSize)){throw new CustomException("Invalid index " +
                "range");}
        System.out.println("Update #" + input);
        dataStore.set(listIndex, params);
    }

    public void revertUpdate(int listIndex, String original) {
        dataStore.set(listIndex, original);
    }

    public ArrayList<String> getData() {
        return dataStore;
    }

    private void printData() {
        for (int i = 0; i < dataStore.size(); i++) {
            System.out.printf("%02d. "+dataStore.get(i)+"\n", i+1);
        }
//        for (String s : dataStore) {
//            System.out.println(s);
//        }
    }


}
