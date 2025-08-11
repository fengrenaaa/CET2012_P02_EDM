package client;

import java.util.ArrayList;
import java.util.Stack;
import commands.*;
import fileoperation.FileReadWrite;
import receiver.Receiver;
import invoker.Invoker;
import utilities.InputValidation;

public class Client {
    public static void main(String[] args) {

        ArrayList<String> dataStore = FileReadWrite.readFile();
        Receiver receiver = new Receiver();

        AddCommand adc0 = new AddCommand(receiver, "Ha%^$##nna Moon tetter.tots@potatoesarelife" +
                ".com");
        AddCommand adc1 = new AddCommand(receiver, "fn1 ln1 e1@xxx.com");
        AddCommand adc2 = new AddCommand(receiver, "fn2 ln2 e2@xxx.com");
        ListCommand list0 = new ListCommand(receiver);
        DeleteCommand dlc0 = new DeleteCommand(receiver, "2 ");
        Invoker invoker = new Invoker();
        invoker.setCommandsForExecution(new Command[]{adc0, adc1, adc2, list0, dlc0, list0});
        Stack<Command> history = new Stack<>();
        invoker.executeCommand(history);
        UndoCommand udc0 = new UndoCommand(receiver, history);
        invoker.setCommandsForExecution(new Command[]{udc0, list0});
        invoker.executeCommand(history);
        UpdateCommand updc0 = new UpdateCommand(receiver, "2 udfn upln   e3@update.cmd");
        invoker.setCommandsForExecution(new Command[]{updc0, list0});
        invoker.executeCommand(history);
        UndoCommand udc1 = new UndoCommand(receiver, history);
        invoker.setCommandsForExecution(new Command[]{udc1, list0});
        invoker.executeCommand(history);
        FileReadWrite.writeFile(receiver.getData());

//        String email = "aaa@bbb.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "aaa@bbb.ccc.";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "aaa@bb.b.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "aaa@bbb.c_cc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "aaa@bb_b.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "a.a.a@bbb.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "a__aa@bbb.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = ".aaa@bbb.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "aaa.@bbb.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "_aa-a.aaa_@bbb.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "_aa--a.aaa_@bbb.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "_aa-a..aaa_@bbb.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "_aa-.a.aaa_@bbb.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "_aa-a.-aaa_@bbb.ccc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "a@b.c";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "a@b.cc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "@b.cc";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "a@bb";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "a@bb.cccc.dd";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "a@bb.cccc.DD.dd";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "a@bb.cccc.DD";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "ABC_";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
//        email = "A.B-C_";
//        System.out.println(email + " - " + InputValidation.validateData3(email));
    }
}