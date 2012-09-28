import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel {

	Image star;
	Timer timer;
	int x, y;
	ImageIcon ii;
	boolean next = true;
	JFileChooser chooser;
	String choosertitle, fileDirectory, fileName;

	public Board(int width, int height) {
		setBackground(Color.BLACK);

		ii = new ImageIcon(this.getClass().getResource("noImage.jpg"));
		star = ii.getImage();

		setDoubleBuffered(true);

		x = width;
		y = height;
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(star, x/2 - star.getWidth(null)/2, y/2 - star.getHeight(null)/2, null);
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
}