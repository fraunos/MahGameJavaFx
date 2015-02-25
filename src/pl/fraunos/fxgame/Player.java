package pl.fraunos.fxgame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ImageView {
	int x, y;
	int speed = 2;
	double direction = 0;
	Image image = SpriteLoader.getSprite(3, 0);

	public void update(double mouseX, double mouseY) {
		System.out.printf(direction + "\t");
		System.out.print(Math.cos(direction) + "\t");
		System.out.println(Math.sin(direction));

		if (Main.kp.down) {
			y += speed * Math.cos(direction);
			x += speed * Math.sin(direction);
		}
		if (Main.kp.up) {
			y += speed * Math.cos(direction);
			x -= speed * Math.sin(direction);
		}
		if (Main.kp.left) {
			y -= speed * Math.cos(direction);
			x += speed * Math.sin(direction);
		}
		if (Main.kp.right) {
			y += speed * Math.cos(direction);
			x += speed * Math.sin(direction);
		}

		setX(x);
		setY(y);
		direction = Math.toDegrees(Math.atan2(mouseY - getCenterY(y), mouseX
				- getCenterX(x)));
		setRotate(direction + 90);
		// System.out.println(isMoving);
	}

	public int getCenterX(int x) {
		return x + 128;

	}

	public int getCenterY(int y) {
		return y + 128;

	}

	Player(int x, int y) {
		setScaleX(SpriteLoader.scale);
		setScaleY(SpriteLoader.scale);
		setImage(image);
		this.x = x;
		this.y = y;
	}

}
