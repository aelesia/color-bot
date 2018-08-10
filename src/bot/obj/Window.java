package bot.obj;

import java.awt.Rectangle;

public class Window {
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	
	public enum Corner {
		UPPER_LEFT,
		UPPER_RIGHT,
		LOWER_LEFT,
		LOWER_RIGHT;
	}

	public Window(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Window(Point relPoint, int radius) {
		this.x1 = relPoint.x()-radius;
		this.y1 = relPoint.y()-radius;
		this.x2 = relPoint.x()+radius;
		this.y2 = relPoint.y()+radius;
	}
	
	public Window(int x, int y, int radius) {
		this.x1 = x-radius;
		this.y1 = y-radius;
		this.x2 = x+radius;
		this.y2 = y+radius;		
	}
	
	public Window(Window parentWindow, double x1Percent, double y1Percent, double x2Percent, double y2Percent) {
		this.x1 = new Point(parentWindow, x1Percent, y1Percent).x();
		this.y1 = new Point(parentWindow, x1Percent, y1Percent).y();
		this.x2 = new Point(parentWindow, x2Percent, y2Percent).x(); 
		this.y2 = new Point(parentWindow, x2Percent, y2Percent).y();
	}
	
	public Window(Window parentWindow, Corner corner, int offsetX1, int offsetY1, int offsetX2, int offsetY2) {
		int baseX = 0;
		int baseY = 0;
		
		switch(corner) {
			case UPPER_LEFT:
				baseX = parentWindow.x1;
				baseY = parentWindow.y1;
				break;
			case UPPER_RIGHT:
				baseX = parentWindow.x1+parentWindow.dx();
				baseY = parentWindow.y1;
				break;
			case LOWER_LEFT:
				baseX = parentWindow.x1;
				baseY = parentWindow.y1+parentWindow.dy();
				break;
			case LOWER_RIGHT:
				baseX = parentWindow.x2;
				baseY = parentWindow.y2;
				break;
		}
		
		this.x1 = baseX + offsetX1;
		this.y1 = baseY + offsetY1;
		this.x2 = baseX + offsetX2;
		this.y2 = baseY + offsetY2;
	}
	
	public int dx() {
		return x2-x1;
	}
	
	public int dy() {
		return y2-y1;
	}
	
	public Rectangle toRectangle() {
		return new Rectangle(x1,  y1, this.dx(),this.dy());
	}
}