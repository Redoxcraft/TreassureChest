package de.redoxcraft.treasurechest.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpawnTreasureCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
				spawnTreasure(p);
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	private void spawnTreasure(Player p) {
		int randomX = (int) (Math.random()*(2000));
		int randomZ = (int) (Math.random()*(2000));
		int x = 1000 - randomX;
		int z = 1000 - randomZ;
		for(int y = 100; y > 20; y--) {
			Location loc = new Location(p.getWorld(), x, y, z);
			if(y == 100 && p.getWorld().getBlockAt(loc).getType() != Material.AIR) {
				spawnTreasure(p);
				return;
			}
			if(p.getWorld().getBlockAt(loc).getType() != Material.AIR && p.getWorld().getBlockAt(loc).getType() != Material.WATER) {
				loc.setY(loc.getY() + 1);
				loc.getBlock().setType(Material.CHEST);
				Chest c = (Chest) loc.getBlock().getState();
				FillChest(c);
				Bukkit.broadcastMessage("Gerüchten zu folge ist bei den Koordinaten " + (int) (x + 0.5) + " " + (int) y + " " + (int) (z + 0.5) + " ein Schatz aufgetaucht!" + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + " *In einem Schatz sind doch wertvolle Gegenstände versteckt...*");
				return;
			}
		}
		spawnTreasure(p);
		
	}

	private void FillChest(Chest c) {
		for(int i = 0; i < 27; i++) {
			int randomfill = (int) (Math.random()*(2));
			if(randomfill == 0) {
				ChestContent(c, i);
			}else if(randomfill == 1) {
				
			}else {
				Bukkit.broadcastMessage("TreasureChest FillChest ERROR, bitte melde das einem Administrator!");
			}
			
		}
		
	}

	private void ChestContent(Chest c, int i) {
		ItemStack item = null;
		int randomRarity = (int) (Math.random()*(100));
		if(randomRarity <= 29) { //materials
			int randomLoot = (int) (Math.random()*(10));
			int randomCount = (int) ((Math.random()*(4)) + 15);
			switch(randomLoot) {
			case 0: item = new ItemStack(Material.SAND, randomCount); break;
			case 1: item = new ItemStack(Material.QUARTZ_BLOCK, randomCount); break;
			case 2: item = new ItemStack(Material.RED_SAND, randomCount); break;
			case 3: item = new ItemStack(Material.PURPUR_BLOCK, randomCount); break;
			case 4: item = new ItemStack(Material.ACACIA_LOG, randomCount); break;
			case 5: item = new ItemStack(Material.OAK_LOG, randomCount); break;
			case 6: item = new ItemStack(Material.BIRCH_LOG, randomCount); break;
			case 7: item = new ItemStack(Material.DARK_OAK_LOG, randomCount); break;
			case 8: item = new ItemStack(Material.SPRUCE_LOG, randomCount); break;
			case 9: item = new ItemStack(Material.JUNGLE_LOG, randomCount); break;
			default: Bukkit.broadcastMessage("TreasureChest ChestContent1 ERROR, bitte melde das einem Administrator!"); break;
			}
			c.getInventory().setItem(i, item);
		}else if(randomRarity >= 30 && randomRarity <= 69) {//rare loot
			int randomLoot = (int) (Math.random()*(10));
			int randomCount = (int) ((Math.random()*(3)) + 1);
			switch(randomLoot) {
			case 0:
			case 1:
			case 2: item = new ItemStack(Material.GUNPOWDER, randomCount + 6); break;
			case 3: item = new ItemStack(Material.DIAMOND, randomCount); break;
			case 4: item = new ItemStack(Material.GOLDEN_APPLE); break;
			case 5: item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE); break;
			case 6: item = new ItemStack(Material.EXPERIENCE_BOTTLE, randomCount); break;
			case 7: item = new ItemStack(Material.SLIME_BALL, randomCount + 3); break;
			case 8: item = new ItemStack(Material.NETHERITE_INGOT); break;
			case 9: item = new ItemStack(Material.NETHERITE_SCRAP, randomCount); break;
			default: Bukkit.broadcastMessage("TreasureChest ChestContent2 ERROR, bitte melde das einem Administrator!"); break;
			}
			c.getInventory().setItem(i, item);
		}else if(randomRarity >= 70) {//cobweb and vines
			int randomLoot = (int) (Math.random()*(2));
			switch(randomLoot) {
			case 0: item = new ItemStack(Material.VINE); break;
			case 1: item = new ItemStack(Material.COBWEB); break;
			default: Bukkit.broadcastMessage("TreasureChest ChestContent3 ERROR, bitte melde das einem Administrator!"); break;
			}
			c.getInventory().setItem(i, item);
		}
		
	}

}
