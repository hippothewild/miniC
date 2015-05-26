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

    public Call semanticAnalysis() {
        FunctionScope fs = getFunctionScope(this.name);
        if (fs == null) {
            raiseError(SEMANTIC_ERR, "Function " + this.name + " is not declared.");
        }

        Call c = new Call(this.name, null);
        c.args = this.args.semanticAnalysis();
        return c;
    }
}
