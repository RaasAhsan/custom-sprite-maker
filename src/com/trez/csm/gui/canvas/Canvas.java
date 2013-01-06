package com.trez.csm.gui.canvas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.trez.csm.physical.AnimationPhysical;

@SuppressWarnings("serial")
public class Canvas extends JPanel implements ActionListener {
	
	AnimationPhysical animation;
	int curframe;
	boolean animating = false;
	
	BufferedImage canvas;
	
	Timer timer;
	
	int zoom;
	
	public Canvas() {
		this.setPreferredSize(new Dimension(256,256));
		curframe = 0;
		zoom = 1;
		try {
			canvas = ImageIO.read(new File("grid.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setVisible(true);
		repaint();
		
		timer = new Timer(60, this);
		timer.start(); 
		timer.stop();
	}
	
	public void setAnimation(AnimationPhysical ap) {
		if(ap != null)
			this.animation = ap;
	}
	
	public void setFrameIndex(int i) {
		this.curframe = i;
		repaint();
	}
	
	// 1 for original
	public void setZoomScale(int i) {
		this.zoom = i;
	}
	
	public void addZoomScale() {
		this.zoom++;
		repaint();
	}
	
	public void subZoomScale() {
		this.zoom--;
		repaint();
	}
	
	public void setAnimating(boolean b) {
		animating = b;
		if(b)
			timer.restart();
		else
			timer.stop();
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(canvas, 0, 0, null);
		if(animation != null) {
			if(curframe > -1) {
				BufferedImage img = animation.getFrameAt(curframe).getImage();
				g2d.drawImage(img, 256 - zoom * 256, 256 - zoom * 256, img.getWidth() * zoom, img.getHeight() * zoom, 0, 0, img.getWidth(), img.getHeight(), null);
			}
		}
		g2d.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(animation != null) {
			curframe++;
			if(curframe >= animation.getSize()) {
				curframe = 0;
			}
			repaint();
		}
	}

}
