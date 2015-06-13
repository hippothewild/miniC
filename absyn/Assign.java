package Absyn;

import java.util.*;

public class Assign extends Absyn {
    String name;
    Expr index, expr;

    public Assign(String n, Expr i, Expr e) {
        name = n;
        index = i;
        expr = e;
    }

    public void printAST() {
        printWriter.write(name);
        if (index != null) {
            printWriter.write("[");
            index.printAST();
            printWriter.write("]");
        }
        printWriter.write("=");
        expr.printAST();
    }

    public Assign semanticAnalysis() {
        // Do semantic check for given assign.
        Assign a = new Assign(name, null, null);
        Symbol sym = getSymbolFromSymbolTable(this.name);
        if (sym == null) {
            raiseError(SEMANTIC_ERR, "Variable " + this.name + " is not declared.");
        }

        a.expr = this.expr.semanticAnalysis();
        printWriter.println("    MOVE VR(0)@ VR(" + (blockIdx+1) + ")");
        blockIdx++;

        if (this.index != null) {
            if (sym.length == 0) {
                raiseError(SEMANTIC_ERR, "Referenced index of non-array variable " + this.name + ".");
            }

            a.index = this.index.semanticAnalysis();
            printWriter.println("    MOVE VR(0)@ VR(" + (blockIdx+1) + ")");
            blockIdx++;
            sym.tGetValue();
            blockIdx--;
            printWriter.println("    ADD MEM(VR(0)@)@ VR(" + (blockIdx+1) + ")@ VR(0)");

            if (a.index.getType() != TypeName.INT) {
                raiseError(TYPE_ERR, "Index of array variable " + this.name + " should have int type.");
            }

            if (sym.type.toSingleType().typeName == TypeName.INT && a.expr.type.typeName != TypeName.INT) {
                raiseError(TYPE_ERR, "Assign to element in array " + this.name + " expects int type, but got other type.");
            } else if (sym.type.toSingleType().typeName == TypeName.FLOAT && a.expr.type.typeName != TypeName.FLOAT) {
                if (a.expr.type.typeName == TypeName.INT) {
                    raiseWarn("Assign to element in array " + this.name + " expects float type, but got int type.");
                    printWriter.println("    I2F VR(0)@ VR(0)");
                } else {
                    raiseError(TYPE_ERR, "Assign to element in array " + this.name + " expects float type, but got other type.");
                }
            }

        } else {
            if (sym.type.typeName == TypeName.INT_ARRAY || sym.type.typeName == TypeName.FLOAT_ARRAY) {
                raiseError(TYPE_ERR, "Can't assign directly to array " + this.name + "; should indicate index.");
            } else {
                if (sym.type.typeName == TypeName.INT && a.expr.type.typeName != TypeName.INT) {
                    raiseError(TYPE_ERR, "Assign to variable " + this.name + " expects int type, but got other type.");
                } else if (sym.type.typeName == TypeName.FLOAT && a.expr.type.typeName != TypeName.FLOAT) {
                    if (a.expr.type.typeName == TypeName.INT) {
                        raiseWarn("Assign to variable " + this.name + " expects float type, but got int type.");
                        printWriter.println("    I2F VR(0)@ VR(0)");
                    } else {
                        raiseError(TYPE_ERR, "Assign to element in array " + this.name + " expects float type, but got other type.");
                    }
                }
            }
            sym.tGetValue();
        }

        blockIdx--;
        printWriter.println("    MOVE VR(" + (blockIdx+1) + ")@ MEM(VR(0)@)");

        return a;
    }
}
