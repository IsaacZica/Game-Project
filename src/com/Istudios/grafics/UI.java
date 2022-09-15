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
        g.setFont(new Font("arial", Font.BOLD, 8));
        g.drawString((int) Game.player.health+" / "+(int) Game.player.maxHealth,(int) (Game.player.maxHealth/2 ),17);
    }

}
