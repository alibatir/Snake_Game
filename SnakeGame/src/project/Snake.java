package project;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import naturesimulator.Action.Type;
import ui.GridPanel;

/**
 * Class that allows Snakes are created implementing some methods which Snakes
 * should have
 * 
 */
public class Snake extends Creature {
	 //snake1 for implementing part of snake
	public LinkedList<Creature> snake1 = new LinkedList<Creature>();
	//the head of snake 
	public Creature head;
	//cntrl: the field provides snake are created for the first time or not 
	static boolean cntrl = true;
     //keep the coordinate of last parts
	public static int x1, y1, x2, y2, x3, y3;

	/**
	 * Creates a new Snake instance 
	 * @param X  the coordinate of location of creature along the width on the grid
	 * @param Y  the coordinate of location of creature along the height on the grid
	 */
	public Snake(int x, int y) {
		this(x, y, 1);
		snake1.add(this);
		head = snake1.getFirst();
		if (cntrl == true) {
			snake1.add(new Snake(3, 1, 1));
			snake1.add(new Snake(2, 1, 1));
			snake1.add(new Snake(1, 1, 1));
			cntrl = false;
		} else {
			snake1.add(new Snake(x1, y1, 1));
			snake1.add(new Snake(x2, y2, 1));
			snake1.add(new Snake(x3, y3, 1));
		}
		// head=creatureList.getFirst();
		setHealth(10.0); // set the value of Snakes' initial health
	}
    
	//private constructor
	private Snake(int x, int y, int z) {
		super(x, y);
	}
	/**
	 * Via this method,the Snake will decide on which action to perform depending on
	 * the information about its environment. 
	 * @param information representing the information a Snake has about its surroundings
	 * @return action the action chosen by the Snake
	 */
	@Override
	public Action chooseAction(LocalInformation information, Creature Food_location) {
		if (this == snake1.getFirst()) {
			// All of directions which are available for move
			ArrayList<Direction> directionforFood = new ArrayList<>();

			if (Food_location != null) {
				// if x minus y equal to 1 or -1, then it can attack
				if ((getX() == Food_location.getX()
						&& (getY() - Food_location.getY() == 1 || getY() - Food_location.getY() == -1))
						|| (getY() == Food_location.getY()
								&& (getX() - Food_location.getX() == 1 || getX() - Food_location.getX() == -1))) {
					//// ATACKKKKK
					if (information.getCreatureDown() instanceof Food) {
						return new Action(Type.ATTACK, Direction.DOWN);
					}
					if (information.getCreatureUp() instanceof Food) {
						return new Action(Type.ATTACK, Direction.UP);
					}
					if (information.getCreatureRight() instanceof Food) {
						return new Action(Type.ATTACK, Direction.RIGHT);
					}
					if (information.getCreatureLeft() instanceof Food) {
						return new Action(Type.ATTACK, Direction.LEFT);
					}

				}
				// find coordinate between Food and head of snake
				if (getX() < Food_location.getX()) {

					directionforFood.add(Direction.RIGHT);
				}
				if (getX() > Food_location.getX()) {

					directionforFood.add(Direction.LEFT);
				}
				if (getY() > Food_location.getY()) {

					directionforFood.add(Direction.UP);
				}
				if (getY() < Food_location.getY()) {

					directionforFood.add(Direction.DOWN);
				}
			}
			// direction is equal to one of freeDirection spaces
			Direction direction = LocalInformation.getRandomDirection(information.getFreeDirections());
			if (directionforFood.size() != 0) {
				//

				Direction FoodDirection = LocalInformation.getRandomDirection(directionforFood);
				////

				if (FoodDirection == Direction.RIGHT && information.getCreatureRight() == null) {
					return new Action(Type.MOVE, FoodDirection);
				} else if (FoodDirection == Direction.LEFT && information.getCreatureLeft() == null) {
					return new Action(Type.MOVE, FoodDirection);
				} else if (FoodDirection == Direction.UP && information.getCreatureUp() == null) {
					return new Action(Type.MOVE, FoodDirection);
				} else if (FoodDirection == Direction.DOWN && information.getCreatureDown() == null) {
					return new Action(Type.MOVE, FoodDirection);
				} else {
					// if head cannot move, then head go to free direction

					return new Action(Type.MOVE, direction);
				}
			}

			return new Action(Type.STAY);
		}

		return null;
	}

	/**
	 * it provides that all Snakes can be visualized on the UI
      @param panel the properties of Grid
	 */
	@Override
	public void draw(GridPanel panel) {
		panel.drawSquare(getX(), getY(), Color.RED);
		for (int i = 1; i < snake1.size(); i++) {
			panel.drawSquare(snake1.get(i).getX(), snake1.get(i).getY(), Color.BLACK);
        }}

	/**
	 * Snakes can stay at the same space
	 */
	@Override
	public void stay() {
          
	}

	/**
	 * Creating a new Snake of the same type on an adjacent empty space
	 * @param direction  representing the empty space where surroundings of a Snake
	 * @return a new Snake
	 */
	@Override
	public Creature reproduce(int x, int y) {

		int headx = snake1.getFirst().getX();
		int heady = snake1.getFirst().getY();

		
		snake1.add(1, new Snake(headx, heady));
		snake1.getFirst().setX(x);
		snake1.getFirst().setY(y);
		 head=snake1.getFirst();
        //if parts of snakes equal to eight, then it will divide
		if (snake1.size() == 8) {
              //save the coordinates of  last element and delete it 
			int lastsnakex = snake1.getLast().getX();
			int lastsnakey = snake1.getLast().getY();
			
			snake1.removeLast();
			x1 = snake1.getLast().getX();
			y1 = snake1.getLast().getY();
			snake1.removeLast();
			x2 = snake1.getLast().getX();
			y2 = snake1.getLast().getY();
			snake1.removeLast();
			x3 = snake1.getLast().getX();
			y3 = snake1.getLast().getY();
			snake1.removeLast();
			
		return new Snake(lastsnakex, lastsnakey);
		}
		return null;
	}

	/**
	 * Changing the position of the Snakes
	 * @param direction modifying its position to the direction which is an adjacent empty cell
	 */
	@Override
	public void move(Direction direction) {
		// get first element
		int headX = snake1.getFirst().getX();
		int headY = snake1.getFirst().getY();
		// change last element with first element
		snake1.getLast().setX(snake1.getFirst().getX());
		snake1.getLast().setY(snake1.getFirst().getY());
		// the last element equals to the first element
		snake1.set(0, snake1.getLast());

		//move the head
		if (Direction.RIGHT == direction) {

			head.setX(headX + 1);

		} else if (Direction.LEFT == direction) {

			head.setX(headX - 1);

		} else if (Direction.DOWN == direction) {

			head.setY(headY + 1);

		} else if (Direction.UP == direction) {

			head.setY(headY - 1);

		}

		snake1.addFirst(head);
		snake1.removeLast();

	}

	/**
	 * Snakes can attack another adjacent foods,in which case it will kill(remove)
	 * it and move to its position
	 * @param attackedCreature            
	 */
	@Override
	public void attack(Creature attackedCreature) {
         
		attackedCreature.setHealth(0.0);
	}

}
