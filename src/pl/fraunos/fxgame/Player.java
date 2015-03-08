package pl.fraunos.fxgame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class Player extends ImageView {
	final int MAX_SPEED = 5;

	double x, y;
	int speed = 0;
	double direction = 0;
	int currentState = 0;
	Image image;
	AudioClip[] ac = { new AudioClip(Main.class.getResource("res/kroki.mp3").toString()), new AudioClip(Main.class.getResource("res/kroki-2.mp3").toString()) };

	public void update(double mouseX, double mouseY) {
		movePlayer();

		updateSprite();
		speed = 0;
		direction = Math.toDegrees(Math.atan2(mouseY - Main.sizeY / 2, mouseX - Main.sizeX / 2)) + 180;
		setRotate(direction - 90);
	}

	public double getCenterX(double x) {
		return x + 128;

	}

	public double getCenterY(double y) {
		return y + 128;

	}

	Player(int x, int y) {
		this.x = x;
		this.y = y;
		setScaleX(SpriteLoader.scale);
		setScaleY(SpriteLoader.scale);
		setX(Main.sizeX / 2 - 128);
		setY(Main.sizeY / 2 - 128);
		setImage(image);

	}

	private void updateSprite() {
		if (speed > 0) {
			if (Main.gameTime % (50 / speed) == 0) {
				currentState++;
				currentState %= 2;
				playSound(currentState);
			}
		} else {
			currentState = 4;
		}
		setImage(SpriteLoader.getSprite(currentState, 0));
	}

	private void playSound(int x) {
		ac[x % 2].play();

	}

	private void movePlayer() {

		if (Main.kp.up) {
			moveInSpecifiedDirection(direction);
		}
		if (Main.kp.left) {
			moveInSpecifiedDirection(direction - 90);
		}
		if (Main.kp.right) {
			moveInSpecifiedDirection(direction + 90);
		}
		if (Main.kp.down) {
			moveInSpecifiedDirection(direction + 180);
		}
	}

	private void moveInSpecifiedDirection(double direction) {
		speed = MAX_SPEED;
		y -= speed * Math.sin(Math.toRadians(direction));
		x -= speed * Math.cos(Math.toRadians(direction));
	}

}
