package net.marfgamer.dbdvsync;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class DBDVSMain {

	public static void main(String[] args) {
		DBDVSFrame frame = null;
		try {
			DBDVSProperties properties = new DBDVSProperties();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			frame = new DBDVSFrame(properties);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (Exception e) {
			if (frame != null) {
				frame.setVisible(false);
			}
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error on load!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

}
