package bot.tools;

import java.awt.MouseInfo;

import bot.obj.Window;
import me.aelesia.commons.utils.ThreadUtils;

public class CoordinateAnalyzer implements Runnable {
	
	int lastX;
	int lastY;
	Window window;
	
	public CoordinateAnalyzer() {
		
	}
	
	public CoordinateAnalyzer(Window window) {
		this.window = window;
	}


	@Override
	public void run() {
		while (true) {
			java.awt.Point point = MouseInfo.getPointerInfo().getLocation();
			int x = (int) point.getX();
			int y = (int) point.getY();
			
			if (x!=lastX || y!=lastY) {
				
				if (window == null) {
					System.out.println(x + ", " + y);
				} else if (x > window.x1 && x < window.x2 && y > window.y1 && y < window.y2){
					int offsetX = x - window.x1;
					int offsetY = y - window.y1;
					float relX = (offsetX) / (float)window.dx() * 100;
					float relY = (offsetY) / (float)window.dy() * 100;
					System.out.println("[" + offsetX + ", " + offsetY + "] | [" + relX + "%, " + relY + "%]");
				}
				lastX = x;
				lastY = y;
			}
			ThreadUtils.sleepFor(1);
		}
	}
}
