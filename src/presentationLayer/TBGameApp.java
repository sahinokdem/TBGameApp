package presentationLayer;

import businessLayer.TBGame;
import businessLayer.TBGame.Initializer;
import businessLayer.TBGame.Menu;

public class TBGameApp {

    public static void main(String[] args) {
    	// Creating necessary game objects
        TBGame tbGame = new TBGame();
        Initializer tbGameInitializer = tbGame. new Initializer();
        tbGameInitializer.initializeGame();
        Menu tbGameMenu = tbGame. new Menu();
        tbGameMenu.startBattle();
    }

}
