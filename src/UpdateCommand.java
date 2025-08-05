public class UpdateCommand implements Command {
    private Receiver receiver;
    private String params;
    private String original;
    private String listIndex;

    public UpdateCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }
    @Override
    public void execute() {
        String[] currentParams = receiver.update(params);
        this.original = currentParams[0];
        this.listIndex = currentParams[1];
    }

    @Override
    public void undo() {
        receiver.revertUpdate(listIndex, original);
    }

    @Override
    public boolean shouldRecord() {return true;}
}
