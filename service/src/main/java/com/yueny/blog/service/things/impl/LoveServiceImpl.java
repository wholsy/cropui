package com.yueny.blog.service.things.impl;

import com.yueny.blog.bo.things.LoveBo;
import com.yueny.blog.service.things.ILoveService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by yueny on 2018/10/11 0011.
 */
@Service
public class LoveServiceImpl implements ILoveService {
    @Value("${things.love.fullYear}")
    private Integer fullYear;
    @Value("${things.love.month}")
    private Integer month;
    /** day */
    @Value("${things.love.day}")
    private Integer day;
    /** hours */
    @Value("${things.love.hours}")
    private Integer hours;
    /** minutes */
    @Value("${things.love.minutes}")
    private Integer minutes;
    /** seconds */
    @Value("${things.love.seconds}")
    private Integer seconds;
    /** milliseconds */
    @Value("${things.love.milliseconds}")
    private Integer milliseconds;
    /** 男士 */
    @Value("${things.love.mr}")
    private String mr;
    /** 女士 */
    @Value("${things.love.mrs}")
    private String mrs;
    /** 正文 */
    @Value("${things.love.context}")
    private String context;
    /** message */
    @Value("${things.love.message}")
    private String message;
    /** loveu */
    @Value("${things.love.loveu}")
    private String loveu;

    public LoveBo queryInfo() {
        return  LoveBo.builder().fullYear(fullYear).month(month)
                .day(day).hours(hours).minutes(minutes)
                .seconds(seconds).milliseconds(milliseconds)
                .mr(mr).mrs(mrs)
                .context(context).message(message).loveu(loveu).build();
    }

}
