import java.util.ArrayList;

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

    public String delete(String index) {
        System.out.println("Delete # " + index);
        int listIndex = Integer.parseInt(index) - 1;
        String deletedData = dataStore.get(listIndex);
        dataStore.remove(listIndex);
        return deletedData;
    }

    public void recover(String index, String params) {
        int listIndex = Integer.parseInt(index) - 1;
        dataStore.add(listIndex, params);
        System.out.println("Undo");
    }

    public String[] update(String params) {
        String[] newFields = params.trim().split("\\s+");
        int listIndex = Integer.parseInt(newFields[0]) - 1;
        String currentData = dataStore.get(listIndex);
        String[] oldFields = currentData.trim().split("\\s+");
        for (int i = 1; i < newFields.length && i <= oldFields.length; i++){
            oldFields[i - 1] = newFields[i];
        }
        String newData = String.join(" ", oldFields);
        dataStore.set(listIndex, newData);
        return new String[]{currentData, "" + listIndex};
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

    public void revertUpdate(String listIndex, String original) {
        dataStore.set(Integer.parseInt(listIndex), original);
    }
}
