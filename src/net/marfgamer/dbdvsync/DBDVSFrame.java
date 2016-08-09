package net.marfgamer.dbdvsync;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

public class DBDVSFrame extends JFrame {

	private static final long serialVersionUID = 7950023662799564294L;
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 115;
	private static final String STATUS_STR = "Status: %s";

	private final JTextPane txtpnStatuss;
	private final JToggleButton tglbtnToggleVsync;

	public DBDVSFrame(final DBDVSProperties properties) {
		getContentPane().setLayout(null);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);

		JTextPane txtpnUseThisProgram = new JTextPane();
		txtpnUseThisProgram.setBackground(UIManager.getColor("Button.background"));
		txtpnUseThisProgram.setText("Enable/disable VSync for Dead by Daylight");
		txtpnUseThisProgram.setBounds(10, 11, 315, 20);
		getContentPane().add(txtpnUseThisProgram);

		tglbtnToggleVsync = new JToggleButton("Toggle VSync");
		tglbtnToggleVsync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean selected = ((AbstractButton) e.getSource()).isSelected();
					setStatus(selected);
					properties.setGameSetting(DBDVSProperties.USE_VSYNC, (selected ? "True" : "False"));
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		tglbtnToggleVsync.setBounds(10, 42, 121, 23);

		getContentPane().add(tglbtnToggleVsync);

		txtpnStatuss = new JTextPane();
		txtpnStatuss.setBackground(UIManager.getColor("Button.background"));
		txtpnStatuss.setText(STATUS_STR);
		txtpnStatuss.setBounds(141, 42, 184, 20);
		getContentPane().add(txtpnStatuss);
		setStatus(properties.getGameSettingBoolean(DBDVSProperties.USE_VSYNC));
	}

	public void setStatus(boolean enabled) {
		tglbtnToggleVsync.setSelected(enabled);
		txtpnStatuss.setText(String.format(STATUS_STR, (enabled ? "Enabled" : "Disabled")));
	}

}
