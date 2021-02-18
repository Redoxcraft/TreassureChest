package de.redoxcraft.treasurechest.main;

import org.bukkit.plugin.java.JavaPlugin;

import de.redoxcraft.treasurechest.commands.SpawnTreasureCommand;

public class Main extends JavaPlugin {

	public void onEnable() {
		
		System.out.println(this.getDescription().getName() + " " + this.getDescription().getVersion() + " geladen.");
		
		getCommand("schatz").setExecutor(new SpawnTreasureCommand());
		
	}
	
}
