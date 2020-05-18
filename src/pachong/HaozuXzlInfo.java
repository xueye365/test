package pachong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HaozuXzlInfo {

    private static final String DOMAIN_NAME = "https://www.haozu.com/";

    /**
     * 获取全部楼盘详情页链接
     * @return
     * @throws IOException
     */
    public List<HaozuXzlDetailVo> getAllDetails(String city) throws IOException {
        List<String> pageUrls = new ArrayList<>();
        String listUrl = DOMAIN_NAME + city + "/zuxiezilou/";
        pageUrls.add(listUrl);
        // 1.获取所有列表页url
        getAllPageUrls(listUrl, pageUrls);

        // 2.获取全部楼盘详情页链接
        List<String> loupanUrls = new ArrayList<>();
        for (String pageUrl : pageUrls) {
            loupanUrls.addAll(getListUrlInfo(pageUrl));
        }

        // 3.获取全部楼盘详情
        List<HaozuXzlDetailVo> haozuXzlDetailVos = new ArrayList<>();
        for (String listInfo : loupanUrls) {
            HaozuXzlDetailVo loupanDetailInfo = getLoupanDetailInfo(listInfo);
            haozuXzlDetailVos.add(loupanDetailInfo);
        }
        return haozuXzlDetailVos;
    }

    /**
     * 翻页(获取所有列表页url)
     * @param listUrl
     * @throws IOException
     */
    private void getAllPageUrls(String listUrl, List<String> pageUrls) throws IOException {
        Document document = Jsoup.connect(listUrl).get();

        // 获取分页信息
        Elements pageDivElements = document.select("[class=pageLink clearfix]");
        if (pageDivElements == null || pageDivElements.size() == 0) {
            return;
        }
        Elements aElements = pageDivElements.select("[class=next]");
        if (aElements == null || aElements.size() == 0) {
            return;
        }
        String pageUrl = DOMAIN_NAME + aElements.attr("href");
        pageUrls.add(pageUrl);

        listUrl = pageUrl;
        getAllPageUrls(listUrl, pageUrls);

    }


    /**
     * 获取某页列表页数据
     * @param listUrl
     * @throws IOException
     */
    private List<String> getListUrlInfo(String listUrl) throws IOException {

        List<String> urls = new ArrayList<>();
        Document document = Jsoup.connect(listUrl).get();

        // 获取列表页信息
        Elements listdivElements = document.select("[class=listCon propertyList]");
        Elements listliElements = listdivElements.select("li");
        for (Element liElement : listliElements) {
            Elements aElements = liElement.select("a");
            String loupanUrl = DOMAIN_NAME + aElements.attr("href");
            urls.add(loupanUrl);
        }
        return urls;
    }



    /**
     * 获取楼盘详情页具体信息
     * @return
     * @throws IOException
     */
    private HaozuXzlDetailVo getLoupanDetailInfo(String pageUrls) throws IOException {

        HaozuXzlDetailVo vo = new HaozuXzlDetailVo();
        Document document = Jsoup.connect(pageUrls).get();

        // url
        vo.setUrl(pageUrls);
        // 楼盘id
        int start = pageUrls.lastIndexOf("_");
        int end = pageUrls.lastIndexOf("/");
        vo.setLoupanId(pageUrls.substring(start + 1, end));
        // 楼盘名
        Elements loupanNameDivElements = document.select("[class=title-h1 clearfix]");
        vo.setLoupanName(loupanNameDivElements.text());
        // 区域 商圈
        Elements districtDivElements = document.select("[class=house-address]");
        Elements aElements = districtDivElements.select("a");
        vo.setDistrict(aElements.get(0).text());
        vo.setBlock(aElements.get(1).text());
        // 地址
        String address = districtDivElements.select("span").get(0).childNode(3).attributes().get("#text");
        vo.setAddress(address.trim());
        // 在租房源量 可租面积 建筑面积 总楼层
        Elements houseAboutDivElements = document.select("[class=house-about fr]");
        Elements houseInfoElements = houseAboutDivElements.select("[class=house-info clearfix]");
        vo.setTotal(houseInfoElements.get(0).select("i").get(0).text());
        vo.setRentArea(houseInfoElements.get(0).select("i").get(1).text());
        vo.setBuildingArea(houseInfoElements.get(1).select("i").get(0).text());
        vo.setTotalFloor(houseInfoElements.get(1).select("i").get(2).text());

        // 总楼层 得房率 标准层高
        Elements leftBoxDivElements = document.select("[class=leftBox fl]");
        Elements overviewElements = leftBoxDivElements.select("[class=overview]");
        vo.setTotalFloor(overviewElements.select("li").get(0).select("[class=s2]").text());
        vo.setRoomRate(overviewElements.select("li").get(2).select("[class=s2]").text());
        vo.setHeight(overviewElements.select("li").get(3).select("[class=s2]").text());
        // 楼盘均价 商圈均价 区域均价
        Elements priceBoxDivElements = document.select("[class=price-box clearfix]");
        Elements priceIElements = priceBoxDivElements.select("i");
        vo.setLoupanAveragePrice(priceIElements.get(0).text());
        vo.setBlockAveragePrice(priceIElements.get(2).text());
        vo.setDistrictAveragePrice(priceIElements.get(4).text());
        return vo;
    }



}
