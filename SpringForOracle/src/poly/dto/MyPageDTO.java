package poly.dto;

/**
 * @author 최별규
 * @version 1.2 마이페이지 작성 DTO
 */
public class MyPageDTO {

	private String gal_nb; // 기본키, 순번
	private String gal_name; //게시판 제목
	private String gal_content; //게시판 내용
	private String gal_size; //파일크기
	private String reg_id; //작성자 
	private String reg_dt; //작성일
	private String chg_dt; //수정일
	private String gal_notice; //긍지글 여부(상태표시글?) 1:상태글 2:일반
	private String file_content; //이미지 파일 경로 저장(미리보기 원본 X)
	private String file_name;//이미지 파일 이름 저장(미리보기 원본 X)
	private String gal_rep;//대표사진 여부 1:대표사진 2: 일반
	
	//galtable, user_info 두개

	
	//user_info 
	private String email; //회원구분
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGal_rep() {
		return gal_rep;
	}
	public void setGal_rep(String gal_rep) {
		this.gal_rep = gal_rep;
	}
	public String getGal_notice() {
		return gal_notice;
	}
	public void setGal_notice(String gal_notice) {
		this.gal_notice = gal_notice;
	}
	
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_content() {
		return file_content;
	}
	public void setFile_content(String file_content) {
		this.file_content = file_content;
	}
	public String getGal_nb() {
		return gal_nb;
	}
	public void setGal_nb(String gal_nb) {
		this.gal_nb = gal_nb;
	}
	public String getGal_name() {
		return gal_name;
	}
	public void setGal_name(String gal_name) {
		this.gal_name = gal_name;
	}
	public String getGal_content() {
		return gal_content;
	}
	public void setGal_content(String gal_content) {
		this.gal_content = gal_content;
	}
	public String getGal_size() {
		return gal_size;
	}
	public void setGal_size(String gal_size) {
		this.gal_size = gal_size;
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
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}


}
