package Lab0POO;

import java.util.Random;

public class RandomDirection {
	Random RandomDir;

    public RandomDirection(int SeedGame) {
    	RandomDir = new Random();
    	RandomDir.setSeed(SeedGame);
    }

    public int random(int i){
        return RandomDir.nextInt(i);
    }
}
