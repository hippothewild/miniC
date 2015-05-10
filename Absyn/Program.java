package Absyn;

import java.util.*;

public class Program {
    DeclList declList;
    FuncList funcList;

    public Program(DeclList d, FuncList f) {
        declList = d;
        funcList = f;
    }
}
