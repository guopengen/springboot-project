package com.wangpf.springbootrequestAndResponse.core.service;

import com.wangpf.springbootrequestAndResponse.core.entity.UserInfo;

/**
 * @Description
 * @Author wangpengfei101022
 * @Date 2020/9/21 21:19
 */
public interface UserInfoService {
    /**
     * 增加用户信息
     *
     * @param userInfo 用户信息
     */
    void addUserInfo(UserInfo userInfo);

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    UserInfo getByName(Integer id);

    /**
     * 修改用户信息
     *
     * @param userInfo 用户信息
     * @return 用户信息
     */
    UserInfo updateUserInfo(UserInfo userInfo);

    /**
     * 删除用户信息
     *
     * @param id 用户ID
     */
    void deleteById(Integer id);
}
