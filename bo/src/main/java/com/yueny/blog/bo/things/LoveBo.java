package com.yueny.blog.bo.things;

import com.yueny.superclub.api.pojo.instance.AbstractBo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yueny on 2018/10/11 0011.
 */
@Getter
@Setter
@Builder
public class LoveBo extends AbstractBo {
    /** fullYear */
    private Integer fullYear;
    /** month */
    private Integer month;
    /** day */
    private Integer day;
    /** hours */
    private Integer hours;
    /** minutes */
    private Integer minutes;
    /** seconds */
    private Integer seconds;
    /** milliseconds */
    private Integer milliseconds;
    /** 男士 */
    private String mr;
    /** 女士 */
    private String mrs;

    /** 正文 */
    private String context;
    /** message */
    private String message;
    /** loveu */
    private String loveu;

}
