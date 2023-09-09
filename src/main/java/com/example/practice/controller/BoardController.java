package com.example.practice.controller;

import com.example.practice.domain.Board;
import com.example.practice.dto.LoginInfo;
import com.example.practice.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// Component to respond HTTP request. Spring boot will create as a bean automatically
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/")
    public String list(@RequestParam(name="page", defaultValue = "0") int page, HttpSession session, Model model){ // Spring insert HttpSession, Model
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        model.addAttribute("loginInfo", loginInfo);

        long totalCount = boardService.getTotalCount();
        List<Board> list = boardService.getBoards(page);
        long pageCount = totalCount / 10;
        if (totalCount %10 > 0){
            pageCount++;
        }
        int currentPage = page;
//        System.out.println("totalCount = " + totalCount);
//        for(Board board : list){
//            System.out.println(board);
//        }
        model.addAttribute("list", list);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("currentPage", currentPage);
        return "list";
    }

    @GetMapping("/board")
    public String board(@RequestParam("boardId") int boardId, HttpSession session, Model model){
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        model.addAttribute("loginInfo", loginInfo);

        Board board = boardService.getBoard(boardId);
        model.addAttribute("board", board);
        return "board";
    }

    @GetMapping("/writeForm")
    public String writeForm(HttpSession session, Model model){
        LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
        if(loginInfo == null){
            return "redirect:/loginform";
        }

        model.addAttribute("loginInfo", loginInfo);

        return "writeForm";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpSession session
    ) {
        LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
        if(loginInfo == null){
            return "redirect:/loginform";
        }

        boardService.addBoard(loginInfo.getUserId(), title, content);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(
            @RequestParam("boardId") int boardId,
            HttpSession session
    ){
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        if(loginInfo == null) {
            return "redirect:/loginform";
        }

        List<String> roles = loginInfo.getRoles();
        if(roles.contains("ROLE_ADMIN")) {
            boardService.deleteBoard(boardId);
        }else {
            boardService.deleteBoard(loginInfo.getUserId(), boardId);
        }

        return "redirect:/";
    }

    @GetMapping("/updateform")
    public String updateform(@RequestParam("boardId") int boardId, Model model, HttpSession session){
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        if(loginInfo == null) {
            return "redirect:/loginform";
        }

        Board board = boardService.getBoard(boardId, false);
        model.addAttribute("board", board);
        model.addAttribute("loginInfo", loginInfo);
        return "updateform";
    }

    @PostMapping("update")
    public String update(
            @RequestParam("boardId") int boardId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpSession session
    ){
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        if(loginInfo == null) {
            return "redirect:/loginform";
        }
        Board board = boardService.getBoard(boardId, false);
        if(board.getUser().getUserId() != loginInfo.getUserId()){
            return "redirect:/board?boardId=" + boardId;
        }

        boardService.updateBoard(boardId, title, content);
        return "redirect:/board?boardId=" + boardId;
    }
}
