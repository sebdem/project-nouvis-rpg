package sebdem.nouvis.datastructs;

import java.awt.geom.Rectangle2D;

public class Vec2 {

	public float x;
	public float y;

	// ===========================================
	// ==||== CONSTRUCTORS ==||==
	// ===========================================

	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	// ===========================================
	// ==||== STATIC METHODS ==||==
	// ===========================================

	public static Vec2 addition(Vec2 base, Vec2 add) {
		return new Vec2(base.x + add.x, base.y + add.y);
	}

	public static Vec2 substract(Vec2 base, Vec2 sub) {
		return new Vec2(base.x - sub.x, base.y - sub.y);
	}
	

	// ===========================================
	// ==||== BASIC METHODS ==||==
	// ===========================================

	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}
	public float value() {
		return (float) x * y;
	}

	public Vec2 unit() {
		float leng = this.length();
		return new Vec2(x / leng, y / leng);
	}

	// ===========================================
	// ==||== ADDITION ==||==
	// ===========================================

	public Vec2 addTo(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vec2 addTo(Vec2 vec) {
		return addTo(vec.x, vec.y);
	}

	public Vec2 addNew(float x, float y) {
		return new Vec2(this.x + x, this.y + y);
	}

	public Vec2 addNew(Vec2 vec) {
		return Vec2.addition(this, vec);
	}

	// ===========================================
	// ==||== SUBSTRACTION ==||==
	// ===========================================

	public Vec2 substractFrom(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vec2 substractFrom(Vec2 vec) {
		return substractFrom(vec.x, vec.y);
	}

	public Vec2 substractNew(float x, float y) {
		return new Vec2(this.x - x, this.y - y);
	}

	public Vec2 substractNew(Vec2 vec) {
		return Vec2.substract(this, vec);
	}

	
	public Vec2 multiplyNew(float factor){
		return new Vec2(this.x * factor, this.y * factor);
	}
	
	// ===========================================
	// ==||== OTHER METHODS ==||==
	// ===========================================

	public Vec2 copy(){
		return new Vec2(x, y);
	}
	
	public String toString(){
		return "Vec2( " + x + " / " + y + " )";
	}
	
	public Vec2 intOffset(){
		return new Vec2(this.x % 1, this.y % 1);
	}
	
	public Rectangle2D.Float createRectTo(Vec2 to) {
		return new Rectangle2D.Float(this.x, this.y, to.x - this.x, to.y - this.y);
	}

	public Rectangle2D.Float createRectSize(Vec2 size) {
		return new Rectangle2D.Float(this.x, this.y, size.x, size.y);
	}
}
