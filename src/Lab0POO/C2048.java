package Lab0POO;

public class C2048 {
	// Tableau contenant les valeurs de la partie
	private int BoardGame[][] = new int[4][4];
	private int Score = 0; // Score du joueur
	private boolean EndGame = true; // True -> partie en cours
	private int UselessVar = 0; // ???
	
	// Constructeur
	protected C2048() {
		// Initialisation du tableau
		for (int y=0; y < 3; y++){
			for (int x=0; x < 3; x++){
				BoardGame[y][x] = 0;
			}
		}
	}

	// Retourne le score du joueur
	protected int getPoint() {
		return Score;
	}

	// Mise � jour du score du joueur
	protected void setPoint(int score) {
		Score = score;
	}
	
	// V�rifie si la partie est finie
	protected boolean isFini(){
		// Valeur de retour, True = la partie n'est pas finie
		boolean Running = true;
		// Verifie si deux chiffres identiques sont adjacents
		boolean SameValue = false;
		int xAdjacent = 0;
		int yAdjacent = 0;
		
		// Parcours du tableau
		for (int y=0; y < 3; y++){
			for (int x=0; x < 3; x++){
				// V�rifie les cases adjacentes
				if(x < 3){
					xAdjacent = x +1;
				} else {
					xAdjacent = x;
				}
				
				if(y < 3){
					yAdjacent = y +1;
				} else {
					yAdjacent = y;
				}
				
				if(BoardGame[y][x] == BoardGame[yAdjacent][x] ||
				   BoardGame[y][x] == BoardGame[y][xAdjacent]) {
					SameValue = true;
				}
				
				// V�rifie si le joueur est victorieux
				if(BoardGame[y][x] == 2048){
					Running = false;
				}
			}
		}
		
		// V�rifie si le joueur peut encore faire des mouvements
		if(SameValue == false){
			Running = false;
		}
		
		return Running;
	}

	// Affichage du plateau de jeu
	public String toString(){
		String Draw = ""; // Variable de mise en page
		Draw = "---------------------";
		
		// Construction du plateau
		for (int y=0; y < 3; y++){
			Draw += "|";
			for (int x=0; x < 3; x++){
				// On r�cup�re la valeur dans le tableau
				// et on mets en page
				String Line = Integer.toString(BoardGame[y][x]);
				switch (Integer.toString(BoardGame[y][x]).length()){
					case 1 : Line = "..." + Line; break;
					case 2 : Line = ".." + Line; break;
					case 3 : Line = "." + Line; break;
					case 4 : break;
				};
				Draw +=  Line + "|";
			}
			Draw += "\n";
		}
		
		Draw += "---------------------\n";
		return Draw;			
	}
}
