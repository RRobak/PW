#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main(){
	printf("efektywny id uzytkownika: %d\nefektywny id grupy: %d\n", geteuid(), getegid());

	return 0;
}
