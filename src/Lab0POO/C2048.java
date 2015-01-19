package Lab0POO;

import java.util.Random;

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

	// Mise à jour du score du joueur
	protected void setPoint(int score) {
		Score = score;
	}
	
	// Vérifie si la partie est finie
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
				// Vérifie les cases adjacentes
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
				
				// Vérifie si le joueur est victorieux
				if(BoardGame[y][x] == 2048){
					Running = false;
				}
			}
		}
		
		// Vérifie si le joueur peut encore faire des mouvements
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
				// On récupère la valeur dans le tableau
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
	
	//fonction qui ajoute aléatoirement une nouvelle tuile de 2 ou 4
	//sur le plateau de jeu

		private void addTile(){
			
			int valeurMax=4;
			int valeurMin=1;
			int randomTile=0;
			int i=0;
			
			//on cree un nombre aléatoire qui permettra de choisir si on obtient un
			//deux ou un quatre sur le plateau de jeu
			Random rand = new Random();
			int nombreAleatoire = rand.nextInt(2); 
			
				if (nombreAleatoire <= 0.5){
					randomTile=2; //
				}
				else{
					randomTile=4;
				}
			
			
				while (i != 2){ // on effectue les lignes suivantes deux fois
						
					// Les deux variables suivantes permettent de choisir une case 
					// aléatoirement dans le plateau de jeu 
					
					int v1 = (int)(Math.random()*(valeurMax-valeurMin))+valeurMin;
					int v2 = (int)(Math.random()*(valeurMax-valeurMin))+valeurMin;
					
					if(BoardGame[v1][v2] == 0){//on vérifie que la case aléatoire est vide
					BoardGame[v1][v2] = randomTile; //on place la valeur 2 ou 4 aléatoirement
					i++;
						}
					}	
				}
}
