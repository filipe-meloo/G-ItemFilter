package me.filipe.gitemfilter.eventos;

import me.filipe.gitemfilter.GItemFilter;
import me.filipe.gitemfilter.data.PlayerFilter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlayerPickup implements Listener {

    private GItemFilter plugin;
    public PlayerPickup(GItemFilter plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {

        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        Player p = (Player) e.getEntity();

        if (!PlayerFilter.getEnabled(p)) {
            return;
        }

        List<ItemStack> filter = PlayerFilter.getFilter(p);
        Item itemP = e.getItem();

        if (PlayerFilter.getReverse(p)) {
            for (ItemStack itemF : filter) {
                if (!itemP.getItemStack().getType().equals(itemF.getType())) {
                    e.setCancelled(true);
                }
            }
        } else {
            for (ItemStack itemF : filter) {
                if (itemP.getItemStack().getType().equals(itemF.getType())) {
                    e.setCancelled(true);
                }
            }
        }

    }
}
