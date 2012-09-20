import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
	
	JFileChooser chooser;
	String choosertitle, fileDirectory,fileName;
	BufferedImage img;
	AnImagePanel panel;

    public Main() {
    	img = null;
    	initMenus();
    	initGUI();
    	
      
    }
    
    public final void initGUI(){
    	 add(getContent());
    	 setTitle("Simple example");
         setSize(800, 600);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
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
				openFile();
			}
    	});
    	
    	fileMenu.add(openImaItem);
    	fileMenu.add(exitMenuItem);
    	
    	menubar.add(fileMenu);
    	setJMenuBar(menubar);
    }
    
    public final void openFile(){
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
    	      panel.loadImage2();
    	      
    	      }
    	    else {
    	      System.out.println("No Selection ");
    	      }
    }
    
    private JScrollPane getContent() {
        panel = new AnImagePanel();
        JScrollPane scrollPane = new JScrollPane(panel);
        return scrollPane;
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}