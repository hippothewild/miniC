package Absyn;

import java.util.*;

public class Call extends Absyn {
    String name;
    ArgList args;

    public Call(String n, ArgList a) {
        name = n;
        args = a;
    }
    
    public void printAST() {
        printWriter.print(name);
        printWriter.print("(");
        if (args != null) {
            args.printAST();
        }
        printWriter.print(")");
    }
}
