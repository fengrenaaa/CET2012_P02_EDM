package receiver;

import fileoperation.FileReadWrite;

import java.util.ArrayList;

/**
 * Receiver class that holds and manipulates the main data store.
 */
public class Receiver {

    private ArrayList<String> dataStore;

    /**
     * Default constructor initializing the data store.
     */
    public Receiver(){
        dataStore = FileReadWrite.readFile();
    }

    /**
     * Constructor with Input data preloaded.
     * @param dataStore input data
     */
    public Receiver(ArrayList<String> dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Adds an item to the data store.
     * @param listIndex index to insert at.
     * @param params the item to add.
     */
    public void add(int listIndex, String params) {
        dataStore.add(listIndex, params);
    }

    /**
     * Lists the data entries.
     */
    public void list() {
        printData();
    }

    /**
     * Deletes the item at the given index.
     * @param listIndex the index of the item to delete.
     * @return the deleted item.
     */
    public String delete(int listIndex) {
        String deletedData = dataStore.get(listIndex);
        dataStore.remove(listIndex);
        return deletedData;
    }

    /**
     * Updates the item at the given index.
     * @param listIndex the index to update.
     * @param params the new value to set.
     */
    public void update(int listIndex, String params) {
        dataStore.set(listIndex, params);
    }

    /**
     * Returns the current data store.
     * @return the data list.
     */
    public ArrayList<String> getData() {
        return dataStore;
    }

    /**
     * Prints all data entries.
     */
    private void printData() {
        for (int i = 0; i < dataStore.size(); i++) {
            System.out.printf("%02d. %s"+"\n", i+1, dataStore.get(i));
        }
    }

    public void storeToFile() {
        FileReadWrite.writeFile(dataStore);
    }
}
