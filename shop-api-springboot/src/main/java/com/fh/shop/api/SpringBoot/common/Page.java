package com.hou.springboot.api.brand.hou.common;

import java.io.Serializable;

public class Page implements Serializable {
    //第几页
    private Integer draw;
    //其实下标
    private Integer start;
    //每页条数
    private Integer length;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Page() {
        super();
    }


}
