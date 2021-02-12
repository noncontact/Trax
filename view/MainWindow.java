package view;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class MainWindow {
	private static JFrame mainFrame;
	private static JMenuBar mainMenuBar;
	private static JScrollPane mapPanel;
	private static Map map;
	
	private static JFrame initWindow() {
		JFrame frame = new JFrame();
		
		frame.setSize(new Dimension(800, 800));
		frame.setTitle("Trax Game - Hyper Ecilpse");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainMenuBar = initMenu();
		mapPanel = initMap();
		frame.getContentPane().add(mapPanel);
		frame.setJMenuBar(mainMenuBar);
		
		frame.setVisible(true);
		
		return frame;
	}
	private static JMenuBar initMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu m;
		JMenuItem mi;
		
		m = new JMenu("File");
		m.setMnemonic(KeyEvent.VK_F);
		mb.add(m);
		mi = new JMenuItem("Load Game");
		m.add(mi);
		mi = new JMenuItem("Save Game");
		m.add(mi);
		mi = new JMenuItem("Exit");
		m.addSeparator();
		m.add(mi);
		
		m = new JMenu("Game");
		m.setMnemonic(KeyEvent.VK_G);
		mb.add(m);
		
		m = new JMenu("Utility");
		m.setMnemonic(KeyEvent.VK_U);
		mb.add(m);
		
		m = new JMenu("Help");
		m.setMnemonic(KeyEvent.VK_H);
		mb.add(m);
		
		return mb;
	}
	private static JScrollPane initMap() {
		JScrollPane sp = new JScrollPane();
		JPanel pn = new JPanel();
		int x, y;
		
		map = new Map();
		pn.setLayout(new GridLayout(map.height, map.width));
		for(y=0; y<map.height; y++) {
			for(x=0; x<map.width; x++) {
				pn.add(map.tiles[y][x]);
			}
		}
		
		sp.setViewportView(pn);
		
		return sp;
	}
	public static void main (String[] args) {
		
		mainFrame = initWindow();
		
		mapPanel.getVerticalScrollBar().setValue(1030);
		mapPanel.getHorizontalScrollBar().setValue(1005);
	}
}