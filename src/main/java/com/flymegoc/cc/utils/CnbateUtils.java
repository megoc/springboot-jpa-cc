package com.flymegoc.cc.utils;

import com.flymegoc.cc.model.News;
import com.flymegoc.cc.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class CnbateUtils {

    @Autowired
    private NewsService newsService;

    public  void parse(){
        try {
            Document doc = Jsoup.connect("http://www.cnbeta.com/").get();
            Elements items = doc.body().getElementsByClass("items-area").first().getElementsByClass("item");
            for (int i=0;i<items.size();i++){
                Element dl = items.get(i).select("dl").first();
                if (dl!=null){
                    String title=dl.select("dt").text();
                    String url=dl.select("dt").select("a").attr("href").toString();
                    String summary=dl.select("dd").text();
                    String imgUrl=dl.select("img").attr("src");

                    System.out.println("标题："+title);
                    System.out.println("正文链接："+url);
                    System.out.println("简介："+summary);
                    System.out.println("图片链接："+imgUrl);

                    int startIndex=url.lastIndexOf("/");
                    int endIndex=url.lastIndexOf(".");
                    long id= Long.parseLong(url.substring(startIndex+1,endIndex));
                    System.out.println("id:"+id);

                    System.out.println("\n");

                    News news=new News();
                    news.setId(id);
                    news.setTitle(title);
                    news.setContentUrl(url);
                    news.setSummary(summary);
                    news.setImgUrl(imgUrl);
                    news.setConmentCount(0);
                    news.setViewCount(0);
                    news.setNewCreateTime(new Date());
                    news.setNewUpdateTime(new Date());

                    newsService.save(news);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
