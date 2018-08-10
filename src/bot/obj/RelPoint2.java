package bot.obj;

// 1 pixel margin of error
public class RelPoint2 {
	private double xPercent;
	private double yPercent;
	private int xOffset;
	private int yOffset;
	private Window parentWindow;

	public RelPoint2(Window window, int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.parentWindow = window;
	}
	
	public RelPoint2(Window window, double xPercent, double yPercent) {
		this.xPercent = xPercent;
		this.yPercent = yPercent;
		this.xOffset = (int) (this.xPercent * parentWindow.dx() / 100.0);
		this.yOffset = (int) (this.yPercent * parentWindow.dy() / 100.0);
		this.parentWindow = window;
	}
	
	public int x() {
		return this.xOffset + this.parentWindow.x1;
	}
	
	public int y() {
		return this.yOffset + this.parentWindow.y1;
	}
}
