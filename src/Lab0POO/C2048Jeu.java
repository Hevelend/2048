package Lab0POO;

public class C2048Jeu {

	public static void main(String[] args) {
		
		C2048 AwesomeGame = new C2048();
		while (!AwesomeGame.isFini()){
			AwesomeGame.jouer();
			AwesomeGame.toString();
		}
		
		System.out.println(AwesomeGame.getPoint());
	}
}
