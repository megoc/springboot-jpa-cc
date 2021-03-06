package com.flymegoc.cc.model.huaban.pins;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flymegoc.cc.model.huaban.FileBean;
import com.flymegoc.cc.model.huaban.TextMetaBean;

/**
 * Created by flymegoc on 2017/4/12.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViaPinBean {
    /**
     * pin_id : 1098235051
     * user_id : 16883668
     * board_id : 19403052
     * file_id : 132766226
     * file : {"id":132766226,"farm":"farm1","bucket":"hbimg","key":"746d16a57c87db14aaede25bc11551db612476da6a02-oDgpJf","type":"image/jpeg","width":"338","height":"600","frames":"1","colors":[{"color":11447982,"ratio":0.19}],"audit":{"porn":{"rate":0.8333254754543304,"label":0,"review":false}},"theme":"aeaeae"}
     * media_type : 0
     * source : tieba.baidu.com
     * link : http://tieba.baidu.com/photo/p?tid=5020148941&kw=%E6%9D%8E%E6%AF%85&see_lz=1&pic_id=99a1cd11728b47109a69b13bcacec3fdfd032386&red_tag=s0578069251
     * raw_text :
     * text_meta : {}
     * via : 1098183666
     * via_user_id : 15704226
     * original : 1054908422
     * created_at : 1491923790
     * like_count : 1
     * comment_count : 0
     * repin_count : 7
     * is_private : 0
     * orig_source : null
     * board : {"board_id":19403052,"user_id":16883668,"title":"清纯 清新 自然  气质","description":"You'll never know about the beauty of females until you are aged enough.","category_id":"beauty","seq":1,"pin_count":1672,"follow_count":1725,"like_count":23,"created_at":1424920362,"updated_at":1491923951,"deleting":0,"is_private":0,"extra":{"cover":{"pin_id":"331038140"}}}
     */

    private int pin_id;
    private int user_id;
    private int board_id;
    private int file_id;
    private FileBean file;
    private int media_type;
    private String source;
    private String link;
    private String raw_text;
    private TextMetaBean text_meta;
    private int via;
    private int via_user_id;
    private int original;
    private int created_at;
    private int like_count;
    private int comment_count;
    private int repin_count;
    private int is_private;
    private Object orig_source;
    private BoardBean board;

    public int getPin_id() {
        return pin_id;
    }

    public void setPin_id(int pin_id) {
        this.pin_id = pin_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public int getMedia_type() {
        return media_type;
    }

    public void setMedia_type(int media_type) {
        this.media_type = media_type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRaw_text() {
        return raw_text;
    }

    public void setRaw_text(String raw_text) {
        this.raw_text = raw_text;
    }

    public TextMetaBean getText_meta() {
        return text_meta;
    }

    public void setText_meta(TextMetaBean text_meta) {
        this.text_meta = text_meta;
    }

    public int getVia() {
        return via;
    }

    public void setVia(int via) {
        this.via = via;
    }

    public int getVia_user_id() {
        return via_user_id;
    }

    public void setVia_user_id(int via_user_id) {
        this.via_user_id = via_user_id;
    }

    public int getOriginal() {
        return original;
    }

    public void setOriginal(int original) {
        this.original = original;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getRepin_count() {
        return repin_count;
    }

    public void setRepin_count(int repin_count) {
        this.repin_count = repin_count;
    }

    public int getIs_private() {
        return is_private;
    }

    public void setIs_private(int is_private) {
        this.is_private = is_private;
    }

    public Object getOrig_source() {
        return orig_source;
    }

    public void setOrig_source(Object orig_source) {
        this.orig_source = orig_source;
    }

    public BoardBean getBoard() {
        return board;
    }

    public void setBoard(BoardBean board) {
        this.board = board;
    }
}
