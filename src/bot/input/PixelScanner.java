package bot.input;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bot.obj.Window;
import bot.output.Clicker;
import me.aelesia.commons.utils.ThreadUtils;

public class PixelScanner {
	Robot robot;
	Random rand = new Random(); 
	
	public PixelScanner(Robot robot) {
		this.robot = robot;
	}
	
	public List<Color> scanColors(Window window, int xSegments, int ySegments) {
		return this.scanColors(window, xSegments, ySegments, 1, false);
	}
	
	public List<Color> scanColors(Window window, int xSegments, int ySegments, int numSamples, boolean sampleRandom) {
		
		float dx = window.dx() / (float)xSegments;
		float dy = window.dy() / (float)ySegments;
		Clicker clicker = new Clicker();
		List<Color> colorList = new ArrayList<Color>();
		
		BufferedImage screenshot = robot.createScreenCapture(window.toRectangle());
		for (float x=dx/2; x<screenshot.getWidth(); x+=dx) {
			for (float y=dy/2; y<screenshot.getHeight(); y+=dy) {
				for (int i=0; i<numSamples; i++) {
					int varianceX = 0; 
					int varianceY = 0;
					if (sampleRandom) {
						varianceX = rand.nextInt((int)dx) - (int)dx/2;
						varianceY = rand.nextInt((int)dy) - (int)dy/2;
					}
					colorList.add(new Color(screenshot.getRGB((int)x+varianceX, (int)y+varianceY)));
//					clicker.click((int)x+window.x1+varianceX, (int)y+window.y1+varianceY);
				}
			}
		}
		return colorList;
	}
}
