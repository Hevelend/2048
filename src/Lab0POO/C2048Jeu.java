package Lab0POO;

public class C2048Jeu {

	public static void main(String[] args) {
		
		C2048 AwesomeGame = new C2048();
		while (!AwesomeGame.isFini()){
			AwesomeGame.jouer();
			System.out.println(AwesomeGame);
		}
		
		System.out.println("Partie termin�e ! votre score est de : " +
		AwesomeGame.getPoint());
	}
}
