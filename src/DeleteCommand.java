public class DeleteCommand implements Command{
    private Receiver receiver;
    private String index;
    private String params;

    public DeleteCommand(Receiver receiver, String index){
        this.receiver = receiver;
        this.index = index;
    }
    @Override
    public void execute() {
        this.params = receiver.delete(index);
    }

    @Override
    public void undo() {
        receiver.recover(index, params);
    }

    @Override
    public boolean shouldRecord() {return true;}
}
