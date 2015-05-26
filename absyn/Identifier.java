package Absyn;

import java.util.*;

public class Identifier extends Absyn {
    String name;
    int size;

    public Identifier(String n) {
        name = n;
        size = 0;
    }

    public Identifier(String n, int s) {
        name = n;
        size = s;
    }

    public void printAST() {
        printWriter.print(name);
        if (size > 0) {
            printWriter.printf("[%d]", size);
        }
    }

    public Identifier semanticAnalysis() {
        return this;
    }
}
