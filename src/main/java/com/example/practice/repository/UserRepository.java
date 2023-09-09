package com.example.practice.repository;


import com.example.practice.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name); // query method

    // where name = ? and email = ?
    Optional<User> findByNameAndEmail(String name, String email);

    // where name = ? or email = ?
    List<User> findByNameOrEmail(String name, String email);

    // where user_id between ? and ?
    List<User> findByUserIdBetween(int startUserId, int endUserId);

    // where user_id < ?
    List<User> findByUserIdLessThan(int UserId);

    // where user_id <= ?
    List<User> findByUserIdLessThanEqual(int UserId);

    // where user_id > ?
    List<User> findByUserIdGreaterThan(int UserId);

    // where user_id >= ?
    List<User> findByUserIdGreaterThanEqual(int UserId);

    // where regdate > ?
    List<User> findByRegdateAfter(LocalDateTime day);

    // where regdate < ?
    List<User> findByRegdateBefore(LocalDateTime day);

    // where name is null
    List<User> findByNameIsNull();

    // where name is not null
    List<User> findByNameIsNotNull();

    // where name like ?
    List<User> findByNameIsLike(String name);

    // where name like ?%
    List<User> findByNameStartingWith(String name);

    // where name like %?
    List<User> findByNameEndingWith(String name);

    // where name like %?%
    List<User> findByNameContaining(String name);

    // order by name asc
    List<User> findByOrderByNameAsc();

    // order by name desc
    List<User> findByOrderByNameDesc();

    // where regdate > ? order by name desc
    List<User> findByRegdateAfterOrderByNameDesc(LocalDateTime day);

    // where name <> ?
    List<User> findByNameNot(String name); // null is not displayed

    // where user_id in (.....)
    List<User> findByUserIdIn(Collection<Integer> userIds);

    // where user_id not in (.....)
    List<User> findByUserIdNotIn(Collection<Integer> userIds);

//    // where flag = true
//    List<User> findByFlagTrue();
//
//    // where flag = false
//    List<User> findByFlagFalse();

    // select count(*) from user3
    Long countBy();

    Long countByNameLike(String name);

    boolean existsByEmail(String name);

    // select * from user3 where name = ? -> find the user_id by using the name.
    // delete from user3 where user_id = ? -> delete the user by using user_id that found.
    int deleteByNameLike(String name);

    // select distinct * from user3 where name ?
    List<User> findDistinctByName(String name);

    // select * from user3 limit 2
    List<User> findFirst2By();
    List<User> findTop2By();

    //select * from user3 order by regdate desc;
    Page<User> findBy(Pageable pageable);

    Page<User> findByName(String name, Pageable pageable);
}

// Page<Board> findBy(Pageable pageable);
// Page<Board> findByName(String name, Pageable pageable);
// Page<Board> findByTitleContaining(String title, Pageable pageable);
// Page<Board> findByContentContaining(String content, Pageable pageable);
// Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
