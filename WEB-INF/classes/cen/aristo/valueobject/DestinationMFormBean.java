package cen.aristo.valueobject;

import java.io.Serializable;

public class DestinationMFormBean implements Serializable 
{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  int depo_code;
	   private  int dst_code;
	   private  String dst_name;
	   private	int distance;
	   
	   /////////////////Getter and Setter method of //////////////////////////	   
	public int getDepo_code() {
		return depo_code;
	}
	public void setDepo_code(int depo_code) {
		this.depo_code = depo_code;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getDst_code() {
		return dst_code;
	}
	public void setDst_code(int dst_code) {
		this.dst_code = dst_code;
	}
	public String getDst_name() {
		return dst_name;
	}
	public void setDst_name(String dst_name) {
		this.dst_name = dst_name;
	} 

	   
	   
	   
	
	
}
