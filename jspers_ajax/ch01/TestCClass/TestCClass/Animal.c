#include <stdio.h>
typedef struct AnimalType { 
	float slept;
	float hunger;
	char* (*wakeup)(void* a);
	float (*eat)(void* a, float howmany);
} Animal;

// 잠을 더 자야하는지 여부를 반환
char* wakeupImplement(Animal* a) {
	float neededsleepTime = 7.6f; // 7.6시간의 잠이 필요함
	if(a->slept < neededsleepTime) {
		return "더자야해. 쿨쿨~";
	}
	return "아함~. 잘잤당.";
}

// howmany는 넘겨준 음식량
// 음식을 먹고, 남은 음식량 반환
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
		printf("돼지가 잔 시간: %f, 돼지가배고픈양: %f\n", pig.slept, pig.hunger);
		printf("돼지야 깨어나라~ : %s\n", pig.wakeup(&pig));
		printf("돼지에게 먹이를 15만큼 준다.\n");
		printf("돼지가 먹고 남은 양: %f\n", pig.eat(&pig, 15.0f));
		++pig.slept;
	}
	printf("돼지야 깨어나라~ : %s\n", pig.wakeup(&pig));
	return 0;
}
