package com.flymegoc.cc.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.flymegoc.cc.model.Category;
import com.flymegoc.cc.model.Picture;
import com.flymegoc.cc.model.huaban.FileBean;
import com.flymegoc.cc.model.huaban.HuaBanRoot;
import com.flymegoc.cc.model.huaban.PinsBean;
import com.flymegoc.cc.model.huaban.board.BoardRoot;
import com.flymegoc.cc.model.huaban.pins.PinsRoot;
import com.flymegoc.cc.service.HuaBanService;
import com.flymegoc.cc.service.PictureService;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**抓取并保存数据
 * Created by flymegoc on 2017/4/12.
 */
@Component
public class HuaBanUtils {

    @Autowired
    private PictureService pictureService;
    DecimalFormat decimalFormat=new DecimalFormat("#.00");
    //主页面抓取
    public void getHuaBan(HuaBanService huaBanService, String url, Category category) {
        Call<ResponseBody> call = huaBanService.getHuaban(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    //开始解析
                    String sourceStr = response.body().string();
                    String patternStr = "app.page\\[\"pins\"\\] = .*";

                    Pattern pattern = Pattern.compile(patternStr);

                    Matcher matcher = pattern.matcher(sourceStr);
                    while (matcher.find()) {
                        String jsonStr = matcher.group(0).replace("app.page[\"pins\"] = ", "").replace(";", "");

                        System.out.println(jsonStr);
                        ObjectMapper mapper = new ObjectMapper();
                        List<HuaBanRoot> huaBanRootList = mapper.readValue(jsonStr, new TypeReference<List<HuaBanRoot>>() {
                        });
                        Date date=new Date();
                        for (HuaBanRoot huaBanRoot : huaBanRootList) {

                            Picture picture=new Picture();
                            picture.setPictureBoardId(huaBanRoot.getBoard_id()+"");
                            picture.setPicturePinId(huaBanRoot.getPin_id()+"");

                            FileBean fileBean=huaBanRoot.getFile();
                            picture.setId(fileBean.getId());
                            picture.setPictureBucket(fileBean.getBucket());
                            picture.setPictureKey(fileBean.getKey());
                            if(!fileBean.getType().equals("image/jpeg")&&!fileBean.getType().equals("image/png")){
                                continue;
                            }
                            picture.setPictureType(fileBean.getType());
                            picture.setPictureWidth(fileBean.getWidth());
                            picture.setPictureHeight(fileBean.getHeight());
                            double ratio=(double) fileBean.getWidth()/fileBean.getHeight();
                            picture.setPictureRatio(Double.valueOf(decimalFormat.format(ratio)));
                            //picture.setSprPictureReads(0);
                            picture.setPictureLikes(0);
                            picture.setCategory(category);
                            picture.setPictureCreateTime(date);
                            picture.setPictureUpdateTime(date);
                            savePicture(picture);
                            getBoards(huaBanService,huaBanRoot.getBoard_id(), category);
                            getPins(huaBanService,huaBanRoot.getPin_id(),category);

                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    //照片墙
    public void getPins(HuaBanService huaBanService,int pinId,Category category) {
        Call<ResponseBody> call1 = huaBanService.getPin(pinId);
        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    //开始解析
                    String sourceStr = response.body().string();
                    String patternStr = "app.page\\[\"pin\"\\] = .*";

                    Pattern pattern = Pattern.compile(patternStr);

                    Matcher matcher = pattern.matcher(sourceStr);
                    while (matcher.find()) {
                        String jsonStr = matcher.group(0).replace("app.page[\"pin\"] = ", "").replace(";", "");
                        ObjectMapper mapper = new ObjectMapper();
                        PinsRoot pinsRoot = mapper.readValue(jsonStr, PinsRoot.class);
                        Date date=new Date();
                        for (PinsBean pinsBean : pinsRoot.getBoard().getPins()) {

                            Picture picture=new Picture();
                            picture.setPictureBoardId(pinsBean.getBoard_id()+"");
                            picture.setPicturePinId(pinsBean.getPin_id()+"");

                            FileBean fileBean=pinsBean.getFile();
                            picture.setId(fileBean.getId());
                            picture.setPictureBucket(fileBean.getBucket());
                            picture.setPictureKey(fileBean.getKey());
                            if(!fileBean.getType().equals("image/jpeg")&&!fileBean.getType().equals("image/png")){
                                continue;
                            }
                            picture.setPictureType(fileBean.getType());
                            picture.setPictureWidth(fileBean.getWidth());
                            picture.setPictureHeight(fileBean.getHeight());
                            double ratio=(double)fileBean.getWidth()/fileBean.getHeight();
                            picture.setPictureRatio(Double.valueOf(decimalFormat.format(ratio)));
                            //picture.setSprPictureReads(0);
                            picture.setPictureLikes(0);
                            picture.setCategory(category);
                            picture.setPictureCreateTime(date);
                            picture.setPictureUpdateTime(date);

                            savePicture(picture);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    //画板
    public void getBoards(HuaBanService huaBanService, int boardId, Category category) {
        Call<ResponseBody> call2 = huaBanService.getBoard(boardId);
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    //开始解析
                    String sourceStr = response.body().string();
                    String patternStr = "app.page\\[\"board\"\\] = .*";

                    Pattern pattern = Pattern.compile(patternStr);

                    Matcher matcher = pattern.matcher(sourceStr);
                    while (matcher.find()) {
                        String jsonStr = matcher.group(0).replace("app.page[\"board\"] = ", "").replace(";", "");
                        ObjectMapper objectMapper = new ObjectMapper();
                        BoardRoot boardRoot = objectMapper.readValue(jsonStr, BoardRoot.class);
                        Date date=new Date();
                        for (PinsBean pinsBean : boardRoot.getPins()) {

                            Picture picture=new Picture();
                            picture.setPictureBoardId(pinsBean.getBoard_id()+"");
                            picture.setPicturePinId(pinsBean.getPin_id()+"");

                            FileBean fileBean=pinsBean.getFile();
                            picture.setId(fileBean.getId());
                            picture.setPictureBucket(fileBean.getBucket());
                            picture.setPictureKey(fileBean.getKey());
                            if(!fileBean.getType().equals("image/jpeg")&&!fileBean.getType().equals("image/png")){
                                continue;
                            }
                            picture.setPictureType(fileBean.getType());
                            picture.setPictureWidth(fileBean.getWidth());
                            picture.setPictureHeight(fileBean.getHeight());
                            double ratio=(double)fileBean.getWidth()/fileBean.getHeight();
                            picture.setPictureRatio(Double.valueOf(decimalFormat.format(ratio)));
                            //picture.setSprPictureReads(0);
                            picture.setPictureLikes(0);
                            picture.setCategory(category);
                            picture.setPictureCreateTime(date);
                            picture.setPictureUpdateTime(date);

                            savePicture(picture);
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }
    //将图片保存到数据库中
    private void savePicture(Picture picture){
        if (picture.getId()<=0){
            return;
        }
        System.out.println("-------->>>>>>>>>>>>>>>>>>>>"+picture.getId());
        pictureService.save(picture);
    }
}
