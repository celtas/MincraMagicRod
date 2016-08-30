package mincra.magicrod.listener;

import mincra.magicrod.api.MagicApi;
import mincra.magicrod.database.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class InventoryListener implements Listener{
	JavaPlugin plugin;

	public InventoryListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onInventoryDragEvent(InventoryDragEvent event) {
		Inventory inventory = event.getInventory();
		if(inventory.getName().equals(DatabaseManager.magicChestInventoryTitle)) {
			Player player = (Player) event.getWhoClicked();
			Set<Integer> slots = event.getRawSlots();
			
			if(event.getType() == DragType.EVEN && event.getRawSlots().size()==0){
				event.setCancelled(true);
				player.sendMessage(ChatColor.AQUA+"ドラッグは使用できません.");
				return;
			}
			for(int slot:slots){
				if(slot < 54 && !MagicApi.isMagic(event.getOldCursor())){
					event.setCancelled(true);
					player.sendMessage(ChatColor.AQUA+"魔法を選択して下さい.");
					return;
				}
			}
			
			int user_id = DatabaseManager.getUserId(player.getUniqueId());
			if(user_id == -1)
				return;
			DatabaseManager.saveMagicChestInventory( inventory, user_id);
		}else if(inventory.getName().equals(DatabaseManager.skillInventoryTitle)) {
			Player player = (Player) event.getWhoClicked();
			Set<Integer> slots = event.getRawSlots();
			
			if(event.getType() == DragType.EVEN){
				event.setCancelled(true);
				player.sendMessage(ChatColor.AQUA+"ドラッグは使用できません.");
				return;
			}
			for(int slot:slots){
				if(slot < 9){
					if(!MagicApi.isMagicMaterial(event.getOldCursor())){
						event.setCancelled(true);
						player.sendMessage(ChatColor.AQUA+"魔法マテリアルを選択して下さい.");
						return;
					}else if(DatabaseManager.getMagicJob(DatabaseManager.getUserId(player.getUniqueId())) != MagicApi.getMagicJob(event.getOldCursor())){
						event.setCancelled(true);
						player.sendMessage(ChatColor.AQUA+"魔法を選択して下さい.");
						return;
					}
				}
			}
			
			int user_id = DatabaseManager.getUserId(player.getUniqueId());
			if(user_id == -1) 
				return;
			DatabaseManager.saveSkillInventory( inventory, user_id);
		}else if(inventory.getName().equals(DatabaseManager.giftBoxInventoryTitle)) {
			Player player = (Player) event.getWhoClicked();
			Set<Integer> slots = event.getRawSlots();
			
			if(event.getType() == DragType.EVEN){
				event.setCancelled(true);
				player.sendMessage(ChatColor.AQUA+"ドラッグは使用できません.");
				return;
			}
			for(int slot:slots){
				if(slot < 54){
					event.setCancelled(true);
					return;
				}
			}
			
			int user_id = DatabaseManager.getUserId(player.getUniqueId());
			if(user_id == -1) 
				return;
			DatabaseManager.saveGiftBoxInventory(inventory, user_id);
		}
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		//player.sendMessage("Action:"+event.getAction().toString());
		//player.sendMessage("RawSlot:"+event.getRawSlot());
		//player.sendMessage("RawSlot:"+event.Slot());//QuickBar 0-8 
		//player.sendMessage("Click:"+event.getClick().toString());//
		//player.sendMessage("Current:"+event.getCurrentItem().getType().toString());クリックする前のスロットのアイテム
		//player.sendMessage("Cursor:"+event.getCursor().getType().toString());クリックする前に持ってるアイテム
		//player.sendMessage("SlotType:"+event.getSlotType().toString());
		Inventory inventory = event.getInventory();
		if (inventory.getName().equals(DatabaseManager.magicChestInventoryTitle)) {
			Player player = (Player) event.getWhoClicked();
			ItemStack clicked = event.getCurrentItem();
			ItemStack cursor = event.getCursor();
				if(event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)){
					if(event.getSlotType().equals(SlotType.QUICKBAR)){
						if(!MagicApi.isMagic(clicked)){
							event.setCancelled(true);
							player.sendMessage(ChatColor.AQUA+"魔法を選択して下さい.");
							return;
						}
					}else if(event.getSlotType().equals(SlotType.CONTAINER)){
						if(event.getRawSlot()>53){
							if(!MagicApi.isMagic(clicked)){
								event.setCancelled(true);
								player.sendMessage(ChatColor.AQUA+"魔法を選択して下さい.");
								return;
							}
						}
					}
				}else if(event.getSlotType().equals(SlotType.CONTAINER)){
					if(event.getRawSlot()<=53){
						InventoryAction action = event.getAction();
						if(action == InventoryAction.SWAP_WITH_CURSOR||action == InventoryAction.PLACE_ALL||action == InventoryAction.PLACE_ONE||action == InventoryAction.PLACE_SOME){
							if(!MagicApi.isMagic(cursor)){
								event.setCancelled(true);
								player.sendMessage(ChatColor.AQUA+"魔法を選択して下さい.");
								return;
							}
						}
					}
				}
				int user_id = DatabaseManager.getUserId(player.getUniqueId());
				if(user_id == -1) 
					return;
				DatabaseManager.saveMagicChestInventory( inventory, user_id);
		}else if (inventory.getName().equals(DatabaseManager.skillInventoryTitle)) {
			Player player = (Player) event.getWhoClicked();
			ItemStack clicked = event.getCurrentItem();
			ItemStack cursor = event.getCursor();
				if(event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)){
					if(event.getSlotType().equals(SlotType.QUICKBAR)){
						if(!MagicApi.isMagicMaterial(clicked)){
							event.setCancelled(true);
							player.sendMessage(ChatColor.AQUA+"魔法マテリアルを選択して下さい.");
							return;
						}
					}else if(event.getSlotType().equals(SlotType.CONTAINER)){
						if(event.getRawSlot()>8){
							if(!MagicApi.isMagicMaterial(clicked)){
								event.setCancelled(true);
								player.sendMessage(ChatColor.AQUA+"魔法マテリアルを選択して下さい.");
								return;
							}
						}
					}
				}
				if(event.getSlotType().equals(SlotType.CONTAINER)){
					if(event.getRawSlot()<9){
						InventoryAction action = event.getAction();
						if(action == InventoryAction.SWAP_WITH_CURSOR||action == InventoryAction.PLACE_ALL||action == InventoryAction.PLACE_ONE||action == InventoryAction.PLACE_SOME){
							if(!MagicApi.isMagicMaterial(cursor)){
								event.setCancelled(true);
								player.sendMessage(ChatColor.AQUA+"魔法マテリアルを選択して下さい.");
								return;
							}
						}
					}
				}
				int user_id = DatabaseManager.getUserId(player.getUniqueId());
				if(user_id == -1) 
					return;
				DatabaseManager.saveSkillInventory( inventory, user_id);
		}else if (inventory.getName().equals(DatabaseManager.giftBoxInventoryTitle)) {
			Player player = (Player) event.getWhoClicked();
				if(event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)){
					if(event.getSlotType().equals(SlotType.QUICKBAR)){
							event.setCancelled(true);
							player.sendMessage(ChatColor.GRAY+"アイテムを入れることはできません.");
							return;
					}else if(event.getSlotType().equals(SlotType.CONTAINER)){
						if(event.getRawSlot()>53){
							event.setCancelled(true);
							player.sendMessage(ChatColor.GRAY+"アイテムを入れることはできません.");
							return;
						}
					}
				}
				if(event.getSlotType() == SlotType.CONTAINER){
					if(event.getRawSlot()<54){
						InventoryAction action = event.getAction();
						if(action == InventoryAction.PLACE_ALL||action == InventoryAction.PLACE_ONE||action == InventoryAction.PLACE_SOME||action == InventoryAction.SWAP_WITH_CURSOR){
							event.setCancelled(true);
							player.sendMessage(ChatColor.GRAY+"アイテムを入れることはできません.");
							return;
						}
					}
				}
				int user_id = DatabaseManager.getUserId(player.getUniqueId());
				if(user_id == -1) 
					return;
				DatabaseManager.saveGiftBoxInventory(inventory, user_id);
		}
	}
	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent event){
		String invName = event.getInventory().getTitle();
		if (invName.equals(DatabaseManager.magicChestInventoryTitle)) {
			int user_id = DatabaseManager.getUserId(event.getPlayer().getUniqueId());
			if(user_id == -1)
				return;
			DatabaseManager.saveMagicChestInventory( event.getInventory(), user_id);
		}else if (invName.equals(DatabaseManager.skillInventoryTitle)) {
			int user_id = DatabaseManager.getUserId(event.getPlayer().getUniqueId());
			if(user_id == -1) 
				return;
			DatabaseManager.saveSkillInventory( event.getInventory(), user_id);
		}else if (invName.equals(DatabaseManager.giftBoxInventoryTitle)) {
			int user_id = DatabaseManager.getUserId(event.getPlayer().getUniqueId());
			if(user_id == -1) 
				return;
			DatabaseManager.saveGiftBoxInventory(event.getInventory(), user_id);
		}
	}
}
