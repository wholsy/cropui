// package com.yueny.cropui.service.shiro;
//
// import org.apache.shiro.authc.AuthenticationException;
// import org.apache.shiro.authc.AuthenticationInfo;
// import org.apache.shiro.authc.AuthenticationToken;
// import org.apache.shiro.authc.SimpleAuthenticationInfo;
// import org.apache.shiro.authz.AuthorizationInfo;
// import org.apache.shiro.realm.AuthorizingRealm;
// import org.apache.shiro.subject.PrincipalCollection;
//
/// **
// * 自定义Realm
// *
// * @author yueny09 <deep_blue_yang@163.com>
// *
// * @DATE 2016年4月10日 下午7:11:47
// *
// */
// public class UserAuthorizingRealm extends AuthorizingRealm {
// // @Resource
// // private BloggerService bloggerService;
//
// /**
// * 验证当前登录的用户
// */
// @Override
// protected AuthenticationInfo doGetAuthenticationInfo(
// final AuthenticationToken token) throws AuthenticationException {
// final String userName = (String) token.getPrincipal();
// // Blogger blogger=bloggerService.getByUserName(userName);
// if (true) {
// // SecurityUtils.getSubject().getSession().setAttribute("currentUser",
// // blogger); // ��ǰ�û���Ϣ�浽session��
// final AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
// "yuanyang", "yuanyang", "xx");
// // AuthenticationInfo authcInfo=new
// //
// SimpleAuthenticationInfo(blogger.getUserName(),blogger.getPassword(),"xx");
// return authcInfo;
// } else {
// return null;
// }
// }
//
// /**
// * 为当限前登录的用户授予角色和权
// */
// @Override
// protected AuthorizationInfo doGetAuthorizationInfo(
// final PrincipalCollection principals) {
// return null;
// }
//
// }
