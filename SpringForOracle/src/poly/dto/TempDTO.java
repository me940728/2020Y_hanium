package poly.dto;

/**
 * @author 최별규
 * @version 1.1 MY'C TEMPERATURE_INFO M테이블
 */
public class TempDTO {

	private String temperature_seq_no;
	private String local_name;
	private String to_date;
	private String whet;
	private String min_tem;
	private String max_tem;
	
	public String getTemperature_seq_no() {
		return temperature_seq_no;
	}
	public void setTemperature_seq_no(String temperature_seq_no) {
		this.temperature_seq_no = temperature_seq_no;
	}
	public String getLocal_name() {
		return local_name;
	}
	public void setLocal_name(String local_name) {
		this.local_name = local_name;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public String getWhet() {
		return whet;
	}
	public void setWhet(String whet) {
		this.whet = whet;
	}
	public String getMin_tem() {
		return min_tem;
	}
	public void setMin_tem(String min_tem) {
		this.min_tem = min_tem;
	}
	public String getMax_tem() {
		return max_tem;
	}
	public void setMax_tem(String max_tem) {
		this.max_tem = max_tem;
	}

	

}