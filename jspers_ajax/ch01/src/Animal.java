public class Animal {
	public float slept;
	public float hunger;
	public String wakeup() {
		float neededsleepTime = 7.6f;
		if (this.slept < neededsleepTime)
			return "�� �ھ���. ����~";
		return "����~. �����.";
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
			System.out.println(String.format("������ �� �ð� : %f, ������ ����� �� : %f", pig.slept, pig.hunger));
			System.out.println(String.format("������ �����~ : %s", pig.wakeup()));
			System.out.println("�������� ���̸� 15��ŭ �ش�.");
			System.out.println(String.format("������ �԰� ���� �� : %f", pig.eat(15.0f)));
			++pig.slept;
		}
		System.out.println(String.format("������ �����~ : %s", pig.wakeup()));
	}
}