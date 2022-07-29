//package com.zzx.tools.refresh_dext;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.lang.StringUtils;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.CollectionUtils;
//
//public class Main {
//	private static Logger logger = LoggerFactory.getLogger(Main.class);
//
//	public static void main(String[] args) {
//		String targetUrl = "https://www.dextools.io/app/pancakeswap/pair-explorer/0x73578e84cff078f2f51fa16826d4238419a1e44b";
//		int time = 1000000;
//		for (int i = 4; i < 5; i++) {
//			final int page = i;
//			refresh(targetUrl, time, page);
//		}
//	}
//
//	public static void refresh(String targetUrl, int time, int page) {
//		String url = "https://ip.jiangxianli.com/api/proxy_ips?page="+page+"&&order_by=speed&order_rule=DESC";
//		List<MyIp> ipList = getIp(url);
//		String blogUrl = targetUrl;
//		AtomicInteger count = new AtomicInteger(0);
//		ExecutorService executorService = Executors.newFixedThreadPool(20);
//		for(int i = 0; i< time; i++) {
//			executorService.submit(new Runnable() {
//				@Override
//				public void run() {
//					//2.设置ip代理
//					for(final MyIp myIp : ipList) {
//						System.setProperty("http.maxRedirects", "50");
//						System.getProperties().setProperty("proxySet", "true");
//						System.getProperties().setProperty("http.proxyHost", myIp.getIp_address());
//						System.getProperties().setProperty("http.proxyPort", myIp.getPort());
//
//						try {
//							Document doc = Jsoup.connect(blogUrl)
//									.userAgent("Mozilla")
//									.cookie("auth", "token")
//									.timeout(3000)
//									.get();
//							if(doc != null) {
//								logger.info("成功刷新次数: " + count.incrementAndGet());
//							}
//						} catch (IOException e) {
//							logger.error(myIp.getIp_address() + ":" + myIp.getPort() + "±¨´í");
//						}
//					}
//				}
//			});
//
//		}
//	}
//
//	public static List<MyIp> getIp(String url) {
//		List ips = new ArrayList<MyIp>();
//		String result = HttpUtil2.doGet(url, null);
//
//		if (StringUtils.isNotEmpty(result)) {
//			JSONObject jsonObject = JSONObject.parseObject(result);
//
//			//List<MyIp> myIps = JsonMapper.getJsonMapper().readValue(data, new TypeReference<List<MyIp>>(){});
//			Object o = JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("data"))).get("data");
//			List<MyIp> myIps = JSONObject.parseArray(JSONObject.toJSONString(o), MyIp.class);
//
//			if (!CollectionUtils.isEmpty(myIps)) {
//				for (MyIp eneity : myIps) {
//					MyIp myIp = new MyIp();
//					myIp.setIp_address(eneity.getIp_address());
//					myIp.setPort(eneity.getPort());
//					ips.add(myIp);
//				}
//			}
//		}
//
//
//			//1.Ïòip´úÀíµØÖ··¢ÆðgetÇëÇó£¬ÄÃµ½´úÀíµÄip
//			//Document doc = Jsoup.connect(url)
//			//  .userAgent("Mozilla")
//			//  .cookie("auth", "token")
//			//		.header("content-ty[e", "application/xml")
//			//  .timeout(3000)
//			//  .get();
//			//
//			////2,½«µÃµ½µÄipµØÖ·½âÎö³ý×Ö·û´®
//			//String ipStr = doc.body().text().trim().toString();
//			//
//			////3.ÓÃÕýÔò±í´ïÊ½È¥ÇÐ¸îËùÓÐµÄip
//			//String[] ips = ipStr.split("\\s+");
//			//
//			////4.Ñ­»·±éÀúµÃµ½µÄip×Ö·û´®£¬·â×°³ÉMyIpµÄbean
//			//ipList = new ArrayList<MyIp>();
//			//for(final String ip : ips) {
//			//	MyIp myIp = new MyIp();
//			//	String[] temp = ip.split(":");
//			//	myIp.setIp_address(temp[0].trim());
//			//	myIp.setPort(temp[1].trim());
//			//	ipList.add(myIp);
//			//}
//		return ips;
//	}
//}
//
//
//
//
//
//
