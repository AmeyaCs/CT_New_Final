import java.lang.String;
import java.nio.file.*;

public class Expression {

    Expression lhs;
    String rhs;
    String op;
    String lhsf;
    String ts;

    public String getPrettyExpression() {
        if (lhsf != null) {              //if this is the last node and no more nodes are left, return lhsf
            ts = lhsf;
            return lhsf  + op + rhs;
        } else {
            return '(' + lhs.getPrettyExpression() + ')' + op + rhs;   // simplify lhs and print wth op and rhs.
        }
    }


    public void changeOperands(){
        if(lhs!=null)
            lhs.changeOperands();
        if(op.equals("equals"))
            op="=";
        else if(op.equals("add"))
            op="+";
        else if(op.equals("subtract"))
            op="-";
        else if(op.equals("divide"))
            op="/";
        else if(op.equals("multiply"))
            op="*";
    }
}
