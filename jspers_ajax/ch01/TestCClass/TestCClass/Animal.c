#include <stdio.h>
typedef struct AnimalType { 
	float slept;
	float hunger;
	char* (*wakeup)(void* a);
	float (*eat)(void* a, float howmany);
} Animal;

// ���� �� �ھ��ϴ��� ���θ� ��ȯ
char* wakeupImplement(Animal* a) {
	float neededsleepTime = 7.6f; // 7.6�ð��� ���� �ʿ���
	if(a->slept < neededsleepTime) {
		return "���ھ���. ����~";
	}
	return "����~. �����.";
}

// howmany�� �Ѱ��� ���ķ�
// ������ �԰�, ���� ���ķ� ��ȯ
float eatImplement(Animal* a, float howmany) {
	if(a->hunger > 0.0f) {
		a->hunger -= howmany;
		if(a->hunger < 0.0f) {
			float left = -a->hunger;
			a->hunger = 0.0f;
			return left;
		}
	} else if(a->hunger == 0) return howmany;
	return 0.0f;
}

int main(void) { 
	int a, b;
	Animal pig = { 5, 20, wakeupImplement, eatImplement };
	while(pig.hunger != 0 || pig.slept < 7.6f) {
		printf("������ �� �ð�: %f, ����������¾�: %f\n", pig.slept, pig.hunger);
		printf("������ �����~ : %s\n", pig.wakeup(&pig));
		printf("�������� ���̸� 15��ŭ �ش�.\n");
		printf("������ �԰� ���� ��: %f\n", pig.eat(&pig, 15.0f));
		++pig.slept;
	}
	printf("������ �����~ : %s\n", pig.wakeup(&pig));
	return 0;
}
