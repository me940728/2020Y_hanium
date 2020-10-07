package poly.dto;

/**
 * @author 이창준
 * @version 1.3 MY'C USER_DTO
 */
public class ClosetInfoDTO {

	// CLOSET_INFO
	private String clo_no; // 옷번호(게시판번호)
	private String clo_type; // 옷종류
	private String clo_class; // 옷분류
	private String post_title; // 게시판 제목
	private String post_content;// 게시판 내용
	private String file_path; // 이미지 경로
	private String file_name; // 이미지 파일 이름
	private String my_rep; // 대표사진 여부
	private String sum_rate; // 여름등급
	private String win_rate; // 겨울등급
	private String reg_id; // 작성자
	private String chg_id; // 수정자
	private String reg_dt; // 작성일
	private String chg_dt; // 수정일

	// USER_INFO, CLOSET_INFO .[세션에 올라감]
	private String user_no; // 회원정보_회원번호
	// USER_INFO
	private String my_content; // 회원정보_회원번호

	public String getMy_content() {
		return my_content;
	}

	public void setMy_content(String my_content) {
		this.my_content = my_content;
	}

	public String getClo_no() {
		return clo_no;
	}

	public void setClo_no(String clo_no) {
		this.clo_no = clo_no;
	}

	public String getClo_type() {
		return clo_type;
	}

	public void setClo_type(String clo_type) {
		this.clo_type = clo_type;
	}

	public String getClo_class() {
		return clo_class;
	}

	public void setClo_class(String clo_class) {
		this.clo_class = clo_class;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getMy_rep() {
		return my_rep;
	}

	public void setMy_rep(String my_rep) {
		this.my_rep = my_rep;
	}

	public String getSum_rate() {
		return sum_rate;
	}

	public void setSum_rate(String sum_rate) {
		this.sum_rate = sum_rate;
	}

	public String getWin_rate() {
		return win_rate;
	}

	public void setWin_rate(String win_rate) {
		this.win_rate = win_rate;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getChg_id() {
		return chg_id;
	}

	public void setChg_id(String chg_id) {
		this.chg_id = chg_id;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getChg_dt() {
		return chg_dt;
	}

	public void setChg_dt(String chg_dt) {
		this.chg_dt = chg_dt;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

}