package com.flymegoc.cc.jobs;

import com.flymegoc.cc.model.Category;
import com.flymegoc.cc.service.CategoryService;
import com.flymegoc.cc.service.HuaBanService;
import com.flymegoc.cc.utils.CnbateUtils;
import com.flymegoc.cc.utils.HuaBanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 定时任务
 * Created by flymegoc on 2017/4/11.
 */
@Component
public class Jobs {

    private String[] names = {"造型美妆", "美食", "旅行", "手工布艺", "健身舞蹈", "儿童", "宠物", "美图", "明星", "美女", "礼物", "极客", "动漫", "建筑设计", "人文艺术", "数据图", "游戏", "汽车摩托", "电影图书", "生活百科", "教育", "运动", "搞笑"};
    private String[] urls = {
            "http://huaban.com/favorite/modeling_hair/",
            "http://huaban.com/favorite/food_drink/",
            "http://huaban.com/favorite/travel_places/",
            "http://huaban.com/favorite/diy_crafts/",
            "http://huaban.com/favorite/fitness/",
            "http://huaban.com/favorite/kids/",
            "http://huaban.com/favorite/pets/",
            "http://huaban.com/favorite/quotes/",
            "http://huaban.com/favorite/people/",
            "http://huaban.com/favorite/beauty/",
            "http://huaban.com/favorite/desire/",
            "http://huaban.com/favorite/geek/",
            "http://huaban.com/favorite/anime/",
            "http://huaban.com/favorite/architecture/",
            "http://huaban.com/favorite/art/",
            "http://huaban.com/favorite/data_presentation/",
            "http://huaban.com/favorite/games/",
            "http://huaban.com/favorite/cars_motorcycles/",
            "http://huaban.com/favorite/film_music_books/",
            "http://huaban.com/favorite/tips/",
            "http://huaban.com/favorite/education/",
            "http://huaban.com/favorite/sports/",
            "http://huaban.com/favorite/funny/"
    };

    private static Logger logger = LoggerFactory.getLogger(Jobs.class);

    public final static long ONE_Minute = 60 * 1000*3;

    private final static String BASE_URL = "http://huaban.com/";

    private HuaBanService huaBanService;

    @Autowired
    private CategoryService categoryService;

    private Random mRandom;

    @Autowired
    private HuaBanUtils huaBanUtils;

    @Autowired
    private CnbateUtils cnbateUtils;

    private List<Integer> randomIndexArray;

    //是当任务执行完毕后1分钟在执行
    @Scheduled(fixedDelay = ONE_Minute)
    public void fixedDelayJob() {
        if (huaBanService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            huaBanService = retrofit.create(HuaBanService.class);

            beginGrabHuaBanData();
        } else {

            beginGrabHuaBanData();
        }
    }

    //开始抓取花瓣网数据
    private void beginGrabHuaBanData(){
        if (mRandom==null){
            mRandom=new Random();
        }
        if (randomIndexArray==null){
            randomIndexArray=new ArrayList<>();
        }

        List<Category> categoryList=categoryService.findAll();
        if (categoryList==null||categoryList.size()==0){

            for(int i=0;i<names.length;i++){
                Category category=new Category();
                category.setId(i+1);
                category.setCategoryName(names[i]);
                category.setCategoryUrl(urls[i]);
                category.setCategoryStatus(0);
                category.setCategoryCreateTime(new Date());
                category.setCategoryUpdateTime(new Date());
                categoryService.save(category);
            }
            logger.info("初始化类别完成");
            return;
        }
        //随机
        int index = mRandom.nextInt(categoryList.size());
        while (randomIndexArray.contains(index)) {
            index = mRandom.nextInt(categoryList.size());
        }
        randomIndexArray.add(index);
        logger.info("已经抓取：" + randomIndexArray);
        //重置
        if (randomIndexArray.size() == categoryList.size()) {
            randomIndexArray.clear();
            logger.info("重置抓取。。。");
        }
        //开始抓取并保存数据
        Category category = categoryList.get(index);
        logger.info("---开始抓取" + "---" + category.getCategoryName() + "(" + category.getId() + "）的数据---");
        huaBanUtils.getHuaBan(huaBanService,category.getCategoryUrl(),category);
        //抓取新闻
        cnbateUtils.parse();
    }

//    //就是每多次分钟一次，不论你业务执行花费了多少时间。我都是1分钟执行1次
//    @Scheduled(fixedRate=ONE_Minute)
//    public void fixedRateJob(){
//
//    }
//
//    /**
//     * * 第一位，表示秒，取值0-59
//     * 第二位，表示分，取值0-59
//     * 第三位，表示小时，取值0-23
//     * 第四位，日期天/日，取值1-31
//     * 第五位，日期月份，取值1-12
//     * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
//     另外：1表示星期天，2表示星期一。
//     * 第7为，年份，可以留空，取值1970-2099
//     */
//    @Scheduled(cron="0 15 3 * * ?")
//    public void cronJob(){
//        System.out.println(DateTimeUtils.format_yyyyMMddHHmmss(new Date())+" >>cron执行....");
//    }
}
