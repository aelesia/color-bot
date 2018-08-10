package runescapebot;

import java.awt.Color;

public class RunescapeItem {
	public String name;
	public String category;
	public Color averageColor;
	public int inventorySlot;
	
	public RunescapeItem(String name, String category, Color averageColor) {
		this.name = name;
		this.category = category;
		this.averageColor = averageColor;
	}
	
	public static RunescapeItem UNKNOWN_ITEM() {
		return new RunescapeItem("[UNKNOWN]", "null", new Color(0, 0, 0));
	}
	
//	public static RunescapeItem NULL_ITEM() {
//		return new RunescapeItem("NULL", "NULL", new Color(62, 53, 41));
//	}
}
