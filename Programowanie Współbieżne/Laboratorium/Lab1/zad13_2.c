#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(){
	printf("getpgrp: %d\n", getpgrp());

	return 0;
}
