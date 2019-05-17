package games.phoenix;

public class EnemyBehavior {
	static enum interacting {FLEEING, COMING, STATIC, STRAIGHT};
	/* FLEEING : fuit le joueur
	 * COMING : va vers le joueur
	 * STATIC : ne bouge pas
	 * STRAIGHT : se dépasse ligne droite
	 * 
	 */
	
	static enum wallCollision {MIRROR};
	
	private Enemy enemy;
	private Player player;
	
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
	public int[] influencedMove()
	{
		int[] vect = {0,0};
		switch(interaction)
		{
		case FLEEING:
			
			break;
		
		case COMING:
			
			break;
			
		case STATIC:
			vect[0] = 0;
			vect[1] = 0;
			

		case STRAIGHT:
			
			
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
}
