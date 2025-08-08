package commands;

import static utilities.InputValidation.*;
import receiver.Receiver;
import customexception.CustomException;

/**
 * Represents a command to delete an item from the receiver.
 */
public class DeleteCommand implements Command{
    /**
     * The receiver that performs data operations.
     */
    private Receiver receiver;
    /**
     * The raw input string provided for deletion.
     */
    private String temp;
    /**
     * The index of the item to be deleted, in string form (as initially input).
     */
    private String index;
    /**
     * The content of the item to be deleted. Stored for undo purposes.
     */
    private String params;
    /**
     * The parsed integer index of the item in the list to be deleted.
     */
    private int listIndex;

    /**
     * Constructs a DeleteCommand with the receiver and input string.
     * @param receiver the receiver.
     * @param index the input index string.
     */
    public DeleteCommand(Receiver receiver, String index) {
        this.receiver = receiver;
        this.temp = index;
    }

    /**
     * Executes the delete operation.
     */
    @Override
    public void execute() throws CustomException {
        validateInput(temp);
        System.out.println("Delete # " + index);
        this.params = receiver.delete(listIndex);
    }

    /**
     * Undoes the delete operation.
     */
    @Override
    public void undo() {
        System.out.println("Undo delete");
        receiver.add(listIndex, params);
    }
    /**
     * Indicates whether this command should be recorded.
     * @return true if it should be recorded; false otherwise.
     */
    @Override
    public boolean shouldRecord() {return true;}

    /**
     * Validates the input string.
     * @param input the string to validate.
     * @return true if valid; false otherwise.
     */
    private boolean validateInput(String input) throws CustomException{
        if(input.isEmpty()){throw new CustomException("Delete: Input should not be empty");}
        String[] inputs = parseInputToArray(input);
        if(invalidDeleteLength(inputs)){throw new CustomException("Delete: Invalid delete command length");}
        if(invalidIndexDataType(inputs[0])){throw new CustomException("Delete: Invalid index data type");}
        this.index = inputs[0];
        listIndex = Integer.parseInt(index) - 1;
        int listSize = receiver.getData().size() - 1;
        if(invalidIndexRange(listIndex, listSize)){throw new CustomException("Delete: Invalid index " +
                "range");}
        return true;
    }
}
