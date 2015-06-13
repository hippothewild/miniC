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
        Call c = new Call(this.name, null);

        if (this.name.equals("printf")) {
            Expr _arg = this.args.argList.get(0).semanticAnalysis();
            printWriter.println("    WRITE VR(0)@");
            this.args.argList.remove(0);
            this.args.add(_arg);
            this.setType(TypeName.INT);
            return this;
        } else if (this.name.equals("scanf")) {
            Expr arg = this.args.argList.get(0);
            if (arg instanceof IdExpr) {
                IdExpr _arg = (IdExpr)arg;
                Symbol s = getSymbolFromSymbolTable(_arg.name);
                if (_arg.isArrayElem) {
                    _arg.index.semanticAnalysis();
                    blockIdx++;
                    printWriter.println("    MOVE VR(0)@ VR(" + blockIdx + ")");
                    s.tGetValue();
                    printWriter.println("    ADD MEM(VR(0)@)@ VR(" + blockIdx + ")@ VR(0)");
                    if (s.type.toSingleType().typeName == TypeName.FLOAT) {
                        printWriter.println("    READF MEM(VR(0)@)");
                    } else {
                        printWriter.println("    READI MEM(VR(0)@)");
                    }
                    blockIdx--;
                } else {
                    s.tGetValue();
                    if (s.type.toSingleType().typeName == TypeName.FLOAT) {
                        printWriter.println("    READF MEM(VR(0)@)");
                    } else {
                        printWriter.println("    READI MEM(VR(0)@)");
                    }
                }
            } else {
                raiseError(SEMANTIC_ERR, "Got non-variable in scanf");
            }
            this.setType(TypeName.INT);
            return this;
        } else {
            FunctionScope fs = getFunctionScope(this.name);
            if (fs == null) {
                raiseError(SEMANTIC_ERR, "Function " + this.name + " is not declared.");
            }

            for (int i = blockIdx; i >= 1; i--) {
                // Push all values in VR to memory.
                push("VR(" + i + ")@");
            }

            if (this.args != null) {
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
                        raiseError(TYPE_ERR, "Type mismatch at param " + i + " - " + param.type.typeName + " and " + arg.type.typeName);
                    }
                    push("VR(0)@");
                    c.args.add(arg);
                }
            }

            int returnLabelNum = labelNum++;
            push("LABEL" + returnLabelNum);
            push("FP@");
            printWriter.println("    MOVE SP@ FP");
            printWriter.println("    JMP " + this.name);
            printWriter.println("LAB LABEL" + returnLabelNum);
            printWriter.println("    SUB SP@ " + (fs.paramList.size()+1) + " SP");

            for (int i = 1; i <= blockIdx; i++) {
                // Pop all values in VR.
                pop("VR(" + i + ")");
            }

            c.setType(fs.type.typeName);
            return c;
        }
    }
}
