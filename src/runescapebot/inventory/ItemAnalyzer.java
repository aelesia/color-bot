package runescapebot.inventory;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bot.input.PixelScanner;
import bot.output.Clicker;
import bot.utils.ColorUtils;
import me.aelesia.commons.storage.JsonTextStorage;
import me.aelesia.commons.storage.Storage;
import runescapebot.RunescapeItem;
import runescapebot.RunescapeUI;

public class ItemAnalyzer {
	
	PixelScanner scanner;
	Clicker clicker;
	public Scanner sc = new Scanner(System.in);

	public List<RunescapeItem> itemDatabase = new ArrayList<RunescapeItem>();
	public List<RunescapeItem> inventory = new ArrayList<RunescapeItem>();
	
	public ItemAnalyzer(PixelScanner scanner, Clicker clicker) {
		this.clicker = clicker;
		this.scanner = scanner;
		try {
			Storage jsonStorage =  new JsonTextStorage();
			itemDatabase = (List<RunescapeItem>)(Object)jsonStorage.loadList("runescape_items.txt", RunescapeItem.class);
		} catch (IOException e) {
			throw new RuntimeException("Error: Unable to load item database file. Please check if 'runescape_items.txt' exists.", e);
		}
	}
	
	public void analyzeInventory() {
		inventory = new ArrayList<RunescapeItem>();
		for (int i=0; i<28; i++) {
			List<Color> unidentifiedItem = scanner.scanColors(RunescapeUI.INVENTORY_SLOT().get(i), 20, 20);
			RunescapeItem item = findFromDatabase(unidentifiedItem);
			item.inventorySlot = i;
			inventory.add(item);
		}
		printInventory();
	}
	
	public void identify() {
		inventory = new ArrayList<RunescapeItem>();
		for (int i=0; i<28; i++) {
			List<Color> unidentifiedItem = scanner.scanColors(RunescapeUI.INVENTORY_SLOT().get(i), 20, 20);
			RunescapeItem identifiedItem = findFromDatabase(unidentifiedItem);
			if (identifiedItem == null) {
//				clicker.rightClick(RunescapeUI.INVENTORY_SLOT().get(i).x1+10, RunescapeUI.INVENTORY_SLOT().get(i).y1+10);
				printInventory();
				System.out.println("Unidentified item in slot " + (i+1));
				System.out.println("_______________________________");
				System.out.print("Enter item name: "); String name = sc.nextLine().toUpperCase();
				System.out.print("Enter item category: "); String category = sc.nextLine().toUpperCase();
				Color averageColor =  ColorUtils.calcAvgColour(scanner.scanColors(RunescapeUI.INVENTORY_SLOT().get(i), 50, 50));
				System.out.println("Average color: " + ColorUtils.colorToString(averageColor));
				identifiedItem = new RunescapeItem(name, category, averageColor);
				itemDatabase.add(identifiedItem);
				saveDatabase();
			}
			inventory.add(identifiedItem);
		}
		printInventory();
	}
	
	public void scanInventory() {
		for (int i=0; i<28; i++) {
			List<Color> item;
			item = scanner.scanColors(RunescapeUI.INVENTORY_SLOT().get(i), 10, 10, 1, true);	
		}	
	}
	
	public RunescapeItem findFromDatabase(List<Color> unidentifiedItem) {
		for (RunescapeItem item : itemDatabase) {
			if (ColorUtils.colorIsSimilar(ColorUtils.calcAvgColour(unidentifiedItem), item.averageColor, 10)) {
				return item;
			}
		}
		return RunescapeItem.UNKNOWN_ITEM();
	}
	
	public void saveDatabase() {
		JsonTextStorage storage = new JsonTextStorage();
		storage.save(itemDatabase, "runescape_items.txt");
	}
	
	public void printInventory() {
		int i=0;
		for (RunescapeItem item : inventory) {
			if (i%4==0) {
				System.out.println();
			}
			i++;
			System.out.print(item.name + ", ");
		}
		System.out.println("");
	}
}
