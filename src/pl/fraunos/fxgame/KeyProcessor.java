package pl.fraunos.fxgame;

import javafx.scene.input.KeyCode;

public class KeyProcessor {
	boolean up, down, left, right;

	private void unpressAllKeys() {
		up = false;
		down = false;
		left = false;
		right = false;
	}

	public void process(KeyCode code, boolean isDown) {

		if (code == KeyCode.W) {
			up = isDown;
		}
		if (code == KeyCode.S) {
			down = isDown;
		}
		if (code == KeyCode.A) {
			left = isDown;
		}
		if (code == KeyCode.D) {
			right = isDown;
		}
	}
}
