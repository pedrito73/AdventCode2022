import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class RPS {
    private ArrayList<Ronda> rondas;
    private ArrayList<Ronda2> rondas2;
    
    public RPS() {
        rondas=new ArrayList<>();
        rondas2=new ArrayList<>();
    }
    
    public void addRonda(RockPaperScissor j1,RockPaperScissor j2) {
        Ronda ronda=new Ronda(j1,j2);
        Ronda2 ronda2=new Ronda2(j1,j2.ordinal());
        rondas.add(ronda);
        rondas2.add(ronda2);
    }
    
    public int getFinalScore() {
        Iterator<Ronda> itr=rondas.iterator();
        int retorno=0;
        while(itr.hasNext()) {
            retorno=retorno+((Ronda) itr.next()).getScore();
        }
        return retorno;
    }
      public int getFinalScore2() {
        Iterator<Ronda2> itr=rondas2.iterator();
        int retorno=0;
        while(itr.hasNext()) {
            retorno=retorno+((Ronda2) itr.next()).getScore();
        }
        return retorno;
    }
    public void processFile(String fileInput) {
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(fileInput);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                String str=sc.nextLine();
                String[] entrada=str.split(" ");
                RockPaperScissor j1,j2;
                j1=getResult(entrada[0]);
                j2=getResult(entrada[1]);                       
                addRonda(j1,j2);
            }
            sc.close(); // closes the scanner
        } catch (Exception e) {
            e.printStackTrace();
        }

}

    
    private RockPaperScissor getResult(String string) {
        int i=Abc.valueOf(string).ordinal()%3;
        switch (i) {
            case 0: 
                return RockPaperScissor.ROCK;
        case 1:

                return RockPaperScissor.PAPER;
        case 2:

                return RockPaperScissor.SCISSOR;
        }
        return null;
    }

    public static void main(String[] args) {
        RPS rps=new RPS();
        rps.processFile(args[0]);
        System.out.println("El resultado final de la parte 1 es:"+rps.getFinalScore());
        System.out.println("El resultado final de la parte 2 es:"+rps.getFinalScore2());
    }

}
