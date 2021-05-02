package src.pachong;

public class HaozuXzlDetailVo {

    // url
    private String url;
    // 楼盘ID
    private String loupanId;
    // 楼盘名
    private String loupanName;
    // 区域
    private String district;
    // 商圈
    private String block;
    // 地址
    private String address;
    // 在租房源量
    private String total;
    // 可租面积
    private String rentArea;
    // 建筑面积
    private String buildingArea;
    // 总楼层
    private String totalFloor;
    // 得房率
    private String roomRate;
    // 标准层高
    private String height;
    // 房源汇总
//    private String ;
    // 楼盘均价
    private String loupanAveragePrice;
    // 商圈均价
    private String blockAveragePrice;
    // 区域均价
    private String districtAveragePrice;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLoupanId() {
        return loupanId;
    }

    public void setLoupanId(String loupanId) {
        this.loupanId = loupanId;
    }

    public String getLoupanName() {
        return loupanName;
    }

    public void setLoupanName(String loupanName) {
        this.loupanName = loupanName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(String totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(String roomRate) {
        this.roomRate = roomRate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLoupanAveragePrice() {
        return loupanAveragePrice;
    }

    public void setLoupanAveragePrice(String loupanAveragePrice) {
        this.loupanAveragePrice = loupanAveragePrice;
    }

    public String getBlockAveragePrice() {
        return blockAveragePrice;
    }

    public void setBlockAveragePrice(String blockAveragePrice) {
        this.blockAveragePrice = blockAveragePrice;
    }

    public String getDistrictAveragePrice() {
        return districtAveragePrice;
    }

    public void setDistrictAveragePrice(String districtAveragePrice) {
        this.districtAveragePrice = districtAveragePrice;
    }


    @Override
    public String toString() {
        return "HaozuXzlDetailVo{" +
                "url='" + url + '\'' +
                ", loupanId='" + loupanId + '\'' +
                ", loupanName='" + loupanName + '\'' +
                ", district='" + district + '\'' +
                ", block='" + block + '\'' +
                ", address='" + address + '\'' +
                ", total='" + total + '\'' +
                ", rentArea='" + rentArea + '\'' +
                ", buildingArea='" + buildingArea + '\'' +
                ", totalFloor='" + totalFloor + '\'' +
                ", roomRate='" + roomRate + '\'' +
                ", height='" + height + '\'' +
                ", loupanAveragePrice='" + loupanAveragePrice + '\'' +
                ", blockAveragePrice='" + blockAveragePrice + '\'' +
                ", districtAveragePrice='" + districtAveragePrice + '\'' +
                '}';
    }
}
