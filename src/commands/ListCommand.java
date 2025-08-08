package commands;

import receiver.Receiver;
import customexception.CustomException;

public class ListCommand implements Command{
    private Receiver receiver;
    private String params;

    public ListCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = null;
    }
    @Override
    public void execute() throws CustomException {
        if (receiver.getData().isEmpty()) {throw new CustomException("The list is empty and " +
                "nothing to List");}
        System.out.println("List");
        receiver.list();
    }

    @Override
    public void undo() {}

    @Override
    public boolean shouldRecord() {return false;}
}
