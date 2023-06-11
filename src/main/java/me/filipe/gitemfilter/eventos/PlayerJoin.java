package me.filipe.gitemfilter.eventos;

import me.filipe.gitemfilter.GItemFilter;
import me.filipe.gitemfilter.data.PlayerFilter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private GItemFilter plugin;
    public PlayerJoin(GItemFilter plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        PlayerFilter.load(plugin, e.getPlayer());
    }

}
