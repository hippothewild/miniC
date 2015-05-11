package Absyn;

import java.io.PrintWriter;
import java.util.*;

public class Absyn {
    // Output option flag.
    static public boolean optionPrintAST      = true;
    static public String  astOutputName       = "ast.out";
    static public boolean optionPrintSymTable = true;
    static public String  symTableOutputName  = "sym_table.out";
    static public String  encoding            = "UTF-8";

    // Writer object.
    static public PrintWriter printWriter = null;

    // Symbol table assisting variables and functions.
    static public int scopeCount = 0;
    static public int symbolCount = 0;
    static public ArrayList<String> scopeStack = new ArrayList<String>();

    static public void printSymTableHeader() {
        int scopeNamePtr = 0;
        if (scopeStack.size() > 1) {
            // More scope name than GLOBAL context.
            scopeNamePtr = 1;
        }
        printWriter.print("Function name : " + scopeStack.get(scopeNamePtr++));

        for(; scopeNamePtr < scopeStack.size(); scopeNamePtr++) {
            printWriter.print(" - " + scopeStack.get(scopeNamePtr));
        }
        printWriter.printf("\n%8s%8s%16s%8s%12s\n", "Count", "Type", "Name", "Array", "Role");
    }

    static public void printSymTableRow(String type, String name, int arrayLen, String role) {

        if (arrayLen == 0) {
            // Non-array
            printWriter.printf("%8d%8s%16s%8s%12s\n", ++symbolCount, type, name, "", role);
        } else {
            // Array
            printWriter.printf("%8d%8s%16s%8d%12s\n", ++symbolCount, type, name, arrayLen, role);
        }
    }

    // Extendable printAST() & printSymTable() function.
    public void printAST() {
        // Will be extended by each node
    }

    public void printSymTable() {
        // Will be extended by each node
    }
}
