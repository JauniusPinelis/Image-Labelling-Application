

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends JFrame {
	
	Board board;
	int width, height;
	TxtConvertor txtConvertor; // im bad with names


    public Main() {
    	setVariables();
    	initMenus();
    	
    	board = new Board(width, height);
    	
        add(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setTitle("Star");
        setResizable(false);
        setVisible(true);

    }
    
    private final void setVariables(){
    	width = 800; // size of the main window
    	height = 640;
    	txtConvertor = new TxtConvertor();
    }
    
    public final void initMenus(){
    	JMenuBar menubar = new JMenuBar();
    	JMenu fileMenu = new JMenu("File");
    	fileMenu.setMnemonic(KeyEvent.VK_F);
    	
    	JMenuItem exitMenuItem = new JMenuItem("Exit");
    	exitMenuItem.setMnemonic(KeyEvent.VK_C);
    	exitMenuItem.setToolTipText("Exit application");
    	exitMenuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
    		
    	});
    	
    	JMenuItem openImaItem= new JMenuItem("Open Image");
    	openImaItem.setMnemonic(KeyEvent.VK_O);
    	openImaItem.setToolTipText("Open an image");
    	openImaItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				board.openImage();
			}
    	});
    	
    	JMenuItem saveTxtItem= new JMenuItem("Save as .txt file");
    	saveTxtItem.setMnemonic(KeyEvent.VK_S);
    	saveTxtItem.setToolTipText("Save as .txt file");
    	saveTxtItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TxtConvertor.writeLine(board.getFileName(), board.getSquareCount(), board.getSquares());
			}
    	});
    	
    	fileMenu.add(openImaItem);
    	fileMenu.add(saveTxtItem);
    	fileMenu.add(exitMenuItem);
    	
    	menubar.add(fileMenu);
    	setJMenuBar(menubar);
    }

    public static void main(String[] args) {
        new Main();
    }
}
