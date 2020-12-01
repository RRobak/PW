#include <stdio.h>
#include <stdlib.h>

int childpid, status, i, parent;

int main(){
	for(i = 0; i < 5; i++){
		if ((childpid = fork()) == -1){
		    perror("nie moge forknac");
		    exit(1);
		} else if (childpid == 0){
			printf("Proces potomny o pidzie %d z rodzica %d\n",getpid(),getppid());
			if(i == 4) sleep(3);
			else wait();
		} else {
			printf("Proces macierzysty o pidzie %d i dziecku %d\n",getpid(),childpid);
			wait();
			exit(1);
		}
	}

	exit(0);    
}
