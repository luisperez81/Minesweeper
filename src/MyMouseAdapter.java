import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyMouseAdapter extends MouseAdapter {

	private boolean flag = false;           //boolean used to identify if there is a flag in a determined coordinate
	Minesweeper game = new Minesweeper();   //instance of the Minesweeper class
	private int[][] mines=game.getMines();  //creates an istance of the mines
	private boolean boom = false;

	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		

			Component c1 = e.getComponent();
			while (!(c1 instanceof JFrame)) {
				c1 = c1.getParent();
				if (c1 == null) {
					return;
				}
			}
			JFrame myFrame1 = (JFrame) c1;
			MyPanel myPanel1 = (MyPanel) myFrame1.getContentPane().getComponent(0);
			Insets myInsets1 = myFrame1.getInsets();
			int x2 = myInsets1.left;
			int y2 = myInsets1.top;
			e.translatePoint(-x2, -y2);
			int x3 = e.getX();
			int y3 = e.getY();
			myPanel1.x = x3;
			myPanel1.y = y3;
			myPanel1.mouseDownGridX = myPanel1.getGridX(x3, y3);
			myPanel1.mouseDownGridY = myPanel1.getGridY(x3, y3);
			myPanel1.repaint();
			break;

			//Do nothing
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						
						Color newColor = null;
						if (game.isMine(gridX, gridY)) 
						{
							newColor = Color.BLACK;
							boom = true;
							JOptionPane.showMessageDialog(null,"I'm sorry, you lose. :(");
						}
						else	
						{				
							//Cases that represent numbers in the grid
							switch (this.game.countMines(gridX, gridY)){
							case 0:
								newColor = Color.LIGHT_GRAY;
								game.chainReaction(gridX, gridY, myPanel);
								break;
							case 1:
								newColor = Color.CYAN;
								break;
							case 2:
								newColor = Color.BLUE;
								break;
							case 3:
								newColor = Color.GREEN;
								break;
							case 4:
								newColor = Color.MAGENTA;
								break;
							case 5:
								newColor = Color.PINK;
								break;
							case 6:
								newColor = Color.ORANGE;
								break;
							case 7:
								newColor = Color.YELLOW.darker();
								break;
							case 8:
								newColor = new Color(0xB57EDC).darker();//Lavender
								break;								
							}							
							System.out.println(this.game.countMines(gridX, gridY));
						}
						myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
						myPanel.repaint();
					}
				}
			}
			myPanel.repaint();
			break;

		case 3:		//Right mouse button				
			Component c1 = e.getComponent();
			while (!(c1 instanceof JFrame))
			{
				c1 = c1.getParent();
				if (c1 == null) 
				{
					return;
				}
			}				
			JFrame myFrame1 = (JFrame) c1;
			MyPanel myPanel1 = (MyPanel) myFrame1.getContentPane().getComponent(0);
			Insets myInsets1 = myFrame1.getInsets();
			int x11 = myInsets1.left;
			int y11 = myInsets1.top;
			e.translatePoint(-x11, -y11);
			int x111 = e.getX();
			int y111 = e.getY();
			myPanel1.x = x111;
			myPanel1.y = y111;
			int gridX1 = myPanel1.getGridX(x111, y111);
			int gridY1 = myPanel1.getGridY(x111, y111);
			if ((myPanel1.mouseDownGridX == -1) || (myPanel1.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} 
			else 
			{
				if ((gridX1 == -1) || (gridY1 == -1)) {
					//Is releasing outside
					//Do nothing
				} 
				else 
				{
					if ((myPanel1.mouseDownGridX != gridX1) || (myPanel1.mouseDownGridY != gridY1)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} 
					else 
					{
						//Released the mouse button on the same cell where it was pressed
						if ((gridX1 == 0) || (gridY1 == 0)) {
							//On the left column and on the top row... do nothing
							Color newColor = null;

							if (!flag){
								newColor = Color.RED;
								flag = true;
							}

							else if(flag) {
								newColor = Color.WHITE;
								flag = false;
							}							
							myPanel1.colorArray[myPanel1.mouseDownGridX][myPanel1.mouseDownGridY] = newColor;
							myPanel1.repaint();
						} 
						else {
							//On the grid other than on the left column and on the top row:
							Color newColor = null;

							if (!flag){
								newColor = Color.RED;
								flag = true;
							}

							else if(flag) {
								newColor = Color.WHITE;
								flag = false;
							}							
							myPanel1.colorArray[myPanel1.mouseDownGridX][myPanel1.mouseDownGridY] = newColor;
							myPanel1.repaint();
						}
					}
				}
			}				
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}    
	}
}