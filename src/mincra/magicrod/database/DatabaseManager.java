package mincra.magicrod.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;

import mincra.magicrod.api.MagicApi;
import mincra.magicrod.item.MagicMaterial;
import mincra.magicrod.main.Magic;
import mincra.magicrod.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DatabaseManager extends MagicApi{
	final public static String skillInventoryTitle = ChatColor.DARK_AQUA+""+ChatColor.BOLD+"スキル編集(Eキーで閉じる)";
	final public static String magicChestInventoryTitle = ChatColor.DARK_AQUA+""+ChatColor.BOLD+"魔法専用チェスト";
	final public static String giftBoxInventoryTitle = ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"ギフトボックス(受け取り専用)";
	static boolean use;

	public DatabaseManager(){
		use = Magic.config.getBoolean("skill.use");
		if(use){
		}else{
			Util.debug("スキル機能は無効化されています.");
		}
		use = Magic.config.getBoolean("magicchest.use");
		if(use){
		}else{
			Util.debug("魔法個人チェストは無効化されています.");
		}
	}
	/*
	private void insert(){
		new BukkitRunnable(){
			@Override
			public void run() {
				Connection conn = null;
				Statement stats = null;
				try {
					conn = ConnectionManager.getConnection();
					stats = conn.createStatement();
					final ResultSet rs = stats.executeQuery("SELECT id,job FROM magic_info");
					while(rs.next()){
							int user_id = rs.getInt(1);
							int job = rs.getInt(2);
							if(job == 1){
								addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.tauntLv1));
								addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.attack));
								addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.wall));
								addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.boost));
								addGiftBoxInventory(user_id, MagicMaterial.boost);
							}else if(job == 2){
								addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.cureLv1));
								addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.resurrection));
							}else if(job == 3){
								addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.blizzard));
								addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.thunder));
								addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.fire));
							}
							addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.walkSpeedLv1));
							addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.jumpLv1));
							Util.debug(user_id+","+"職業:"+job);
					}
				} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							if (stats != null)
								stats.close();
							conn.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
			}
		}.runTaskLater(Magic.plugin, 1);
	}*/

	public static String getSkillName(int user_id,int number){
		ItemStack item = getSkillInventory(user_id).getItem(number);
		if(item !=null){
			return item.getItemMeta().getDisplayName();
		}
		return null;
	}
	public static List<String> getSkill(int user_id,int number){
		ItemStack item = getSkillInventory(user_id).getItem(number);
		if(item !=null){
			List<String> lores = item.getItemMeta().getLore();
			return lores;
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static int getUserId(String name){
		Connection conn = null;
		Statement stats = null;
		try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT id FROM user WHERE name = '"+name+"'");
			if(rs.next()){
				return rs.getInt(1);
			}else{
				stats.executeUpdate("INSERT INTO user (name,uuid) VALUES ('"+name+"','"+Bukkit.getPlayer(name).getUniqueId()+"')");
				rs = stats.executeQuery("SELECT id FROM user WHERE name = '"+name+"'");
				rs.next();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,name+"のユーザーデータ取得に失敗しました.");
			e.printStackTrace();
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return -1;
	}
	public static int getUserId(UUID uuid){
		Connection conn = null;
		Statement stats = null;
		try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT id FROM user WHERE uuid = '"+uuid+"'");
			if(rs.next()){
				return rs.getInt(1);
			}else{
				stats.executeUpdate("INSERT INTO user (name,uuid) VALUES ('"+Bukkit.getPlayer(uuid).getName()+"','"+uuid+"')");
				rs = stats.executeQuery("SELECT id FROM user WHERE uuid = '"+uuid+"'");
				rs.next();
				Util.debug(Bukkit.getPlayer(uuid).getName()+"のユーザーデータを作成しました.");
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,Bukkit.getPlayer(uuid).getName()+"のユーザーデータ取得に失敗しました.");
			e.printStackTrace();
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return -1;
	}

	public static Inventory getSkillInventory(int user_id) {
		Connection conn = null;
		Statement stats = null;
		try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT skill FROM magic_info WHERE id = '"+user_id+"'");
			if(!rs.next()){
				UUID uuid = getUUID(user_id);
				createUser(uuid,user_id);
				rs = stats.executeQuery("SELECT skill FROM magic_info WHERE id = '"+user_id+"'");
				rs.next();
			}
			return StringToInventory(skillInventoryTitle, 9,rs.getString(1));
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,user_id+"番のスキルデータの作成に失敗しました.");
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	private static UUID getUUID(int user_id) {
		Connection conn = null;
		Statement stats = null;
		try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT uuid FROM user WHERE id = '"+user_id+"'");
			if(rs.next()){

				return UUID.fromString(rs.getString(1));
			}
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,user_id+"番のUUID取得に失敗しました.");
			e.printStackTrace();
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	public static Inventory getMagicChestInventory(int user_id) {
		Connection conn = null;
		Statement stats = null;
		try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT magicchest FROM magic_info WHERE id = '"+user_id+"'");
			if(!rs.next()){
				UUID uuid = getUUID(user_id);
				createUser(uuid,user_id);
				rs = stats.executeQuery("SELECT magicchest FROM magic_info WHERE id = '"+user_id+"'");
				rs.next();
			}
			return StringToInventory(magicChestInventoryTitle, 54,rs.getString(1));
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,user_id+"番の魔法チェストの作成に失敗しました.");
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	private static void createUser(UUID uuid,int user_id) {
		Player player = Bukkit.getPlayer(uuid);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getConnection();
			Inventory inv  = Bukkit.createInventory(null, 9, skillInventoryTitle);
			Inventory inv2  = Bukkit.createInventory(null, 54, magicChestInventoryTitle);
			Inventory inv3  = Bukkit.createInventory(null, 54, giftBoxInventoryTitle);
			//Calendar cal = Calendar.getInstance();
			//if(cal.get(Calendar.YEAR)==2015&&cal.get(Calendar.MONTH)+1==9&&cal.get(Calendar.DATE)<=8){
			inv.addItem(MagicMaterial.walkSpeedLv1);
			addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.walkSpeedLv1));
			Random r = new Random();
			int job = r.nextInt(3)+1;
			List<Integer> materialList = DatabaseManager.getMaterialHistory(user_id);
			//1 戦士
			//2 僧侶
			//3 魔法使い
			if(job==1){
				player.sendMessage(ChatColor.GRAY+"あなたは戦士クラスに選ばれました.");
				player.sendMessage(ChatColor.GREEN+"戦士クラスのマテリアルをスキルスロットに追加しました.");
				Bukkit.getServer().broadcastMessage(ChatColor.RED+""+ChatColor.BOLD+player.getName()+"さんは「戦士クラス」になりました.");
				if(!materialList.contains(3)){
					addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.tauntLv1));
					inv.addItem(MagicMaterial.tauntLv1);
				}
				if(!materialList.contains(9)){
					addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.attack));
					inv.addItem(MagicMaterial.attack);
				}
				if(!materialList.contains(8)){
					addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.wall));
					inv.addItem(MagicMaterial.wall);
				}
			}else if(job==2){
				player.sendMessage(ChatColor.GRAY+"あなたはプリーストクラスに選ばれました.");
				player.sendMessage(ChatColor.GRAY+"プリーストクラスのマテリアルをスキルスロットに追加しました.");
				Bukkit.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+player.getName()+"さんは「プリーストクラス」になりました.");
				if(!materialList.contains(5)){
					inv.addItem(MagicMaterial.cureLv1);
					addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.cureLv1));
				}
			}else if(job==3){
				player.sendMessage(ChatColor.GRAY+"あなたは魔術師クラスに選ばれました.");
				player.sendMessage(ChatColor.GRAY+"魔術師クラスのマテリアルをスキルスロットに追加しました.");
				Bukkit.getServer().broadcastMessage(ChatColor.GOLD+""+ChatColor.BOLD+player.getName()+"さんは「魔術師クラス」になりました.");
				if(!materialList.contains(1)){
					inv.addItem(MagicMaterial.blizzard);
					addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.blizzard));
				}
				if(!materialList.contains(4)){
					inv.addItem(MagicMaterial.thunder);
					addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.thunder));
				}
				if(!materialList.contains(2)){
					inv.addItem(MagicMaterial.fire);
					addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.fire));
				}
			}else if(job==4){
				player.sendMessage(ChatColor.GRAY+"あなたはアーチャークラスに選ばれました.");
				player.sendMessage(ChatColor.GRAY+"アーチャークラスのマテリアルをスキルスロットに追加しました.");
				Bukkit.getServer().broadcastMessage(ChatColor.YELLOW+""+ChatColor.BOLD+player.getName()+"さんは「アーチャークラス」になりました.");
				if(!materialList.contains(21)){
					inv.addItem(MagicMaterial.magicArrow);
					addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.magicArrow));
				}
			}
			String sql = "INSERT INTO magic_info (id,skill,magicchest,giftbox,job) VALUES ('"+user_id+"',?,?,?,'"+job+"')";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, InventoryToString(inv));
			stmt.setString(2, InventoryToString(inv2));
			stmt.setString(3, InventoryToString(inv3));
			stmt.execute();
			Util.debug(Bukkit.getPlayer(uuid).getName()+"のデータを作成しました.");
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,Bukkit.getPlayer(uuid).getName()+"のデータの作成に失敗しました.");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void saveSkillInventory(Inventory inventory,int user_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getConnection();
			String sql = "UPDATE magic_info SET skill = ? WHERE id = '"+user_id+"'";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, InventoryToString(inventory));
			stmt.executeUpdate();
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,user_id+"番のスキルデータの更新に失敗しました.");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	public static void saveMagicChestInventory(Inventory inventory,int user_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getConnection();
			String sql = "UPDATE magic_info SET magicchest = ? WHERE id = '"+user_id+"'";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, InventoryToString(inventory));
			stmt.executeUpdate();
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,user_id+"番のスキルデータの更新に失敗しました.");
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

    public static String getJob(int user_id){
    	Connection conn = null;
		Statement stats = null;
    	try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT job FROM magic_info WHERE id = '"+user_id+"'");
			if(rs.next()){
				int job_id = rs.getInt(1);
				ResultSet rs2 = stats.executeQuery("SELECT name FROM job WHERE id = '"+job_id+"'");
				if(rs2.next()){
					return rs2.getString(1);
				}
			}else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
    }

    public static MagicJob getMagicJob(int user_id){
    	Connection conn = null;
		Statement stats = null;
    	try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT job FROM magic_info WHERE id = '"+user_id+"'");
			if(rs.next()){
				return getMagicJob(rs.getInt(1));
			}else{
				return MagicJob.NONE;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
    	return MagicJob.NONE;
    }

    public static int getJobNumber(int user_id){
    	Connection conn = null;
		Statement stats = null;
    	try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT job FROM magic_info WHERE id = '"+user_id+"'");
			if(rs.next()){
				return rs.getInt(1);
			}else{
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return -1;
    }

    public static boolean setNotice(int user_id,Boolean bool){
    	Connection conn = null;
		Statement stats = null;
    	try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			stats.executeUpdate("UPDATE magic_info SET notice = "+bool.toString()+" WHERE id = '"+user_id+"'");
			return true;
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,user_id+"番のnoticeの更新に失敗しました.");
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
    }
    public static boolean setEnable(int user_id,Boolean bool){
    	Connection conn = null;
		Statement stats = null;
    	try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			stats.executeUpdate("UPDATE magic_info SET enable = "+bool.toString()+" WHERE id = '"+user_id+"'");
			return true;
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,user_id+"番のenableの更新に失敗しました.");
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
    }
    public static boolean getNotice(int user_id){
    	Connection conn = null;
		Statement stats = null;
    	try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT notice FROM magic_info WHERE id = '"+user_id+"'");
			if(rs.next()){
				return rs.getBoolean(1);
			}
			return true;
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,user_id+"番のnoticeの取得に失敗しました.");
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return true;
    }
    public static boolean getEnable(int user_id){
    	Connection conn = null;
		Statement stats = null;
    	try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT enable FROM magic_info WHERE id = '"+user_id+"'");
			if(rs.next()){
				return rs.getBoolean(1);
			}
			return true;
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,user_id+"番のenableの取得に失敗しました.");
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return true;
    }

	public static boolean replaceSkillSlot(int user_id,Player player) {
		Inventory inv = getSkillInventory(user_id);
		int count = 0;
		if(inv.getViewers().isEmpty()){
			ItemStack[] items = inv.getContents();
			for(int i=0;i<items.length;i++){
				if(items[i]!=null){
					List<String> lores = items[i].getItemMeta().getLore();
						String materialName = lores.get(0);
						int num = materialName.indexOf(":");
						ItemStack material = null;
						if(num!=-1){
							material = getMaterial(Integer.valueOf(materialName.substring(num+1)));
							if(!material.getItemMeta().getLore().equals(items[i].getItemMeta().getLore())){
								if(player.getOpenInventory().getTitle().equalsIgnoreCase(skillInventoryTitle)){
									player.sendMessage(ChatColor.GRAY+"スキルスロットの更新を行うために閉じました.");
									player.closeInventory();
								}
							inv.setItem(i, material);
							player.sendMessage(ChatColor.GRAY+material.getItemMeta().getDisplayName()+"マテリアルを更新しました.");
							count++;
							}
						}
				}
			}
		}
		if(count>0){
			saveSkillInventory(inv,user_id);
			return true;
		}
		return false;
	}

	public static void saveGiftBoxInventory(Inventory inventory,int user_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionManager.getConnection();
			String sql = "UPDATE magic_info SET giftbox = ? WHERE id = '"+user_id+"'";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, InventoryToString(inventory));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	public static Inventory getGiftBoxInventory(int user_id) {
		Connection conn = null;
		Statement stats = null;
		try {
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT giftbox FROM magic_info WHERE id = '"+user_id+"'");
			if(!rs.next()){
				UUID uuid = getUUID(user_id);
				createUser(uuid,user_id);
				rs = stats.executeQuery("SELECT giftbox FROM magic_info WHERE id = '"+user_id+"'");
				rs.next();
			}
			return StringToInventory(giftBoxInventoryTitle, 54,rs.getString(1));
		} catch (SQLException e) {
			Util.debug(ChatColor.YELLOW,user_id+"番のギフトボックスの取得に失敗しました.");
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	public static void addGiftBoxInventory(Player player,int user_id,ItemStack item) {
		if(player.getOpenInventory().getTitle().equalsIgnoreCase(giftBoxInventoryTitle)){
			player.sendMessage(ChatColor.GRAY+"ギフトボックスにアイテムが追加されたため閉じました.");
			player.closeInventory();
		}
		Inventory giftbox = getGiftBoxInventory(user_id);
		if(giftbox != null){
			giftbox.addItem(item);
			saveGiftBoxInventory(giftbox,user_id);
			player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, 1f);
			player.sendMessage(ChatColor.GRAY+"/giftbox: ギフトボックスを開く.");
			player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"ギフトボックスに何かが届いているようです.");
		}else{
			player.sendMessage(ChatColor.GRAY+"ギフトボックスを取得できませんでした.");
			Util.debug(ChatColor.YELLOW,"ギフトボックスを取得できませんでした.");
		}
	}
	public void addGiftBoxInventory(int user_id,ItemStack item) {
		if(user_id == -1) return;
		Inventory giftbox = getGiftBoxInventory(user_id);
		if(giftbox != null){
			giftbox.addItem(item);
			saveGiftBoxInventory(giftbox,user_id);
		}else{
			Util.debug(ChatColor.YELLOW,"ギフトボックスを取得できませんでした.");
		}
	}
	public static boolean hasMaterialHistory(int user_id,int material_id){
		Connection conn = null;
		Statement stats = null;
		try {
			if(user_id == -1) return false;
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT * FROM magic_history WHERE user_id = '"+user_id+"' AND material_id = '"+material_id+"'");
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}
	public static List<Integer> getMaterialHistory(int user_id){
		Connection conn = null;
		Statement stats = null;
		List<Integer> materialList = new ArrayList<Integer>();
		try {
			if(user_id == -1) return materialList;
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			ResultSet rs = stats.executeQuery("SELECT material_id FROM magic_history WHERE user_id = '"+user_id+"'");
			while(rs.next()){
				materialList.add(rs.getInt(1));
			}
			return materialList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return materialList;
	}
	public static void addMaterialHistory(int user_id,int material_id){
		Connection conn = null;
		Statement stats = null;
		try {
			if(user_id == -1) return;
			conn = ConnectionManager.getConnection();
			stats = conn.createStatement();
			stats.executeUpdate("INSERT INTO magic_history (user_id,material_id) VALUES ("+user_id+","+material_id+")");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stats != null)
					stats.close();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * Converts an Inventory into a serialized inventoryString for saving
	 * https://github.com/MrTwiggy/MachineFactory/blob/master/src/com/github/MrTwiggy/MachineFactory/Utility/InventoryStringDeSerializer.java
	 */
    @SuppressWarnings("deprecation")
	public static String InventoryToString (Inventory invInventory)
    {
        String serialization = invInventory.getSize() + ";";
        for (int i = 0; i < invInventory.getSize(); i++)
        {
            ItemStack is = invInventory.getItem(i);
            if (is != null)
            {
                String serializedItemStack = new String();

                String isType = String.valueOf(is.getType().getId());
                serializedItemStack += "::t@" + isType;

                if (is.getDurability() != 0)
                {
                    String isDurability = String.valueOf(is.getDurability());
                    serializedItemStack += "::d@" + isDurability;
                }

                if (is.getAmount() != 1)
                {
                    String isAmount = String.valueOf(is.getAmount());
                    serializedItemStack += "::a@" + isAmount;
                }

                Map<Enchantment,Integer> isEnch = is.getEnchantments();
                if (isEnch.size() > 0)
                {
                    for (Entry<Enchantment,Integer> ench : isEnch.entrySet())
                    {
                        serializedItemStack += "::e@" + ench.getKey().getId() + "@" + ench.getValue();
                    }
                }

                if (is.getItemMeta().getDisplayName() != null)
                {
                	 String[] itemDisplayName = is.getItemMeta().getDisplayName().split(" ");
                     serializedItemStack += "::n@";
                     for (int m = 0; m < itemDisplayName.length; m++)
                     {
                    	 serializedItemStack += itemDisplayName[m] + "=";
                     }
                }

                if (is.getItemMeta().hasLore())
                {
                	 List<String> itemLore = is.getItemMeta().getLore();
                     serializedItemStack += "::l@";
                     for (int m = 0; m < itemLore.size(); m++)
                     {
                    	 serializedItemStack += itemLore.get(m) + "=";
                     }
                }


                serialization += i + "#" + serializedItemStack + ";";
            }
        }
        return serialization;
    }

    /**
     * Converts a serialized inventoryString into an Inventory object
     */
    @SuppressWarnings("deprecation")
	public static Inventory StringToInventory (String invName,int num,String invString)
    {
        String[] serializedBlocks = invString.split(";");
        Inventory deserializedInventory = Bukkit.createInventory(null, num, invName);

        for (int i = 1; i < serializedBlocks.length; i++)
        {
            String[] serializedBlock = serializedBlocks[i].split("#");
            int stackPosition = Integer.valueOf(serializedBlock[0]);

            if (stackPosition >= deserializedInventory.getSize())
            {
                continue;
            }

            ItemStack is = null;
            Boolean createdItemStack = false;

            String[] serializedItemStack = serializedBlock[1].split("::");
            for (String itemInfo : serializedItemStack)
            {
                String[] itemAttribute = itemInfo.split("@");
                if (itemAttribute[0].equals("t"))
                {
                    is = new ItemStack(Material.getMaterial(Integer.valueOf(itemAttribute[1])));
                    createdItemStack = true;
                }
                else if (itemAttribute[0].equals("d") && createdItemStack)
                {
                    is.setDurability(Short.valueOf(itemAttribute[1]));
                }
                else if (itemAttribute[0].equals("a") && createdItemStack)
                {
                    is.setAmount(Integer.valueOf(itemAttribute[1]));
                }
                else if (itemAttribute[0].equals("e") && createdItemStack)
                {
                    is.addUnsafeEnchantment(Enchantment.getById(Integer.valueOf(itemAttribute[1])), Integer.valueOf(itemAttribute[2]));
                }
                else if (itemAttribute[0].equals("n") && createdItemStack)
                {
                	ItemMeta meta = is.getItemMeta();
                	String[] displayName = itemAttribute[1].split("=");
                	String finalName = "";


                	for (int m = 0; m < displayName.length; m++)
                	{
                		if (m == displayName.length - 1)
                			finalName += displayName[m];
                		else
                			finalName += displayName[m] + " ";
                	}
                	meta.setDisplayName(finalName);
                	is.setItemMeta(meta);
                }else if (itemAttribute[0].equals("l") && createdItemStack)
                {
                	ItemMeta meta = is.getItemMeta();
                	String[] lore = itemAttribute[1].split("=");
                	List<String> lores = new ArrayList<String>();

                	for (int m = 0; m < lore.length; m++)
                	{
                		lores.add(lore[m]);
                	}
                	meta.setLore(lores);
                	is.setItemMeta(meta);
                }
            }
            deserializedInventory.setItem(stackPosition, is);
        }

        return deserializedInventory;
    }
}
