package pachong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThreeFangXzlInfo {

    private static final String DOMAIN_NAME = "https://office.3fang.com";

    /**
     * 获取全部楼盘详情页链接
     * @return
     * @throws IOException
     */
    public List<ThreeFangXzlDetailVo> getAllDetails(String city) throws IOException {
        List<String> pageUrls = new ArrayList<>();
        String listUrl = DOMAIN_NAME + city +"/list/loupan";
        pageUrls.add(listUrl);
        // 1.获取所有列表页url
        getAllPageUrls(listUrl, pageUrls);

        // 2.获取全部楼盘详情页链接
        List<String> loupanUrls = new ArrayList<>();
        for (String pageUrl : pageUrls) {
            loupanUrls.addAll(getListUrlInfo(pageUrl));
        }

        // 3.获取全部楼盘详情
        List<ThreeFangXzlDetailVo> threeFangXzlDetailVos = new ArrayList<>();
        for (String listInfo : loupanUrls) {
            ThreeFangXzlDetailVo threeFangXzlDetailVo = getLoupanDetailInfo(listInfo);
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
        Elements target = pageDivElements.select("[class=on]");
        if (target == null || target.size() == 0) {
            return;
        }
        Elements next = target.next();
        if (next == null || next.attr("style").equals("display:none")) {
            return;
        }
        Elements aElement = next.select("a");
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
        Elements listdivElements = document.select("[class=shop_list sh-list_n1]");
        Elements listliElements = listdivElements.select("dl");
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
    private ThreeFangXzlDetailVo getLoupanDetailInfo(String pageUrls) throws IOException {

        ThreeFangXzlDetailVo vo = new ThreeFangXzlDetailVo();
        Document document = Jsoup.connect(pageUrls).get();
        // url
        vo.setUrl(pageUrls);
        // 楼盘id
        int start = pageUrls.lastIndexOf("/");
        vo.setLoupanId(pageUrls.substring(start + 1));
        // 楼盘名
        Elements loupanNameDivElements = document.select("[class=lp-sum_info]");
        vo.setLoupanName(loupanNameDivElements.select("h4").text());
        // 区域 商圈 地址
        Elements districtDivElements = loupanNameDivElements.select("[class=tagTips]");
        Element spanElement0 = districtDivElements.select("span").get(0);
        String[] spanElementSplit = spanElement0.text().split("-");
        vo.setDistrict(spanElementSplit[0]);
        vo.setBlock(spanElementSplit[1]);
        Element spanElement1 = districtDivElements.select("span").get(1);
        vo.setAddress(spanElement1.text());
        // 在租房源量 在售房源量 出租房源列表 出售房源列表
        Elements linkOtherDivElements = document.select("[class=link-other clearfix]");
        Elements infTxtDivElements = linkOtherDivElements.select("[class=inf-txt]");
        Elements spans = infTxtDivElements.select("span");
        vo.setTotalOnRent(spans.get(0).text());
        vo.setTotalOnSale(spans.get(1).text());
        Elements a = linkOtherDivElements.select("a");
        vo.setRentLink(DOMAIN_NAME + a.get(0).attr("href"));
        vo.setSaleLink(DOMAIN_NAME + a.get(1).attr("href"));
        return vo;
    }

}
