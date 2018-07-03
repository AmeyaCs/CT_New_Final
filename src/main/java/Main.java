import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import sun.misc.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.lang.String;

public class Main {
    public static void main(String args[]) throws Exception {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Scanner sc=new Scanner(System.in);

        System.out.println("Give path for JSON file: ");
        String path=sc.nextLine();
        //preprocess file to enable gson to parse properly
        int x=JsonFileModification.modifyFile(path);
        InputStream inputStream = new FileInputStream(path);
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        Gson gson=new Gson();
        Expression expression=new Expression();
        expression=gson.fromJson(result, Expression.class);
        String st;
        expression.changeOperands();
        st = expression.getPrettyExpression();
        System.out.println("Before simplification: \n" + st);

        InToPost convert = new InToPost(st);
        System.out.println("This expression written in Postfix notation is: \n" + convert.infixToPostfix());

        String[] part = convert.infixToPostfix().split("=");
        String temp = part[0] + part[1] +  "-";

        String solve = part[1];

        String r = Misc.LhsToRhs.solution(part[0]);
        System.out.println("This is final:" + r);
        System.out.println("X : \n" + Calculate.evaluate("2 3 *"));

//        String expr = "((2*5)+(6/2))";
//        System.out.println("Expression: "+expr);
//        System.out.println("Final Result: "+evaluateInfix(expr));
//        expr = "(((2 * 5) - (1 * 2)) / (11 - 9))";
//        System.out.println("Expression: "+expr);
//        System.out.println("Final Result: "+evaluateInfix(expr));

        Scanner scanner = new Scanner(System.in);
//        String exp = scanner.nextLine().trim().replace(" ", "");
        System.out.println("Expression: " + Misc.LhsToRhs.solution(part[0]));

    }
}