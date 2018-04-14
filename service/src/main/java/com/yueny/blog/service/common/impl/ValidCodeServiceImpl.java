/**
 * 
 */
package com.yueny.blog.service.common.impl;

import com.yueny.blog.service.BaseBiz;
import com.yueny.blog.service.common.IProfileService;
import com.yueny.blog.service.common.IValidCodeService;
import com.yueny.rapid.message.email.sender.EmailTemplateSenderHelper;
import com.yueny.rapid.message.email.sender.EmailType;
import com.yueny.rapid.message.email.sender.core.EmailMessage;
import com.yueny.rapid.message.email.sender.entity.ThreadEmailEntry;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 验证码服务
 * 
 * @author yueny09 <yueny09@163.com>
 * 
 * @DATE 2017年9月25日 下午9:16:02
 *
 */
@Service
public class ValidCodeServiceImpl extends BaseBiz implements IValidCodeService {
	@Autowired
	private IProfileService profileService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 验证码被动过期时间
	 */
	private Long smsCodeExpireSecond = 300L;

	@Override
	public boolean validateCode(String target, String smsCode) {
		logger.debug("邮箱：{} 的验证码验证。", target);

		if(StringUtils.isEmpty(smsCode)){
			return false;
		}

		String keys = getCapiCacheKey(target);
		String cacheSmsCode = stringRedisTemplate.opsForValue().get(keys);
		if(StringUtils.isEmpty(cacheSmsCode)) {
			return false;
		}

		boolean isOK = smsCode.equals(cacheSmsCode);

		//如果登陆成功，则作该废验证码
		if(isOK){
			stringRedisTemplate.delete(keys);
		}
		return isOK;
	}

	@Override
	public boolean sendCode(String target) {
		logger.debug("邮箱：{} 的验证码发送。", target);

		String smsCode = setAndCacheSmsCode(target);

		Future<ThreadEmailEntry> future = sendMail(target, smsCode);

		try {
			future.get();
			return true;
		} catch (ExecutionException | InterruptedException var5) {
			logger.error("邮件发送异常", var5);
		}

		return false;
	}

	/**
	 * 生成并且缓存短信验证码<br>
	 * 缓存时间5分钟
	 */
	private String setAndCacheSmsCode(final String target) {
		String smsCode = getFixLenthString(6);

		stringRedisTemplate.opsForValue().set(getCapiCacheKey(target), smsCode, smsCodeExpireSecond, TimeUnit.SECONDS);

		return smsCode;
	}

	/**
	 * 得到验证码需要缓存的key键
	 */
	private String getCapiCacheKey(final String target) {
		return "CAPI_CACHE_KEY" + "_" + target;
	}

	/**
	 * 生成指定位数数字字符串
	 *
	 * @param strLength
	 * @return
	 */
	private String getFixLenthString(int strLength) {
		Random rm = new Random();
		// 获得随机数
		double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
		// 将获得的获得随机数转化为字符串
		String fixLenthString = String.valueOf(pross);
		// 返回固定的长度的随机数
		return fixLenthString.substring(1, strLength + 1);
	}

	/**
	 * 发送邮件
	 */
	private Future<ThreadEmailEntry> sendMail(String target, String smsCode){
		final EmailMessage emailMessage = new EmailMessage("验证码:" + smsCode, "登录验证码");
		emailMessage.to(target);

		final Future<ThreadEmailEntry> future = EmailTemplateSenderHelper.send(emailMessage);

		return future;
	}

}
