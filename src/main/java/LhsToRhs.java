package Misc;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.*;
import java.util.List;

public class LhsToRhs {

    public static String solution(String exp) {

        ImmutableList<String> OP_LIST = ImmutableList.of("+", "-", "*", "/");

        ImmutableMap<Object, Object> OP_MAP = ImmutableMap.builder()
                .put("+", "-")
                .put("-", "+")
                .put("*", "/")
                .put("/", "*")
                .build();

//        String temps;
        //Splitting string in LHS and RHS
        String[] splitExp = exp.split("=");
//        String temp = part[0] + part[1];

        List<String> fst = Arrays.asList(splitExp[0].split("(?<=[-*/{])|(?=[-*/}])"));

        Stack<String> st = new Stack<>();
        for (int i = 0; i < fst.size(); i++) {
            st.push(fst.get(i));
        }

        String operand = "";
        String operator = "";
        String finalExp = splitExp[1];

        while (st.empty() == false) {
            String c = st.pop();

            if (c.equals("(") || c.equals(")"))
                continue;

            if (!OP_LIST.contains(c)) {
                operand = c;
            } else {
                operator = (String) OP_MAP.get(c);
            }

            if (operand == "" && operand != "") {
                finalExp = finalExp + "(" + finalExp + operator;
            } else if (operand != "" && operator != "") {
                finalExp = "(" + finalExp + operator + operand + ")";
                operator = "";
                operand = "";
            }
        }
        return finalExp;
    }
}