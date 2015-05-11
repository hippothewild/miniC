package Absyn;

import java.util.*;

public class ArgList extends Absyn {
    ArrayList<Expr> argList;

    public ArgList(Expr e) {
        argList = new ArrayList<Expr>();
        if(e != null) {
            argList.add(e);
        }
    }

    public void add(Expr e) {
        if(e != null) {
            argList.add(e);
        }
    }

    public void printAST() {
        for (Expr e : argList.subList(0, argList.size()-1)) {
            e.printAST();
			printWriter.print(", ");
        }
        argList.get(argList.size() - 1).printAST();
    }
}
