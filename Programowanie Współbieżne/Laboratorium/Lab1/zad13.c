#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>


int main(){
	printf("pid procesu: %d\npid procesu macierzystego:%d\n", getpid(), getppid());
	

	return 0;
}
