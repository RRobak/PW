#include <stdio.h>
#include <stdlib.h>

int childpid, status;

int main(){
	printf("Wystartowal proces macierzysty. PID: %d\n",getpid());

	if ((childpid = fork()) == -1){
	    perror("nie moge forknac");
	    exit(1);
	} else if (childpid == 0){
		printf("Proces potomny o pidzie %d z rodzica %d\n",getpid(),getppid());
		sleep(3);
		exit(2);
	} else {
		printf("Proces macierzysty o pidzie %d i dziecku %d\n",getpid(),childpid);
		wait(&status);
		printf("Proces potomny sie zakonczyl\n");
		printf("%d\n", status);
	}

	exit(0);    
}
