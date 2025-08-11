package commands;

import static utilities.InputValidation.*;
import receiver.Receiver;
import customexception.CustomException;

/**
 * Represents a command that updates data in the receiver.
 */
public class UpdateCommand implements Command {
    /**
     * The receiver that handles data operations.
     */
    private Receiver receiver;
    /**
     * The raw input string provided by the user.
     */
    private String temp;
    /**
     * The validated and formatted parameters used for updating the data.
     */
    private String params;
    /**
     * The original value at the specified index before the update,
     * used for undoing the operation.
     */
    private String original;
    /**
     * The index in the data list where the update is applied.
     */
    private int listIndex;
    /**
     * The parsed and validated input components from the user input string.
     */
    private String[] inputs;

    /**
     * Constructs an UpdateCommand with the receiver and input string.
     * @param receiver the receiver.
     * @param params the input parameters to be updated.
     */
    public UpdateCommand(Receiver receiver, String params){
        this.receiver = receiver;
        this.temp = params;
    }

    /**
     * Executes the update operation.
     */
    @Override
    public void execute() throws CustomException {
        validateInput(temp);
        this.original = receiver.getData().get(listIndex);
        this.params = constructNewParams();
        String input = String.join(" ", inputs);
        System.out.println("Update #" + input);
        receiver.update(listIndex, params);

    }

    /**
     * Undoes the update operation.
     */
    @Override
    public void undo() {
        System.out.println("Undo update");
        receiver.update(listIndex, original);
    }

    /**
     * Indicates whether the command should be recorded.
     * @return true if recordable; false otherwise.
     */
    @Override
    public boolean shouldRecord() {return true;}

    /**
     * Validates the input string for update operation.
     * @param input the input string.
     * @return true if valid; false otherwise.
     */
    private boolean validateInput(String input) throws CustomException{
        if (isEmpty(input)){throw new CustomException("Update: Input should not be empty");}
        inputs = parseInputToArray(input);
        if(invalidUpdateLength(inputs)){throw new CustomException("Update: Invalid update length");}
        if(invalidIndexDataType(inputs[0])){throw new CustomException("Update: Invalid index data" +
                " type");}
        listIndex = Integer.parseInt(inputs[0]) - 1;

        int listSize = receiver.getData().size() - 1;
        if(invalidIndexRange(listIndex, listSize)){throw new CustomException("Update: Invalid index " +
                "range");}

        if(inputs.length >=2){inputs[1] = titledName(inputs[1]);}
        if(inputs.length >=3){inputs[2] = titledName(inputs[2]);}
        if(inputs.length ==4){
            int data3Validation = validateData3(inputs[3]);
            if (data3Validation < 0){throw new CustomException("Update Email: "+inputs[3]+" is " +
                    "not " +
                    "valid");}
            if (data3Validation == 2){inputs[3] = titledName(inputs[3]);}
        }
        return true;
    }

    /**
     * Constructs new parameters for the update.
     * @return the constructed string.
     */
    private String constructNewParams() {
        String[] currentFields = parseInputToArray(original);
        for(int i = 1; i < inputs.length; i++){
            currentFields[i-1] = inputs[i];
        }
        return String.join(" ", currentFields);
    }
}
