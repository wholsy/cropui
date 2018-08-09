/**
 * 
 */
package com.yueny.blog.service;

/**
 * 验证码服务
 * 
 * @author yueny09 <yueny09@163.com>
 * 
 * @DATE 2017年9月25日 下午9:14:21
 *
 */
public interface IValidCodeService {
	/**
	 * 校验短信验证码是否正确<br>
	 * @param target
	 * 			用户手机号/邮箱
	 * @param smsCode
	 * 			用户输入的短信验证码
	 * @return
	 */
	boolean validateCode(final String target, final String smsCode);
	
	/**
	 * 发送验证码
	 * @param target 发送目的地
	 * @return 发送结果
	 */
	boolean sendCode(final String target);

}
