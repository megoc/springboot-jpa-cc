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

    public void parse() {
        try {
            Document doc = Jsoup.connect("http://www.cnbeta.com/").get();
            Elements items = doc.body().getElementsByClass("items-area").first().getElementsByClass("item");
            for (int i = 0; i < items.size(); i++) {
                Element dl = items.get(i).select("dl").first();
                if (dl != null) {
                    String title = dl.select("dt").text();
                    String url = dl.select("dt").select("a").attr("href").toString();
                    String summary = dl.select("dd").text();
                    String imgUrl = dl.select("img").attr("src");

                    System.out.println("标题：" + title);
                    System.out.println("正文链接：" + url);
                    System.out.println("简介：" + summary);
                    System.out.println("图片链接：" + imgUrl);

                    int startIndex = url.lastIndexOf("/");
                    int endIndex = url.lastIndexOf(".");
                    long id = Long.parseLong(url.substring(startIndex + 1, endIndex));
                    System.out.println("id:" + id);

                    News news = newsService.findById(id);
                    if (news != null) {
                        news.setSummary(summary);
                        news.setImgUrl(imgUrl);
                    } else {
                        news = new News();
                        news.setId(id);
                        news.setTitle(title);
                        news.setContentUrl(url);
                        news.setSummary(summary);
                        news.setImgUrl(imgUrl);
                        news.setConmentCount(0);
                        news.setViewCount(0);
                        news.setNewCreateTime(new Date());
                        news.setNewUpdateTime(new Date());
                    }
                    System.out.println("\n");


                    newsService.save(news);
                }
            }
            Element els = doc.getElementsByClass("cnbeta-home-hero-figures").first();
            Elements elss = els.getElementsByClass("item");
            for (Element element : elss) {
                String figureImgUrl = element.getElementsByClass("figure-img").select("img").attr("src");
                System.out.println(figureImgUrl);
                String figureTitle = element.getElementsByClass("figure-title").text();
                System.out.println(figureTitle);
                String figureContentUrl = element.select("a").attr("href");
                System.out.println(figureContentUrl);

                int startIndex = figureContentUrl.lastIndexOf("/");
                int endIndex = figureContentUrl.lastIndexOf(".");
                long id = Long.parseLong(figureContentUrl.substring(startIndex + 1, endIndex));
                System.out.println("id:" + id);

                News news = newsService.findById(id);
                if (news != null) {
                    news.setFigureImgUrl(figureImgUrl);
                } else {
                    news = new News();
                    news.setId(id);
                    news.setTitle(figureTitle);
                    news.setSummary("");
                    news.setImgUrl("");
                    news.setContentUrl(figureContentUrl);
                    news.setFigureImgUrl(figureImgUrl);
                    news.setConmentCount(0);
                    news.setViewCount(0);
                    news.setNewCreateTime(new Date());
                    news.setNewUpdateTime(new Date());
                }
                newsService.save(news);
                System.out.println("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取新闻正文
     *
     * @param url 正文链接
     * @return 正文
     */
    public static String getNewsContent(String url) {
        String content = "";
        Element el = null;
        Element summary = null;
        try {
            Document doc = Jsoup.connect(url).get();
            summary = doc.getElementsByClass("article-summary").first();
            el = doc.getElementById("artibody");
//                Elements imgs = el.select("img");
//                for (Element element:imgs){
//                    element.attr("width","100%");
//                }
            content = summary.toString() + el.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
