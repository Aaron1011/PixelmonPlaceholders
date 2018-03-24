package com.github.happyzleaf.pixelmonplaceholders;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.File;
import java.io.IOException;

public class PPConfig {
	public static boolean disableEggInfo = true;
	
	public static String disabledEggMessage = "&cThe eggs are disabled.";
	
	private static ConfigurationLoader<CommentedConfigurationNode> loader;
	private static CommentedConfigurationNode node;
	private static File file;
	
	public static void init(ConfigurationLoader<CommentedConfigurationNode> loader, File file) {
		PPConfig.loader = loader;
		PPConfig.file = file;
		
		loadConfig();
	}
	
	public static void loadConfig() {
		if (!file.exists()) {
			saveConfig();
		}
		
		load();
		
		ConfigurationNode miscellaneous = node.getNode("miscellaneous");
		disableEggInfo = miscellaneous.getNode("disableEggInfo").getBoolean();
		disabledEggMessage = miscellaneous.getNode("disabledEggMessage").getString();
	}
	
	public static void saveConfig() {
		load();
		
		CommentedConfigurationNode miscellaneous = node.getNode("miscellaneous");
		miscellaneous.getNode("disableEggInfo").setValue(disableEggInfo);
		miscellaneous.getNode("disabledEggMessage").setValue(disabledEggMessage);
		
		save();
	}
	
	private static void load() {
		try {
			node = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void save() {
		try {
			loader.save(node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}