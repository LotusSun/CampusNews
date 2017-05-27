/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.model;

import cn.bmob.v3.BmobObject;

public class ScoreBean extends BmobObject {
    private String name;
    private String Polical;
    private String English;
    private String Math;
    private String Profession;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPolical() {
        return Polical;
    }

    public void setPolical(String polical) {
        Polical = polical;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getMath() {
        return Math;
    }

    public void setMath(String math) {
        Math = math;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }
}
