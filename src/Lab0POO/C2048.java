package Lab0POO;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class C2048 {
	// Tableau contenant les valeurs de la partie
	private int BoardGame[][];
	private int Score = 0; // Score du joueur
	private int GameSeed = 0; // Seed de g�n�ration
	private int LenghtBoardGame = 4; // Taille du plateau de jeu
	private int CounterLoop = 1; // Utiliser pour le nextint du random
	
	// Constructeur
	protected C2048() {
		// Initialisation du tableau
		initBoardGame();
	}
	
	// Constructeur surcharg�
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

	// Mise � jour du score du joueur
	protected void setPoint(int score) {
		Score = score;
	}
	
	// Boucle de jeu
	protected void jouer(){
		int Direction;
		
		this.addTile();
		System.out.println(this);
		Direction = this.directionChoice();
		this.additionTile(Direction);
	}
	
	// V�rifie si la partie est finie
	protected boolean isFini(){
		// Valeur de retour, True = la partie est finie
		boolean GameOver = false;
		// Verifie si deux chiffres identiques sont adjacents
		boolean SameValue = false;
		// Verifie si il reste des emplacements disponibles
		boolean FreeTile = false;
		
		// Parcours du tableau
		for (int y=0; y < LenghtBoardGame; y++){
			for (int x=0; x < LenghtBoardGame; x++){
				// Verifie si il reste des emplacements
				if(BoardGame[y][x] == 0){
					FreeTile = true;
				} else if(BoardGame[y][x] == 2048){
					// V�rifie si le joueur est victorieux
					GameOver = true;
				} else {
					// V�rifie les cases adjacentes
					if(x < LenghtBoardGame - 1){
						if(BoardGame[y][x] == BoardGame[y][x + 1]) {
							SameValue = true;
						}
					}
					if(y < LenghtBoardGame - 1){
						if(BoardGame[y][x] == BoardGame[y + 1][x]) {
							SameValue = true;
						}
					}
				}
			}
		}
		
		// V�rifie si le joueur peut encore faire des mouvements
		if(SameValue == false && FreeTile == false){
			GameOver = true;
		}
		
		return GameOver;
	}

	// Affichage du plateau de jeu
	public String toString(){
		String Draw = ""; // Variable de mise en page
		Draw = "---------------------\n";
		
		// Construction du plateau
		for (int y=0; y < LenghtBoardGame; y++){
			Draw += "|";
			for (int x=0; x < LenghtBoardGame; x++){
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
	
	// Choix d'une direction ou g�n�ration avec un seed
	private int directionChoice(){
		int Direction = 1;
		boolean Loop = true;
		String Text = "Saisir un chiffre : 1 Haut, 2 Gauche, 3 Droite, 4 Bas";
		
		// Choix utilisateur
		if(GameSeed == 0){
			Scanner ScanReader = new Scanner(System.in);
			do{
				System.out.println(Text);
	            try{
	            	Direction = ScanReader.nextInt();
	            	ScanReader.nextLine();
	            }
	            catch(InputMismatchException e){
	            	Direction = 0;
	            	ScanReader.next();
	            	ScanReader.nextLine();
	            }
	            if(Direction == 1 || Direction == 2 || Direction == 3 ||
 			       Direction == 4  ){
 			    	Loop = false;
 			    }
	        }while(Loop == true);
		} else {
			// G�n�ration avec un seed
			int TempCounter = CounterLoop;
			while(Loop == true){
				RandomDirection randomDir = new RandomDirection(GameSeed);
				Direction = randomDir.random(TempCounter);
			    if(Direction == 1 || Direction == 2 || Direction == 3 ||
			       Direction == 4){
			    	Loop = false;
			    }
			    TempCounter += 1;
			}
			CounterLoop ++;
		}
		
		return Direction;
	}
	
	//fonction qui ajoute al�atoirement une nouvelle tuile de 2 ou 4
	//sur le plateau de jeu
	private void addTile(){
		int valeurMax = LenghtBoardGame;
		int valeurMin = 0;
		int randomTile = 0;
		int i = 0;
		int PossibleTile = 2; // Nombre de tuile � placer
		int FreeTile = 0;
		
		//on cree un nombre al�atoire qui permettra de choisir si on 
		//obtient un deux ou un quatre sur le plateau de jeu
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(2); 
		
		if (nombreAleatoire <= 0.5){
			randomTile = 2; 
		} else{
			randomTile = 4;
		}
		
		// Calcul du nombre d'emplacements vident pour �viter une boucle
		// infinie si il ne reste qu'une cellule de libre et non deux.
		for (int y=0; y < LenghtBoardGame; y++){
			for (int x=0; x < LenghtBoardGame; x++){
				if(BoardGame[y][x] == 0){
					FreeTile += 1;
				}
			}
		}
		if(FreeTile > 2){
			PossibleTile = 2;
		} else {
			PossibleTile = FreeTile;
		}
		
		while (i != PossibleTile){ // on effectue les lignes suivantes deux fois
				
			// Les deux variables suivantes permettent de choisir une case 
			// al�atoirement dans le plateau de jeu 
			
			int v1 = (int)(Math.random()*(valeurMax-valeurMin))+valeurMin;
			int v2 = (int)(Math.random()*(valeurMax-valeurMin))+valeurMin;
			
			if(BoardGame[v1][v2] == 0){ //on v�rifie que la case al�atoire est vide
				//on place la valeur 2 ou 4 al�atoirement
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
	
	//fonction qui permet de g�rer le mouvement � gauche
	private int additionLeft(){
		for (int y = 0; y < LenghtBoardGame ; y ++){
			//on cree une nouvelle ligne 
			int[] newLine = new int[LenghtBoardGame];
			int cpt = 0;
			int cpt1 = 0;
			for (int x = 0; x < LenghtBoardGame; x ++){
				int var1 = BoardGame[y][x];
				newLine[x] = 0;
					if(var1 != 0){
						newLine[cpt] = var1;
						
						// Suppression de la cellule d�plac�e
						BoardGame[y][x] = 0;
						cpt ++;
					}
			}
		for (int x = 0; x < LenghtBoardGame; x ++){
			int temp = newLine[x];
				if (temp != 0){
					if (x == LenghtBoardGame - 1){
						BoardGame[y][cpt1] = temp;
						cpt1 ++;
					}
					else{
						if(newLine[x + 1] == temp){
							BoardGame[y][cpt1] = temp * 2;
							newLine[x + 1] = 0;
							cpt1 ++;
							
							Score += temp *2;
						
						}
						else{
							BoardGame[y][cpt1] = temp;
							cpt1 ++;
						}
					}
				}
			}
		}
		return Score;
	}

	//fonction qui permet de g�rer le mouvement � droite
	private int additionRight(){
		for (int y = 0; y < LenghtBoardGame ; y ++){
			//on cree une nouvelle ligne 
			int[] newLine = new int[LenghtBoardGame];
			int cpt = LenghtBoardGame - 1;
			int cpt1 = LenghtBoardGame - 1;
			for (int x = LenghtBoardGame - 1; x >= 0; x --){
				int var1 = BoardGame[y][x];
				newLine[x] = 0;
				if(var1 != 0){
					newLine[cpt] = var1;
					
					// Suppression de la cellule d�plac�e
					BoardGame[y][x] = 0;
					cpt --;
				}
			}
			for (int x = 0; x < LenghtBoardGame; x ++){
				int temp = newLine[x];
				if (temp != 0){
					if (x == LenghtBoardGame - 1){
						BoardGame[y][cpt1] = temp;
						cpt1 --;
					}
					else{
						if(newLine[x + 1] == temp){
							BoardGame[y][cpt1] = temp * 2;
							BoardGame[y][x] = 0;
							newLine[cpt1] = 0;
							cpt1 --;
							
							Score += temp * 2;
						
						}
						else{
							BoardGame[y][cpt1] = temp;
							newLine[x] = 0;
							cpt1 --;
						}
					}
				}
			}			
		}
		return Score;
	}
	
	//fonction qui permet de g�rer le mouvement vers le haut
	private int additionTop(){
		for (int x = 0; x < LenghtBoardGame ; x ++){
			//on cree une nouvelle colonne 
			int[] newColumn = new int[LenghtBoardGame];
			int cpt = 0;
			int cpt1 = 0;
			for (int y = 0; y < LenghtBoardGame; y ++){
				int var1 = BoardGame[y][x];
				newColumn[y] = 0; // on cree une nouvelle colonne
					if(var1 != 0){ // si la premi�re case n'est pas vide
						newColumn[cpt] = var1;
						
						// Suppression de la cellule d�plac�e
						BoardGame[y][x] = 0;
						cpt ++;
					}
			}
		for (int y = 0; y < LenghtBoardGame; y ++){
			int temp = newColumn[y];
				if (temp != 0){
					if (y == LenghtBoardGame - 1){
						BoardGame[cpt1][x] = temp;
						cpt1 ++;
					}
					else{
						if(newColumn[y + 1] == temp){
							BoardGame[cpt1][x] = temp * 2;
							newColumn[y + 1] = 0;
							cpt1 ++;
							
							Score += temp *2;
						
						}
						else{
							BoardGame[cpt1][x] = temp;
							cpt1 ++;
						}
					}
				}
			}
		}
		return Score;
	}
	
	///fonction qui permet de g�rer le mouvement vers le bas
	private int additionBottom(){
		for (int x = 0; x < LenghtBoardGame ; x ++){
			//on cree une nouvelle ligne 
			int[] newColumn = new int[LenghtBoardGame];
			int cpt = LenghtBoardGame - 1;
			int cpt1 = LenghtBoardGame - 1;
			for (int y = LenghtBoardGame - 1; y >= 0; y --){
				int var1 = BoardGame[y][x];
				newColumn[y] = 0;
				if(var1 != 0){
					newColumn[cpt] = var1;
					
					// Suppression de la cellule d�plac�e
					BoardGame[y][x] = 0;
					cpt --;
				}
			}
			for (int y = LenghtBoardGame - 1; y >= 0; y --){
				int temp = newColumn[y];
				if (temp != 0){
					if (y == 0){
						BoardGame[cpt1][y] = temp;
						cpt1 --;
					}
					else{
						if(newColumn[y - 1] == temp){
							BoardGame[cpt1][x] = temp * 2;
							BoardGame[y-1][x] = 0;
							newColumn[cpt1 - 1] = 0;
							cpt1 --;
							
							Score += temp * 2;
						
						}
						else{
							BoardGame[cpt1][x] = temp;
							newColumn[y] = 0;
							cpt1 --;
						}
					}
				}
			}			
		}
		return Score;

	}
}
