import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.util.List;

import bot.input.PixelScanner;
import bot.obj.Point;
import bot.obj.RelWindow;
import bot.obj.Window;
import bot.output.Clicker;
import bot.tools.CoordinateAnalyzer;
import bot.utils.ColorUtils;
import me.aelesia.commons.utils.ThreadUtils;
import runescapebot.RunescapeUI;
import runescapebot.inventory.ItemAnalyzer;

public class Main {

	static Window window = new Window(4, 25, 899, 759);

	public static void main(String[] args) throws AWTException, InterruptedException {
		// TODO Auto-generated method stub
		Robot robot;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
		
//		CoordinateAnalyzer ca = new CoordinateAnalyzer(RunescapeUI.RUNESCAPE_WINDOW);
//		new Thread(ca).start();
		
//		for (int i=5; i>0; i--) {
//			System.out.println("Starting in " + i);
//			ThreadUtils.sleepFor(1000);
//		}
//		
		Clicker clicker = new Clicker();
//		for (int i=4; i<28; i++) {
//			clicker.clickOn(RunescapeUI.INVENTORY_SLOT().get(i));
//			System.out.println("Clicking on : " + i);
//			ThreadUtils.sleepFor(500);
//		}
//		while (true) {
//			Point b = MouseInfo.getPointerInfo().getLocation();
//			int x = (int) b.getX();
//			int y = (int) b.getY();
//			System.out.println(x + ", " + y);
//		}
//		clicker.clickOn(window);
//		clicker.click(new RelCoords(window, 99.84, 99.83));
//		for (int i=0; i<1000; i++) {
////			clicker.clickAround(new RelCoords(window, 44.64, 25.47), 15);
//			clicker.clickOn(window);
//			Thread.sleep(10);
//		}
		
		PixelScanner scanner = new PixelScanner(robot);
//		List<Color> trout = scanner.scanColors(RunescapeUI.INVENTORY_SLOT().get(4), 10, 10, 1, true);
//		List<Color> salmon = scanner.scanColors(RunescapeUI.INVENTORY_SLOT().get(6), 20, 20, 1, true);
//		for (int i=0; i<28; i++) {
//			List<Color> item = scanner.scanColors(RunescapeUI.INVENTORY_SLOT().get(i), 10, 10, 1, true);
//
//			System.out.print(i + " - ");
////			for (Color c : item) {
////				System.out.print(ColorUtils.colorToString(c) + " | ");
////			}
//			if (ColorUtils.picturesAreSimilar(trout, item, 10)) {
//				System.out.print("is a trout");
//				clicker.click(RunescapeUI.INVENTORY_SLOT().get(i));
//			}
//			System.out.println("");
//		}

		ItemAnalyzer itemAnalyzer = new ItemAnalyzer(scanner, clicker);
		itemAnalyzer.identify();

//		List<Color> colorList = scanner.scanColors(window, 50, window.dy());
//		List<Color> colorList = scanner.scanColors(window, 50, 50, 1);
//		for (Color color : colorList) {
//			System.out.println(color);
//		}
//		System.out.println(colorList.size());
	}

}
