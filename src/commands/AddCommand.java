package commands;
import static utilities.InputValidation.*;
import receiver.Receiver;
import customexception.CustomException;

public class AddCommand implements Command {
    private Receiver receiver;
    private String params;
    private String temp;
    private int listIndex;

    public AddCommand(Receiver receiver, String params){
        this.receiver = receiver;
        this.temp = params;
    }
    @Override
    public void execute() throws CustomException{
        this.params = validateInput(temp);
        System.out.println("add");
        this.listIndex = receiver.getData().size();
        receiver.add(listIndex, params);
    }

    @Override
    public void undo() {
        System.out.println("undo Add");
        receiver.delete(listIndex);
    }

    @Override
    public boolean shouldRecord() {return true;}

    private String validateInput(String input) throws CustomException {
        if(isEmpty(input)){throw new CustomException("Input should not be empty");}
        String[] inputs = parseInputToArray(input);
        if (invalidAddLength(inputs)) {throw new CustomException("Input length should be 3");}
        int data3Validation = validateData3(inputs[2]);
        if (data3Validation < 0){throw new CustomException("Email: "+inputs[2]+" is not valid");}
        if (data3Validation == 2){inputs[2] = titledName(inputs[2]);}
        inputs[0] = titledName(inputs[0]);
        inputs[1] = titledName(inputs[1]);
        return String.join(" ", inputs);
    }

}
