import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class TxtConvertor {
	
	static File file;
	
	private final static void createSave() throws IOException{
		System.out.println("Trying");
		file = new File("Save");
		if(!file.exists()){
			System.out.println("Creating");
			file.createNewFile();
		}
		else{
			// will need to change this
			System.out.println("Save already exists");
		}
		
	}
	
	public final static void writeLine(String imgName, int nrRectangles, Rectangle[] rectangles){
		try {
			createSave();
		    BufferedWriter out = new BufferedWriter(new FileWriter("Save.txt"));
		    out.append(imgName + "   " + nrRectangles + "   ");
		    for(int i = 0; i < nrRectangles; i++){
		    	out.write(rectangles[i].width + " " + rectangles[i].height  + " " +  rectangles[i].x  + " " +  rectangles[i].y);
		    	
		    }
		    out.newLine();
		    out.close();
		} catch (IOException e) {
		}
	}
	
	

}
