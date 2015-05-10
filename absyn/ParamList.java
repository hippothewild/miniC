package Absyn;

import java.util.*;

public class ParamList extends Absyn {
    ArrayList<Param> paramList;

    public ParamList(Param p) {
        paramList = new ArrayList<Param>();
        paramList.add(p);
    }

    public void add(Param p) {
        paramList.add(p);
    }

    public void printAST() {
        for (Param p : paramList.subList(0, paramList.size()-1)) {
            p.printAST();
			printWriter.print(", ");
        }
        paramList.get(paramList.size() - 1).printAST();
    }
}
