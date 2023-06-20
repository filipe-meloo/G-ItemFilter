package me.filipe.gitemfilter;

import me.filipe.gitemfilter.commands.OpenItemFilter;
import me.filipe.gitemfilter.commands.filtertext;
import me.filipe.gitemfilter.data.Dados;
import me.filipe.gitemfilter.data.PlayerFilter;
import me.filipe.gitemfilter.eventos.ItemClick;
import me.filipe.gitemfilter.eventos.PlayerJoin;
import me.filipe.gitemfilter.eventos.PlayerPickup;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class GItemFilter extends JavaPlugin {

    public Dados data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.data = new Dados(this);

        new PlayerJoin(this);
        new ItemClick(this);
        new PlayerPickup(this);

        new OpenItemFilter(this);
        new filtertext(this);

        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerFilter.load(this, p);
        }

        Bukkit.getConsoleSender().sendMessage("G-ItemFilter enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PlayerFilter.saveConfig(this);

        Bukkit.getConsoleSender().sendMessage("G-ItemFilter disabled!");
    }
}
