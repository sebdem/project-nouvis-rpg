package sebdem.nouvis.datastructs;

import sebdem.nouvis.world.Tile;

public class MetaKey {
	
	
	public int originid;
	
	public int tul;
	public int tum;
	public int tur;

	public int tml;
	public int tmr;

	public int tbl;
	public int tbm;
	public int tbr;
	
	
	public MetaKey(Tile origin, int tul, int tum, int tur, int tml, int tmr, int tbl, int tbm, int tbr) {
		super();
		this.originid = origin.getId();
		this.tul = tul;
		this.tum = tum;
		this.tur = tur;
		this.tml = tml;
		this.tmr = tmr;
		this.tbl = tbl;
		this.tbm = tbm;
		this.tbr = tbr;
	}


	public boolean equals(Object o){
		boolean value = false;
		if(o instanceof MetaKey){
			MetaKey mk = (MetaKey)o;
			if(this.originid == mk.originid){
				value = (this.tul == mk.tul) && (this.tum == mk.tum)  && (this.tur == mk.tur)
					 && (this.tml == mk.tml) 						  && (this.tmr == mk.tmr)
					 && (this.tbl == mk.tbl) && (this.tbm == mk.tbm)  && (this.tbr == mk.tbr);
			}
		} else {
			value = super.equals(o);
		}
		return value;
	}
	
	public int hashCode(){
		return originid + (tul + tum + tur)  + (tml + tmr) + (tbl + tbm + tbr) ;
	}
	
}
