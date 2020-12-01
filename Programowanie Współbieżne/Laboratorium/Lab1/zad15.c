#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>

struct stat s;
int i, ret;

int main(int argc, char *argv[]){
	if(argc < 2) {
		printf("Zla ilosc argumentow!\n");
		return 0;
	}

	for(i = 2; i <= argc; i++){
		ret = stat("plik.txt", &s);
		if(ret == -1) {
			perror("stat");
			//printf("Blad stat: %d, %d\n", ret, errno);
			continue;
		}
		
		printf("ID of device containing file\t%d\n", (int) s.st_dev);
		printf("inode number\t%d\n", (int) s.st_ino);
		printf("protection\t%d\n", (int) s.st_mode);
	}

	return 0;
}
