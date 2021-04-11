package D4.B2;

public class Question {
    protected String Ques,A1,A2,A3,A4,Ans;

    public Question() {
    }

    public Question(String ques, String a1, String a2, String a3, String a4, String ans) {
        Ques = ques;
        A1 = a1;
        A2 = a2;
        A3 = a3;
        A4 = a4;
        Ans = ans;
    }

    public String getQues() {
        return Ques;
    }

    public void setQues(String ques) {
        Ques = ques;
    }

    public String getA1() {
        return A1;
    }

    public void setA1(String a1) {
        A1 = a1;
    }

    public String getA2() {
        return A2;
    }

    public void setA2(String a2) {
        A2 = a2;
    }

    public String getA3() {
        return A3;
    }

    public void setA3(String a3) {
        A3 = a3;
    }

    public String getA4() {
        return A4;
    }

    public void setA4(String a4) {
        A4 = a4;
    }

    public String getAns() {
        return Ans;
    }

    public void setAns(String ans) {
        Ans = ans;
    }
}
