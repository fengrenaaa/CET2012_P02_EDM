import java.util.Arrays;

import static Utilities.InputValidation.*;

public class UpdateCommand implements Command {
    private Receiver receiver;
    private String params;
    private String original;
    private int listIndex;
    private String[] inputs;

    public UpdateCommand(Receiver receiver, String params) throws CustomException{
        this.receiver = receiver;
        if (validateInput(params)) {
            this.original = receiver.getData().get(listIndex);
            this.params = constructNewParams();
        };
    }
    @Override
    public void execute() throws CustomException {
        String input = String.join(" ", inputs);
        receiver.update(listIndex, params, input);
    }

    @Override
    public void undo() {
        receiver.revertUpdate(listIndex, original);
    }

    @Override
    public boolean shouldRecord() {return true;}

    private boolean validateInput(String input) throws CustomException{
        if (isEmpty(input)){throw new CustomException("Input should not be empty");}
        inputs = parseInputToArray(input);
        if(invalidUpdateLength(inputs)){throw new CustomException("Invalid update length");}
        if(invalidIndexDataType(inputs[0])){throw new CustomException("Invalid index data type");}
        listIndex = Integer.parseInt(inputs[0]) - 1;
        if(inputs.length >=2){inputs[1] = titledName(inputs[1]);};
        if(inputs.length >=3){inputs[2] = titledName(inputs[2]);};
        if(inputs.length ==4){
            int data3Validation = validateData3(inputs[2]);
            if (data3Validation < 0){throw new CustomException("Email: "+inputs[3]+" is not " +
                    "valid");}
            if (data3Validation == 2){inputs[3] = titledName(inputs[3]);}
        };
        return true;
    }
    private String constructNewParams() {
        String[] currentFields = parseInputToArray(original);
        for(int i = 1; i < inputs.length; i++){
            currentFields[i-1] = inputs[i];
        }
        return String.join(" ", currentFields);
    }
}
