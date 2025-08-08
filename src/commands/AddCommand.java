package commands;
import static utilities.InputValidation.*;
import receiver.Receiver;
import customexception.CustomException;
/**
 * Represents a command that adds an item to the receiver's data.
 */
public class AddCommand implements Command {
    /**
     * The receiver that handles data operations.
     */
    private Receiver receiver;
    /**
     * The validated input string to be added.
     */
    private String params;
    /**
     * The raw input string provided by the user.
     */
    private String temp;
    /**
     * The index at which the new item is added to the data list.
     * Used for undoing the operation.
     */
    private int listIndex;

    /**
     * Constructs an AddCommand with the given receiver and input string.
     * @param receiver the receiver to perform the add operation on.
     * @param params the input string to be added.
     */
    public AddCommand(Receiver receiver, String params){
        this.receiver = receiver;
        this.temp = params;
    }

    /**
     * Executes the add operation.
     */
    @Override
    public void execute() throws CustomException{
        this.params = validateInput(temp);
        System.out.println("add");
        this.listIndex = receiver.getData().size();
        receiver.add(listIndex, params);
    }

    /**
     * Undoes the add operation.
     */
    @Override
    public void undo() {
        System.out.println("undo Add");
        receiver.delete(listIndex);
    }

    /**
     * Indicates whether this command should be recorded.
     * @return true if recordable, false otherwise.
     */
    @Override
    public boolean shouldRecord() {return true;}

    /**
     * Validates the input string.
     * @param input the input string.
     * @return the validated and possibly formatted string.
     */
    private String validateInput(String input) throws CustomException {
        if(isEmpty(input)){throw new CustomException("Add: Input should not be empty");}
        String[] inputs = parseInputToArray(input);
        if (invalidAddLength(inputs)) {throw new CustomException("Add: Input length should be 3");}
        int data3Validation = validateData3(inputs[2]);
        if (data3Validation < 0){throw new CustomException("Add Email: "+inputs[2]+" is not " +
                "valid");}
        if (data3Validation == 2){inputs[2] = titledName(inputs[2]);}
        inputs[0] = titledName(inputs[0]);
        inputs[1] = titledName(inputs[1]);
        return String.join(" ", inputs);
    }

}
