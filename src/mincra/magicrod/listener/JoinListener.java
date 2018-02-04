
package mincra.magicrod.listener;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import mincra.magicrod.database.DatabaseManager;
import mincra.magicrod.main.Magic;
import mincra.magicrod.util.Util;

public class JoinListener extends DatabaseManager implements Listener{
	private Magic plugin;
	public JoinListener(Magic magic){
		plugin = magic;
	}
	@EventHandler
	public void onPlayerJoinEvent(final PlayerJoinEvent e){
		final Player player = e.getPlayer();
		player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
		final int user_id = DatabaseManager.getUserId(e.getPlayer().getUniqueId());
		if(user_id != -1){
			new BukkitRunnable(){
				@Override
				public void run() {
					if(!player.isOnline())
						this.cancel();
					player.setMetadata("user_id", new FixedMetadataValue(plugin, user_id));
				}
			}.runTask(plugin);

			new BukkitRunnable(){
				@Override
				public void run() {
					if(!player.isOnline())
						this.cancel();
					DatabaseManager.replaceSkillSlot(user_id,player);
					int job = DatabaseManager.getJobNumber(user_id);
					player.setMetadata("MagicJob", new FixedMetadataValue(plugin, job));
				}
			}.runTaskLater(plugin, 100);

			new BukkitRunnable(){
				@Override
				public void run() {
					if(!player.isOnline())
						this.cancel();
					Inventory inv = DatabaseManager.getGiftBoxInventory(user_id);
					if(inv!=null){
						for(ItemStack item:inv){
						    if(item != null){
						    	player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1f, 1f);
								player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"ギフトボックスに何かが届いているようです.");
								player.sendMessage(ChatColor.GRAY+"/giftbox : ギフトボックスを開く.");
								break;
						    }
						}
					}else{
						player.sendMessage(ChatColor.GRAY+"内部エラー:ギフトボックスを取得できませんでした.");
						Util.debug(ChatColor.YELLOW,"内部エラー:ギフトボックスを取得できませんでした.");
					}
				}
			}.runTaskLater(plugin, 400);

			new BukkitRunnable(){
				@Override
				public void run() {
					if(!player.isOnline())
						this.cancel();
/*					List<Integer> materialList = DatabaseManager.getMaterialHistory(user_id);
					MagicJob job = DatabaseManager.getMagicJob(user_id);
					for(Achievement achievement:Achievement.values()){
						if(player.hasAchievement(achievement)){
							switch(achievement){
								case ACQUIRE_IRON:						//ACQUIRE_IRON					金属を手に入れる		鉄鉱石を精錬し、鉄を手に入れる
									break;
								case BAKE_CAKE:							//BAKE_CAKE						the cake is a lie		小麦に砂糖、牛乳、それから卵!
									break;
								case BOOKCASE:							//BOOKCASE							司書						本棚を置いてエンチャントテーブルを強化する
									break;
								case BREED_COW:							//BREED_COW						種の反映					小麦を使い2頭のウシを繁殖させる
									break;
								case BREW_POTION:						//BREW_POTION					町の薬屋さん				ポーションを醸造する
									if(job==MagicJob.PRIEST&&!materialList.contains(SkillType.RESURRECTION.getId()))
										giveMaterial(user_id,MagicMaterial.resurrection);
									break;
								case BUILD_BETTER_PICKAXE:		//BUILD_BETTER_PICKAXE	アップグレード				よりよいツルハシをつくる
									break;
								case BUILD_FURNACE:					//BUILD_FURNACE					ホットトピック				丸石を8つ使い、かまどを作る
									break;
								case BUILD_HOE:							//BUILD_HOE						いざ農業!				木材と棒を使い、木のクワを作る
									break;
								case BUILD_PICKAXE:					//BUILD_PICKAXE					いざ採掘!				木材と棒を使い、木のツルハシを作る
									break;
								case BUILD_SWORD:						//BUILD_SWORD					いざ突撃!				木材と棒を使い、剣を作る
									break;
								case BUILD_WORKBENCH:				//BUILD_WORKBENCH			土台作り					木材を4つ使い、作業台を作る
									if(job==MagicJob.ARCHER){
										if(!materialList.contains(SkillType.MAGICARROW.getId()))
											giveMaterial(user_id,MagicMaterial.magicArrow);
										if(!materialList.contains(SkillType.DIRECTIONLv1.getId()))
											giveMaterial(user_id,MagicMaterial.directionLv1);
									}
									break;
								case COOK_FISH:							//COOK_FISH						美味しい魚				魚を釣って焼く
									break;
								case DIAMONDS_TO_YOU:				//DIAMONDS_TO_YOU			ダイヤモンドをあなたに!	他のプレイヤーにダイヤモンドを投げる
									break;
								case ENCHANTMENTS:					//ENCHANTMENTS					エンチャントの使い手	本、黒曜石、ダイヤモンドを使ってエンチャントテーブルを作る
									break;
								case END_PORTAL:						//END_PORTAL						おしまい?					ジ・エンドを見つける
									break;
								case EXPLORE_ALL_BIOMES:			//EXPLORE_ALL_BIOMES		冒険の時間				全てのバイオームを発見する
									break;
								case FLY_PIG:								//FLY_PIG								ブタさんの飛ぶ頃に		ブタに乗って崖から落ちる
									break;
								case FULL_BEACON:						//FULL_BEACON					ビーコン使い				最大状態のビーコンを組み立てる
									break;
								case GET_BLAZE_ROD:					//GET_BLAZE_ROD				炎の中へ					ブレイズロッドを手に入れる
									if(job==MagicJob.MAGICIAN && !materialList.contains(SkillType.FIRE.getId()))
										giveMaterial(user_id,MagicMaterial.fire);
									break;
								case GET_DIAMONDS:					//GET_DIAMONDS					ダイヤモンド!				鉄の道具を使ってダイヤモンドを手に入れる
									break;
								case GHAST_RETURN:					//GHAST_RETURN					宛先不明				火の玉をガストに打ち返す
									break;
								case KILL_COW:							//KILL_COW							牛転がし					革を手に入れる
									break;
								case KILL_ENEMY:							//KILL_ENEMY						モンスターハンター		モンスターを攻撃して倒す
									if(job==MagicJob.MAGICIAN){
										if(!materialList.contains(SkillType.BLIZZARD.getId()))
											giveMaterial(user_id,MagicMaterial.blizzard);
										if(player.hasPermission("jobs.hunter30")&&!materialList.contains(SkillType.THUNDER.getId()))
											giveMaterial(user_id,MagicMaterial.thunder);
									}
									if(job==MagicJob.PRIEST){
										if(!materialList.contains(SkillType.HOLYLv1.getId()))
											giveMaterial(user_id,MagicMaterial.holyLv1);
									}
									if(job==MagicJob.KNIGHT){
										if(!materialList.contains(SkillType.ATTACK.getId()))
											giveMaterial(user_id,MagicMaterial.attack);
									}
									break;
								case KILL_WITHER:						//KILL_WITHER						はじまり。					ウィザーを倒す
									break;
								case MAKE_BREAD:						//MAKE_BREAD						パンを焼く					小麦をパンにする
									break;
								case MINE_WOOD:							//MINE_WOOD						木を手に入れる			ブロックになるまで木を叩く
									break;
								case NETHER_PORTAL:					//NETHER_PORTAL				さらなる深みへ			ネザーへのポータルを作る
									break;
								case ON_A_RAIL:							//ON_A_RAIL							世界のトロッコから		トロッコにのって出発地点から1km以上走行する
									break;
								case OPEN_INVENTORY:				//OPEN_INVENTORY				所持品の確認			「E」キーを押して持ち物をみる
									if(!materialList.contains(SkillType.CHARGELv1.getId()))
										giveMaterial(user_id,MagicMaterial.chargeLv1);
									if(!materialList.contains(SkillType.WALKSPEEDLv1.getId()))
										giveMaterial(user_id,MagicMaterial.walkSpeedLv1);

									if(job==MagicJob.PRIEST){
										if(!materialList.contains(SkillType.CURELv1.getId()))
											giveMaterial(user_id,MagicMaterial.cureLv1);
									}
									if(job==MagicJob.KNIGHT){
										if(!materialList.contains(SkillType.WALL.getId()))
											giveMaterial(user_id,MagicMaterial.wall);
									}
									break;
								case OVERKILL:								//OVERKILL							オーバーキル				一撃でハート8個分のダメージを与える
									break;
								case OVERPOWERED:						//OVERPOWERED					圧倒的な力				強い効果を持つリンゴを作成する
									if(job==MagicJob.KNIGHT&&!materialList.contains(SkillType.BOOST.getId())){
										int user_id = getUserId(player.getUniqueId());
										if(user_id == -1)
											return;
										addMaterialHistory(user_id,getMaterialNumber(MagicMaterial.boost));
										addGiftBoxInventory(player,user_id,MagicMaterial.boost);
									}
									break;
								case SNIPE_SKELETON:					//SNIPE_SKELETON				スナイパー対決			50m以上離れたスケルトンを矢で倒す
									if(job==MagicJob.ARCHER){
										if(player.hasPermission("jobs.hunter30")&&!materialList.contains(SkillType.DEVIDELv1.getId()))
											giveMaterial(user_id,MagicMaterial.devideLv1);
									}
									break;
								case SPAWN_WITHER:					//SPAWN_WITHER					はじまり?					ウィザーを出現させる
									break;
								case THE_END:								//THE_END							おしまい。					エンダードラゴンを倒す
									if(job==MagicJob.MAGICIAN){
										if(player.hasPermission("jobs.hunter30") && !materialList.contains(SkillType.GRAVITYLv1.getId()))
												giveMaterial(user_id,MagicMaterial.gravityLv1);
										if(player.hasPermission("jobs.hunter40") && !materialList.contains(SkillType.UNGRAVITYLv1.getId()))
											giveMaterial(user_id,MagicMaterial.untigravityLv1);
									}
									break;
								default:
									break;
							}
						}
					}
*/				}

				@SuppressWarnings("unused")
				private void giveMaterial(int user_id, ItemStack itemstack) {
					addGiftBoxInventory(user_id,itemstack);
					addMaterialHistory(user_id,getMaterialNumber(itemstack));
				}
			}.runTaskLater(plugin, 600);
		}
	}
}
