package bot.output;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.im.InputContext;
import java.util.Random;

import bot.obj.Point;
import bot.obj.Window;
import me.aelesia.commons.utils.ThreadUtils;

public class Clicker {

	Robot robot;
	Random rand = new Random(); 
	
	public Clicker() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void clickOn(Window window) {
		int x1 = (int)(window.x1 + window.dx()*0.1);
		int y1 = (int)(window.y1 + window.dy()*0.1);
		int x2 = (int)(window.x1 + window.dx()*0.9);
		int y2 = (int)(window.y1 + window.dy()*0.9);
//		int x1 = window.x1+window.dx()/2;
//		int y1 = window.y1+window.dy()/2;
//		int x2 = window.x2;
//		int y2 = window.y2;
		
		int offsetX = rand.nextInt(x2-x1);
		int offsetY = rand.nextInt(y2-y1);
//		offsetX=0;
//		offsetY=0;
		
		this.click(x1+offsetX, y1+offsetY);
		System.out.println(x1 + ", " + y1 + ", " + x2 + ", " + y2);
	}
	
	public void clickNear(int x, int y, int radiusX, int radiusY) {
		float range = rand.nextFloat();
		int randomPixel = (int) (range * radiusX +1);
		int range2 = (int) ((1-range) * radiusY);
		int randomPixel2 = rand.nextInt(range2+1);
		
		int randInt = rand.nextInt(4);
		if (randInt==0) {
			x = (int)(x + randomPixel);
			y = (int)(y + randomPixel2);
		} else if (randInt==1) {
			x = (int)(x + randomPixel);
			y = (int)(y - randomPixel2);
		} else if (randInt==2) {
			x = (int)(x - randomPixel);
			y = (int)(y - randomPixel2);
		} else if (randInt==3) {
			x = (int)(x - randomPixel);
			y = (int)(y + randomPixel2);
		}
		this.click(x, y);
	}
	
	public void clickNear(int x, int y, int radius) {
		this.clickNear(x, y, radius, radius);
	}
	
	public void clickNear(Point relCoords, int radius) {
		this.clickNear(relCoords.x(), relCoords.y(), radius);
	}
	
	public void click(int x, int y) {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public void rightClick(int x, int y) {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON2_MASK);
		robot.mouseRelease(InputEvent.BUTTON2_MASK);
	}
	
	public void click(Point point) {
		this.click(point.x(), point.y());
	}
	
	public void click(Window window) {
		int x = window.x1 + window.dx()/2;
		int y = window.y1 + window.dy()/2;
		this.click(x, y);
	}
}
