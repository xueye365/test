package src.pachong;

public class ThreeFangXzlDetailVo {

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
    private String totalOnRent;
    // 在售房源量
    private String totalOnSale;
    // 出租房源列表
    private String rentLink;
    // 出售房源列表
    private String saleLink;
    // 其他
    private String other;

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

    public String getTotalOnRent() {
        return totalOnRent;
    }

    public void setTotalOnRent(String totalOnRent) {
        this.totalOnRent = totalOnRent;
    }

    public String getTotalOnSale() {
        return totalOnSale;
    }

    public void setTotalOnSale(String totalOnSale) {
        this.totalOnSale = totalOnSale;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getRentLink() {
        return rentLink;
    }

    public void setRentLink(String rentLink) {
        this.rentLink = rentLink;
    }

    public String getSaleLink() {
        return saleLink;
    }

    public void setSaleLink(String saleLink) {
        this.saleLink = saleLink;
    }

    @Override
    public String toString() {
        return "ThreeFangXzlDetailVo{" +
                "url='" + url + '\'' +
                ", loupanId='" + loupanId + '\'' +
                ", loupanName='" + loupanName + '\'' +
                ", district='" + district + '\'' +
                ", block='" + block + '\'' +
                ", address='" + address + '\'' +
                ", totalOnRent='" + totalOnRent + '\'' +
                ", totalOnSale='" + totalOnSale + '\'' +
                ", rentLink='" + rentLink + '\'' +
                ", saleLink='" + saleLink + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
