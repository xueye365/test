package src.pachong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫
 *
 * https://www.haozu.com/sh/zuxiezilou/  好租页面信息，整个城市的全部写字楼信息
 *
 * https://office.3fang.com/nb/list/loupan  三方页面信息，整个城市的全部写字楼信息，及写字楼下的房源信息
 *
 */
public class Test {

    public static void main(String[] args) {
        try {
            // 好租写字楼信息
            HaozuXzlInfo haozuXzlList = new HaozuXzlInfo();
            List<HaozuXzlDetailVo> haozuDetails = haozuXzlList.getAllDetails("sh");

            // 3房写字楼信息
            ThreeFangXzlInfo threeFangXzlInfo = new ThreeFangXzlInfo();
            List<ThreeFangXzlDetailVo> threeFangXzlDetails = threeFangXzlInfo.getAllDetails("nb");

            // 3房房源详情页
            ThreeFangHouseInfo threeFangHouseInfo = new ThreeFangHouseInfo();
            List<ThreeFangHouseDetailVo> threeFangHouseDetailVo = new ArrayList<>();
            threeFangXzlDetails.stream().forEach(threeFangXzlDetailVo -> {
                try {
                    threeFangHouseDetailVo.addAll(threeFangHouseInfo.getAllDetails(threeFangXzlDetailVo.getRentLink()));
                    threeFangHouseDetailVo.addAll(threeFangHouseInfo.getAllDetails(threeFangXzlDetailVo.getSaleLink()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            System.out.println(haozuDetails);
            System.out.println(threeFangHouseInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
