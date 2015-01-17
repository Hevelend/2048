package Lab0POO;

public class C2048 {
	private int BoardGame[][] = new int[4][4];
	private int Score = 0;
	private boolean EndGame = true;
	private int UselessVar = 0;
	
	public String toString(){
		String Draw = "";
		Draw = "---------";
		for (int y=0; y < 3; y++){
			Draw += "|";
			for (int x=0; x < 3; x++)
				switch(BoardGame[y][x]){
				case -1 : Draw += "O";break;
				case 0 : Draw += " ";break;
				case 1 : Draw += "X";break;
				}
			Draw += "|\n";
		}
		return Draw;			
	}
}
