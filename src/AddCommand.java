import static Utilities.InputValidation.*;

public class AddCommand implements Command {
    private Receiver receiver;
    private String params;

    public AddCommand(Receiver receiver, String params) throws CustomException {
        this.receiver = receiver;
        this.params = validateInput(params);
    }
    @Override
    public void execute() throws CustomException{
//        String addedInput = String.format("%02d. "+ params+"\n", addedIndex);
        receiver.add(params);
    }

    @Override
    public void undo() {
        receiver.removeAdded();
    }

    @Override
    public boolean shouldRecord() {return true;}

    private String validateInput(String input) throws CustomException{
//        if (input == null || input.isEmpty()) {
//            throw new CustomException("Input should not be empty");
//        }
        if(isEmpty(input)){throw new CustomException("Input should not be empty");}
//        String[] inputs = input.trim().split("\\s+");
        String[] inputs = parseInputToArray(input);
        if (invalidAddLength(inputs)) {throw new CustomException("Input length should be 3");}
//        if (invalidEmail(inputs[2])){throw new CustomException("Email is not valid");}
        int data3Validation = validateData3(inputs[2]);
        if (data3Validation < 0){throw new CustomException("Email: "+inputs[2]+" is not valid");}
        if (data3Validation == 2){inputs[2] = titledName(inputs[2]);}
        inputs[0] = titledName(inputs[0]);
        inputs[1] = titledName(inputs[1]);
        return String.join(" ", inputs);
    }

}
