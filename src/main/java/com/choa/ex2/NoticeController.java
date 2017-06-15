package com.choa.ex2;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.notice.NoticeDTO;
import com.choa.notice.NoticeService;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	
	@Inject//Inject는 데이터 타입으로 찾는다.
	private NoticeService noticeService; 
	
	//list
	@RequestMapping(value="noticeList", method=RequestMethod.GET)
	public void list(Model model, @RequestParam(defaultValue="1") Integer curPage) throws Exception{
		List<NoticeDTO> ar = noticeService.noticeList(curPage);
		model.addAttribute("list", ar);
	}
	//view
	@RequestMapping(value="noticeView", method=RequestMethod.GET)
	public void view(Integer num, Model model) throws Exception{
		NoticeDTO noticeDTO = noticeService.noticeView(num);
		model.addAttribute("dto", noticeDTO);
	}
	//writeForm
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public void Write(){
		
	}
	//write 처리
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String Write(NoticeDTO noticeDTO, RedirectAttributes redirectAttributes) throws Exception{
		int result = noticeService.noticeWrite(noticeDTO);
		String message = "FAIL";
		if(result>0){
			message = "SUCCESS";
						
		}
			
		
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:noticeList?curPage=1";
		//if 문 처리
	}
	
	//update Form
	@RequestMapping(value="noticeUpdate", method=RequestMethod.GET)
	public void update(Integer num, Model model) throws Exception{
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO = noticeService.noticeView(num);
		model.addAttribute("dto", noticeDTO);
	}
	
	//update 처리
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public String update(NoticeDTO noticeDTO, RedirectAttributes redirectAttributes) throws Exception{
		int result = noticeService.noticeUpate(noticeDTO);
		String message = "FAIL";
		if(result>0){
			message = "SUCCESS";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/notice/noticeList?curPage=1";
		//if 문 처리
	}
	
	//delete 처리
	@RequestMapping(value="noticeDelete", method=RequestMethod.GET)
	public String delete(Integer num, RedirectAttributes redirectAttributes) throws Exception{
		String message = "FAIL";
		int result = noticeService.noticeDelete(num);
		if(result>0){
			message = "SUCCESS";
		}
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/notice/noticeList?curPage=1";
				
		
		
	}
	
}
