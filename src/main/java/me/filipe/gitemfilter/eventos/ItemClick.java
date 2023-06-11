package me.filipe.gitemfilter.eventos;

import me.filipe.gitemfilter.GItemFilter;
import me.filipe.gitemfilter.data.PlayerFilter;
import me.filipe.gitemfilter.menus.ItemFilter;
import me.filipe.gitemfilter.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemClick implements Listener {

    private GItemFilter plugin;
    public ItemClick(GItemFilter plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof ItemFilter)) return;

        e.setCancelled(true);

        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE) && e.getSlot() >= 18) return;

        if (e.getSlot() == 22) {
            PlayerFilter.toggleEnabled((Player) e.getWhoClicked());
            e.getWhoClicked().closeInventory();
            return;
        }

        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTopInventory() == e.getClickedInventory()) {
            PlayerFilter.removeFilter(p, e.getCurrentItem());
            e.getView().setItem(e.getSlot(), null);
            p.playSound(p, Sound.BLOCK_LEVER_CLICK, 1f, 1f);
        }

        if (e.getView().getBottomInventory() == e.getClickedInventory()) {

            List<ItemStack> filter = PlayerFilter.getFilter(p);
            if (filter.size() == 18) {
                p.sendMessage(Utils.chat("&cO teu filtro está cheio!"));
                return;
            }

            if (filter.contains(e.getCurrentItem())) {
                p.sendMessage(Utils.chat("&cEsse item já está no teu filtro!"));
                return;
            }

            ItemStack item = e.getCurrentItem();
            item.setAmount(1);
            PlayerFilter.addFilter(p, item);

            for (int i = 0; i < e.getView().getTopInventory().getSize() - 9; i++) {
                if (e.getView().getTopInventory().getItem(i) == null) {
                    e.getView().setItem(i, e.getCurrentItem());
                    break;
                }
            }

            p.playSound(p, Sound.BLOCK_LEVER_CLICK, 1f, 5f);
        }
    }

}
