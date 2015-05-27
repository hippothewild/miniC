package Absyn;

import java.util.*;

public class Call extends Expr {
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
        // Check if function is properly declared or not.
        FunctionScope fs = getFunctionScope(this.name);
        if (fs == null) {
            raiseError(SEMANTIC_ERR, "Function " + this.name + " is not declared.");
        }

        Call c = new Call(this.name, null);
        if (this.args == null) {
            return c;
        } else {
            c.args = new ArgList(null);

            // Check if proper number of argument is given.
            if (fs.paramList.size() != this.args.argList.size()) {
                raiseError(SEMANTIC_ERR, "The number of arguments in function " + this.name + " should be " + fs.paramList.size() + ".");
            }

            // Check type of each parameter and argument are matched.
            for (int i = 0; i < fs.paramList.size(); i++) {
                Symbol param = fs.paramList.get(i);
                Expr arg = this.args.argList.get(i).semanticAnalysis();

                if(param.type.typeName != arg.type.typeName) {
                    raiseError(TYPE_ERR, "Type mismatch - " + param.type.typeName + " and " + arg.type.typeName);
                }
                c.args.add(arg);
            }
            c.setType(fs.type.typeName);
            return c;
        }
    }
}
