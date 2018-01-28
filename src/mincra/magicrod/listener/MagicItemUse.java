package mincra.magicrod.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.inventivetalent.bossbar.BossBarAPI;

import mincra.magicrod.bar.Bar;
import mincra.magicrod.database.DatabaseManager;
import mincra.magicrod.item.MagicItem;
import mincra.magicrod.main.Magic;
import mincra.magicrod.rod.AiceRod;
import mincra.magicrod.rod.AiceRod_3;
import mincra.magicrod.rod.AiceRod_4;
import mincra.magicrod.rod.Barrier;
import mincra.magicrod.rod.Barrier_2;
import mincra.magicrod.rod.Barrier_3;
import mincra.magicrod.rod.DestroyRod;
import mincra.magicrod.rod.DestroyRod_2;
import mincra.magicrod.rod.DestroyRod_3;
import mincra.magicrod.rod.DestroyRod_4;
import mincra.magicrod.rod.DestroyRod_5;
import mincra.magicrod.rod.ExpRod;
import mincra.magicrod.rod.ExpRod_2;
import mincra.magicrod.rod.ExpRod_3;
import mincra.magicrod.rod.InfernoRod;
import mincra.magicrod.rod.InfernoRod_2;
import mincra.magicrod.rod.InfernoRod_3;
import mincra.magicrod.rod.JampRod;
import mincra.magicrod.rod.JampRod_2;
import mincra.magicrod.rod.JampRod_3;
import mincra.magicrod.rod.Lightning;
import mincra.magicrod.rod.MineCart_1;
import mincra.magicrod.rod.MoveRod;
import mincra.magicrod.rod.MoveRod_2;
import mincra.magicrod.rod.MoveRod_3;
import mincra.magicrod.rod.QureRod;
import mincra.magicrod.rod.QureRod_2;
import mincra.magicrod.rod.QureRod_3;
import mincra.magicrod.rod.Spawn_Rod;
import mincra.magicrod.rod.Spawn_Rod_2;
import mincra.magicrod.rod.Spawn_Rod_3;
import mincra.magicrod.rod.Water_Rod;
import mincra.magicrod.skill.Skill;
import mincra.magicrod.util.Util;
import mincra.magicrod.version.Version;

public class MagicItemUse extends Skill implements Listener {
	public static Magic plugin;
	@SuppressWarnings("static-access")
	public MagicItemUse(Magic plugin) {
		super(plugin);
	    this.plugin = plugin;
	}
	//プレイヤーがプレイヤーをクリックするイベント.
	@EventHandler
	public void PlayerInteractEntityEvent(PlayerInteractEntityEvent event){
		if(event.getPlayer().getInventory().getItemInMainHand()!=null&&
		event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()&&
		event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()){
			Player player=event.getPlayer();
			ItemStack item = player.getInventory().getItemInMainHand();
			String lore = item.getItemMeta().getLore().get(0);
			lore = ChatColor.stripColor(lore);
			int num = lore.indexOf(":");

			if(num == -1)
				return;

			switch(lore.substring(0, num)){
				case"ROD番号":
					if(item.getType().equals(Material.BLAZE_ROD)||item.getType().equals(Material.ENCHANTED_BOOK)){
						if(BossBarAPI.hasBar(player)==false&&event.getRightClicked() instanceof Player){
							Player player2=(Player) event.getRightClicked();
							String number = lore.substring(lore.indexOf(":")+1);
							if(player.hasPermission("mincra.admin.debug")){
								player.sendMessage(ChatColor.GRAY+"デバッグ01");
							}
							switch(number){
								case "4-1":
									rod401(player,player2,item);
									break;
								case "4-2":
							    //破壊の杖lv2
							    	rod402(player,player2,item);
							    	break;
								case "4-3":
							    //破壊の杖lv3
							    	rod403(player,player2,item);
							    	break;
								case "8-1":
							    //結界の杖lv1
							    	rod801(player,player2,item);
							    	break;
								case "8-2":
								//結界の杖lv2
								    rod802(player,player2,item);
								    break;
							    default:
							    	break;
							}
						}
					}
					break;
				default:
					break;
			}
		}
	}
	@EventHandler
	public void onPlayerInteractBlock(PlayerInteractEvent event) {
		if(event.getAction().equals(Action.LEFT_CLICK_AIR)||event.getAction().equals(Action.LEFT_CLICK_BLOCK)&&event.getItem()==null){
			if(!(event.getPlayer().isSneaking()&&!BossBarAPI.hasBar(event.getPlayer())))
				return;

			final Player player = event.getPlayer();

			int user_id = DatabaseManager.getUserId(player.getUniqueId());
			if(user_id == -1)
				return;

			if(!DatabaseManager.getEnable(user_id))
				return;

			List<String> lores = DatabaseManager.getSkill(user_id, player.getInventory().getHeldItemSlot());

			if(lores==null)
				return;

			String materialName = lores.get(0);
			Float coolTime=Float.valueOf(lores.get(2).substring(2, 6));
			//MPの消費の計算 %か、数値か
			String lores3 = lores.get(3).substring(2, 6);
			int index;
			if((index=lores3.indexOf('%'))!=-1){
				//MPを消費
					int setLevel = player.getLevel();
					float setExp = player.getExp();
					float needPer = Float.valueOf(lores3.substring(0, index)) * 0.01f;
					if(needPer<=player.getExp()){
						setExp = player.getExp() - needPer;
					}else{
						while(needPer>0f){
							if(setLevel<=0){
								player.sendMessage(ChatColor.GRAY+"MPが足りない!");
								return;
							}
							if(needPer<1f){
								setExp = 1f - needPer;
							}
							needPer = needPer - 1f;
							setLevel--;
						}
					}
				player.setLevel(setLevel);
				player.setExp(setExp);
			}else{
				float useLevel = Float.valueOf(lores.get(3).substring(2, 6));
				if(player.getLevel()<useLevel){
					player.sendMessage(ChatColor.GRAY+"MPが足りない!");
					return;
				}
				player.setLevel((int) (player.getLevel()-useLevel));
			}
			int num = materialName.indexOf(":");
			if(num!=-1){
				switch(Integer.valueOf(materialName.substring(num+1))){
					case 1:
						blizardLv1(player,coolTime);
						break;
					case 2:
						fireLv1(player,coolTime);
						break;
					case 3:
						tauntLv1(player,coolTime);
						break;
					case 4:
						thunderLv1(player,coolTime);
						break;
					case 5:
						healLv1(player,coolTime);
				    	break;
					case 6:
						walkSpeedLv1(player,coolTime);
				    	break;
					case 7:
						jumpLv1(player,coolTime);
				    	break;
					case 8:
						wallLv1(player,coolTime);
				    	break;
					case 9:
						attackLv1(player,coolTime);
				    	break;
					case 10:
						resurrectionLv1(player,coolTime);
				    	break;
					case 11:
						boostLv1(player,coolTime);
				    	break;
					case 12:
						healLv2(player,coolTime);
				    	break;
					case 13:
						tauntLv2(player,coolTime);
						break;
					case 14:
						jumpLv2(player,coolTime);
				    	break;
					case 15:
						walkSpeedLv2(player,coolTime);
				    	break;
					case 16:
						holyLv1(player,coolTime);
				    	break;
					case 17:
						holyLv2(player,coolTime);
				    	break;
					case 18:
						gravityLv1(player,coolTime);
				    	break;
					case 19:
						untigravityLv1(player,coolTime);
				    	break;
					case 20:
						villagerLv1(player,coolTime);
				    	break;
					case 21:
						magicArrowLv1(player,coolTime);
				    	break;
					case 22:
						directionLv1(player,coolTime);
				    	break;
					case 23:
						devideLv1(player,coolTime);
				    	break;
					case 24:
						chargeLv1(player,coolTime);
				    	break;
					case 25:
						invisiblehandsLv1(player,coolTime);
				    	break;
					case 26:
						invisiblehandsLv2(player,coolTime);
				    	break;
					case 27:
						invisiblehandsLv3(player,coolTime);
				    	break;
					default:
						break;
				}
			}
		}else if((event.getAction().equals(Action.RIGHT_CLICK_AIR)||event.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
			final Player player = event.getPlayer();
			if(player.getInventory().getItemInMainHand().getType()!=Material.AIR&&player.getInventory().getItemInMainHand().getItemMeta().hasLore()==true){
				if(player.hasPermission("mincra.admin.debug")){
					player.sendMessage(ChatColor.GRAY+"デバッグ02");
				}
				String lore =  player.getInventory().getItemInMainHand().getItemMeta().getLore().get(0);
				lore = ChatColor.stripColor(lore);
				if(player.hasPermission("mincra.admin.debug")){
					player.sendMessage(ChatColor.GRAY+lore);
				}
				int num = lore.indexOf(":");
				if(num!=-1){
				switch(lore.substring(0, num)){
				case"ROD番号":
					if(BossBarAPI.hasBar(player)==false){
						ItemStack item=player.getInventory().getItemInMainHand();
						switch(item.getType()){
							case BLAZE_ROD:
								switch(lore.substring(lore.indexOf(":")+1)){
									//エクスペlv1
									case "1-1":
										expeRod_101(player,item);
										break;
									//エクスペlv2
									case "1-2":
										expeRod_102(player,item);
										break;
									//エクスペlv3
									case "1-3":
										expeRod_103(player,item);
										break;
									//青水晶の杖
									case "2-1":
										jumpRod_201(player,item);
										break;
									//蒼水晶の杖lv2
									case "2-2":
										jumpRod_202(player,item);
										break;
									//蒼水晶の杖lv3
									case "2-3":
										jumpRod_203(player,item);
										break;
									//インフェルノ杖lv1
									case "3-1":
										infernoRod_301(player,item);
										break;
									//インフェルノ杖lv2
									case "3-2":
										infernoRod_302(player,item);
										break;
									//インフェルノ杖lv3
									case "3-3":
										infernoRod_303(player,item);
										break;
									//癒しの杖lv1
									case "5-1":
										cureRod_501(player,item);
										break;
									//癒しの杖lv2
									case "5-2":
										cureRod_502(player,item);
										break;
									//癒しの杖lv3
									case "5-3":
										cureRod_503(player,item);
										break;
									//翠水晶の杖lv1
									case "6-1":
										rod601(player,item);
										break;
									//翠水晶の杖lv2
									case "6-2":
										rod602(player,item);
										break;
									//翠水晶の杖lv3
									case "6-3":
										rod603(player,item);
										break;
									//大氷樹の杖lv1
									case "7-1":
										rod701(player,item);
										break;
									//大氷樹の杖lv2
									case "7-2":
										rod702(player,item);
										break;
									//水杖
									case "9-1":
								    	waterRod_901(player,item);
									default:
										break;
								}
								//よくわからないのでコメントアウト
								//if(event.getItem().getDurability()!=0){
								//	player.chat("/mmr ikou");
								//}
								if(player.hasPermission("mincra.admin.debug")){
									player.sendMessage(ChatColor.GRAY+"デバッグ04");
								}
								break;
							case ENCHANTED_BOOK:
								switch(lore.substring(lore.indexOf(":")+1)){
									//破壊の書lv4
									case "4-4":
										rod404(player,item);
										break;
									case "7-3":
										//大氷樹の書lv3
									    rod703(player,item);
									    break;
									case "7-4":
										//大氷樹の書lv4
									    rod704(player,item);
									    break;
									case "8-3":
										//結界の書lv3
									    rod803(player,item);
									    break;
									case "10-1":
										//召還獣の書lv1
									    rod1001(player,item);
									    break;
									case "10-2":
									    //召還獣の書lv2
									    rod1002(player,item);
									    break;
									case "10-3":
									    //召還獣の書lv3
										rod1003(player,item);
										break;
									case "11-1":
									   //トロッコブースターの書lv1
									   rod1101(player,item);
									   break;
									case "12-1":
									   //白雷の書lv1
									   lightningBook(player,item);
									   break;
									default:
										break;
								}
								//if(event.getItem().getDurability()!=0){
								//	player.chat("/mmr ikou");
								//}
								break;
							case DIAMOND_PICKAXE:
								switch(lore.substring(lore.indexOf(":")+1)){
									case "4-5":
								    	rod405(player,item);
										break;
									default:
										break;
								}
								break;
							default:
								break;
						}
					}
					break;
				case"魔法武器番号":
					if(BossBarAPI.hasBar(player)==false){
						ItemStack item=player.getInventory().getItemInMainHand();
						switch(lore.substring(lore.indexOf(":")+1)){
							case"6":
								mechanics(player,item,0.25F);
								break;
							case"9":
								mechanics(player,item,1F);
								break;
							default:
								break;
						}
					}
					break;
				case"魔法アイテム番号":
					switch(lore.substring(lore.indexOf(":")+1)){
						case "5":
							player.sendMessage(ChatColor.AQUA+""+ChatColor.BOLD+"魔法引換券です.様々なアイテムと交換することが可能です.");
							player.sendMessage(ChatColor.AQUA+""+ChatColor.BOLD+"/trade:アイテムを交換する.");
							if(event.getItem().getDurability()!=0){
								player.chat("/mmr ikou");
							}else{
								if(!event.getItem().getItemMeta().getLore().equals(MagicItem.MagicTicket_lore)){
									player.chat("/mmr updatelore");
								}
							}
							break;
						case "5-2":
							player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"魔法引換券です.様々なアイテムと交換することが可能です.");
							player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"/trade2:アイテムを交換する.");
							if(event.getItem().getDurability()!=0){
								player.chat("/mmr ikou");
							}
							break;
						case "6":
							if(player.getInventory().getItemInMainHand().equals(MagicItem.magicWeed)){
								if(player.getLevel() <= 50){
									player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"魔法草を使用しました.MPが1回復しました.");
									player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
									player.giveExpLevels(1);
								}else{
									player.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Orrrrrrrr.....ゲホッ...");
								}
								ItemStack is = MagicItem.magicWeed;
		    					is.setAmount(1);
		    					player.getInventory().removeItem(is);
							}
							break;
						default:
							break;
					}
					break;
				default:
					break;
				}
				}
				return;
				//空気を所持時の右クリックイベントは出ないので,左クリックのみ
			}else  if(event.getPlayer().isSneaking()){
				new BukkitRunnable(){
					@Override
					public void run() {
						int user_id = DatabaseManager.getUserId(player.getUniqueId());

						if(user_id == -1)
							return;

						if(!DatabaseManager.getNotice(user_id))
							return;
						String name = DatabaseManager.getSkillName(user_id, player.getInventory().getHeldItemSlot());
						if(name==null){
							player.sendMessage(ChatColor.GRAY+"/skill notice off: この通知を受け取らない.");
							player.sendMessage(ChatColor.GRAY+"/skill: スキルスロットを表示する.");
							player.sendMessage(ChatColor.GRAY+String.valueOf(player.getInventory().getHeldItemSlot()+1)+"番目のスキルスロットには何も登録されていません.");
						}else{
							player.sendMessage(name+ChatColor.GRAY+"がセットされています.");
						}
					}
				}.runTaskAsynchronously(plugin);
			}
		}else{
			return;
		}
	}
	private void invisiblehandsLv3(final Player player,float cooltime) {
		Util.debug(player.getName()+"が見えざる手Lv3を発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.DARK_GRAY+""+ChatColor.BOLD+"見えざる手Lv3"+ChatColor.RESET+"]クールタイム",cooltime);
		super.invisiblehands(player,32,16,32,30,200,5);
	}
	private void invisiblehandsLv2(final Player player,float cooltime) {
		Util.debug(player.getName()+"が見えざる手Lv2を発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.DARK_GRAY+""+ChatColor.BOLD+"見えざる手Lv2"+ChatColor.RESET+"]クールタイム",cooltime);
		super.invisiblehands(player,16,8,16,15,100,4);
	}
	private void invisiblehandsLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"が見えざる手Lv1を発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.DARK_GRAY+""+ChatColor.BOLD+"見えざる手Lv1"+ChatColor.RESET+"]クールタイム",cooltime);
		super.invisiblehands(player,8,8,8,10,100,2);
	}
	private void chargeLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"がチャージを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"チャージ"+ChatColor.RESET+"]クールタイム",cooltime);
		super.chargeLv1(player);
	}

	private void devideLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"がディバイドを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.YELLOW+""+ChatColor.BOLD+"ディバイド"+ChatColor.RESET+"]クールタイム",cooltime);
		super.devideLv1(player);
	}
	private void directionLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"がディレクションを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.GREEN+""+ChatColor.BOLD+"ディレクション"+ChatColor.RESET+"]クールタイム",cooltime);
		super.disableArrow(player, 2400);
	}
	private void magicArrowLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"がマジックアローを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.BOLD+""+ChatColor.BOLD+"マジックアロー"+ChatColor.RESET+"]クールタイム",cooltime);
		super.magicArrowLv1(player);
	}
	private void villagerLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"がむらびとを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.GOLD+""+ChatColor.BOLD+"むらびと"+ChatColor.RESET+"]クールタイム",cooltime);
		super.villagerLv1(player);
	}
	private void untigravityLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"がアンチグラビティを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.DARK_GRAY+""+ChatColor.BOLD+"アンチグラビティ"+ChatColor.RESET+"]クールタイム",cooltime);
		super.untigravityLv1(player,32,16,32,5,80);
	}
	private void gravityLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"がグラビティを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.DARK_GRAY+""+ChatColor.BOLD+"グラビティ"+ChatColor.RESET+"]クールタイム",cooltime);
		super.gravityLv1(player,32,16,32,5,80);
	}
	private void holyLv2(final Player player,float cooltime) {
		Util.debug(player.getName()+"がホーリラを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.WHITE+""+ChatColor.BOLD+"ホーリラ"+ChatColor.RESET+"]クールタイム",cooltime);
		super.holyWave(player,22,10,22,24);
	}
	private void holyLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"がホーリーを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.WHITE+""+ChatColor.BOLD+"ホーリー"+ChatColor.RESET+"]クールタイム",cooltime);
		super.holy(player);
	}
	private void boostLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"がブーストを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.GOLD+""+ChatColor.BOLD+"ブースト"+ChatColor.RESET+"]クールタイム",cooltime);
		super.boost(player);
	}
	private void resurrectionLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"がリザレクションを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.WHITE+""+ChatColor.BOLD+"リザレクション"+ChatColor.RESET+"]クールタイム",cooltime);
		super.ressurection(player,16,8,16,5,6000);
	}
	private void attackLv1(Player player,float cooltime) {
		Util.debug(player.getName()+"がバーストを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.WHITE+""+ChatColor.BOLD+"バースト"+ChatColor.RESET+"]クールタイム",cooltime);
		player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 1);
		player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6000, 1));
	}
	private void wallLv1(Player player,float cooltime) {
		Util.debug(player.getName()+"がプロテクトを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.WHITE+""+ChatColor.BOLD+"プロテクト"+ChatColor.RESET+"]クールタイム",cooltime);
		player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 1);
		player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 6000, 1));
	}
	private void jumpLv1(Player player,float cooltime) {
		Util.debug(player.getName()+"が青水晶を発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.AQUA+""+ChatColor.BOLD+"青水晶"+ChatColor.RESET+"]クールタイム",cooltime);
		player.setFallDistance(-6F);
    	player.setVelocity(player.getVelocity().add(new Vector(0,1.2f,0)));
    	player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1f, 1f);
	}
	private void jumpLv2(Player player,float cooltime) {
		Util.debug(player.getName()+"が蒼水晶を発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.BLUE+""+ChatColor.BOLD+"蒼水晶"+ChatColor.RESET+"]クールタイム",cooltime);
		player.setFallDistance(-12F);
		player.setVelocity(player.getVelocity().add(new Vector(0,2.4f,0)));
		player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1f, 1f);
	}
	private void walkSpeedLv1(Player player,float cooltime) {
		Util.debug(player.getName()+"が緑水晶を発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.GREEN+""+ChatColor.BOLD+"緑水晶"+ChatColor.RESET+"]クールタイム",cooltime);
		player.removePotionEffect(PotionEffectType.SPEED);
		if(!player.getWorld().getName().equalsIgnoreCase("world")){
	    	player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15*20, 15));
	    	player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.3f, 1f);
		}else{
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15*20, 2));
	    	player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 1f);
		}
	}
	private void walkSpeedLv2(Player player,float cooltime) {
		Util.debug(player.getName()+"が翠水晶を発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.GREEN+""+ChatColor.BOLD+"翠水晶"+ChatColor.RESET+"]クールタイム",cooltime);
		player.removePotionEffect(PotionEffectType.SPEED);
		if(!player.getWorld().getName().equalsIgnoreCase("world")){
	    	player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (int) (20*cooltime), 30));
	    	player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 0.3f, 1f);
		}else{
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15*20, 2));
	    	player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 1f);
		}
	}
	private void healLv2(Player player,float cooltime) {
		Util.debug(player.getName()+"がキュアルを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.GREEN+""+ChatColor.BOLD+"キュアル"+ChatColor.RESET+"]クールタイム",cooltime);
	    Version.playeffect(player.getLocation(),"VILLAGER_HAPPY");
    	Version.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP, 1F, 1.2F);
    	super.heal(player, 16, 8, 16, 5, 8);
	}

	private void healLv1(Player player,float cooltime) {
		Util.debug(player.getName()+"がキュアを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.GREEN+""+ChatColor.BOLD+"キュア"+ChatColor.RESET+"]クールタイム",cooltime);
	    Version.playeffect(player.getLocation(),"VILLAGER_HAPPY");
    	Version.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP, 1F, 1.2F);
    	super.heal(player, 16, 8, 16, 5, 4);
	}
	private void thunderLv1(Player player,float cooltime) {
		Util.debug(player.getName()+"がサンダーを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.YELLOW+""+ChatColor.BOLD+"サンダー"+ChatColor.RESET+"]クールタイム",cooltime);
	    Version.playeffect(player.getLocation().add(0, -1.2, 0),"VILLAGER_ANGRY");
		super.thunder(player, 16, 8, 16, 12);
	}
	private void tauntLv2(final Player player,float cooltime) {
		Util.debug(player.getName()+"が挑発Lv2を発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.RED+""+ChatColor.BOLD+"挑発Lv2"+ChatColor.RESET+"]クールタイム",cooltime);
	    Version.playeffect(player.getLocation(),"SPELL_INSTANT");
	    Version.playSound(player.getLocation(),Sound.ENTITY_ZOMBIE_INFECT, 10F, 2F);
	    super.disableArrow(player,600);
    	super.taunt(player, 32, 24, 32, 24);
	}
	private void tauntLv1(Player player,float cooltime) {
		Util.debug(player.getName()+"が挑発Lv1を発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.RED+""+ChatColor.BOLD+"挑発Lv1"+ChatColor.RESET+"]クールタイム",cooltime);
	    Version.playeffect(player.getLocation(),"SPELL_INSTANT");
	    Version.playSound(player.getLocation(),Sound.ENTITY_ZOMBIE_INFECT, 10F, 2F);
    	super.taunt(player, 32, 24, 32, 12);
	}
	private void fireLv1(Player player,float cooltime) {
		Util.debug(player.getName()+"がファイアを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.RED+""+ChatColor.BOLD+"ファイア"+ChatColor.RESET+"]クールタイム",cooltime);
	    Version.playeffect(player.getLocation(),"FLAME");
    	Version.playSound(player.getLocation(),Sound.ENTITY_ZOMBIE_INFECT, 10F, 1F);
    	super.fire(player, 16, 8, 16, 6, 300);
	}
	private void blizardLv1(final Player player,float cooltime) {
		Util.debug(player.getName()+"がブリザードを発動");
		if(cooltime>0)
			new Bar(player,"["+ChatColor.AQUA+""+ChatColor.BOLD+"ブリザード"+ChatColor.RESET+"]クールタイム",cooltime);
	    Version.playeffect(player.getLocation(),"SNOWBALL");
    	Version.playSound(player.getLocation(),Sound.BLOCK_GLASS_BREAK, 3F, 1F);
		super.blizard(player,8,8,8,4,100);
	}

	private Boolean expeRod_101(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"ENCHANTMENT_TABLE");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 4F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1F, 1F);
	    		player.updateInventory();
	    	}
	    	new ExpRod(player).runTaskLater(plugin, 40);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}

	private Boolean expeRod_102(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"ENCHANTMENT_TABLE");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 4F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new ExpRod_2(player).runTaskLater(plugin, 80);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean expeRod_103(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"ENCHANTMENT_TABLE");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 4F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new ExpRod_3(player).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean jumpRod_201(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"REDSTONE");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.01F, 4F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new JampRod(player).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean jumpRod_202(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"CLOUD");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 4F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new JampRod_2(player).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean jumpRod_203(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"CLOUD");
    		Version.playeffect(player.getLocation(),"SPELL_INSTANT");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 4F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new JampRod_3(player).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean infernoRod_301(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"FLAME");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 4F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new InfernoRod(player).runTaskLater(plugin, 5);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean infernoRod_302(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"FLAME");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 4F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new InfernoRod_2(player).runTaskLater(plugin, 5);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean infernoRod_303(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"FLAME");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 4F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new InfernoRod_3(player).runTaskLater(plugin, 5);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod401(Player player,Player player2,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	int hlv=Integer.valueOf(list.get(4).substring(2, 3));
    	Version.playSound(player.getLocation(),Sound.BLOCK_ANVIL_USE, 0.1F, 1F);
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	Version.playeffect(player.getLocation(),"VILLAGER_HAPPY");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.01F, 10F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 3));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new DestroyRod(player,player2).runTaskLater(plugin, 5);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 5));
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod402(Player player,Player player2,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	int hlv=Integer.valueOf(list.get(4).substring(2, 3));
    	Version.playSound(player.getLocation(),Sound.BLOCK_ANVIL_USE, 0.1F, 1F);
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"VILLAGER_HAPPY");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.01F, 10F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 3));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new DestroyRod_2(player,player2).runTaskLater(plugin, 5);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 5));
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod403(Player player,Player player2,ItemStack item){
		List<String> list=new ArrayList<String>();
		list=item.getItemMeta().getLore();
		//MP消費
		int hlv=Integer.valueOf(list.get(4).substring(2, 3));
		Version.playSound(player.getLocation(),Sound.BLOCK_ANVIL_USE, 0.1F, 1F);
		if(hlv==0||player.getLevel()>=hlv){
			player.setLevel(player.getLevel()-hlv);
			Version.playeffect(player.getLocation(),"VILLAGER_HAPPY");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.01F, 10F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 3));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new DestroyRod_3(player,player2).runTaskLater(plugin, 5);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 4));
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
		}else{
			player.sendMessage("私のMP低すぎっ!?");
			return false;
		}
	}
	private Boolean rod404(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	Version.playSound(player.getLocation(),Sound.BLOCK_ANVIL_USE, 0.1F, 1F);
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"VILLAGER_HAPPY");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.01F, 10F);
	    	player.setFoodLevel(0);
	    	player.setHealth(1);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"書物が破れてしまいました。");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new DestroyRod_4(player).runTaskLater(plugin, 40);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod405(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	Version.playSound(player.getLocation(),Sound.BLOCK_ANVIL_USE, 0.1F, 1F);
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playeffect(player.getLocation(),"VILLAGER_HAPPY");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.01F, 10F);
	    	player.setFoodLevel(0);
	    	player.setHealth(1);
	    	String st2=list.get(5);
	    	item.setDurability((short) (item.getDurability()+25));
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"ピッケルが壊れてしまいました。");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new DestroyRod_5(player).runTaskLater(plugin, 40);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean cureRod_501(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	Version.playeffect(player.getLocation(),"VILLAGER_HAPPY");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.01F, 10F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 3));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new QureRod(player).runTaskLater(plugin, 40);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean cureRod_502(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	Version.playeffect(player.getLocation(),"VILLAGER_HAPPY");
	    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.01F, 10F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 3));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new QureRod_2(player).runTaskLater(plugin, 40);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean cureRod_503(Player player,ItemStack item){
		 List<String> list=new ArrayList<String>();
	    	list=item.getItemMeta().getLore();
	    	//MP消費
	    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
	    	int hlv=(int) hlv2;
	    	if(hlv==0||player.getLevel()>=hlv){
	    		player.setLevel(player.getLevel()-hlv);
		    	Version.playeffect(player.getLocation(),"VILLAGER_HAPPY");
		    	Version.playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.01F, 10F);
		    	String st2=list.get(5);
		    	//破壊確率
		    	Float f=Float.valueOf(st2.substring(2, 3));
		    	int b=(int) (100/f);
		    	Random r = new Random();
		    	if(r.nextInt(b)==1){
					if(item.getAmount()!=1){
						int amount=item.getAmount()-1;
						item.setAmount(amount);
					}else{
						player.getInventory().removeItem(item);
					}
		    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
		    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
		    		player.updateInventory();
		    	}
		    	new QureRod_3(player).runTaskLater(plugin, 40);
		    	String st=list.get(3);
		    	//クールタイム
		    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
		    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
		    	return true;
	    	}else{
	    		player.sendMessage("私のMP低すぎっ!?");
	    		return false;
	    	}
	}
	private Boolean rod601(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	//Magicrod.playboy(player,player.getLocation(),"fireworksSpark");
	    	//((CraftPlayer)player).playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 10F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	Version.playSound(player.getLocation(),Sound.ENTITY_WITHER_SHOOT, 0.2F, 1F);
	    	new MoveRod(player).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod602(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	//Magicrod.playboy(player,player.getLocation(),"fireworksSpark");
	    	//((CraftPlayer)player).playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 10F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	Version.playSound(player.getLocation(),Sound.ENTITY_WITHER_SHOOT, 0.2F, 1F);
	    	new MoveRod_2(player).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod603(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	//Magicrod.playboy(player,player.getLocation(),"fireworksSpark");
	    	//((CraftPlayer)player).playSound(player.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 10F);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	Version.playSound(player.getLocation(),Sound.ENTITY_WITHER_SHOOT, 0.2F, 1F);
	    	new MoveRod_3(player).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod701(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new AiceRod(player,plugin).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod702(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new AiceRod(player,plugin).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod703(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	String st2=list.get(5);
	    	Version.playeffect2(player.getLocation(),"ENCHANTMENT_TABLE");
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"書物がっ...");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new AiceRod_3(player,plugin).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod704(Player player,ItemStack item){
		 List<String> list=new ArrayList<String>();
	    	list=item.getItemMeta().getLore();
	    	//MP消費
	    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
	    	int hlv=(int) hlv2;
	    	if(hlv==0||player.getLevel()>=hlv){
	    		player.setLevel(player.getLevel()-hlv);
		    	String st2=list.get(5);
		    	Version.playeffect2(player.getLocation(),"ENCHANTMENT_TABLE");
		    	//破壊確率
		    	Float f=Float.valueOf(st2.substring(2, 6));
		    	int b=(int) (100/f);
		    	Random r = new Random();
		    	if(r.nextInt(b)==1){
					if(item.getAmount()!=1){
						int amount=item.getAmount()-1;
						item.setAmount(amount);
					}else{
						player.getInventory().removeItem(item);
					}
		    		player.sendMessage(ChatColor.DARK_RED+"書物がっ...");
		    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
		    		player.updateInventory();
		    	}
		    	new AiceRod_4(player,plugin).runTaskLater(plugin, 0);
		    	String st=list.get(3);
		    	//クールタイム
		    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
		    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
		    	return true;
	    	}else{
	    		player.sendMessage("私のMP低すぎっ!?");
	    		return false;
	    	}
	}
	private Boolean rod801(Player player,Player player2,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	Version.playeffect(player.getLocation(),"SPELL_INSTANT");
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new Barrier(player,player2).runTaskLater(plugin, 20);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod802(Player player,Player player2,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	Version.playeffect(player.getLocation(),"SPELL_INSTANT");
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new Barrier_2(player,player2).runTaskLater(plugin, 20);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod803(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	String st2=list.get(5);
	    	Version.playeffect2(player.getLocation(),"ENCHANTMENT_TABLE");
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"書物がっ...");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new Barrier_3(player).runTaskLater(plugin, 20);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean waterRod_901(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	Version.playeffect(player.getLocation(),"WATER_BUBBLE");
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"杖がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new Water_Rod(player).runTaskTimer(plugin,1,1);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod1001(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	Version.playeffect(player.getLocation(),"SPELL_INSTANT");
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"書物がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new Spawn_Rod(player,plugin).runTaskLater(plugin, 10);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod1002(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	Version.playeffect(player.getLocation(),"SPELL_INSTANT");
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"書物がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	new Spawn_Rod_2(player,plugin).runTaskLater(plugin, 20);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean rod1003(Player player,ItemStack item){
		 List<String> list=new ArrayList<String>();
	    	list=item.getItemMeta().getLore();
	    	//MP消費
	    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
	    	int hlv=(int) hlv2;
	    	if(hlv==0||player.getLevel()>=hlv){
	    		player.setLevel(player.getLevel()-hlv);
		    	Version.playeffect(player.getLocation(),"SPELL_INSTANT");
		    	String st2=list.get(5);
		    	//破壊確率
		    	Float f=Float.valueOf(st2.substring(2, 6));
		    	int b=(int) (100/f);
		    	Random r = new Random();
		    	if(r.nextInt(b)==1){
					if(item.getAmount()!=1){
						int amount=item.getAmount()-1;
						item.setAmount(amount);
					}else{
						player.getInventory().removeItem(item);
					}
		    		player.sendMessage(ChatColor.DARK_RED+"書物がっ・・・");
		    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
		    		player.updateInventory();
		    	}
		    	new Spawn_Rod_3(player,plugin).runTaskLater(plugin, 40);
		    	String st=list.get(3);
		    	//クールタイム
		    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
		    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
		    	return true;
	    	}else{
	    		player.sendMessage("私のMP低すぎっ!?");
	    		return false;
	    	}
	}
	private Boolean rod1101(Player player,ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	Version.playeffect(player.getLocation().add(player.getVelocity().multiply(100)),"SPELL_INSTANT");
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"書物がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	Version.playSound(player.getLocation(),Sound.ENTITY_WITHER_SHOOT, 0.15F, 1F);
	    	new MineCart_1(player,plugin).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private Boolean lightningBook(Player player,ItemStack item){
	    List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(4).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
	    	Version.playeffect(player.getLocation().add(player.getVelocity().multiply(100)),"SPELL_INSTANT");
	    	String st2=list.get(5);
	    	//破壊確率
	    	Float f=Float.valueOf(st2.substring(2, 6));
	    	int b=(int) (100/f);
	    	Random r = new Random();
	    	if(r.nextInt(b)==1){
				if(item.getAmount()!=1){
					int amount=item.getAmount()-1;
					item.setAmount(amount);
				}else{
					player.getInventory().removeItem(item);
				}
	    		player.sendMessage(ChatColor.DARK_RED+"書物がっ・・・");
	    		Version.playSound(player.getLocation(),Sound.ENTITY_ITEM_BREAK, 1, 1);
	    		player.updateInventory();
	    	}
	    	Version.playSound(player.getLocation(),Sound.ENTITY_WITHER_SHOOT, 0.15F, 1F);
	    	new Lightning(player,plugin).runTaskLater(plugin, 0);
	    	String st=list.get(3);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	return true;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	private boolean mechanics(final Player player, ItemStack item,final float fallDamagePer) {
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(3).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
	    	String st=list.get(2);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F) new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	//発生確率
	    	String st2=list.get(4);
	    	Float i=Float.valueOf(st2.substring(2, 6));
	    	Random r = new Random();
	    	if((r.nextInt(100)+1)<=i){
	    		player.setLevel(player.getLevel()-hlv);
		    	item.setDurability((short) (item.getDurability()+3));
		    	player.setVelocity(new Vector(player.getVelocity().getX(),5F,player.getVelocity().getZ()));
		    	player.getWorld().playSound(player.getLocation(),Sound.ENTITY_WITHER_SHOOT, 0.1F, 1F);
		    	player.setFallDistance(-25);
		    	new BukkitRunnable(){
		    		boolean exit = false;
		    		double height = 0;
					@Override
					public void run() {
						if(exit){
							if(player.getVelocity().getY()>=-0.1F){
								double falldistance = height-player.getLocation().getY();
								if(falldistance>=10){
									Version.particle(player.getLocation(), "SPELL_INSTANT", 3F, 0F, 0.1F, 1, 200);
									Version.particle(player.getLocation(), "SPELL_INSTANT", 0.1F, 0F, 3F, 1, 200);
									for(Entity entity:player.getNearbyEntities(7, 1, 7)){
										if(entity instanceof Monster){
											((Damageable) entity).damage(falldistance*fallDamagePer);
											player.setFallDistance(0);
											entity.setVelocity(new Vector(entity.getVelocity().getX(),2F,entity.getVelocity().getZ()));
										}
									}
								}
								this.cancel();
							}
						}else if(player.getVelocity().getY()<=3.6F){
							player.setVelocity(new Vector(player.getVelocity().getX(),-5F,player.getVelocity().getZ()));
							player.getWorld().playSound(player.getLocation(),Sound.ENTITY_WITHER_SHOOT, 0.1F, 1F);
							height = player.getLocation().getY();
							exit = true;
						}else{
							Version.particle(player.getLocation(), "FIREWORKS_SPARK", 0F, 0F, 0.F, 1, 5);
						}
					}
		    	}.runTaskTimer(plugin, 0, 1);
		    	return true;
	    	}
	    	return false;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	/*private float getTotalExp(int level,float exp){
		float totalExp = 0;
		if(level>0){
			if(level<=16){
				totalExp=level*level+6;
			}else if(level<=31){
				totalExp=(2.5f*level)*(2.5f*level)-(40.5f*level)+360;
			}else{
				totalExp=(4.5f*level)*(4.5f*level)-(162.5f*level)+2220;
			}
		}
		return totalExp;
	}
	private int getMaxExp(int level){
		if(level<=16){
			return 2*(level)+7;
		}else if(level<=31){
			return 5*(level)-38;
		}else{
			return 9*(level)-158;
		}
	}
	private float getUseExp(int level,int exp,int per,Player player){
		float useExp=0;
		while(per>=1){
			player.sendMessage("消費MP: "+String.valueOf(per)+"%");

			int lvTotalExp = getMaxExp(level);
			player.sendMessage(level+"LvのMAX経験値量: "+String.valueOf(lvTotalExp));
			player.sendMessage("所持経験値量,exp:"+exp);

			float useE = (float) (lvTotalExp*(per*0.01f));
			player.sendMessage("使用すべき経験値量,useE: "+String.valueOf(useE));

			if(useE<exp){
				useExp = useExp+useE;
				per = 0;
			}else{
				per = (int) (((useE-exp)/lvTotalExp)*100f);
				level--;
				useExp = useExp+useE;
				exp = getMaxExp(level);
				player.sendMessage("残り%:"+String.valueOf(per)+"%");
			}
		}
		return useExp;
	}*/
}
