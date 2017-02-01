import java.awt.FileDialog;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class FileMenu {

	public void openFile(JFrame frame) {
		
		FileDialog open = new FileDialog(frame, "Open Dialog", FileDialog.LOAD);
		open.setVisible(true);
		
		try{
			FileReader reader = new FileReader(open.getDirectory() + open.getFile());
			String content = "";
			int b = 0;
			while(true){
				b = reader.read();
				if(-1 == b) break;
				content += (char)(b);
			}
			
			Database.query.setText(content);
			reader.close();
			
		} catch(NullPointerException npe){
			npe.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void saveFile(JFrame frame) {
		
		FileDialog save = new FileDialog(frame, "Save Dialog", FileDialog.SAVE);
		save.setVisible(true);
		
		try {
			FileWriter write = new FileWriter(save.getDirectory() + save.getFile());
			write.write(Database.query.getText());
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
