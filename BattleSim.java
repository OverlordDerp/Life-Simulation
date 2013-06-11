import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  // Needed for ActionListener
import javax.swing.event.*;  // Needed for ActionListener

class BattleSim extends JFrame
{
    static BattleMap colony = new BattleMap (1);
    static Timer t;
    int strength = 0;
    int vitality = 0;
    int dexterity = 0;
    int intelligence = 0;
    
    JLabel StrengthBtn = new JLabel ("Strength");
    JLabel StrengthValue = new JLabel ("" + strength);
    JButton StrengthPlus = new JButton ("+");
    JButton StrengthMinus = new JButton ("-");
    JLabel VitalityBtn = new JLabel ("Vitality");
    JLabel VitalityValue = new JLabel ("" + vitality);
    JButton VitalityPlus = new JButton ("+");
    JButton VitalityMinus = new JButton ("-");
    JLabel DexterityBtn = new JLabel ("Dexterity");
    JLabel DexterityValue = new JLabel ("" + dexterity);
    JButton DexterityPlus = new JButton ("+");
    JButton DexterityMinus = new JButton ("-");
    JLabel IntelligenceBtn = new JLabel ("Intelligence");
    JLabel IntelligenceValue = new JLabel ("" + intelligence);
    JButton IntelligencePlus = new JButton ("+");
    JButton IntelligenceMinus = new JButton ("-");
    
    //======================================================== constructor
    public BattleSim ()
    {
	// 1... Create/initialize components
	
	BtnListener btnListener = new BtnListener (); // listener for all buttons

	
	StrengthPlus.addActionListener (btnListener);
	StrengthMinus.addActionListener (btnListener);
	
	VitalityPlus.addActionListener (btnListener);
	VitalityMinus.addActionListener (btnListener);
	
	DexterityPlus.addActionListener (btnListener);
	DexterityMinus.addActionListener (btnListener);
	
	IntelligencePlus.addActionListener (btnListener);
	IntelligenceMinus.addActionListener (btnListener);
	
	// 2... Create content pane, set layout
	JPanel content = new JPanel ();        // Create a content pane
	content.setLayout (new BorderLayout ()); // Use BorderLayout for panel
	JPanel east = new JPanel ();
	east.setLayout (new FlowLayout ()); // Use FlowLayout for input area

	DrawArea board = new DrawArea (500, 500);

	// 3... Add the components to the input area.

	east.add (StrengthBtn);
	east.add(StrengthValue);
	east.add(StrengthPlus);
	east.add (StrengthMinus);
	
	east.add (VitalityBtn);
	east.add(VitalityValue);
	east.add(VitalityPlus);
	east.add (VitalityMinus);

	east.add (DexterityBtn);
	east.add(DexterityValue);
	east.add(DexterityPlus);
	east.add (DexterityMinus);
	
	east.add (IntelligenceBtn);
	east.add(IntelligenceValue);
	east.add(IntelligencePlus);
	east.add (IntelligenceMinus);
	
	content.add (east, "East"); // Input area
	content.add (board, "South"); // Output area

	// 4... Set this window's attributes.
	setContentPane (content);
	pack ();
	setTitle ("Life Simulation");
	setSize (650, 600);
	setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo (null);           // Center window.
    }
    
	public void updateLabel ()
	{
		StrengthValue.setText("" + strength);
		VitalityValue.setText("" + vitality);
		DexterityValue.setText("" + dexterity);
		IntelligenceValue.setText("" + intelligence);
	}

    class BtnListener implements ActionListener // Button menu
    {
	public void actionPerformed (ActionEvent e)
	{
	    if (e.getActionCommand ().equals ("StrengthPlus"))
	    {
	    	strength++;
	    	System.out.println(strength);	
	    }
	    
	    else if (e.getActionCommand ().equals ("StrengthMinus"))
	    {
	    	strength--;
	    }
	    
	    else if (e.getActionCommand ().equals ("VitalityPlus"))
	    {
	    	vitality++;
	    }
	    
	    else if (e.getActionCommand ().equals ("VitalityMinus"))
	    {
	    	vitality--;
	    }
	    
	    else if (e.getActionCommand ().equals ("DexterityPlus"))
	    {
	    	dexterity++;
	    }
	    
	    else if (e.getActionCommand ().equals ("DexterityMinus"))
	    {
	    	dexterity--;
	    }
	    
	    else if (e.getActionCommand ().equals ("IntelligencePlus"))
	    {
	    	intelligence++;
	    }
	    
	    else if (e.getActionCommand ().equals ("IntelligenceMinus"))
	    {
	    	intelligence--;
	    	updateLabel();
	    }
	    updateLabel();
	    repaint ();            // refresh display of colony
	    }
	}

    class DrawArea extends JPanel
    {
	public DrawArea (int width, int height)
	{
	    this.setPreferredSize (new Dimension (width, height)); // size
	}


	public void paintComponent (Graphics g)
	{
	    colony.show (g);
	}
    }


    class Movement implements ActionListener
    {
	private BattleMap colony;

	public Movement (BattleMap col)
	{
	    colony = col;
	}


	public void actionPerformed (ActionEvent event)
	{
	    colony.advance ();
	    repaint ();
	}
    }


    //======================================================== method main
    public static void main (String[] args)
    {
	BattleSim window = new BattleSim ();
	window.setVisible (true);
    }
}

class BattleMap
{
    private int grid[] [];

    public BattleMap (int level)
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
	grid [x] [y] = 1;//1 is the array location for an enemy

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
		if (grid [row] [col] == 2) // life
		    g.setColor (Color.red);
		else if (grid [row] [col] == 1)
		    g.setColor (Color.black);
		else 
			g.setColor (Color.white);
		g.fillRect (col * 5 + 20, row * 5 + 2, 5, 5); // draw life form
	    }
    }
    
    public int chooseRandom()
    {
    	return (int) (Math.random() * 100);
    }


    public void advance ()
    {
    }
}

//=========================================================GUI Class

