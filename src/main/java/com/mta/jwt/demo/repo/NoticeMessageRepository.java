package com.mta.jwt.demo.repo;

import com.mta.jwt.demo.entity.acl.NoticeMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMessageRepository extends JpaRepository<NoticeMessage,Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    List<NoticeMessage> findAll();

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    NoticeMessage findById(Integer id);

    @SuppressWarnings("unchecked")
    @PreAuthorize("hasPermission(#noticeMessage, 'WRITE')")
    NoticeMessage save(@Param("noticeMessage") NoticeMessage noticeMessage);

}
