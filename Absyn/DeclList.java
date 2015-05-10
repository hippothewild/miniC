package Absyn;

import java.util.*;

public class DeclList {
    ArrayList<Declaration> declList;

    public DeclList(Declaration d) {
        declList = new ArrayList<Declaration>();
        declList.add(d);
    }

    public void add(Declaration d) {
        declList.add(d);
    }
}
