package com.trez.csm;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public class ColorConvertor {

	public static int rgbToHex(int rgb) {
		int r = ((rgb & 0x00FF0000)) >> 3;
		int g = ((rgb & 0x0000FF00)) >> 3;
		int b = (rgb & 0xFF) >> 3;
		return (b << 10) | (g << 5) | r;
	}
	
	public static Color hextoRGB(int hex) {
		int b = (hex >> 10) & 0x1F;
		int g = (hex >> 5) & 0x1F;
		int r = hex & 0x1F;
		return new Color(r << 3,g << 3,b << 3);
	}
	
	private static BufferedImage imageToBufferedImage(Image image) {
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

		return bufferedImage;

	}

	public static BufferedImage makeColorTransparent(BufferedImage im, final Color color) {
		ImageFilter filter = new RGBImageFilter() {
			public int markerRGB = color.getRGB() | 0xFF000000;

			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					return 0x00FFFFFF & rgb;
				} else {
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return imageToBufferedImage(Toolkit.getDefaultToolkit().createImage(ip));
	}
	
}

