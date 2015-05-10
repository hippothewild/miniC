package Absyn;

import java.util.*;

public class Program extends Absyn {
    DeclList declList = null;
    FuncList funcList = null;

    public Program(DeclList d, FuncList f) {
        declList = d;
        funcList = f;
    }

    public void printAST() {
        if (declList != null) {
            declList.printAST();
        }
        if (funcList != null) {
            funcList.printAST();
        }
    }
}
