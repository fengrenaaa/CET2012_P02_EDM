package client;

import java.util.ArrayList;
import java.util.Stack;
import commands.*;
import fileoperation.FileReadWrite;
import receiver.Receiver;
import invoker.Invoker;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Client {
    public static void main(String[] args) {

//        ArrayList<String> dataStore = FileReadWrite.readFile();
        Receiver receiver = new Receiver();
        try{
            AddCommand adc0 = new AddCommand(receiver, "fn ln abc@2.co");
            AddCommand adc1 = new AddCommand(receiver, "fn1 ln1 e1@xxx.com");
            AddCommand adc2 = new AddCommand(receiver, "fn2 ln2 e2@xxx.com");
            ListCommand list0 = new ListCommand(receiver, "");
            DeleteCommand dlc0 = new DeleteCommand(receiver, "2");
            Invoker invoker = new Invoker();
            invoker.setCommandsForExecution(new Command[]{adc0, adc1, adc2, list0, dlc0, list0});
            Stack<Command> history = new Stack<>();
            invoker.executeCommand(history);
            UndoCommand udc0 = new UndoCommand(receiver, history);
            invoker.setCommandsForExecution(new Command[]{udc0, udc0, list0});
            invoker.executeCommand(history);
            UpdateCommand updc0 = new UpdateCommand(receiver, "2 udfn upln   e3@update.cmd");
            invoker.setCommandsForExecution(new Command[]{updc0, list0});
            invoker.executeCommand(history);
            UndoCommand udc1 = new UndoCommand(receiver, history);
            invoker.setCommandsForExecution(new Command[]{udc1, list0});
            invoker.executeCommand(history);
        }catch(RuntimeException e){
            System.out.println(e.getMessage()+": "+e.getCause().getMessage());
        }finally {
            FileReadWrite.writeFile(receiver.getData());
        }

    }
}