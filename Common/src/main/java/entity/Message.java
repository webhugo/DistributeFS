package entity;

import utils.JsonUtils;

import java.io.Serializable;

/**
 * Created by webhugo on 17-7-14.
 */
public class Message implements Serializable{
    private MessageType type;
    //额外补充的信息
    private String extra;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
