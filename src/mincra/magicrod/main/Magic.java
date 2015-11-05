package mincra.magicrod.main;

import java.io.File;
import java.io.IOException;

import mincra.magicrod.cmd.GiftBoxCommands;
import mincra.magicrod.cmd.MagicChestCommands;
import mincra.magicrod.cmd.MincraCommands;
import mincra.magicrod.cmd.SkillCommands;
import mincra.magicrod.database.ConnectionManager;
import mincra.magicrod.database.DatabaseManager;
import mincra.magicrod.item.MagicItem;
import mincra.magicrod.item.MagicMaterial;
import mincra.magicrod.item.MagicRod;
import mincra.magicrod.item.MagicWeapon;
import mincra.magicrod.listener.AnvilListener;
import mincra.magicrod.listener.ArrowListener;
import mincra.magicrod.listener.GrassDropListener;
import mincra.magicrod.listener.CraftListener;
import mincra.magicrod.listener.CustomDropListener;
import mincra.magicrod.listener.DeathListener;
import mincra.magicrod.listener.InventoryListener;
import mincra.magicrod.listener.JoinListener;
import mincra.magicrod.listener.LightListener;
import mincra.magicrod.listener.MagicItemUse;
import mincra.magicrod.listener.EntityDamageListener;
import mincra.magicrod.listener.PlayerAchievementAwardedListener;
import mincra.magicrod.listener.PotionListener;
import mincra.magicrod.listener.SignListener;
import mincra.magicrod.listener.SoundPacketListener;
import mincra.magicrod.listener.SpawnListener;
import mincra.magicrod.listener.WeaponListener;
import mincra.magicrod.rod.Aice;
import mincra.magicrod.version.Version;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Magic extends JavaPlugin{
	public static Magic plugin;
	public static Location[] ice=new Location[25];
	public static Location[] ice_2=new Location[25];
	private File configFile,tradefile,tradefile_gold;
	public static FileConfiguration config;
	public static YamlConfiguration trade,trade_gold;
	public void onDisable(){

	}
	public void onEnable(){
		plugin=this;

		this.saveDefaultConfig();
		configFile= new File(getDataFolder(),"config.yml");
		config =this.getConfig();
		try {
			config.load(this.configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//trade.yml作成
	    tradefile = new File(getDataFolder(), "trade.yml");
	    trade = new YamlConfiguration();
	    if(!tradefile.exists()){
	    	Bukkit.getLogger().info("[MincraMagicRod] "+"trade.ymlを生成しました.");
	    	saveResource("trade.yml", true);
	    }
	    tradefile = new File(getDataFolder(), "trade.yml");
	    try {
			trade.load(tradefile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}

		//trade2作成
	    tradefile_gold = new File(getDataFolder(), "trade2.yml");
	    trade_gold = new YamlConfiguration();
	    if(!tradefile_gold.exists()){
	    	Bukkit.getLogger().info("[MincraMagicRod] "+"trade2.ymlを生成しました.");
	    	saveResource("trade2.yml", true);
	    }
	    tradefile_gold = new File(getDataFolder(), "trade2.yml");
	    try {
			trade_gold.load(tradefile_gold);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}

		new MagicItem(plugin);
		new MagicWeapon(plugin);
		new MagicRod(plugin);
		new MagicMaterial(plugin);

		try {
			new Version();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}

		getServer().getPluginManager().registerEvents(new EntityDamageListener(this), this);
		getServer().getPluginManager().registerEvents(new MagicItemUse(this), this);
		getServer().getPluginManager().registerEvents(new PotionListener(this), this);
		getServer().getPluginManager().registerEvents(new WeaponListener(this), this);
		getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
		getServer().getPluginManager().registerEvents(new GrassDropListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerAchievementAwardedListener(), this);
		getServer().getPluginManager().registerEvents(new CustomDropListener(), this);
		//魔法MOBの生成
		getServer().getPluginManager().registerEvents(new SpawnListener(this), this);
		//最大体力を40に設定する,実績系
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		//看板の監視
		getServer().getPluginManager().registerEvents(new SignListener(), this);
		//接続管理の起動,ユーザーテーブルのチェック
		new ConnectionManager();
		//スキルセットの起動
		new DatabaseManager();
		//new MagicChest();
		//雷の音を消す.仕様:8192以上のボリュームのサウンドをキャンセルする.
		new SoundPacketListener(this);

		getServer().getPluginManager().registerEvents(new LightListener(), this);
		getServer().getPluginManager().registerEvents(new CraftListener(this), this);
		getServer().getPluginManager().registerEvents(new ArrowListener(this), this);
		getServer().getPluginManager().registerEvents(new DeathListener(this), this);
		getServer().getPluginManager().registerEvents(new AnvilListener(), this);
		getCommand("mincramagicrod").setExecutor(new MincraCommands());
		getCommand("skill").setExecutor(new SkillCommands());
		getCommand("magicchest").setExecutor(new MagicChestCommands(this));
		getCommand("giftbox").setExecutor(new GiftBoxCommands());

		Aice ai=new Aice();
		ai.load();
	}
}
