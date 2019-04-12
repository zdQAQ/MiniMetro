package utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenShots{
	public static void make(String name) {
		Robot robot;
		try {
			int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();  //要截取的宽度
            int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();  //要截取的高度
			robot = new Robot();
            BufferedImage image=robot.createScreenCapture(new Rectangle(width,height));
            System.out.println(System.getProperty("os.name"));
			switch (System.getProperty("os.name")) {
            case "Mac OS X": 
            	new File("/Users/zd/Desktop/metroScreenShots").mkdir();
            	ImageIO.write(image, "png", new File("/Users/zd/Desktop/metroScreenShots/"+name+".png"));
                     break;
            case "Windows":  
            	new File("C:\\metroScreenShots").mkdir();
            	ImageIO.write(image, "png", new File("C:\\metroScreenShots\\"+name+".png"));
                     break;
            default:
            	new File("C:\\metroScreenShots").mkdir();
            	ImageIO.write(image, "png", new File("C:\\metroScreenShots\\"+name+".png"));
}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}