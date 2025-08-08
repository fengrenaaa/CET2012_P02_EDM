package commands;

import static utilities.InputValidation.*;
import receiver.Receiver;
import customexception.CustomException;

public class DeleteCommand implements Command{
    private Receiver receiver;
    private String temp;
    private String index;
    private String params;
    private int listIndex;

    public DeleteCommand(Receiver receiver, String index) {
        this.receiver = receiver;
        this.temp = index;
    }
    @Override
    public void execute() throws CustomException {
        validateInput(temp);
        System.out.println("Delete # " + index);
        this.params = receiver.delete(listIndex);
    }

    @Override
    public void undo() {
        System.out.println("Undo delete");
        receiver.add(listIndex, params);
    }

    @Override
    public boolean shouldRecord() {return true;}

    private boolean validateInput(String input) throws CustomException{
        if(input.isEmpty()){throw new CustomException("Input should not be empty");}
        String[] inputs = parseInputToArray(input);
        if(invalidDeleteLength(inputs)){throw new CustomException("Invalid delete command length");}
        if(invalidIndexDataType(inputs[0])){throw new CustomException("Invalid index data type");}
        this.index = inputs[0];
        listIndex = Integer.parseInt(index) - 1;
        int listSize = receiver.getData().size() - 1;
        if(invalidIndexRange(listIndex, listSize)){throw new CustomException("Invalid index " +
                "range");}
        return true;
    }
}
