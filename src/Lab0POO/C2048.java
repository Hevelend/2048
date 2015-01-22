package Lab0POO;

import java.util.Random;
import java.util.Scanner;

public class C2048 {
	// Tableau contenant les valeurs de la partie
	private int BoardGame[][];
	private int Score = 0; // Score du joueur
	private int GameSeed = 0; // Seed de génération
	private int LenghtBoardGame = 4; // Taille du plateau de jeu
	private int CounterLoop = 1; // Utiliser pour le nextint du random
	
	// Constructeur
	protected C2048() {
		// Initialisation du tableau
		initBoardGame();
	}
	
	// Constructeur surchargé
	protected C2048(int LenghtGame, int Seed) {
		GameSeed = Seed;
		LenghtBoardGame = LenghtGame;
		initBoardGame();
	}

	// Initialisation du plateau
	private void initBoardGame(){
		BoardGame = new int[LenghtBoardGame][LenghtBoardGame];
		for (int y=0; y < LenghtBoardGame; y++){
			for (int x=0; x < LenghtBoardGame; x++){
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
	
	// Boucle de jeu
	protected void jouer(){
		int Direction;
		
		this.addTile();
		Direction = this.directionChoice();
		this.additionTile(Direction);
	}
	
	// Vérifie si la partie est finie
	protected boolean isFini(){
		// Valeur de retour, True = la partie n'est pas finie
		boolean GameOver = false;
		// Verifie si deux chiffres identiques sont adjacents
		boolean SameValue = false;
		int xAdjacent = 0;
		int yAdjacent = 0;
		
		// Parcours du tableau
		for (int y=0; y < LenghtBoardGame; y++){
			for (int x=0; x < LenghtBoardGame; x++){
				// Vérifie les cases adjacentes
				if(x < LenghtBoardGame){
					xAdjacent = x +1;
				} else {
					xAdjacent = x;
				}
				
				if(y < LenghtBoardGame){
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
					GameOver = true;
				}
			}
		}
		
		// Vérifie si le joueur peut encore faire des mouvements
		if(SameValue == false){
			GameOver = true;
		}
		
		return GameOver;
	}

	// Affichage du plateau de jeu
	public String toString(){
		String Draw = ""; // Variable de mise en page
		Draw = "---------------------";
		
		// Construction du plateau
		for (int y=0; y < LenghtBoardGame; y++){
			Draw += "|";
			for (int x=0; x < LenghtBoardGame; x++){
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
	
	// Choix d'une direction ou génération avec un seed
	private int directionChoice(){
		int Direction = 1;
		boolean Loop = true;
		String Text = "Saisir un chiffre : 1 Haut, 2 Gauche, 3Droite, 4 Bas";
		
		// Choix utilisateur
		if(GameSeed == 0){
			while(Loop == true){
				System.out.println(Text);
			    Scanner Reader = new Scanner(System.in);
			    if(Reader.nextInt() == 1 || Reader.nextInt() == 2 ||
			       Reader.nextInt() == 3 || Reader.nextInt() == 4  ){
			    	Direction = Reader.nextInt();
			    	Loop = false;
			    }
			}
		} else {
			// Génération avec un seed
			while(Loop == true){
				RandomDirection randomDir = new RandomDirection(GameSeed);
				Direction = randomDir.random(CounterLoop);
			    if(Direction == 1 || Direction == 2 || Direction == 3 ||
			       Direction == 4){
			    	Loop = false;
			    }
			    CounterLoop ++;
			}
		}
		
		return Direction;
	}
	
	//fonction qui ajoute aléatoirement une nouvelle tuile de 2 ou 4
	//sur le plateau de jeu
	private void addTile(){
		int valeurMax=4;
		int valeurMin=1;
		int randomTile=0;
		int i=0;
		
		//on cree un nombre aléatoire qui permettra de choisir si on 
		//obtient un deux ou un quatre sur le plateau de jeu
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(2); 
		
		if (nombreAleatoire <= 0.5){
			randomTile=2; 
		} else{
			randomTile=4;
		}
		
		while (i != 2){ // on effectue les lignes suivantes deux fois
				
		// Les deux variables suivantes permettent de choisir une case 
		// aléatoirement dans le plateau de jeu 
		
		int v1 = (int)(Math.random()*(valeurMax-valeurMin))+valeurMin;
		int v2 = (int)(Math.random()*(valeurMax-valeurMin))+valeurMin;
		
		if(BoardGame[v1][v2] == 0){ //on vérifie que la case aléatoire est vide
			//on place la valeur 2 ou 4 aléatoirement
			BoardGame[v1][v2] = randomTile; 
			i++;
			}
		}	
	}
	
	// Switch suivant la direction
	private void additionTile(int Direction){
		switch(Direction){
			case 1:
				additionTop();
				break;
			case 2:
				additionLeft();	
				break;
			case 3:
				additionRight();
				break;
			case 4:
				additionBottom();
				break;
		}
	}
	
	private void additionLeft(){
		for (int y=0; y < LenghtBoardGame ; y++){
			for (int x= LenghtBoardGame -1; x >= 0; x--){
				
				int var1 = BoardGame[y][x];
					
					
				
			}
		}	
	}
}
