package bot.utils;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

public class ColorUtils {
	
	public static String colorToHex(Color c) {
		String r = Integer.toHexString(c.getRed());
		String g = Integer.toHexString(c.getGreen());
		String b = Integer.toHexString(c.getBlue());
		return r+g+b;
	}
	
	public static String colorToString(Color c) {
		String r = Integer.toString(c.getRed());
		String g = Integer.toString(c.getGreen());
		String b = Integer.toString(c.getBlue());
		String a = Integer.toString(c.getAlpha());
		return "["+r+","+g+","+b+","+a+"]";
	}
	
	public static boolean colorIsSimilar(Color c1, Color c2, int variance) {
		int dxRed = Math.abs(c1.getRed()-c2.getRed());
		int dxGreen =  Math.abs(c1.getGreen()-c2.getGreen());
		int dxBlue =  Math.abs(c1.getBlue()-c2.getBlue());
		int dxAlpha = Math.abs(c1.getAlpha()-c2.getAlpha());
		if ((dxRed + dxGreen + dxBlue) <= variance*2 && dxAlpha <=4) {
			return true;
		}
		return false;
	}
	
	public static Color calcAvgColour(List<Color> picture) {
		int p1r=0;
		int p1g=0;
		int p1b=0;
		int p1a;
		int p1Count=0;
		for (Color c1 : picture) {
			if (ColorUtils.colorIsSimilar(c1, new Color(62, 53, 41), 5)) {
				continue;
			} else if (c1.getRed() == 255 && c1.getGreen() == 255) {
				continue;
			}
			p1Count++;
			p1r += c1.getRed();
			p1g += c1.getGreen();
			p1b += c1.getBlue();
		}
		if (p1Count==0) {
			return new Color(62, 53, 41, 0);
		}
		p1r = p1r/p1Count;
		p1g = p1g/p1Count;
		p1b = p1b/p1Count;
		p1a = (int) (p1Count / (float)picture.size() * 100);
		return new Color(p1r, p1g, p1b, p1a);
	}
	
	public static boolean picturesAreSimilar(List<Color> picture1, List<Color> picture2, int variance) {
		
		Color p1Avg = calcAvgColour(picture1);
		Color p2Avg = calcAvgColour(picture2);
		
		System.out.println(p1Avg.getAlpha() + " | " + p2Avg.getAlpha());
	
		if (ColorUtils.colorIsSimilar(p1Avg, p2Avg, variance)) {
			return true;
		}
		
		
		return false;
	}
}
