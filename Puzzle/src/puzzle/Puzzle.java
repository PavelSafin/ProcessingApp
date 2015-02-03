package puzzle;

import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")
public class Puzzle extends PApplet {

	PImage img;
	ArrayList<PImage> a;
	int b = 0;
	
	public void setup() {
		a = new ArrayList<PImage>();
		img = loadImage("pic.jpg");
		size(img.width, img.height);
		
		int t = 0;
		int m = 100;
		for (int i = 0; i < 64; ++i) {
			PImage im = createImage(img.width / 8, img.height / 8, ARGB);
			int j = 0;
			if (i % 8 == 0 && i > 0) {
				t += 80000;
			}
			for (int k = 0; k < 100 * 100; ++k) {
				if (k % 100 == 0 && k > 0) 
					++j;
				im.pixels[k] = img.pixels[(k % 100) + (j * img.height) + ((i % 8) * m) + t];
				if (i == 63)
					im.pixels[k] = color(0);
			}
			a.add(im);
		}
		Collections.shuffle(a);
		createPuzzle(img);
	}

	public void draw() {
	}
	
	public void keyPressed() {
		PImage pic = createImage(img.width, img.height, ARGB);
		PImage im = createImage(img.width / 8, img.height / 8, ARGB);
		
		switch (key) {
		case 'w':
			if (b - 8 >= 0) {
				im = a.get(b - 8);
				a.set(b - 8, a.get(b));
				a.set(b, im);
			}
			createPuzzle(pic);
			break;
		case 's':
			if (b + 8 < 64) {
				im = a.get(b + 8);
				a.set(b + 8, a.get(b));
				a.set(b, im);
			}
			createPuzzle(pic);
			break;
		case 'd':
			if ((b % 8) + 1 < 8) {
				im = a.get(b + 1);
				a.set(b + 1, a.get(b));
				a.set(b, im);
			}
			createPuzzle(pic);
			break;
		case 'a':
			if ((b % 8) - 1 >= 0) {
				im = a.get(b - 1);
				a.set(b - 1, a.get(b));
				a.set(b, im);
			}
			createPuzzle(pic);
			break;
		default:
			break;
		}	
		
		image(pic, 0, 0);
	}
	
	public void createPuzzle (PImage pic) {
		int t = 0;
		int m = 100;
		for (int i = 0; i < a.size(); ++i) {
			int j = 0;
			int c = 0;
			if (i % 8 == 0 && i > 0) {
				t += 80000;
			}
			for (int k = 0; k < 100 * 100; ++k) {
				if (k % 100 == 0 && k > 0) 
					++j;
				pic.pixels[(k % 100) + (j * pic.height) + ((i % 8) * m) + t] = a.get(i).pixels[k];
				if (a.get(i).pixels[k] == color(0))
					++c;
			}
			if (c == 100 * 100)
				b = i;
		}
	}
}

