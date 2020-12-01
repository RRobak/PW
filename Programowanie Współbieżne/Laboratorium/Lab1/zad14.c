#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main(){
	printf("id uzytkownika: %d\nid grupy: %d\n", getuid(), getgid());

	return 0;
}
