package src.pachong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreeFangHouseInfo {

    private static final String DOMAIN_NAME = "https://office.3fang.com";
    private static final String HTTP = "https:";

    /**
     * 获取全部楼盘详情页链接
     * @return
     * @throws IOException
     */
    public List<ThreeFangHouseDetailVo> getAllDetails(String url) throws IOException {
        List<String> pageUrls = new ArrayList<>();
        pageUrls.add(url);
        // 1.获取所有列表页url
        getAllPageUrls(url, pageUrls);

        // 2.获取全部楼盘详情页链接
        List<String> loupanUrls = new ArrayList<>();
        for (String pageUrl : pageUrls) {
            loupanUrls.addAll(getListUrlInfo(pageUrl));
        }

        // 3.获取全部楼盘详情
        List<ThreeFangHouseDetailVo> threeFangXzlDetailVos = new ArrayList<>();
        for (String listInfo : loupanUrls) {
            ThreeFangHouseDetailVo threeFangXzlDetailVo = getLoupanDetailInfo(listInfo);
            threeFangXzlDetailVos.add(threeFangXzlDetailVo);
        }
        return threeFangXzlDetailVos;
    }

    /**
     * 翻页(获取所有列表页url)
     * @param listUrl
     * @throws IOException
     */
    private void getAllPageUrls(String listUrl, List<String> pageUrls) throws IOException {
        Document document = Jsoup.connect(listUrl).get();

        // 获取分页信息
        Elements pageDivElements = document.select("[class=page_box]");
        if (pageDivElements == null || pageDivElements.size() == 0) {
            return;
        }
        Elements target = pageDivElements.select("p");
        if (target == null || target.size() == 0) {
            return;
        }
        Element nextPage = target.stream().filter(t -> t.text().equals("下一页")).collect(Collectors.toList()).get(0);
        if (nextPage == null) {
            return;
        }
        Element aElement = nextPage.parent();
        if (aElement == null || aElement.attr("style").equals("display:none;")) {
            return;
        }
        String pageUrl = DOMAIN_NAME + aElement.attr("href");
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
        Elements listdivElements = document.select("[class=fangListwrap]");
        Elements listliElements = listdivElements.select("dl");
        for (Element liElement : listliElements) {
            Elements aElements = liElement.select("a");
            String loupanUrl = HTTP + aElements.attr("href");
            urls.add(loupanUrl);
        }
        return urls;
    }



    /**
     * 获取楼盘详情页具体信息
     * @return
     * @throws IOException
     */
    private ThreeFangHouseDetailVo getLoupanDetailInfo(String pageUrls) throws IOException {

        ThreeFangHouseDetailVo vo = new ThreeFangHouseDetailVo();
        Document document = Jsoup.connect(pageUrls).get();
        // url
        vo.setUrl(pageUrls);
        // 业务类型
        vo.setBusinessType(pageUrls.substring(8, pageUrls.indexOf(".")));
        // 租售状态
        vo.setRentalCategory(pageUrls.split("/")[4]);
        // 房源ID
        vo.setHouseId(pageUrls.substring(pageUrls.indexOf("_") + 1, pageUrls.lastIndexOf(".")));
        // 房源标题
        vo.setHouseTitle(document.select("[class=cont_tit]").text());
        // 发布时间
        Elements publishTimeElements = document.select("[class=time]");
        vo.setPublishTime(publishTimeElements.get(0).childNode(2).toString());
        // 面积
        Elements areaElements = document.select("[class=wid154]");
        vo.setArea(areaElements.select("b").text());
        // 总价
        Elements priceElements = document.select("[class=wid305]");
        vo.setPriceTotal(priceElements.select("b").text());
        // 楼盘名 区域 商圈 地址
        Elements loupanElements = document.select("[class=tel_area]");
        vo.setLoupanName(loupanElements.select("a").get(0).text());
        vo.setDistrict(loupanElements.select("a").get(1).text());
        vo.setBlock(loupanElements.select("a").get(2).text());
        vo.setAddress(loupanElements.select("span").get(2).text());

        // 发布人姓名 发布人性质 发布人公司 发布人手机号
        Elements publisherElements = document.select("[class=trlcont rel]");
        if (publisherElements != null) {
            Elements publisherNameElements = publisherElements.select("[class=zf_jjname]");
            vo.setPublisher(publisherNameElements.text());
            Elements publisherNatureElements = publisherElements.select("[class=tjcont-list-cline2]");
            Elements publisherNatureSpan = publisherNatureElements.select("span");
            vo.setPublisherNature(publisherNatureSpan.get(0).text());
            vo.setPublisherCompany(publisherNatureSpan.get(1).text());
            Elements publisherPhoneElements = publisherElements.select("[class=tjcont-list-cline3 font16]");
            vo.setPublisherPhone(publisherPhoneElements.text());
        }
        return vo;
    }

}
