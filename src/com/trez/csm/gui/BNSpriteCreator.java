package com.trez.csm.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.trez.csm.ByteWriter;
import com.trez.csm.CustomSpriteMaker;
import com.trez.csm.ImageOperations;
import com.trez.csm.Palette;
import com.trez.csm.SpriteConvertor;
import com.trez.csm.data.SpriteReader;
import com.trez.csm.gui.canvas.CanvasPanel;
import com.trez.csm.physical.AnimationPhysical;
import com.trez.csm.physical.FramePhysical;
import com.trez.csm.physical.ImageReader;
import com.trez.csm.physical.OAMPhysical;
import com.trez.csm.physical.SpritePhysical;

@SuppressWarnings("serial")
public class BNSpriteCreator extends JFrame implements ActionListener {
	
	JMenuBar menubar;
	
	JMenu file;
	JMenu sprite;
	
	JMenuItem load;
	JMenuItem loadanim;
	JMenuItem exportanim;
	JMenuItem exportsheet;
	
	JPanel mainpanel;
	
	public AnimationPanel anim;
	public FramePanel fram;
	public OptionsPanel opts;
	public OAMControl oamcon;

	SpritePhysical data;
	
	CanvasPanel canvas;
	
	public BNSpriteCreator() {
		super("Battle Network Sprite Creator");
		
		// sets the look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		mainpanel = new JPanel();
		mainpanel.setLayout(new FlowLayout());
		
		menubar = new JMenuBar();
		
		data = null;
		
		file = new JMenu("File");
		
		sprite = new JMenu("Sprite");
		
		load = new JMenuItem("Load Binary Sprite");
		load.addActionListener(this);
		load.setActionCommand("loadbin");
		exportsheet = new JMenuItem("Export Spritesheet");
		exportsheet.addActionListener(this);
		exportsheet.setActionCommand("exports");
		file.add(load);
		file.addSeparator();
		file.add(exportsheet);
		
		loadanim = new JMenuItem("Load Animation");
		loadanim.addActionListener(this);
		loadanim.setActionCommand("loada");
		exportanim = new JMenuItem("Export Animation");
		exportanim.addActionListener(this);
		exportanim.setActionCommand("exporta");
		sprite.add(loadanim);
		sprite.addSeparator();
		sprite.add(exportanim);
		
		menubar.add(file);
		menubar.add(sprite);
		
		JPanel lists = new JPanel();
		lists.setLayout(new FlowLayout());
		
		lists.add((anim = new AnimationPanel()));
		lists.add((fram = new FramePanel()));
		
		mainpanel.add(lists);
		
		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("Canvas", (canvas = new CanvasPanel()));
		jtp.addTab("Tileset", new JPanel());
		
		mainpanel.add(jtp);
		mainpanel.add((oamcon = new OAMControl()));
		mainpanel.add((opts = new OptionsPanel()));
		
		this.add(mainpanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(menubar);
		this.setSize(new Dimension(900, 575));
		this.setResizable(false);
	}
	
	public void reloadList() {
		anim.dlm.clear();
		fram.dlm.clear();
		
		for(AnimationPhysical ap : data.getAnimations()) {
			anim.dlm.addElement(ap);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("loadbin")) {
			JFileChooser jfc = new JFileChooser();
			if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File f = jfc.getSelectedFile();
				data = SpriteConvertor.convertToPhysical(new SpriteReader().createSprite(f));
				reloadList();
			}
		} else if(ae.getActionCommand().equals("exporta")) {
			int width = 256;
			int height = 256;
			
			AnimationPhysical ap = (AnimationPhysical) this.anim.animations.getSelectedValue();
			BufferedImage bi = new BufferedImage(ap.getSize() * width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = (Graphics2D) bi.getGraphics();
			int x = 0;
			for(FramePhysical fp: ap.getFrames()) {
				g2d.drawImage(fp.getImage(), x, 0, null);
				x += width;
			}
			
			try {
				ImageIO.write(bi, "png", new File("spritesheet.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(ae.getActionCommand().equals("exports")) {
			BufferedImage bi = new BufferedImage(256*this.data.getLargestAnimationSize(), 256*this.data.getAnimations().size(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = (Graphics2D) bi.getGraphics();
			
			int x = 0, y = 0;
			for(AnimationPhysical ap : this.data.getAnimations()) {
				for(FramePhysical fp : ap.getFrames()) {
					g2d.drawImage(fp.getImage(), x, y, null);
					x += 256;
				}
				x = 0;
				y += 256;
			}
			
			try {
				ImageIO.write(bi, "png", new File("spritesheet.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(ae.getActionCommand().equals("loada")) {
			try {
				AnimationPhysical anim = new AnimationPhysical();
				Vector<OAMPhysical> oams = ImageReader.getOAMsOfImage(ImageIO.read(new File("megaframe.png")));
				FramePhysical fp = new FramePhysical(2, 0);
				for(int i = 0; i < oams.size(); i++) {
					fp.addOAM(oams.get(i));
				}
				anim.addFrame(fp);
				
				// pixels are written dx+1, dx into 1 byte
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				ByteWriter.write32BitInt(baos, fp.getOAMs().size() * 32);
				
				System.out.println(fp.getOAMs().size() * 32);
				
				for(OAMPhysical oam : fp.getOAMs()) {
					BufferedImage bi = oam.getOAM();
					Palette p = Palette.getPaletteOf(oam.getOAM());
					for(int y = 0; y < oam.getOAM().getHeight(); y++) {
						for(int x = 0; x < oam.getOAM().getWidth(); x+=2) {
							int dpix = 0;
							dpix += 0xF0 & (p.getIndexOf(bi.getRGB(x+1, y)) << 4);
							dpix += 0x0F & (p.getIndexOf(bi.getRGB(x, y)));
							
							baos.write(dpix);
						}
					}
				}
				
				// take sprite physical and do something with it
				/*int region = this.data.getSize() * 4;
				for(int i = 0; i < region; i+=4) {
					ByteWriter.write32BitInt(baos, region + ((i/4) * 20));
				}
				
				ByteWriter.allocate(baos, this.data.getSize() * 20);
				*/
				FileOutputStream fos = new FileOutputStream(new File("bytes.dmp"));
				baos.writeTo(fos);
				
				baos.close();
				fos.close();
				
				//ImageIO.write(p.getPaletteImage(), "png", new File("pal.png"));
				
				//CustomSpriteMaker.gui.data.addAnimation(anim);
				//System.out.println(CustomSpriteMaker.gui.data.getSize());
				//this.reloadList();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
