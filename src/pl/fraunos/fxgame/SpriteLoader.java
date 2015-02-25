package pl.fraunos.fxgame;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class SpriteLoader {
	static Image sprites = new Image(Main.class.getResource("res/sprites.png")
			.toString());
	static int spriteSize = 256;
	static double scale = 0.5;

	public static Image getSprite(int column, int row) {
		return new WritableImage(sprites.getPixelReader(), column * spriteSize,
				row * spriteSize, spriteSize, spriteSize);
	}

}
