package com.myproject.webapp.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.webapp.biz.board.BoardListVO;
import com.myproject.webapp.biz.board.BoardService;
import com.myproject.webapp.biz.board.BoardVO;

@Controller
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	// 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> map = new HashMap<>();
		map.put("제목", "title");
		map.put("내용", "content");
		return map;
	}
	
	//json
	@RequestMapping("/dataTransformJsonExample.do")
	@ResponseBody
	public List<BoardVO> dataTransformJson(BoardVO vo) {
		vo.setSearchCondition("title");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		return boardList;
	}
	
	// xml
	@RequestMapping("/dataTransformXmlExample.do")
	@ResponseBody
	public BoardListVO dataTransformXml(BoardVO vo) {
		vo.setSearchCondition("title");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardList(boardList);
		return boardListVO;
	}
	
	@RequestMapping("/insertBoardExample.do")
	public String insertBoard(BoardVO vo) throws IOException {
		MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("D:/" + fileName));
		}
		
		boardService.insertBoard(vo);
		return "getBoardListExample.do";
	}
	
	@RequestMapping("/updateBoardExample.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		boardService.updateBoard(vo);
		return "getBoardListExample.do";
	}
	
	@RequestMapping("/deleteBoardExample.do")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "getBoardListExample.do";
	}
	
	@RequestMapping("/getBoardExample.do")
	public String getBoard(BoardVO vo, Model model) {
		model.addAttribute("board", boardService.getBoard(vo));
		return "getBoardExample.jsp";
	}
	
	@RequestMapping("/getBoardListExample.do")
	public String getBoardList(BoardVO vo, Model model) {
		if (vo.getSearchCondition() == null) vo.setSearchCondition("title");
		if (vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		model.addAttribute("boardList", boardService.getBoardList(vo));
		return "getBoardListExample.jsp";
	}
}
