package learn;

import java.io.File;

import org.junit.Test;

public class FileTest {
	@Test
	public void test() {
		File file = new File("C:/");
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			
			for (File fileItem : files) {
				this.printFileInfo(fileItem);
			}
		} else {
			this.printFileInfo(file);
		}
		
	}
	
	private void printFileInfo(File file) {
		System.out.println("name : " + file.getName());
		System.out.println("absolutePath : " + file.getAbsolutePath());
	}
}
