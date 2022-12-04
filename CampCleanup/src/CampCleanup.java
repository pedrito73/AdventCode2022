import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CampCleanup {

    private int c = 0; // coincidencias
    private int o=0;//overlaps
    private ArrayList<Assignment> asign;

    public CampCleanup() {
        asign = new ArrayList<>();

    }

    public void addAsign(int l, int h) {
        Assignment a = new Assignment();
        a.setLb(l);
        a.setHb(h);
        asign.add(a);
    }

    public void addAsign(Assignment a){
        asign.add(a);
    }

    public void countContains() {
        Assignment[] arr = new Assignment[asign.size()];
        arr = asign.toArray(arr);

        for (int i = 0; i < arr.length - 1; i=i+2) {
            if (checkAinB(arr[i], arr[i+1]))
                c++;

        }
    }

    public void countOverlaps(){
        Assignment[] arr = new Assignment[asign.size()];
        arr = asign.toArray(arr);

        for (int i = 0; i < arr.length - 1; i=i+2) {
            if (overlaps(arr[i], arr[i+1]))
                o++;

        }
    }

    public int getC() {
        return c;
    }

    public int getO(){
        return o;
    }

    public void processFile(String InputFile) {
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(InputFile);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            do {
                String str = sc.nextLine();
                String[] rangos = str.split(",");
                Linea l=new Linea(rangos);
                addAsign(l.getA());
                addAsign(l.getB());

            } while(sc.hasNextLine());
            sc.close(); // closes the scanner
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean checkAinB(Assignment a, Assignment b) {
        return a.contains(b.getLb(), b.getHb()) || b.contains(a.getLb(), a.getHb());
    }

    public boolean overlaps(Assignment a,Assignment b){
        return a.overlaps(b.getLb(),b.getHb())||b.overlaps(a.getLb(),a.getHb());
    }

    public static void main(String[] args) {
        CampCleanup cu = new CampCleanup();
        cu.processFile(args[0]);
        cu.countContains();
        cu.countOverlaps();
        System.out.println("El numero de rangos contenidos es:" + cu.getC());
        System.out.println("El numero de overlaps es:"+cu.getO());

    }

    public class Linea {
        Assignment a, b;

        public Linea(String[] rangos) {
            int[] valores = new int[4];
            int d = 0;
            for (int i = 0; i < 2; i++) {
                String[] values = rangos[i].split("-");
                for (int j = 0; j < 2; j++) {
                    valores[d++] = Integer.parseInt(values[j]);
                }
            }
            a=new Assignment(valores[0],valores[1]);
            b=new Assignment(valores[2],valores[3]);
        }

        public Assignment getA(){
            return a;
        }

        public Assignment getB(){
            return b;
        }
    }
}
