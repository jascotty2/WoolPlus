package com.gmail.nossr50.woolplus;

import com.jascotty2.ColorPalette;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class WPPlayerListener implements Listener {

    //private final WoolPlus plugin;

    public WPPlayerListener(WoolPlus instance) {
        //plugin = instance;
    }

	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){ 
        Player player = event.getPlayer();
        ItemStack item = event.getPlayer().getItemInHand();
        Block block = event.getClickedBlock();//.getBlockClicked();
        if (block != null && item != null && isDye(item) && isWool(block)) {
            dyeWool(block, item, player);
        }
    }

    public static boolean isDye(ItemStack item) {
        return item.getTypeId() == 351;// || type == 352
    }

    public static boolean isWool(Block block) {
        return block.getTypeId() == 35;
    }

    public static boolean isBoneMeal(ItemStack item) {
        return item.getTypeId() == 351 && item.getDurability() == 15;
    }

    public static boolean isLightColoredWool(byte wool) {
        return wool == 4 || wool == 5 || wool == 6 || wool == 9 || wool == 2 || wool == 3;
    }

    public void consumeDye(short type, Player player) {
        PlayerInventory inv = player.getInventory();
        // consume the dye the player is holding
        ItemStack hand =  inv.getItemInHand();
        if(isDye(hand) && hand.getDurability()== type){
            if(hand.getAmount()==1){
                inv.setItemInHand(null);
            }else{
                hand.setAmount(hand.getAmount()-1);
                inv.setItemInHand(hand);
            }
            return;
        }
        // failsafe: find one that is of this type
        for (int i = 0; i <= 35; ++i) {
            if (inv.getItem(i).getTypeId() == 351 && inv.getItem(i).getDurability() == type) {
                if (inv.getItem(i).getAmount() == 1) {
                    inv.setItem(i, null);
                } else {
                    ItemStack n = inv.getItem(i);
                    n.setAmount(n.getAmount() - 1);
                    inv.setItem(i, n);
                }
                return;
            }
        }
    }

    public void dyeWool(Block block, ItemStack item, Player player) {
        byte wool = ColorPalette.DyeColor.fromWool(block.getData()).data;
        short resultColor = ColorPalette.MixColors(wool, item.getDurability()).toWool();

        if(resultColor>=0){
            block.setData((byte)resultColor);
            consumeDye(item.getDurability(), player);
        }
    }
}
