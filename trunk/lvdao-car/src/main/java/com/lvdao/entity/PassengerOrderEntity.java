package com.lvdao.entity;

import java.io.Serializable;
import java.util.Date;

public class PassengerOrderEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 7252797784256075501L;
	
	private String passengerOrderId;//乘客订单ID

	private String userId;//USER ID

    private String userName;//用户名

    private String originalPlaceName;//起始地点名字

    private String originalLongtitude;//起始经度

    private String originalLatitude;//起始纬度

    private String targetPlaceName;//目标地点名称

    private String targetLongtitude;//目标地点经度

    private String targetLatitude;//目标地点纬度

    private String orderMoney;//订单预估金额（实际金额线下支付）

    private String orderDistance;//起始地点产生的距离公里数

    private String orderSeat;//订单需要几个座位

    private String goodsName;//货物名称

    private String goodsWeight;//货物重量

    private String goodsHeight;//货物高度

    private String goodsWidth;//货物宽度

    private Date orderAppointTime;//订单预约时间
    
    private Date orderStartTime;//订单开始时间
    
    private Date orderEndTime;//订单结束时间
    
    private String orderType;//订单类型:1 出租车 2 巴士 3 大货车
    
    private String orderDesc;//描述
    
    private String orderStatus;//订单状态:0表示未上车,1表示已到达,2表示已取消
    
    private String matchDegree;//匹配度
    
    private String headPic;//乘客头像
    
    private String charteredVehicle;//包车:1包车,0非包车 
    
    private String vehicleLevel;//车辆等级类型 : 1 小面包车  2 中面包车  3 小货车 4 中货车
    
    
    private String userPicUrl;//用户图片地址
    
    private String userRealName;//用户真是姓名
    
    private String vehicleNo;//车牌号
    
    private String vehicleBrand;//汽车品牌
    
    private String vehicleColor;//车辆颜色
    
    private String vehicleSeatNo;//车辆座位数
    
    private String vehicleOrderAppointTime;//司机方订单预约时间
    
    private String charterFee;//包车费用
    
    private String meanExpense;//人均费用
    
	public String getVehicleOrderAppointTime() {
		return vehicleOrderAppointTime;
	}

	public void setVehicleOrderAppointTime(String vehicleOrderAppointTime) {
		this.vehicleOrderAppointTime = vehicleOrderAppointTime;
	}

	public String getCharterFee() {
		return charterFee;
	}

	public void setCharterFee(String charterFee) {
		this.charterFee = charterFee;
	}

	public String getMeanExpense() {
		return meanExpense;
	}

	public void setMeanExpense(String meanExpense) {
		this.meanExpense = meanExpense;
	}

	public String getVehicleSeatNo() {
		return vehicleSeatNo;
	}

	public void setVehicleSeatNo(String vehicleSeatNo) {
		this.vehicleSeatNo = vehicleSeatNo;
	}

	public String getUserPicUrl() {
		return userPicUrl;
	}

	public void setUserPicUrl(String userPicUrl) {
		this.userPicUrl = userPicUrl;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public String getVehicleLevel() {
		return vehicleLevel;
	}

	public void setVehicleLevel(String vehicleLevel) {
		this.vehicleLevel = vehicleLevel;
	}

	public String getCharteredVehicle() {
		return charteredVehicle;
	}

	public void setCharteredVehicle(String charteredVehicle) {
		this.charteredVehicle = charteredVehicle;
	}

	public synchronized String getPassengerOrderId() {
		return passengerOrderId;
	}

	public synchronized void setPassengerOrderId(String passengerOrderId) {
		this.passengerOrderId = passengerOrderId;
	}

	public Date getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(Date orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public Date getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(Date orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getOriginalPlaceName() {
        return originalPlaceName;
    }

    public void setOriginalPlaceName(String originalPlaceName) {
        this.originalPlaceName = originalPlaceName == null ? null : originalPlaceName.trim();
    }

    public String getOriginalLongtitude() {
        return originalLongtitude;
    }

    public void setOriginalLongtitude(String originalLongtitude) {
        this.originalLongtitude = originalLongtitude == null ? null : originalLongtitude.trim();
    }

    public String getOriginalLatitude() {
        return originalLatitude;
    }

    public void setOriginalLatitude(String originalLatitude) {
        this.originalLatitude = originalLatitude == null ? null : originalLatitude.trim();
    }

    public String getTargetPlaceName() {
        return targetPlaceName;
    }

    public void setTargetPlaceName(String targetPlaceName) {
        this.targetPlaceName = targetPlaceName == null ? null : targetPlaceName.trim();
    }

    public String getTargetLongtitude() {
        return targetLongtitude;
    }

    public void setTargetLongtitude(String targetLongtitude) {
        this.targetLongtitude = targetLongtitude == null ? null : targetLongtitude.trim();
    }

    public String getTargetLatitude() {
        return targetLatitude;
    }

    public void setTargetLatitude(String targetLatitude) {
        this.targetLatitude = targetLatitude == null ? null : targetLatitude.trim();
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney == null ? null : orderMoney.trim();
    }

    public String getOrderDistance() {
        return orderDistance;
    }

    public void setOrderDistance(String orderDistance) {
        this.orderDistance = orderDistance == null ? null : orderDistance.trim();
    }

    public String getOrderSeat() {
        return orderSeat;
    }

    public void setOrderSeat(String orderSeat) {
        this.orderSeat = orderSeat == null ? null : orderSeat.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight == null ? null : goodsWeight.trim();
    }

    public String getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(String goodsHeight) {
        this.goodsHeight = goodsHeight == null ? null : goodsHeight.trim();
    }

    public String getGoodsWidth() {
        return goodsWidth;
    }

    public void setGoodsWidth(String goodsWidth) {
        this.goodsWidth = goodsWidth == null ? null : goodsWidth.trim();
    }

    public Date getOrderAppointTime() {
        return orderAppointTime;
    }

    public void setOrderAppointTime(Date orderAppointTime) {
        this.orderAppointTime = orderAppointTime;
    }

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc == null ? null : orderDesc.trim();
	}

	public synchronized String getMatchDegree() {
		return matchDegree;
	}

	public synchronized void setMatchDegree(String matchDegree) {
		this.matchDegree = matchDegree;
	}

	public synchronized String getHeadPic() {
		return headPic;
	}

	public synchronized void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

}