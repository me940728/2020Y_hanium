package poly.dto;

public class UserInfoDTO {
	//USER_INFO
	private String user_name; //회원이름
	private String password; //비밀번호
	private String sex; //성별
	private String user_an; //닉네임
	private String reg_id; //최초등록자
	private String reg_dt; //최초등록일
	private String chg_id; //최근수정자
	private String chg_dt; //최근수정일
	private String seq; //회원번호
	private String exists_yn;//중복여부
    private String random; // 비밀번호 재설정 확인용
    private String my_nb;//개인 이미지 게시판 번호
    private String my_content; //개인 상태글
	
	//REVIEWS(피드백)
	private String point; //점
	private String outers; //겉옷
	private String top; //상의
	private String bottom; //하의
	private String category; //옷 종류 분류를 위해 사용
	
	//USER_INFO, REVIEWS
	private String email; //이메일
	
	//USER_INFO, GALTABLE
	private String user_id; //회원아이디(PK 시퀀스로 했음)

	public String getMy_nb() {
		return my_nb;
	}

	public void setMy_nb(String my_nb) {
		this.my_nb = my_nb;
	}

	public String getMy_content() {
		return my_content;
	}

	public void setMy_content(String my_content) {
		this.my_content = my_content;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUser_an() {
		return user_an;
	}

	public void setUser_an(String user_an) {
		this.user_an = user_an;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getChg_id() {
		return chg_id;
	}

	public void setChg_id(String chg_id) {
		this.chg_id = chg_id;
	}

	public String getChg_dt() {
		return chg_dt;
	}

	public void setChg_dt(String chg_dt) {
		this.chg_dt = chg_dt;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getExists_yn() {
		return exists_yn;
	}

	public void setExists_yn(String exists_yn) {
		this.exists_yn = exists_yn;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getOuters() {
		return outers;
	}

	public void setOuters(String outers) {
		this.outers = outers;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getRandom() {
		return random;
	}
	
	public void setRandom(String random) {
		this.random = random;
	}
	

}
