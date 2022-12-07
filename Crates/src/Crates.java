import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crates {
    private LinkedList<Stack<Character>> crates;
    private Stack<String> linesCrates;
    private LinkedList<String> linesMoves;
    int nCrates = 0;

    public Crates() {
        linesCrates = new Stack<>();
        linesMoves = new LinkedList<>();
    }

    public String getTops() {
        StringBuilder sb = new StringBuilder();
        for (Stack<Character> s : crates) {
            sb.append(s.peak());
        }
        return sb.toString();
    }

    public void processFile(String fileInput) {
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(fileInput);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            int status = 0; // VAMOS A USAR UN AUTOMATA DE DOS ESTADOS
            do {
                String str = sc.nextLine();
                if (status == 0) {
                    Pattern pattern = Pattern.compile("\\s[0-9]+\\s");
                    Matcher matcher = pattern.matcher(str);
                    if (matcher.find()) {
                        status = 1;
                        nCrates = 1;
                        while (matcher.find()) {
                            nCrates++;
                        }
                    } else {
                        linesCrates.push(str);
                    }
                } else {
                    if (str.length() > 0) {
                        linesMoves.add(str);
                    }
                }

            } while (sc.hasNextLine());
            sc.close(); // closes the scanner
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processMoves() {
        for(String str:linesMoves) {
            processMove(str);
        }
    }

    private void processMove(String str) {
        final String regex="[\\d]+";
        Pattern pattern=Pattern.compile(regex);
        Matcher match=pattern.matcher(str);
        int[] move=new int[3];
        int i=0;
        while(match.find()) {
            move[i]=Integer.parseInt(match.group());
            i++;
        }
//        for(int j=0;j<move[0];j++) {
//            moveCrate(move[1],move[2]);}
        moveCrate(move[0],move[1],move[2]);
        
    }

    private void moveCrate(int origen, int destino) {
        System.out.println("moving from "+origen +" to "+destino);
        char c=crates.get(origen-1).pop();
        crates.get(destino-1).push(c);

    }
    
    private void moveCrate(int n,int origen, int destino) {
    	if (n==1) {
    		moveCrate(origen,destino);
    	} else {
    		char c[]=new char[n];
    		for(int i=0;i<n;i++) {
    			c[i]=crates.get(origen-1).pop();
    		}
    		for(int i=0;i<n;i++) {
    			crates.get(destino-1).push(c[n-i-1]);
    		}
    		
    	}
    }

    private void processCrates() {
        //CREAMOS LAS CRATES VACIAS
        crates=new LinkedList<>();
        for(int i=0;i<nCrates;i++) {
            crates.add(new Stack<Character>());
        }
        //crates=(Stack<Character>[]) tmp.toArray();

        //PROCESAMOS LOS VALORES INICIALES
        int size=linesCrates.size();
        for (int i=0;i<size;i++) {
            processCrate(linesCrates.pop());
        }
    }

    private void processCrate(String line) {
        final String regex = "\\[[A-Z]\\]";
        Pattern pattern=Pattern.compile(regex);
        Matcher match=pattern.matcher(line);
        while(match.find()) {
            int pos=(match.end()+1)/4;
            char c=match.group().charAt(1);
            crates.get(pos-1).push(c);

        }
    }

    public int getCrates() {
        return nCrates;
    }

    public static void main(String[] args) {
        Crates c = new Crates();
        c.processFile(args[0]);
        c.processCrates();
        c.processMoves();
        System.out.println("el numero de crates es:" + c.getCrates());
        System.out.println("Las cajas tops son:" + c.getTops());
    }

}