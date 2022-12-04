import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
/**
 * Write a description of class Rucksack2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rucksack2
{
   
    private ArrayList<Line3> lineas;

    public Rucksack2() {
        lineas = new ArrayList<>();
    }

    public void processFile(String fileInput) {
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(fileInput);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            do {
                String str0= sc.nextLine();
                String str1= sc.nextLine();
                String str2= sc.nextLine();
                Line3 l = new Line3(str0,str1,str2);
                lineas.add(l);
            } while (sc.hasNextLine());
            sc.close(); // closes the scanner
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int calculateErrors() {
        int c = 0;
        Iterator<Line3> itr = lineas.iterator();
        while (itr.hasNext()) {
            Line3 l = itr.next();
            c = c + processLinea3(l);
        }
        return c;

    }

    public int processLinea3(Line3 l) {
        /*
         * 1.- Partir la linea por la mitad y generar dos strings 
         * 2.- Crear un conjunto
         * con los elementos de cada string pasandolos a valores enteros 
         * 3.- Ejecutar la interseccion de ambos conjuntos y devolver el valor del resultado
         */
        int retorno=0;
        String str0 = l.getLine(0).getStr();
        String str1 = l.getLine(1).getStr();
        String str2 = l.getLine(2).getStr();
        Set<Integer> set0 = getSet(str0);
        Set<Integer> set1 = getSet(str1);
        Set<Integer> set2 = getSet(str2);
        set0.retainAll(set1);
        set0.retainAll(set2);
        for(int i:set0) {
            retorno=i;
        }
        return retorno;
    }

    private LinkedHashSet<Integer> getSet(String str) {
        LinkedHashSet<Integer> ret = new LinkedHashSet<>();
        for (char ch : str.toCharArray()) {
            int i = charToInt(ch);
            ret.add(i);
        }
        return ret;
    }

    public int charToInt(char c) {
        int i = c;
        if (c <= 122 && c >= 97) {
            return c - 96;
        }
        if (c <= 90 && c >= 65) {
            return c - 64+26;
        }
        return i;
    }

    public static void main(String[] args) {
        Rucksack2 r = new Rucksack2();
        r.processFile(args[0]);
        System.out.println("El resultado es:"+r.calculateErrors());
    }

    public class Line {

        public String str;

        public Line(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }
    }
    public class Line3{
        private Line lineas[]=new Line[3];
    
        public Line3(String s0,String s1,String s2){
        lineas[0]=new Line(s0);
        lineas[1]=new Line(s1);
        lineas[2]=new Line(s2);
        }
        
        public Line3(Line l1,Line l2,Line l3){
        lineas[0]=l1;
        lineas[1]=l2;
        lineas[2]=l3;
        }
    
        public Line[] getLineas3(){
        return lineas;
        }
        public Line getLine(int i){
        return lineas[i];
    }
    }
}
