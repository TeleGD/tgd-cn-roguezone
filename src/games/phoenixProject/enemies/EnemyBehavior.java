package games.phoenixProject.enemies;

import games.phoenixProject.Player;
import games.phoenixProject.map.Room;

public class EnemyBehavior {
	public static enum interacting {FLEEING, COMING, STATIC, STRAIGHT, BOUNCE};
	/* FLEEING : fuit le joueur
	 * COMING : va vers le joueur
	 * STATIC : ne bouge pas
	 * STRAIGHT : ligne droite
	 *
	 */

	static enum wallCollision {MIRROR};
	//TODO : mettre une reflexion du type : aléatoire (je rebondis n'importe quelle direction
	//TODO : mettre une reflexion du type : je rebondis en direction du joueur

	private Enemy enemy;
	private Player player;

	/*
	 * direction de l'enemis UNIQUEMENT POUR l'interaction STRAIGHT (pour le moment)
	 * il faut qu'il soit de norme 1
	 */
	private double[] normalizedVector = {1/Math.sqrt(2),1/Math.sqrt(2)};

	private interacting interaction = interacting.STATIC;

	private float playerX;
	private float enemyX;

	private float enemyY;
	private float playerY;

	private float deltaX;
	private float deltaY;



	private double norm;

	private float directionX;
	private float directionY;


	/**
	 * constructeur du comportement de l'ennemis
	 * @param e
	 */
	public EnemyBehavior(Enemy e, Player p)
	{
		enemy = e;
		player = p;
		playerX = player.getPos()[0];
		enemyX = enemy.getPos()[0];
		enemyY = enemy.getPos()[1];
		playerY = player.getPos()[1];
		deltaX = enemyX - playerX;
		deltaY = enemyY - playerY;
		norm  = Math.sqrt(Math.pow((double) deltaX, (double) 2) + Math.pow((double) deltaY, (double) 2));
		directionX = (float) (((double) deltaX )/ norm) * enemy.getSpeed();
		directionY = (float) ((((double) deltaY )/ norm) * enemy.getSpeed());
	}

	/**
	 *
	 * @return vecteur de position au format [x,y]
	 */
	public interacting getPlayerInfluence()
	{
		return interaction;
	}

	/**
	 * permet de dire comment l'ennemis réagit face au joueur
	 * @param i
	 */

	public void setPlayerInfluence(interacting i)
	{
		interaction = i;
	}

	/**
	 * permet de renvoyer le vecteur de mouvement dû à l'influence du joueur
	 * @return
	 */
	public float[] influencedMove()
	{
		float[] vect = {0,0};
		float playerX = player.getPos()[0];
		float enemyX = enemy.getPos()[0];

		float enemyY = enemy.getPos()[1];
		float playerY = player.getPos()[1];

		float deltaX = enemyX - playerX;
		float deltaY = enemyY - playerY;



		double norm  = Math.sqrt(Math.pow((double) deltaX, (double) 2) + Math.pow((double) deltaY, (double) 2));

		switch(interaction)
		{
		case FLEEING:
			if (norm!=0)
			{
				vect[0]= (float) ((((double) deltaX )/ norm) * enemy.getSpeed());
				vect[1]= (float) ((((double) deltaY )/ norm) * enemy.getSpeed());
			}
			break;

		case COMING:
			if (norm!=0)
			{
				vect[0]= -(float) ((((double) deltaX )/ norm) * enemy.getSpeed());
				vect[1]= -(float) ((((double) deltaY )/ norm) * enemy.getSpeed());
			}
			break;
		case BOUNCE:
			if (enemy.getPos()[0]-enemy.getRadius()< Room.xMargin
					|| enemy.getPos()[0]+enemy.getRadius()>Room.worldWidth-Room.xMargin
					|| enemy.getPos()[1]-enemy.getRadius()<Room.yMargin
					|| enemy.getPos()[1]+enemy.getRadius()>Room.worldHeight-Room.yMargin ) {
				/*	l'ennemi touche un mur */
				directionX = (float) (((double) deltaX )/ norm) * enemy.getSpeed();
				directionY  = (float) ((((double) deltaY )/ norm) * enemy.getSpeed());
			}
			vect[0]= -directionX;
			vect[1]= -directionY;
			break;
		case STRAIGHT:
			vect[0] = (float) (normalizedVector[0] * enemy.getSpeed());
			vect[1] = -(float) (normalizedVector[1] * enemy.getSpeed());
			break;
		case STATIC:

			break;
		}
		return vect;
	}

	/**
	 * permet de dire comment l'ennemis réagit face à un mur
	 * @return
	 */
	public int[] reflect()
	{
		int[] pos = {};
		//do some stuff
		return pos;
	}

	public void setVector(float[] vect)
	{
		normalizedVector[0] = vect[0];
		normalizedVector[1] = vect[1];
	}
}
