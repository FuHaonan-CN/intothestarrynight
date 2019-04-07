package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.PictureInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 图片信息表PictureInfo数据库CDUQ
 * @Author: fhn
 * @Date: 2017/1/18 19:34
 * @Version: 1.0
 **/
public interface PictureInfoRepo extends JpaRepository<PictureInfo, Long> {

}
