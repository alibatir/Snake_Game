package project;

import java.awt.Color;
import ui.GridPanel;
/**
 * Class that allows Foods are created  
 * implementing some methods which Foods should have 
 * 
 */
public class Food extends Creature {

/**
	 * Creates a new Food instance
	 * @param X the coordinate of location of creature along the width on the grid 
	 * @param Y the coordinate of location of creature along the height on the grid
	 */
	public Food(int x,int y)
	{
		super(x,y);
		setHealth(0.5); //set the value of Foods' initial health 
	}
@Override
	public void draw(GridPanel panel)
	{
	 panel.drawSquare(getX(),getY(),Color.BLUE);
	}


}
 



	


