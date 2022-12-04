
public class Ronda {
	private RockPaperScissor j1,j2;
	private int score;
	
	public Ronda(RockPaperScissor j1,RockPaperScissor j2) {
		this.j1=j1;
		this.j2=j2;
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


	

}
