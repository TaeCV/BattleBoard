package input;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputUtility {
	private static ArrayList<KeyCode> keyPressed = new ArrayList<KeyCode>();

	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}

	public static void setKeyPressed(KeyCode keyCode, boolean pressed) {
		if (pressed) {
			if (!keyPressed.contains(keyCode)) {
				keyPressed.add(keyCode);
			} else {
				keyPressed.remove(keyCode);
			}
		}
	}

	public static void removeKeyPressed() {
		for (int i = keyPressed.size() - 1; i >= 0; i--) {
			keyPressed.remove(i);
		}
	}
}
