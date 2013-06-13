
import java.awt.Color;
import java.awt.Graphics;

class BattleMap 
{
    private int grid[] [];
    int x, y;
    int level = 1;

    public BattleMap ()
    {
	grid = new int [100] [100];
		int x = chooseRandom();
		int y = chooseRandom();
	grid [x] [y] = 2; //2 will be the array location of the character figure

	for (int q = 0; q <= level + 1; q++)
	{
		x = chooseRandom();
		y = chooseRandom();
	while (grid [x][y] == 2)
	{
		x = chooseRandom();
		y = chooseRandom();
	}
	grid [x] [y] = 1; //1 is the array location for an enemy

	}
	
	for (int row = 0 ; row < grid.length ; row++)
	{
		for (int col = 0 ; col < grid [0].length ; col++)
	    {
	    	if (grid [row] [col] != 1 && grid [row] [col] != 2)
	    		grid [row] [col] = 0; //0 is just blank space
	    }
	}
    }


    public void show (Graphics g)
    {
	for (int row = 0 ; row < grid.length ; row++)
	    for (int col = 0 ; col < grid [0].length ; col++)
	    {
		if (grid [row] [col] == 2)
		{// life
		    g.setColor (Color.red);
		}
		else if (grid [row] [col] == 1)
		    g.setColor (Color.black);
		else 
			g.setColor (Color.white);
		g.fillRect (col * 5 + 175, row * 5 + 2, 5, 5); // draw life form
	    }
    }
    
    public int chooseRandom()
    {
    	return (int) (Math.random() * 100);
    }


    public void advance ()
    {
    }
    
    
/*
    public void keyPressed (KeyEvent e)
    {
    	for (int row = 0 ; row < grid.length ; row++)
    	{
    	    for (int col = 0 ; col < grid [0].length ; col++)
    	    {
    	    	if (grid [row] [col] == 2)
    	    	{
    	    		x = row;
    	    		y = col;
    	    	}
    	    }
    	}

    	int key = e.getKeyCode();
    	
    	if (key == KeyEvent.VK_LEFT)
    	{
    		grid [x] [y-1] = grid [x] [y];
    		grid [x] [y] = 0;
    		
    	}
    	repaint();
	}

	public void keyReleased (KeyEvent e) {}
    public void keyTyped (KeyEvent e) {}
*/
 
    
  
    public int getCharXPos ()
 
    {
    	int xPos = 0;
    	for (int row = 0 ; row < grid.length ; row++)
    	{
    		for (int col = 0 ; col < grid [0].length ; col++)
    	    {
    	    	if (grid [row] [col] == 2)
    	    		xPos = col;
    	    }
    	}
    	return xPos;
    }
    public int getCharYPos ()
    {
    	int yPos = 0;
    	for (int row = 0 ; row < grid.length ; row++)
    	{
    		for (int col = 0 ; col < grid [0].length ; col++)
    	    {
    	    	if (grid [row] [col] == 2)
    	    		yPos = row;
    	    }
    	}
    	return yPos;
    }
    
    public void setCharPos (int q, int r)
    {
    	grid [r] [q] = 2;
    	System.out.println(r + " " + q);
    }
    public void setBlankPos (int q, int r)
    {
    	grid [r] [q] = 0;
    	System.out.println(r + " " + q);
    }

}
