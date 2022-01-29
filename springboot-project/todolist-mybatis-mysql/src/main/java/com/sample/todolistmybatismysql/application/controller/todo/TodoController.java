package com.sample.todolistmybatismysql.application.controller.todo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sample.todolistmybatismysql.domain.model.entity.Todo;
import com.sample.todolistmybatismysql.domain.service.login.ReservationLoginDetails;
import com.sample.todolistmybatismysql.domain.service.todo.TodoService;

@Controller
@RequestMapping("/todos")
public class TodoController {

	private static final String FOLDER = "html/todo/";
	private static final String FILE1 = "todos";
	private static final String HTML1 = FOLDER + FILE1;
	private static final String FILE2 = "addtodo";
	private static final String HTML2 = FOLDER + FILE2;

	@Autowired
	private TodoService toDoService;

	/**
	 * todo一覧へ遷移
	 * 
	 * @param ModelAndView mav
	 * @return mav
	 */
	@GetMapping
	public ModelAndView todos(ModelAndView mav) {
		List<Todo> todoList = toDoService.selectTodo();
		mav.addObject("todo", todoList);
		mav.setViewName(HTML1);
		
		return mav;
	}

	/**
	 * todoの詳細ページへ遷移
	 * 
	 * @param long         id
	 * @param ModelAndView mav
	 * @return mav
	 */
//	@GetMapping("/detail/{id}")
//	public ModelAndView todoDetail(@PathVariable("id") long id, ModelAndView mav) {
//		boolean detailFlg = true;
//		Todo todo = toDoService.selectDetailTodo(id, detailFlg);
//
//		mav.addObject("todo", todo);
//		mav.setViewName(HTML1);
//
//		return mav;
//	}

	/**
	 * todoの新規作成ページへ遷移
	 * 
	 * @return ModelAndView
	 */
	@GetMapping("/create")
	public ModelAndView create(@AuthenticationPrincipal ReservationLoginDetails userDetails, ModelAndView mav) {
		mav.addObject("todo", new Todo());
		mav.setViewName(HTML2);

		return mav;
	}

	/**
	 * todoテーブルのレコードを新規作成
	 * 
	 * @param Todo                     todo
	 * @param long                     id
	 * @param @AuthenticationPrincipal ReservationLoginDetails
	 *                                 reservationLoginDetails
	 * @return redirect:
	 */
	@PostMapping("/create")
	public String todoCreate(@Valid Todo todo, BindingResult bindingResult,
			@AuthenticationPrincipal ReservationLoginDetails reservationLoginDetails, Model model) {
		if (bindingResult.hasErrors()) {
			return "addtodo";
		}

		if (reservationLoginDetails.getUser().getUsername() != null) {
			toDoService.todoCreate(todo, reservationLoginDetails.getUsername());
		}

		return "redirect:";
	}

	/**
	 * todoの編集ページへ遷移
	 * 
	 * @param long         id
	 * @param ModelAndView mav
	 * @return mav
	 */
//	@GetMapping("/edit/{id}")
//	public ModelAndView todoEdit(@PathVariable("id") long id, ModelAndView mav) {
//		boolean editFlg = false;
//		Todo todo = toDoService.selectDetailTodo(id, editFlg);
//
//		mav.addObject("todo", todo);
//		mav.setViewName(HTML3);
//
//		return mav;
//	}

	/**
	 * todoの保存ボタンを押下した際の処理 更新処理
	 * 
	 * @param Todo                     todo
	 * @param BindingResult            bindingResult
	 * @param @AuthenticationPrincipal ReservationLoginDetails
	 *                                 reservationLoginDetails
	 * @param Model                    model
	 * @return redirect:
	 */
//	@PostMapping("/save")
//	public String todoSave(@Valid Todo todo, BindingResult bindingResult, @AuthenticationPrincipal ReservationLoginDetails reservationLoginDetails, Model model) {
//	if (bindingResult.hasErrors()) {
//		return "addtodo";
//	}
	// toDoService.saveTodo(todo, reservationLoginDetails.getUsername());
//
//		return "redirect:";
//	}

	/**
	 * todoの削除ボタンを押下した際の処理 論理削除処理
	 * 
	 * @param long id
	 * @return redirect:
	 */
	@PostMapping("/delete")
	public String todoDelete(long id) {
		toDoService.deleteLogicalTodo(id);

		return "redirect:";
	}
}
