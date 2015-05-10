package Absyn;

import java.util.*;

public class ParamList {
    ArrayList<Param> paramList;

    public ParamList(Param p) {
        paramList = new ArrayList<Param>();
        paramList.add(p);
    }

    public void add(Param p) {
        paramList.add(p);
    }
}
