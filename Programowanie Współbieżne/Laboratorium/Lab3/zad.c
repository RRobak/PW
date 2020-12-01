#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

int n;
char buff[100], buff2[100], filename[100];
int pipechild[2], pipeparent[2];
int childpid;

int main(){
	if(pipe(pipechild) < 0)
		perror("Blad otwierania pipe_c\n");
	if(pipe(pipeparent) < 0)
		perror("Blad otwierania pipe_s\n");

	if((childpid = fork()) == -1){
		perror("Blad fork\n");
	} else {
		if(childpid == 0){
			close(pipechild[1]);
			close(pipeparent[0]);

			if((n = read(pipechild[0], buff, sizeof(buff))) <= 0)
				perror("Blad odczytu pipechild[0]\n");

			FILE *file = fopen(buff, "r");

			if(file == NULL){
				if(write(pipeparent[1], "Blad otwarcia pliku\n", 20) != 20)
					perror("Blad zapisu pipserver[1]\n");
			} else {
				while(fgets(buff, sizeof(buff), file) != NULL)
                			write(pipeparent[1], buff, sizeof(buff));
			}

			fclose(file);

			close(pipechild[0]);
			close(pipeparent[1]);
		} else {
			close(pipechild[0]);
			close(pipeparent[1]);

			printf("Podaj nazwe pliku: ");		
			scanf("%s", filename);

			if(write(pipechild[1], filename, sizeof(filename)) != sizeof(filename))
				perror("Blad zapisu pipechild[1]\n");

			while(read(pipeparent[0], buff2, sizeof(buff2)) > 0)
                    		write(1, buff2, strlen(buff2)); 

			wait(NULL);
			close(pipechild[1]);
			close(pipeparent[0]);
		}
	}

	return 0;
}
