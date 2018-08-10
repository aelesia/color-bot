package runescapebot;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import bot.obj.Point;
import bot.obj.Window;

public class RunescapeUI {

	// Current Window
	public static Window RUNESCAPE_WINDOW = new Window(4, 25, 899, 759);
	// Mspaint
//	public static Window RUNESCAPE_WINDOW = new Window(6, 143, 645, 659);
	
	public static Window INVENTORY = new Window(RUNESCAPE_WINDOW, Window.Corner.LOWER_RIGHT, -241, -334, 0, 0);
	
	public static List<Window> INVENTORY_SLOT() {
		List<Window> inventorySlotList = new ArrayList<Window>();

		for (int y=0; y<7; y++) {
			for (int x=0; x<4; x++) {
				inventorySlotList.add(new Window(new Point(INVENTORY, 56+42*x, 61+36*y), 17));
			}
		}
		return inventorySlotList;
	}
	
	public static Color BACKGROUND_COLOUR = new Color(62, 53, 41);
	
}
