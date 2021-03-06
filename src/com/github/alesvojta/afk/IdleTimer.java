package com.github.alesvojta.afk;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Aleš Vojta (https://github.com/alesvojta)
 */
class IdleTimer implements Runnable {

    private final Player player;
    private final AFK plugin;
    private Location lastLocation;

    /**
     * Constructor initialize variables.
     * It calls updatePlayer() which updates Players location.
     *
     * @param playerName Player
     * @param plugin     AFK Plugin
     */
    protected IdleTimer(String playerName, AFK plugin) {
        this.player = Bukkit.getPlayer(playerName);
        this.plugin = plugin;
        updatePlayer();
    }

    /**
     * If Player stands on one place for specified amount of time, it sets the player AFK.
     */
    @Override
    public void run() {
        if (player.getLocation().equals(lastLocation) && !AFK.isPlayerAfk(player.getName())) {
            long idleTime = plugin.getCfg().idleTime() * 20;
            player.setPlayerTime(-idleTime, true);
            plugin.becomeAFK(player);
            return;
        }
        updatePlayer();
    }

    /**
     * Updates Players location.
     */
    private void updatePlayer() {
        lastLocation = player.getLocation();
    }
}
