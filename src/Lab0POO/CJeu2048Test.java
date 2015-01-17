package Lab0POO;

/**
 * Cette classe sert d'outil de vérification de conformité pour le laboratoire 0.
 * @author Patrice Guérin
 */
public class CJeu2048Test {
	/**
	 * Un main standard.
	 * @param args rien du tout dans ce programme.
	 */
	public static void main(String[] args) {
	
		C2048 Game21 = new C2048(4, 8261);
		while (!Game21.isFini())	Game21.jouer();
		System.out.println(Game21.getPoint() == 36 ? "bon" : "mauvais");
	
		C2048 Game2 = new C2048(4, 5994);
		while (!Game2.isFini())	Game2.jouer();
		System.out.println(Game2.getPoint() == 4316 ? "bon" : "mauvais");
	}
}