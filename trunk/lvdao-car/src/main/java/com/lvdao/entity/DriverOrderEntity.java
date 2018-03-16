package com.lvdao.entity;

import java.io.Serializable;
import java.util.Date;

public class DriverOrderEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4268440659539368181L;
	
	private String driverOrderId;//司机订单
	private String userId;//'USER ID
    private String userName;//用户名
    private String vehicleNo;//车牌号
    private String vehicleBrand;
    private String vehicleModel;
    private String vehicleColor;
    private String originalPlaceName;//起始地点名字
    private String originalLongtitude;//起始经度
    private String originalLatitude;//起始纬度
    private String targetPlaceName;//目标地点名称
    private String targetLongtitude;//目标地点经度
    private String targetLatitude;//目标地点纬度
    private String orderDistance;//起始地点产生的距离公里数
    private Date orderAppointTime;//订单预约时间
    private String orderDesc;//备注
    
    private String orderStatus;//订单状态(0表示待出发,1表示已完成,2表示已取消)
    private String route;//路线
    private String residual;//剩余载重量
    private String charterFee;//包车费用
    private String contacts;//联系人
    private String contactNumber;//联系电话
    private String seatNo;//座位数
    private Date orderStartTime;//订单开始时间
    private Date orderEndTime;//订单结束时间
    private String meanExpense;//人均费用
    private String orderType;//订单类型:0，小型汽车；1，货车；2，巴士
    
    private String vehicleLevel;//车辆等级类型: 1 小面包车  2中面包车  3小货车 4中货车
    
    public String getVehicleLevel() {
		return vehicleLevel;
	}

	public void setVehicleLevel(String vehicleLevel) {
		this.vehicleLevel = vehicleLevel;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
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

	public String getMeanExpense() {
		return meanExpense;
	}

	public void setMeanExpense(String meanExpense) {
		this.meanExpense = meanExpense;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public synchronized String getDriverOrderId() {
		return driverOrderId;
	}

	public synchronized void setDriverOrderId(String driverOrderId) {
		this.driverOrderId = driverOrderId;
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

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
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

    public String getOrderDistance() {
        return orderDistance;
    }

    public void setOrderDistance(String orderDistance) {
        this.orderDistance = orderDistance == null ? null : orderDistance.trim();
    }

    public Date getOrderAppointTime() {
        return orderAppointTime;
    }

    public void setOrderAppointTime(Date orderAppointTime) {
        this.orderAppointTime = orderAppointTime;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc == null ? null : orderDesc.trim();
    }

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public synchronized String getRoute() {
		return route;
	}

	public synchronized void setRoute(String route) {
		this.route = route;
	}

	public synchronized String getResidual() {
		return residual;
	}

	public synchronized void setResidual(String residual) {
		this.residual = residual;
	}

	public synchronized String getCharterFee() {
		return charterFee;
	}

	public synchronized void setCharterFee(String charterFee) {
		this.charterFee = charterFee;
	}

	public synchronized String getContacts() {
		return contacts;
	}

	public synchronized void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public synchronized String getContactNumber() {
		return contactNumber;
	}

	public synchronized void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public synchronized String getVehicleBrand() {
		return vehicleBrand;
	}

	public synchronized void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public synchronized String getVehicleModel() {
		return vehicleModel;
	}

	public synchronized void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public synchronized String getVehicleColor() {
		return vehicleColor;
	}

	public synchronized void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	
}