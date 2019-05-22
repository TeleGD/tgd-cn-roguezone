package games.phoenix.enemies;

import games.phoenix.Player;

public class EnemyBehavior {
	public static enum interacting {FLEEING, COMING, STATIC, STRAIGHT};
	/* FLEEING : fuit le joueur
	 * COMING : va vers le joueur
	 * STATIC : ne bouge pas
	 * STRAIGHT : se dépasse ligne droite
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
	
	
	/**
	 * constructeur du comportement de l'ennemis
	 * @param e
	 */
	public EnemyBehavior(Enemy e, Player p)
	{
		enemy = e;
		player = p;
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
		float enemyX = enemy.getPos()[0]+ enemy.getSprite().getWidth()/2;
		
		float enemyY = enemy.getPos()[1]+ enemy.getSprite().getHeight()/2;
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
			
		case STATIC:

			break;
		case STRAIGHT:
			vect[0] = (float) (normalizedVector[0] * enemy.getSpeed());
			vect[1] = -(float) (normalizedVector[1] * enemy.getSpeed());
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
