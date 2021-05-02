package src.pachong;

public class ThreeFangHouseDetailVo {

    // url
    private String url;
    // 业务类型
    private String businessType;
    // 租售状态
    private String rentalCategory;
    // 房源ID
    private String houseId;
    // 房源标题
    private String houseTitle;
    // 发布时间
    private String publishTime;
    // 面积
    private String area;
    // 总价
    private String priceTotal;
    // 楼盘名
    private String loupanName;
    // 区域
    private String district;
    // 商圈
    private String block;
    // 地址
    private String address;
    // 发布人姓名
    private String publisher;
    // 发布人性质
    private String publisherNature;
    // 发布人公司
    private String publisherCompany;
    // 发布人手机号
    private String publisherPhone;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getRentalCategory() {
        return rentalCategory;
    }

    public void setRentalCategory(String rentalCategory) {
        this.rentalCategory = rentalCategory;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseTitle() {
        return houseTitle;
    }

    public void setHouseTitle(String houseTitle) {
        this.houseTitle = houseTitle;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherNature() {
        return publisherNature;
    }

    public void setPublisherNature(String publisherNature) {
        this.publisherNature = publisherNature;
    }

    public String getPublisherCompany() {
        return publisherCompany;
    }

    public void setPublisherCompany(String publisherCompany) {
        this.publisherCompany = publisherCompany;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }


    @Override
    public String toString() {
        return "ThreeFangHouseDetailVo{" +
                "url='" + url + '\'' +
                ", businessType='" + businessType + '\'' +
                ", rentalCategory='" + rentalCategory + '\'' +
                ", houseId='" + houseId + '\'' +
                ", houseTitle='" + houseTitle + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", area='" + area + '\'' +
                ", priceTotal='" + priceTotal + '\'' +
                ", loupanName='" + loupanName + '\'' +
                ", district='" + district + '\'' +
                ", block='" + block + '\'' +
                ", address='" + address + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publisherNature='" + publisherNature + '\'' +
                ", publisherCompany='" + publisherCompany + '\'' +
                ", publisherPhone='" + publisherPhone + '\'' +
                '}';
    }
}
