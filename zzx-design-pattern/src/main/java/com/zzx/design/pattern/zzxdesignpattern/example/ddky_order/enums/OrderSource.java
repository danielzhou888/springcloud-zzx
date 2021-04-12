/**
 * 
 */
package com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.enums;

/**
 * 订单来源枚举
 * @author zhumingzhou
 * @date 2014年12月17日
 */
public enum OrderSource {

	APP_CLIENT(1,"APP",true, true, true, null), 
	WEBSITE(2,"H5网站", true, true, true, null),
	TELEPHONE(3 ,"电话 ", false, true, false, null),
	PICTURE_TAKED(4, "拍照", false, false, false, null),
	DELIVERY(5,"配送", true, true, true, null),
	MEITUAN(6,"美团", false, true, false, 0),
	B2C(7, "B2C", false,  true, false, null),
	DIANPING(8, "大众点评", false, true, false, null),
	BAIDU(9, " 百度外卖",false, true, false, 3),
	LIFEC(10, "生活圈",false, true, false, null),
	DAOWEI(11, "到位",false, true, false, null),
	ZHONGPU(12, "忠仆",false, true, false, null),
	B2CIMPORT(13, "B2C导入",false, true, false, null),
	SEXTOY(14, "情趣用品",false, false, false, null),
	PRESALE(15, "预售", false, false, false, null),
	PC(16, "PC", false, true, false, null),
	ELE(17, "ele外卖",false, true, false, 2),
	TELEPHONE_B2C(18, "电话b2c", false, true, false, null),
	CHUNYU(19, "春雨医生", true, true, true, 5),
	GAODE(20, "高德", true, true, true, 6),
	BAOSHUIQU(21, "保税区", false, false, false, null),
	DAOJIA58(22, "58到家", true, true, true, 7),
	MITU(23, "MITU", false, true, false, null),
	ZWYL(24, "自我药疗", true, true, true, null),
	PING_AN(25, "平安好医生", false, true, false, 1),
	PC_YISHOU(26, "颐寿PC", false, true, false, null),
	ZHIYOU(27, "海外直邮", false, false, false, null),
	YAOZHIDA(28, "药直达", false, true, false, 4),
	ZFWS(29, "中方微商", false, true, false, null),
	MEDICALCARD(30, "体检卡", false, false, false, null),
	JINGDONGDAOJIA(31, "京东到家", false, true, false, 10),
	TONGFANG(32, "同方商城", false, true, false, null),
	WECHAT_CX(33, "微信小程序", false, true, false, null),
	QIJIAN(34, "企健网", false, true, false, 12),
	YHDJ(35, "医护到家", false, true, false, 11),
	OFFLINE(36, "线下药店", false, false, false, null),
	ZHYFBAIDU(37, "智慧药房百度外卖", false, false, false, 13),
	DABAI(38, "叮当大白", false, false, false, null),
	XYWY(39, "寻医问药", false, false, false, 14),
	GENECHECK(40, "基因检测", false, false, false, null),
	JJK(41, "记健康", false, false, false, null),
	KEJIANG(42, "科匠", false, false, false, 15),
	HWYD(43, "海外药店", false, false, false, null),
	BLM(44, "菠萝蜜", false, false, false, 16),
	YFT(45, "云付通", false, true, false, 17),
	CYSB(46, "查悦社保", false, true, false, 18),
	YL(47, "药联", false, true, false, 19),
	JINGDONGDAOJIA_SEX(48, "京东到家情趣", false, true, false, 20),
	XIAOMI(49, "小米", false, true, false, null),
	MEITUAN_SEX(50, "美团情趣", false, true, false, 21),
	VIRTUAL_ORDER(51, "虚拟礼包", false, true, false, null),
	YJY(52, "医加壹", false, false, false, 25),
	BLD(53, "便利店", false, false, false, null),
	HUAWEI(54, "华为", false, true, false, null),
	YYB(55, "用药宝", false, false, false, 24),
	ZFBXCX(56, "支付宝小程序", false, true, false, null),
	PRODUCT_SEARCH(57,"代客找药", true, false, false, null),
	OASIS(58,"泓华", false, false, false, 22),
	YILIAN(59,"医联", false, false, false, 23),
	YCT(60,"医程通", false, false, false, null),
	WM(61,"微盟", false, false, false, 26),
	GROUP_BUYING(62,"拼团", false, false, false, null),
	YL_HOSPITAL(63,"医联互联网医院", false, false, false, null),
	XCX_BEIJING(64, "北京药房小程序", false, true, false, null),
	MJK(65,"妙健康", false, false, false, null),
	TANGCHENBEIJIAN(66, "汤臣倍健", false, true, false, null),
	TMALLIMPORT(67, "天猫优卡丹订单导入", false, true, false, null),
	PINDD(68, "拼多多", false, false, false, null),
	FUYANJIE(69, "叮当快药妇炎洁小程序", false, true, false, null),
	SHANLIANG(70, "叮当快药闪亮小程序", false, true, false, null),
	BISHENGYUAN(71, "叮当快药碧生源小程序", false, true, false, null),
	SILIAN(72, "叮当快药斯利安小程序", false, true, false, null),
	HUISHI(73, "叮当快药惠氏小程序", false, true, false, null),
	HESHENGYUAN(74, "叮当快药合生元小程序", false, true, false, null),
	YAODU(75, "叮当快药药都小程序", false, true, false, null),
	APP_PRO_CLIENT(76,"PROAPP",true, true, true, null),
	RENHE_HOSPITAL(77, "仁和互联网医院", false, false, false, null),
	YJK(78, "优健康", false, false, false, null),
	ESURF(79, "翼健康", false, true, false, null),
	SHXCX(80, "上海小程序", false, true, false, null),
	TFH5(81, "同方H5", false, true, false, null),
	TJXCX(82, "天津小程序", false, true, false, null),
	SZXCX(83, "深圳小程序", false, true, false, null),
	CDXCX(84, "成都小程序", false, true, false, null),
	PAZXC(85, "平安中心仓", false, false, false, 27),
	CCB(86, "建行", false, false, false, 28),
	DTP(87,"DTP", false, false, false, null),
	TKBX(88,"泰康保险", false, false, false, 29),
	KBRJK(89,"康佰瑞健康", false, false, false, 30),
	ZFWS_FXYS(90, "药师分享", false, true, false, null),
	MEITUAN_HOSPITAL(91, "美团互联网", false, true, false, null),
	ZHIHUI_EBAO(92, "智慧E保", false, true, false, 63),
	BDXCX(93, "百度小程序", false, true, false, null),
	JRTT(94, "今日头条小程序", false, true, false, null),
	ZZHZ(95, "中智合作", false, false, false, 31),
	TFSC(96, "同方市场", false, true, false, null),
	XSL(97, "杏树林三方", false, true, false, null),
	ZMSK(98, "中美史克", false, true, false, null),
	CHYO(99, "畅由", false, false, false, 32),
	JKK(100, "健康卡", false, false, false, null),
	LTV(101, "联通沃钱包", false, false, false, 64),
	WEIYI(102, "微医", false, false, false, 33),
	HANYI(103, "瀚医", false, false, false, 34),
	KYJYJC(104, "快医基因检测", false, false, false, null),
	MTBLD(105, "美团便利店", false, false, false, 35),
	JIANYI(106, "健医", false, false, false, 36),
	HLWTELEPHONE_O2C(107, "互联网医院电话o2c", false, true, false, null),
	HLWTELEPHONE_B2C(108, "互联网医院电话b2c", false, true, false, null),
	ZSHYJ(109, "中石化易捷", false, false, false, 37),
	YFW(110, "药房网", false, false, false, 38),
	HAINAN_HOSPITAL(111, "海南互联网医院", false, false, false, null),
	YMKJ(112, "远盟康健", false, false, false, 39),
	WYKL(113, "网易考拉", false, false, false, 40),
	HGSH(114, "会工生活", false, false, false, 41),
	ZYRS(115, "中意人寿", false, false, false, 42),
	TKCX(116, "泰康出行", false, false, false, 43),
	JJW(117, "聚酒窝", false, false, false, 44),
	EBZYB(118, "E保中银保", false, false, false, 45),
	GDXCX(119, "高德小程序", false, true, false, null),
	YFWSC(120, "药房网商城", false, false, false, 46),
	JXK(121, "京小康", false, false, false, 47),
	QEXR(122, "企鹅杏仁", false, false, false, 48),
	PKB(123, "普康宝", false, false, false, 49),
	JYGZH(124, "健医公众号", false, false, false, 50),
	FR(125, "福瑞", false, false, false, 51),
	KZYY(126, "口罩预约", false, false, false, 52),
	BDXD(127, "百度小度", false, false, false, 53),
	KANJIA(128,"砍价", true, true, true, null),
	TKMZ(129,"泰康门诊", false, false, false, 54),
	PKBGZH(130,"普康宝公众号", false, false, false, 55),
	BDJK(131,"百度健康", false, false, false, 56),
	JDSC(132,"京东商城", false, false, false, 57),
	EBB2C(133,"饿百B2C", false, false, false, 58),
	MTB2C(134,"美团B2C", false, false, false, 59),
	HYGZH(135,"慧医公众号", false, false, false, 60),
	YLSW(136,"联合医务", false, false, false, 61),
	ZGYH(137,"中国银行", false, false, false, 62),
	QQXCX(138, "QQ小程序", false, true, false, null),
	KYGXYS(139, "快医共享医生", false, false, false, 65),
	DTLL(140, "动力直播", false, false, false, 66),
	PDDO2O(141, "拼多多O2O", false, false, false, 67),
	GJPAHYS(142, "网店管家-平安好医生", false, false, false, 68),
	GJPDD(143, "网店管家-拼多多", false, false, false, 69),
	GJTB(144, "网店管家-淘宝", false, false, false, 70),
	GJJD(145, "网店管家-京东", false, false, false, 71),
	GJYFW(146, "网店管家-药房网", false, false, false, 72),
	GJDY(147, "网店管家-抖音", false, false, false, 73),
	GJTMSC(148, "网店管家-天猫商城", false, false, false, 74),
	HEYL(149, "海尔医疗", false, false, false, 75),
	BBYY(150, "保贝医院", false, false, false, 76),
	EBSYSM(151, "E保送药上门", false, false, false, 77),
	YDTG(152, "叮当健康", false, false, false, 78),
	TKJT(153, "泰康健投", false, false, false, 79);
	private int code;
	private String title;
	private boolean riskyAudit;//风险审核
	private boolean flagAudit;//标记审核
	private boolean marketingSupport;//营销支持
	private Integer thirdpartySource;
	
	private OrderSource(int code, String title, boolean riskyAudit, boolean flagAudit, boolean marketingSupport) {
		this.code = code;
		this.title = title;
		this.riskyAudit = riskyAudit;
		this.flagAudit = flagAudit;
		this.marketingSupport = marketingSupport;
	}
	
	private OrderSource(int code, String title, boolean riskyAudit, boolean flagAudit, boolean marketingSupport,Integer thirdpartySource) {
		this(code, title, riskyAudit, flagAudit, marketingSupport);
		this.thirdpartySource = thirdpartySource;
	}

	public static OrderSource get(int code) {
		for(OrderSource source : OrderSource.values()) {
			if(code == source.getCode())
				return source;
		}
		return null;
	}
	
	public static OrderSource get(String title) {
		for(OrderSource source : OrderSource.values()) {
			if(title.equals(source.getTitle()))
				return source;
		}
		return null;
	}
	
	public int getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}
	
	public boolean isRiskyAudit() {
		return riskyAudit;
	}

	public boolean isFlagAudit() {
		return flagAudit;
	}
	
	public boolean isMarketingSupport(){
		return marketingSupport;
	}
	
	public Integer getThirdpartySource() {
		return thirdpartySource;
	}
}
