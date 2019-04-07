package com.hzvtc.starrynight.repository;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;
/**
 * @Description: JPA使用原生SQL基类
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 * @Version: 1.0
 */
@Service
public class BaseNativeSqlRepository {

    @PersistenceUnit
    private EntityManagerFactory emf;

    /**
     * 查询多个属性
     * 返回List<Object[]>数组形式的List，数组中内容按照查询字段先后
     * @Param: sql   原生SQL语句
     * @Return:
     */
    public List<Object[]> sqlArrayList(String sql){
        EntityManager em=emf.createEntityManager();
        Query query=em.createNativeQuery(sql);
        List<Object[]> list = query.getResultList();
        em.close();
        return  list;
    }

    /**
     * 查询多个属性
     * 返回List<Object>对象形式的List，Object为Class格式对象
     * @Param: sql   原生SQL语句
     * @Param: obj   Class格式对象
     * @Return:
     */
    public List sqlObjectList(String sql, Object obj){
        EntityManager em=emf.createEntityManager();
        Query query=em.createNativeQuery(sql,obj.getClass());
        List list = query.getResultList();
        em.close();
        return  list;
    }

    /**
     * 查询单个属性
     * 返回List<Object>对象形式的List，Object为对象数据类型
     * @Param:  sql  原生SQL语句
     * @Return:
     */
    public List sqlSingleList(String sql){
        EntityManager em=emf.createEntityManager();
        Query query=em.createNativeQuery(sql);
        List list = query.getResultList();
        em.close();
        return  list;
    }

}
