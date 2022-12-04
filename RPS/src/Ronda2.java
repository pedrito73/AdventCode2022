
/**
 * Write a description of class Ronda2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ronda2
{
    private RockPaperScissor j1;
    private RockPaperScissor j2;
    private int result;
    private int score;

    public Ronda2(RockPaperScissor j1,int result) {
        this.j1=j1;
        this.result=result;
        calcJ2(j1,result);
        calcScore();
    }

    private void calcScore() {
        int jugada2=0;
        if (j2==RockPaperScissor.ROCK) jugada2=1;
        if (j2==RockPaperScissor.PAPER) jugada2=2;
        if (j2==RockPaperScissor.SCISSOR) jugada2=3;
        this.score= jugada2+calcWinnerJ2(j1,j2);

    }

    private int calcWinnerJ2(RockPaperScissor j1, RockPaperScissor j2) {
        int gameMatrix[][]= {{3,6,0},{0,3,6},{6,0,3}};

        return gameMatrix[j1.ordinal()][j2.ordinal()];
    }

    public int getScore() {
        return score;
    }

    private void calcJ2(RockPaperScissor j1, int result){
        int gameMatrix[][]= {{3,6,0},{0,3,6},{6,0,3}};
        int[] fila=gameMatrix[j1.ordinal()];
        int posicion=4;
        for(int i=0;i<fila.length;i++){
            if(result*3==fila[i]){posicion=i;}
        }
        if (posicion==0) j2=RockPaperScissor.ROCK;

        if (posicion==1) j2=RockPaperScissor.PAPER;

        if (posicion==2) j2=RockPaperScissor.SCISSOR;
    }

}
