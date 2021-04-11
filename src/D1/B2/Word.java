package D1.B2;

public class Word {
    protected String EN,VN,Meaning;

    public Word() {
    }

    public Word(String EN, String VN, String meaning) {
        this.EN = EN;
        this.VN = VN;
        Meaning = meaning;
    }

    public String getEN() {
        return EN;
    }

    public void setEN(String EN) {
        this.EN = EN;
    }

    public String getVN() {
        return VN;
    }

    public void setVN(String VN) {
        this.VN = VN;
    }

    public String getMeaning() {
        return Meaning;
    }

    public void setMeaning(String meaning) {
        Meaning = meaning;
    }

    @Override
    public String toString() {
        return EN + '$' + VN + '$' + Meaning;
    }
}
