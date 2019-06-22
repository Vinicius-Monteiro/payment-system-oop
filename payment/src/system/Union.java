package system;

public class Union {
	private boolean belongs;
	private int id;
	private double fee;

	public Union(boolean belongs, int id, double fee){
		this.belongs = belongs;
		this.id = id;
		this.fee = fee;
	}

	public void setBelongs(boolean belongs){
		this.belongs = belongs;
	}

	public void setId(int id){
		this.id = id;
	}

	public void setFee(double fee){
		this.fee = fee;
	}

	public boolean getBelongs(){
		return this.belongs;
	}

	public int getId(){
		return this.id;
	}

	public double getFee(){
		return this.fee;
	}
}