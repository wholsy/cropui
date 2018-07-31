package com.yueny.blog.service.impl;

import com.yueny.blog.service.IPasswdService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yueny on 2018/4/16 0016.
 */
public class PasswdServiceTest {
    private IPasswdService passwdService = new PasswdServiceImpl();

    @Test
    public void testGetEncodeData(){
        String ps = passwdService.getEncodeData("liyu.211314.", "");
        Assert.assertEquals(ps, "2c94aa72146ac190916005ff83ecf1f5");
    }

}
