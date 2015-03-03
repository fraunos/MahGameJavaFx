package pl.fraunos.fxgame;

import java.util.Random;

import javafx.scene.image.Image;

public class WorldMap {

	int[] map = new int[10];
	Random random = new Random();

	Image grass = SpriteLoader.getSprite(1, 1);

	public WorldMap() {
		System.out.println(map.length);
		for (int i = 0; i < map.length / 2; i++) {
			for (int j = 0; j < map.length / 2; j++) {
				map[i + j] = random.nextInt(2);
			}
		}
	}

}
