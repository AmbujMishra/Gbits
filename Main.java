package com.gameScreens.games;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
/*@author ambuj mishra*/
public class Main {
   public static void main(String[] args) {
      LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
      cfg.title = "Gbits";
      cfg.useGL30 = false;
      cfg.width = 800;
      cfg.height = 480;

      new LwjglApplication(new GbitsGame(), cfg);
      
   }
}
