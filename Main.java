package com.Gbits.Screens;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
/*@author ambuj mishra*/
public class Main {
   public static void main(String[] args) {
      LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
      cfg.title = "GbitsGame";
      cfg.useGL30 = false;
      cfg.width = 300;
      cfg.height = 200;

      new LwjglApplication(new GbitsGame(), cfg);
      
   }
}
