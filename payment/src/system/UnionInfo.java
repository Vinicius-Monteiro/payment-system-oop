package system;

public class UnionInfo implements Cloneable{
	private boolean belongs;
	private int id;
	private double fee;

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

	public Object clone(){
		try{
			return super.clone();
		} catch(CloneNotSupportedException e) {
			return null;
		}
	}

	public String toString(){
		if (this.belongs) return "\nPertence ao sindicato\nID sindical: " + this.id + "\nTaxa sindical: " + this.fee + "%";
		else return "\nNão pertence ao sindicato";
	}
}