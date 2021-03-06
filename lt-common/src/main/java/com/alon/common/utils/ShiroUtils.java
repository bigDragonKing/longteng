package com.alon.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 功能描述:Shiro工具类
 * @param:
 * @return:
 * @auther: zoujiulong
 * @date: 2018/8/28   15:24
 */
public class ShiroUtils {
	/**  加密算法 */
	public final static String hashAlgorithmName = "MD5";
	/**  循环次数 */
	public final static int hashIterations = 1024;

	public static String md5(String password, String salt) {
		return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
	}

	public static String getSalt(){
		return RandomStringUtils.randomAlphanumeric(20);
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

//	public static SysUserEntity getUserEntity() {
//		return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
//	}
//
//	public static Long getUserId() {
//		return getUserEntity().getUserId();
//	}

	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}

	public static String getKaptcha(String key) {
		Object kaptcha = getSessionAttribute(key);
		if(kaptcha == null){
//			throw new RRException("验证码已失效");
		}
		getSession().removeAttribute(key);
		return kaptcha.toString();
	}
}
