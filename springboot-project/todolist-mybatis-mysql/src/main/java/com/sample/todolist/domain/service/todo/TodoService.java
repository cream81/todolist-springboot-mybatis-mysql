package com.sample.todolist.domain.service.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.todolist.domain.mapper.todo.TodoMapper;
import com.sample.todolist.domain.model.entity.Todo;

@Service
public class TodoService {

	@Autowired
	private TodoMapper todoMapper;

	/**
	 * todoテーブルを取得 ※論理削除フラグ1は取得しない
	 * 
	 * @return List<Todo>
	 */
	public List<Todo> selectTodo() {
		List<Todo> todoList = todoMapper.selectTodo();

		return todoList;
	}

	/**
	 * 特定のtodoテーブルのレコードを取得
	 * 
	 * @param long id
	 * @return Todo
	 */
	public Todo selectDetailTodo(long id, boolean flg) {
		Todo todo = todoMapper.selectDetailTodo(id);

		return todo;
	}

	/**
	 * todoテーブルのレコードを新規作成
	 * 
	 * @param Todo   todo
	 * @param String username
	 */
	public void todoCreate(Todo todo, String username) {

		if (!username.equals(null)) {
			todoMapper.insetTodo(username, todo.getDescription(), todo.getStatus());
		}
	}

	/**
	 * todoテーブルのレコードを更新
	 * 
	 * @param Todo   todo
	 * @param String username
	 */
	public void saveTodo(Todo todo, String username) {
		todoMapper.updateTodo(todo, username);
	}

	/**
	 * todoテーブルのレコードを論理削除
	 * 
	 * @param long id
	 */
	public void deleteLogicalTodo(long id) {
		todoMapper.deleteLogicalTodo(id);
	}
}
