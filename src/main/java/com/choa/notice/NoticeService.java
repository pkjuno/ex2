package com.choa.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.choa.util.MakePage;
import com.choa.util.PageMaker;
import com.choa.util.RowMaker;

@Service
//NoticeService noticeService = new NoticeService(); 와 같다.
public class NoticeService {
	
	@Inject
	private NoticeDAO noticeDAO;
	
	/*//Constructor(생성자)
	public NoticeService(NoticeDAO noticeDAO){
		this.noticeDAO=noticeDAO;
	}
	//Setter
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}	*/
	//View
	public NoticeDTO noticeView(int num) throws Exception{
		return noticeDAO.noticeView(num);
	} 

	//List
	public List<NoticeDTO> noticeList(int curPage) throws Exception{
		int totalCount = noticeDAO.noticeCount();
		PageMaker pageMaker = new PageMaker(curPage);
		RowMaker rowMaker = pageMaker.getRowMaker("", "");
		MakePage makePage = pageMaker.getMakePage(totalCount);
		return noticeDAO.noticeList(rowMaker);
	}
	//Write
	public int noticeWrite(NoticeDTO noticeDTO) throws Exception{
		return noticeDAO.noticeWrite(noticeDTO);
	}
	//Update
	public int noticeUpate(NoticeDTO noticeDTO) throws Exception{
		return noticeDAO.noticeUpdate(noticeDTO);
	}
	//Delete
	public int noticeDelete(int num) throws Exception{
		return noticeDAO.noticeDelete(num);
	}
	
}
