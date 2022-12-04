import java.io.FileInputStream;
import java.util.Scanner;

public class ElfFood {
    String fileInput;
    pQueue<Elf> elves;

    public ElfFood(String fileInput) {
        elves = new pQueue<>();
        processFile(fileInput);
    }

    private void processFile(String fileInput) {
        // TODO Auto-generated method stub
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(fileInput);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            Elf e=new Elf();
            while (sc.hasNextLine()) {
                String str=sc.nextLine();
                if (str.length()<=0) {
                    elves.insert(e,e.getPesoElf());
                    e=new Elf();
                } else {
                    e.addSnack(Integer.parseUnsignedInt(str));
                }
            }
            elves.insert(e,e.getPesoElf());
            sc.close(); // closes the scanner
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMaximumElfWeight() {
        return elves.first().getPesoElf();
    }

    public int getMaximumtThreeElfWeight(){
        int retorno=0;
        System.out.println("Numero de elfos:"+elves.size());
        for(int i=0;i<3;i++){
            int temp=elves.getHead().getPesoElf();
            System.out.println("elfo:"+i+" lleva:"+temp);
            retorno=retorno+temp;
        }
        return retorno;
    }

    public static void main(String[] args) {
        ElfFood ef=new ElfFood(args[0]);
        System.out.println("El peso maximo es:"+ef.getMaximumElfWeight());
        System.out.println("El peso maximo de los 3 es:"+ef.getMaximumtThreeElfWeight());
    }

}
