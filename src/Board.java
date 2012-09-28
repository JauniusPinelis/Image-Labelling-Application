import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements MouseMotionListener {

	Image star;
	Timer timer;
	int x, y;
	ImageIcon ii;
	boolean next = true;
	JFileChooser chooser;
	String choosertitle, fileDirectory, fileName;

	private static final int SquareWidth = 10;

	  private static final int Max = 100;

	  private Rectangle[] squares = new Rectangle[Max];

	  private int squareCount = 0;

	  private int currentSquareIndex = -1;
	  private boolean finished = false;

	public Board(int width, int height) {
		setBackground(Color.WHITE);

		ii = new ImageIcon(this.getClass().getResource("noImage.jpg"));
		star = ii.getImage();


		x = width;
		y = height;
		// ----------------------------------------------

		addMouseListener(new MouseAdapter() {
		      public void mousePressed(MouseEvent evt) {
		        int x = evt.getX();
		        int y = evt.getY();
		        currentSquareIndex = getSquare(x, y);
		        if (currentSquareIndex < 0) // not inside a square
		          add(x, y);
		        else if(squares[0].contains(x,y))
		        {
		        	finished = true;
		        	repaint();
		        }
		        
		      }

		      public void mouseClicked(MouseEvent evt) {
		        int x = evt.getX();
		        int y = evt.getY();
		        currentSquareIndex = getSquare(x, y);
		        
		        	

		        if (evt.getClickCount() >= 2) {
		        	
		        		remove(currentSquareIndex);
		        	
		        }
		      }
		    });
		    addMouseMotionListener(this);

		// --------------------------------------------

	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(star, x / 2 - star.getWidth(null) / 2, y / 2
				- star.getHeight(null) / 2, null);
		if (finished  == true){
			g2d.setColor(Color.RED);
			g2d.drawLine(squares[0].x, squares[0].y, squares[squareCount-1].x, squares[squareCount-1].y);
			}
		
		
		for (int i = 0; i < squareCount; i++)
		      ((Graphics2D)g).draw(squares[i]);
		
		for (int i = 1; i < squareCount; i++){
			g2d.drawLine(squares[i].x, squares[i].y, squares[i-1].x, squares[i-1].y);
		}
		
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	

	public void openImage() {
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(choosertitle);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		chooser.setAcceptAllFileFilterUsed(false);
		//   
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			fileDirectory = chooser.getCurrentDirectory().getAbsolutePath();
			fileName = chooser.getSelectedFile().getAbsolutePath();
			System.out.println(fileDirectory);
			System.out.println(fileName);

			try {
				star = ImageIO.read(new File(fileName));
			} catch (IOException e) {
			}
			repaint();

		} else {
			System.out.println("No Selection ");
		}
	}

	public int getSquare(int x, int y) {
	    for (int i = 0; i < squareCount; i++)
	      if(squares[i].contains(x,y))
	        return i;
	    return -1;
	  }

	  public void add(int x, int y) {
	    if (squareCount < Max) {
	      squares[squareCount] = new Rectangle(x, y,SquareWidth,SquareWidth);
	      currentSquareIndex = squareCount;
	      squareCount++;
	      repaint();
	    }
	  }
	  //---------------------
	  // go back fucntion
	  public void remove(int n) {
	    if (n < 0 || n >= squareCount)
	      return;
	    
	    
	    squareCount--;
	    //squares[n] = squares[squareCount];
	    if (currentSquareIndex == n)
	      currentSquareIndex = -1;
	    repaint();
	  }

	  public void mouseMoved(MouseEvent evt) {
	    int x = evt.getX();
	    int y = evt.getY();

	    if (getSquare(x, y) >= 0)
	      setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	    else
	      setCursor(Cursor.getDefaultCursor());
	  }

	  public void mouseDragged(MouseEvent evt) {
	    int x = evt.getX();
	    int y = evt.getY();

	    if (currentSquareIndex >= 0) {
	      Graphics g = getGraphics();
	      g.setXORMode(getBackground());
	      ((Graphics2D)g).draw(squares[currentSquareIndex]);
	      squares[currentSquareIndex].x = x;
	      squares[currentSquareIndex].y = y;
	      ((Graphics2D)g).draw(squares[currentSquareIndex]);
	      g.dispose();
	      repaint();
	    }
	  }
	  
	  public String getFileName(){
		  return fileName;
	  }
	  
	  public int getSquareCount(){
		  return squareCount;
	  }
	  
	  public Rectangle[] getSquares(){
		  return squares;
	  }
	
}