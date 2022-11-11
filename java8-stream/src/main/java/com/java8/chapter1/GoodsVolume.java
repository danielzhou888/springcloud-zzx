package com.java8.chapter1;

import java.io.Serializable;
import java.util.Objects;
/**
 * 
 * goods销量信息
 * @author		LiuJianHua
 * @version		1.0
 * Created		2022年2月16日 下午8:51:58
 */
public class GoodsVolume implements Serializable {
	private static final long serialVersionUID = 5527484122734792587L;

	private Long goodsId;
	private Long pharmacyId;
	private Long skuId;
	private Long productId;
	private Long salesVolume;//4天销量
	private Long salesVolume30;//30天销量
	
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Long getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getSalesVolume() {
		if(Objects.isNull(salesVolume)) {
			salesVolume = 0L;
		}
		return salesVolume;
	}
	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
	}
	public Long getSalesVolume30() {
		if(Objects.isNull(salesVolume30)) {
			salesVolume30 = 0L;
		}
		return salesVolume30;
	}
	public void setSalesVolume30(Long salesVolume30) {
		this.salesVolume30 = salesVolume30;
	}
}
