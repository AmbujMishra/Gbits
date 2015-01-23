package com.kingAm.games;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
   public static void main(String[] args) {
      LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
      cfg.title = "Gbits";
      cfg.useGL30 = false;
      cfg.width = 256;
      cfg.height = 256;

      new LwjglApplication(new Gbits(), cfg);
      
   }
}
