package com.sample.todolistmybatismysql.repository.todo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sample.todolistmybatismysql.model.dto.Todo;

@Mapper
public interface TodoMapper {

	/**
	 * todoテーブルを取得 ※論理削除フラグ1は取得しない
	 * 
	 * @return List<Todo>
	 */
	List<Todo> selectTodo();

	/**
	 * 特定のtodoテーブルのレコードを取得
	 * 
	 * @param long id
	 * @return Todo
	 */
	Todo selectDetailTodo(@Param("id") long id);

	/**
	 * todoテーブルのレコードを新規作成
	 * 
	 * @param String username
	 * @param String   description
	 * @param String   status
	 */
	void insetTodo(@Param("username") String username, @Param("description") String description, @Param("status") String status);

	/**
	 * todoテーブルのレコードを更新
	 * 
	 * @param Todo   todo
	 * @param String username
	 */
	void updateTodo(Todo todo, @Param("username") String username);

	/**
	 * todoテーブルのレコードを論理削除
	 * 
	 * @param long id
	 */
	void deleteLogicalTodo(@Param("id") long id);
}
