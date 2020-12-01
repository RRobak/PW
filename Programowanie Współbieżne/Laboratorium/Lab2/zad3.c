#include <stdio.h>
#include <stdlib.h>

int childpid, status;

int main(){
	printf("Wystartowal proces macierzysty. PID: %d\n",getpid());

	if ((childpid = fork()) == -1){
	    perror("nie moge forknac");
	    exit(1);
	} else if (childpid == 0){
		sleep(5);
		printf("Proces potomny o pidzie %d z rodzica %d\n",getpid(),getppid());
	} else {
		printf("Proces macierzysty o pidzie %d i dziecku %d\n",getpid(),childpid);
	}

	exit(0);    
}
