package com.denachina.shadow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JpaRepository扩展了PagingAndSortingRepository，后者又扩展了CrudRepository。
 * 1、CrudRepository主要提供CRUD功能。所以它可以让你创建，读取，更新和删除记录，而不必定义自己的方法。
 * 2、PagingAndSortingRepository提供了进行分页和排序记录的方法。
 * 3、JpaRepository提供了一些与JPA相关的方法，例如刷新持久化上下文和批量删除记录。
 */
@Repository("UserDataRepository")
public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    UserData findTopByJobNameOrderByCreatedOnDesc(String jobName);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update UserData set jobName = ?1 where userId = ?2")
    int updateJobName(String jobName,Integer userId);
}
