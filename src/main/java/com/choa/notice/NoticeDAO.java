package com.choa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.choa.util.DBConnect;
import com.choa.util.RowMaker;

import oracle.jdbc.proxy.annotation.Pre;

@Repository
//NoticeDAO noticeDAO = new NoticeDAO()와 같다.
//멤버 변수 처리가 필요 @Inject 할것.
public class NoticeDAO {
	
	@Inject
	private DataSource dataSource;
	
	/*public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/

	//view
	public NoticeDTO noticeView(int num) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		NoticeDTO noticeDTO = null;
		
		String sql = "select * from notice where num=?";
		st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		rs = st.executeQuery();
		
		if(rs.next()){
			noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
		}else {
			noticeDTO = null;
		}
		
		DBConnect.disConnect(rs, st, con);
		return noticeDTO; 
	}
	
	//Count
	public int noticeCount() throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "select nvl(count(num), 0) from notice";
		
		st= con.prepareStatement(sql);
		
		rs = st.executeQuery();
		
		rs.next();
		
		result = rs.getInt(1);
		
		DBConnect.disConnect(rs, st, con);
		
		return result;
	}
	
	//List
	public List<NoticeDTO> noticeList(RowMaker rowMaker) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		List<NoticeDTO> ar = new ArrayList<NoticeDTO>();
		NoticeDTO noticeDTO = null;
		
		String sql = "select * from "
				+ "(select rownum R , N.* from "
				+ "(select * from notice order by num desc) N) "
				+ "where R between ? and ?";
				
		st = con.prepareStatement(sql);
		
		st.setInt(1, rowMaker.getStartRow());
		st.setInt(2, rowMaker.getLastRow());
		
		rs = st.executeQuery();
		
		while(rs.next()){
			noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setHit(rs.getInt("hit"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			ar.add(noticeDTO);		
		}
		
		DBConnect.disConnect(rs, st, con);
		
		return ar;
	}
	
	//Write
	public int noticeWrite(NoticeDTO noticeDTO) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result=0;
		
		String sql = "insert into notice values (point_seq.nextval, ?,?,?,sysdate,0)";
		
		st = con.prepareStatement(sql);
		
		st.setString(1, noticeDTO.getWriter());
		st.setString(2, noticeDTO.getTitle());
		st.setString(3, noticeDTO.getContents());
		
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
	}
	//Update
	public int noticeUpdate(NoticeDTO noticeDTO) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result=0;
		String sql = "update notice set title=?, contents=? where num=?";
		
		st = con.prepareStatement(sql);
		st.setString(1, noticeDTO.getTitle());
		st.setString(2, noticeDTO.getContents());
		st.setInt(3, noticeDTO.getNum());
		
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		return result;
	}
	//Delete
	public int noticeDelete(int num) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result=0;
		String sql = "delete notice where num=?";
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		
		result = st.executeUpdate();
		
		DBConnect.disConnect(st, con);
		
		return result;
	}
	
	
	
	
}
