package net.marfgamer.dbdvsync;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;

public class DBDVSProperties {

	private static final String ROAMING_PATH = System.getenv("APPDATA");
	private static final File APPDATA_FILE = new File(ROAMING_PATH).getParentFile();
	private static final File CONFIG_FILE = new File(APPDATA_FILE,
			"Local/DeadByDaylight/Saved/Config/WindowsNoEditor/GameUserSettings.ini");
	private static final String NODE_GAME_USER_SETTINGS = "/Script/DeadByDaylight.DBDGameUserSettings";
	public static final String USE_VSYNC = "bUseVSync";

	private Ini ini;

	public DBDVSProperties() throws IOException {
		if (!CONFIG_FILE.exists()) {
			throw new IOException("Failed to load config file! Has the game been launched at least once?");
		}
		this.ini = new Ini(CONFIG_FILE);
	}

	public String getGameSetting(String property) {
		return ini.get(NODE_GAME_USER_SETTINGS, "bUseVSync");
	}

	public boolean getGameSettingBoolean(String property) {
		return Boolean.parseBoolean(this.getGameSetting(property));
	}

	public void setGameSetting(String property, String value) throws IOException {
		Section section = ini.get(NODE_GAME_USER_SETTINGS);
		if (section.containsKey(property)) {
			section.remove(property); // No duplicates!
		}
		section.put(property, value);
		ini.put(NODE_GAME_USER_SETTINGS, section);
		ini.store(CONFIG_FILE);
	}

}
