package bot.obj;

// 1 pixel margin of error
public class Point {
	private double xPercent;
	private double yPercent;
	private int xOffset;
	private int yOffset;
	private Window parentWindow;
	private int x;
	private int y;

	public Point(Window window, int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.parentWindow = window;
		this.x = this.xOffset + this.parentWindow.x1;
		this.y = this.yOffset + this.parentWindow.y1;
	}
	
	public Point(Window window, double xPercent, double yPercent) {
		this.xPercent = xPercent;
		this.yPercent = yPercent;
		this.xOffset = (int) (this.xPercent * parentWindow.dx() / 100.0);
		this.yOffset = (int) (this.yPercent * parentWindow.dy() / 100.0);
		this.parentWindow = window;
		this.x = this.xOffset + this.parentWindow.x1;
		this.y = this.yOffset + this.parentWindow.y1;
	}
	
	public int x() {
		return this.x;
	}
	
	public int y() {
		return this.y;
	}
}
