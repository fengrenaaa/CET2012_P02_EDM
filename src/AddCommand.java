public class AddCommand implements Command {
    private Receiver receiver;
    private String params;

    public AddCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }
    @Override
    public void execute() {
//        String addedInput = String.format("%02d. "+ params+"\n", addedIndex);
        receiver.add(params);
    }

    @Override
    public void undo() {
        receiver.removeAdded();
    }

    @Override
    public boolean shouldRecord() {return true;}
}
