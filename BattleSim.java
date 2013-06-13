import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  // Needed for ActionListener

class BattleSim extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static BattleMap colony = new BattleMap ();
    static Timer t;
    int strength = 0;
    int vitality = 0;
    int dexterity = 0;
    int intelligence = 0;
    int PointsLeft = 25;
    
    JLabel StrengthBtn = new JLabel ("Strength");
    JLabel StrengthValue = new JLabel ("" + strength);
    JButton StrengthPlus = new JButton ("STR+");
    JButton StrengthMinus = new JButton ("STR-");
    JLabel VitalityBtn = new JLabel ("Vitality");
    JLabel VitalityValue = new JLabel ("" + vitality);
    JButton VitalityPlus = new JButton ("VIT+");
    JButton VitalityMinus = new JButton ("VIT-");
    JLabel DexterityBtn = new JLabel ("Dexterity");
    JLabel DexterityValue = new JLabel ("" + dexterity);
    JButton DexterityPlus = new JButton ("DEX+");
    JButton DexterityMinus = new JButton ("DEX-");
    JLabel IntelligenceBtn = new JLabel ("Intelligence");
    JLabel IntelligenceValue = new JLabel ("" + intelligence);
    JButton IntelligencePlus = new JButton ("INT+");
    JButton IntelligenceMinus = new JButton ("INT-");
    JLabel PointsAllocate = new JLabel ("Stat points left: " + PointsLeft);
    
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
	this.addKeyListener(new arrowListener());
	east.requestFocus();

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
	
	east.add(PointsAllocate);
	
	StrengthPlus.setFocusable(false);
	StrengthMinus.setFocusable(false);
	
	VitalityPlus.setFocusable(false);
	VitalityMinus.setFocusable(false);
	
	DexterityPlus.setFocusable(false);
	DexterityMinus.setFocusable(false);
	
	IntelligencePlus.setFocusable(false);
	IntelligenceMinus.setFocusable(false);
	
	content.add (east, "North"); // Input area
	content.add (board, "South"); // Output area

	// 4... Set this window's attributes.
	setContentPane (content);
	pack ();
	setTitle ("Life Simulation");
	setSize (850, 600);
	setFocusable(true);
	setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo (null);           // Center window.
	enableCheck();
    }
    
	public void updateLabel ()
	{
		StrengthValue.setText("" + strength);
		VitalityValue.setText("" + vitality);
		DexterityValue.setText("" + dexterity);
		IntelligenceValue.setText("" + intelligence);
		PointsAllocate.setText("Stat points left: " + PointsLeft);
	}
	
	public void enableCheck ()
	{
		if (PointsLeft == 0)
		{
			StrengthPlus.setEnabled(false);
			VitalityPlus.setEnabled(false);
			DexterityPlus.setEnabled(false);
			IntelligencePlus.setEnabled(false);
		}
		else if (PointsLeft > 0)
		{
			StrengthPlus.setEnabled(true);
			VitalityPlus.setEnabled(true);
			DexterityPlus.setEnabled(true);
			IntelligencePlus.setEnabled(true);
		}
		
		if (strength == 0)
		{
			StrengthMinus.setEnabled(false);
		}
		else if (strength > 0)
		{
			StrengthMinus.setEnabled(true);
		}
		if (vitality == 0)
		{
			VitalityMinus.setEnabled(false);
		}
		else if (vitality > 0)
		{
			VitalityMinus.setEnabled(true);
		}
		if (dexterity == 0)
		{
			DexterityMinus.setEnabled(false);
		}
		else if (dexterity > 0)
		{
			DexterityMinus.setEnabled(true);
		}
		if (intelligence == 0)
		{
			IntelligenceMinus.setEnabled(false);
		}
		else if (intelligence > 0)
		{
			IntelligenceMinus.setEnabled(true);
		}
	}

    class BtnListener implements ActionListener // Button menu
    {
    	
	public void actionPerformed (ActionEvent e)
	{
		
	    if (e.getActionCommand ().equals ("STR+")) { strength++; PointsLeft--; }

	    else if (e.getActionCommand ().equals ("STR-")) { strength--; PointsLeft++; }
	    
	    else if (e.getActionCommand ().equals ("VIT+")) { vitality++; PointsLeft--; }
	    
	    else if (e.getActionCommand ().equals ("VIT-")) { vitality--; PointsLeft++; }
	    
	    else if (e.getActionCommand ().equals ("DEX+")) { dexterity++; PointsLeft--; }

	    else if (e.getActionCommand ().equals ("DEX-")) { dexterity--; PointsLeft++; }

	    else if (e.getActionCommand ().equals ("INT+")) { intelligence++; PointsLeft--; }

	    else if (e.getActionCommand ().equals ("INT-")) { intelligence--; PointsLeft++; }
	    
	    enableCheck();
	    updateLabel();
	    repaint ();            // refresh display of colony
	    }
	}
    
    class arrowListener implements KeyListener
    {
    public void keyPressed (KeyEvent e)
    {
    	int w = colony.getCharXPos();
    	int t = colony.getCharYPos();
    	int key = e.getKeyCode();
    	
    	if (key == KeyEvent.VK_LEFT)
    	{
    		colony.setBlankPos(w, t);
    		colony.setCharPos(w-1, t);
    		System.out.println("hi");
	
    	}
    	if (key == KeyEvent.VK_RIGHT)
    	{
    		colony.setBlankPos(w, t);
    		colony.setCharPos(w+1, t);
    		System.out.println("hi");
	
    	}
    	if (key == KeyEvent.VK_UP)
    	{
    		colony.setBlankPos(w, t);
    		colony.setCharPos(w, t-1);
    		System.out.println("hi");
	
    	}
    	if (key == KeyEvent.VK_DOWN)
    	{
    		colony.setBlankPos(w, t);
    		colony.setCharPos(w, t+1);
    		System.out.println("hi");
	
    	}
    	repaint();
	}

	public void keyReleased (KeyEvent e) {}
    public void keyTyped (KeyEvent e) {}
    
    }
    
    class DrawArea extends JPanel
    {
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;


	public DrawArea (int width, int height)
	{
	    this.setPreferredSize (new Dimension (width, height)); // size
	}


	public void paintComponent (Graphics g)
	{
	    colony.show (g);
	}
    }

    //======================================================== method main
    public static void main (String[] args)
    {
	BattleSim window = new BattleSim ();
	window.setVisible (true);

    }
}


//=========================================================GUI Class

