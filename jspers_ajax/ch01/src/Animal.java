public class Animal {
	public float slept;
	public float hunger;
	public String wakeup() {
		float neededsleepTime = 7.6f;
		if (this.slept < neededsleepTime)
			return "´õ ÀÚ¾ßÇØ. ÄðÄð~";
		return "¾ÆÇÔ~. ÀßÀä´ç.";
	}
	public float eat(float howmany) {
		if (this.hunger > 0.0f) {
			this.hunger -= howmany;
			if (this.hunger < 0.0f) {
				float left = -this.hunger;
				this.hunger = 0.0f;
				return left;
			}
		} else if (this.hunger == 0)	return howmany;
		return 0.0f;
	}

	public static void main(String[] args) {
		Animal pig = new Animal();
		pig.slept = 5;
		pig.hunger = 20;
		while (pig.hunger != 0 || pig.slept < 7.6f) {
			System.out.println(String.format("µÅÁö°¡ ÀÜ ½Ã°£ : %f, µÅÁö°¡ ¹è°íÇÂ ¾ç : %f", pig.slept, pig.hunger));
			System.out.println(String.format("µÅÁö¾ß ±ú¾î³ª¶ó~ : %s", pig.wakeup()));
			System.out.println("µÅÁö¿¡°Ô ¸ÔÀÌ¸¦ 15¸¸Å­ ÁØ´Ù.");
			System.out.println(String.format("µÅÁö°¡ ¸Ô°í ³²Àº ¾ç : %f", pig.eat(15.0f)));
			++pig.slept;
		}
		System.out.println(String.format("µÅÁö¾ß ±ú¾î³ª¶ó~ : %s", pig.wakeup()));
	}
}