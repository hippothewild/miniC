package Absyn;

import java.util.*;

public class IdExpr extends Expr {
    String name;
    boolean isArrayElem;
    Expr index;

    public IdExpr(String n) {
        // Single identifier, or array itself
        name = n;
        isArrayElem = false;
    }

    public IdExpr(String n, Expr i) {
        // Element of array
        name = n;
        index = i;
        isArrayElem = true;
    }

    public void printAST() {
        printWriter.print(name);
        if (isArrayElem) {
            printWriter.printf("[");
            index.printAST();
            printWriter.printf("]");
        }
    }

    public IdExpr semanticAnalysis() {
        // Do semantic check for given identifier.
        Symbol sym = getSymbolFromSymbolTable(this.name);
        if (sym == null) {
            raiseError(SEMANTIC_ERR, "Variable " + this.name + " is not declared.");
        }

        IdExpr i = new IdExpr(this.name);

        if (this.isArrayElem) {
            i.isArrayElem = true;
            if (sym.length == 0) {
                // Symbol is declared as single variable, but referenced as if it's element of array
                raiseError(SEMANTIC_ERR, "Referenced index of non-array variable " + this.name + ".");
            }

            i.index = this.index.semanticAnalysis();
            if (i.index.getType() != TypeName.INT) {
                raiseError(TYPE_ERR, "Array index of variable " + this.name + " should be int type.");
            }

            blockIdx++;
            printWriter.println("    MOVE VR(0)@ VR(" + blockIdx + ")");
            sym.tGetValue();
            printWriter.println("    ADD MEM(VR(0)@)@ VR(" + blockIdx + ")@ VR(0)");
            printWriter.println("    MOVE MEM(VR(0)@)@ VR(0)");
            blockIdx--;

            i.setType(sym.type.toSingleType().typeName);

        } else {
            i.isArrayElem = false;
            sym.tGetValue();
            printWriter.println("    MOVE MEM(VR(0)@)@ VR(0)");
            i.setType(sym.type.typeName);
        }

        return i;
    }
}
