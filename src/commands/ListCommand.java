package commands;

import receiver.Receiver;
import customexception.CustomException;

/**
 * Represents a command that lists items from the receiver.
 */
public class ListCommand implements Command{
    /**
     * The receiver that performs data operations.
     */
    private Receiver receiver;
    /**
     * The input data for operations. Not necessary in this command.
     */
    private String params;
    /**
     * Constructs a ListCommand with the specified receiver.
     * @param receiver the receiver object that handles the data operations.
     */
    public ListCommand(Receiver receiver){
        this.receiver = receiver;
    }
    /**
     * Constructs a ListCommand with the specified receiver.
     * @param receiver the receiver object that handles the data operations.
     * @param params param the input string parameter for the command。
     */
    public ListCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = null;
    }

    /**
     * Executes the list operation.
     */
    @Override
    public void execute() throws CustomException {
        if (receiver.getData().isEmpty()) {throw new CustomException("The list is empty and " +
                "nothing to List");}
        System.out.println("List");
        receiver.list();
    }
    /**
     * Reverts the list operation, if applicable. Not used in this case.
     */
    @Override
    public void undo() {}

    /**
     * Indicates whether this command should be recorded for undo functionality.
     * @return true if it should be recorded; false otherwise.
     */
    @Override
    public boolean shouldRecord() {return false;}
}
