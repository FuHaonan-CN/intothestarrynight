package service;

import com.hzvtc.starrynight.entity.TUserInfo;

/**
 * @Description: UserServiceImpl
 * @Author: fhn
 * @Date: 2018/12/23 21:51
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 新增一个用户
     * @Param: TUserInfo
     */
    public void save(TUserInfo user);

    /**
     * 根据id删除一个用户
     * @Param: name
     */
    public void deleteById(Long id);


}
