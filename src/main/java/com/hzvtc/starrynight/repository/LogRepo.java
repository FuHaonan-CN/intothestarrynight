package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.TLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 日志表Log数据库CDUQ
 * @Author: fhn
 * @Date: 2017/1/18 19:34
 * @Version: 1.0
 **/
public interface LogRepo extends JpaRepository<TLog, Long> {

}
