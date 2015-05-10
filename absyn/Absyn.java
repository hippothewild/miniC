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
    static public PrintWriter printWriter;

    public void printAST() {
        // Will be extended by each node
    }

    public void printSymTable() {
        // Will be extended by each node
    }
}
