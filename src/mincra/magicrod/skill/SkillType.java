package mincra.magicrod.skill;

public enum SkillType{
	AIR(0),
	BLIZZARD(1),
	FIRE(2),
	TAUNTLv1(3),
	TAUNTLv2(13),
	THUNDER(4),
	CURELv1(5),
	CURELv2(12),
	WALKSPEEDLv1(6),
	WALKSPEEDLv2(15),
	JUMPLv1(7),
	JUMPLv2(14),
	WALL(8),
	ATTACK(9),
	RESURRECTION(10),
	BOOST(11),
	HOLYLv1(16),
	HOLYLv2(17),
	GRAVITYLv1(18),
	UNGRAVITYLv1(19),
	VILLAGER(20),
	MAGICARROW(21),
	DIRECTIONLv1(22),
	DEVIDELv1(23),
	CHARGELv1(24),
	INVISIBLEHANDSLv1(25),
	INVISIBLEHANDSLv2(26),
	INVISIBLEHANDSLv3(27);

	private final int id;

	private SkillType(final int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}
