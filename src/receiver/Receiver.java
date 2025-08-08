package receiver;

import fileoperation.FileReadWrite;

import java.io.IOException;
import java.util.ArrayList;
import static fileoperation.FileReadWrite.*;

public class Receiver {

    private ArrayList<String> dataStore;

    public Receiver(){
        dataStore = FileReadWrite.readFile();
    }

    public Receiver(ArrayList<String> dataStore) {
        this.dataStore = dataStore;
    }

    public void add(int listIndex, String params) {
        dataStore.add(listIndex, params);
    }

    public void list() {
        printData();
    }

    public String delete(int listIndex) {
        String deletedData = dataStore.get(listIndex);
        dataStore.remove(listIndex);
        return deletedData;
    }

    public void update(int listIndex, String params) {
        dataStore.set(listIndex, params);
    }

    public ArrayList<String> getData() {
        return dataStore;
    }

    private void printData() {
        for (int i = 0; i < dataStore.size(); i++) {
            System.out.printf("%02d. "+dataStore.get(i)+"\n", i+1);
        }
    }
}
