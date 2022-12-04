import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Rucksack {

    private ArrayList<Line> lineas;

    public Rucksack() {
        lineas = new ArrayList<>();
    }

    public void processFile(String fileInput) {
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(fileInput);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            do {
                String str = sc.nextLine();
                Line l = new Line(str);
                lineas.add(l);
            } while (sc.hasNextLine());
            sc.close(); // closes the scanner
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int calculateErrors() {
        int c = 0;
        Iterator<Line> itr = lineas.iterator();
        while (itr.hasNext()) {
            Line l = itr.next();
            c = c + processLine(l);
        }
        return c;

    }

    public int processLine(Line l) {
        /*
         * 1.- Partir la linea por la mitad y generar dos strings 
         * 2.- Crear un conjunto
         * con los elementos de cada string pasandolos a valores enteros 
         * 3.- Ejecutar la interseccion de ambos conjuntos y devolver el valor del resultado
         */
        int retorno=0;
        String str = l.getStr();
        int length = str.length();
        String str1 = str.substring(0, length / 2);
        String str2 = str.substring(length / 2, length);
        Set<Integer> set1 = getSet(str1);
        Set<Integer> set2 = getSet(str2);
        set1.retainAll(set2);
        for(int i:set1) {
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
        Rucksack r = new Rucksack();
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
}
