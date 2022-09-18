package com.Istudios.grafics;

import com.Istudios.entities.Player;
import com.Istudios.main.Game;

import java.awt.*;

public class UI {

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(10,10,50,7);
        g.setColor(Color.GREEN);
        g.fillRect(10,10,(int) ((Game.player.health/Game.player.maxHealth)*50),7);
        g.setColor(Color.white);
        g.setFont(new Font("smooth",Font.TYPE1_FONT, 8));
        g.drawString((int) Game.player.health+" / "+(int) Game.player.maxHealth,(int) (Game.player.maxHealth/2 ),17);

        g.setColor(Color.RED);
        g.fillRect(10, 22, 31, 7);
        g.setColor(Color.CYAN);
        g.fillRect(10,22,(int) ((Game.player.mana/Game.player.maxMana)*31), 7);
        g.setColor(Color.WHITE);
        g.drawString((int) Game.player.mana+" / "+(int) Game.player.maxMana,(int) (Game.player.maxMana/2) -20,29);

    }

}
