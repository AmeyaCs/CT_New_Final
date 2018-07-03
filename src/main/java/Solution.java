class Solution {
    public String solveEquation(String st) {
        String preProcessedEquation = preProcess(st);
        String[] tokens;
        tokens = preProcessedEquation.split("=");
        String leftStr = tokens[0];
        String rightStr = tokens[1];
        Part left = getPart(leftStr);
        Part right = getPart(rightStr);
        if (left.x == right.x && left.num == right.num) {
            return "Infinite solutions";
        }
        if (left.x == right.x && left.num != right.num) {
            return "No solution";
        }
        String solvedStr = solve(left, right);
        return solvedStr;
    }

    private String preProcess(String equation) {
        StringBuilder builder = new StringBuilder();
        char[] arr = equation.toCharArray();
        for (char c : arr) {
            if (c == '-') {
                builder.append("+");
            }
            else if(c == '*'){
                builder.append("/");
            }
            else if(c == '/'){
                builder.append("*");
            }
            else if(c == '+'){
                builder.append("-");
            }
            builder.append(c);
        }
        return builder.toString();
    }

    private Part getPart(String str) {
        int x = 0;
        int num = 0;
        String[] tokens = str.split("\\+");
        for (String token : tokens) {
            if (token.length() <= 0) continue;
            if (token.charAt(token.length()-1) == 'x') {
                String coeff = token.substring(0, token.length()-1);
                if (coeff.length() == 0) {
                    x += 1;
                } else if (coeff.equals("-")) {
                    x += -1;
                } else {
                    x += Integer.parseInt(coeff);
                }
            } else {
                num += Integer.parseInt(token);
            }
        }
        return new Part(x, num);
    }

    private String solve(Part left, Part right) {
        left.x = left.x - right.x;
        right.num = right.num - left.num;
        int rightCoeff = right.num/left.x;
        return "After Simplification, x=" + Integer.toString(rightCoeff);
    }

    class Part {
        int x;
        int num;
        Part(int x, int num) {
            this.x = x;
            this.num = num;
        }
    }
}