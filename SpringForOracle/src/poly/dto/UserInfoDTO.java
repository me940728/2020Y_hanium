package poly.dto;

public class UserInfoDTO {
	//USER_INFO
	private String user_no; //회원번호
	private String password; //비밀번호
	private String user_name; //회원이름
	private String email; // 이메일
	private String sex; //성별
	private String user_an; // 유저 닉네임
	private String random; //인증번호
	private String my_content; //상태글
	private String reg_id; //작성자
	private String chg_id;//수정자
    private String reg_dt; // 작성일
    private String chg_dt;//수정일
	private String exists_yn; // 중복 확인

	//REVIEWS(피드백)
	private String point; //점
	private String outers; //겉옷
	private String top; //상의
	private String bottom; //하의
	private String category; //옷 종류 분류를 위해 사용
	
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public String getMy_content() {
		return my_content;
	}
	public void setMy_content(String my_content) {
		this.my_content = my_content;
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
	public String getExists_yn() {
		return exists_yn;
	}
	public void setExists_yn(String exists_yn) {
		this.exists_yn = exists_yn;
	}


}
