package poly.dto;

/**
 * @author 최별규
 * @version 1.1 MY'C RECOMMEND_INFO 테이블
 */
public class RecomDTO {

	private String recomment_seq_no;
	private String user_seq_nq;
	private String temperature_seq_no;
	private String clothes_seq_no;
	private String recomment_detail;
	private String recomment_rate;
	private String reg_dt;
	private String chg_dt;
	private String chg_id;
	
	public String getRecomment_seq_no() {
		return recomment_seq_no;
	}
	public void setRecomment_seq_no(String recomment_seq_no) {
		this.recomment_seq_no = recomment_seq_no;
	}
	public String getUser_seq_nq() {
		return user_seq_nq;
	}
	public void setUser_seq_nq(String user_seq_nq) {
		this.user_seq_nq = user_seq_nq;
	}
	public String getTemperature_seq_no() {
		return temperature_seq_no;
	}
	public void setTemperature_seq_no(String temperature_seq_no) {
		this.temperature_seq_no = temperature_seq_no;
	}
	public String getClothes_seq_no() {
		return clothes_seq_no;
	}
	public void setClothes_seq_no(String clothes_seq_no) {
		this.clothes_seq_no = clothes_seq_no;
	}
	public String getRecomment_detail() {
		return recomment_detail;
	}
	public void setRecomment_detail(String recomment_detail) {
		this.recomment_detail = recomment_detail;
	}
	public String getRecomment_rate() {
		return recomment_rate;
	}
	public void setRecomment_rate(String recomment_rate) {
		this.recomment_rate = recomment_rate;
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
	public String getChg_id() {
		return chg_id;
	}
	public void setChg_id(String chg_id) {
		this.chg_id = chg_id;
	}

}