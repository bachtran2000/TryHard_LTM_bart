package ReadWriteFile;

import java.io.*;
import java.util.ArrayList;

public class main {
    static ArrayList<main> listM = new ArrayList<>();
    protected String a,b;
    public main() {
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public static void ReadFile(){
        try{
            File f = new File("test.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line = br.readLine())!=null){
                String[] result;
                result = line.split("\\$");
                main m = new main();
                m.setA(result[0]);
                m.setB(result[1]);
                listM.add(m);
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriteFile(){
        try {
            File f = new File("test.txt");
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < listM.size(); i++) {
                if (i<listM.size())
                    fw.write(listM.get(i).toString()+"\n");
                else
                fw.write(listM.get(i).toString());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return a + "$"+b;
    }

    public static void main(String[] args) {
        ReadFile();
        WriteFile();

        System.out.println(listM.toString());
    }
}
