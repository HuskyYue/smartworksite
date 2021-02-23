package com.slzhkj.smartworksite.model.dto;

import lombok.Data;

/**
 * 数据接入Dto
 *
 * @author Yuezejian
 * @date 2020年 11月03日 10:53:24
 */
@Data
public class DataAcceptDto {

    private String paramOne;

    private String paramTwo;

    private String paramThree;

    private String paramFour;

    private String paramFive;

    private String paramSix;

    private String paramSeven;

    private String paramEight;

    private String paramNine;

    private String paramTen;

    private String paramEleven;

    private String paramTwelve;

    private String paramThirteen;

    private String paramFourteen;

    private String paramFifteen;

    private String paramSixteen;

    public DataAcceptDto(String paramOne, String paramTwo) {
        this.paramOne = paramOne;
        this.paramTwo = paramTwo;
    }
}
