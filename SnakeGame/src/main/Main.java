package main;

import naturesimulator.NatureSimulator;
import project.Snake;
import ui.ApplicationWindow;

import java.awt.*;

/**
 * The main class that can be used as a playground to test your project. This
 * class will be discarded and replaced with our own grading class.
 */
public class Main {

	/**
	 * Main entry point for the application.
	 * @param args application arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				// Create game
				// You can change the world width and height, size of each grid square in pixels
				// or the game speed
				NatureSimulator game = new NatureSimulator(44, 44, 20, 1000);
				// add a new Food

				game.addFood();
				// add 4 snakes
				game.addCreature(new Snake(4, 1));

				// Create application window that contains the game panel
				ApplicationWindow window = new ApplicationWindow(game.getGamePanel());
				window.getFrame().setVisible(true);

				// Start game
				game.start();

				//

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
