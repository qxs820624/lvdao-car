package com.lvdao.entity;

import java.io.Serializable;

public class VehicleEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 3102667913630300566L;

    private String vehicleId;//车辆ID

    private String vehicleBrand;// 汽车品牌

    private String vehicleModel;//汽车型号，比如沃尔沃XC60等

    private String vehicleNo;//车牌号

    private String vehicleEngineNo;//发动机号

    private String vehicleVinNo;//车架号

    private String vehicleType;//车辆类型：0，小型汽车；1，货车；2，巴士

    private String vehicleLength;//汽车长度

    private String vehicleHeight;//汽车高度

    private String vehicleWidth;//汽车宽度
    
    private String vehicleWeight;//汽车重量

    private String vehicleSeatNo;//汽车座位数量 --小型汽车 巴士不能为空

    private String vehicleLoadCapacity;//汽车载重量 --货车不能为空
    
    private String vehicleLoadVolume;//汽车载货体积 --货车不能为空
    
    private String vehicleColor;//车辆颜色

    private String vehicleDriverId;//车主ID

    private String vehicleDriverName;//车主名字

    private String vehicleDriverMobileNo;//车主联系电话

    private String vehicleLevel;//车辆等级类型 : 1小面包车  2中面包车  3小货车 4中货车
    
    //页面需要
    private String userLongitude;
    private String userLatitude;
    private String vehiclePhotos;//汽车车辆照片
    
	public String getVehiclePhotos() {
		return vehiclePhotos;
	}

	public void setVehiclePhotos(String vehiclePhotos) {
		this.vehiclePhotos = vehiclePhotos;
	}

	public String getVehicleWidth() {
		return vehicleWidth;
	}

	public void setVehicleWidth(String vehicleWidth) {
		this.vehicleWidth = vehicleWidth;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId == null ? null : vehicleId.trim();
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand == null ? null : vehicleBrand.trim();
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel == null ? null : vehicleModel.trim();
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
    }

    public String getVehicleEngineNo() {
        return vehicleEngineNo;
    }

    public void setVehicleEngineNo(String vehicleEngineNo) {
        this.vehicleEngineNo = vehicleEngineNo == null ? null : vehicleEngineNo.trim();
    }

    public String getVehicleVinNo() {
        return vehicleVinNo;
    }

    public void setVehicleVinNo(String vehicleVinNo) {
        this.vehicleVinNo = vehicleVinNo == null ? null : vehicleVinNo.trim();
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType == null ? null : vehicleType.trim();
    }

    public String getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(String vehicleLength) {
        this.vehicleLength = vehicleLength == null ? null : vehicleLength.trim();
    }

    public String getVehicleHeight() {
        return vehicleHeight;
    }

    public void setVehicleHeight(String vehicleHeight) {
        this.vehicleHeight = vehicleHeight == null ? null : vehicleHeight.trim();
    }

    public String getVehicleWeight() {
        return vehicleWeight;
    }

    public void setVehicleWeight(String vehicleWeight) {
        this.vehicleWeight = vehicleWeight == null ? null : vehicleWeight.trim();
    }

    public String getVehicleSeatNo() {
        return vehicleSeatNo;
    }

    public void setVehicleSeatNo(String vehicleSeatNo) {
        this.vehicleSeatNo = vehicleSeatNo == null ? null : vehicleSeatNo.trim();
    }

    public String getVehicleLoadCapacity() {
        return vehicleLoadCapacity;
    }

    public void setVehicleLoadCapacity(String vehicleLoadCapacity) {
        this.vehicleLoadCapacity = vehicleLoadCapacity == null ? null : vehicleLoadCapacity.trim();
    }

    public String getVehicleDriverId() {
        return vehicleDriverId;
    }

    public void setVehicleDriverId(String vehicleDriverId) {
        this.vehicleDriverId = vehicleDriverId == null ? null : vehicleDriverId.trim();
    }

    public String getVehicleDriverName() {
        return vehicleDriverName;
    }

    public void setVehicleDriverName(String vehicleDriverName) {
        this.vehicleDriverName = vehicleDriverName == null ? null : vehicleDriverName.trim();
    }

    public String getVehicleDriverMobileNo() {
        return vehicleDriverMobileNo;
    }

    public void setVehicleDriverMobileNo(String vehicleDriverMobileNo) {
        this.vehicleDriverMobileNo = vehicleDriverMobileNo == null ? null : vehicleDriverMobileNo.trim();
    }

    public String getVehicleLevel() {
        return vehicleLevel;
    }

    public void setVehicleLevel(String vehicleLevel) {
        this.vehicleLevel = vehicleLevel == null ? null : vehicleLevel.trim();
    }

	public synchronized String getUserLongitude() {
		return userLongitude;
	}

	public synchronized void setUserLongitude(String userLongitude) {
		this.userLongitude = userLongitude;
	}

	public synchronized String getUserLatitude() {
		return userLatitude;
	}

	public synchronized void setUserLatitude(String userLatitude) {
		this.userLatitude = userLatitude;
	}

	public synchronized String getVehicleLoadVolume() {
		return vehicleLoadVolume;
	}

	public synchronized void setVehicleLoadVolume(String vehicleLoadVolume) {
		this.vehicleLoadVolume = vehicleLoadVolume;
	}
	
}