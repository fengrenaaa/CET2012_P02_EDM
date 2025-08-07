import Utilities.InputValidation.*;

import static Utilities.InputValidation.*;

public class DeleteCommand implements Command{
    private Receiver receiver;
    private String index;
    private String params;

    public DeleteCommand(Receiver receiver, String index) throws CustomException{
        this.receiver = receiver;
        if(validateInput(index)){
            this.index = index;
        }
    }
    @Override
    public void execute() throws CustomException {
        this.params = receiver.delete(index);
    }

    @Override
    public void undo() {
        receiver.recover(index, params);
    }

    @Override
    public boolean shouldRecord() {return true;}

    private boolean validateInput(String input) throws CustomException{
        if(input.isEmpty()){throw new CustomException("Input should not be empty");}
        String[] inputs = parseInputToArray(input);
        if(invalidDeleteLength(inputs)){throw new CustomException("Invalid update length");}
        if(invalidIndexDataType(inputs[0])){throw new CustomException("Invalid index data type");}
//        int listIndex = Integer.parseInt(inputs[0]) - 1;
//        int size = receiver.getData().size();
//        if(invalidIndexRange(listIndex, size)){throw new CustomException("Invalid index range");}
        return true;
    }
}
